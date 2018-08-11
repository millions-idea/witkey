//"../dist/v2/json/product-categorys/index.json"
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
    },
    /**
     * 获取卖家账号列表
     * @param param
     * @param callback
     */
    getSellAccountList: function (param, callback) {
        $.request.get("../dist/v2/json/sell-accounts/index.json", param, function (data) {
            callback(data);
        })
    },
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
     * 获取商品详情
     * @param param
     * @param callback
     */
    getProductDetail: function (param, callback) {
        $.request.get(request_url + "product/web/getProductByCode", param, function (data) {
            callback(data);
        })
    },
}

// 获取request对象
var request = GetRequest();

$(function () {
    // 绑定焦点导航元素
    set_nav("yptask_my", "on");

    // 获取当前试用类型
    var type;
    if(request["type"] == null){
        type = 0;
    }else{
        type = request["type"];
    }

    // 初始化UI框架
    initLayui();
    
    
    // 获取试用列表
    service.getTryTypeList({}, function (data) {
        if(!isNaN(data.error) || (!isNaN(data.code) && data.code != 0)){
            layer.msg("加载试用列表失败");
            return;
        }
        ReactDOM.render(<TryTypeSelectView list={data.data} currentType={type} />, document.getElementById("try-type-list"), function () {

        })
    })

    // 获取卖家账号列表
    service.getSellAccountList({}, function (data) {
        if(!isNaN(data.error) || (!isNaN(data.code) && data.code != 0)){
            layer.msg("加载卖家账号列表失败");
            return;
        }

        ReactDOM.render(<SellAccountSelectView list={data.data} />, document.getElementById("sell-account-list"), function () {

        })
    })

    // 获取类目列表
    service.getCategoryList({}, function (data) {
        if (!isNaN(data.error) || (!isNaN(data.code) && data.code != 0)) {
            layer.msg("加载类目列表失败");
            return;
        }

        ReactDOM.render(<CategorySelectListView list={data.data}/>, document.getElementById("category-selector"), function () {

        })
    })

    // 获取商品详情
    service.getProductDetail({
        code: "52f31a72-8763-422a-a4a4-1285c12fd898"
    }, function (data) {
        if (!isNaN(data.error) || (!isNaN(data.code) && data.code != 0)) {
            layer.msg("加载商品资料失败");
            return;
        }
        
        var nData = new Array();
        data.attributes.forEach(function (value) {
            if(value.type == 0){
                nData.push(value);
            }
        })

        ReactDOM.render(<TryAttributesView  list={nData}/>, document.getElementById("attribute-list"), function () {
            $(".ele_add").click(function () {
                addImageEle(this);
            })

            $(".ele_del").click(function () {
                delImageEle(this);
            })
        })
    })
})

/**
 * 初始化layui的组件包
 */
function initLayui() {
    layui.use(['layer'], function(){
        var layer = layui.layer;
    });
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



function addImageEle(obj) {
    var ele_name = $(obj).parent().parent().find('input:eq(0)').attr('name');
    if (ele_name == 'post_fields[ban_area]') {
        area_add(obj);
    } else if (ele_name == 'post_fields[zhutu]') {
        zhutu_add(obj);
    } else if (ele_name == 'post_fields[shaitu]') {
        shaitu_add(obj);
    }
    qtip_init();
}
function delImageEle(obj) {
    var ele_name = $(obj).parent().find('input:eq(0)').attr('name');
    if (ele_name == 'post_fields[ban_area]') {
        area_del(obj);
    } else if (ele_name == 'post_fields[zhutu]') {
        zhutu_del(obj);
    } else if (ele_name == 'post_fields[shaitu]') {
        shaitu_del(obj);
    }
    qtip_init();
}