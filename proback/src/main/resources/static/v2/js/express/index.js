/*! 快递空包服务管理 韦德 2018年8月1日18:58:23*/
var route = "./express-platform";
var service;
var marketTableIndex;
(function () {
    service = initService(route);
    // 加载快递空包公司集合
    initPlatforms();

    // 加载市场进货渠道数据表
    initMarketDataTable(route + "/get", function (form, table, layer, vipTable, tableIns) {
        // 动态注册事件
        var $tableDelete = $("#market-data-table-delete"),
            $tableAdd = $("#market-data-table-add");
        $tableDelete.click(function () {
            layer.confirm('您确定要删除这些数据？', {
                title: "敏感操作提示",
                btn: ['确定','取消'],
                shade: 0.3,
                shadeClose: true
            },function () {
                var data = layui.table.checkStatus('dataCheck').data;
                var expIdArr = new Array();
                data.forEach(function (value) {
                    expIdArr.push(value.exp_id);
                });
                var param = {
                    exp_id: expIdArr.join(",")
                };
                service.delete(param, function (data) {
                    if(data.code == 1){
                        layer.msg("删除失败");
                        return
                    }
                    layer.msg("删除成功");
                    tableIns.reload();
                })
            })
        })
        $tableAdd.click(function () {
            service.getAddView(function (data) {
                layer.open({
                    type: 1,
                    skin: 'layui-layer-rim',
                    title: '添加',
                    area: ['420px', '240px'],
                    content: data
                });
            })
        })
    },function (table, res, curr, count) {
        // 监听工具条
        table.on('tool(market-data-table)', function(obj){
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的DOM对象

            if(layEvent === 'detail'){ //查看
                service.view(data,function (html) {
                    layer.open({
                        type: 1,
                        skin: 'layui-layer-rim',
                        title: '预览',
                        area: ['420px', '185px'],
                        content: html
                    });
                })
            } else if(layEvent === 'del'){ //删除
                layer.confirm('确定要删除此项吗？', function(index){
                    var param = {
                        exp_id: obj.data.exp_id.toString()
                    };
                    service.delete(param, function (data) {
                        if(data.code == 1){
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
                        title: '预览',
                        area: ['420px', '240px'],
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
 * @returns {{getAddView: getAddView, delete: delete, view: view}}
 */
function initService(r) {
    return {
        /**
         * 获取添加页视图 韦德 2018年8月1日22:13:35
         * @param callback
         */
        getAddView: function (callback) {
            $.get(r + "/addView",function (data) {
                callback(data);
            })
        },
        /**
         * 删除快递空包平台(支持多个) 韦德 2018年8月1日22:13:51
         * @param param
         * @param callback
         */
        delete: function (param, callback) {
            $.get(r + "/delete", param, function (data) {
                callback(data);
            });
        },
        /**
         * 预览快递空包平台信息 韦德 2018年8月1日22:14:17
         * @param param
         * @param callback
         */
        view: function (param, callback) {
            $.get(r + "/view", param, function (data) {
                callback(data);
            });
        },
        /**
         * 获取编辑页视图 韦德 2018年8月1日22:55:55
         * @param param
         * @param callback
         */
        getEditView: function (param, callback) {
            $.get(r + "/editView", param, function (data) {
                callback(data);
            });
        },
        /**
         * 获取平台集合 韦德 2018年8月2日11:03:30
         */
        getPlatforms: function (callback) {
            $.get(route + "/get?page=1&limit=30",function (data) {
                var json = data.data;
                callback(json);
            });
        }
    }
}

/**
 * 加载快递空包公司集合
 */
function initPlatforms(){
    layui.use(['tree'], function () {
        // 获取平台集合
        service.getPlatforms(function (data) {
            var nodes = eval(data);
            layui.tree({
                elem: '#tree'
                , click: function (item) {
                    // your code
                }
                , nodes: nodes
            });
        })
    });
}

/**
 * 加载市场进货渠道数据表
 */
function initMarketDataTable(url,callback,loadDone) {
    layui.use(['table', 'form', 'layer', 'vip_table', 'layedit', 'tree'], function () {
        // 操作对象
        var form = layui.form
            , table = layui.table
            , layer = layui.layer
            , vipTable = layui.vip_table
            , $ = layui.jquery
            , layedit = layui.layedit;

        // 表格渲染
        marketTableIndex = table.render({
            elem: '#market-data-table'                  //指定原始表格元素选择器（推荐id选择器）
            , height: 650    //容器高度
            , cols: [[
                {type: "checkbox"}
                , {field: 'expp_id', title: 'ID', width: 80}
                , {field: 'name', title: '空包公司名称', width: 120}
                , {field: 'url', title: '网址', width: 240}
                , {fixed: 'right', title: '操作', width: 150, align: 'center', toolbar: '#barOption'}
            ]]
            , id: 'market-data-table'
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
        $('#btn-refresh-market-data-table').on('click', function () {
            marketTableIndex.reload();
        });


        // you code ...
        callback(form, table, layer, vipTable, marketTableIndex);
    });
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
function loadTable(id,elem,cols,url,loadDone) {
    marketTableIndex.reload({
        elem: elem                 //指定原始表格元素选择器（推荐id选择器）
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
            loadDone(res, curr, count);
        }
    });
}