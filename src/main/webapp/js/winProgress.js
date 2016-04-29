/**
 * Created by why on 2016/4/28.
 */

//频率 
var frequency = 50;
//步长 
var step = 3;
//背景颜色 
var loadingBgcolor = "#b9fff1";
//宽度 
var loadingWidth = 450;
//成功之后,清除Interval
var id;
/* 
 *参数说明: 
 *content：显示内容，可以为空； 
 *imageURL：将引用JS文件的路径设置即可； 
 *left：进度条显示位置left 
 *top：进度条显示位置top 
 */
function Loading(content, imageURL, left, top)
{
    imageURL = imageURL + "Loading.jpg";

    LoadTable(content, imageURL, left, top);
    showimage.style.display="";
    id=window.setInterval("RefAct();", frequency);
}

function RefAct()
{
    var imgAct=document.getElementById("imgAct");
    imgAct.width += step;
    if(imgAct.width > loadingWidth-4)
    {
        imgAct.width = 0;
    }
}

function LoadTable(content, imageURL, left, top)
{
    var hh=window.parent.document.body.scrollTop;
    var hmin=window.document.body.scrollTop;
    if(hh+hmin<=20){
        hh=top;
    }else{
        hh=hh+hmin;
    }
    var strLoading;
    strLoading = "";
    strLoading += "<div id=\"showimage\" style=\"DISPLAY:none;Z-INDEX:100;LEFT:" + left+ "px;POSITION:absolute;TOP:" +hh+ "px;\" align=\"center\">";
    strLoading += "<TABLE id=\"Table1\" cellSpacing=\"0\" cellPadding=\"0\" width=\"" + loadingWidth + "\" border=\"0\" bgcolor=\"" + loadingBgcolor+ "\">";
    if(content != "")
    {
        strLoading += "<tr>";
        strLoading += "<td align=\"center\">";
        strLoading += "<font size=\"3\" face=\"Courier New, Courier, mono\"><strong>" + content + "</strong></font>";
        strLoading += "</td>";
        strLoading += "</tr>";
        strLoading += "<TR>";
    }
    strLoading += "<TD style='border:#33001a 1px solid;background-color: white; ' height=\"8\">";
    strLoading += "<IMG id=\"imgAct\" height=\"15\" alt=\"\" src=\"" + imageURL + "\" width=\"0\">";
    strLoading += "</TD>";
    strLoading += "</TR>";
    strLoading += "</TABLE>";
    strLoading += "</div>";

    document.getElementById("loading_div").innerHTML = strLoading;
}

function clearint(){
    window.clearInterval(id);
    document.getElementById("loading_div").innerHTML='';
}-