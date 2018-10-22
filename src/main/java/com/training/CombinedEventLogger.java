package com.training;

import java.util.Collection;

public class CombinedEventLogger implements EventLogger {
    private Collection<EventLogger> loggers;


    public CombinedEventLogger(Collection<EventLogger> loggers) {
        super();
        this.loggers = loggers;
    }

    @Override
    public void logEvent(Event event) {
        for(EventLogger eventLogger: loggers){
            eventLogger.logEvent(event);
        }

    }
}
