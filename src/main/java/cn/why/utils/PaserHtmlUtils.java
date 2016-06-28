package cn.why.utils;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * 解析html工具类
 * Created by why on 2016/5/11.
 */
public class PaserHtmlUtils {

    //日志记录
    public static Logger logger = Logger.getLogger(PaserHtmlUtils.class.getName());

    /**
     * 解析html中的字段的值（input）
     * @param html html文本
     * @param fieldName 属性名
     * @return 属性值
     */
    public static String getInputValue(String html, String fieldName) {

        String value = "";
        if (StringUtils.isNotBlank(html)) {

            Document document = Jsoup.parse(html);
            Elements elements = document.select("input[name='" + fieldName + "']"); //查找name为fieldName的input

            if (null != elements && elements.size() > 0) {
                Element element = elements.first();
                if (null != element) {
                    Attributes attributes = element.attributes();
                    if (null != attributes && attributes.size() > 0) {
                        value = attributes.get("value");
//                        logger.info("页面上属性名为" + fieldName + "的input标签的值为：" + value);
                    }
                } else {
                    logger.info("页面上没有属性名为" + fieldName + "的input标签");
                }
            }
        } else {
//            logger.info("html内容为空");
        }
        return value;
    }
    
    /**
     * 获取textarea控件的值
     * @param html 页面
     * @param fieldName 属性名称
     * @return String
     */
    public static String getTextareaValue(String html, String fieldName) {

        String value = "";
        if (StringUtils.isNotBlank(html)) {

            Document document = Jsoup.parse(html);
            Elements elements = document.select("textarea[name='" + fieldName + "']"); //查找name为fieldName的input

            if (null != elements && elements.size() > 0) {
                Element element = elements.first();
                value = element.text().trim();
            }
        } else {
            logger.info("html内容为空");
        }
        return value;
    }

    /**
     * 获取单元格内容
     * @param html
     * @return String
     */
    public static List<Map<String, String>> getTdValue(String html) {

        List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
        Map<String, String> map = new HashMap<String, String>();
        String value = "";
        if (StringUtils.isNotBlank(html)) {

            Document document = Jsoup.parse(html);
            Elements trs = document.select("table .chart_list").select("tr");
            for(int i = 0;i<trs.size();i++){
                Elements tds = trs.get(i).select("td");
                //过滤不满足条件的tds
                if (tds.size() >= 6) {
                    String applyNo = tds.get(4).text();
                    if (StringUtils.isNotEmpty(applyNo)) {//申请号不为空
                        map.put("applyNo", applyNo);
                        String orderCode = tds.get(5).text();//订单号
                        map.put("orderCode", orderCode);
                        String applyDate = tds.get(3).text();//申请日期
                        map.put("applyDate", applyDate);
                        String applyPerson = tds.get(2).text();//申请人
                        map.put("applyPerson", applyPerson);

                        //下载链接
                        Elements links = tds.get(6).select("a[href]");
                        map.put("href", links.get(0).attr("href"));
                    }
                    mapList.add(map);
                }
            }
        } else {
            logger.info("html内容为空");
        }
        return mapList;
    }
}
