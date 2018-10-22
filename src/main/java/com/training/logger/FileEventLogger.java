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


    public void logEvent(Event event) {

        try {
            FileUtils.writeStringToFile(file, event.toString(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // String str = "";
       /* try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))){
            writer.write(event.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
*/

        //writer.close();
        //new FileOutputStream(fileName).

    }
}
