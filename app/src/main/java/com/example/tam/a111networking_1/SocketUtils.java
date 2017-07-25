package com.example.tam.a111networking_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by tam on 7/24/2017.
 */

public class SocketUtils {
    public static BufferedReader getReader(Socket s) throws IOException {
        return (new BufferedReader(new InputStreamReader(s.getInputStream())));
    }

    public static PrintWriter getWriter(Socket s) throws IOException { // Second argument of true means autoflush.
        return (new PrintWriter(s.getOutputStream(), true)); }


}
