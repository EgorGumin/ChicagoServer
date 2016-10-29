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
    private long challengeId;

    @Column(name = "user_id")
    private long userId;

    @Column
    private int result;
}
