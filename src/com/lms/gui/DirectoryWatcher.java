package com.lms.gui;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;

public class DirectoryWatcher {
    private boolean isModified = false;

    public DirectoryWatcher(Path path) {
        // get path object pointing to the directory we wish to monitor
        try {
            // get watch service which will monitor the directory
            WatchService watcher = path.getFileSystem().newWatchService();

            // associate watch service with the directory to listen to the event
            // types
            path.register(watcher, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE,
                    StandardWatchEventKinds.ENTRY_MODIFY);
            System.out.println("Monitoring directory for changes...");

            // listen to events
            WatchKey watchKey = watcher.take();

            // get list of events as they occur
            List<WatchEvent<?>> events = watchKey.pollEvents();

            //iterate over events
            for (var event : events) {
                if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
                    System.out.println("Created: " + event.context().toString());
                } else if (event.kind() == StandardWatchEventKinds.ENTRY_DELETE) {
                    System.out.println("Deleted: " + event.context().toString());
                } else if (event.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
                    System.out.println("Modified: " + event.context().toString());
                }
                isModified = true;
//                watchKey.reset();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isModified() {
        return isModified;
    }
}
