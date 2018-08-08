/*!商家代发快递 韦德 2018年8月7日22:43:53*/
var route = request_url + "express-platform/web",
    service;
(function () {
    var user = eval("(" + sessionStorage.getItem("member") + ")");

    // 初始化service服务
    service = initService(route);

    var $postalAddressesSelector = $("#addressid"),
        $expressCategorySelector = $("#kuaidicatid"),
        $titleInput = $("#title"),
        $weightInput = $("#weight"),
        $remarkInput = $("#remark"),
        $resetButton = $("input[name='reset']"),
        $submitButton = $("input[name='submit']");


    // 获取发货地址列表
    service.getPostalAddresses({
        userId: user.id
    }, function (data) {
        if(!isNaN(data.error) || isNaN(data.code) || data.code != 0) {
            layer.msg("获取发货地址列表失败");
            return;
        }
        var str = "";
        data.data.forEach(function (value) {
            str += "<option value='" + value.address_id + "'>" + value.address + "</option>";
        })
        $postalAddressesSelector.append(str);
    })

    // 获取快递分类
    service.getExpressCategory({}, function (data) {
        if(!isNaN(data.error) || isNaN(data.code) || data.code != 0) {
            layer.msg("获取发货地址列表失败");
            return;
        }
        var str = "";
        data.data.forEach(function (value) {
            str += "<option value='" + value.goods_id + "'>" + value.name + "&nbsp;&nbsp;价格：" + value.price + "元/个</option>";
        })
        $expressCategorySelector.append(str);
    })

    // 动态验证收货地址格式
    $titleInput.bind("input propertychange", function () {
        var recvAddress = $titleInput.val();
        if(!service.isRight(recvAddress)){
            if(recvAddress == null || recvAddress.length == 0){
                $titleInput.removeClass("my-error");
                $titleInput.next().css("cssText","display:none!important")
            }else{
                $titleInput.addClass("my-error");
                $titleInput.next().css("cssText","display:block!important")
            }
        }else{
            $titleInput.removeClass("my-error");
            $titleInput.next().css("cssText","display:none!important")
        }
    })

    // 重置表单
    $resetButton.click(function () {
        $titleInput.removeClass("my-error");
        $titleInput.next().css("cssText","display:none!important")
    })

    // 提交表单
    $submitButton.click(function () {
        $submitButton.attr("disabled",true)

        // 基本的表单验证
        if($postalAddressesSelector.val() == ""){
            layer.msg("请选择发货地址");
            $submitButton.removeAttr("disabled")
            return;
        }

        if($titleInput.hasClass("my-error")){
            layer.msg("收货地址不能为空");
            $submitButton.removeAttr("disabled")
            return;
        }

        if($expressCategorySelector.val() == ""){
            layer.msg("请选择发货地址");
            $submitButton.removeAttr("disabled")
            return;
        }

        service.addMerchantOrder({
            user_id: user.id,
            send_address_id: $postalAddressesSelector.val(),
            recv_address: $titleInput.val(),
            weight: $weightInput.val(),
            goods_id: $expressCategorySelector.val(),
            remark: $remarkInput.val()
        }, function (data) {
            $submitButton.removeAttr("disabled")
            if(!isNaN(data.error) || isNaN(data.code) || data.code != 0) {
                layer.msg("添加失败");
                return;
            }
            location.reload();
        })
    })
})()

/**
 * 初始化service服务
 * @param r
 */
function initService(r) {
    return {
        /**
         * 获取发货地址列表
         * @param param
         * @param callback
         */
        getPostalAddresses: function (param, callback) {
            $.get(r + "/getPostalAddresses", param, function (data) {
                callback(data);
            });
        },
        /**
         * 获取快递分类
         * @param param
         * @param callback
         */
        getExpressCategory: function (param, callback) {
            $.get(r + "/getExpressCategory", param, function (data) {
                callback(data);
            });
        },
        /**
         * 验证收货地址
         * @param value
         * @returns {boolean}
         */
        isRight: function (value) {
            if(value == null || value.length == 0) return false;
            if(value.indexOf("，") == -1) return false;
            var strings = value.split("，");
            return strings.length >= 4;
        },
        /**
         * 代发快递
         * @param param
         * @param callback
         */
        addMerchantOrder: function (param, callback) {
            $.post(request_url + "express-orders/web/add", param, function (data) {
                callback(data);
            });
        },
    }
}