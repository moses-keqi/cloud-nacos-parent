package com.moses.cloud.commons.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.regex.Pattern;

/**
 * @Author HanKeQi
 * @Date 2021/2/3 下午2:14
 * @Version 1.0
 **/
@Slf4j
public class IpUtils {

    private static final String ANYHOST_VALUE = "0.0.0.0";
    private static final String LOCALHOST_IPV4_VALUE = "127.0.0.1";
    private static final String LOCALHOST_IPV6_VALUE = "0:0:0:0:0:0:0:1";
    private static final Pattern IP_PATTERN = Pattern.compile("\\d{1,3}(\\.\\d{1,3}){3,5}$");
    private static final String UNKNOWN = "unknown";
    private static final String IP_UTILS_SPLIT = ",";

    private static volatile InetAddress LOCAL_ADDRESS = null;

    // ---------------------- valid ----------------------

    public static InetAddress toValidAddress(InetAddress address) {
        if (address instanceof Inet6Address) {
            Inet6Address v6Address = (Inet6Address) address;
            if (isPreferIPV6Address()) {
                return normalizeV6Address(v6Address);
            }
        }
        if (isValidV4Address(address)) {
            return address;
        }
        return null;
    }

    public static boolean isPreferIPV6Address() {
        return Boolean.getBoolean("java.net.preferIPv6Addresses");
    }

    /**
     * valid Inet4Address
     *
     * @param address
     * @return
     */
    private static boolean isValidV4Address(InetAddress address) {
        if (address == null || address.isLoopbackAddress()) {
            return false;
        }
        String name = address.getHostAddress();
        boolean result = (name != null
                && IP_PATTERN.matcher(name).matches()
                && !ANYHOST_VALUE.equals(name)
                && !LOCALHOST_IPV4_VALUE.equals(name));
        return result;
    }


    /**
     * normalize the ipv6 Address, convert scope name to scope id.
     * e.g.
     * convert
     * fe80:0:0:0:894:aeec:f37d:23e1%en0
     * to
     * fe80:0:0:0:894:aeec:f37d:23e1%5
     * <p>
     * The %5 after ipv6 address is called scope id.
     * see java doc of {@link Inet6Address} for more details.
     *
     * @param address the input address
     * @return the normalized address, with scope id converted to int
     */
    public static InetAddress normalizeV6Address(Inet6Address address) {
        String addr = address.getHostAddress();
        int i = addr.lastIndexOf('%');
        if (i > 0) {
            try {
                return InetAddress.getByName(addr.substring(0, i) + '%' + address.getScopeId());
            } catch (UnknownHostException e) {
                // ignore
                log.debug("Unknown IPV6 address: ", e);
            }
        }
        return address;
    }

    // ---------------------- find ip ----------------------


    public static InetAddress getLocalAddress0() {
        InetAddress localAddress = null;
        try {
            localAddress = InetAddress.getLocalHost();
            InetAddress addressItem = toValidAddress(localAddress);
            if (addressItem != null) {
                return addressItem;
            }
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
        }

        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            if (null == interfaces) {
                return localAddress;
            }
            while (interfaces.hasMoreElements()) {
                try {
                    NetworkInterface network = interfaces.nextElement();
                    if (network.isLoopback() || network.isVirtual() || !network.isUp()) {
                        continue;
                    }
                    Enumeration<InetAddress> addresses = network.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        try {
                            InetAddress addressItem = toValidAddress(addresses.nextElement());
                            if (addressItem != null) {
                                try {
                                    if(addressItem.isReachable(100)){
                                        return addressItem;
                                    }
                                } catch (IOException e) {
                                    // ignore
                                }
                            }
                        } catch (Throwable e) {
                            log.error(e.getMessage(), e);
                        }
                    }
                } catch (Throwable e) {
                    log.error(e.getMessage(), e);
                }
            }
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
        }
        return localAddress;
    }


    // ---------------------- tool ----------------------

    /**
     * Find first valid IP from local network card
     *
     * @return first valid local IP
     */
    public static InetAddress getLocalAddress() {
        if (LOCAL_ADDRESS != null) {
            return LOCAL_ADDRESS;
        }
        InetAddress localAddress = getLocalAddress0();
        LOCAL_ADDRESS = localAddress;
        return localAddress;
    }

    /**
     * get ip address
     *
     * @return String
     */
    public static String getIp(){
        return getLocalAddress().getHostAddress();
    }

    /**
     * get ip:port
     *
     * @param port
     * @return String
     */
    public static String getIpPort(int port){
        String ip = getIp();
        return getIpPort(ip, port);
    }

    public static String getIpPort(String ip, int port){
        if (ip==null) {
            return null;
        }
        return ip.concat(":").concat(String.valueOf(port));
    }

    public static Object[] parseIpPort(String address){
        String[] array = address.split(":");

        String host = array[0];
        int port = Integer.parseInt(array[1]);

        return new Object[]{host, port};
    }


    private static String[] PROXY_IP = {"X-Real-IP", "X-Forwarded-For" , "Proxy-Client-IP", "WL-Proxy-Client-IP"
            , "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR"};

    public static String getIpAddr(HttpServletRequest request) {
        String ip = null;

        for (int i = 0; i < PROXY_IP.length; i++) {
            ip = request.getHeader(PROXY_IP[i]);
            if (!StringUtils.isEmpty(ip) && !UNKNOWN.equalsIgnoreCase(ip)) {
                continue;
            }
        }
        //兼容k8s集群获取ip
        if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if (LOCALHOST_IPV4_VALUE.equalsIgnoreCase(ip) || LOCALHOST_IPV6_VALUE.equalsIgnoreCase(ip)) {
                //根据网卡取本机配置的IP
                InetAddress inetAddress = null;
                try {
                    inetAddress = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    log.error("getClientIp error: {}", e);
                }
                ip = inetAddress.getHostAddress();
            }
        }

        if (!StringUtils.isEmpty(ip)) {
            ip = ip.split(IP_UTILS_SPLIT)[0];
        }
        return ip;
    }


}
