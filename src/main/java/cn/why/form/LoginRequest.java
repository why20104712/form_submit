package cn.why.form;

import javafx.scene.effect.ReflectionBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.http.*;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.cookie.ClientCookie;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieSpec;
import org.apache.http.impl.client.*;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.springframework.util.ReflectionUtils;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 模拟登陆请求
 * Created by why on 2016/5/3.
 */
public class LoginRequest {

    @Test
    // Post方法，模拟表单提交参数登录到网站
    public void login2Lashou() throws Exception {

        //创建httpClient，相当于浏览器
        CloseableHttpClient httpClient = HttpClients.createDefault();

//        HttpPost httpPost = new HttpPost("http://sbsq.saic.gov.cn:9080/tmoas/wssqsy_getlogin.xhtml");
        HttpPost httpPost = new HttpPost("http://sbsq.saic.gov.cn:9080/tmoas/wssqsy_getlogin.xhtml");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("user_id", "testuser007"));
        params.add(new BasicNameValuePair("pwd", "asdfg123"));
        params.add(new BasicNameValuePair("save_user", "on"));
        params.add(new BasicNameValuePair("save_pwd", "on"));
        params.add(new BasicNameValuePair("sub", "登录"));
        httpPost.setEntity(new UrlEncodedFormEntity(params));

        HttpResponse response = httpClient.execute(httpPost);
        HttpEntity entity = response.getEntity();
        // 在这里可以用Jsoup之类的工具对返回结果进行分析，以判断登录是否成功
        String postResult = EntityUtils.toString(entity, "GBK");
        // 我们这里只是简单的打印出当前Cookie值以判断登录是否成功。
        CookieStore cookieStore = ((AbstractHttpClient) httpClient).getCookieStore();
        List<Cookie> cookies = ((AbstractHttpClient) httpClient)
                .getCookieStore().getCookies();
        for (Cookie cookie : cookies)
            System.out.println("cookie begin***\n" + cookie + "\n cookie end");
        httpPost.releaseConnection();

        // 第三步：打开会员页面以判断登录成功（未登录用户是打不开会员页面的）
        String memberpage = "http://www.lashou.com/account/orders/";
        HttpGet httpGet = new HttpGet(memberpage);
        response = httpClient.execute(httpGet); // 必须是同一个HttpClient！
        entity = response.getEntity();
        String html = EntityUtils.toString(entity, "GBK");
        httpGet.releaseConnection();

        System.out.println(html);
    }

    public void test() throws IOException, URISyntaxException {
        //基本的cookies操作
        BasicClientCookie cookie = new BasicClientCookie("name", "value");
        // Set effective domain and path attributes
        cookie.setDomain(".mycompany.com");
        cookie.setPath("/");
        // Set attributes exactly as sent by the server
        cookie.setAttribute(ClientCookie.PATH_ATTR, "/");
        cookie.setAttribute(ClientCookie.DOMAIN_ATTR, ".mycompany.com");

        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        //get请求URL拼接
        URI uri = new URIBuilder()
                .setScheme("http")
                .setHost("www.google.com")
                .setPath("/search")
                .setParameter("q", "httpclient")
                .setParameter("btnG", "Google Search")
                .setParameter("aq", "f")
                .setParameter("oq", "")
                .build();
        HttpPost httpPost = new HttpPost("www.baidu.com");

        CloseableHttpResponse httpResponse = closeableHttpClient.execute(httpPost);

        Header[] allHeaders = httpResponse.getAllHeaders();
        System.out.println(ReflectionToStringBuilder.toString(allHeaders));


        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        //表单参数键值对
        NameValuePair nameValuePair = new BasicNameValuePair("username", "why");
        //设置form表单内容实体,并指定编码
        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, Consts.UTF_8));


        //cookie实体操作
        BasicClientCookie basicClientCookie = new BasicClientCookie("name", "value");

        basicClientCookie.setAttribute("path","/");
        basicClientCookie.setDomain("127.0.0.1");
        basicClientCookie.setComment("ssss");
        basicClientCookie.setExpiryDate(new Date());
        basicClientCookie.setSecure(true);
        basicClientCookie.setVersion(1);
        basicClientCookie.setCreationDate(new Date());
        basicClientCookie.setValue("vvv");

        HttpClientContext httpClientContext = new HttpClientContext();
        CookieStore cookieStore = httpClientContext.getCookieStore();
        List<Cookie> cookies = cookieStore.getCookies();


        CookieSpec cookieSpec = httpClientContext.getCookieSpec();


        CookieStore cookieStore1 = httpClientContext.getCookieStore();


    }
}
