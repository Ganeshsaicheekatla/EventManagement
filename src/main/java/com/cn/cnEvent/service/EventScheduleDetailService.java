package com.cn.cnEvent.service;

import com.cn.cnEvent.dal.EventScheduleDetailDAL;
import com.cn.cnEvent.entity.EventScheduleDetail;
import com.cn.cnEvent.exception.ElementAlreadyExistException;
import com.cn.cnEvent.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class EventScheduleDetailService {

    @Autowired
    EventScheduleDetailDAL eventScheduleDetailDAL;


    @Transactional
    public String save(EventScheduleDetail eventScheduleDetail) {



        if(eventScheduleDetail.getId()!=null && eventScheduleDetailDAL.getById(eventScheduleDetail.getId()) != null){
            throw  new ElementAlreadyExistException("Event schedule is already exists with given id:"+ eventScheduleDetail.getId());
        }



        return eventScheduleDetailDAL.save(eventScheduleDetail);
    }

    @Transactional
    public List<EventScheduleDetail> getAllEventSchedule() {

        List<EventScheduleDetail> eventScheduleDetailList =eventScheduleDetailDAL.getAllEventSchedule();

        if( ObjectUtils.isEmpty(eventScheduleDetailList)){
            throw new NotFoundException("Not event are schedule right now.");
        }
        return eventScheduleDetailList;
    }

    @Transactional
    public EventScheduleDetail getById(Long id) {

        EventScheduleDetail eventScheduleDetail = eventScheduleDetailDAL.getById(id);

        if(ObjectUtils.isEmpty(eventScheduleDetail)){
            throw new NotFoundException("No eventSchedule to founded for the given given id:"+id);
        }

        return eventScheduleDetail;
    }
}
