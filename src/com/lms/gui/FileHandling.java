package com.lms.gui;

import javax.swing.*;
import java.io.*;

public class FileHandling {
    private static final String PATH = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "Library Borrowing System";


    public String[] retrieve(String id) {
        String[] attribute = new String[7];
        try {
            FileReader file_reader = new FileReader(PATH + File.separator + id + ".txt");
            BufferedReader buff_reader = new BufferedReader(file_reader);
            for (int line = 0; line < 7; line++) {
                switch (line) {
                    case 0 -> attribute[0] = buff_reader.readLine(); //Book title;
                    case 1 -> attribute[1] = buff_reader.readLine(); //Author and year published
                    case 2 -> attribute[2] = buff_reader.readLine(); //ISBN
                    case 3 -> attribute[3] = buff_reader.readLine(); //Borrower name
                    case 4 -> attribute[4] = buff_reader.readLine(); //Borrower affiliation
                    case 5 -> attribute[5] = buff_reader.readLine(); //Date Borrowed
                    case 6 -> attribute[6] = buff_reader.readLine(); //Due date
                    default -> buff_reader.readLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "File not found!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        return attribute;
    }

}
