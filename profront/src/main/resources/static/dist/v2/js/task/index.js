//"../dist/v2/json/product-categorys/index.json"
var service = {
    /**
     * 获取商品类目
     * @param param
     * @param callback
     */
    getCategoryList: function (param, callback) {
        $.request.get(request_url + "product-category/web/get", param, function (data) {
            callback(data);
        })
    },
    /**
     * 获取商品列表
     * @param param
     * @param callback
     */
    getProductList: function (param, callback) {
        $.request.get("../dist/v2/json/product/index.json", param, function (data) {
            callback(data);
        })
    },
    /**
     * 获取买号列表
     * @param param
     * @param callback
     */
    getBuyAccountList: function (param, callback) {
        $.request.get("../dist/v2/json/buy-accounts/index.json", param, function (data) {
            callback(data);
        })
    }
}

$(function () {
    // 绑定焦点导航元素
    set_nav("yptask", "on");

    // 初始化UI框架
    initLayui();

    // 加载类目
    service.getCategoryList({}, function (data) {
        if(!isNaN(data.error) || (!isNaN(data.code) && data.code != 0)){
            layer.msg("加载类目失败");
            return;
        }
        ReactDOM.render(<CategoryNavigationView list={data.data} />, document.getElementById("category-list"), function () {
            // 动态切换排序焦点
            var req = GetRequest(),
                $orderByButtons = $("a[name='orderBy']")

            if(req["orderBy"] != null && req["orderBy"] == 0){
                $orderByButtons.removeClass("act");
                $orderByButtons.eq(0).addClass("act");
            }
            if(req["orderBy"] != null && req["orderBy"] == 1){
                $orderByButtons.removeClass("act");
                $orderByButtons.eq(1).addClass("act");
            }
            if(req["orderBy"] != null && req["orderBy"] == 2){
                $orderByButtons.removeClass("act");
                $orderByButtons.eq(2).addClass("act");
            }

            bindCategoryEvents();
        })

    })


    // 加载商品列表
    service.getProductList({},function (data) {
        if(!isNaN(data.error) || (!isNaN(data.code) && data.code != 0)){
            layer.msg("加载商品失败");
            return;
        }
        ReactDOM.render(<ProductDataListView list={data.data} />, document.getElementById("product-list"), function () {
            bindProductEvents();

            var $applyButton = $("a[name='apply']"),
                $selectApplyButton = $("button[name='apply-icon']");

            // 申请试用
            $applyButton.unbind('click').bind('click',function () {
                layer.open({
                    type: 1,
                    title: "请从下面选择一个买号",
                    area: ["auto", "auto"], //宽高
                    shadeClose: true,
                    content: $("#apply-html").html()
                });

            })
        })
    });

    // 加载买号列表
    service.getBuyAccountList({}, function (data) {
        if(!isNaN(data.error) || (!isNaN(data.code) && data.code != 0)){
            layer.msg("加载买号列表失败");
            return;
        }
        ReactDOM.render(<BuyAccountDataTableView list={data.data} />, document.getElementById("apply-html"))

    })
})

/**
 * 初始化layui的组件包
 */
function initLayui() {
    layui.use(['laypage', 'layer'], function(){
        var laypage = layui.laypage
            ,layer = layui.layer;

        //自定义样式
        laypage.render({
            elem: 'product-limit'
            ,count: 100
            ,limit: 20
            ,theme: '#1E9FFF'
            ,layout: ['count','prev', 'page', 'next',  'skip']
            ,hash: true
            ,jump: function(obj, first){
                //obj包含了当前分页的所有参数，比如：
                console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
                console.log(obj.limit); //得到每页显示的条数

                //首次不执行
                if(!first){
                    //do something
                    layer.msg(obj.curr);
                }
            }
        });

    });
}

/**
 * 页面加载分类完成时绑定的事件
 */
function bindCategoryEvents() {
    // 绑定切换视图
    var switch_grid = get_cookie('switch_grid');
    if (switch_grid == 0) {
        $('#taskList').addClass('shiyong');
        $("[data-qtip-img]").qtip({position: {my: 'center bottom', at: 'top center', adjust: {x: 10}}, style: {classes: 'qtip-bootstrap'}});
    }

    // 自动刷新
    var autorefresh = getCookie('autorefresh');
    var autorefreshid = 0;
    if (autorefresh == 1) {
        $('#autorefresh').text('取消自动刷新').attr('title', '点击取消自动刷新');
        autorefreshid = setTimeout('location.reload()', 10000);
    }

    // 切换视图
    $("#active-view").click(function () {
        $('#taskList').toggleClass('shiyong');
        setTimeout(function () {
            if ($('#taskList').hasClass('shiyong')) {
                set_cookie('switch_grid', '');
                $("[data-qtip-img]").qtip({position: {my: 'center bottom', at: 'top center', adjust: {x: 10}}, style: {classes: 'qtip-bootstrap'}});
            } else {
                set_cookie('switch_grid', 1);
                qtip_init();
            }
        }, 100);
    })

    // 自动刷新
    var autorefreshid = 0;
    $("#autorefresh").click(function () {
        if ($('#autorefresh').text() == '自动刷新') {
            setCookie('autorefresh', 1);
            location.reload();
        } else {
            if (autorefreshid) clearInterval(autorefreshid);
            setCookie('autorefresh', 0);
            $('#autorefresh').text('自动刷新').attr('title', '点击自动刷新');
        }
    })

    // 绑定小弹窗事件
    bindTipEvent();
}

/**
 * 绑定小弹窗事件
 */
function bindTipEvent() {
    // 使用要求小弹窗
    var tipsIndex;
    $("*[oldtitle]").hover(function () {
        if(!$(this).hasClass("tips")  &&  $(this)[0].tagName == "A") return;
        $(this).attr("oldtitle")
        tipsIndex = layer.tips($(this).attr("oldtitle"), $(this), {
            tips: [3, '#000']
        });
    },function () {
        layer.close(tipsIndex);
    })
}

/**
 * 页面加载商品列表完成时绑定的事件
 */
function bindProductEvents() {
    // 绑定小弹窗事件
    bindTipEvent();
}