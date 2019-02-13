package de.ergodirekt.drag.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Files {

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
}
