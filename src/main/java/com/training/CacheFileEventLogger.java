package com.training;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CacheFileEventLogger extends FileEventLogger{
    private int cacheSize;
    private List<Event> cache;
/*
    public void init1() {
        cache = new ArrayList<>();
    }*/

    public CacheFileEventLogger(String fileName, int cacheSize) {
        super(fileName);
        this.cacheSize = cacheSize;
        this.cache = new ArrayList<>(cacheSize);
    }

    public void logEvent(Event event) {
        cache.add(event);

        if(cache.size() == cacheSize){
            writeEventsFromCache();
            cache.clear();
        }
    }

    private void writeEventsFromCache() {
        for (Event e : cache) {
            super.logEvent(e);
        }
    }

    public void destroy() {
        if(! cache.isEmpty()){
            writeEventsFromCache();
        }
    }
}
