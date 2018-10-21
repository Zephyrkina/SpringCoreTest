package com.training;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

public class App {
    private Client client;
    private EventLogger defaultLogger;
    private Map<EventType, EventLogger> loggers;

    public App(Client client, EventLogger defaultLogger, Map<EventType, EventLogger> loggers) {
        this.client = client;
        this.defaultLogger = defaultLogger;
        this.loggers = loggers;
    }

    public void logEvent(Event event, EventType type) {
        EventLogger logger = loggers.get(type);
        if(logger == null) {
            logger = defaultLogger;
        }
        //String message = msg.replaceAll(client.getId(), client.getFullName());
        logger.logEvent(event);
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) context.getBean("app");
        // (EventType) context.getBean("typeEvent")

        app.logEvent((Event) context.getBean("event"), null);
        app.logEvent((Event) context.getBean("event"), EventType.ERROR);
        app.logEvent((Event) context.getBean("event"), EventType.ERROR);
        app.logEvent((Event) context.getBean("event"), EventType.INFO);
        app.logEvent((Event) context.getBean("event"), EventType.ERROR);

        context.close();


    }
}
