package com.myproject.ip;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IPHostname {
	 
    public static void main(String[] args) {
 
        InetAddress ip;
        String hostname;
        try {
            ip = InetAddress.getLocalHost();
            hostname = ip.getHostName();
            System.out.println("Your current IP address : " + ip.getHostAddress());
            System.out.println("Your current canonical address : " + ip.getCanonicalHostName());
            System.out.println("Your current Hostname : " + hostname);
 
        } catch (UnknownHostException e) {
 
            e.printStackTrace();
        }
    }
}