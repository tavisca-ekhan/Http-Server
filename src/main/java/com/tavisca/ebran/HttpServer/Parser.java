package com.tavisca.ebran.HttpServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private BufferedReader bufferedReader;
    Parser(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    protected String parseData() throws IOException {
        return bufferedReader.readLine();
    }

    protected String getFileNameFromData(String data) {
        Pattern pattern = Pattern.compile("GET /(.*) HTTP/1.1");
        Matcher matcher = pattern.matcher(data);
        if (matcher.find())
            return matcher.group(1);
        return "";
    }
}

