package de.ergodirekt.drag.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class DragProperties {
    private static Path pathToProperties;
    private static Properties properties = new Properties();

    public DragProperties() {
        Path pathToPropDir = Paths.get(System.getProperty("user.home") + "\\drag-home");
        pathToPropDir.toFile().mkdirs();
        pathToProperties = Paths.get(System.getProperty("user.home") + "\\drag-home" + "\\drag.properties");
        Path p = Paths.get("T:/Friedrich/DO NOT TOUCH THIS!");
        addToProperties("destinationFolder", p.toString());
    }

    public Properties getProperties() {
        try (BufferedReader rdr = Files.newBufferedReader(pathToProperties)) {
            properties.load(rdr);
        } catch (IOException e1) {
            // TODO Errorhandling
            e1.printStackTrace();
        }
        return properties;
    }

    public void addToProperties(String propertyName, String propertyValue) {
        try (BufferedWriter w = Files.newBufferedWriter(pathToProperties, Charset.defaultCharset())) {
            if (properties.getProperty(propertyName) == null) {
                properties.setProperty(propertyName, propertyValue);
                properties.store(w, "ONLY EDIT IF YOU KNOW WHAT YOU'RE DOING!");
            }
        } catch (IOException e1) {
            // TODO Errorhandling
            e1.printStackTrace();
        }
    }

    public String getProperty(String propertyName) {
        return properties.getProperty(propertyName);
    }
}
