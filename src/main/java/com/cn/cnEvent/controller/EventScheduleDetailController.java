package com.cn.cnEvent.controller;

import com.cn.cnEvent.entity.EventScheduleDetail;
import com.cn.cnEvent.service.EventScheduleDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventScheduleDetail")
public class EventScheduleDetailController {

    @Autowired
    EventScheduleDetailService eventScheduleDetailService;

    @GetMapping("/{id}")
    public EventScheduleDetail getEventScheduleById(@PathVariable Long id){
        return eventScheduleDetailService.getById(id);
    }

    @GetMapping("/all")
    public List<EventScheduleDetail> getAllEventSchedule(){
        return eventScheduleDetailService.getAllEventSchedule();
    }

    @PostMapping("/save")
    public  String saveEventScheduleDetails(@RequestBody EventScheduleDetail eventScheduleDetail){
        return eventScheduleDetailService.save(eventScheduleDetail);
    }
}
