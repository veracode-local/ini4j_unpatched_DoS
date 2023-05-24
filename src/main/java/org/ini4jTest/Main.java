package org.ini4jTest;

import org.ini4j.Ini;

import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Ini ini = new Ini();
        System.out.println("Loading payload.ini");
        ini.load(new FileReader(System.getProperty("user.dir") + "/src/main/java/org/ini4jTest/payload.ini")
        );
        System.out.println("Attempting to access the weight property in 'sectionOne'");
        try {
            ini.get("sectionOne").fetch("weight");
            System.out.println("Successfully accessed the weight property in 'sectionOne'");
        } catch (StackOverflowError e) {
            System.out.println("Stack overflow exception!");
            e.printStackTrace();
        }
    }
}