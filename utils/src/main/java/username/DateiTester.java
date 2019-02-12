package username;




import username.Datei;

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
        String txt = System.getProperty("user.name");

        // eine Instanz von "Datei" erzeugen
        Datei d = new Datei("test.txt");
        d.schreibe(txt);
        String inhalt = d.lese();
        System.out.println(inhalt);



    }

}
