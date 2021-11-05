package com.example.demo.util;

import org.junit.Test;

import java.net.Socket;

public class ClientSocketTest {
    @Test
    public void socket() throws Exception{
        Socket socket = new Socket("127.0.0.1",8089);

    }
}
