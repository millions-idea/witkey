var route = "/member";
var service;
var tableIndex;
(function () {
    service = initService(route);

    // 加载数据表
    initDataTable("/member/toAuthBank", function (form, table, layer, vipTable, tableIns) {
        // 动态注册事件
        var $tableDelete = $("#my-data-table-delete");
        var  $tableReject = $("#my-data-table-reject");
        var $rejectReason = $("#reject-reason");

        //批量通过实名验证
        $tableDelete.click(function () {
            layer.confirm('您确定要通过这些认证？', {
                title: "敏感操作提示",
                btn: ['确定','取消'],
                shade: 0.3,
                shadeClose: true
            },function () {
                var data = table.checkStatus('my-data-table').data;
                var idArr = new Array();
                data.forEach(function (value) {
                    idArr.push(value.id);
                });
                var param = {
                    id: idArr.join(",")
                };
                service.deleteBy(param, function (data) {
                    if(!isNaN(data.error) || data.code == 1){
                        layer.msg("更新失败");
                        return;
                    }
                    layer.msg("更新成功");
                    tableIndex.reload();
                })
            })
        })

        $tableReject.click(function () {
            layer.confirm('您确定要拒绝这些认证？', {
                title: "敏感操作提示",
                btn: ['确定','取消'],
                shade: 0.3,
                shadeClose: true
            },function () {
                var data = table.checkStatus('my-data-table').data;
                var idArr = new Array();
                var reason = $rejectReason.val();

                data.forEach(function (value) {
                    idArr.push(value.id);
                });
                var param = {
                    id: idArr.join(","),
                    reason:reason
                };
                service.rejectBy(param, function (data) {
                    if(!isNaN(data.error) || data.code == 1){
                        layer.msg("更新失败");
                        return;
                    }
                    layer.msg("更新成功");
                    tableIndex.reload();
                })
            })
        })

    },function (table, res, curr, count) {
        // 监听工具条
        table.on('tool(my-data-table)', function(obj){
            var data = obj.data; //获得当前行数据
            //清除时间
            data.create_time = null;

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
            } else if(layEvent === 'del'){
                //拒绝
                var param = {};
                param.id = data.id;
                service.rejectBy(param, function (data) {
                    if(!isNaN(data.error) || data.code == 1){
                        layer.msg("更新失败");
                        return;
                    }
                    layer.msg("更新成功");
                    tableIndex.reload();
                })
            } else if(layEvent === 'edit'){
                //通过
                var param = {};
                param.id = data.id;

                service.deleteBy(param, function (data) {
                    if(!isNaN(data.error) || data.code == 1){
                        layer.msg("更新失败");
                        return;
                    }
                    layer.msg("更新成功");
                    tableIndex.reload();
                })
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
        getBrands: function (callback) {
            $.get(route + "/getLimit",function (data) {
                if(data.error != null && data.error == 1) return;
                if(data.code == 0){
                    callback(data.data);
                }
            });
        },
        view: function (param, callback) {
            $.get(r + "/viewBankDetail", param, function (data) {
                callback(data);
            });
        },
        /**
         * 审核通过
         */
        deleteBy: function (param, callback) {

            $.get(r + "/agreeMemberBank", param, function (data) {
                callback(data);
            });
        },

        /**
         * 审核拒绝
         */
        rejectBy: function (param, callback) {
            $.get(r + "/rejectMemberBank", param, function (data) {
                callback(data);
            });
        },

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
        //搜索值
        $queryCondition = $("#my-data-table-condition");
        //性别
        var $gender = $("#gender");
        //搜索条件
        var $cond = $("#cond");
        //是否认证
        var is_auth = $("#is_auth");

    var cols = getTableColumns();

    // 注册查询事件
    $queryButton.click(function () {
        $queryButton.attr("disabled",true);
        loadTable(tableIndex,"my-data-table", "#my-data-table", cols,
            url +
            "?cond="+$cond.val()+"&content=" + $queryCondition.val()
            +"&is_auth=" + is_auth.val(),
            function (res, curr, count) {
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
        , {field: 'id', title: '会员ID', width: 80, sort: true}
        , {field: 'bank_username', title: '姓名', width: 80}
        , {field: 'bank_id_card', title: '身份证号码', width: 180}
        , {field: 'bank_type', title: '银行类型', width: 100}
        , {field: 'bank_num', title: '银行卡号', width: 120}
        , {field: 'bank_mobile', title: '银行手机号', width: 120}
        , {field: 'create_time', title: '提交时间', width: 120}

        , {field: 'isEnable', title: '状态', width: 120, templet: function (d) {

            var  result = '';
            if (d.is_auth != null && d.is_auth == 0) {
                result = '未认证';
            }else if(d.is_auth != null && d.is_auth == 1){
                result = '已认证';
            }else if(d.is_auth != null && d.is_auth == 2){
                result = '已拒绝';
            }
                return result;
        }}
        , {fixed: 'right', title: '操作',
            width: 150, align: 'center',
            toolbar: '#barOption'} //这里的toolbar值是模板元素的选择器
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
