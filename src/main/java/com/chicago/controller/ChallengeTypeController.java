package com.chicago.controller;

import com.chicago.dao.ChallengeRepository;
import com.chicago.dao.ChallengeTypeRepository;
import com.chicago.entity.Challenge;
import com.chicago.entity.ChallengeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("challenge_type")
public class ChallengeTypeController extends CrudController<ChallengeType, Long> {
    @Autowired
    private ChallengeTypeRepository repository;

    @Override
    protected PagingAndSortingRepository<ChallengeType, Long> getRepository() {
        return repository;
    }
}
