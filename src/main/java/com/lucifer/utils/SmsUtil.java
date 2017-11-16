//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.lucifer.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public abstract class SmsUtil {
    public SmsUtil() {
    }

    public static void sendSMS(String mobile, String content, String name) {
        System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
        System.setProperty("sun.net.client.defaultReadTimeout", "30000");
        StringBuffer buffer = new StringBuffer();
        String encode = "GBK";
        String username = "xnkj";
        String password_md5 = "1adbb3178591fd5bb0c248518f39bf6d";
        String apikey = "10ef83eb3a17312af4d5e79eb7a502f7";

        try {
            String contentUrlEncode = URLEncoder.encode(content, encode);
            buffer.append("http://m.5c.com.cn/api/send/index.php?username=" + username + "&password_md5=" + password_md5 + "&mobile=" + mobile + "&apikey=" + apikey + "&content=" + contentUrlEncode + "&encode=" + encode);
            URL url = new URL(buffer.toString());
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Connection", "Keep-Alive");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String result = reader.readLine();
            System.out.println(result);
            System.out.println(content);
        } catch (Exception var13) {
            var13.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Integer mobileCode = Integer.valueOf((int)((Math.random() * 9.0D + 1.0D) * 1000.0D));
        String content = new String("【暖行】您的验证码是：" + mobileCode + "，一分钟内有效。");
        sendSMS("+8613681181338", content, (String)null);
    }
}
