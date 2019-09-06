package com.tavisca.ebran.HttpServer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ServerTest {
    @Test
    void checkingForCreatingTheDataPOJO() {
        Data data = new Data("file.txt", 200, "OK");
        assertEquals("file.txt", data.filename);
        assertEquals(200, data.statusCode);
        assertEquals("OK", data.status);
    }
}
