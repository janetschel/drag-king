package username;

import java.io.File;

public class Files {
    public static void main(String[] args) {
        String folderPath = "T:\\Friedrich\\Projekt\\properties\\test.txt";
        File file = new File(folderPath);
        File[] files = file.listFiles();
        for (File fileName : files) {
            System.out.println(fileName);
        }
    }
}





