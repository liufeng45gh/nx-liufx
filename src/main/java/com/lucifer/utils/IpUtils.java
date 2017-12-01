package com.lucifer.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by liufx on 2017/12/1.
 */
public class IpUtils {

    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("PRoxy-Client-IP");
        }

        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        if(ip == null) {
            ip = "";
        }

        if(!StringHelper.isEmpty(ip)) {
            String[] ipArr = ip.split(",");
            if(ipArr.length > 1) {
                ip = ipArr[0];
            }
        }

        return ip;
    }
}
