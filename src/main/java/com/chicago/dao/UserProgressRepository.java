package com.chicago.dao;


import com.chicago.entity.UserProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserProgressRepository extends JpaRepository<UserProgress, Long> {
    //TODO check UserProgress
    @Query(value = "select up from UserProgress up where up.challengeId = :challengeId and " +
            "up.userId = :userId")
    UserProgress getUserProgress(@Param("challengeId") Long challengeId, @Param("userId") Long userId);

    @Query(value = "select sum(result) from user_progress where challenge_id = :challengeId", nativeQuery = true)
    Object getProgress(@Param("challengeId") Long challengeId);

    List<UserProgress> findByChallengeId(@Param("challengeId") Long challengeId);

    @Query(value = "select up.challenge_id,up.user_id,up.result,ch.status, cht.goal, s1.sum from user_progress up " +
            "join challenges ch on ch.id=up.challenge_id " +
            "join challenge_types cht on cht.id = ch.type_id " +
            "join (select up2.challenge_id, sum(up2.result) as sum from user_progress up2 group by up2.challenge_id) " +
            "as s1 on up.challenge_id = s1.challenge_id " +
            "where up.challenge_id=:challengeId and up.user_id =:userId", nativeQuery = true)
    Object[][] getChallengeInfo(@Param("challengeId") Long challengeId, @Param("userId") Long userId);

    @Query(value = "select up.challenge_id,ch.status, up.result, s1.sum, pos.player_position, quant.players_quantity from user_progress up\n" +
            "join challenges ch on ch.id=up.challenge_id\n" +
            "join (select ch1.id challenge_id, ch1.status, COALESCE(sum(up2.result), 0) sum from challenges ch1  \n" +
            "      LEFT OUTER JOIN user_progress up2 on (ch1.id=up2.challenge_id) group by ch1.id) \n" +
            "      as s1 on up.challenge_id = s1.challenge_id\n" +
            "join (select COALESCE((select count(*) from (select * from user_progress up3 where \n" +
            "          ((up3.result > (select up4.result from user_progress up4 where (up4.user_id=:userId and up4.challenge_id = :challengeId))) and up3.challenge_id = :challengeId)) cntr group by cntr.challenge_id) +1, 1) \n" +
            "          as player_position, :challengeId as challenge_id) as pos on ch.id = pos.challenge_id\n" +
            "join (select COALESCE((select count(*) from user_progress up6 where up6.challenge_id=:challengeId group by up6.challenge_id), 0) as players_quantity, :challengeId as challenge_id) \n" +
            "\tas quant on ch.id = quant.challenge_id\n" +
            "where up.challenge_id=:challengeId and up.user_id =:userId", nativeQuery = true)
    Object[][] getChallengeStatus(@Param("challengeId") Long challengeId, @Param("userId") Long userId);
}
