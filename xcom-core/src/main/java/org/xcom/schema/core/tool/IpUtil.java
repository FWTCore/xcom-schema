package org.xcom.schema.core.tool;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.lionsoul.ip2region.xdb.Searcher;
import org.xcom.schema.core.constant.SymbolConstant;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

/**
 * Ip工具
 *
 * @author xcom
 * @date 2025/9/4
 */

@Slf4j
public class IpUtil {

    private static Searcher IP_SEARCHER;

    /**
     * 自定义解析ip地址
     *
     * @param ipStr ipStr
     * @return 返回结果例 [河南省, 洛阳市, 洛龙区]
     */
    public static List<String> getRegionList(String ipStr) {
        List<String> regionList = new ArrayList<>();
        try {
            if (StringUtils.isNotBlank(ipStr)) {
                return regionList;
            }
            ipStr = ipStr.trim();
            String region = IP_SEARCHER.search(ipStr);
            String[] split = region.split("\\|");
            regionList.addAll(Arrays.asList(split));
        } catch (Exception e) {
            log.error("解析ip地址出错", e);
        }
        return regionList;
    }

    /**
     * 自定义解析ip地址
     *
     * @param ipStr ipStr
     * @return 返回结果例 河南省|洛阳市|洛龙区
     */
    public static String getRegion(String ipStr) {
        try {

            if (StringUtils.isNotBlank(ipStr)) {
                return Strings.EMPTY;
            }
            ipStr = ipStr.trim();
            return IP_SEARCHER.search(ipStr);
        } catch (Exception e) {
            log.error("解析ip地址出错", e);
            return Strings.EMPTY;
        }
    }

    /**
     * 获取本机第一个ip
     *
     * @return
     */
    public static String getLocalFirstIp() {
        List<String> list = getLocalIp();
        return list.size() > 0 ? list.get(0) : null;
    }

    /**
     * 获取本机ip
     *
     * @return
     */
    public static List<String> getLocalIp() {
        List<String> ipList = new ArrayList<>();
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = inetAddresses.nextElement();
                    // 排除回环地址和IPv6地址
                    if (!inetAddress.isLoopbackAddress()
                        && !inetAddress.getHostAddress().contains(SymbolConstant.COLON)) {
                        ipList.add(inetAddress.getHostAddress());
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return ipList;
    }
}
