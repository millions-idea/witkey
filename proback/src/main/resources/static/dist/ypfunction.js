//当前导航
function set_nav(ele_id, ele_class) {
    $("#" + ele_id).addClass(ele_class);
}

//消息提醒
function get_notice() {
    if ($('.layui-layer-title:contains(消息通知 notice)').size() > 0) return false;
    $.ajax({
        type: "GET",
        url: "/express/ypuserinfo.php",
        dataType: "json", cache: false,
        success: function (json) {
            if ($(".notice_box").size() == 0 && json.noticeid) {
                notice(json.noticeid);
            }
        }
    });
}

//发布评价任务
var pro5_index;

function pro5_comment(itemid) {//发布评价任务
    $.get('/yptask/pro5.php?action=comment&itemid=' + itemid, function (data, status) {
        if (status == 'success') {
            layer.open({
                type: 1,
                title: '发布评价任务，ID：' + itemid,
                shadeClose: true,
                shade: 0.4,
                area: ['1028px', '540px'],
                content: data,
                success: function (layero, index) {
                    pro5_index = index;
                }
            });
        }
    });
}

function pro5_addcomment(itemid) {//发布评价任务
    $.get('/yptask/pro5.php?action=addcomment&itemid=' + itemid, function (data, status) {
        if (status == 'success') {
            layer.open({
                type: 1,
                title: '发布追评任务，ID：' + itemid,
                shadeClose: true,
                shade: 0.4,
                area: ['1028px', '420px'],
                content: data,
                success: function (layero, index) {
                    pro5_index = index;
                }
            });
        }
    });
}

//提示
function notice(itemid) {
    layer.open({
        type: 2,
        title: '消息通知 notice',
        shadeClose: true,
        shade: 0.4,
        area: ['320px', '160px'],
        content: '/express/notice.php?itemid=' + itemid + '&ran=' + Math.random()
    });
}

function layer_alert(content, callback) {
    layer.open({
        content: content, btn: ['确定'], yes: function (index) {
            if (typeof callback == "function") callback();
            else layer.close(index);
        }
    });
}

//确认框
function ypconfirm(title, func) {
    layer.confirm(title, {
        btn: ['确定', '取消']
    }, function () {
        func();
    });
}

function ypconfirm_url(title, url) {
    layer.confirm(title, {
        btn: ['确定', '取消']
    }, function () {
        location.href = url;
    });
}

function tpl(element, context) {
    var template = $(element).html();
    var compiledTemplate = Template7.compile(template);
    var html = compiledTemplate(context);
    return html;
}

//发布试用
function renwu_cat(title, cat) {
    var cats, cur_cat;
    cats = cat.split(",");
    var str = '';
    for (var i = 0; i < cats.length; i++) {
        cur_cat = cats[i].split("|");
        str += '<a href="' + cur_cat[0] + '">' + cur_cat[1] + '</a>';
    }
    layer.open({
        type: 1,
        area: ['480px', '280px'], //宽高
        title: title,
        content: '<div class="renwu_cat">' + str + '</div>'
    });
}

//保存截图
function submit_img(obj, itemid) {
    $.ajax({
        url: "/yptask/viewpro.php",
        data: {
            submit: 1,
            itemid: itemid,
            name: $(obj).prev().attr("name"),
            content: encodeURI($(obj).prev().val())
        },
        type: 'post',
        cache: false,
        success: function (data) {
            if (data == "success") alert("保存成功！");
            else alert("保存失败，原因：" + data);
        },
        error: function () {
            alert("异常！");
        }
    });
}

function img_upload(fid) {
    if (fid.indexOf('viewpro') == -1) return;
    var post_name;
    post_name = fid.substr(0, fid.indexOf('_viewpro'));
    var img_arr = [];
    var ele_id = '#' + fid.replace('img_', 'thumb_');
    var catid = $(ele_id).data('catid');
    var itemid = $(ele_id).attr('itemid');
    $(ele_id).parent().parent().find('input').each(function () {
        if ($(this).val()) img_arr.push($(this).val());
    });
    var imgs;
    imgs = img_arr.join('|');
    $.post('/yptask/viewpro.php', {
        submit: 1,
        catid: catid,
        itemid: itemid,
        name: post_name,
        content: imgs
    }, function (response) {
        if (!isNaN(response)) {
            $(ele_id).parent().parent().find('.img_upload_date').text('上传时间：' + getTime(response));
        } else {
            layer.alert("保存失败，原因：" + response);
        }
    });
}

function viewpro_setimg(itemid, fd) {
    $.post('/yptask/viewpro.php', {
        submit: 1,
        catid: 4,
        itemid: itemid,
        name: fd,
        content: 1
    });
}

function save_txt_weixin(th) {
    var content = $(th).prev().val();
    var itemid = $(th).data('itemid');
    var catid = $(th).data('catid');
    var action = $(th).data('action');
    $.post('/yptask/viewpro.php', {
        submit: 1,
        action: 'save_txt_pinglun',
        catid: catid,
        itemid: itemid,
        content: content
    }, function (data) {
        if (data == 'success') {
            layer.alert('保存成功');
        } else {
            layer.alert(data);
        }
    })
}

function img_delete(filename) {
    if (filename != '') {
        makeRequest('action=clear_img&file=' + filename, AJPath);
    }
}

function zip_jietu(itemid) {//导出试用截图
    $.post('/yptask/daochu.php', {
        itemid: itemid,
        action: 'yptask_weixin_jietu'
    }, function (data) {
        if (data.status == 'success') {
            window.open(data.data);
        } else {
            layer.alert(data.data);
        }
    }, 'json');
}

function wei_view_account(itemid) {//查看任务手机号
    $.get('/express/my.php?mid=23&catid=10&action=view_account&itemid=' + itemid, function (data) {
        layer.open({
            type: 1,
            title: '试用编号：' + itemid,
            area: ['320px', '410px'],
            shadeClose: true, //点击遮罩关闭
            content: '<div class="yplayer">' + data + '</div>'
        });
    });
}

function viewinfo_jie(itemid_jie) {//查看上传详情
    $.get('/express/my.php?mid=23&catid=10&action=viewinfo_jie&itemid_jie=' + itemid_jie, function (data) {
        layer.open({
            type: 1,
            title: '任务上传编号：' + itemid_jie,
            area: ['320px', '410px'],
            shadeClose: true, //点击遮罩关闭
            content: '<div class="yplayer">' + data + '</div>'
        });
    });
}

//导出任务excel
function daochu(pro) {
    $.post('/yptask/daochu.php', {
        pro: pro,
        addtime1: $('#addtime1').val(),
        addtime2: $('#addtime2').val(),
        action: 'yptask'
    }, function (data) {
        if (data.status == 'success') {
            window.open(data.url);
        } else {
            layer.alert(data.msg);
        }

    }, 'json');
}

//创建、读取cookie
function setCookie(c_name, value, expiredays) {
    var exdate = new Date()
    exdate.setDate(exdate.getDate() + expiredays)
    document.cookie = c_name + "=" + escape(value) +
        ((expiredays == null) ? "" : ";expires=" + exdate.toGMTString())
}

function getCookie(c_name) {
    if (document.cookie.length > 0) {
        c_start = document.cookie.indexOf(c_name + "=")
        if (c_start != -1) {
            c_start = c_start + c_name.length + 1
            c_end = document.cookie.indexOf(";", c_start)
            if (c_end == -1) c_end = document.cookie.length
            return unescape(document.cookie.substring(c_start, c_end))
        }
    }
    return ""
}

function geturl(url) {//短网址
    $('#codes').val('获取链接中');
    $.ajax({
        type: 'POST',
        url: "/ajax.php?action=shorturl",
        data: {
            url: url
        },
        cache: false,
        success: function (data) {
            if (data) $('#codes').val(data);
            else $('#codes').val(url);
        }
    });
}

function getTime(/** timestamp=0 **/) {
    var ts = arguments[0] || 0;
    var t, y, m, d, h, i, s;
    t = ts ? new Date(ts * 1000) : new Date();
    y = t.getFullYear();
    m = t.getMonth() + 1;
    d = t.getDate();
    h = t.getHours();
    i = t.getMinutes();
    s = t.getSeconds();
    // 可根据需要在这里定义时间格式
    return y + '-' + (m < 10 ? '0' + m : m) + '-' + (d < 10 ? '0' + d : d) + ' ' + (h < 10 ? '0' + h : h) + ':' + (i < 10 ? '0' + i : i) + ':' + (s < 10 ? '0' + s : s);
}

//提交订单编号
function subimit_orderid(th) {
    var itemid = $(th).find('[name=itemid]').val();
    var price_true = $(th).find('[name=price_true]').val();
    var orderid = $(th).find('[name=orderid]').val();
    if (!price_true) {
        layer_alert('请输入实际付款金额');
        return false;
    }
    if (!orderid) {
        layer_alert('请输入宝贝订单编号');
        return false;
    }
    layer.confirm('<p>确认实际付款金额为【' + price_true + '】元？</p><p>确认宝贝订单编号为【' + orderid + '】？</p>', function () {
        var data = {
            submit: 1,
            ajax: 1,
            itemid: itemid,
            price_true: price_true,
            orderid: orderid
        };
        $.post('/express/yptaskview.php?action=orderid', data, function (response) {
            if (response == 'success') {
                $('#yptask_list_' + itemid).find('[name=orderid]').val(orderid);
                layer.alert('操作成功', function () {
                    location.href = '/express/yptaskview.php?pro=3';
                });
            } else {
                layer.alert(response);
            }
        });
    });
    return false;
}