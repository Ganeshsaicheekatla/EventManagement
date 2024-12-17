package com.cn.cnEvent.controller;



import com.cn.cnEvent.entity.Speaker;
import com.cn.cnEvent.service.SpeakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/speaker")
public class SpeakerController {

    @Autowired
    SpeakerService speakerService;

    @GetMapping("/{id}")
    public Speaker getSpeakerById(@PathVariable Long id){
        return speakerService.getById(id);
    }

    @GetMapping("/all")
    public List<Speaker> getAllSpeakers(){
        return  speakerService.getAll();
    }

    @GetMapping("/eventCount/{eventCount}/experience/{experience}")
    public List<Speaker> getAllSpeakersWithEventCountAndExperience(@PathVariable Long eventCount , @PathVariable Long experience){
        return  speakerService.getAllSpeakersWithEventCountAndExperience(eventCount,experience);
    }

    @PostMapping("/save")
    public String saveSpeaker(@RequestBody Speaker speaker){
          return speakerService.save(speaker);
    }

    @PostMapping("/id/{speakerId}/eventId/{eventId}")
    public void createRecord(@PathVariable("eventId") Long eventId, @PathVariable("speakerId") Long speakerId){
         speakerService.createNewRecord(eventId , speakerId);
    }
}
