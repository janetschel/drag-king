package de.ergodirekt.drag.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class Copy {
    public static void copy(String srcPath, String destPath) throws DragException {
        File srcFolderFile = new File(srcPath);
        File destFolderFile = new File(destPath);

        if (!srcFolderFile.exists()) {
                throw new DragException("Directory does not exist.");
        } else {
            try {
                copyFolder(srcFolderFile, destFolderFile);
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }

    private static void copyFolder(File src, File dest) throws IOException {
        if (src.isDirectory()) {
            if (!dest.exists()) {
                dest.mkdir();
            }
            String[] files = src.list();
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
            }
        }

    }
}









