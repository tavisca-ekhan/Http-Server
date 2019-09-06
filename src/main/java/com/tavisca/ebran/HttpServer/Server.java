package com.tavisca.ebran.HttpServer;

import java.io.*;
import java.net.Socket;

public class Server {
    private final String FILE_NOT_FOUND = "not_found.html";
    private Socket client;
    private BufferedReader bufferedReader = null;
    private Parser parser;

    public Server(Socket client) {
        this.client = client;
    }

    protected void handleClient() throws IOException {
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            parser = new Parser(bufferedReader);
            String data = parser.parseData();
            String filename = parser.getFileNameFromData(data);
            if (filename.contains("jpg"))
                new ImageSender().sendFile(new Data(filename, 200, "OK"), this.client);
            else if (filename.contains("mp4"))
                new VideoSender().sendFile(new Data("sample.mp4", 200, "OK"), this.client);
            else
                new WebPageSender().sendFile(new Data(filename, 200, "OK"), this.client);
        } catch (FileNotFoundException e) {
            String filename = FILE_NOT_FOUND;
            if (filename.equals("jpg"))
                new ImageSender().sendFile(new Data("404.jpg", 200, "OK"), this.client);
            else
                new WebPageSender().sendFile(new Data(filename, 404, "NOT_FOUND"), this.client);
        } finally {
            client.close();
            bufferedReader.close();
        }
    }
}

