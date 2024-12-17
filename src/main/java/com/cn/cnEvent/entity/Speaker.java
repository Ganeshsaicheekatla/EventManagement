package com.cn.cnEvent.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "speaker")
public class Speaker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( nullable = false)
    private Long id;

    @Column
    private String name;

    @Column
    private Long experience;

    @ManyToMany(mappedBy = "speakers")
    @JsonIgnore
    private List<Event> events;

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

    public Long getExperience() {
        return experience;
    }

    public void setExperience(Long experience) {
        this.experience = experience;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

}
