package com.training.logger;

import com.training.bean.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;

@Component
public class CombinedEventLogger implements EventLogger {

    @Resource(name="combinedLoggers")
    private Collection<EventLogger> loggers;



    /*
    @Autowired
    public CombinedEventLogger(Collection<EventLogger> loggers) {
        super();
        this.loggers = loggers;
    }*/

    @Override
    public void logEvent(Event event) {
        for(EventLogger eventLogger : loggers){
            eventLogger.logEvent(event);
        }

    }
}
