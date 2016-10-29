package com.chicago.controller;

import com.chicago.dao.ChallengeRepository;
import com.chicago.dao.UserRepository;
import com.chicago.entity.Challenge;
import com.chicago.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by color on 29.10.2016.
 */
@RestController
@RequestMapping("challenge")
public class ChallengeController extends CrudController<Challenge, Long> {
    @Autowired
    private ChallengeRepository repository;

    @Override
    protected PagingAndSortingRepository<Challenge, Long> getRepository() {
        return repository;
    }
}
