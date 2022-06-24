package org.example.config;

public class UuidFormatConfig {
    public static String encrypt(String value) {
        String result = null;
        String[] tokens = value.split("-");

        //xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx
        //3-2-1-4-5
        result = tokens[2] + tokens[1] + tokens[0] + tokens[3] + tokens[4];

        return result;
    }

    public static String decrypt(String value) {
        String result = null;

        result = value.substring(8, 16) + "-" + value.substring(4, 8) + "-" + value.substring(0, 4) + "-" + value.substring(16, 20) + "-" + value.substring(20);

        return result;
    }
}
