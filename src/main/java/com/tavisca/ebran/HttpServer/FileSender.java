package com.tavisca.ebran.HttpServer;

import java.io.IOException;
import java.net.Socket;

public abstract class FileSender {
    abstract void sendFile(Data data, Socket client) throws IOException;
}

