package com.cn.cnEvent.dal;

import com.cn.cnEvent.entity.Event;
import com.cn.cnEvent.entity.Speaker;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SpeakerDALImpl implements  SpeakerDAL {

    @Autowired
    EntityManager entityManager;

    @Override
    public Speaker getById(Long id) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(Speaker.class , id);
    }

    @Override
    public List<Speaker> getAll() {
        Session session = entityManager.unwrap(Session.class);
        String hql = "select s from Speaker s";
        Query query = session.createQuery(hql , Speaker.class);
        return query.getResultList();
    }

    @Override
    public List<Speaker> getAllSpeakersWithEventCountAndExperience(Long eventCount, Long experience) {

        Session session = entityManager.unwrap(Session.class);
        String hql = "select s from Speaker s";

        List<Speaker> speakers = new ArrayList<>();

        for(Speaker speaker:getAll()){
            if(speaker.getEvents().size() >= eventCount && speaker.getExperience() >= experience)
               speakers.add(speaker);
        }

        return  speakers;
    }

    @Override
    public String save(Speaker speaker) {
        Session session = entityManager.unwrap(Session.class);
        session.save(speaker);
        return "The speaker was saved successfully.";
    }

    @Override
    public void saveNewRecord(Speaker newSpeaker, Long eventId) {

        Session session = entityManager.unwrap(Session.class);

        Event event = session.get(Event.class , eventId);

        if(newSpeaker.getEvents() == null)
             newSpeaker.setEvents(new ArrayList<>());

        if(event.getSpeakers() == null)
              event.setSpeakers(new ArrayList<>());

        newSpeaker.getEvents().add(event);
        event.getSpeakers().add(newSpeaker);

        session.save(event);

    }
}
