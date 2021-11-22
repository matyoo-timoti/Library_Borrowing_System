package com.lms.gui;

import javax.swing.JOptionPane;
import java.io.*;

public class CreateFile {

    private File file;
    private final String folderPath;

    public String getFolderPath() {
        return folderPath;
    }

    public CreateFile(String path, String fileName) {
        this.folderPath = path;
        File folder = new File(folderPath);
        if (folder.exists() || folder.mkdirs()) {
            file = new File(folderPath + fileName + ".txt");
            try {
                if (file.createNewFile()) {
                    System.out.println("File created: " + file.getName());
                } else {
                    JOptionPane.showMessageDialog(null, "There are similar entry in the system.", "Error", JOptionPane.ERROR_MESSAGE);
                    System.out.println("ERROR: File Already Exists!");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "ERROR: Folder does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Not created");
        }
    }

    public void write(String text) {
        try (FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(text);
            System.out.println("File is updated.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "An Error Occurred");
            System.out.println("______________________________________________________________________________");
            System.out.println("ERROR: Cannot write in file.");
            e.printStackTrace();
            System.out.println("______________________________________________________________________________");
            System.out.println("ERROR: File Does Not Exists!");
        }
    }

}
