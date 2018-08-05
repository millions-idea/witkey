var route = "/system/tixian";
var service;
var tableIndex;
(function () {
    service = initService(route);
    service.getEditView();
})()

/**
 * 加载模块
 * @param r
 * @returns
 */
function initService(r) {
    return {

        /**
         * 获取品牌 韦德 2018年8月3日22:54:11
         */
        getSetting: function (callback) {
            $.get(route, function (data) {
                if (data.error != null && data.error == 1) return;
                if (data.code == 0) {
                    callback(data.data);
                }
            });
        },
        /**
         * 预览品牌信息 韦德 2018年8月3日23:10:46
         * @param param
         * @param callback
         */
        view: function (param, callback) {
            $.get(r + "/view", param, function (data) {
                callback(data);
            });
        },
        getEditView: function (param, callback) {
            $.get(r, function (data) {
                callback(data);
            });
        },
        /**
         * 编辑品牌 韦德 2018年8月3日23:21:55
         * @param param
         * @param callback
         */
        edit: function (param, callback) {
            $.post(route + "/edit", param, function (data) {
                callback(data);
            });
        },
    }
}

/**
 * 加载数据表
 * @param url
 * @param callback
 * @param loadDone
 */
function initSettingData(url, callback, loadDone) {
    var $queryButton = $("#my-data-table-query"),
        $queryCondition = $("#my-data-table-condition");

    var cols = getTableColumns();

    // 注册查询事件
    $queryButton.click(function () {
        $queryButton.attr("disabled", true);
        loadTable(tableIndex, "my-data-table", "#my-data-table", cols, url + "?condition=" + $queryCondition.val(), function (res, curr, count) {
            $queryButton.removeAttr("disabled");
        });
    })

    layui.use(['table', 'form', 'layer', 'vip_table', 'layedit', 'tree', 'element'], function () {
        // 操作对象
        var form = layui.form
            , table = layui.table
            , layer = layui.layer
            , vipTable = layui.vip_table
            , $ = layui.jquery
            , layedit = layui.layedit
            , element = layui.element;

        // 表格渲染
        tableIndex = table.render({
            elem: '#my-data-table'                  //指定原始表格元素选择器（推荐id选择器）
            , height: 650    //容器高度
            , cols: cols
            , id: 'my-data-table'
            , url: url
            , method: 'get'
            , page: true
            , limits: [30, 60, 90, 150, 300]
            , limit: 30 //默认采用30
            , loading: true
            , even: true
            , done: function (res, curr, count) {
                loadDone(table, res, curr, count);
            }
        });

        // 刷新
        $('#btn-refresh-my-data-table').on('click', function () {
            tableIndex.reload();
        });

        // you code ...
        callback(form, table, layer, vipTable, tableIndex);
    });
}

/**
 * 获取商品表格列属性
 * @returns {*[]}
 */
function getTableColumns() {
    return [[
        {type: "numbers"}
        , {type: "checkbox"}
        , {field: 'business_id', title: 'ID', width: 80, sort: true}
        , {field: 'name', title: '电商网站', width: 120}
        , {
            field: 'isEnable', title: '状态', width: 120, templet: function (d) {
                return d.isEnable != null && d.isEnable == 1 ? "启用" : "禁用";
            }
        }
        , {fixed: 'right', title: '操作', width: 150, align: 'center', toolbar: '#barOption'} //这里的toolbar值是模板元素的选择器
    ]];
}


/**
 * 加载表格数据
 * @param tableIns
 * @param id
 * @param elem
 * @param cols
 * @param url
 * @param loadDone
 */
function loadTable(index, id, elem, cols, url, loadDone) {
    index.reload({
        elem: elem
        , height: 650    //容器高度
        , cols: cols
        , id: id
        , url: url
        , method: 'get'
        , page: true
        , limits: [30, 60, 90, 150, 300]
        , limit: 30 //默认采用30
        , loading: true
        , even: true
        , done: function (res, curr, count) {
            resetPager();
            loadDone(res, curr, count);
        }
    });
}

function resetPager() {
    $(".layui-table-body.layui-table-main").each(function (i, o) {
        $(o).height(569);
    });
}
