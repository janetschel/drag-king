package de.ergodirekt.drag.utils;
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





