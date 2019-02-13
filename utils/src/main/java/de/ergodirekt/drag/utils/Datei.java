package de.ergodirekt.drag.utils;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Datei {
    private String dateiName;

    public Datei(String dateiName) {
        this.dateiName = dateiName;

    }

    public void schreibe(String text) {
        schreibe(text, false);
    }

    public void schreibe(String txt, boolean append) {


        File file = new File(dateiName);
        File datei = null;
        datei = new File(dateiName);
        try (FileWriter outStream = new FileWriter(datei, append)){

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
}