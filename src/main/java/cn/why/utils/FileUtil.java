package cn.why.utils;

import java.io.IOException;
import java.io.InputStream;

/**
 * 文件工具类
 * Created by why on 2016/6/15.
 */
public class FileUtil {

    public static String readString(InputStream inputStream) {
        int len = 0;
        String str = "";
        try {
            // size  为字串的长度 ，这里一次性读完
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
//            str = new String(buffer, "GB2312");
            str = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return str.toString();
    }
}
