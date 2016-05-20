package cn.why.form;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.*;

/**
 * 登陆、文件上传、表单提交测试
 * Created by why on 2016/5/6.
 */
public class TestLogin {

    public static void main(String[] args) {
//        HttpClient httpClient = LoginFactory.getLoginHttpClient(LoginConstant.LOGIN_SUBMIT_URL);
//        String filePath = "E:" + File.separator + "Work" + File.separator + "form" + File.separator + "执照.pdf";
//        UploadFactory.upload(httpClient, FormConstant.UPLOAD_URL_PDF, "fileWt", filePath);

        String imagePath = "E:" + File.separator + "Work" + File.separator + "form" + File.separator + "111.jpg";
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://101.200.142.161//base/getfile/download/501469?uid=zgg");
        try {
            HttpResponse response = httpClient.execute(httpGet);
            FileOutputStream fileOutputStream = new FileOutputStream(new File(imagePath));
            response.getEntity().writeTo(fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
