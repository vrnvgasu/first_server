package ru.edu;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        MyFirstServer server = new MyFirstServer(9000);
        server.start();
    }
}
