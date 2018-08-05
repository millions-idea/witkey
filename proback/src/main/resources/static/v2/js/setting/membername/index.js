var route = "/member";
var service;
var tableIndex;
(function () {
    service = initService(route);

})()

/**
 * 加载模块
 * @param r
 * @returns
 */
function initService(r) {
    return {
        edit: function (param,callback) {
            $.post(route + "/forbidden", param , function (data) {
                callback(data);
            });
        }
    }
}

function resetPager() {
    $(".layui-table-body.layui-table-main").each(function (i, o) {
        $(o).height(569);
    });
}
