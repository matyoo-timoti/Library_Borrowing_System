package com.lms.gui;

import java.io.File;

public class TraverseFolder {
    public File[] getFiles() {
        return files;
    }

    private final File[] files;

    public TraverseFolder(String folderPath) {
        File directoryPath = new File(folderPath);
        files = directoryPath.listFiles();
    }

}
