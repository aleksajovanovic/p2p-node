package com.ats.node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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

        try (InputStream in = getResourceAsStream(this.directory);
                BufferedReader br = new BufferedReader(new InputStreamReader(in))) {

            String resource;

            while ((resource = br.readLine()) != null) {
                filenames.add(resource);
            }
        } catch (IOException e) {
            System.out.println("Error obtaining resources");
            System.out.println(e.getMessage());
        }
    }

    private ClassLoader getContextClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    private InputStream getResourceAsStream(String resource) {
        final InputStream in = getContextClassLoader().getResourceAsStream(resource);

        return in == null ? getClass().getResourceAsStream(resource) : in;
    }

}