/*! 快递空包服务管理 韦德 2018年8月1日18:58:23*/
var route = "./express-platform";
var service;
var marketTableIndex,
    goodsTableIndex,
    ordersTableIndex;
$(function () {
    service = initService(route);

    // 切换tab键
    $("#tab-channel li").click(function () {
        var index = $(this).index();
        switch(index){
            case 0:
                // 加载默认数据表
                loadDefaultTable();
                break;
            case 1:
                // 加载商品数据表
                initGoodsDataTable(route + "/getGoods", function (form, table, layer, vipTable, tableIns) {
                    // 动态注册事件
                    var $tableDelete = $("#goods-data-table-delete"),
                        $tableAdd = $("#goods-data-table-add");
                    $tableDelete.unbind("click");
                    $tableAdd.unbind("click");
                    $tableDelete.bind('click',function () {
                        layer.confirm('您确定要删除这些数据？', {
                            title: "敏感操作提示",
                            btn: ['确定','取消'],
                            shade: 0.3,
                            shadeClose: true
                        },function () {
                            var data = table.checkStatus('goods-data-table').data;
                            var idArr = new Array();
                            data.forEach(function (value) {
                                idArr.push(value.goods_id);
                            });
                            var param = {
                                id: idArr.join(",")
                            };
                            service.deleteGoods(param, function (data) {
                                if(!isNaN(data.error) || data.code == 1){
                                    layer.msg("删除失败");
                                    return
                                }
                                layer.msg("删除成功");
                                goodsTableIndex.reload();
                            })
                        })
                    })
                    $tableAdd.bind('click',function () {
                        service.getAddGoodsView(function (data) {
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
                    table.on('tool(goods-data-table)', function(obj){
                        var data = obj.data; //获得当前行数据
                        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
                        var tr = obj.tr; //获得当前行 tr 的DOM对象

                        if(layEvent === 'detail'){ //查看
                            service.goodsView(data,function (html) {
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
                                service.deleteGoods(param, function (data) {
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
                            service.getGoodsEditView(data, function (html) {
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
                break;
            case 2:
                // 加载订单数据表
                initOrdersDataTable("/express-orders/get", function (form, table, layer, vipTable, tableIns) {
                    // 动态注册事件
                    var $tableDelete = $("#orders-data-table-delete"),
                        $tableAdd = $("#orders-data-table-add"),
                        $tableSend = $("#orders-data-table-send");
                    $tableDelete.unbind("click");
                    $tableAdd.unbind("click");
                    $tableSend.unbind("click");
                    $tableDelete.bind('click',function () {
                        layer.confirm('您确定要删除这些数据？', {
                            title: "敏感操作提示",
                            btn: ['确定','取消'],
                            shade: 0.3,
                            shadeClose: true
                        },function () {
                            var data = table.checkStatus('orders-data-table').data;
                            var idArr = new Array();
                            data.forEach(function (value) {
                                idArr.push(value.order_id);
                            });
                            var param = {
                                id: idArr.join(",")
                            };
                            service.deleteOrders(param, function (data) {
                                if(!isNaN(data.error) || data.code == 1){
                                    layer.msg("删除失败");
                                    return
                                }
                                layer.msg("删除成功");
                                ordersTableIndex.reload();
                            })
                        })
                    })
                    $tableAdd.bind('click',function () {
                        service.getAddGoodsView(function (data) {
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
                    $tableSend.bind('click',function () {
                        layer.confirm('您确定要对这些数据进行发货操作？', {
                            title: "敏感操作提示",
                            btn: ['确定','取消'],
                            shade: 0.3,
                            shadeClose: true
                        },function () {
                            var data = table.checkStatus('orders-data-table').data;

                            var modelList = new Array();

                            data.forEach(function (value) {
                                modelList.push({
                                    userId: value.user_id,
                                    goodsId: value.goods_id,
                                    orderId: value.order_id
                                });
                            });

                            service.editOrderStatuses(modelList, function (data) {
                                if(!isNaN(data.error) || data.code == 1){
                                    layer.msg("批量发货失败");
                                    return
                                }
                                layer.msg("批量发货成功");
                                ordersTableIndex.reload();
                            })
                        })
                    })
                },function (table, res, curr, count) {
                    // 监听工具条
                    table.on('tool(orders-data-table)', function(obj){
                        var data = obj.data; //获得当前行数据
                        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
                        var tr = obj.tr; //获得当前行 tr 的DOM对象

                        if(layEvent === 'detail'){ //查看
                            service.ordersView(data,function (html) {
                                layer.open({
                                    type: 1,
                                    skin: 'layui-layer-rim',
                                    title: '预览',
                                    area: ['750px', 'auto'],
                                    shadeClose:true,
                                    content: html
                                });
                            })
                        } else if(layEvent === 'del'){ //删除
                            layer.confirm('确定要删除此项吗？', function(index){
                                var param = {
                                    id: obj.data.order_id.toString()
                                };
                                service.deleteOrders(param, function (data) {
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
                            service.getOrdersEditView(data, function (html) {
                                layer.open({
                                    type: 1,
                                    skin: 'layui-layer-rim',
                                    title: '编辑',
                                    area: ['750px', 'auto'],
                                    shadeClose:true,
                                    content: html
                                });
                            });
                        }
                    });
                });
                break;
        }
    })

    // 加载快递空包公司集合
    initPlatforms(function (item) {
        // 空包商品
        if($("#tab-channel li[class$='layui-this']").index() == 1){
            loadTable(goodsTableIndex,"goods-data-table", "#goods-data-table", getGoodsTableColumns(), route + "/getGoods" + "?condition=" + item.name, function (res, curr, count){});
        }
    });

    // 加载默认数据表
    loadDefaultTable();
})

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
        deleteBy: function (param, callback) {
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
            $.get(route + "/get",function (data) {
                if(data.error != null && data.error == 1) return;
                if(data.code == 0){
                    callback(data.data);
                }
            });
        },
        /**
         * 添加 韦德 2018年8月2日15:37:00
         * @param callback
         */
        add: function (param, callback) {
            $.post(route + "/add", param , function (data) {
                callback(data);
            });
        },
        /**
         * 编辑 韦德 2018年8月2日16:52:55
         * @param param
         */
        edit: function (param,callback) {
            $.post(route + "/edit", param , function (data) {
                callback(data);
            });
        },
        /**
         * 预览快递空包商品信息 韦德 2018年8月3日10:36:57
         * @param param
         * @param callback
         */
        goodsView: function (param, callback) {
            $.get(r + "/goodsView", param, function (data) {
                callback(data);
            });
        },
        /**
         * 获取商品编辑页视图 韦德 2018年8月3日10:45:01
         * @param param
         * @param callback
         */
        getGoodsEditView: function (param, callback) {
            $.get(r + "/goodsEditView", param, function (data) {
                callback(data);
            });
        },
        /**
         * 获取品牌公司数据
         * @param param
         * @param callback
         */
        getBusinesses: function(param, callback) {
            $.get("/business-brands/get",param, function (data) {
                var arr = new Array();
                data.data.forEach(function(item){
                    if(item.isEnable == 1){
                        arr.push(item);
                    }
                })
                callback(arr);
            })
        },
        /**
         * 编辑商品 韦德 2018年8月3日15:50:40
         * @param param
         * @param callback
         */
        editGoods: function (param,callback) {
            $.post(route + "/editGoods", param , function (data) {
                callback(data);
            });
        },
        /**
         * 获取添加商品视图 韦德 2018年8月3日17:13:02
         * @param callback
         */
        getAddGoodsView: function (callback) {
            $.get(r + "/addGoodsView",function (data) {
                callback(data);
            })
        },
        /**
         * 添加商品 韦德 2018年8月3日17:13:12
         * @param param
         * @param callback
         */
        addGoods: function (param,callback) {
            $.post(route + "/addGoods", param , function (data) {
                callback(data);
            });
        },
        /**
         * 删除商品 韦德 2018年8月3日21:47:43
         * @param param
         * @param callback
         */
        deleteGoods: function (param, callback) {
            $.get(r + "/deleteGoods", param, function (data) {
                callback(data);
            });
        },
        /**
         * 预览订单 韦德 2018年8月4日16:37:04
         * @param param
         * @param callback
         */
        ordersView: function (param, callback) {
            $.get("./express-orders/view", param, function (data) {
                callback(data);
            });
        },
        /**
         * 编辑订单视图 韦德 2018年8月4日18:12:00
         * @param param
         * @param callback
         */
        getOrdersEditView: function (param, callback) {
            $.get("./express-orders/editView", param, function (data) {
                callback(data);
            });
        },
        /**
         * 编辑订单 韦德 2018年8月4日23:19:43
         * @param param
         * @param callback
         */
        editOrder: function (param,callback) {
            $.post("./express-orders/edit", param , function (data) {
                callback(data);
            });
        },
        /**
         * 编辑订单运单号 韦德 2018年8月4日23:25:49
         * @param param
         * @param callback
         */
        editOrderExpressId: function (param,callback) {
            $.post("./express-orders/editExpressId", param , function (data) {
                callback(data);
            });
        },
        /**
         * 编辑订单状态 韦德 2018年8月5日00:15:54
         * @param param
         * @param callback
         */
        editOrderStatuses: function (param, callback) {
            $.ajax({
                type:"post",
                url:"./express-orders/batchSendOut",
                data: JSON.stringify(param),
                contentType : "application/json",
                dataType:"json",
                success:function (data) {
                    callback(data);
                }
            });
        },
        editOrderStatus: function (param,callback) {
            $.post("./express-orders/sendOut", param , function (data) {
                callback(data);
            });
        },
        /**
         * 删除订单 韦德 2018年8月5日00:59:22
         * @param param
         * @param callback
         */
        deleteOrders: function (param,callback) {
            $.get("./express-orders/delete", param , function (data) {
                callback(data);
            });
        },
    }
}

/**
 * 加载快递空包公司集合
 */
function initPlatforms(clickFunc){
    layui.use(['tree'], function () {
        // 获取平台集合
        service.getPlatforms(function (data) {
            var nodes = eval(data);
            layui.tree({
                elem: '#tree'
                , click: function (item) {
                    $("#tree li").removeClass("li-active");
                    $("#tree li").each(function(i,o){
                        if($(o).find("cite").text() == item.name){
                            $(o).addClass("li-active");
                            return;
                        }
                    })
                   clickFunc(item);
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
        , {field: 'expp_id', title: 'ID', width: 80, sort: true}
        , {field: 'name', title: '空包公司名称', width: 120}
        , {field: 'url', title: '网址', width: 240}
        , {field: 'isEnable', title: '状态', width: 120, templet: function (d) {
                return d.isEnable != null  && d.isEnable == 1 ? "启用" : "禁用";
            }}
        , {title: '操作', width: 160, align: 'center', toolbar: '#barOption'}
    ]];

    // 注册查询事件
    $queryButton.unbind('click');
    $queryButton.bind('click',function () {
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
            , loading: true
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

/**
 * 加载商品数据表
 * @param url
 * @param callback
 * @param loadDone
 */
function initGoodsDataTable(url, callback, loadDone) {
    var $queryButton = $("#goods-data-table-query"),
        $queryCondition = $("#goods-data-table-condition");

    var cols = getGoodsTableColumns();

    // 注册查询事件
    $queryButton.unbind('click');
    $queryButton.bind('click',function () {
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
            , loading: true
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

/**
 * 加载订单数据表
 * @param url
 * @param callback
 * @param loadDone
 */
function initOrdersDataTable(url, callback, loadDone) {
    var $queryButton = $("#orders-data-table-query"),
        $queryCondition = $("#orders-data-table-condition");

    var cols = getOrdersTableColumns();

    // 注册查询事件
    $queryButton.unbind('click');
    $queryButton.bind('click',function () {
        $queryButton.attr("disabled",true);
        loadTable(ordersTableIndex,"orders-data-table", "#orders-data-table", cols, url + "?condition=" + $queryCondition.val(), function (res, curr, count) {
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
        ordersTableIndex = table.render({
            elem: '#orders-data-table'                  //指定原始表格元素选择器（推荐id选择器）
            , height: 650    //容器高度
            , cols: cols
            , id: 'orders-data-table'
            , url: url
            , method: 'get'
            , page: true
            , limits: [30, 60, 90, 150, 300]
            , limit: 30 //默认采用30
            , loading: true
            , even: true
            , done: function (res, curr, count) {
                resetPager();
                loadDone(table, res, curr, count);
            }
        });

        // 刷新
        $('#btn-refresh-orders-data-table').on('click', function () {
            ordersTableIndex.reload();
        });

        //监听单元格编辑
        table.on('edit(orders-data-table)', function(obj){
            var value = obj.value //得到修改后的值
                ,data = obj.data //得到所在行所有键值
                ,field = obj.field; //得到字段
            service.editOrderExpressId({
                order_id: data.order_id,
                user_id: data.user_id,
                express_id: value
            },function (data) {
                if(!isNaN(data.error) || data.code == 1){
                    layer.msg("编辑失败");
                    return
                }
                layer.msg("编辑成功");
            })
        });

        // 开关监听
        form.on("switch(item-status)",function (obj) {
            if(!$(obj.othis).hasClass("layui-form-onswitch")){
                return false;
            }
            service.editOrderStatus({
                order_id: $(this).data("order_id"),
                goods_id: $(this).data("goods_id"),
                user_id: $(this).data("user_id")
            },function (data) {
                if(!isNaN(data.error) || data.code == 1){
                    layer.msg("编辑失败");
                    return;
                }
                layer.msg("编辑成功，如需查看最新数据，请点击刷新按钮");
            })
        });

        // you code ...
        callback(form, table, layer, vipTable, ordersTableIndex);
    });
}

function resetPager() {
    $(".layui-table-body.layui-table-main").each(function (i, o) {
        $(o).height(569);
    });
}

/**
 * 获取商品表格列属性
 * @returns {*[]}
 */
function getGoodsTableColumns() {
    return [[
        {type: "numbers"}
        , {type: "checkbox"}
        , {field: 'goods_id', title: 'ID', width: 80, sort: true}
        , {field: 'expp_name', title: '空包网站', width: 120}
        , {field: 'category_name', title: '电商网站', width: 120}
        , {field: 'name', title: '名称', width: 240}
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
        , {title: '操作', width: 160, align: 'center', toolbar: '#barOption'} //这里的toolbar值是模板元素的选择器
    ]];
}

/**
 * 获取订单表格列属性
 * @returns {*[]}
 */
function getOrdersTableColumns() {
    return [[
        {fixed: 'left', type: "numbers"}
        , {fixed: 'left', type: "checkbox"}
        , {fixed: 'left', field: 'order_id', title: 'ID', width: 80, sort: true}
        , {fixed: 'left', field: 'username', title: '用户名', width: 120}
        , {fixed: 'left', field: 'amount', title: '价格', width: 80}
        , {field: 'real_name', title: '真实姓名', width: 120}
        , {field: 'phone', title: '手机号', width: 120}
        , {field: 'goods_name', title: '快递', width: 120}
        , {field: 'express_id', title: '运单号', width: 160, edit: 'text'}
        , {field: 'weight', title: '重量', width: 80}
        , {field: 'status', title: '订单状态', width: 120, templet: function (d) {
                var str = "待发货";
                switch (d.status){
                    case 0:
                        str = "<span style='color:#3337d4;'>" + "待发货" + "</span>"
                        break;
                    case 1:
                        str = "<span style='color:#009b1a;'>" + "已发货" + "</span>"
                        break;
                    case 2:
                        str = "<span style='color:#000;'>" + "已拒绝" + "</span>"
                        break;
                    case 3:
                        str = "<span style='color:#e83535;'>" + "已取消" + "</span>"
                        break;
                    default:
                        str = "未知";
                        break;
                }
                return str;
            }}
        , {field: 'orders_remark', title: '订单备注', width: 240}
        , {field: 'recv_address', title: '收货地址', width: 340}
        , {field: 'address_remark', title: '备注', width: 240}
        , {field: 'send_address', title: '发货地址', width: 340}
        , {field: 'edit_date', title: '最后更新时间', width: 240, templet: function (d) {
                return new Date(d.edit_date).format('yyyy-MM-dd hh:mm:ss');
        }}
        , {field: 'isEnable', fixed: 'right', align:"center", title: '状态', width: 80, templet: function (d) {
                return d.isEnable != null  &&  d.isEnable == 1 ? "启用" : "禁用";
            }}
        , {fixed: 'right', title: '快捷操作', width: 150, align: 'center', templet: function (obj) {
                return getSendOutButton(obj);
            }} //这里的toolbar值是模板元素的选择器
        , {fixed: 'right', title: '操作', width: 160, align: 'center', toolbar: '#barOption'} //这里的toolbar值是模板元素的选择器
    ]];
}

/**
 * 加载默认表格
 */
function loadDefaultTable() {
    // 加载市场进货渠道数据表
    initMarketDataTable(route + "/getLimit", function (form, table, layer, vipTable, tableIns) {
        // 动态注册事件
        var $tableDelete = $("#market-data-table-delete"),
            $tableAdd = $("#market-data-table-add");
        $tableDelete.unbind("click");
        $tableAdd.unbind("click");
        $tableDelete.bind('click',function () {
            layer.confirm('您确定要删除这些数据？', {
                title: "敏感操作提示",
                btn: ['确定','取消'],
                shade: 0.3,
                shadeClose: true
            },function () {
                var data = table.checkStatus('market-data-table').data;
                var expIdArr = new Array();
                data.forEach(function (value) {
                    expIdArr.push(value.expp_id);
                });
                var param = {
                    expp_id: expIdArr.join(",")
                };
                service.deleteBy(param, function (data) {
                    if(!isNaN(data.error) || data.code == 1){
                        layer.msg("删除失败");
                        return;
                    }
                    layer.closeAll();
                    marketTableIndex.reload();
                })
            })
        })
        $tableAdd.bind('click',function () {
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
                        shadeClose:true,
                        content: html
                    });
                })
            } else if(layEvent === 'del'){ //删除
                layer.confirm('确定要删除此项吗？', function(index){
                    var param = {
                        id: obj.data.expp_id.toString()
                    };
                    service.deleteBy(param, function (data) {
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
                        title: '编辑',
                        area: ['420px', 'auto'],
                        shadeClose:true,
                        content: html
                    });
                });
            }
        });
    });
}

/**
 * 获取发货按钮
 */
function getSendOutButton(param) {
    var html = " <input type=\"checkbox\" name=\"item-status\" lay-filter=\"item-status\"\n" +
        "               data-order_id=\"" + param.order_id + "\"\n" +
        "               data-goods_id=\"" + param.goods_id + "\"\n" +
        "               data-user_id=\"" + param.user_id + "\"\n";
    if(param.status == 1){
        html +=         "               lay-skin=\"switch\" lay-text=\"已发货|待发货\"\n";
        html += " checked";
    }else if (param.status == 2){
        html +=         "               lay-skin=\"switch\" lay-text=\"允许操作|禁止操作\"\n";
        html += " disabled";
    }else if (param.status == 3){
        html +=         "               lay-skin=\"switch\" lay-text=\"允许操作|禁止操作\"\n";
        html += " disabled";
    }else{
        html +=         "               lay-skin=\"switch\" lay-text=\"已发货|待发货\"\n";
    }
    html += ">";
    return html;
}