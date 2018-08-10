/*!通用ajax库 韦德 2018年8月9日10:57:32 */
$(function () {
    $.extend({
        request: {
            get: function(url,param,callback){
                var user = eval("(" + sessionStorage.getItem("member") + ")");
                $.ajax({
                    type: "GET",
                    url: url,
                    data: param,
                    beforeSend: function (xhr) {
                        xhr.setRequestHeader("mobile", user.mobile);
                        xhr.setRequestHeader("token", user.mobile);
                    },
                    success: callback
                });
            },
            post: function(url,param,callback){
                var user = eval("(" + sessionStorage.getItem("member") + ")");
                $.ajax({
                    type: "POST",
                    url: url,
                    data: param,
                    beforeSend: function (xhr) {
                        xhr.setRequestHeader("mobile", user.mobile);
                        xhr.setRequestHeader("token", user.mobile);
                    },
                    success: callback
                });
            }
        }
    })
})