package com.lms.gui;

import javax.swing.*;
import java.io.*;

public class FileHandling {
    private String book;
    private String author;
    private String borrower;
    private String affiliation;
    private String isbn;
    private String dateBorrowed;
    private String dueDate;

    public void setBook(String book) {
        this.book = book;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setBorrower(String borrower) {
        this.borrower = borrower;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setDateBorrowed(String dateBorrowed) {
        this.dateBorrowed = dateBorrowed;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

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

    public static void list_files() {
        File directoryPath = new File(PATH);
        File[] files_list = directoryPath.listFiles();
        System.out.println("List of files and directories in the specified directory:");
        assert files_list != null;
        for (File file : files_list) {
            System.out.println("File name: " + file.getName());
            System.out.println("File path: " + file.getAbsolutePath());
            System.out.println();
        }
    }

}
