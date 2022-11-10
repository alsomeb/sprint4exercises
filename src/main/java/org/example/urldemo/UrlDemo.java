package org.example.urldemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class UrlDemo {
    public UrlDemo() {
        // Mha en klass som heter URL
        try {
            URL url = new URL("https://studentportal.nackademin.se/");

            // Opens a connection to this URL and returns an InputStream for reading from that connection.
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));

            String temp;
            while ((temp = bufferedReader.readLine()) != null) {
                System.out.println(temp);
            }

            // Det är både HTML o Javascript som returns, källkoden till hemsidan

        } catch (MalformedURLException e) {
            /*
            if no protocol is specified,
            or an unknown protocol is found, or spec is null,
            or the parsed URL fails to comply with the specific
            syntax of the associated protocol.
             */
            e.printStackTrace();

            // För buffered readern
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new UrlDemo();
    }
}
