<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/4/27
  Time: 11:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传</title>
</head>
<body>
<div class="panel-body">
    <form id ="firstUpdateForm" action="/uploadController/upload" method="post" enctype="multipart/form-data" >
                <label class="col-sm-3 control-label">上传文件</label>
                <input type="file" id="firstDemoImgFile" name="multipartFile">
        <button id="createPeriadBtn" type="submit" class="btn btn-default">确定 </button>
    </form>
</div>
</body>
</html>
