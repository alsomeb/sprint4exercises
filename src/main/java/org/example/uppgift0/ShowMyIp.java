package org.example.uppgift0;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ShowMyIp {
    public ShowMyIp() throws UnknownHostException {

        /*
        Returns the address of the local host.
        This is achieved by retrieving the name of the host from the system,
        then resolving that name into an InetAddress.
         */
        InetAddress ip = InetAddress.getLocalHost();
        System.out.println("IP: " + ip.getHostAddress());
        System.out.println("Host Name: " + ip.getHostName());

        System.out.println();

        /*
        Returns the loopback address.
        The InetAddress returned will represent the IPv4 loopback address,
        127.0.0.1, or the IPv6 loopback address
         */
        InetAddress ip2 = InetAddress.getLoopbackAddress();
        System.out.println("IP2: " + ip2.getHostAddress());
        System.out.println("Host Name2: " + ip2.getHostName());

    }

    public static void main(String[] args) throws UnknownHostException {
        new ShowMyIp();
    }
}
