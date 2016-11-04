package com.chicago.dao;


import com.chicago.entity.ChallengeType;
import com.chicago.entity.UserProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserProgressRepository extends JpaRepository<UserProgress, Long> {

    @Query(value = "select up from UserProgress up where up.challengeId = :challengeId and " +
            "up.userId = :userId")
    UserProgress getUserProgress(@Param("challengeId")Long challengeId, @Param("userId") Long userId);

    @Query(value = "select sum(result) from user_progress where challenge_id = :challengeId", nativeQuery = true)
    Object getProgress(@Param("challengeId") Long challengeId);

    List<UserProgress> findByChallengeId(@Param("challengeId") Long challengeId);

    @Query(value = "select up.challenge_id,up.user_id,up.result,ch.status, cht.goal, s1.sum from user_progress up " +
            "join challenges ch on ch.id=up.challenge_id " +
            "join challenge_types cht on cht.id = ch.type_id " +
            "join (select up2.challenge_id, sum(up2.result) as sum from user_progress up2 group by up2.challenge_id) " +
            "as s1 on up.challenge_id = s1.challenge_id " +
            "where up.challenge_id=:challengeId and up.user_id =:userId", nativeQuery = true)
    Object[][] getChallengeInfo(@Param("challengeId")Long challengeId, @Param("userId") Long userId);
}
