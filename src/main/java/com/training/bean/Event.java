package com.training.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.time.LocalDateTime;

@Component
@Scope("prototype")
public class Event {
    private int id;
    private String msg;

    @Autowired
    @Qualifier("newDate")
    private static  LocalDateTime date;

    @Autowired
    private DateFormat df;

    public Event(LocalDateTime date, DateFormat df) {
        this.date = date;
        this.df = df;
        id = (int) (Math.random()*10000);
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public static boolean isDay() {
        if(date.getHour() > 8 && date.getHour() < 17 ) {
            System.out.println(date.getHour());
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                ", date=" + df.format(date) +
                '}';
    }
}
