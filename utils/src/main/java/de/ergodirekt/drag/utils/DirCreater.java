package de.ergodirekt.drag.utils;

import java.io.File;

public class DirCreater {

    private static String path ="T:\\Friedrich\\Projekt\\properties";

    public DirCreater(String path) {
        String newname = System.getProperty("user.name");

        String newpath = path + "\\" + newname;
        File dir = new File(newpath);
        if (!dir.exists()) {
            System.out.println("Creating dir ..");
            boolean result = false;
            try {
                dir.mkdir();
                result = true;
            } catch (SecurityException e) {
                e.printStackTrace();
            }
            if (result) {
                System.out.println("Dir created ");
            }
        }
    }
    public static void main(String[] args){
            DirCreater d = new DirCreater(path);
    }
}
