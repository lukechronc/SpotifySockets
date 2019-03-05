package com.company;

import com.google.gson.Gson;

import java.net.*;
import java.io.*;

public class Server {
    //TODO make the server multi-client compatible
    public static void main(String[] args) {
        int portNumber = 8888; // Params for socket
        Gson gson = new Gson(); // Sets up GSON

        try {
            ServerSocket serverSocket = new ServerSocket(portNumber); // Sets up server-side socket
            Socket clientSocket = serverSocket.accept(); // Accepts connection from client
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true); // Sets up PrintWriter to write to socket
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); // BufferedReader reading from the input of the socket

            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                APIRequestParams tmp = gson.fromJson(inputLine, APIRequestParams.class); // Generates an APIRequestParams object from supplied JSON from client
                out.println(
                        gson.toJson(
                                new APIRequestResponse(
                                        tmp.getRequest(),new APIRequestHandler(tmp.getRequest(),"oof",tmp.getAccessToken())
                                        .execute()))); // Attempts to execute the request and writes the result to the socket TODO generate UserIDs
            }

        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}