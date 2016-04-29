package cn.why.controller;

/**
 *
 * Created by why on 2016/4/27.
 */
public class Test {


    public static void main(String[] args) {
        int total = 2;
        int beginPage = 0;
        int pageSize = 10;//分页大小
        int num = total / pageSize;

        for (int i = 0; i <= num; i++) {

            beginPage = i * pageSize;
            System.out.println(beginPage);


        }
    }
}
