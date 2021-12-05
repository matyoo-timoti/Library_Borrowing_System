package com.lms.gui;

import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {
    File file;
    FileReader fileReader;
    BufferedReader bufferedReader;

    //    Constructor with String parameter for the file path
    public ReadFile(String filePath) {
        file = new File(filePath);
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "File not found!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    //    Constructor with File parameter
    public ReadFile(File file) {
        this.file = file;
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "File not found!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    //    Reads only one line.
    public String readLine() {
        String readLine = null;
        try {
            readLine = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return readLine;
    }

    //    Reads all lines in the file and returns it in a list.
    public List<String> readFile() {
        List<String> list = new ArrayList<>();
        try {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                list.add(line);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}