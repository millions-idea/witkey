/*! 快递空包服务管理 韦德 2018年8月1日18:58:23*/
var route = "./express-platform";
var service;
var marketTableIndex,
    goodsTableIndex;
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
                    area: ['420px', 'auto'],
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
                        area: ['420px', 'auto'],
                        content: html
                    });
                })
            } else if(layEvent === 'del'){ //删除
                layer.confirm('确定要删除此项吗？', function(index){
                    var param = {
                        exp_id: obj.data.expp_id.toString()
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
                        area: ['420px', 'auto'],
                        content: html
                    });
                });
            }
        });
    });

    // 加载销售分类数据表
    initGoodsDataTable(route + "/getGoods", function (form, table, layer, vipTable, tableIns) {
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
                    area: ['420px', 'auto'],
                    content: data
                });
            })
        })
    },function (table, res, curr, count) {
        // 监听工具条
        table.on('tool(goods-data-table)', function(obj){
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
                        content: html
                    });
                })
            } else if(layEvent === 'del'){ //删除
                layer.confirm('确定要删除此项吗？', function(index){
                    var param = {
                        exp_id: obj.data.expp_id.toString()
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
                        area: ['420px', 'auto'],
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
                if(data.error != null && data.error == 1) return;
                if(data.code == 0){
                    var arr = new Array();
                    data.data.forEach(function(item){
                        if(item.isEnable == 1){
                            arr.push(item);
                        }
                    })
                    callback(arr);
                }
            });
        },
        /**
         * 添加 韦德 2018年8月2日15:37:00
         * @param callback
         */
        add: function (param) {
            $.post(route + "/add", param , function (data) {
                if(data.code == 1) {
                    layer.msg("添加失败");
                    return false;
                }
                layer.closeAll();
                marketTableIndex.reload();
            });
        },
        /**
         * 编辑 韦德 2018年8月2日16:52:55
         * @param param
         */
        edit: function (param) {
            $.post(route + "/edit", param , function (data) {
                if(data.code == 1) {
                    layer.msg("编辑失败");
                    return false;
                }
                layer.closeAll();
                marketTableIndex.reload();
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
    var $queryButton = $("#market-data-table-query"),
        $queryCondition = $("#market-data-table-condition");

    var cols = [[
        {type: "numbers"}
        , {type: "checkbox"}
        , {field: 'expp_id', title: 'ID', width: 80}
        , {field: 'name', title: '空包公司名称', width: 120}
        , {field: 'url', title: '网址', width: 240}
        , {field: 'isEnable', title: '状态', width: 120, templet: function (d) {
                return d.isEnable != null  && d.isEnable == 1 ? "启用" : "禁用";
            }}
        , {fixed: 'right', title: '操作', width: 150, align: 'center', toolbar: '#barOption'}
    ]];

    // 注册查询事件
    $queryButton.click(function () {
        $queryButton.attr("disabled",true);
        loadTable(marketTableIndex,"market-data-table", "#market-data-table", cols, url + "?condition=" + $queryCondition.val(), function (res, curr, count) {
            $queryButton.removeAttr("disabled");
        });
    })

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
            , cols: cols
            , id: 'market-data-table'
            , url: url
            , method: 'get'
            , page: true
            , limits: [30, 60, 90, 150, 300]
            , limit: 30 //默认采用30
            , loading: false
            , even: true
            , done: function (res, curr, count) {
                resetPager();
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
function loadTable(index,id,elem,cols,url,loadDone) {
    index.reload({
        elem: '#goods-data-table'
        , height: 650    //容器高度
        , cols: cols
        , id: 'goods-data-table'
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

/**
 * 加载销售分类数据表
 * @param url
 * @param callback
 * @param loadDone
 */
function initGoodsDataTable(url, callback, loadDone) {
    var $queryButton = $("#goods-data-table-query"),
        $queryCondition = $("#goods-data-table-condition");

    var cols = [[
        {type: "numbers"}
        , {type: "checkbox"}
        , {field: 'goods_id', title: 'ID', width: 80}
        , {field: 'category_name', title: '电商公司', width: 120}
        , {field: 'name', title: '物流公司', width: 120}
        , {field: 'price', title: '进价', width: 120}
        , {field: 'rate', title: '利率', width: 120, templet: function (d) {
                var rate = d.rate;
                return rate + "%";
            }}
        , {field: 'sell_price', title: '销售价', width: 120, templet: function (d) {
                return d.price * (100 + d.rate) / 100;
            }}
        , {field: 'isEnable', title: '状态', width: 120, templet: function (d) {
                return d.isEnable != null  &&  d.isEnable == 1 ? "启用" : "禁用";
            }}
        , {fixed: 'right', title: '操作', width: 150, align: 'center', toolbar: '#barOption'} //这里的toolbar值是模板元素的选择器
    ]];

    // 注册查询事件
    $queryButton.click(function () {
        $queryButton.attr("disabled",true);
        loadTable(goodsTableIndex,"goods-data-table", "#goods-data-table", cols, url + "?condition=" + $queryCondition.val(), function (res, curr, count) {
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
        goodsTableIndex = table.render({
            elem: '#goods-data-table'                  //指定原始表格元素选择器（推荐id选择器）
            , height: 650    //容器高度
            , cols: cols
            , id: 'goods-data-table'
            , url: url
            , method: 'get'
            , page: true
            , limits: [30, 60, 90, 150, 300]
            , limit: 30 //默认采用30
            , loading: false
            , even: true
            , done: function (res, curr, count) {
                resetPager();
                loadDone(table, res, curr, count);
            }
        });

        // 刷新
        $('#btn-refresh-goods-data-table').on('click', function () {
            goodsTableIndex.reload();
        });

        // you code ...
        callback(form, table, layer, vipTable, goodsTableIndex);
    });
}


function resetPager() {
    $(".layui-table-body.layui-table-main").each(function (i, o) {
        $(o).height(569);
    });
}
