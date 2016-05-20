package cn.why.form;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.logging.Logger;

/**
 * 文件上传模块
 * Created by why on 2016/5/6.
 */
public class UploadFactory {

    //日志记录
    private static Logger logger = Logger.getLogger(UploadFactory.class.getName());

    public static void upload(HttpClient httpClient, final String uploadUrl, String fieldName, String filePath) {
//        try {
        HttpPost httpPost = new HttpPost(uploadUrl);

        ContentType contentType = ContentType.create("application/octet-stream", Charset.forName("UTF-8"));
        FileBody fileBody = new FileBody(new File(filePath), contentType);
        StringBody fileFlag = StringBody.create("7", "text/plain", Charset.forName("UTF-8"));


        HttpEntity reqEntity = MultipartEntityBuilder.create().addPart(fieldName, fileBody).addPart("fileFlag", fileFlag).build();
//        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();

//        File file = new File("d:/photo.jpg");
//        普通input属性
//        mutiEntity.addPart("desc",new StringBody("美丽的西双版纳", Charset.forName("utf-8")));
//        文件属性
//        mutiEntity.addPart("pic", newFileBody(file));

        httpPost.setEntity(reqEntity);
        System.out.println("executing request " + httpPost.getRequestLine());
        HttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
            if (null != response) {
                System.out.println("----------------------------------------");
                System.out.println(response.getStatusLine());
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    System.out.println("Response content length: " + resEntity.getContentLength());
                    String responseText = EntityUtils.toString(resEntity, Charset.forName("UTF-8"));


                    System.out.println(responseText);

                    /**
                     * 解析返回中的指定字段的值
                     */
                    Document document = Jsoup.parse(responseText);
                    Elements fileIds = document.select("input[name='fileId']"); //查找name为fileId的input
                    Elements fileNames = document.select("input[name='fileName']"); //查找name为fileName的input

                    Element fileId = null;
                    Element fileName = null;
                    if (null != fileIds) {
                        fileId = fileIds.first();
                    }
                    if (null != fileNames) {
                        fileName = fileNames.first();
                    }

                    System.out.println(fileName.toString() + fileId);
                }

                EntityUtils.consume(reqEntity);
            } else {
                logger.info("文件上传请求失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("文件上传请求失败" + e.getMessage());
        }
//            try {
//                if (resEntity != null) {
//                    System.out.println("Response content length: " + resEntity.getContentLength());
//                    System.out.println(EntityUtils.toString(resEntity));
//                }
//                EntityUtils.consume(resEntity);
//            } finally {
//                response.close();
//            }
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
