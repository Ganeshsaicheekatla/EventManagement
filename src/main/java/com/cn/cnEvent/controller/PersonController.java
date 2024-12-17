package com.cn.cnEvent.controller;

import com.cn.cnEvent.entity.Person;
import com.cn.cnEvent.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable Long id){
        return  personService.getById(id);
    }

    @GetMapping("/all")
    public List<Person> getAllPersonList()
    {
        return personService.getAllPersonList();
    }
}
