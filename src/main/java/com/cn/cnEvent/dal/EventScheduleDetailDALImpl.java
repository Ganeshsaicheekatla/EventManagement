package com.cn.cnEvent.dal;

import com.cn.cnEvent.entity.EventScheduleDetail;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.util.List;

@Repository
public class EventScheduleDetailDALImpl implements EventScheduleDetailDAL{

    @Autowired
    EntityManager entityManager;

    @Override
    public String save(EventScheduleDetail eventScheduleDetail) {
        Session session = entityManager.unwrap(Session.class);
        session.save(eventScheduleDetail);


        return "saved successfully";
    }

    @Override
    public List<EventScheduleDetail> getAllEventSchedule() {
        Session session = entityManager.unwrap(Session.class);

        String hql = "select es from EventScheduleDetail es";

        Query query = session.createQuery(hql , EventScheduleDetail.class);

        return query.getResultList();
    }

    @Override
    public EventScheduleDetail getById(Long id) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(EventScheduleDetail.class , id);
    }
}
