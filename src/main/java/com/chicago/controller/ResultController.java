package com.chicago.controller;

import com.chicago.dao.ChallengeRepository;
import com.chicago.dao.UserProgressRepository;
import com.chicago.dao.UserRepository;
import com.chicago.dto.ChallengeInfo;
import com.chicago.dto.ChallengeStatus;
import com.chicago.dto.GameResult;
import com.chicago.entity.Challenge;
import com.chicago.entity.ChallengeType;
import com.chicago.entity.UserProgress;
import com.chicago.entity.Users;
import com.chicago.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@RestController
@RequestMapping("game")
public class ResultController {
    @Autowired
    private ResultService service;

    @RequestMapping(method = RequestMethod.POST)
    public ChallengeStatus applyResult(@RequestBody GameResult gameResult) {
       return service.applyResult(gameResult);
    }
}
