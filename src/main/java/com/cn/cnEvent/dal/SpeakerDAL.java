package com.cn.cnEvent.dal;

import com.cn.cnEvent.entity.Speaker;

import java.util.List;

public interface SpeakerDAL {
    Speaker getById(Long id);

    List<Speaker> getAll();

    List<Speaker> getAllSpeakersWithEventCountAndExperience(Long eventCount, Long experience);

    String save(Speaker speaker);


    void saveNewRecord(Speaker newSpeaker, Long eventId);
}
