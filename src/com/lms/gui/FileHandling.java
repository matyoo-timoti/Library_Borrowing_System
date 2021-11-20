package com.lms.gui;

import javax.swing.*;
import java.io.*;

public class FileHandling {

    private static final String PATH = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "Library Borrowing System";

    public void save(String book_title, String author_date, String ISBN, String borrower_name, String affiliation, String date_borrowed, String due_date) {
        StringBuilder id = new StringBuilder();
        id.append(book_title).append("_").append(borrower_name).append("_").append(date_borrowed).append("_").append(due_date);
        File folder = new File(PATH);
        if (folder.exists() || folder.mkdirs()) {
            File new_file = new File(PATH + File.separator + id + ".txt");
            try {
                if (new_file.createNewFile()) {
                    System.out.println("File created: " + new_file.getName());
                    try {
                        FileWriter file_writer = new FileWriter(PATH + File.separator + id + ".txt");
                        file_writer.write(book_title + "\n");
                        file_writer.write(author_date + "\n");
                        file_writer.write(ISBN + "\n");
                        file_writer.write(borrower_name + "\n");
                        file_writer.write(affiliation + "\n");
                        file_writer.write(date_borrowed + "\n");
                        file_writer.write(due_date);
                        file_writer.close();
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, "An Error Occurred");
                        e.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "There are similar entry in the system.", "Error", JOptionPane.ERROR_MESSAGE);
                    System.out.println("File Already Exists!");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Not created");
        }
    }

    public String[] retrieve(String id) {
        String[] entry = new String[7];
        try {
            FileReader file_reader = new FileReader(PATH + File.separator + id + ".txt");
            BufferedReader buff_reader = new BufferedReader(file_reader);
            for (int line = 0; line < 7; line++) {
                switch (line) {
                    case 0 -> entry[0] = buff_reader.readLine(); //Book title;
                    case 1 -> entry[1] = buff_reader.readLine(); //Author and year published
                    case 2 -> entry[2] = buff_reader.readLine(); //ISBN
                    case 3 -> entry[3] = buff_reader.readLine(); //Borrower name
                    case 4 -> entry[4] = buff_reader.readLine(); //Borrower affiliation
                    case 5 -> entry[5] = buff_reader.readLine(); //Date Borrowed
                    case 6 -> entry[6] = buff_reader.readLine(); //Due date
                    default -> buff_reader.readLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "File not found!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        return entry;
    }

    public static void list_files() throws IOException {
        File directoryPath = new File(PATH);
        File[] files_list = directoryPath.listFiles();
        System.out.println("List of files and directories in the specified directory:");
        for (File file : files_list) {
            System.out.println("File name: " + file.getName());
            System.out.println("File path: " + file.getAbsolutePath());
            System.out.println();
        }
    }

}
