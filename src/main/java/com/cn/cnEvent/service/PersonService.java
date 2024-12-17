package com.cn.cnEvent.service;

import com.cn.cnEvent.dal.PersonDAL;
import com.cn.cnEvent.entity.Person;
import com.cn.cnEvent.exception.InvalidInputException;
import com.cn.cnEvent.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PersonService {

    @Autowired
    PersonDAL personDAL;

    @Transactional
    public Person getById(Long id) {

        Person person = personDAL.getById(id);
        if(person == null){
            throw  new NotFoundException("person with given id not founded");
        }

        return person;
    }

    @Transactional
    public List<Person> getAllPersonList() {
        List<Person> personList= personDAL.getAll();

        if(ObjectUtils.isEmpty(personList)){
            throw new NotFoundException("No person details are Available right now.");
        }
        return personList;
    }
}
