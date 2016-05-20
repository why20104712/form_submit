package cn.why.form;

import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * 商标申请表单的数据拼装和请求执行
 * Created by why on 2016/5/4.
 */
public class FormFactory {

    //日志记录
    private static Logger logger = Logger.getLogger(FormFactory.class.getName());


    /**
     * 封装请求参数
     * 需要从数据库获取对应的参数信息
     * @return List<NameValuePair>
     */
    public static List<NameValuePair> getPostParam() {

        List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
        /**
         * TODO:添加对应的参数值
         */
        nameValuePairList.add(new BasicNameValuePair("signCert", LoginConstant.BEIJING_LOGIN_SIGN_CERT));


        return nameValuePairList;
    }

    /**
     * 进行form表单提交的请求
     * @param nameValuePairList 请求参数
     * @param httpClient 客户端
     * @param httpContext 上下文
     * @return HttpClient
     */
    public static HttpClient executePost(List<NameValuePair> nameValuePairList, HttpClient httpClient, HttpContext httpContext) {

        HttpPost httpPost = new HttpPost(LoginConstant.FROM_SUBMIT_URL);
        HttpResponse httpResponse = null;
        UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(nameValuePairList, Consts.UTF_8);//指定请求参数编码格式
        httpPost.setEntity(urlEncodedFormEntity);
        try {
            httpResponse = httpClient.execute(httpPost, httpContext);
            if (null != httpResponse) {
                int statusCode = httpResponse.getStatusLine().getStatusCode();

                if (HttpStatus.SC_OK == statusCode) {

                    /**
                     * TODO:进行form提交之后的响应处理
                     */
                    logger.info("form表单提交成功");
                }

            } else {
                logger.info("form请求无响应");
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("form请求失败" + e.getMessage());
        }

        return httpClient;
    }

}
