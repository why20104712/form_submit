
/**
 *
 * @param {Object} pinCode
 * @return {TypeName}
 */
function ca_sign(pinCode) {
    var PkiCom3 = document.getElementById("PkiCom3");
    pinCode = window.showModalDialog("tmoas/WSSQ/input_password.jsp",window,"dialogWidth=400px;dialogHeight=150px;status=no");
    if ( pinCode == undefined || pinCode == null || pinCode == "" )
        alert("请输入Pin码");
    return false;

    var ret;
    //step 1
    var ret = PkiCom3.AtvInit();//PkiCom3.ECInit();

    if (ret != 0) {
        //alert("客户端初始化失败:" + ret);
        alert("数字证书控件安装出错或浏览器设置有问题,导致无法验签");
        return false;
    }
    //step 2
    ret = PkiCom3.Login(1, pinCode);//ECLogin(1, pinCode,"LAB_USERCERT");//
    if (!ret) {
        //alert("登录失败:"+ret);
        //alert("登录失败");
        alert("PIN码输入错误,请重新输入!提醒:九次输入错误后系统会自动锁住证书");
        PkiCom3.Logout();
        PkiCom3.AtvEnd();
        //PkiCom3.ECEnd();
        // PkiCom3.ECLogout();
        return false;
    }

    var data = composeData();
    var sign_cert=PkiCom3.GetCert(2);//;GetEc ("LAB_USERCERT",2)
    var sign_data=PkiCom3.SignData(data,32772,0);//ECSignData("LAB_USERCERT",data,32772,0);
    $("#signCert").val(sign_cert);
    //alert("返回编码后的证书信息--公钥:"+sign_cert);
    $("#signData").val(sign_data);
    //alert("原文："+data);
    $("#oriData").val(data);
    //alert('数字证书验证成功,正在提交数据......请稍候');
    return true;
}


/**
 * 有图片时做hash,因为图片的字节过多
 * @param {Object} pinCode
 * @return {TypeName}
 */
function ca_signImg(pinCode) {
    var PkiCom3 = document.getElementById("PkiCom3");
    pinCode = window.showModalDialog("tmoas/WSSQ/input_password.jsp",window,"dialogWidth=400px;dialogHeight=150px;status=no");
    //alert("pinCode: " + pinCode);
    if ( pinCode == undefined || pinCode == null || pinCode == "" ){
        alert("请输入Pin码");
        return false;
    }

    var ret;
    //step 1
    //alert(PkiCom3);
    var ret = PkiCom3.AtvInit();//PkiCom3.ECInit();

    if (ret != 0) {
        //alert("客户端初始化失败:" + ret);
        alert("数字证书控件安装出错或浏览器设置有问题");
        return false;
    }
    //step 2
    ret = PkiCom3.Login(1, pinCode);//ECLogin(1, pinCode,"LAB_USERCERT");//
    if (!ret) {
        //alert("登录失败:"+ret);
        alert("PIN码输入错误,请重新输入!提醒:九次输入错误后系统会自动锁住证书");
        PkiCom3.Logout();
        PkiCom3.AtvEnd();
        //PkiCom3.ECEnd();
        // PkiCom3.ECLogout();
        return false;
    }

    var data = composeData();
    var sign_cert=PkiCom3.GetCert(2);//;GetEc ("LAB_USERCERT",2)
    var hash_data = PkiCom3.HashData(32772, data);
    var sign_data=PkiCom3.SignData(hash_data,32772,0);//ECSignData("LAB_USERCERT",data,32772,0);
    $("#signCert").val(sign_cert);
    //alert("返回编码后的证书信息--公钥:"+sign_cert);
    $("#signData").val(sign_data);
    $("#hashData").val(hash_data);//hashdata,只是用来做验签
    //alert("原文："+data);
    $("#oriData").val(data);
    //alert('数字证书验证成功,正在提交数据......请稍候');
    return true;
}

/**
 * 此为更新时需要用到的方法
 * 有图片时做hash,
 * @param {Object} pinCode
 * @return {TypeName}
 */
function ca_signUpdate(pinCode) {
    var PkiCom3 = document.getElementById("PkiCom3");
    pinCode = window.showModalDialog("tmoas/WSSQ/input_password.jsp",window,"dialogWidth=400px;dialogHeight=150px;status=no");
    //alert("pinCode: " + pinCode);
    if ( pinCode == undefined || pinCode == null || pinCode == "" ){
        alert("请输入Pin码");
        return false;
    }

    var ret;
    //step 1
    var ret = PkiCom3.AtvInit();//PkiCom3.ECInit();

    if (ret != 0) {
        //alert("客户端初始化失败:" + ret);
        alert("数字证书控件安装出错或浏览器设置有问题");
        return false;
    }
    //step 2
    ret = PkiCom3.Login(1, pinCode);//ECLogin(1, pinCode,"LAB_USERCERT");//
    if (!ret) {
        //alert("登录失败:"+ret);
        alert("PIN码输入错误,请重新输入!提醒:九次输入错误后系统会自动锁住证书");
        PkiCom3.Logout();
        PkiCom3.AtvEnd();
        //PkiCom3.ECEnd();
        // PkiCom3.ECLogout();
        return false;
    }

    var data = composeData();
    var sign_cert=PkiCom3.GetCert(2);//;GetEc ("LAB_USERCERT",2)
    var hash_data = PkiCom3.HashData(32772, data);
    var sign_data=PkiCom3.SignData(hash_data,32772,0);//ECSignData("LAB_USERCERT",data,32772,0);
    $("#signCert").val(sign_cert);
    //alert("返回编码后的证书信息--公钥:"+sign_cert);
    $("#signData").val(sign_data);
    $("#hashData").val(hash_data);//hashdata,只是用来做验签
    //alert("原文："+data);
    $("#oriData").val(data);

    return true;
}

/**
 *
 * @param {Object} label
 * @param {Object} value
 * @return {TypeName}
 */
function compose(label, value) {
    return '[![' + label + ']~]' + value + ']!]';
}
function KeyDown(){
    if ((window.event.altKey)&&
        ((window.event.keyCode==37)|| //屏蔽 Alt+ 方向键 ← 
        (window.event.keyCode==39))){ //屏蔽 Alt+ 方向键 → 
        event.returnValue=false;
    }
    if ((event.keyCode==8) || //屏蔽退格删除键 
        (event.keyCode==116)|| //屏蔽 F5 刷新键 
        (event.ctrlKey && event.keyCode==82)){ //Ctrl + R 
        event.keyCode=0;
        event.returnValue=false;
    }
    if ((event.ctrlKey)&&(event.keyCode==78)) //屏蔽 Ctrl+n 
        event.returnValue=false;
    if ((event.shiftKey)&&(event.keyCode==121)) //屏蔽 shift+F10 
        event.returnValue=false;
//		if ((event.keyCode == 112) || //屏蔽 F1 刷新键
//			(event.keyCode == 122) || //屏蔽 F11 全屏显示会有后退
//			(event.shiftKey) || //屏蔽 shift 刷新键
//			(event.altKey) || //屏蔽 alert 刷新键
//			(event.ctrlKey)) //屏蔽 crrl 刷新键
//		    event.keyCode = 0;
//	        event.returnValue = false;
}

/**
 *
 */
function KeyDown2(){
    if ((window.event.altKey)&&
        ((window.event.keyCode==37)|| //屏蔽 Alt+ 方向键 ← 
        (window.event.keyCode==39))){ //屏蔽 Alt+ 方向键 → 
        event.returnValue=false;
    }
    if ( (event.keyCode==116)|| //屏蔽 F5 刷新键 
        (event.ctrlKey && event.keyCode==82)){ //Ctrl + R 
        event.keyCode=0;
        event.returnValue=false;
    }
    if ((event.ctrlKey)&&(event.keyCode==78)) //屏蔽 Ctrl+n 
        event.returnValue=false;
    if ((event.shiftKey)&&(event.keyCode==121)) //屏蔽 shift+F10 
        event.returnValue=false;
}


//管理员则无法进行下一步操作
//${applicationScope.contextPath}/独立放在一个js里面这没用
/**
 *
 * @param {Object} bu
 */
function sqSubmit(bu){
    $.post("user_isTmManeger.xhtml", function(data) {
        if (data == "false") {
            //alert('您是管理员，无法进行下一步操作');
            $("#"+bu).attr("disabled",true);
            $("#butdaili").attr("disabled",true);
            $("#butgeren").attr("disabled",true);
        }
        if (data == "true") {
            $("#"+bu).attr("disabled",false);
        }
    });
}



/**
 * 页面另存
 * @param {Object} fileName
 */
function SaveAsFile(fileName)
{
    //fileName文件名
    var hw = window.open();
    hw.document.open("html","utf-8");
    var title = fileName  + ".html";
    hw.document.writeln("<html><head><title>" + title + "</title></head><body>");
    hw.document.write(document.body.innerHTML);//$("#html").html()
    hw.document.writeln();
    hw.document.writeln("</body></html>");
    var ret = hw.document.execCommand ("SaveAs", true, title);
    hw.close();

}


//代理正式提交 
/**
 *
 * @param {Object} url
 */
function doDailiCommit(url){
    //document.form1.butdali.disabled=true;
    //判断余额是否足--也可以用ajax判断
    if(parseFloat($("#appMoney").val())>parseFloat($("#balance").val())){
        alert('您当前余额不足以申请,请充值后再来申请');
    }
    else{
        $("#butdali").attr("disabled",true);
        if ( ca_sign("") ) {
            $("#bnext").attr("disabled",true);
            $("#bback").attr("disabled",true);
            $("#bgive").attr("disabled",true);
            $("#bsave").attr("disabled",true);
            document.form1.action=url;
            document.form1.submit();
        }else{
            //alert("登陆失败，检查是否密码输错");
            $("#butdali").attr("disabled",false);
        }
    }

}
//个人正式提交
/**
 *
 * @param {Object} url
 */
function doGerenCommit(url){
    $("#butgeren").attr("disabled",true);
    $("#bnext").attr("disabled",true);
    $("#bback").attr("disabled",true);
    $("#bgive").attr("disabled",true);
    $("#bsave").attr("disabled",true);
    document.form1.action=url;
    document.form1.submit();
}
//得到下次申请
/**
 *
 * @param {Object} url
 */
function doNextsqStart(url){
    document.form1.action=url;
    document.form1.submit();
}

/**
 *
 */
function do_Scoroll(){
    window.parent.document.documentElement.scrollTop=0;
    window.parent.document.body.scrollTop=0;
}
	

	
