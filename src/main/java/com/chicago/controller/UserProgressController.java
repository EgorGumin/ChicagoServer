package com.chicago.controller;

import com.chicago.dao.ChallengeRepository;
import com.chicago.dao.UserProgressRepository;
import com.chicago.entity.Challenge;
import com.chicago.entity.UserProgress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by color on 29.10.2016.
 */
@RestController
@RequestMapping("user_progress")
public class UserProgressController extends CrudController<UserProgress, Long> {
    @Autowired
    private UserProgressRepository repository;

    @Override
    protected PagingAndSortingRepository<UserProgress, Long> getRepository() {
        return repository;
    }
}
