package com.chicago.entity;

import javax.persistence.*;

/**
 * Created by color on 29.10.2016.
 */
@Entity
@Table(name = "challenge_types")
public class ChallengeType {
    public ChallengeType() {
        super();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private int type;

    @Column
    private String description;

    @Column(name = "prize_grand")
    private int prizeGrand;

    @Column(name = "prize_top")
    private int prizeTop;

    @Column(name = "prize_participate")
    private int prizeParticipate;

    @Column
    private int goal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrizeGrand() {
        return prizeGrand;
    }

    public void setPrizeGrand(int prizeGrand) {
        this.prizeGrand = prizeGrand;
    }

    public int getPrizeTop() {
        return prizeTop;
    }

    public void setPrizeTop(int prizeTop) {
        this.prizeTop = prizeTop;
    }

    public int getPrizeParticipate() {
        return prizeParticipate;
    }

    public void setPrizeParticipate(int prizeParticipate) {
        this.prizeParticipate = prizeParticipate;
    }

    public int getGoal() {
        return goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }
}
