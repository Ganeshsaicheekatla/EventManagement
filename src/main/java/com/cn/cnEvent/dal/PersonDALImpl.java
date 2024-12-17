package com.cn.cnEvent.dal;

import com.cn.cnEvent.entity.Person;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class PersonDALImpl implements PersonDAL{

    @Autowired
    EntityManager entityManager;

    @Override
    public Person getById(Long id) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(Person.class,id);
    }

    @Override
    public List<Person> getAll() {
        Session session = entityManager.unwrap(Session.class);

        String hql = "select p from Person p";

        Query query = session.createQuery(hql , Person.class);

        return query.getResultList();
    }
}
