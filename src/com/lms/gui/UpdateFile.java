package com.lms.gui;

import javax.swing.*;
import java.io.*;

public class UpdateFile {
    private final File file;

    private static final String PATH = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "Library Borrowing System";

    public UpdateFile(String filePath) {
        file = new File(filePath);
    }

    public UpdateFile (File file) {
        this.file = file;
    }

    public static void main(String[] args) {
        var update = new UpdateFile(PATH + File.separator + "qwerty_Matthew_11-22-2021_12-01-2021" + ".txt");
        update.clear();
        update.writeln("newline after overwritten.");
    }

    public void write(String text) {
        try (FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.print(text);
            System.out.println("File is updated.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "An Error Occurred");
            System.out.println("ERROR: Cannot write in file.");
            e.printStackTrace();
        }
    }

    public void writeln(String text) {
        try (FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(text);
            System.out.println("File is updated.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "An Error Occurred");
            System.out.println("ERROR: Cannot write in file.");
            e.printStackTrace();
        }
    }

    public void clear() {
        try (FileWriter fw = new FileWriter(file, false);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.print("");
            System.out.println("File is updated.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "There are similar entry in the system.", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("ERROR: Cannot write in file.");
            e.printStackTrace();
        }
    }

}
