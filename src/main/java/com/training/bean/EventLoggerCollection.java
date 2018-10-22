package com.training.bean;

import com.training.logger.EventLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.List;

public class EventLoggerCollection {
    @Autowired(required = false)
    @Qualifier("CollectionsBean")
    private List<EventLogger> loggers(){
        return new ArrayList<>();
    }
}
