package com.cn.cnEvent.dal;

import com.cn.cnEvent.entity.EventScheduleDetail;

import java.util.List;

public interface EventScheduleDetailDAL {
    String save(EventScheduleDetail eventScheduleDetail);

    List<EventScheduleDetail> getAllEventSchedule();

    EventScheduleDetail getById(Long id);
}
