package com.company;

// A Java program for a Client

import com.google.gson.Gson;

import java.net.*;
import java.io.*;
import java.util.regex.Pattern;

public class Client {


    public static void main(String args[]) {
        String hostName = "localhost"; // Args for socket
        int portNumber = 8888;

        Gson gson = new Gson(); // Sets up GSON

        try {
            Socket socket = new Socket(hostName, portNumber); // Sets up socket
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true); // Creates a PrintWriter for writing to the socket
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // BufferedReader for input from the socket

            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in)); // BufferedReader to read from console TODO create GUI

            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                String[] parts = userInput.split(Pattern.quote(".")); // Split on period.
                out.println(new APIRequestParams(parts[0],parts[1]).toJson()); // Generates JSON from params from console
                APIRequestResponse tmp = gson.fromJson(in.readLine(), APIRequestResponse.class); // Creates an APIRequestResponse object from JSON received from server
                System.out.println("Response: request: " + tmp.getRequest() + ", body: " + tmp.getBody()); // Prints out the result

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


