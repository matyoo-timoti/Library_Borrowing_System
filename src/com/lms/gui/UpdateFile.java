package com.lms.gui;

import javax.swing.JOptionPane;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class UpdateFile {
    private final File file;

    public UpdateFile(String filePath) {
        file = new File(filePath);
    }

    public UpdateFile (File file) {
        this.file = file;
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
