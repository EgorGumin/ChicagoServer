package com.chicago.dto;

import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * Created by color on 29.10.2016.
 */
public class GameResult {
    private Long deckId;
    private Long challengeId;
    private Long userId;

    private Integer gold;
    private Integer enemies;
    private Boolean win;
    
    public GameResult() {}

    public Long getDeckId() {
        return deckId;
    }

    public void setDeckId(Long deckId) {
        this.deckId = deckId;
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

    public Integer getGold() {
        return gold;
    }

    public void setGold(Integer gold) {
        this.gold = gold;
    }

    public Integer getEnemies() {
        return enemies;
    }

    public void setEnemies(Integer enemies) {
        this.enemies = enemies;
    }

    public Boolean getWin() {
        return win;
    }

    public void setWin(Boolean win) {
        this.win = win;
    }
}
