package com.zss.commons.support.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class NetUtils {

    private final static String localServerIp = getSiteLocalIp();
    public static String getLocalServerIp() {
        return localServerIp;
    }


    private static String getSiteLocalIp() {
        Enumeration<NetworkInterface> netList = null;
        try {
            netList = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            e.printStackTrace();
            return "172.0.0.1";
        }

        List<NetworkInterface> downIfList = new ArrayList<NetworkInterface>();
        while (netList.hasMoreElements()) {
            NetworkInterface netif = netList.nextElement();
            try {
                if (netif.isLoopback() | netif.isPointToPoint()
                        | netif.isVirtual()) {
                    continue;
                }

                if (netif.isUp()) {
                    String ip = findSiteLocalAddress(netif.getInetAddresses());
                    if (ip != null) {
                        return ip;
                    }
                } else {
                    downIfList.add(netif);
                }
            } catch (SocketException e) {
                e.printStackTrace();
                continue;
            }
        }

        for (NetworkInterface downIf : downIfList) {
            String ip = findSiteLocalAddress(downIf.getInetAddresses());
            if (ip != null) {
                return ip;
            }
        }
        return "127.0.0.1";
    }

    private static String findSiteLocalAddress(Enumeration<InetAddress> addrList) {
        while (addrList.hasMoreElements()) {
            String ip = addrList.nextElement().getHostAddress();
            if (ip.startsWith("10.")) {
                return ip;
            }
            if (ip.startsWith("172.")) {
                return ip;
            }
            if (ip.startsWith("192.")) {
                return ip;
            }
        }
        return null;
    }


}
