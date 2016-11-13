package com.chicago.dto;


public class ChallengeStatus {
    private Long challengeID;
    private Boolean status;
    private Integer progress;
    private Integer userProgress;
    private Integer playerPosition;
    private Integer playersQuantity;

    public ChallengeStatus() {
    }

    public Long getChallengeID() {
        return challengeID;
    }

    public void setChallengeID(Long challengeID) {
        this.challengeID = challengeID;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public Integer getUserProgress() {
        return userProgress;
    }

    public void setUserProgress(Integer userProgress) {
        this.userProgress = userProgress;
    }

    public Integer getPlayerPosition() {
        return playerPosition;
    }

    public void setPlayerPosition(Integer playerPosition) {
        this.playerPosition = playerPosition;
    }

    public Integer getPlayersQuantity() {
        return playersQuantity;
    }

    public void setPlayersQuantity(Integer playersQuantity) {
        this.playersQuantity = playersQuantity;
    }
}
