package com.mumarual.messagingapp.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    public static InputStream getInputStream(File file) {
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<String> File2List(InputStream stream) {
        BufferedReader bufferedReader = null;
        List<String> lines = new ArrayList<>();
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(stream));
            boolean eof = false;
            while (!eof) {
                String line = bufferedReader.readLine();
                if (line != null)
                    lines.add(line.trim());
                else
                    eof = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null)
                    bufferedReader.close();
                if (stream != null)
                    stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return lines;
    }
}
