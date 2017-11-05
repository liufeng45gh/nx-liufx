package com.lucifer.service.hfc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucifer.dao.hfc.MemberDao;
import com.lucifer.model.User;
import com.lucifer.model.hfc.Member;
import com.lucifer.utils.DateUtils;
import com.lucifer.utils.Md5Utils;
import com.lucifer.utils.RandomUtil;
import com.lucifer.utils.Result;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;

import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

/**
 * Created by fx on 2017/3/4.
 */
@Component
public class HfcAccountService {

    @Value("${sms.sendCodeUrl}")
    private String sendCodeUrl;

    @Value("${sms.appKey}")
    private String appKey;

    @Value("${sms.appSecret}")
    private String appSecret;

    @Value("${sms.verifyCodeUrl}")
    private String verifyCodeUrl;

    @Resource
    private MemberDao memberDao;

    @Resource
    private MemberLoginService memberLoginService;


    private ObjectMapper objectMapper = new ObjectMapper();

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    public Result sendPhoneMsg(String phone) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String jsonStr = null;
        CloseableHttpResponse response = null;
        try {
            // 创建httpget.
            // HttpGet httpget = new HttpGet("http://115.29.109.91:8080/data/follow-clubs?ids=" + idsStr);
            // HttpGet httpget = new HttpGet("http://192.168.0.101:8888/follow-clubs?ids=" + idsStr);
            String data = "mobile="+phone;
            HttpPost httpPost = new HttpPost(sendCodeUrl);
            HttpEntity send_entity = new StringEntity(data);
            httpPost.setEntity(send_entity);
            this.setHeader(httpPost);
            // System.out.println("executing request " + httpget.getURI());
            // 执行get请求.
            response = httpclient.execute(httpPost);

            // 获取响应实体
            HttpEntity entity = response.getEntity();
            // System.out.println("--------------------------------------");
            // 打印响应状态
            logger.info("response.getStatusLine() {}",response.getStatusLine());
            //System.out.println(response.getStatusLine());
            if (entity != null) {
                // 打印响应内容长度
                //System.out.println("Response content length: " + entity.getContentLength());
                // 打印响应内容
                // System.out.println("Response content: " + EntityUtils.toString(entity));
                jsonStr = EntityUtils.toString(entity);
            }
            logger.info("jsonStr {}",jsonStr);
           // System.out.println("------------------------------------");

        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("连接sms服务器出错") ;
        }  finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (jsonStr != null) {
            Map result = objectMapper.readValue(jsonStr,Map.class);
            Integer code = (Integer) result.get("code");
            if (code == 200) {
                return Result.ok();
            }else {
                return Result.fail(result.get("msg").toString());
            }
        }
        return Result.fail() ;
    }

    private void setHeader(HttpPost httpPost) {
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
        httpPost.setHeader("AppKey", appKey);
        Long curTime = DateUtils.now().getTime()/1000;
        httpPost.setHeader("CurTime", curTime.toString());

        String random = RandomUtil.getRamdomIntString(6);
        httpPost.setHeader("Nonce", random);
        String CheckSum = this.appSecret + random + "" + curTime;
        logger.info("CheckSum before sha1 {} ",CheckSum);
        //byte[] bytes = DigestUtils.sha1(CheckSum);
        CheckSum = DigestUtils.sha1Hex(CheckSum);
        //
        // CheckSum = String.valueOf(bytes);
        CheckSum = CheckSum.toLowerCase();
        logger.info("CheckSum after toLowerCase {} ",CheckSum);
        httpPost.setHeader("CheckSum", CheckSum);
    }



    public Result checkPhoneCode(String phone,String code) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String jsonStr = null;
        CloseableHttpResponse response = null;
        try {
            // 创建httpget.
            // HttpGet httpget = new HttpGet("http://115.29.109.91:8080/data/follow-clubs?ids=" + idsStr);
            // HttpGet httpget = new HttpGet("http://192.168.0.101:8888/follow-clubs?ids=" + idsStr);
            String data = "mobile="+phone + "&code="+code;
            HttpPost httpPost = new HttpPost(verifyCodeUrl);
            HttpEntity send_entity = new StringEntity(data);
            httpPost.setEntity(send_entity);
            this.setHeader(httpPost);
            // System.out.println("executing request " + httpget.getURI());
            // 执行get请求.
            response = httpclient.execute(httpPost);

            // 获取响应实体
            HttpEntity entity = response.getEntity();
            // System.out.println("--------------------------------------");
            // 打印响应状态
            logger.info("response.getStatusLine() {}",response.getStatusLine());
            //System.out.println(response.getStatusLine());
            if (entity != null) {
                // 打印响应内容长度
                //System.out.println("Response content length: " + entity.getContentLength());
                // 打印响应内容
                // System.out.println("Response content: " + EntityUtils.toString(entity));
                jsonStr = EntityUtils.toString(entity);
            }
            logger.info("jsonStr {}",jsonStr);
            // System.out.println("------------------------------------");

        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("连接sms服务器出错") ;
        }  finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (jsonStr != null) {
            Map result = objectMapper.readValue(jsonStr,Map.class);
            Integer resultCode = (Integer) result.get("code");
            if (resultCode == 200) {
                return Result.ok();
            }else {
                return Result.fail(result.get("msg").toString());
            }
        }
        return Result.fail() ;
    }

    public Result register(String phone,String phoneCode,String password) throws Exception {
        Result checkResult = this.checkPhoneCode(phone,phoneCode);
        Member user = new Member();
        user.setPhone(phone);
        String salt = RandomUtil.getNextSalt();
        user.setSalt(salt);
        String encrypt_password = Md5Utils.md5(Md5Utils.md5(password)+salt);
        user.setPassword(encrypt_password);
        user.setCreatedAt(DateUtils.now());
        user.setUpdatedAt(DateUtils.now());
        String account = "mp_"+RandomUtil.getNextAccount();
        user.setNickName(account);
        memberDao.insertMember(user);
        user.setPassword(password);
        Result loginResult = memberLoginService.loginByPhone(user);
        return loginResult;
    }
}
