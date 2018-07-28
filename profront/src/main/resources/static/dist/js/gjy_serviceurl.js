var request_url = "http://192.168.0.101:8081/";

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