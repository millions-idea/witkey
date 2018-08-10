/*!商家发货地址 韦德 2018年8月9日15:05:54*/
var route = request_url + "express-address/web",
    service,
    pageForm;

$(function () {
    var user = eval("(" + sessionStorage.getItem("member") + ")");

    // 初始化service服务
    service = initService(route);

    var $provincesSelector = $("#provinces"),
        $resetButton = $("input[name='reset']"),
        $tabButtons = $(".menu .tab a"),
        $container = $(".my-container .item"),
        $addressDetailsInput = $("#address_deails");


    // 动态验证详细地址格式
    /* $addressDetailsInput.bind("input propertychange",function () {
        var address = $addressDetailsInput.val();
        if(!service.isRight(address)){
            if(recvAddress == null || recvAddress.length == 0){
                $addressDetailsInput.removeClass("my-error");
                $addressDetailsInput.next().css("cssText","display:none!important")
            }else{
                $addressDetailsInput.addClass("my-error");
                $addressDetailsInput.next().css("cssText","display:block!important")
            }
        }else{
            $addressDetailsInput.removeClass("my-error");
            $addressDetailsInput.next().css("cssText","display:none!important")
        }
    })*/


    var $listSize = $("#list-size"),
        $diffSize = $("#diff-size");

    $listSize.text(0);
    $diffSize.text(0);


    // 加载表单模块
    layui.use(['form', 'layedit', 'laydate'], function() {
        var form = layui.form
            ,layer = layui.layer;


        // 表单提交验证
        form.on('submit(my-form)',function(data){
            var $provincesSelector = $("#provinces"),
                $areaInput = $("#area"),
                $postalCodeInput = $("#postal_code"),
                $realNameInput = $("#real_name"),
                $phone = $("#phone"),
                $sort = $("#sort"),
                $remark = $("#remark");

            var address =  $("#provinces [value='" + $provincesSelector.val() + "']").text() + " " + $areaInput.val() + " " + $addressDetailsInput.val();

            var param = {
                user_id: user.id,
                city_id: $provincesSelector.val(),
                postal_code: $postalCodeInput.val(),
                address: address,
                real_name: $realNameInput.val(),
                phone: $phone.val(),
                sort: $sort.val(),
                remark: $remark.val()
            };

            service.addAddress(param, function (data) {
                if(!isNaN(data.error) || isNaN(data.code) || data.code != 0) {
                    layer.msg("添加失败");
                    return;
                }
                location.reload();
            });

            return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        });

        // 加载省级列表
        loadProvinces("#provinces", form, function () {
            form.render();
        });


        // 编辑表单
        form.on('submit(my-edit)',function(data){
            var $provincesSelector = $("#e_provinces"),
                $areaInput = $("#e_area"),
                $postalCodeInput = $("#e_postal_code"),
                $realNameInput = $("#e_real_name"),
                $phone = $("#e_phone"),
                $sort = $("#e_sort"),
                $remark = $("#e_remark"),
                $addressId = $("#e_address_id"),
                $addressDetailsInput = $("#e_address_deails"),
                $addDateInput = $("#e_add_date");

            var address =  $("#e_provinces [value='" + $provincesSelector.val() + "']").text() + " " + $areaInput.val() + " " + $addressDetailsInput.val();

            var param = {
                address_id: $addressId.val(),
                user_id: user.id,
                city_id: $provincesSelector.val(),
                postal_code: $postalCodeInput.val(),
                address: address,
                real_name: $realNameInput.val(),
                phone: $phone.val(),
                sort: $sort.val(),
                remark: $remark.val(),
                add_date: $addDateInput.val()
            };

            service.editAddress(param, function (data) {
                if(!isNaN(data.error) || isNaN(data.code) || data.code != 0) {
                    layer.msg("添加失败");
                    setAddFormAllFilter();
                    return;
                }
                location.reload();
            });

            return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        });


        // 获取发货地址
        service.getPostalAddresses({
            userId: user.id
        }, function (data) {
            if(!isNaN(data.error) || isNaN(data.code) || data.code != 0) {
                layer.msg("加载发货地址失败");
                return;
            }

            $listSize.text(data.count);
            $diffSize.text(10 - data.count);

            var str = "<tbody>";
            str += "<tr>\n" +
                "                <th>地址</th>\n" +
                "                <th>姓名</th>\n" +
                "                <th>手机</th>\n" +
                "                <th>添加时间</th>\n" +
                "                <th>排序</th>\n" +
                "                <th width=\"40\">修改</th>\n" +
                "                <th width=\"40\">删除</th>\n" +
                "            </tr>";
            data.data.forEach(function (value) {
                str += "<tr onmouseover=\"this.className='on';\" onmouseout=\"this.className='';\" align=\"center\" class=\"\" data-obj='"+JSON.stringify(value)+"'>";
                str += "<td height=\"30\" align=\"left\">&nbsp;&nbsp;"+ value.address +"</td>";
                str += "<td>" + value.real_name + "</td>";
                str += "<td>" + value.phone + "</td>";
                var add_date = value.add_date;
                if(add_date == null) add_date = "";
                str += "<td class=\"px11 f_gray\" data-hasqtip=\"11\" oldtitle=\"添加时间 "+  add_date +"\" title=\"\">"+  add_date +"</td>";
                str += "<td class=\"px11 f_gray\">" + value.sort + "</td>";
                str += "<td><a href=\"javascript:void(0)\" name='edit'><img width=\"16\" height=\"16\" src=\"http://001.topaaa.com/member/image/edit.png\" alt=\"\" data-hasqtip=\"12\" oldtitle=\"修改\" title=\"\"></a></td>\n";
                str += "<td>\n" +
                    "                                <a href=\"javascript:void(0)\" name='delete'><img width=\"16\" height=\"16\" src=\"http://001.topaaa.com/member/image/delete.png\" alt=\"\" data-hasqtip=\"13\" oldtitle=\"删除\" title=\"\"></a>\n" +
                    "                            </td>";
                str += "</tr>";
            })
            str += "</tbody>";
            $("#express-address-list").html("");
            $("#express-address-list").append(str);

            // 删除地址
            $("a[name='delete']").click(function () {
                var detail = $(this).parent().parent().data("obj");
                layer.confirm('您确定要删除此条地址？', {
                    title: "敏感操作提示",
                    btn: ['确定','取消'],
                    shade: 0.3,
                    shadeClose: true
                },function () {
                    service.deletePostalAddresses({
                        id: detail.address_id
                    }, function (data) {
                        if(!isNaN(data.error) || data.code == 1){
                            layer.msg("删除失败");
                            return;
                        }
                        location.reload();
                    })
                })
            })

            // 编辑地址
            $("a[name='edit']").click(function () {
                var detail = $(this).parent().parent().data("obj");
                getEditView(detail, form);
            })
        })
    })



    // 动态切换tab页
    $tabButtons.click(function () {
        // 移除样式
        $tabButtons.removeClass("sb0");
        // 给选中项添加样式
        $(this).addClass("sb0");
        if($(this).text() == "添加地址"){
            $container.removeClass("my-tab-this");
            $container.removeClass("my-tab-this-none");
            $container.addClass("my-tab-this-none");
            $container.eq(0).removeClass("my-tab-this-none");
            $container.eq(0).addClass("my-tab-this");
        }else if($(this).text() == "管理地址"){
            $container.removeClass("my-tab-this");
            $container.removeClass("my-tab-this-none");
            $container.addClass("my-tab-this-none");
            $container.eq(1).removeAttr("style");
            $container.eq(1).removeClass("my-tab-this-none");
            $container.eq(1).addClass("my-tab-this");
        }else{
            getEditView();
        }
    })

    // 重置表单
    $resetButton.click(function () {
        $titleInput.removeClass("my-error");
        $titleInput.next().css("cssText","display:none!important")
    })
})

/**
 * 初始化service服务
 * @param r
 */
function initService(r) {
    return {
        /**
         * 获取省级列表
         * @param param
         * @param callback
         */
        getProvinces: function (param, callback) {
            $.request.get(request_url + "area/getAllProvince", param, function (data) {
                callback(data);
            })
        },
        /**
         * 添加发货地址
         * @param param
         * @param callback
         */
        addAddress: function (param, callback) {
            $.request.post(route + "/addExpressAddress", param, function (data) {
                callback(data);
            })
        },
        /**
         * 编辑发货地址
         * @param param
         * @param callback
         */
        editAddress: function (param, callback) {
            $.request.post(route + "/editExpressAddress", param, function (data) {
                callback(data);
            })
        },
        /**
         * 验证地址是否正确
         * @param value
         * @returns {boolean}
         */
        isRight: function (value) {
            if(value == null || value.length == 0) return false;
            if(value.indexOf("，") == -1) return false;
            var strings = value.split("，");
            return strings.length >= 4;
        },
        getPostalAddresses: function (param, callback) {
            $.request.get(route + "/getPostalAddresses", param, function (data) {
                callback(data);
            })
        },
        /**
         * 编辑发货地址
         * @param param
         * @param callback
         */
        editExpressAddress: function (param, callback) {
            $.request.post(route + "/editExpressAddress", param, function (data) {
                callback(data);
            })
        },
        /**
         * 删除发货地址
         * @param param
         * @param callback
         */
        deletePostalAddresses: function (param, callback) {
            $.request.get(route + "/deleteExpressAddress", param, function (data) {
                callback(data);
            })
        },
    }
}

/**
 * 获取编辑视图
 */
function getEditView(detail,form) {
    removeAddFormAllFilter();

    var $container = $(".my-container .item");
    $container.removeClass("my-tab-this");
    $container.removeClass("my-tab-this-none");
    $container.addClass("my-tab-this-none");
    $container.eq(2).removeAttr("style");
    $container.eq(2).removeClass("my-tab-this-none");
    $container.eq(2).addClass("my-tab-this");

    var $provincesSelector = $("#e_provinces"),
        $areaInput = $("#e_area"),
        $postalCodeInput = $("#e_postal_code"),
        $realNameInput = $("#e_real_name"),
        $phone = $("#e_phone"),
        $sort = $("#e_sort"),
        $remark = $("#e_remark"),
        $addressId = $("#e_address_id"),
        $addressDetailsInput = $("#e_address_deails"),
        $addDateInput = $("#e_add_date");

    loadProvinces("#e_provinces",form, function () {
        $provincesSelector.val(detail.city_id);
        $areaInput.val(detail.address.trim().split(" ")[1])
        $postalCodeInput.val(detail.postal_code)
        $realNameInput.val(detail.real_name)
        $phone.val(detail.phone)
        $sort.val(detail.sort)
        $remark.val(detail.remark)
        $areaInput.val(detail.address.trim().split(" ")[1])
        $addressDetailsInput.val(detail.address.substr(detail.address.indexOf(detail.address.trim().split(" ")[2]), detail.address.length))
        $addressId.val(detail.address_id);
        $addDateInput.val(detail.add_date);
        form.render('select');
    });

}

function loadProvinces(name,form,callback) {
    service.getProvinces({}, function (data) {
        var $provincesSelector = $(name);
        var str = "";
        data.forEach(function (value) {
            str += "<option value=\"" + value.codeid + "\">"+ value.cityName +"</option>";
        })
        $provincesSelector.append(str);
        form.render('select');
        callback();
    })
}


/**
 * 移除添加表单下的所有layui过滤器
 */
function removeAddFormAllFilter() {
    $(".my-container .item:eq(0) *[lay-verify]").removeAttr("lay-verify");
}

/**
 * 为添加表单设置layui过滤器
 */
function setAddFormAllFilter() {
    $(".my-container .item:eq(0) *[lay-verify]").attr("lay-verify","required");
}