package de.ergodirekt.drag.utils;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class FileWatcher implements Runnable {
    private boolean running;
    private String pfad;
    private FileWatcherListener listener;

    public FileWatcher(String pfad, FileWatcherListener listener) {
        this.pfad = pfad;
        this.listener = listener;
        start();
    }

    public void start() {
        running = true;
        Thread t = new Thread(this);
        t.start();
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        Path path = Paths.get(pfad);

        while (running) {
            try (WatchService watcher = path.getFileSystem().newWatchService()) {
                path.register(watcher, StandardWatchEventKinds.ENTRY_CREATE);
                WatchKey watchKey = watcher.poll(500, TimeUnit.MILLISECONDS);
                if (watchKey != null) {
                    List<WatchEvent<?>> events = watchKey.pollEvents();
                    for (WatchEvent event : events) {
                        if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
                            listener.hasFileChanged(true);
                        }
                    }
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}