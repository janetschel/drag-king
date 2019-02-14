package de.ergodirekt.drag.utils;

import de.ergodirekt.drag.gui.ReceiveFileGUI;

import java.io.IOException;
import java.nio.file.*;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class FileWatcher implements Runnable {
    private boolean running;
    private String pfad;
    private static String FILE_PATH = "T:/Friedrich/Projekt/properties"; //TODO Pfad auf Laufwerk

    public FileWatcher(String pfad) {
        this.pfad = pfad;
    }

    public void start() {
        running = true;
        Thread t = new Thread(this);
        t.start();
    }

    public void stop() {
        running = false;
    }

    public static void main(String[] args) throws IOException {
        FileWatcher f = new FileWatcher(FILE_PATH);
        f.start();
        System.out.println("Beenden mit einem Tstendruck");
        System.in.read();
        f.stop();

    }

    @Override
    public void run() {
        System.out.println("Checking for new Files  :)  ");
        Path path = Paths.get(pfad);

        while (running) {
            try (WatchService watcher = path.getFileSystem().newWatchService()) {
                path.register(watcher, StandardWatchEventKinds.ENTRY_CREATE);
                WatchKey watchKey = watcher.poll(500, TimeUnit.MILLISECONDS);
                if (watchKey != null) {
                    List<WatchEvent<?>> events = watchKey.pollEvents();

                    for (WatchEvent event : events) {
                        if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
                            System.out.println("Something new : " + event.context().toString());
                            new ReceiveFileGUI(FILE_PATH);

                        }
                    }

                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}