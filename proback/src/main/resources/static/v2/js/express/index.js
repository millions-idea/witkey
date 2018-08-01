/*! 快递空包服务管理 韦德 2018年8月1日18:58:23*/



(function () {
    var service = initService("./express-platform");
    // 加载数据表
    initData('./../v2/json/express/index.json',function (form, table, layer, vipTable, tableIns) {
        // 动态注册事件
        var $tableDelete = $("#table-delete"),
            $tableAdd = $("#table-add");

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
    }, function (table, res, curr, count) {
        // 监听工具条
        table.on('tool(test)', function(obj){
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
        }
    }
}

/**
 * 加载数据表
 */
function initData(url,callback,loadDone) {


    layui.use(['table', 'form', 'layer', 'vip_table', 'layedit'], function () {

        // 操作对象
        var form = layui.form
            , table = layui.table
            , layer = layui.layer
            , vipTable = layui.vip_table
            , $ = layui.jquery
            , layedit = layui.layedit;

        // 表格渲染
        var tableIns = table.render({
            elem: '#dateTable'                  //指定原始表格元素选择器（推荐id选择器）
            , height: 650    //容器高度
            , cols: [[                  //标题栏
                {checkbox: true, sort: true, fixed: true, space: true}
                , {field: 'exp_id', title: 'ID', width: 80}
                , {field: 'platform_name', title: '平台名称', width: 120}
                , {field: 'platform_url', title: '平台网址', width: 240}
                , {fixed: 'right', title: '操作', width: 150, align: 'center', toolbar: '#barOption'} //这里的toolbar值是模板元素的选择器
            ]]
            , id: 'dataCheck'
            , url: url
            , method: 'get'
            , page: true
            , limits: [30, 60, 90, 150, 300]
            , limit: 30 //默认采用30
            , loading: false
            , done: function (res, curr, count) {
                //如果是异步请求数据方式，res即为你接口返回的信息。
                //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
                console.log(res);

                //得到当前页码
                console.log(curr);

                //得到数据总量
                console.log(count);

                loadDone(table, res, curr, count);
            }
        });


        // 刷新
        $('#btn-refresh').on('click', function () {
            tableIns.reload();
        });

        // you code ...
        callback(form, table, layer, vipTable, tableIns);
    });
}

