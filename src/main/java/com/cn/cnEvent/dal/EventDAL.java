package com.cn.cnEvent.dal;

import com.cn.cnEvent.entity.Event;
import com.cn.cnEvent.entity.Ticket;

import java.util.List;

public interface EventDAL {

	Event getById(Long id);

	List<Event> getAllEvents();

	String save(Event item);

	String delete(Long id);

	String update(Event updateEvent);

	String deleteEventScheduleByEventId(Long id);

	List<Event> getAllEventByLocation(String location);


    List<Ticket> getAllTicketsOfId(Long id);

	List<Event> getAllEventByGreaterPrice(Long price);
}
