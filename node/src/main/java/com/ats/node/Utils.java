package com.ats.node;

public class Utils {

    public static int hash(String fileName) {
        char chars[] = new char[fileName.length()];
        fileName.getChars(0, fileName.length() - 1, chars, 0);
        int hash = 0;

        for (int i = 0; i < chars.length; i++) {
            hash += chars[i];
        }

        return hash;
    }

}