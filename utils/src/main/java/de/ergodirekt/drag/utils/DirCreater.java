package de.ergodirekt.drag.utils;

import java.io.File;

public class DirCreater {
    public static void createDir(String path) {
        String newpath = path;
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
            DirCreater.createDir("C:\\Users\\Administrator\\Documents\\Ali");
    }
}
