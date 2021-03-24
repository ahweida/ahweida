package com.weida.epmain.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * @ClassName InetAddressUtils
 * @Description
 * @Author liaoze
 * @Date 2019/8/28 下午3:11
 */
public class InetAddressUtils {

    private static Logger log = LoggerFactory.getLogger("InetAddressUtils");

    /**
     * 获取当前服务器ip地址
     * @return
     */
    public static String getIpAdd() {
        String ip = "";
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                String name = intf.getName();
                if (!name.contains("docker") && !name.contains("lo")) {
                    for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                        //获得IP
                        InetAddress inetAddress = enumIpAddr.nextElement();
                        if (!inetAddress.isLoopbackAddress()) {
                            String ipaddress = inetAddress.getHostAddress();
                            if (!ipaddress.contains("::") && !ipaddress.contains("0:0:") && !ipaddress.contains("fe80")) {
                                if (!"127.0.0.1".equals(ip)) {
                                    ip = ipaddress;
                                }
                            }
                        }
                    }
                }
            }
        } catch (SocketException e) {
            log.error("获取当前服务器地址发生异常", e);
            e.printStackTrace();
        } catch (Exception e) {
            log.error("获取当前服务器地址发生异常", e);
            e.printStackTrace();
        }
        return ip;
    }

    /**
     * 获取linux服务器ip地址
     * @return
     */
    public static String getLinuxLocalIp() {
        String ip = "";
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                String name = intf.getName();
                if (!name.contains("docker") && !name.contains("lo")) {
                    for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                        InetAddress inetAddress = enumIpAddr.nextElement();
                        if (!inetAddress.isLoopbackAddress()) {
                            String ipaddress = inetAddress.getHostAddress();
                            if (!ipaddress.contains("::") && !ipaddress.contains("0:0:") && !ipaddress.contains("fe80")) {
                                ip = ipaddress;
                            }
                        }
                    }
                }
            }
        } catch (SocketException ex) {
            System.out.println("获取ip地址异常");
            ip = "127.0.0.1";
            ex.printStackTrace();
        } catch (Exception e) {
            log.error("获取当前服务器地址发生异常", e);
            e.printStackTrace();
        }
        return ip;
    }
}
