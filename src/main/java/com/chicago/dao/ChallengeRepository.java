package com.chicago.dao;


import com.chicago.entity.Challenge;
import com.chicago.entity.ChallengeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
    @Query(value = "select c from Challenge c where c.status = java.lang.Boolean.TRUE and c.startDate <= :date and " +
            "c.finishDate >= :date")
    List<Challenge> getActiveChallenges(@Param("date") Date date);

    @Query(value = "select cht.goal, gr.sum from challenges c \n" +
            "join challenge_types cht on c.type_id = cht.id\n" +
            "left join ( select challenge_id, sum(result) from user_progress group by challenge_id) \n" +
            "as gr on gr.challenge_id = c.id\n" +
            "where c.id = :challengeId", nativeQuery = true)
    Object[][] getCurrentProgress(@Param("challengeId") Long challengeId);



}
