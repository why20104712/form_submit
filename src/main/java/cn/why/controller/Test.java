package cn.why.controller;

import cn.why.model.Province;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by why on 2016/4/27.
 */
public class Test {


    public static void main(String[] args) {

//        String jsonStr = "[{\"id\":\"340800\",\"name\":\"安徽省安庆市\"},{\"id\":\"340823\",\"name\":\"安徽省安庆市枞阳县\"}]";
        //ObjectMapper类用序列化与反序列化映射器
        ObjectMapper mapper = new ObjectMapper();
        String jsonPath = "E:" + File.separator + "Work" + File.separator + "form" + File.separator + "provinces.json";
        File json = new File(jsonPath);
        //当反序列化json时，未知属性会引起的反序列化被打断，这里我们禁用未知属性打断反序列化功能，
        //因为，例如json里有10个属性，而我们的bean中只定义了2个属性，其它8个属性将被忽略
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

//        三种方式加载文件
//        1、使用类加载器
        InputStream inputStream = Test.class.getClassLoader().getResourceAsStream("provinces.json");
//        2、使用文件系统
//        FileInputStream fileInputStream = new FileInputStream("D:\\provinces.json");
//        3、使用web程序可以通过ServletContext进行加载
//        inputStream = sc.getResourceAsStream("/config/server-config.properites");

        JavaType javaType = getCollectionType(ArrayList.class, Province.class);
        try {
            List<Province> lst = (List<Province>) mapper.readValue(readString(inputStream), javaType);
            for (Province province : lst) {
                System.out.println(province.getName() + "    " + province.getId());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取泛型的Collection Type
     *
     * @param collectionClass 泛型的Collection
     * @param elementClasses  元素类
     * @return JavaType Java类型
     * @since 1.0
     */
    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }


//        String pic = "http://192.168.88.7//base/getfile/pic/8569?uid=1251";
//        String imagePath = "E:" + File.separator +"imagePath" +".jpg";
//        HttpClient httpClient = HttpClients.createDefault();
//        HttpGet httpGet = new HttpGet(pic);
//        try {
//            HttpResponse response = httpClient.execute(httpGet);
//            FileOutputStream fileOutputStream = new FileOutputStream(new File(imagePath));
//            response.getEntity().writeTo(fileOutputStream);
//            fileOutputStream.flush();
//            fileOutputStream.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    private static String readString(InputStream inputStream) {
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
