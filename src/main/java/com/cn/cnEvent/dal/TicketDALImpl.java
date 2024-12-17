package com.cn.cnEvent.dal;

import com.cn.cnEvent.entity.Ticket;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class TicketDALImpl implements TicketDAL {

    @Autowired
    EntityManager entityManager;

    @Override
    public Ticket getById(Long id) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(Ticket.class,id);
    }

    @Override
    public List<Ticket> getAll() {
        Session session = entityManager.unwrap(Session.class);

        String hql = "select t from Ticket t";

        Query query = session.createQuery(hql , Ticket.class);

        return query.getResultList();
    }

    @Override
    public List<Ticket> getAllTicketsByAge(Long age) {
        Session session = entityManager.unwrap(Session.class);


        //String hql = "select t from Ticket t where t.person.age =: age";
//        Query query = session.createQuery(hql , Ticket.class);
//
//        query.setParameter("age",age);

        List<Ticket> ticketList = new ArrayList<>();

        for(Ticket ticket : getAll()){
            if(ticket.getPerson().getAge() < age){
                ticketList.add(ticket);
            }
        }

        return ticketList;

    }
}
