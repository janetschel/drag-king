package de.ergodirekt.drag.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class Copy {

    public static void main(String[] args) throws DragException {
        File srcFolder = new File("C:\\Users\\Administrator\\Desktop\\hello");
        File destFolder = new File("C:\\Users\\Administrator\\Documents\\newHello");

        if (!srcFolder.exists()) {
                throw new DragException("Directory does not exist.");

        } else {
            try {
                copyFolder(srcFolder, destFolder);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Good bye2 ");
                System.exit(1);
            }
        }
        System.out.println("Cool 1 ");
    }

    public static void copyFolder(File src, File dest) throws IOException {
        if (src.isDirectory()) {
            if (!dest.exists()) {
                dest.mkdir();
                System.out.println(" copied from " + src + "  to " + dest);
            }
            String files[] = src.list();
            for (String file : files) {
                File srcFile = new File(src, file);
                File destFile = new File(dest, file);
                copyFolder(srcFile, destFile);
            }
        }else {

            try (InputStream in = new FileInputStream(src);
                 FileOutputStream out = new FileOutputStream(dest)) {
                byte[] buffer = new byte[1024];
                int length;
                while ((length = in.read(buffer)) > 0) {
                    out.write(buffer, 0, length);
                }
                System.out.println("copied from " + src + " to " + dest);
            }
        }

    }
}






















