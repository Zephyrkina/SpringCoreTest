package com.training.logger;

import com.training.bean.Event;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;

@Component
public class FileEventLogger implements EventLogger {

    @Value("out4.txt")
    private String fileName;
    private File file;


    public FileEventLogger() {
    }

    public FileEventLogger(String fileName) {
        this.fileName = fileName;
    }

    @PostConstruct
    public void init() throws IOException {
        this.file = new File(fileName);
        System.out.println(fileName);
        if (!file.canWrite() && file.exists()) {
            throw new IOException("file write access denied");
        }  else if (!file.exists()) {
            file.createNewFile();
        }
    }


    @Override
    public void logEvent(Event event) {

        try {
            FileUtils.writeStringToFile(file, event.toString(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
