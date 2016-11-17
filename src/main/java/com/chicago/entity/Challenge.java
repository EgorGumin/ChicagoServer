package com.chicago.entity;

import com.chicago.dao.UserProgressRepository;
import com.chicago.dao.UserRepository;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "challenges")
public class Challenge {
    public Challenge() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type_id")
    private Long typeId;

    @ManyToOne
    @JoinColumn(name = "type_id", insertable = false, updatable = false)
    private ChallengeType challengeType;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "finish_date")
    private Date finishDate;

    @Column
    private Boolean status;

    @Column(name = "deck_id")
    private Long deckId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public ChallengeType getChallengeType() {
        return challengeType;
    }

    public void setChallengeType(ChallengeType challengeType) {
        this.challengeType = challengeType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Long getDeckId() {
        return deckId;
    }

    public void setDeckId(Long deckId) {
        this.deckId = deckId;
    }

    public void setRewards(UserProgressRepository upr) {
        List<UserProgress> userProgressData = upr.findByChallengeId(id).stream()
                .sorted((o1, o2) -> {
                    return -o1.getResult().compareTo(o2.getResult());
                })
                .collect(Collectors.toList());

        for (int i = 0; i < userProgressData.size(); i++) {
            UserProgress userProgress = userProgressData.get(i);
            int gold = 0;
            BigInteger challengeProgress = (BigInteger) upr.getProgress(id);
            if (challengeProgress == null) {
                challengeProgress = BigInteger.ZERO;
            }
            if(challengeProgress.intValue() >= challengeType.getGoal())
            switch (i) {
                case 0:
                    gold = challengeType.getPrizeGrand();
                    break;
                case 1:
                    gold = challengeType.getPrizeTop();
                    break;
                default:
                    //TODO fix different info from sql and this sorting algo
                    if (userProgress.getResult() > 0) {
                        gold = challengeType.getPrizeParticipate();
                    }
            }
            userProgress.setReward(gold);
            upr.saveAndFlush(userProgress);
        }
    }
}
