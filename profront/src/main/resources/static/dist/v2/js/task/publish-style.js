/***
 * 原有的js函数
 */
var areaid = 0,
    zhutu_num = 0,
    shaitu_num = 0;


$(function () {
    set_nav("yptask_my_add", "on");
    $('[data-autohide]').click(function () {
        if ($(this).val() == '0') {
            $.each($(this).data('autohide').split(','), function (i, item) {
                $('#d' + item).parent().parent().slideUp();
            });
        } else {
            $.each($(this).data('autohide').split(','), function (i, item) {
                $('#d' + item).parent().parent().slideDown();
            });
        }
    });
    flag = 0;
    bott = $(document).height() - $('#tongji').offset().top - $('#tongji').height() - 42;
    tongji_fixed();
    $(window).scroll(function () {
        tongji_fixed();
    });
    $('#menu_div>a').click(function () {
        var index_click = $(this).index();
        change_fabu(index_click);
    });

});
jQuery.fn.extend({
    slideRightShow: function (speed, callback) {
        return this.each(function () {
            $(this).show('slide', {direction: 'right'}, speed, callback);
        });
    },
    slideLeftHide: function (speed, callback) {
        return this.each(function () {
            $(this).hide('slide', {direction: 'left'}, speed, callback);
        });
    },
    slideRightHide: function (speed, callback) {
        return this.each(function () {
            $(this).hide('slide', {direction: 'right'}, speed, callback);
        });
    },
    slideLeftShow: function (speed, callback) {
        return this.each(function () {
            $(this).show('slide', {direction: 'left'}, speed, callback);
        });
    }
});
function btnCilentAdd(th) {
    var diff = 1;
    if ($(th).hasClass('fl')) diff = -1
    $('#menu_div>a').eq($('#menu_div>a.on').index() + diff).click();
    if ($('#body_main').offset().top - $(window).scrollTop() <= 0) {
        $('html, body').animate({scrollTop: $('#body_main').offset().top - 35}, 500);
    }
}
var flag = 0;
var bott = 0;
function change_fabu(index_click) {
    var index = $('#menu_div>a.on').index();
    $('.fabu').height($('.fabu').height());
    var height_click = $('.fabu>div').eq(index_click).height();
    if (index == index_click) return false;
    $('#menu_div>a').removeClass('on');
    $('#menu_div>a').eq(index_click).addClass('on');
    if (index < index_click) {
        $('.fabu>div').slideLeftHide(250).eq(index_click).slideRightShow(250);
        $('.fabu').animate({height: height_click}, function () {
            $('.fabu').removeAttr('style');
            tongji_fixed();
        });
    } else {
        $('.fabu>div').slideRightHide(250).eq(index_click).slideLeftShow(250);
        $('.fabu').animate({height: height_click}, function () {
            $('.fabu').removeAttr('style');
            tongji_fixed();
        });
    }
    setTimeout(function () {
        $('#tongji .sudiv').fadeOut(125, function () {
            $('#tongji .sudiv').html($('.fabu>div').eq(index_click).find('.sudiv').html());
            $('#tongji .sudiv').fadeIn(125)
        });
    }, 250);
}
function tongji_fixed() {
    var totalheight = $(window).height() + $(window).scrollTop();
    var documentheight = $(document).height();
    var tongji_height = $('#tongji').height();
    if (flag) documentheight += bott;
    var diff = documentheight - totalheight;
    if (diff <= bott) {
        $('#tongji').removeClass('tongji_fixed');
        flag = 0;
    } else {
        $('#tongji').addClass('tongji_fixed');
        flag = 1;
    }
}


function add_kwd() {
    if ($('.tmpl_kwd').length >= 5) {
        layer_alert('最多可增加5组搜索词');
        return;
    }
    var select = '';
    var i;
    $('#dkwd').before('<p class="tmpl_kwd"><input type="text" name="tmpl[kwd][v][]" value="" size="30" class="input_line"> <span class="label">任务数量：</span><input onchange="cal_money()" type="number" min="1" name="tmpl[kwd][n][]" value="1" size="10" class="input_line"></p>');
    $("#dkwd").parent().trigger('create');
    basecomm();
    lianmengbi();
    yakuan();
}
function del_kwd() {
    if ($('.tmpl_kwd').length > 1) $('.tmpl_kwd:last').remove();
}


function area_del(obj) {
    areaid = parseInt(areaid);
    if (areaid <= 1) return false;
    $('#load_area_' + areaid + ',#areaid_' + areaid).remove();
    areaid--;
}
function zhutu_add(obj) {
    zhutu_num = parseInt(zhutu_num);
    if (zhutu_num >= 5) {
        layer.alert('商品主图最多不能超过5个');
        return false;
    }
    zhutu_num++;
    zhutu_num = zhutu_num.toString();
    var upload_item = '<div class="img_upload_item"> <input zhutu="1" id="thumb_zhutu_' + zhutu_num + '" type="hidden"> <span> <img src="http://001.topaaa.com/skin/ypmili/image/waitpic.png" id="showthumb_zhutu_' + zhutu_num + '" alt="" onclick="if(this.src.indexOf(\'waitpic.png\') == -1){_preview(Dd(\'showthumb_zhutu_' + zhutu_num + '\').src, 1);}else{Dalbum(\'_zhutu_' + zhutu_num + '\',23,100,100, Dd(\'thumb_zhutu_' + zhutu_num + '\').value, true, \'yptask_add\');}" height="100" width="100"> </span> <span> <img title="上传" src="/member/image/img_upload.gif" onclick="Dalbum(\'_zhutu_' + zhutu_num + '\',23,100,100, Dd(\'thumb_zhutu_' + zhutu_num + '\').value, true, \'yptask_add\');" height="12" width="12">&nbsp;&nbsp; <img title="预览" src="/member/image/img_select.gif" onclick="_preview(Dd(\'showthumb_zhutu_' + zhutu_num + '\').src, 1);" height="12" width="12">&nbsp;&nbsp; <img title="删除" src="/member/image/img_delete.gif" onclick="img_delete(Dd(\'thumb_zhutu_' + zhutu_num + '\').value);delAlbum(\'_zhutu_' + zhutu_num + '\',\'wait\');" height="12" width="12"> </span> </div>';
    $(obj).prev().append(upload_item);
}
function zhutu_del(obj) {
    zhutu_num = parseInt(zhutu_num);
    if (zhutu_num <= 1) return false;
    $('#thumb_zhutu_' + zhutu_num).parent().remove();
    zhutu_num--;
}
//晒图********************************************
function shaitu_add(obj) {
    shaitu_num = parseInt(shaitu_num);
    shaitu_num++;
    if (shaitu_num >= 5) {
        layer.alert('晒图最多不能超过5个');
        return false;
    }
    shaitu_num = shaitu_num.toString();
    var upload_item = '<div class="img_upload_item" style="padding-top: 10px;"> <input shaitu="1" id="thumb_shaitu_' + shaitu_num + '" type="hidden"> <span> <img src="http://001.topaaa.com/skin/ypmili/image/waitpic.png" id="showthumb_shaitu_' + shaitu_num + '" alt="" onclick="if(this.src.indexOf(\'waitpic.png\') == -1){_preview(Dd(\'showthumb_shaitu_' + shaitu_num + '\').src, 1);}else{Dalbum(\'_shaitu_' + shaitu_num + '\',23,100,100, Dd(\'thumb_shaitu_' + shaitu_num + '\').value, true, \'yptask_add\');}" height="100" width="100"> </span> <span> <img title="上传" src="/member/image/img_upload.gif" onclick="Dalbum(\'_shaitu_' + shaitu_num + '\',23,100,100, Dd(\'thumb_shaitu_' + shaitu_num + '\').value, true, \'yptask_add\');" height="12" width="12">&nbsp;&nbsp; <img title="预览" src="/member/image/img_select.gif" onclick="_preview(Dd(\'showthumb_shaitu_' + shaitu_num + '\').src, 1);" height="12" width="12">&nbsp;&nbsp; <img title="删除" src="/member/image/img_delete.gif" onclick="img_delete(Dd(\'thumb_shaitu_' + shaitu_num + '\').value);delAlbum(\'_shaitu_' + shaitu_num + '\',\'wait\');" height="12" width="12"> </span> </div>';
    $(obj).parent().prev().append(upload_item);
}
function shaitu_del(obj) {
    shaitu_num = parseInt(shaitu_num);
    if (shaitu_num <= 1) return false;
    $('#thumb_shaitu_' + shaitu_num).parent().remove();
    shaitu_num--;
}
