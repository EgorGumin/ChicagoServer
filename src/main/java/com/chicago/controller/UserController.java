package com.chicago.controller;

import com.chicago.dao.UserRepository;
import com.chicago.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by color on 29.10.2016.
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserRepository userRepository;



    @RequestMapping(method = RequestMethod.GET, path = "{id}")
    public Users getById(@PathVariable Long id){

        return userRepository.findOne(id);
    }
}
