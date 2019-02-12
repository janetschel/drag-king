package username;


import java.util.ArrayList;
import java.util.List;

/**
 * Invariante: Ändere diese Klasse nicht!
 *
 * 1. Mach IntelliJ glücklich!
 *
 * 2. Sorge dafür das der Fehler nicht ausgegeben wird.
 *
 * 3. Optional: die Zusatzaufgabe
 *
 * @author fk
 *
 */
public class DateiTester {


    public static void main(String[] args) throws Exception {
        //String txt = System.getProperty("user.name");
        Datei d = new Datei("T:\\Friedrich\\Projekt\\properties\\test.txt");
        if (d.lese().contains( System.getProperty("user.name"))){
            System.out.println("Welcome " +  System.getProperty("user.name"));
        }
        else {
            d.schreibe(System.getProperty("user.name")+"\n",true);
        }

    }
}





