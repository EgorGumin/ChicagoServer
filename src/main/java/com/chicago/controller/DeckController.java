package com.chicago.controller;

import com.chicago.dao.ChallengeRepository;
import com.chicago.dao.DeckRepository;
import com.chicago.entity.Challenge;
import com.chicago.entity.Deck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by color on 29.10.2016.
 */
@RestController
@RequestMapping("deck")
public class DeckController extends CrudController<Deck, Long> {
    @Autowired
    private DeckRepository repository;

    @Override
    protected PagingAndSortingRepository<Deck, Long> getRepository() {
        return repository;
    }
}
