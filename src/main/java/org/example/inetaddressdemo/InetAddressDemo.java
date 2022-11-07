package org.example.inetaddressdemo;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressDemo {
    public static void main(String[] args) {
        try {
            InetAddress ip1 = InetAddress.getLocalHost(); // Samma som loopback
            System.out.println(ip1);
            System.out.println(ip1.getHostName());
            System.out.println(ip1.getHostAddress());

/*            InetAddress ip2 = InetAddress.getByName("192.168.68.54");
            System.out.println(ip2);*/

            InetAddress ip3 = InetAddress.getLoopbackAddress(); // hårdkodade pekar alltid ut den egna datorn
            System.out.println(ip3);

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
}