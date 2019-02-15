package de.ergodirekt.drag.utils;


import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Datei {
    private String dateiName;

    public Datei(String dateiName) {
        this.dateiName = dateiName;

    }

    public static Properties getProperties() {
        Properties properties = new Properties();
        Path pathToPropFile = Paths.get(System.getProperty("user.home"), "drag-home", "drag.properties");
        try (BufferedReader rdr = Files.newBufferedReader(pathToPropFile)) {
            properties.load(rdr);
        } catch (NoSuchFileException e) {
            Path p = Paths.get(System.getProperty("user.home"), "Ordner");
            properties.setProperty("destinationFolder", p.toString());

            properties.setProperty("austauschOrdner", Paths.get(p.toString(), System.getProperty("user.name")).toString());

            Path pathToPropDir = Paths.get(System.getProperty("user.home"), "drag-home");
            pathToPropDir.toFile().mkdirs();
            try (BufferedWriter w = Files.newBufferedWriter(pathToPropFile, Charset.defaultCharset())) {
                properties.store(w, "Neu angelegt");

            } catch (IOException e1) {
                // TODO Errorhandling
                e1.printStackTrace();
            }
        } catch (IOException e1) {
            // TODO Errorhandling
            e1.printStackTrace();
        }
        return properties;
    }

    public void schreibe(String text) {
        schreibe(text, false);
    }

    public void schreibe(String txt, boolean append) {


        File file = new File(dateiName);
        File datei = null;
        datei = new File(dateiName);
        try (FileWriter outStream = new FileWriter(datei, append)) {

            outStream.write(txt);

        } catch (IOException e) {
            // Fehlerbehandlung
            e.printStackTrace();
        }
    }

    public String lese() {
        StringBuffer inhalt = new StringBuffer();
        File datei = null;
        BufferedReader reader = null;
        // einlesen der Datei
        datei = new File(dateiName); // Erzeuge ein Datei-Objekt
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

    public static List<String> getFilePathsFromDirectory(String folderPath) {
        File folder = new File(folderPath);
        File[] listOfFiles = folder.listFiles();
        List<String> listOfFilePaths = new ArrayList<>();

        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                listOfFilePaths.add(file.getPath());
            }
        }

        return listOfFilePaths;
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
}
