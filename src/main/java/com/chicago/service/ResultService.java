package com.chicago.service;

import com.chicago.dao.ChallengeRepository;
import com.chicago.dao.UserProgressRepository;
import com.chicago.dto.ChallengeStatus;
import com.chicago.dto.GameResult;
import com.chicago.entity.Challenge;
import com.chicago.entity.UserProgress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.Date;


@Service
@Transactional
public class ResultService{

    @Autowired
    private UserProgressRepository upr;
    @Autowired
    private ChallengeRepository challengeRepository;

    public ChallengeStatus applyResult(@RequestBody GameResult gameResult) {
        Long userID = gameResult.getUserId();
        Long challengeID = gameResult.getChallengeId();
        Challenge challenge = challengeRepository.findOne(challengeID);
        if (challenge == null){
            return null;
        }

        UserProgress userProgress = upr.getUserProgress(challengeID, userID);
        if (userProgress == null) {
            userProgress = new UserProgress();
            userProgress.setDefaultValues(challengeID, userID);
        }

        if(challenge.getStatus()){
            if(new Date().before(challenge.getFinishDate())){
                if(gameResult.getWin()) {
                    BigInteger challengeProgress = (BigInteger) upr.getProgress(gameResult.getChallengeId());
                    if (challengeProgress == null) {
                        challengeProgress = BigInteger.ZERO;
                    }
                    if(challengeProgress.intValue() + gameResult.getGold() >= challenge.getChallengeType().getGoal()){
                        userProgress.setResult(userProgress.getResult() + gameResult.getGold());
                        upr.saveAndFlush(userProgress);
                        challenge.setRewards(upr);
                        challenge.setStatus(false);
                        challengeRepository.saveAndFlush(challenge);
                    }
                    else{
                        userProgress.setResult(userProgress.getResult() + gameResult.getGold());
                        upr.saveAndFlush(userProgress);
                    }
                }
            }
            else{
                challenge.setRewards(upr);
                challenge.setStatus(false);
                challengeRepository.saveAndFlush(challenge);
            }
        }
        return getChallengeStatus(challengeID, userID);
    }

    private ChallengeStatus getChallengeStatus(Long challengeId, Long userId) {
        Object[][] result = upr.getChallengeStatus(challengeId, userId);
        if (result == null || result.length == 0) {
            return null;
        }
        return new ChallengeStatus(result[0]);
    }
}
