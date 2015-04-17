/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testws.log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author FARHAD
 */
public class Log {
 private static File logFile = new File("log)");
 
    public Log() {
        if (!logFile.exists()) {
            try {
                logFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
 
    public static void write(String msg) {
        writeInFile(createString(msg));
    }
 
    private static String createString(String msg) {
        StringBuilder sb = new StringBuilder(100);
        sb.append(MyDate.currentDate()).append("\t| ").append(msg);
        return sb.append('\n').toString();
    }
 
    private static void writeInFile(String msg) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(logFile,
                true))) {
            out.write(msg);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    static class MyDate {
        public static String currentDate() {
            DateFormat formatter = new SimpleDateFormat("HH:mm.ss");
            Date date = new Date();
            return formatter.format(date);
        }
    }
}