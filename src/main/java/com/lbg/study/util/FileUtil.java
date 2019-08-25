package com.lbg.study.util;

import java.io.*;

public class FileUtil {

    public static void closeStream(Closeable stream) {
        if (stream != null) {
            try {
                stream.close();
            } catch (Exception exp) {
                exp.printStackTrace();
            } finally {
                stream = null;
            }
        }
    }

    public static String getDataFromFile(String filePath) {
        FileInputStream fileInputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        StringBuffer laststr = new StringBuffer();
        String tempString = null;
        try {
            fileInputStream = new FileInputStream(filePath);
            inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            reader = new BufferedReader(inputStreamReader);
            while ((tempString = reader.readLine()) != null) {
                laststr.append(tempString);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeStream(reader);
            closeStream(inputStreamReader);
            closeStream(fileInputStream);
        }
        return laststr.toString();
    }

    public static void saveDataToFile(String targetFilePath, String data) {
        File file = new File(targetFilePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file, false);
            fileWriter.write(data);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeStream(fileWriter);
        }
    }

}
