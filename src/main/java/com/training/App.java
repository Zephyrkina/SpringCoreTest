package com.training;

import com.training.config.AppConfig;
import com.training.bean.Client;
import com.training.bean.Event;
import com.training.bean.EventType;
import com.training.config.LoggersConfig;
import com.training.logger.EventLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.Resource;
import java.util.Map;

public class App {

    @Autowired
    private Client client;

    @Resource(name="defaultLogger")
    private EventLogger defaultLogger;


    @Resource(name="loggerMap")
    private Map<EventType, EventLogger> loggers;

    public App() {
    }


    public App(Client client, EventLogger defaultLogger, Map<EventType, EventLogger> loggers) {
        this.client = client;
        this.defaultLogger = defaultLogger;
        this.loggers = loggers;
    }

    public void logEvent( EventType type, Event event, String msg) {
        String message = msg.replaceAll(client.getId(), client.getFullName());
        event.setMsg(message);

        EventLogger logger = loggers.get(type);
        if(logger == null) {
            logger = defaultLogger;
        }
        //String message = msg.replaceAll(client.getId(), client.getFullName());
        logger.logEvent(event);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(AppConfig.class, LoggersConfig.class);
        ctx.scan("com.training");
        ctx.refresh();

        App app = (App) ctx.getBean("app");

        Client client = ctx.getBean(Client.class);
        System.out.println("Client says: " + client.getGreeting());

        Event event = ctx.getBean(Event.class);
        app.logEvent(EventType.INFO, event, "Some event for 1");

        event = ctx.getBean(Event.class);
        app.logEvent(EventType.ERROR, event, "Some event for 2");

        event = ctx.getBean(Event.class);
        app.logEvent(null, event, "Some event for 3");

        ctx.close();



    }
}
