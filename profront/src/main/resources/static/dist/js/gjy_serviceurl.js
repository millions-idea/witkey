var request_url = "http://192.168.169.151:8081/";

var img_prefix_url = "http://192.168.1.103:8081/images/upload/";

//获取全局配置 网站名称 Logo等
var get_setting_url = request_url+ "intf_setting/getSetting";
//任务领取
var pick_task_url = request_url +"intf_shop/pickTask";

//任务领取
var user_task_list = request_url +"intf_shop/getUserApplyTaskList";

//任务开始
var user_task_start = request_url +"intf_shop/getUserTaskStep";

//用户信息
var userresult_json = eval("("+sessionStorage.getItem("member")+")");

//获取URL地址栏参数
function GetRequest() {
    var url = location.search; //获取url中"?"符后的字串
    var theRequest = new Object();
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        strs = str.split("&");
        for(var i = 0; i < strs.length; i ++) {
            theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);
        }
    }
    return theRequest;
}

function num(obj){
    //onkeyup='this.value=this.value.replace(/\D/gi,"")'  只让输入数字
    obj.value = obj.value.replace(/[^\d.]/g,""); //清除"数字"和"."以外的字符
    obj.value = obj.value.replace(/^\./g,""); //验证第一个字符是数字
    obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个, 清除多余的
    obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
    obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3'); //只能输入两个小数
}

//字符串以xxx开头
String.prototype.startWith=function(str){
    var reg=new RegExp("^"+str);
    return reg.test(this);
}

//字符串以xxx结尾
String.prototype.endWith=function(str){
    var reg=new RegExp(str+"$");
    return reg.test(this);
}

//storage操作
function setStorage(key,value){
    sessionStorage.setItem(key,value);
}
function getStorage(key){
    sessionStorage.getItem(key);
}
//全局
function putStorage(key,value){
    localStorage.setItem(key,value);
}
function pullStorage(key){
    localStorage.getItem(key);
}

/**
 * 是否为空
 * @param param
 */
function isNull(param){
    return param==undefined || param==null || param=='';
}