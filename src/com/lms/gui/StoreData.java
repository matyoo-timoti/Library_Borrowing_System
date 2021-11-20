package com.lms.gui;

import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class StoreData {
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

    public void store() {
        StringBuilder id = new StringBuilder();
        id.append(book).append("_").append(borrower).append("_").append(dateBorrowed).append("_").append(dueDate);
        File folder = new File(PATH);
        if (folder.exists() || folder.mkdirs()) {
            File new_file = new File(PATH + File.separator + id + ".txt");
            try {
                if (new_file.createNewFile()) {
                    try {
                        FileWriter file_writer = new FileWriter(PATH + File.separator + id + ".txt");
                        file_writer.write(book + "\n");
                        file_writer.write(author + "\n");
                        file_writer.write(isbn + "\n");
                        file_writer.write(borrower + "\n");
                        file_writer.write(affiliation + "\n");
                        file_writer.write(dateBorrowed + "\n");
                        file_writer.write(dueDate);
                        file_writer.close();
                        System.out.println("File created: " + new_file.getName());
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
            JOptionPane.showMessageDialog(null, "Folder is not created.", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Not created");
        }
    }
}
