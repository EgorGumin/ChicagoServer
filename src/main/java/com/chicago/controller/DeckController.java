package com.chicago.controller;

import com.chicago.dao.ChallengeRepository;
import com.chicago.dao.DeckRepository;
import com.chicago.entity.Challenge;
import com.chicago.entity.Deck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RestController
@RequestMapping("deck")
public class DeckController extends CrudController<Deck, Long> {
    @Autowired
    private DeckRepository repository;

    @Override
    protected PagingAndSortingRepository<Deck, Long> getRepository() {
        return repository;
    }

    @RequestMapping(path = "{id}/clear", method = RequestMethod.GET)
    public void getDeckClear(@PathVariable Long id, HttpServletResponse response) throws IOException {
        Deck deck = repository.findOne(id);
        response.getWriter().write(deck.getDeck());
    }
}
