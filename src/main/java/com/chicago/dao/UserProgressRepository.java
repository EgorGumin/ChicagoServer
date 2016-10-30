package com.chicago.dao;


import com.chicago.entity.ChallengeType;
import com.chicago.entity.UserProgress;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserProgressRepository extends PagingAndSortingRepository<UserProgress, Long> {

    @Query(value = "select up from UserProgress up where up.challengeId = :challengeId and " +
            "up.userId = :userId")
    UserProgress getUserProgress(@Param("challengeId")Long challengeId, @Param("userId") Long userId);

    @Query(value = "select sum(result) from user_progress where challenge_id = :challengeId", nativeQuery = true)
    Object getProgress(@Param("challengeId") Long challengeId);

    List<UserProgress> findByChallengeId(@Param("challengeId") Long challengeId);
}
