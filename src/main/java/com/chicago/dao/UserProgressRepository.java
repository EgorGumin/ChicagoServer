package com.chicago.dao;


import com.chicago.entity.ChallengeType;
import com.chicago.entity.UserProgress;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProgressRepository extends PagingAndSortingRepository<UserProgress, Long> {
}
