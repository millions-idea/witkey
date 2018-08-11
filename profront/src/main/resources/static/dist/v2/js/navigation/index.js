/*!导航模块 韦德 2018年8月11日11:17:18*/
var service = {
    /**
     * 获取试用列表
     * @param param
     * @param callback
     */
    getTryTypeList: function (param, callback) {
        $.request.get(request_url + "product/web/getTryTypeList", param, function (data) {
            callback(data);
        })
    }
}

$(function () {
    var $callTryCenterWindow = $("#publish-try-task"),
        $tryCenterWindow = $("#try-center-window"),
        $tryCenterItems = $("#try-center-items"),
        $taskListUl = $("ul[name='task-list']");

    // 加载试用列表
    service.getTryTypeList({}, function (data) {
        if(!isNaN(data.error) || (!isNaN(data.code) && data.code != 0)){
            layer.msg("加载试用列表失败");
            return;
        }
        var str = "";
        var taskListStr = "";
        data.data.forEach(function (value) {
            str += "<a href=\"/task/publish?type="+ value.id +"\">"+ value.type_name +"</a>";
            taskListStr += "<a title=\""+ value.type_name +"\" href=\"/task?type="+ value.id +"\">"+value.type_name+"</a>";
        })
        $tryCenterItems.html("");
        $tryCenterItems.append(str);
        $taskListUl.html("");
        $taskListUl.append(taskListStr);
    })

    // 绑定试用中心弹窗
    $callTryCenterWindow.click(function () {
        layer.open({
            type: 1,
            title: "请选择要发布的试用类型",
            area: ["480px", "230px"], //宽高
            shadeClose: true,
            content: $tryCenterWindow.html()
        });
    })


})