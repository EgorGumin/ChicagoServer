package com.chicago.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_progress")
public class UserProgress {
    public UserProgress() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "challenge_id")
    private Long challengeId;

    @Column(name = "user_id")
    private Long userId;

    @Column
    private Integer result;

    @Column
    private Integer reward;

    @Column(name = "reward_received")
    private Boolean rewardReceived;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(Long challengeId) {
        this.challengeId = challengeId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Integer getReward() {
        return reward;
    }

    public void setReward(Integer reward) {
        this.reward = reward;
    }

    public Boolean getRewardReceived() {
        return rewardReceived;
    }

    public void setRewardReceived(Boolean rewardReceived) {
        this.rewardReceived = rewardReceived;
    }

    public void setDefaultValues(Long challengeID, Long userID) {
        this.challengeId = challengeID;
        this.userId = userID;
        this.reward = -1;
        this.rewardReceived = false;
        this.result = 0;
    }
}
