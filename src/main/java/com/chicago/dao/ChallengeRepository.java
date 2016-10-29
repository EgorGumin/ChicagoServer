package com.chicago.dao;


import com.chicago.entity.Challenge;
import com.chicago.entity.ChallengeType;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChallengeRepository extends PagingAndSortingRepository<Challenge, Long> {

}
