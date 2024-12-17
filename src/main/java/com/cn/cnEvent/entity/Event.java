package com.cn.cnEvent.entity;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "event")
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "description", nullable = false)
	private String description;

//	one to one relationship between event and eventSchedule
	@OneToOne( cascade = CascadeType.ALL)
	private EventScheduleDetail eventScheduleDetail;

//	one to Many relationship between event and ticket
	@OneToMany(mappedBy = "event" ,cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Ticket> tickets;

//	many to many relationship between event and speaker
	@ManyToMany(cascade = {CascadeType.DETACH,CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.MERGE})
	@JoinTable(name = "event_speaker" , joinColumns = @JoinColumn(name="event_id") ,
				inverseJoinColumns = @JoinColumn(name = "speaker_id"))

	private List<Speaker> speakers;

	public List<Speaker> getSpeakers() {
		return speakers;
	}

	public void setSpeakers(List<Speaker> speakers) {
		this.speakers = speakers;
	}

	public EventScheduleDetail getEventScheduleDetail() {
		return eventScheduleDetail;
	}

	public void setEventScheduleDetail(EventScheduleDetail eventScheduleDetail) {
		this.eventScheduleDetail = eventScheduleDetail;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}
}
