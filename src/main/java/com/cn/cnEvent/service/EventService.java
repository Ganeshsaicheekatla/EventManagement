package com.cn.cnEvent.service;

import com.cn.cnEvent.dal.EventDAL;
import com.cn.cnEvent.entity.Event;
import com.cn.cnEvent.entity.EventScheduleDetail;
import com.cn.cnEvent.entity.Ticket;
import com.cn.cnEvent.exception.ElementAlreadyExistException;
import com.cn.cnEvent.exception.InvalidInputException;
import com.cn.cnEvent.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class EventService {

	@Autowired
	EventDAL eventDAL;

	@Transactional
	public Event getEventById(Long id) {
		Event event=eventDAL.getById(id);
		if(event==null){
			throw new NotFoundException("No event found with id:  "+id);
		}
		return event;
	}

	@Transactional
	public List<Event> getAllEvents() {
		List<Event> events=eventDAL.getAllEvents();
		if(events==null){

			throw new RuntimeException("No events found.");
		}
		return events;
	}

	@Transactional
	public String saveEvent(Event newEvent) {

		List<Event> allEvents  = getAllEvents();
		for(Event event : allEvents)
		{
			if(Objects.equals(event.getId(), newEvent.getId()))
			{

				throw new ElementAlreadyExistException("This event already exist.");
			}

		}
		try {

			return eventDAL.save(newEvent);
		}
		catch (Exception e)
		{
			throw new InvalidInputException("The input entity for event is invalid.");
		}
	}

	@Transactional
	public String delete(Long id) {
		List<Event> allEvents = getAllEvents();

		boolean isEntityPresent=false;
		for(Event event : allEvents)
		{
			if (Objects.equals(event.getId(), id)) {
				isEntityPresent = true;
			}
		}
		if(!isEntityPresent)
		{
			throw new InvalidInputException("This event doesn't exist.");
		}
		try{
			return eventDAL.delete(id);
		}
		catch (Exception e){
			throw new InvalidInputException("Error in deleting event.");
		}

	}


	@Transactional
	public String update(Event updateEvent) {
		try{
			return eventDAL.update(updateEvent);
		}
		catch (Exception e){
			throw new NotFoundException("Error in deleting eventScheduleDetail from event.");
		}
	}


	@Transactional
	public String deleteEventScheduleByEventId(Long id) {

		try{
			return eventDAL.deleteEventScheduleByEventId(id);
		}
		catch (Exception e){
			throw new InvalidInputException("not found");
		}


	}

	@Transactional
	public List<Event> getAllEventByLocation(String location) {
	   List<Event> eventList = eventDAL.getAllEventByLocation(location);

	   if(ObjectUtils.isEmpty(eventList)){
		   throw new NotFoundException("No event are registered till now.");
	   }
	   return eventList;
	}

	@Transactional
	public EventScheduleDetail 	getEventScheduleByEventId(Long id) {

		Event event = eventDAL.getById(id);

		if(ObjectUtils.isEmpty(event) || ObjectUtils.isEmpty(event.getEventScheduleDetail() )){
			throw new NotFoundException("No event schedule not found for this id");
		}

		return  event.getEventScheduleDetail() ;
	}

	public List<Ticket> getAllTicketsOfEventId(Long id) {

		List<Ticket> ticketList = eventDAL.getAllTicketsOfId(id);

		if(ObjectUtils.isEmpty(ticketList)){
			throw new NotFoundException("No event schedule not found for this id");

		}

		return ticketList;
	}

	public List<Event> getAllEventsWithGreaterTicketPrice(Long price) {
		List<Event> eventList = eventDAL.getAllEventByGreaterPrice(price);

//		if(ObjectUtils.isEmpty(eventList)){
//			throw new NotFoundException("No event are registered till now.");
//		}
		return eventList;
	}
}
