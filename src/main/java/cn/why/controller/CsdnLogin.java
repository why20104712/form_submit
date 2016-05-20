package cn.why.controller;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.http.*;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * csdn模拟登陆
 * Created by why on 2016/5/3.
 */
public class CsdnLogin {

    public static void main(String[] args) throws IOException {
        CsdnLogin.testLogin();
    }

    public static void testLogin() throws IOException {
//        CloseableHttpClient httpClient = SSLClient.createSSLClientDefault();

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("https://passport.csdn.net/account/login;jsessionid=D76B936579BBC360268BDA7869610292.tomcat1?from=http://my.csdn.net/my/mycsdn");

        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        //参数设置
        nameValuePairs.add(new BasicNameValuePair("username", "why20104712%40gmail.com"));
        nameValuePairs.add(new BasicNameValuePair("password", "why%400217"));
        nameValuePairs.add(new BasicNameValuePair("rememberMe", "true"));
        nameValuePairs.add(new BasicNameValuePair("lt", "LT-178007-r61cEBQ1V7HDVtbiQgeMtGoSaeWI9j"));
        nameValuePairs.add(new BasicNameValuePair("execution", "e2s1"));
        nameValuePairs.add(new BasicNameValuePair("_eventId", "submit"));

        UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(nameValuePairs, Consts.UTF_8);
        httpPost.setEntity(urlEncodedFormEntity);

        //请求上下文设置
        HttpClientContext httpClientContext = new HttpClientContext();

        //cookie设置
       CookieStore cookieStore = new BasicCookieStore();

        BasicClientCookie cookie1 = new BasicClientCookie("JSESSIONID", "D76B936579BBC360268BDA7869610292.tomcat1");
        cookie1.setDomain("passport.csdn.net");
        BasicClientCookie cookie2 = new BasicClientCookie("uuid_tt_dd", "2622024350069507270_20160503");
        cookie2.setDomain("passport.csdn.net");
        BasicClientCookie cookie3 = new BasicClientCookie("Hm_lvt_6bcd52f51e9b3dce32bec4a3997715ac", "1462265013");
        cookie3.setDomain("passport.csdn.net");
        BasicClientCookie cookie4= new BasicClientCookie("Hm_lpvt_6bcd52f51e9b3dce32bec4a3997715ac", "1462265013");
        cookie4.setDomain("passport.csdn.net");
        BasicClientCookie cookie5 = new BasicClientCookie("dc_tos", "o6lewr");
        cookie5.setDomain("passport.csdn.net");
        BasicClientCookie cookie6 = new BasicClientCookie("dc_session_id", "1462265013070");
        cookie6.setDomain("passport.csdn.net");
        BasicClientCookie cookie7 = new BasicClientCookie("LSSC", "LSSC-307071-0d0Qd5fyB99PbsClNNRyK5k0PfG1C1-passport.csdn.net");
        cookie7.setDomain("passport.csdn.net");
        BasicClientCookie cookie8 = new BasicClientCookie("_ga", "GA1.2.2037477009.1462265020");
        cookie8.setDomain("passport.csdn.net");
        BasicClientCookie cookie9 = new BasicClientCookie("_gat", "1");
        cookie9.setDomain("passport.csdn.net");
        BasicClientCookie cookie10 = new BasicClientCookie("JSESSIONID", "D76B936579BBC360268BDA7869610292.tomcat1");
        cookie10.setDomain("passport.csdn.net");

        cookieStore.addCookie(cookie1);
        cookieStore.addCookie(cookie2);
        cookieStore.addCookie(cookie3);
        cookieStore.addCookie(cookie4);
        cookieStore.addCookie(cookie5);
        cookieStore.addCookie(cookie6);
        cookieStore.addCookie(cookie7);
        cookieStore.addCookie(cookie8);
        cookieStore.addCookie(cookie9);
        cookieStore.addCookie(cookie10);


        //设置浏览器
//        httpPost.setHeader(new BasicHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2652.2 Safari/537.36"));

        httpClientContext.setCookieStore(cookieStore);
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost, httpClientContext);
//        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);

        // HttpClient对于要求接受后继服务的请求，象POST和PUT等不能自动处理转发
        // 301或者302
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode == HttpStatus.SC_MOVED_PERMANENTLY ||
                statusCode == HttpStatus.SC_MOVED_TEMPORARILY) {
            // 从头中取出转向的地址
            Header locationHeader = httpResponse.getFirstHeader("location");
            String location = null;
            if (locationHeader != null) {
                location = locationHeader.getValue();
                System.out.println("The page was redirected to:" + location);

                HttpGet httpGet = new HttpGet(location);
                CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpGet, httpClientContext);

//                HttpEntity entity =  closeableHttpResponse.getEntity();
//                System.out.println(EntityUtils.toString(entity));

                cookieStore = httpClientContext.getCookieStore();

                List<Cookie> cookies = cookieStore.getCookies();
                if (!CollectionUtils.isEmpty(cookies)) {
                    for (Cookie cookie : cookies) {
                        System.out.println(cookie.getName() + "=" + cookie.getValue());
                    }
                    //            System.out.println(ReflectionToStringBuilder.toString(cookies));
                }

                int statusCode1 = closeableHttpResponse.getStatusLine().getStatusCode();
                if (statusCode1 == HttpStatus.SC_OK) {
                    Header locationHeader1 = httpResponse.getFirstHeader("location");
                    String location1 = null;
                    if (locationHeader1 != null) {
                        location1 = locationHeader1.getValue();
                        System.out.println("The page was redirected to:" + location1);

                    }
//                    http://my.csdn.net/my/mycsdn
                    HttpGet httpGet1 = new HttpGet(location1);
                    CloseableHttpResponse execute = httpClient.execute(httpGet1, httpClientContext);
                    HttpEntity entity1 =  execute.getEntity();
                    System.out.println(EntityUtils.toString(entity1));


                    EntityUtils.consume(entity1);

                    HttpGet httpGet2 = new HttpGet("http://my.csdn.net/my/mycsdn");
                    CloseableHttpResponse execute2 = httpClient.execute(httpGet2, httpClientContext);
                    HttpEntity entity2 =  execute2.getEntity();
                    System.out.println(EntityUtils.toString(entity2));

                }
            } else {
                System.err.println("Location field value is null.");
            }

        }

    }

}
