package com.ats.node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FileManager {
    private ArrayList<String> filenames;
    private String directory;

    public FileManager(String directory) {
        this.filenames = new ArrayList<>();
        this.directory = directory;
        getResourceFiles();
    }

    public ArrayList<String> getFilenames() {
        return this.filenames;
    }

    private void getResourceFiles() {

        System.out.println("getResourceFiles()");

        try (InputStream in = getResourceAsStream(this.directory);
                BufferedReader br = new BufferedReader(new InputStreamReader(in))) {

            String resource;
            System.out.println("getResourceFiles() try...");

            while ((resource = br.readLine()) != null) {
                System.out.println(resource);
                this.filenames.add(resource);
            }

        } catch (IOException e) {
            System.out.println("Error obtaining resources");
            System.out.println(e.getMessage());
        }
    }

    public boolean isFileExistent(String filename) {

        for (String file : this.filenames) {
            if (file.equals(filename)) {
                return true;
            }
        }
        return false;
    }

    private ClassLoader getContextClassLoader() {
        System.out.println("getContextClassLoader()");
        return Thread.currentThread().getContextClassLoader();
    }

    private InputStream getResourceAsStream(String resource) {
        System.out.println("getResourceAsStream(): Resource: " + resource);
        final InputStream in = getContextClassLoader().getResourceAsStream(resource);

        if (in == null) {
            System.out.println("in is null");

        }
        return in == null ? getClass().getResourceAsStream(resource) : in;
    }

}