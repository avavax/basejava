package com.basejava.webapp;

import java.io.File;
import java.io.IOException;

public class MainFile {
    public static void main(String[] args) {

        try {
            File file = new File("./src");
            printFileTree(file, "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printFileTree(File file, String output) throws IOException {
        System.out.println(output + " " + file.getName());
        if (file.isDirectory()) {
            File[] list = file.listFiles();
            if (list != null) {
                for (File n : list) {
                    printFileTree(n, output + "-");
                }
            }
        }
    }
}
