package cn.why.form;

import org.apache.http.*;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * 登陆工厂，进行登陆操作并获取登录之后的http客户端
 * Created by why on 2016/5/4.
 */
public class LoginFactory {

      //http客户端相关的上下文信息（cookie等）
    private static HttpClientContext httpClientContext = new HttpClientContext();

    //日志记录
    private static Logger logger = Logger.getLogger(LoginFactory.class.getName());

    /**
     * TODO:完善不同公司的key
     * 封装请求参数
     * 需要从数据库获取对应的参数信息
     * @return List<NameValuePair>
     */
    public static List<NameValuePair> getLoginParams(final String keyName) {

        List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
        if (LoginConstant.KEY_NAME_BEIJING.equals(keyName)) {
            nameValuePairList.add(new BasicNameValuePair("signCert", LoginConstant.BEIJING_LOGIN_SIGN_CERT));
            nameValuePairList.add(new BasicNameValuePair("signData", LoginConstant.BEIJING_LOGIN_SIGN_DATA));
            nameValuePairList.add(new BasicNameValuePair("random", LoginConstant.BEIJING_LOGIN_RANDOM));
            nameValuePairList.add(new BasicNameValuePair("validDate", LoginConstant.BEIJING_LOGIN_VALID_DATE));
            nameValuePairList.add(new BasicNameValuePair("startValidDate", LoginConstant.BEIJING_LOGIN_START_VALID_DATE));
            nameValuePairList.add(new BasicNameValuePair("certInfo", LoginConstant.BEIJING_LOGIN_CERT_INFO));
            nameValuePairList.add(new BasicNameValuePair("username", LoginConstant.BEIJING_LOGIN_USERNAME));
            nameValuePairList.add(new BasicNameValuePair("name", LoginConstant.BEIJING_LOGIN_NAME));
            nameValuePairList.add(new BasicNameValuePair("pinLogin", LoginConstant.BEIJING_LOGIN_PIN_LOGIN));
            nameValuePairList.add(new BasicNameValuePair("key", LoginConstant.BEIJING_LOGIN_KEY));
        }
        return nameValuePairList;
    }


    /**
     * 获取cookie
     * @return CookieStore
     */
    public static CookieStore getCookieStore() {

        CookieStore cookieStore = new BasicCookieStore();
        BasicClientCookie cookie1 = new BasicClientCookie("yunsuo_session_verify", "bc4145715010d28ffed333e9f8e9edf7");
        cookie1.setDomain("sbsq.saic.gov.cn");
        BasicClientCookie cookie2 = new BasicClientCookie("JSESSIONID", "00002FagOXkxir9mmRZRT8-s6v3:17g9i2a78");
        cookie2.setDomain("sbsq.saic.gov.cn");
        BasicClientCookie cookie3 = new BasicClientCookie("SANGFOR_AD_TMOAS", "20111150");
        cookie3.setDomain("sbsq.saic.gov.cn");
        cookieStore.addCookie(cookie1);
        cookieStore.addCookie(cookie2);
        cookieStore.addCookie(cookie3);
        return cookieStore;
    }


    /**
     * 进行登陆操作并返回登录之后的客户端
     * @param loginUrl 登陆请求的url
     * @return HttpClient
     */
    public static HttpClient getLoginHttpClient(final String loginUrl) {

        HttpClient httpClient = HttpClients.createDefault();

        /**
         * 进行post登陆请求
         */
        HttpPost httpPost = new HttpPost(loginUrl);
        UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(getLoginParams(LoginConstant.KEY_NAME_BEIJING), Consts.UTF_8);//指定请求参数编码格式
//        UrlEncodedFormEntity urlEncodedFormEntity = null;//指定请求参数编码格式
//        try {
//            urlEncodedFormEntity = new UrlEncodedFormEntity(getPostParam());
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//            logger.info("请求参数编码错误");
//        }
        //设置请求参数
        httpPost.setEntity(urlEncodedFormEntity);

        //设置cookie
//        httpClientContext.setCookieStore(getCookieStore());

        //设置浏览器为IE
        httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko");
        HttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpPost, httpClientContext);
            if (null != httpResponse) {
                //获取响应状态码
                int statusCode = httpResponse.getStatusLine().getStatusCode();
                if (statusCode == HttpStatus.SC_OK) {//成功响应
                    //进行相应的页面访问或者重定向

                    HttpEntity httpEntity = httpResponse.getEntity();
//                    System.out.println(EntityUtils.toString(httpEntity));
                    HttpGet httpGet = new HttpGet(LoginConstant.LOGIN_SUCESS_URL);

                    HttpResponse response = httpClient.execute(httpGet, httpClientContext);
                    if (null != response) {
                        int code = response.getStatusLine().getStatusCode();
                        if (HttpStatus.SC_OK == code) {
                            logger.info("登陆成功");
                            HttpEntity entity = response.getEntity();
                            System.out.println(EntityUtils.toString(entity));
                            EntityUtils.consume(entity);//关闭底层资源
                        }

                    }
                }


                //            EntityUtils.consume(httpEntity);//关闭底层资源

            } else {
                logger.info("请求失败，无返回内容");
            }

        } catch (IOException e) {
            e.printStackTrace();
            logger.info("请求失败" + e.getMessage());
        }

        return httpClient;
    }

    /**
     * 获取http客户端上下文信息
     * @return
     */
    public static HttpClientContext getHttpClientContext() {
        return httpClientContext;
    }

}
