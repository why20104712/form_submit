package cn.why.tomcat;

/**
 * Created by why on 2016/5/27.
 */
public class Test {
    public static void main(String[] args) {
        String home = System.getenv("catalina.home");
        String base = System.getenv("catalina.base");

        String userDir = System.getProperty("user.dir");


        System.out.println(userDir);
    }
}
