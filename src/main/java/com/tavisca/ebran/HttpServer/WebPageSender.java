package com.tavisca.ebran.HttpServer;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

public class WebPageSender extends FileSender{
    @Override
    protected void sendFile(Data data, Socket client) throws IOException {
        File file = new File(data.filename);
        int fileLength = (int) file.length();
        byte[] fileInBytes;
        FileInputStream fileInputStream;
        fileInputStream = new FileInputStream(data.filename);
        fileInBytes = new byte[fileLength];
        fileInputStream.read(fileInBytes);
        DataOutputStream dataOutputStream = new DataOutputStream(client.getOutputStream());
        dataOutputStream.writeBytes("HTTP/1.1 " + data.statusCode + " " + data.status + "\r\n");
        dataOutputStream.writeBytes("Content-Type: text/html\r\n");
        dataOutputStream.writeBytes("Content-Length: " + fileLength + "\r\n");
        dataOutputStream.writeBytes("\r\n");
        dataOutputStream.write(fileInBytes, 0, fileLength);
        dataOutputStream.flush();
        dataOutputStream.close();
        client.close();
    }
}
