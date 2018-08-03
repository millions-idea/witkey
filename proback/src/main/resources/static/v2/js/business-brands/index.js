/*! 快递空包服务管理 韦德 2018年8月1日18:58:23*/
var route = "./business-brands";
var service;
var tableIndex;
(function () {
    service = initService(route);

    // 加载数据表
    initDataTable(route + "/getLimit", function (form, table, layer, vipTable, tableIns) {
        // 动态注册事件
        var $tableDelete = $("#my-data-table-delete"),
            $tableAdd = $("#my-data-table-add");
        $tableDelete.click(function () {
            layer.confirm('您确定要删除这些数据？', {
                title: "敏感操作提示",
                btn: ['确定','取消'],
                shade: 0.3,
                shadeClose: true
            },function () {
                var data = table.checkStatus('my-data-table').data;
                var idArr = new Array();
                data.forEach(function (value) {
                    idArr.push(value.business_id);
                });
                var param = {
                    id: idArr.join(",")
                };
                service.deleteBy(param, function (data) {
                    if(!isNaN(data.error) || data.code == 1){
                        layer.msg("删除失败");
                        return
                    }
                    layer.msg("删除成功");
                    tableIndex.reload();
                })
            })
        })
        $tableAdd.click(function () {
            service.getAddView(function (data) {
                layer.open({
                    type: 1,
                    skin: 'layui-layer-rim',
                    title: '添加',
                    area: ['420px', 'auto'],
                    shadeClose: true,
                    content: data
                });
            })
        })
    },function (table, res, curr, count) {
        // 监听工具条
        table.on('tool(my-data-table)', function(obj){
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的DOM对象

            if(layEvent === 'detail'){ //查看
                service.view(data,function (html) {
                    layer.open({
                        type: 1,
                        skin: 'layui-layer-rim',
                        title: '预览',
                        area: ['420px', 'auto'],
                        shadeClose:true,
                        content: html
                    });
                })
            } else if(layEvent === 'del'){ //删除
                layer.confirm('确定要删除此项吗？', function(index){
                    var param = {
                        id: obj.data.goods_id.toString()
                    };
                    service.deleteBy(param, function (data) {
                        if(!isNaN(data.error) || data.code == 1){
                            layer.msg("删除失败");
                            return
                        }
                        layer.msg("删除成功");
                        obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                        layer.close(index);
                    })
                });
            } else if(layEvent === 'edit'){ //编辑
                service.getEditView(data, function (html) {
                    layer.open({
                        type: 1,
                        skin: 'layui-layer-rim',
                        title: '编辑',
                        area: ['420px', 'auto'],
                        shadeClose:true,
                        content: html
                    });
                });
            }
        });
    });
})()

/**
 * 加载模块
 * @param r
 * @returns
 */
function initService(r) {
    return {
        /**
         * 获取添加视图 韦德 2018年8月3日23:29:26
         * @param callback
         */
        getAddView: function (callback) {
            $.get(r + "/addView",function (data) {
                callback(data);
            })
        },
        /**
         * 获取品牌 韦德 2018年8月3日22:54:11
         */
        getBrands: function (callback) {
            $.get(route + "/getLimit",function (data) {
                if(data.error != null && data.error == 1) return;
                if(data.code == 0){
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
        /**
         * 获取编辑页视图 韦德 2018年8月3日23:19:44
         * @param param
         * @param callback
         */
        getEditView: function (param, callback) {
            $.get(r + "/editView", param, function (data) {
                callback(data);
            });
        },
        /**
         * 编辑品牌 韦德 2018年8月3日23:21:55
         * @param param
         * @param callback
         */
        edit: function (param,callback) {
            $.post(route + "/edit", param , function (data) {
                callback(data);
            });
        },
        /**
         * 删除品牌 韦德 2018年8月3日23:26:11
         * @param param
         * @param callback
         */
        deleteBy: function (param, callback) {
            $.get(r + "/delete", param, function (data) {
                callback(data);
            });
        },
        /**
         * 添加品牌 韦德 2018年8月3日23:28:37
         * @param param
         * @param callback
         */
        add: function (param,callback) {
            $.post(route + "/add", param , function (data) {
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
function initDataTable(url, callback, loadDone) {
    var $queryButton = $("#my-data-table-query"),
        $queryCondition = $("#my-data-table-condition");

    var cols = getTableColumns();

    // 注册查询事件
    $queryButton.click(function () {
        $queryButton.attr("disabled",true);
        loadTable(tableIndex,"my-data-table", "#my-data-table", cols, url + "?condition=" + $queryCondition.val(), function (res, curr, count) {
            $queryButton.removeAttr("disabled");
        });
    })

    layui.use(['table', 'form', 'layer', 'vip_table', 'layedit', 'tree','element'], function () {
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
            , loading: false
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
        , {field: 'isEnable', title: '状态', width: 120, templet: function (d) {
                return d.isEnable != null  &&  d.isEnable == 1 ? "启用" : "禁用";
        }}
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
function loadTable(index,id,elem,cols,url,loadDone) {
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
        , loading: false
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
