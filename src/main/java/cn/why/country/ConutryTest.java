package cn.why.country;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;

/**
 * Created by why on 2016/6/18.
 */
public class ConutryTest {

    private static ClassPathXmlApplicationContext context;

    public static void initSpring() {
        context = new ClassPathXmlApplicationContext(
                new String[]{
                        "spring-context.xml"
                });
        context.start();
        System.out.println("Start Success!");
    }

    public static void main(String[] args) {

        initSpring();
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File("E:\\Intellij\\form_submit\\src\\main\\webapp\\WEB-INF\\country.html"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String html = readString(inputStream);

        Document document = Jsoup.parse(html);
        Elements els = document.select("#appCrtyId> option");

        for (Element el: els) {
//            ZgCountry zgCountry = new ZgCountry();
//            String code = el.attr("value");
//            if (StringUtils.equals("-1", code)) {
//                continue;
//            }
//            zgCountry.setCode(code);
//            String[] split = el.text().split("  ");
//            zgCountry.setEnName(split[0]);
//            zgCountry.setZhName(split[1]);
//            zgCountry.setEnable(1);
//            zgCountryService.insertCountry(zgCountry);
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
