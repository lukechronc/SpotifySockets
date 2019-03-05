package com.company;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.Socket;


public class ServerTests {
    private Socket socket = new Socket();
    @BeforeMethod
    public void setUp() {
        String hostName = "localhost"; // Args for socket
        int portNumber = 8888;


        try {
            socket = new Socket(hostName, portNumber);
        } catch (IOException e){e.printStackTrace();}
    }

    @Test
    public void testClientConnection(){
        Assert.assertTrue(socket.isConnected());


    }
}
