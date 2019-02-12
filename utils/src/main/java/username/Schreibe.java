package username;


import java.io.*;

/**
 * Schreiben eines Textes in eine Textdatei.
 *
 */
public class Schreibe {
    public static void main(String[] arg) {
        File datei = null;
        String dateiName = "test.txt";

        String txt = System.getProperty("user.name");
        datei = new File(dateiName);
        try (FileWriter outStream = new FileWriter(datei)){

            outStream.write(txt);

        } catch (IOException e) {
            // Fehlerbehandlung
            e.printStackTrace();
        }

    }
}
