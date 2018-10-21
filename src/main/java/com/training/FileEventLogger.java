package com.training;

import org.apache.commons.io.FileUtils;

import java.io.*;

public class FileEventLogger implements EventLogger {
    private String fileName;
    private File file;

    public FileEventLogger(String fileName) {
        this.fileName = fileName;
    }

    public void init() throws IOException {
        this.file = new File(fileName);
        System.out.println(fileName);
        if (!file.canWrite()) {
            throw new IOException("file write access denied");
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
