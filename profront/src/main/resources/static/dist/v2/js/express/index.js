/*!商家代发快递 韦德 2018年8月7日22:43:53*/
var route = request_url + "express-platform/web",
    service;
$(function () {
    var user = eval("(" + sessionStorage.getItem("member") + ")");

    // 初始化service服务
    service = initService(route);

    var $postalAddressesSelector = $("#addressid"),
        $expressCategorySelector = $("#kuaidicatid"),
        $titleInput = $("#title"),
        $weightInput = $("#weight"),
        $remarkInput = $("#remark"),
        $resetButton = $("input[name='reset']"),
        $submitButton = $("input[name='submit']"),
        $tabButtons = $(".menu .tab a"),
        $container = $(".my-container .item");

    // 动态切换tab页
    $tabButtons.click(function () {
        // 移除样式
        $tabButtons.removeClass("sb0");
        // 给选中项添加样式
        $(this).addClass("sb0");
        if($(this).text() == "添加快递"){
            $container.removeClass("my-tab-this");
            $container.removeClass("my-tab-this-none");
            $container.addClass("my-tab-this-none");
            $container.eq(0).removeClass("my-tab-this-none");
            $container.eq(0).addClass("my-tab-this");
        }else{
            $container.removeClass("my-tab-this");
            $container.removeClass("my-tab-this-none");
            $container.addClass("my-tab-this-none");
            $container.eq(1).removeAttr("style");
            $container.eq(1).removeClass("my-tab-this-none");
            $container.eq(1).addClass("my-tab-this");


            // 获取代发快递
            service.getMerchantExpressOrders({
                userId: user.id
            }, function (data) {
                if(!isNaN(data.error) || isNaN(data.code) || data.code != 0) {
                    layer.msg("加载代发快递列表失败");
                    return;
                }

                var str = "<tbody>";
                str += "<tr>\n" +
                    "                            <th width=\"70\">快递分类</th>\n" +
                    "                            <th width=\"150\">状态/单号</th>\n" +
                    "                            <th>地址(邮编)</th>\n" +
                    "                            <th width=\"50\">姓名</th>\n" +
                    "                            <th width=\"100\">手机</th>\n" +
                    "                            <th width=\"120\">添加时间</th>\n" +
                    "                        </tr>";
                data.data.forEach(function (value) {
                    str += "<tr onmouseover=\"this.className='on';\" onmouseout=\"this.className='';\" align=\"center\" class=\"\" data-obj='"+JSON.stringify(value)+"'>";
                    str += "<td>"+ value.express_name +"</td>";
                    str += "<td align=\"left\">";
                    var status = "未发货";
                    if(value.status != 0){
                        if(value.status == 1) status = "已发货";
                        if(value.status == 2) status = "被拒绝";
                        if(value.status == 3) status = "已被取消";
                    }
                    var expressId = value.express_id;
                    if(expressId == null) expressId = "";
                    str += "&nbsp;&nbsp;"+status+"                    <br>&nbsp;&nbsp;"+ expressId +"                </td>";
                    str += "<td align=\"left\">";
                    str += "    <p class=\"h40\">发货地址：<input type=\"text\" value=\""+value.send_address+"\" size=\"35\"></p>";
                    str += "    <p class=\"h40\">收货地址：<input type=\"text\" value=\""+value.recv_address+"\" size=\"35\"></p>";
                    str += "</td>";
                    str += "<td>"+value.real_name+"</td>";
                    str += "<td>"+value.phone+"</td>";
                    var editDate = value.edit_date;
                    if(editDate == null) editDate = "";
                    str += "<td class=\"px11 f_gray\" data-hasqtip=\"11\" oldtitle=\"更新时间 "+  editDate +"\" title=\"\">"+  editDate +"</td>";
                    str += "</tr>";
                })
                str += "</tbody>";
                $("#express-order-list").html("");
                $("#express-order-list").append(str);
            })
        }
    })

    // 获取发货地址列表
    service.getPostalAddresses({
        userId: user.id
    }, function (data) {
        if(!isNaN(data.error) || isNaN(data.code) || data.code != 0) {
            layer.msg("加载发货地址列表失败");
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
            layer.msg("加载发货地址列表失败");
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
})

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
            $.request.get(request_url + "express-address/web/getPostalAddresses", param, function (data) {
                callback(data);
            })
        },
        /**
         * 获取快递分类
         * @param param
         * @param callback
         */
        getExpressCategory: function (param, callback) {
            $.request.get(r + "/getExpressCategory", param, function (data) {
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
            $.request.post(request_url + "express-orders/web/add", param, function (data) {
                callback(data);
            });
        },
        getMerchantExpressOrders:function (param, callback) {
            $.request.get(request_url + "express-orders/web/getMerchantExpressOrders", param, function (data) {
                callback(data);
            });
        }
    }
}