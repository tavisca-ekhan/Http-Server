package com.tavisca.ebran.HttpServer;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

public class ImageSender extends FileSender {
    @Override
    void sendFile(Data data, Socket client) throws IOException {
            File file = new File(data.filename);
            FileInputStream fileInputStream = new FileInputStream(file);
            int fileLength = (int)file.length();
            byte[] fileInBytes = new byte[fileLength];
            fileInputStream.read(fileInBytes);
            DataOutputStream dataOutputStream = new DataOutputStream(client.getOutputStream());
            dataOutputStream.writeBytes("HTTP/1.1 " + data.statusCode + " " + data.status + "\r\n");
            dataOutputStream.writeBytes("Content-Type: image/jpeg\r\n");
            dataOutputStream.writeBytes("Content-Length: " + fileLength + "\r\n");
            dataOutputStream.writeBytes("\r\n");
            dataOutputStream.write(fileInBytes, 0, fileLength);
            dataOutputStream.flush();
            dataOutputStream.close();
            client.close();
    }
}
