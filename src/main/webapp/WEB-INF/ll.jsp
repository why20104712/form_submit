<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="http://sbsq.saic.gov.cn:9080/tmoas/">
    <title>中国商标网网上申请</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <link rel="stylesheet" href="css/newpublic2.css" type="text/css">
    <link href="/tmoas/css/style.css" type=text/css rel=stylesheet>
    <script type="text/javascript" src="/tmoas
/style/js/jquery-1.4.4.js"></script>
    <link type="text/css" rel="stylesheet" href="/tmoas
/style/css/validator.css"></link>
    <script type="text/javascript" charset="UTF-8" src="/tmoas
/style/js/prometValidator2.js"></script>
    <script type="text/javascript" charset="UTF-8" src="/tmoas
/style/js/formValidator-4.0.1.js"></script>
    <script type="text/javascript" charset="UTF-8" src="/tmoas
/style/js/formValidatorRegex.js"></script>
    <script type="text/javascript">
        var flag = "false";
        function doimage() {
            var a = $.trim($("#imagecode").val());
            if(a.length>=4){
                $.post("/tmoas/wssqsy_getImage.xhtml?imagecode="
                        + a, function(data) {
                    if (data == "false") {
                        //document.getElementById("imgd").innerHTML="<font color='red'>验证码输入错误</font>";
                        flag = "false";
                    }
                    if (data == "true") {
                        //document.getElementById("imgd").innerHTML="<font color='green'>恭喜您输入正确</font>";
                        flag = "true";
                    }
                });
            }
        }
        var loginStr="";
        function isKeyLogin(u_name,y_name){
            $.post("/tmoas/wssqsy_iskeyLogin.xhtml",{"kuName":u_name,"kName":y_name}, function(data) {
                if(data!=null){
                    loginStr=data;
                }else{
                    loginStr="用户出错";
                }
            });
        }

        function doCommon() {
            var loginname=$.trim($("#loginName").val());
            var password=$.trim($("#password").val());
            if (loginname == "") {
                alert("用户名不能为空");
                return false;
            }
            if (password== "") {
                alert("密码不能为空");
                return false;
            }
            if (document.getElementById("imagecode").value == "") {
                alert("验证码没输入");
                return false;
            }
            if (flag == "false") {
                var timenow = new Date().getTime();//这是为了防止每次刷新的时候验证码相同
                $("#validateCode").attr("src", "wssqsy_doRandCode.xhtml?d=" + timenow);
                $("#imagecode").val('');
                alert("验证码输入错误");
                return false;
            }
            document.form2.action = "/tmoas/wssqsy_getlogin.xhtml";
            document.form2.submit();
            return true;

        }

        function dotonggao() {
            location.href = "/tmoas/wssqsy_getBulltin.xhtml";
        }

        function doreset() {
            $("#loginName").val('');
            $("#password").val('');
            $("#imagecode").val('');
            $("#imgd").html('');
        }

        var popUpWin = 0;
        function popUpWindow(URLStr) {
            if (popUpWin) {
                if (!popUpWin.closed)
                    popUpWin.close();
            }
            popUpWin = open(URLStr, 'popUpWin',
                    'resizable=yes,scrollbars=yes,width=1000,height=600,left=200,top=50');
        }

        $(function() {
            $.getJSON("/tmoas/wssqsy_getBull.xhtml?math="+Math.random(5), function(
                    data) {
                //document.getElementById("").innerHTML=data.sheng;
                if(data!=null ){

                    $("#shengming").html(data.sheng);
                    $("#gonggao").html(data.gao);
                }
            });
        })

        function changeValidateCode() {
            var timenow = new Date().getTime();//这是为了防止每次刷新的时候验证码相同
            $("#validateCode").attr("src", "wssqsy_doRandCode.xhtml?d=" + timenow);
            $("#imagecode").val('');

        }

        function loadKeyjar(){
            location.href = "/tmoas/wssqsy_loadKeyjar.xhtml";
        }

        function key_Login() {
            var pin =$.trim($("#key").val());
            if ( pin == '' ) {
                alert("请输入Pin码");
                //cancel_qr();
                return false;
            }
            var PkiCom3 =document.getElementById("PkiCom3");// $("#PkiCom3");

            var ret;
            //step 1
            var ret = PkiCom3.AtvInit();
            if (ret != 0) {
                //alert("客户端初始化失败:" + ret);
                alert("数字证书控件安装出错或浏览器设置有问题,导致无法登陆");
                $("#key").val('');
                $("#key").focus();
                return false;
            }

            //step 2
            ret = PkiCom3.Login(1, pin);
            if (!ret) {
                //alert("输入的PIN码不正确，请检查"+ret);
                alert("PIN码输入错误,请重新输入!提醒:九次输入错误后系统会自动锁住证书");
                PkiCom3.Logout();
                PkiCom3.AtvEnd();
                $("#key").val('');
                $("#key").focus();
                return false;
            }

            var sign_cert=PkiCom3.GetCert(2);//返回编码后的证书信息--公钥
            var sign_data=PkiCom3.SignData("123456",32772,0);//输出的已编码的签名数据-- 前台加密后数据
            var certinfo=PkiCom3.GetCertInfo(sign_cert);//
            if(sign_cert.length==0||sign_data.length==0){
                alert("客户端问题，请检查设置");
                $("#key").val('');
                $("#key").focus();
                return false;
            }
            var validdate = parseUserInfo(certinfo,'validity');
            validdate=validdate.replace('年','').replace('月','').replace('日','');
            /*if(get_nowDate()>=get_keyDate(validdate)){
             alert("您的证书已到期导致无法登陆,请您到有关部门办理续期手续");
             $("#key").val('');
             return false;
             }

             var compareDate=get_keyDate(validdate)-get_nowDate();
             if(compareDate<=60){
             alert("您的证书即将于"+parseUserInfo(certinfo,'validity')+"到期,请登陆后进入我的账户下[续期申请]中填写续期申请!!!");
             }*/
            //alert("????："+certinfo);
            $("#signCert").val(sign_cert);
            $("#signData").val(sign_data);
            $("#certInfo").val(certinfo);
            $("#random").val("123456");
            $("#startValidDate").val(parseUserInfo(certinfo,'validitybe').replace('年','').replace('月','').replace('日',''));
            var u_name=parseUserInfo(certinfo,'username');
            var y_name=parseUserInfo(certinfo,'name');
            $("#ca_username").val(u_name);
            $("#ca_name").val(y_name);
            $("#validDate").val(validdate.replace('年','').replace('月','').replace('日',''));
            document.form1.action = "/tmoas/wssqsy_getlogin.xhtml";
            document.form1.submit();
            return true;

        }

        function dokeyReset(){
            $("#key").val('');
        }

        //window.returnValue =pinCode = window.showModalDialog("input_password.jsp",window,"dialogWidth=400px;dialogHeight=150px;status=no");//打开后不能操作父窗口,模式窗体
        function parseUserInfo(certinfo,type) {
            var begin=certinfo.indexOf("<subject>");
            var end=certinfo.lastIndexOf("</subject>");

            var begindate=certinfo.indexOf("<validity>");
            var enddate=certinfo.lastIndexOf("</validity>");
            var subject;
            var name ;
            if(type=='username'){
                subject=certinfo.substring(begin+8,end);
                begin=subject.indexOf("<dn_cn>");
                end=subject.indexOf("</dn_cn>");
                name = subject.substring(begin+7,end);
            }else if(type=='name'){
                subject=certinfo.substring(begin+8,end);
                begin=subject.lastIndexOf("<dn_cn>");
                end=subject.lastIndexOf("</dn_cn>");
                name = subject.substring(begin+7,end);
            }else if(type=='validity'){
                subject=certinfo.substring(begindate+10,enddate);
                begin=subject.indexOf("<notafter>");
                end=subject.lastIndexOf("</notafter>");
                name = subject.substring(begin+10,end);
            }
            else if(type=='validitybe'){
                subject=certinfo.substring(begindate+10,enddate);
                begin=subject.indexOf("<notbefore>");
                end=subject.lastIndexOf("</notbefore>");
                name = subject.substring(begin+11,end);
            }
            return name;
        }

        function get_nowDate(){
            var a=new Date();
            var yy=parseInt(a.getFullYear());
            var mm=a.getMonth()+1;
            mm=(mm>=10?mm:'0'+mm);
            var dd=a.getDate();
            dd=(dd>=10?dd:'0'+dd);
            var now=new Date(a.getFullYear(),a.getMonth(),a.getDate());
            return Math.floor(now.getTime()/(1000*60*60*24));//yyyy+"年"+mm+"月"+dd+"日";//+hh+":" +ff+":"+ss;
        }
        /**
         * 前提条件传进来的日期格式字符串必须是2013年01月27日(yyyy年MM月dd日)
         * @param {Object} dateCa
         * @return {TypeName}
         */
        function get_keyDate(dateCa){
            //var dateCa='20130127';
            var yy1=parseInt(dateCa.substr(0,4));
            var mm1;
            //  经研究发现parseInt当不指定radix时，当以0x开头时，s按照十六进制计算的；如果以0开头且第二位不为x,则s是按照八进制计算的，因为八进制不能有8，9所以报错返回0。
            mm1=parseInt(dateCa.substr(4,2),10)-1;
            var strmm=dateCa.substr(4,2);
            if(strmm.substr(0,1)=='0'){
                mm1=parseInt(strmm.substr(1,1))-1;
            }
            var dd1;
            dd1=parseInt(dateCa.substr(6,2),10);
            var strdd=dateCa.substr(6,2);
            if(strdd.substr(0,1)=='0'){
                dd1=parseInt(strdd.substr(1,1));
            }
            var key=new Date(yy1,mm1,dd1);
            return Math.floor(key.getTime()/(1000*60*60*24));
        }

        function enterCommit(){
            if($("#imagecode").focus() && event.keyCode==13){
                //document.getElementById("gerenCommit").onclick();
                $("#gerenCommit").click();
            }
        }

        var ipNum=0;
        function getIp(){
            $.post("/tmoas/isApp_getServiceIp.xhtml",function(data){
                ipNum++;
                if(data=='false'){
                    $('#ipid').html('获取ip失败');
                }else{
                    $('#ipid').html("第"+ipNum+"次获取：<font color='red' size='4'>"+data+"</font>");
                }
            });
        }

    </script>



</head>


<body TOPMARGIN=0 MARGINWIDTH=0 MARGINHEIGHT=0 onload="changeValidateCode()" >
<table width="100%" border="0" align="center" cellpadding="0"
       cellspacing="0" class="banner">
    <tr>
        <td class="background_01">
            &nbsp;
        </td>
        <td class="background_02">
            &nbsp;
        </td>
        <td class="background_03">
            &nbsp;
        </td>
    </tr>
</table>
<table width="100%" border="0" align="center" cellpadding="0"
       cellspacing="0" class="leader">
    <tr>
        <td class="background_01"> &nbsp;</td>
        <td class="background_02">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td width="8%" align="center">
                        <img src="images/leader_lefticon.jpg" width="46" height="28">
                    </td>
                    <td width="80%">
                        当前位置：网上申请
                    </td>
                    <td width="3%">
                        &nbsp;
                    </td>
                    <td width="9%">
                        &nbsp;
                    </td>
                </tr>
            </table>
        </td>
        <td class="background_03"> &nbsp;</td>
    </tr>
</table>

<table width="100%" border="0" align="center" cellpadding="0"
       cellspacing="0" class="leader_under">
    <tr>
        <td class="background_01">
            &nbsp;
        </td>
        <td class="background_02">
            &nbsp;
        </td>
        <td class="background_03">
            &nbsp;
        </td>
    </tr>
</table>

<table width="100%" border="0" align="center" cellpadding="0"
       cellspacing="0" class="import">
    <tr>
        <td class="background_01">
            &nbsp;
        </td>
        <td class="background_02">
            <table width="100%" border="0" align="center" cellpadding="0"
                   cellspacing="0" class="import_m">
                <tr>
                    <td class="background_01">
                        &nbsp;
                    </td>
                    <td class="background_02">

                        <table border="0" cellspacing="0" cellpadding="0"
                               class="login_zylj">
                            <tr>
                                <td class="background_01">
                                    &nbsp;
                                </td>
                                <td class="background_02">
                                    <table width="841" height="42" border="0" cellpadding="0"
                                           cellspacing="0">
                                        <tr>
                                            <td width="36" align="center">
                                                <img src="images/login_icon_01.png" width="11" height="11">
                                            </td>
                                            <td width="805">
                                                <font color="red" size='4'>重要链接：</font>
                                                <a href="javascript:location.href='/tmoas/wssqsyKey_keyBullMain.xhtml'">新申请用户领取usb-key通告
                                                </a>｜
                                                <a href="javascript:location.href='/tmoas/wssqsy_getBulltin.xhtml'">用户后续业务领取usb-key通告
                                                </a>｜
                                                <a href="javascript:location.href='/tmoas/wssqsy_getBullList.xhtml'">
                                                    商标网上申请指南</a>｜

                                                <a href="javascript:location.href='/tmoas/sycjwt_getMain.xhtml'">
                                                    常见问题</a>｜




                                            </td>
                                        </tr>
                                    </table>
                                </td>
                                <td class="background_03">
                                    &nbsp;
                                </td>
                            </tr>
                        </table>

                        <table border="0" cellspacing="0" cellpadding="0"
                               style="margin: 10px">
                            <tr>
                                <td valign="top">
                                    <table border="0" cellspacing="0" cellpadding="0"
                                           class="login_zysm">
                                        <tr>
                                            <td class="background_01">
                                                &nbsp;
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="background_02">
                                                <table width="80%" height="600" border="0" align="center"
                                                       cellpadding="0" cellspacing="0">
                                                    <tr>
                                                        <td>
                                                            <img src="images/login_zysm_tu.jpg" width="539"
                                                                 height="61">
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td  class="clsLH22">
                                                            <div id="shengming" style="line-height: 200%;"></div>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="clsLH22">
                                                            <br>
                                                            <marquee direction="up" id="mar" style="height: 350" scrollamount="2"
                                                                     onmouseout="this.start()"  onmouseover="this.stop();">
                                                                <div id="gonggao" style="line-height: 200%;"></div>
                                                            </marquee>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="background_03">
                                                &nbsp;
                                            </td>
                                        </tr>
                                    </table>
                                </td>


                                <td style="vertical-align: top">
                                    <table cellspacing="0" cellpadding="0">
                                        <tr>
                                            <td >
                                                <form action="" method="post" name="form1" onsubmit="return key_Login()">
                                                    <table width="90%" border="0" cellspacing="0" cellpadding="0"
                                                           style="margin-left: 26px; margin-top: 20px;">
                                                        <tr>
                                                            <td height="30" colspan="3" align="center">
                                                                <h3>
                                                                    商标代理机构
                                                                </h3>
                                                                <div style="display: none">


                                                                    <object id="PkiCom3" classid="CLSID:0DC9CA0C-ED42-4C87-A335-1B0BD043FC70" ></object>


                                                                </div>
                                                                <input type="hidden" name="signCert" id="signCert"/>
                                                                <input type="hidden" name="signData" id="signData"/>
                                                                <input type="hidden" name="random" id="random"/>
                                                                <input type="hidden" name="validDate" id="validDate"/>
                                                                <input type="hidden" name="startValidDate" id="startValidDate"/>
                                                                <input type="hidden" name="certInfo" id="certInfo"/>
                                                                <input type="hidden" name="username" id="ca_username"/>
                                                                <input type="hidden" name="name" id="ca_name"/>
                                                                <input type="hidden" name="pinLogin" value="key"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td height="30" width="40%">
                                                                用户ＰＩＮ码：
                                                            </td>
                                                            <td width="60%" height="30" colspan="2">
                                                                <label>
                                                                    <input name="key" type="password"
                                                                           id="key" size="14">
                                                                </label>
                                                            </td>
                                                        </tr>
                                                        <tr>

                                                            <td height="30" colspan="3" align="center">
                                                                <img src="images/login_button_dl.png" width="65"
                                                                     height="24" onclick="key_Login()">
                                                                <img src="images/login_button_cz.png" width="65"
                                                                     height="24" onclick="dokeyReset()">
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td height="30" align="center">
                                                                <img src="images/login_icon_01.png" width="11"
                                                                     height="11">
                                                            </td>
                                                            <td height="30" colspan="2">
                                                                <a href="tmoas/wssqsy/szzssqlc.jsp">用户注册申请</a>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td height="30" align="center">
                                                                <img src="images/login_icon_02.png" width="11"
                                                                     height="11">
                                                            </td>
                                                            <td height="30" colspan="2" >
                                                                <a href="/tmoas/wssqsy_loadKeyjar.xhtml" >usb-key驱动及应用控件</a>
                                                            </td>
                                                        </tr>

                                                        <tr>
                                                            <td height="30" align="center">
                                                                <img src="images/login_icon_01.png" width="11"
                                                                     height="11">
                                                            </td>
                                                            <td height="30" colspan="2">
                                                                <a href="/tmoas/agentInfo_getAgentDljg.xhtml" target="_blank">商标代理</a>
                                                            </td>
                                                        </tr>


                                                        <tr>
                                                            <td height="30" >
                                                                &nbsp;
                                                            </td>
                                                            <td height="30" colspan="2">
                                                                &nbsp;
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </form>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td align="left">

                                            </td>
                                        </tr>
                                        <tr>
                                            <td>


                                                <form action="" method="post" name="form2"
                                                      onsubmit="return doCommon()">
                                                    <table width="90%" border="0" cellspacing="0"
                                                           cellpadding="0"
                                                           style="margin-left: 26px; margin-top: 20px">
                                                        <tr>
                                                            <td height="30" colspan="3" align="center">

                                                                <h3>
                                                                    商标申请人登录
                                                                    <input type="hidden" name="commonLogin" value="qy"/>
                                                                </h3>
                                                            </td>
                                                        </tr>

                                                        <tr>
                                                            <td height="30" width="35%">
                                                                用户名：
                                                            </td>
                                                            <td height="30" colspan="2" width="65%">
                                                                <input name="loginName" type="text" id="loginName" size="19">
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td height="30">
                                                                密&nbsp;&nbsp;码：
                                                            </td>
                                                            <td height="30" colspan="2" width="65%">
                                                                <input name="password" type="password" id="password"
                                                                       size="20">
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td height="30">
                                                                校验码：
                                                            </td>
                                                            <td height="30" colspan="2"  width="65%">
                                                                <div></div>
                                                                <input name="imagecode" style="vertical-align:middle"   type="text" id="imagecode"
                                                                       size="5" onkeyup="doimage()"  onkeydown="enterCommit()" >
                                                                <img id="validateCode" style="vertical-align:middle"
                                                                     src="wssqsy_doRandCode.xhtml"
                                                                     onclick="changeValidateCode()" />
                                                                <br><br>
                                                                &nbsp;
																		<span style="color: #666; font-size: 12px;">看不清楚？
																			<a href="javascript:changeValidateCode();">点击刷新</a> </span>

                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td colspan="2">
                                                                &nbsp;
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td colspan="2">
                                                                &nbsp;
                                                            </td>
                                                        </tr>
                                                        <tr>

                                                            <td height="30" colspan="3" align="center">
                                                                <img  src="images/login_button_dl.png" alt=""
                                                                      width="65" height="24" id="gerenCommit" onclick="doCommon()">
                                                                <img src="images/login_button_cz.png" alt=""
                                                                     width="65" height="24" onclick="doreset()">
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </form>



                                                &nbsp;

                                            </td>
                                        </tr>
                                    </table>
                                    &nbsp;
                                </td>

                            </tr>
                        </table>
                    </td>
                    <td class="background_03">
                        &nbsp;
                    </td>
                </tr>
            </table>
        </td>
        <td class="background_03">
            &nbsp;
        </td>
    </tr>
</table>






<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="http://sbsq.saic.gov.cn:9080/tmoas/">
    <title>中国商标网网上申请</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <link rel="stylesheet" href="css/newpublic2.css" type="text/css">
    <script>
    </script>
</head>

<body>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="copyright">
    <tr>
        <td class="background_01">&nbsp;</td>
        <td class="background_02">&nbsp;</td>
        <td class="background_03">&nbsp;</td>
    </tr>
</table>
</body>
</html>

</body>
</html>
