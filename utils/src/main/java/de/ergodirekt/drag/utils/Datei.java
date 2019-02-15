package de.ergodirekt.drag.utils;

import javax.swing.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class Datei {
    public void schreibe(String dateiPfad, String text) {
        schreibe(dateiPfad, text, false);
    }

    public void schreibe(String dateiPfad, String txt, boolean append) {
        File datei;
        datei = new File(dateiPfad);
        try (FileWriter outStream = new FileWriter(datei, append)) {
            outStream.write(txt);
        } catch (IOException e) {
            // Fehlerbehandlung
            e.printStackTrace();
        }
    }

    public String lese(String dateiPfad) {
        StringBuffer inhalt = new StringBuffer();
        File datei;
        BufferedReader reader;
        // einlesen der Datei
        datei = new File(dateiPfad); // Erzeuge ein Datei-Objekt
        try (FileReader inStream = new FileReader(datei)) {
            reader = new BufferedReader(inStream);
            String zeile = " ";
            while ((zeile = reader.readLine()) != null) // bis alles drin ist
            {
                if (inhalt.length() > 0) {
                    inhalt.append("\n");
                }
                inhalt.append(zeile);
            }
        }
        // Etwas schief gegangen?
        catch (IOException e) {
            e.printStackTrace();
        }
        return inhalt.toString();
    }

    public static String[] getFilePathsFromDirectory(String folderPath) {
        File folder = new File(folderPath);
        File[] listOfFiles = folder.listFiles();
        List<String> listOfFilePaths = new ArrayList<>();

        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                listOfFilePaths.add(file.getPath());
            }
        }

        return listOfFilePaths.toArray(new String[0]);
    }

    public static String getFileNameFromPath(String filePath) {
        String[] filePathParts = filePath.replace("\\", "/").split("/");
        return filePathParts[filePathParts.length-1];
    }

    public static void deleteAllFilesFromDirectory(String folderPath) {
        File folder = new File(folderPath);
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                file.delete();
            }
        }
    }

    public static void sendFiles(JList jList, String[] filesToSend, String destinationPath) throws DragException {
        for (int i = 0; i < jList.getModel().getSize(); i++) {
            for (String file : filesToSend) {
                String[] splitPath = file.replace("\\", "/").split("/");
                String fileName = splitPath[splitPath.length - 1];
                Copy.copy(file, destinationPath + "/" + jList.getModel().getElementAt(i) + "/" + fileName);
            }
        }
    }
}
