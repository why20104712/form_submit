<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/6/20
  Time: 15:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" type="text/css" href="./js/jquery.autocomplete.css"/>
    <script type="text/javascript" src="./js/jquery-1.8.0.min.js"></script>
    <script type="text/javascript" src="./js/jquery.autocomplete.js"></script>
    <script type="text/javascript">
//        $(function(){
//            var data = "the People's Republic of China".split(" ");
//            $("#autocomplete").autocomplete(data,{minChars:0}).result(function(event,data,formatted){
//                alert(data);
//            });
//        });

//$(function(){
//    var websites = [{ "User_LastName": "安春", "user_employeeNo": "0041", "User_UserId": 50233 },
//        { "User_LastName": "安国兵", "user_employeeNo": "1404", "User_UserId": 51278 },
//        { "User_LastName": "包小文", "user_employeeNo": "0685", "User_UserId": 50521 }];
//    $("#autocomplete").autocomplete(websites, {
//        minChars: 1,                   //最少输入字条
//        max: 12,
//        autoFill: false,                //是否选多个,用","分开
//        mustMatch: true,               //是否全匹配, 如数据中没有此数据,将无法输入
//        matchContains: true,            //是否全文搜索,否则只是前面作为标准
//        scrollHeight: 220,
//        width: 500,
//        scroll:true,
//        multiple: false,
//        formatItem: function (row, i, max) {                    //显示格式
//            return row.User_LastName;
//        },
//        formatMatch: function (row, i, max) {               //以什么数据作为搜索关键词,可包括中文,
//            return row.User_LastName;
//        },
//        formatResult: function (row) {                      //返回结果
//            return row.User_LastName;
//        }
//    });
//});




        /**
         * 联想输入
         */
        function initLxsr() {
            function format(item) {
                if (item == null) {
                    return;
                }
                return item.name;
            }

            var data = [
                {
                    "code": "340800",
                    "name": "安徽省安庆市"
                },
                {
                    "code": "340823",
                    "name": "安徽省安庆市枞阳县"
                },
                {
                    "code": "340803",
                    "name": "安徽省安庆市大观区"
                }];


            $("#autocomplete").autocomplete(data, {
                minLength: 0,
                minChars: 0,
                max: 50,
                autoFill: false,
                mustMatch: true,
                multiple: false,
                dataType: "json",
                scroll: true,
                parse: function (data) {
                    return $.map(data, function (row) {
                        return {
                            data: row,
                            value: row.code,
                            result: row.name
                        }
                    });
                },
                formatItem: function (item) {
                    return format(item);
                }
            });
        }


    </script>
</head>
<body onload="initLxsr();">
<input type="text" id="autocomplete"/>
<INPUT id=appRegionalismIdHide size=40 type=hidden name=appRegionalismIdHide>
</body>
</html>
