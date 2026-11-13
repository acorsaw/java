package com.corsaw.example;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        PrintWriter pw = new PrintWriter(System.out);
        BufferedWriter bw = new BufferedWriter(pw);
        try {
            bw.write(57);
            bw.flush();
        } catch (IOException ioe) {
            System.out.println("Failed to write to BufferedWriter");
        }
    }
}
