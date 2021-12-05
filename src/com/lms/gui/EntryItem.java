package com.lms.gui;

import java.io.File;
import java.io.IOException;

public class EntryItem{
    private File file;
    private String book;
    private String author;
    private String borrower;
    private String affiliation;
    private String isbn;
    private String dateBorrowed;
    private String dueDate;
    private boolean isReturned;

    public EntryItem(File file) {
        var read = new ReadFile(file);
        setFile(file);
        setBook(read.readLine());
        setAuthor(read.readLine());
        setIsbn(read.readLine());
        setBorrower(read.readLine());
        setAffiliation(read.readLine());
        setDateBorrowed(read.readLine());
        setDueDate(read.readLine());
        setReturned(Boolean.parseBoolean(read.readLine()));
        try {
            read.bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBorrower() {
        return borrower;
    }

    public void setBorrower(String borrower) {
        this.borrower = borrower;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getDateBorrowed() {
        return dateBorrowed;
    }

    public void setDateBorrowed(String dateBorrowed) {
        this.dateBorrowed = dateBorrowed;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isReturned() {
        return isReturned;
    }

    public void setReturned(boolean returned) {
        isReturned = returned;
    }
}
