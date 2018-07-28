//动态创建表单提交图片
function makeForm(number){
    var turnForm = document.createElement("form");
    //一定要加入到body中！！
    document.body.appendChild(turnForm);
    turnForm.method = 'post';
    turnForm.enctype = 'multipart/form-data';
    turnForm.action = '/fileup/fileUpload';
    turnForm.target = '_blank';
    //创建隐藏表单
    var id = Math.random();

    var newElement = document.createElement("input");
    newElement.setAttribute("name","file");
    newElement.setAttribute("type","file");
    newElement.setAttribute("id",id);
    turnForm.appendChild(newElement);
    //触发点击
    newElement.click();

    newElement.onchange = function (ev) {
        //这里唯一需要注意的就是这个form-add的id
        var formData = new FormData(turnForm);
        $.ajax({
            //接口地址
            url: request_url+'fileup/fileUpload' ,
            type: 'POST',
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (data) {
                //成功的回调
                if(data.error == '1'){
                    alert("上传图片大小不得超过5M")
                }else {
                    //显示上传成功的图片
                    switch (number) {
                        case 1:
                            $("#file1").attr("src",data.url);
                            $("#path1").val(data.path);
                            break;
                        case 2:
                            $("#file2").attr("src",data.url);
                            $("#path2").val(data.path);
                            break;
                        case 3:
                            $("#file3").attr("src",data.url);
                            $("#path3").val(data.path);
                            break;
                        case 4:
                            $("#file4").attr("src",data.url);
                            $("#path4").val(data.path);
                            break;
                        case 5:
                            $("#file5").attr("src",data.url);
                            $("#path5").val(data.path);
                            break;
                    }
                    //移除表单
                    document.body.removeChild(turnForm);
                }
            },
            error: function (returndata) {
                //请求异常的回调
                // modals.warn("网络访问失败，请稍后重试!");
            }
        });
    };

}