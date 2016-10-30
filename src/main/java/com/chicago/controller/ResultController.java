package com.chicago.controller;

import com.chicago.dao.ChallengeRepository;
import com.chicago.dao.UserProgressRepository;
import com.chicago.dao.UserRepository;
import com.chicago.dto.GameResult;
import com.chicago.entity.Challenge;
import com.chicago.entity.ChallengeType;
import com.chicago.entity.UserProgress;
import com.chicago.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("game")
public class ResultController {
    @Autowired
    private UserProgressRepository upr;
    @Autowired
    private ChallengeRepository challengeRepository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.POST)
    public void applyResult(@RequestBody GameResult gameResult){
        if(!gameResult.getWin()){
            return;
        }
        Challenge challenge = challengeRepository.findOne(gameResult.getChallengeId());
        if(!challenge.getStatus() || challenge.getFinishDate().before(new Date())){
            return;
        }
        BigInteger sum = (BigInteger) upr.getProgress(gameResult.getChallengeId());

        UserProgress userProgress = upr.getUserProgress(gameResult.getChallengeId(), gameResult.getUserId());
        if (userProgress == null){
            userProgress = new UserProgress();
            userProgress.setChallengeId(gameResult.getChallengeId());
            userProgress.setUserId(gameResult.getUserId());
            userProgress.setResult(0);
        }
        userProgress.setResult(userProgress.getResult() + gameResult.getGold());
        upr.save(userProgress);

        if (sum.intValue() + gameResult.getGold() >= challenge.getChallengeType().getGoal()) {
            challenge.setStatus(false);
            challengeRepository.save(challenge);

            List<UserProgress> members = upr.findByChallengeId(
                    gameResult.getChallengeId()).stream()
                    .sorted((o1, o2) -> {
                        return -Integer.valueOf(o1.getResult()).compareTo(Integer.valueOf(o2.getResult()));})
                    .collect(Collectors.toList());
            for (int i = 0; i < members.size(); i++) {
                Users user = userRepository.findOne(members.get(i).getUserId());
                int gold = 0;
                ChallengeType challengeType = challenge.getChallengeType();

                switch (i){
                    case 0:
                        gold = challengeType.getPrizeGrand();
                        break;
                    case 1:
                        gold = challengeType.getPrizeTop();
                        break;
                    default:
                        gold = challengeType.getPrizeParticipate();
                }
                user.setGold(user.getGold() + gold);
                userRepository.save(user);
            }
        }



    }
}
