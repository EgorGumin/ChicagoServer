package com.chicago.dto;


public class ChallengeInfo {
    private Long challengeId;
    //TODO change UserId to userID
    private Long UserId;
    private Integer userProgress;
    private Boolean status;
    private Integer goal;
    private Integer progress;
    private Integer prize;

    public Long getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(Long challengeId) {
        this.challengeId = challengeId;
    }

    public Long getUserId() {
        return UserId;
    }

    public void setUserId(Long userId) {
        UserId = userId;
    }

    public Integer getUserProgress() {
        return userProgress;
    }

    public void setUserProgress(Integer userProgress) {
        this.userProgress = userProgress;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getGoal() {
        return goal;
    }

    public void setGoal(Integer goal) {
        this.goal = goal;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public Integer getPrize() {
        return prize;
    }

    public void setPrize(Integer prize) {
        this.prize = prize;
    }
}
