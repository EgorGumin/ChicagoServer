package com.chicago.controller;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;

@RestController
@RequestMapping("hello")
public class SampleController {
    @RequestMapping(method = RequestMethod.GET)
    public Challenge challenge() {

        InputStream is = this.getClass().getClassLoader().getResourceAsStream("cards.json");
        Gson gson = new Gson();

        return gson.fromJson(new InputStreamReader(is), Challenge.class);
    }
}
