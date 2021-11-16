package com.lms.gui;

import javax.swing.*;
import java.io.*;

public class FileHandling {
    public static void Save(String bt, String ad, long is, String bn, String af, String db, String dd) {
        StringBuilder id = new StringBuilder();
        id.append(bt.charAt(0));
        id.append(bn.charAt(0));
        id.append("-").append(db).append("-").append(dd);
        String path = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "Library Borrowing System";
        File folder = new File(path);
        if (folder.exists() || folder.mkdirs()) {
            File new_file = new File(path + File.separator + id + ".txt");
            try {
                if (new_file.createNewFile()) {
                    System.out.println("File created: " + new_file.getName());
                    try {
                        FileWriter file_writer = new FileWriter(path + File.separator + id + ".txt");
                        file_writer.write(bt + "\n");
                        file_writer.write(ad + "\n");
                        file_writer.write(is + "\n");
                        file_writer.write(bn + "\n");
                        file_writer.write(af + "\n");
                        file_writer.write(db + "\n");
                        file_writer.write(dd);
                        file_writer.close();
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, "An Error Occurred");
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("File already exists.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Not created");
        }
    }
}
