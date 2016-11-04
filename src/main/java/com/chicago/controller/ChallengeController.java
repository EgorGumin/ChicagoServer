package com.chicago.controller;

import com.chicago.dao.ChallengeRepository;
import com.chicago.dao.UserRepository;
import com.chicago.entity.Challenge;
import com.chicago.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("challenge")
public class ChallengeController extends CrudController<Challenge, Long> {
    @Autowired
    private ChallengeRepository repository;

    @Override
    protected JpaRepository<Challenge, Long> getRepository() {
        return repository;
    }

    @RequestMapping(path = "active", method = RequestMethod.GET)
    public List<Challenge> getActiveChallenges(){
        return repository.getActiveChallenges(new Date());
    }

    @RequestMapping(path = "{id}/progress", method = RequestMethod.GET)
    public BigDecimal[] getChallenge(@PathVariable Long id){
        Object[][] result = repository.getCurrentProgress(id);
        if(result == null || result.length == 0){
            return new BigDecimal[]{BigDecimal.ZERO, BigDecimal.ZERO};
        }
        Object[] data = result[0];
        if(data[1] == null){

            return new BigDecimal[]{(BigDecimal) new BigDecimal((Integer) data[0]), BigDecimal.ZERO};
        }
        return new BigDecimal[]{(BigDecimal) new BigDecimal((Integer) data[0]), new BigDecimal((BigInteger) data[1])};
    }
}
