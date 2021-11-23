package com.lms.gui;

import javax.swing.JOptionPane;
import java.io.File;
import java.io.IOException;

public class CreateFile {

    private File file;
    private final String folderPath;

    public String getFolderPath() {
        return folderPath;
    }

    public File getFile() {
        return file;
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
}
