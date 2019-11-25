package com.easyway.cluster.id;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

public class MacTest {
    public static void main(String[] args) {
        try {

            InetAddress add = InetAddress.getLocalHost();
            //.getByName("10.123.96.102");
            System.out.println(add.getHostAddress()+" "+add.getHostName());
            NetworkInterface ni1 = NetworkInterface.getByInetAddress(add);
            if (ni1 != null) {
                byte[] mac1 = ni1.getHardwareAddress();
                System.out.println(new String(mac1));
                if (mac1 != null) {
                    for (int k = 0; k < mac1.length; k++) {
                        System.out.format("%02X%s", mac1[k], (k < mac1.length - 1) ? "-" : "");
                    }
                } else {
                    System.out.println("Address doesn't exist ");
                }
                System.out.println();
            } else {
                System.out.println("address is not found.");
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}
