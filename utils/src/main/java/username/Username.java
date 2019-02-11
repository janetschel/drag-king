package username;

import java.io.*;
import java.util.ArrayList;

public class Username {
    public static void main(String[] args) {


    }

    public static class Schreibe {
        public static void main(String[] arg) {
            File datei = null;
            String dateiName = "test.txt";
            String txt = System.getProperty("user.name");
            datei = new File(dateiName);
            try (FileWriter outStream = new FileWriter(datei)) {
                String[] username = new String[5];
                username[1] = " ";
                ArrayList<String> usernameListe = new ArrayList<>();
                usernameListe.add(txt);
                usernameListe.add("Friedrich");
                usernameListe.add("Habib");
                for (int i = 0; i < usernameListe.size(); i++) {
                    outStream.write(i + "." + usernameListe.get(i));
                    System.out.println(usernameListe.get(i));
                }

            } catch (IOException e1) {
                e1.printStackTrace();


            }
        }

    }
}







