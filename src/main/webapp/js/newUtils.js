/**
 * 判断数组是否存在,存在返回false
 * @param {Object} arrayList
 * @param {Object} va
 * @return {TypeName}
 */
function do_array(arrayList,va){
    if(arrayList instanceof Array){
        var num=0;
        for(var i=0;i<5;i++){
            if(arrayList[i]==va){
                num++;
            }
        }
        if(num>=1){
            return false;
        }else{
            return true;
        }
    }
}
/**
 * 存在则返回true
 * @param {Object} arrayList
 * @param {Object} va
 * @return {TypeName}
 */
function do_IsExists(arrayList,va){
    if($.inArray(va, arrayList)!=-1){
        return true;
    }else{
        return false;
    }
}

/**
 *
 */
function do_Scoroll(){
    window.parent.document.documentElement.scrollTop=0;
    window.parent.document.body.scrollTop=0;
}

/**
 *
 * @param {Object} focusId
 * @param {Object} buId
 */
function enterCom(focusId,buId){

    if($("#"+focusId).focus() && event.keyCode==13){
        //document.getElementById("gerenCommit").onclick();
        $("#"+buId).click();
        //$("#"+buId).submit();
    }
}


/**
 * 判断是否含有字母
 * @param {Object} str
 * @return {TypeName}
 */
function checkEn(str)
{
    var strSource ="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    var ch;
    var i;
    var temp;
    var flag=true;
    if(str.length==0){
        flag= false;
    }
    else{
        for (i=0;i<=(str.length);i++)
        {
            ch = str.charAt(i);
            temp = strSource.indexOf(ch);
            if (ch!=' '&&temp==-1)
            {
                flag=false;
            }
        }
    }

    return flag;
}

/**
 * 判断是否包含英文,包含则为true
 * @param {Object} str
 * @return {TypeName}
 */
function checkContaisEn(str)
{
    var strSource ="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    var ch;
    var i;
    var temp;
    var flag=true;
    if(str.length==0){
        flag= false;
    }
    else{
        for (i=0;i<=(str.length);i++)
        {
            ch = str.charAt(i);
            temp = strSource.indexOf(ch);
            if (ch!=' '&&temp>-1)
            {
                flag=true;
                break;
            }
        }
    }

    return flag;
}

/**
 * 为汉字返回true
 * @param {Object} val
 * @return {TypeName}
 */
function isHanZi(val){//onkeyup="value=value.replace(/[^\u4E00-\u9FA5]/g,'')"
    var reg = new RegExp("/[\u4E00-\u9FA5]/g");//  /[\u4E00-\u9FA5]/ [\\u4E00-\\u9FFF]+
    var flag=true;
    if(!reg.test(val)){
        flag=false;
    }else{
        for(var i=0;i<val.length;i++){
            if(!reg.test(val.charAt(i))){
                alert(val.charAt(i))
                flag=false;
            }
        }
    }
    if(checkEn(str)){

    }
    return flag;
}

/**
 * 为汉字返回true
 * @param {Object} val
 * @return {TypeName}
 */
function isHanZiNew(val){//onkeyup="value=value.replace(/[^\u4E00-\u9FA5]/g,'')"
    var reg = new RegExp("[\\u4E00-\\u9FFF]+","g");//  /[\u4E00-\u9FA5]/ [\\u4E00-\\u9FFF]+
    var flag=true;
    if(!reg.test(val)){
        flag=false;
    }else{
        for(var i=0;i<val.length;i++){
            if(val.charCodeAt(i)<=256){  //255
                flag=false;
            }
        }
    }


    return flag;
}

/**
 * 验证申请人名称
 * @param {Object} val
 * @return {TypeName}
 */
function isAppName(val){//onkeyup="value=value.replace(/[^\u4E00-\u9FA5]/g,'')"
    var reg = new RegExp("[\\u4E00-\\u9FFF]+","g");//  /[\u4E00-\u9FA5]/ [\\u4E00-\\u9FFF]+
    var flag=true;
    if(!reg.test(val)){
        flag=false;
    }else{
        for(var i=0;i<val.length;i++){
            if(val.charCodeAt(i)<=256){  //255
                flag=false;
            }
        }
    }
    //如果不包含英文也不是汉字
    if(flag==false){
        if(checkEn(val)){
            flag=true;
        }else{
            flag=false;
        }
    }

    return flag;
}

/**
 * 判断是否含有常用标点符号
 * @param {Object} str
 * @return {TypeName}
 */
function checkFuhao(str)
{
    var strSource =",./\?;:'!<>{}[]《》、()。，-“”";
    var ch;
    var i;
    var temp;
    var flag=true;
    if(str.length==0){
        flag= false;
    }
    else{
        for (i=0;i<=(str.length);i++)
        {
            ch = str.charAt(i);
            temp = strSource.indexOf(ch);
            if (ch !='' && temp==-1)
            {
                flag=false;
            }
        }
    }

    return flag;
}

/**
 * 判断是否含有常用标点符号
 * @param {Object} str
 * @return {TypeName}
 */
function checkEnFuhao(str)
{
    var strSource =",./\?;:'!<>{}[]《》、-()";
    var ch;
    var i;
    var temp;
    var flag=true;
    if(str.length==0){
        flag= false;
    }
    else{
        for (i=0;i<=(str.length);i++)
        {
            ch = str.charAt(i);
            temp = strSource.indexOf(ch);
            if (ch !='' && temp==-1)
            {
                flag=false;
            }
        }
    }

    return flag;
}


/**
 * 判断是否含有字母数字
 * @param {Object} str
 * @return {TypeName}
 */
function checkEnNum(str)
{
    var strSource ="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    var ch;
    var i;
    var temp;
    var flag=true;
    if(str.length==0){
        flag= false;
    }
    else{
        for (i=0;i<=(str.length);i++)
        {
            ch = str.charAt(i);
            temp = strSource.indexOf(ch);
            if (ch!=' '&&temp==-1)
            {
                flag=false;
            }
        }
    }

    return flag;
}

/**
 * 判断是否在数字/英文字母/汉字及常用标点符号中的一项
 * @param {Object} val
 * @return {TypeName}
 */
function isInputAll(str){
    var strSource ="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789,./\?;:'!<>{}[]+《》、-()&%*＇@。（）~ -：.・\"“”#";
    var ch;
    var i;
    var temp;
    var flag=true;
    var strs='';
    if(str.length==0){
        flag= false;
    }
    else{
        for (i=0;i<=(str.length);i++)
        {
            ch = str.charAt(i);
            temp = strSource.indexOf(ch);
            if (ch!=' '&&temp==-1)
            {
                strs+=ch;
            }
        }

        for(var i=0;i<strs.length;i++){
            if(strs.charCodeAt(i)<=256){
                flag=false;
            }
        }
    }

    return flag;
}

/**
 * 判断是否在数字/英文字母/汉字及常用标点符号中的一项
 * @param {Object} val
 * @return {TypeName}
 */
function isInputEn(str){
    var strSource ="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789,./\?;:'!<>{}[]+《》、-()@%&*＇。（）~- ：.\"’“”#";
    var ch;
    var i;
    var temp;
    var flag=true;
    if(str.length==0){
        flag= false;
    }
    else{
        for (i=0;i<=(str.length);i++)
        {
            ch = str.charAt(i);
            temp = strSource.indexOf(ch);
            if (ch!=' '&&temp==-1)
            {
                flag=false;
            }
        }
    }

    return flag;
}

