package com.chicago.service;

import com.chicago.dao.ChallengeRepository;
import com.chicago.dao.UserProgressRepository;
import com.chicago.dao.UserRepository;
import com.chicago.dto.ChallengeInfo;
import com.chicago.dto.GameResult;
import com.chicago.entity.Challenge;
import com.chicago.entity.ChallengeType;
import com.chicago.entity.UserProgress;
import com.chicago.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by color on 30.10.2016.
 */

@Service
@Transactional
public class ResultService {

    @Autowired
    private UserProgressRepository upr;
    @Autowired
    private ChallengeRepository challengeRepository;

//    @Autowired
//    private EntityManager em;


    @Autowired
    private UserRepository userRepository;

    public ChallengeInfo applyResult(@RequestBody GameResult gameResult) {
        int prize = 0;
        if (!gameResult.getWin()) {
            return getChallengeInfo(gameResult.getChallengeId(), gameResult.getUserId(), null);
        }
        Challenge challenge = challengeRepository.findOne(gameResult.getChallengeId());

        BigInteger sum = (BigInteger) upr.getProgress(gameResult.getChallengeId());
        if (sum == null) {
            sum = BigInteger.ZERO;
        }

        UserProgress userProgress = upr.getUserProgress(gameResult.getChallengeId(), gameResult.getUserId());
        if (userProgress == null) {
            userProgress = new UserProgress();
            userProgress.setChallengeId(gameResult.getChallengeId());
            userProgress.setUserId(gameResult.getUserId());
            userProgress.setResult(0);
        }
        userProgress.setResult(userProgress.getResult() + gameResult.getGold());
        upr.saveAndFlush(userProgress);

        if (sum.intValue() + gameResult.getGold() >= challenge.getChallengeType().getGoal()) {
            challenge.setStatus(false);
            challengeRepository.saveAndFlush(challenge);

            List<UserProgress> members = upr.findByChallengeId(
                    gameResult.getChallengeId()).stream()
                    .sorted((o1, o2) -> {
                        return -Integer.valueOf(o1.getResult()).compareTo(Integer.valueOf(o2.getResult()));
                    })
                    .collect(Collectors.toList());
            for (int i = 0; i < members.size(); i++) {
                Users user = userRepository.findOne(members.get(i).getUserId());
                int gold = 0;
                ChallengeType challengeType = challenge.getChallengeType();

                switch (i) {
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
                userRepository.saveAndFlush(user);
                if (Objects.equals(user.getId(), gameResult.getUserId())) {
                    prize = gold;
                }
            }
        }
        if (!challenge.getStatus() || challenge.getFinishDate().before(new Date())) {
            return getChallengeInfo(gameResult.getChallengeId(), gameResult.getUserId(), null);
        }
//        em.flush();
        return getChallengeInfo(gameResult.getChallengeId(), gameResult.getUserId(), prize);
    }

    private ChallengeInfo getChallengeInfo(Long userId, Long challengeId, Integer prize) {
        Object[][] result = upr.getChallengeInfo(challengeId, userId);
        if (result == null || result.length == 0) {
            return null;
        }
        Object[] row = result[0];
        ChallengeInfo ci = new ChallengeInfo();
        ci.setChallengeId(challengeId);
        ci.setUserId(userId);
        ci.setPrize(prize);

        ci.setUserProgress((Integer) row[2]);
        ci.setStatus((Boolean) row[3]);
        ci.setGoal((Integer) row[4]);
        ci.setProgress(((BigInteger) row[5]).intValue());
        return ci;
    }
}
