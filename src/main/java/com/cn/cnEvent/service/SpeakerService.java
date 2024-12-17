package com.cn.cnEvent.service;

import com.cn.cnEvent.dal.EventDAL;
import com.cn.cnEvent.dal.SpeakerDAL;
import com.cn.cnEvent.entity.Event;
import com.cn.cnEvent.entity.Speaker;
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
public class SpeakerService {

    @Autowired
    SpeakerDAL speakerDAL;

    @Autowired
    EventDAL eventDAL;

    @Transactional
    public Speaker getById(Long id) {
        Speaker speaker = speakerDAL.getById(id);
        if (speaker == null)
              throw new NotFoundException("Speaker not found with given id"+id);

        return speaker;
    }

    @Transactional
    public List<Speaker> getAll() {
        List<Speaker> speakers= speakerDAL.getAll();
//        if(ObjectUtils.isEmpty(speakers)){
//            throw  new NotFoundException("No speaker are registrated till now.");
//        }
        return  speakers;
    }

    @Transactional
    public List<Speaker> getAllSpeakersWithEventCountAndExperience(Long eventCount, Long experience) {

        List<Speaker> speakers=  speakerDAL.getAllSpeakersWithEventCountAndExperience(eventCount,experience);

        if(ObjectUtils.isEmpty(speakers)){
            throw  new NotFoundException("No speaker are registrated till now.");
        }
         return  speakers;
    }


    @Transactional
    public String save(Speaker speaker) {

        List<Speaker> speakerList = getAll();

        if(speaker.getId()!=null) {
            for (Speaker speaker1 : speakerList) {
                if (speaker.getId().equals(speaker1.getId()))
                    throw  new ElementAlreadyExistException("Speaker already exist");
            }
        }

        try {
            return speakerDAL.save(speaker);
        }
        catch (Exception e){
            throw  new InvalidInputException("invalid");
        }

    }

    @Transactional
    public void createNewRecord(Long eventId, Long speakerId) {

        Speaker newSpeaker = getById(speakerId);

        for(Event event:newSpeaker.getEvents()){

            if(Objects.equals(event.getId(), eventId)){
                throw new  ElementAlreadyExistException("This pair already exits in database");
            }
        }

        speakerDAL.saveNewRecord(newSpeaker , eventId);

    }
}
