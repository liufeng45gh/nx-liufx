package com.lucifer.service.hfc;

import com.lucifer.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by liufx on 2017/12/1.
 */
@Component
public class TradeService {

    @Value("${wx.appid}")
    private String appId;

    @Value("${wx.mchId}")
    private String mchId;

    @Value("${wx.apiKey}")
    private String apiKey;

    @Value("${wx.serviceDom}")
    private String serviceDom;

    @Value("${wx.unifiedorderUrl}")
    private String unifiedorderUrl;

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

    public Result wxMakeOrder(String type,
                            String body,
                            String detail,
                            String out_trade_no,
                            Integer total_fee,
                            String spbill_create_ip,
                            String notify_url) throws Exception {
        //String appid = "wx93f526ae469ba2f0";
        //String mch_id = "1455164902";
        //String apiKey = "NUANXingapiKey6352ERyusiJanDaoNo";
        //String serviceDom = "http://47.92.129.220:8082";
        //String weixin_pay_unified_place_order_url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
        //String uuid = UUIDUtil.getId();
        //ResourceBundle resourceBundle = ResourceBundle.getBundle("commission");
        //String serviceIp = resourceBundle.getString("serviceIp");
        String noticeUrl =  serviceDom + notify_url;
                //"http://" + serviceIp + "/giveReward_AppWeixinNotify.action";
        String currTime = dateFormat.format(new Date());
        //String strTime = currTime.substring(8, currTime.length());
        //String strRandom = String.valueOf(PayCommonUtil.buildRandom(4));
        String nonce_str = RandomUtil.getRandomString(32);
        String trade_type = "APP";
        SortedMap<Object, Object> packageParams = new TreeMap();
        packageParams.put("appid", appId);
        packageParams.put("mch_id", mchId);
        packageParams.put("nonce_str", nonce_str);
        packageParams.put("body", body);
        packageParams.put("out_trade_no", out_trade_no);
        //BigDecimal bigIntegerTotal = new BigDecimal(totalFee);
        //BigDecimal param = new BigDecimal(100);
        //int total_fee_temp = param.multiply(bigIntegerTotal).intValue();
        //String total_fee = String.valueOf(total_fee_temp);
        packageParams.put("total_fee", total_fee);
        packageParams.put("spbill_create_ip", spbill_create_ip);
        packageParams.put("notify_url", noticeUrl);
        packageParams.put("trade_type", trade_type);
        packageParams.put("attach", type);
        String sign = this.createSign(packageParams, apiKey);
        packageParams.put("sign", sign);
        String requestXML = this.getRequestXml(packageParams);
        logger.info(requestXML);
        String resXml = HttpsUtil.sPost(unifiedorderUrl, requestXML,"utf-8");
        Map map = XMLUtil.doXMLParse(resXml);
        String return_code = (String)map.get("return_code");
        String prepay_id = null;
        if(return_code.contains("SUCCESS")) {
            prepay_id = (String)map.get("prepay_id");
        }
        return Result.ok(map);
      
        
    }


    public  String createSign(SortedMap<Object, Object> packageParams, String API_KEY) {
        StringBuffer sb = new StringBuffer();
        Set es = packageParams.entrySet();
        Iterator it = es.iterator();

        while(it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            String k = (String)entry.getKey();
            String v = (String)entry.getValue();
            if(v != null && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }

        sb.append("key=" + API_KEY);
        String sign = Md5Utils.md5(sb.toString()).toUpperCase();
        return sign;
    }

    public static String getRequestXml(SortedMap<Object, Object> parameters) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        Set es = parameters.entrySet();
        Iterator it = es.iterator();

        while(true) {
            while(it.hasNext()) {
                Map.Entry entry = (Map.Entry)it.next();
                String k = (String)entry.getKey();
                String v = (String)entry.getValue();
                if(!"attach".equalsIgnoreCase(k) && !"body".equalsIgnoreCase(k) && !"sign".equalsIgnoreCase(k)) {
                    sb.append("<" + k + ">" + v + "</" + k + ">");
                } else {
                    sb.append("<" + k + ">" + "<![CDATA[" + v + "]]></" + k + ">");
                }
            }

            sb.append("</xml>");
            return sb.toString();
        }
    }


}
