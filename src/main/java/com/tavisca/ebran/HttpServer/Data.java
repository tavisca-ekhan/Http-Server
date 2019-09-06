package com.tavisca.ebran.HttpServer;

public class Data {
    public final String filename;
    public final int statusCode;
    public final String status;

    Data(String filename, int statusCode, String status) {
        this.filename = filename;
        this.statusCode = statusCode;
        this.status = status;
    }
}
