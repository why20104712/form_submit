package cn.why.controller;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.*;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by why on 2016/4/29.
 */
public class ZggLoginTest {

    public static void main(String[] args) throws Exception {
        login2Lashou();
    }

    // Post方法，模拟表单提交参数登录到网站。
    // 结合了上面两个方法：grabPageHTML/downloadFile，同时增加了Post的代码。
    public static void login2Lashou() throws Exception {
        // 第一步：先下载验证码到本地
        String url = "http://127.0.0.1:8080/zgg_admin_new/regCode/getCode";
        String destfilename = "D:\\code.png";
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(url);
        File file = new File(destfilename);
        if (file.exists()) {
            file.delete();
        }

        HttpResponse response = httpclient.execute(httpget);
        HttpEntity entity = response.getEntity();
        InputStream in = entity.getContent();
        try {
            FileOutputStream fout = new FileOutputStream(file);
            int l = -1;
            byte[] tmp = new byte[2048];
            while ((l = in.read(tmp)) != -1) {
                fout.write(tmp);
            }
            fout.close();
        } finally {
            in.close();
        }
        httpget.releaseConnection();

        // 第二步：用Post方法带若干参数尝试登录，需要手工输入下载验证码中显示的字母、数字
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("请输入下载下来的验证码中显示的数字...");
        String regCode = br.readLine();

        HttpPost httppost = new HttpPost("http://127.0.0.1:8080/zgg_admin_new/login/check");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("name", "zgg_zhoulingling"));
        params.add(new BasicNameValuePair("password", "123456"));
        params.add(new BasicNameValuePair("regCode", regCode));
        httppost.setEntity(new UrlEncodedFormEntity(params));

        response = httpclient.execute(httppost);
        entity = response.getEntity();
        // 在这里可以用Jsoup之类的工具对返回结果进行分析，以判断登录是否成功
        String postResult = EntityUtils.toString(entity, "GBK");
        // 我们这里只是简单的打印出当前Cookie值以判断登录是否成功。
        CookieStore cookieStore = ((AbstractHttpClient) httpclient).getCookieStore();
        List<Cookie> cookies = ((AbstractHttpClient) httpclient)
                .getCookieStore().getCookies();
        for (Cookie cookie : cookies) {
            System.out.println("***cookie begin***");
            System.out.println(cookie);
            System.out.println("***cookie end***");
        }

        httppost.releaseConnection();

        // 第三步：打开会员页面以判断登录成功（未登录用户是打不开会员页面的）
        String memberpage = "http://127.0.0.1:8080/zgg_admin_new/finance/exportList?menuId=49";
        httpget = new HttpGet(memberpage);
        response = httpclient.execute(httpget); // 必须是同一个HttpClient！

        CookieStore cookieStore1 = ((AbstractHttpClient) httpclient).getCookieStore();
        List<Cookie> cookies1 = ((AbstractHttpClient) httpclient)
                .getCookieStore().getCookies();
        for (Cookie cookie : cookies1) {
            System.out.println("***cookie begin***");
            System.out.println(cookie);
            System.out.println("***cookie end***");
        }

        entity = response.getEntity();
        String html = EntityUtils.toString(entity, "GBK");
        httpget.releaseConnection();

        System.out.println(html);
    }

}
