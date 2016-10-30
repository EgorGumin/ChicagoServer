package com.chicago.controller;

import com.chicago.entity.Challenge;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.Serializable;


public abstract class CrudController <T, I extends Serializable>{

    @RequestMapping(method = RequestMethod.GET, path = "{id}")
    public T getById(@PathVariable I id){
        return getRepository().findOne(id);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "{id}")
    public T update(@RequestBody T entity){
        return getRepository().save(entity);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "{id}")
    public void update(@PathVariable I id){
        getRepository().delete(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public T create(@RequestBody T entity){
        return getRepository().save(entity);
    }

    protected abstract PagingAndSortingRepository<T, I> getRepository();
}
