package com.cn.cnEvent.dal;

import com.cn.cnEvent.entity.Event;
import com.cn.cnEvent.entity.EventScheduleDetail;
import com.cn.cnEvent.entity.Ticket;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

@Repository
public class EventDALImpl implements EventDAL {

    @Autowired
    EntityManager entityManager;

    @Override
    public Event getById(Long id) {
        Session session = entityManager.unwrap(Session.class);
        Event event = session.get(Event.class, id);
        return event;
    }

    @Override
    public List<Event> getAllEvents() {
        Session session = entityManager.unwrap(Session.class);
        List<Event> allEvents = session.createQuery("SELECT e FROM Event e", Event.class).getResultList();
        return allEvents;
    }

    @Override
    public String save(Event event) {
        Session session = entityManager.unwrap(Session.class);
        session.save(event);

        return "The event was saved successfully.";
    }

    @Override
    public String delete(Long id) {
        Session session = entityManager.unwrap(Session.class);
        Event event = session.get(Event.class, id);
        session.delete(event);
        return "The event was deleted successfully";
    }

    @Override
    public String update(Event updateEvent) {
        Session session = entityManager.unwrap(Session.class);
        Event currentEvent = session.get(Event.class, updateEvent.getId());
        currentEvent.setName(updateEvent.getName());
        currentEvent.setDescription(updateEvent.getDescription());
        session.update(currentEvent);
        return "Event is updated successfully";
    }


    @Override
    public String deleteEventScheduleByEventId(Long id) {
//        Session session = entityManager.unwrap(Session.class);
//        Event event = session.get(Event.class, id);
////        Event schedule id
//
//        Long eventScheduleId = event.getEventScheduleDetail().getId();
//        System.out.println(eventScheduleId);
//        EventScheduleDetail eventScheduleDetail = session.get(EventScheduleDetail.class , eventScheduleId);
//        System.out.println(eventScheduleDetail);
//        if(session.get(EventScheduleDetail.class,eventScheduleId)!=null)
//            System.out.println("n");
//        session.delete(eventScheduleDetail);
//        if(session.get(EventScheduleDetail.class,eventScheduleId)!=null)
//            System.out.println("dn");
        Session session = entityManager.unwrap(Session.class);
        try {
            EventScheduleDetail eventScheduleDetail = session.createQuery(
                            "SELECT e FROM EventScheduleDetail e WHERE e.id = :id", EventScheduleDetail.class)
                    .setParameter("id", id)
                    .getSingleResult();


            for (Event event : getAllEvents()) {
                if(event.getEventScheduleDetail()!=null && event.getEventScheduleDetail().getId().equals(id)){
                    event.setEventScheduleDetail(null);
                    save(event);
                }
            }
            session.delete(eventScheduleDetail);
            return "The eventSchedule was deleted successfully";
        } catch (NoResultException ex) {
            return "EventScheduleDetail with id " + id + " does not exist";
        }


    }

    @Override
    public List<Event> getAllEventByLocation(String location) {

        Session session = entityManager.unwrap(Session.class);

        String hql = "select e from Event e where e.eventScheduleDetail.location  = :pattern";

        Query query = session.createQuery(hql , Event.class);

        query.setParameter("pattern",location);

        return query.getResultList();
    }


    @Override
    public List<Ticket> getAllTicketsOfId(Long id) {

        Event event = getById(id);

        return event.getTickets();
    }

    @Override
    public List<Event> getAllEventByGreaterPrice(Long price) {

        Session session = entityManager.unwrap(Session.class);

        String hql = "SELECT DISTINCT e FROM Event e JOIN e.tickets t WHERE t.price > :price";

        Query query = session.createQuery(hql , Event.class);

        query.setParameter("price",price);

        return query.getResultList();
    }
}
