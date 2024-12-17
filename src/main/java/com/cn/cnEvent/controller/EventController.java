package com.cn.cnEvent.controller;

import com.cn.cnEvent.entity.Event;
import com.cn.cnEvent.entity.EventScheduleDetail;
import com.cn.cnEvent.entity.Ticket;
import com.cn.cnEvent.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {
	@Autowired
	EventService eventService;

	@GetMapping("/{id}")
	public Event getEventById(@PathVariable Long id)
	{
		return eventService.getEventById(id);
	}
	@GetMapping("/all")
	public List<Event> getAllEvents()
	{
		return eventService.getAllEvents();
	}

	@PostMapping("/save")
	public String saveEvent(@RequestBody Event event) {

		return eventService.saveEvent(event);
	}

	@DeleteMapping("/delete/{id}")
	public String deleteEvent(@PathVariable Long id)
	{
		return eventService.delete(id);
	}

	@PutMapping("/update")
	public String updateEvent(@RequestBody Event updateEvent)
	{
		return eventService.update(updateEvent);
	}


	@GetMapping("/eventScheduleDetail/{id}")
	public EventScheduleDetail getEventScheduleByEventId(@PathVariable Long id){
		return eventService.getEventScheduleByEventId(id);
	}

	@GetMapping("/location/{location}")
	public List<Event> getAllEventByLocation(@PathVariable String location){
		return  eventService.getAllEventByLocation(location);
	}

	@DeleteMapping("/delete/eventScheduleDetail/{id}")
	public String deleteEventScheduleByEventId(@PathVariable Long id){
		return eventService.deleteEventScheduleByEventId(id);
	}

	@GetMapping("/allTickets/{id}")
	public List<Ticket> getAllTicketsOfEventId(@PathVariable Long id){
		return eventService.getAllTicketsOfEventId(id);
	}

	@GetMapping("/tickets/PriceGreaterThan/{price}")
	public List<Event> getAllEventsWithGreaterTicketPrice(@PathVariable Long price){
	    return  eventService.getAllEventsWithGreaterTicketPrice(price);
	}

}
