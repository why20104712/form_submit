package cn.why.controller;

import cn.why.form.FormConstant;
import cn.why.form.FormEntity;
import cn.why.utils.FileUtil;
import cn.why.utils.PaserHtmlUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

/**
 * Created by why on 2016/6/15.
 */
public class ApplyTest {

    public static void main(String[] args) {
//        try {
//            String readString = FileUtil.readString(new FileInputStream(new File("E:\\Intellij\\form_submit\\src\\main\\webapp\\WEB-INF\\apply.html")));
//            List<Map<String, String>> mapList = PaserHtmlUtils.getTdValue(readString);
//            System.out.println(mapList.get(0).get("code"));
//            String list = ReflectionToStringBuilder.toString(mapList);
//            System.out.println(list);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
        FormEntity formEntity = new FormEntity();
        if (null != formEntity) {
            System.out.println("11111111111111");
        }

    }
}
