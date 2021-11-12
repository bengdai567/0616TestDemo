package com.example.demo.util.jds;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.MessageFormat;
import java.util.Enumeration;

//获取ip地址
public class IPUtil {
        private static Logger logger = LoggerFactory.getLogger(IPUtil.class);

        public IPUtil() {
        }

        public static long ipToLong(String strIp) {
            String[] ip = strIp.split("\\.");
            return (Long.parseLong(ip[0]) << 24) + (Long.parseLong(ip[1]) << 16) + (Long.parseLong(ip[2]) << 8) + Long.parseLong(ip[3]);
        }

        public static String longToIP(long longIp) {
            StringBuffer sb = new StringBuffer("");
            sb.append(longIp >>> 24);
            sb.append(".");
            sb.append((longIp & 16777215L) >>> 16);
            sb.append(".");
            sb.append((longIp & 65535L) >>> 8);
            sb.append(".");
            sb.append(longIp & 255L);
            return sb.toString();
        }

        public static InetAddress getLocalHostLANAddress() {
            try {
                InetAddress candidateAddress = null;
                Enumeration ifaces = NetworkInterface.getNetworkInterfaces();

                while(ifaces.hasMoreElements()) {
                    NetworkInterface iface = (NetworkInterface)ifaces.nextElement();
                    Enumeration inetAddrs = iface.getInetAddresses();

                    while(inetAddrs.hasMoreElements()) {
                        InetAddress inetAddr = (InetAddress)inetAddrs.nextElement();
                        if (!inetAddr.isLoopbackAddress()) {
                            if (inetAddr.isSiteLocalAddress()) {
                                return inetAddr;
                            }

                            if (candidateAddress == null) {
                                candidateAddress = inetAddr;
                            }
                        }
                    }
                }

                if (candidateAddress != null) {
                    return candidateAddress;
                } else {
                    InetAddress jdkSuppliedAddress = InetAddress.getLocalHost();
                    if (jdkSuppliedAddress == null) {
                        throw new RuntimeException("The JDK InetAddress.getLocalHost() method unexpectedly returned null.");
                    } else {
                        return jdkSuppliedAddress;
                    }
                }
            } catch (Exception var5) {
                logger.error(MessageFormat.format("获取IP失败，失败原因【{0}】", var5.getMessage()), var5);
                throw new RuntimeException(MessageFormat.format("获取IP失败，失败原因【{0}】", var5.getMessage()), var5);
            }
        }

        public static String getIp() {
            return getLocalHostLANAddress().getHostAddress();
        }

        public static String getRealHttpIp(HttpServletRequest request) {
            String ip = request.getHeader("X-Real-IP");
            if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("X-Forwarded-For");
            }

            if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }

            if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }

            if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }

            if (StringUtils.isNotBlank(ip) && ip.contains(",")) {
                String[] ipArray = ip.split(",");
                ip = ipArray[0];
            }

            return ip;
        }

        public static void main(String[] args) {
            System.out.println(getLocalHostLANAddress().getHostAddress());
            System.out.println(longToIP(3232270821L));
        }

}
