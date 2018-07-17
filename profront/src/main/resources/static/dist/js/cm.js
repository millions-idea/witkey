$(function () {
    qtip_init();
    //警告闪烁
    setInterval('$(".ypwarning").animate({ opacity: "0" },800).animate({ opacity: "1" },800)', 1600);
    var dm = document.domain;
    var num = dm.lastIndexOf('.');
    var top_dm = dm.substring(0, num);
    num = top_dm.lastIndexOf('.');
    top_dm = document.domain.substring(num + 1, dm.length);
    var topaaa = new Array('top.com', 'topaaa.com');
    if ($.inArray(top_dm, topaaa) != -1) {
        if ($('.yptip').size() > 0) {
            layer.tips('此处是后台的设置说明<br>管理员可以编辑或删除<br>用来给会员做提示信息', '.yptip', {
                tips: [2, '#197cfd'],
                time: 3000
            });
        }
    }
});

function qtip_init() {
    $("[title]").qtip({style: {classes: 'qtip-bootstrap'}});
    $("[data-qtip-img]").qtip({
        content: {
            text: function () {
                var src = $(this).data('qtip-img');
                var width = $(this).data('qtip-img-width');
                var height = $(this).data('qtip-img-height');
                if (width != 0) width = 'width="' + width + '"';
                if (height != 0) height = 'height="' + height + '"';
                return '<img src="' + src + '" ' + width + ' ' + height + '>';
            }, title: function () {
                return $(this).attr('title');
            }
        },
        position: {
            my: 'left center',
            at: 'center right',
            adjust: {x: 10}
        },
        style: {classes: 'qtip-bootstrap'}
    });
    //在右侧的图标提示向左
    $(".you[title], [lay-type=history]").qtip({
        position: {
            my: 'top right',
            at: 'bottom left'
        },
        style: {
            classes: 'qtip-bootstrap'
        }
    });
}

/*  淡入淡出  */
$(function () {
    $("#body").css("opacity", 0).delay(150).animate({opacity: 1}, 150);//淡入淡出
    setInterval("get_notice()", 10000);//消息提醒
});
/*  公告滚动  */
$(function () {
    //单行应用@Mr.Think
    var _wrap = $('ul.line');//定义滚动区域
    var _interval = 4000;//定义滚动间隙时间
    var _moving;//需要清除的动画
    _wrap.hover(function () {
        clearInterval(_moving);//当鼠标在滚动区域中时，停止滚动
    }, function () {
        _moving = setInterval(function () {
            var _field = _wrap.find('li:first');//此变量不可放置于函数起始处，li:first取值是变化的
            var _h = _field.height();//取得每次滚动高度
            _field.animate({marginTop: -_h + 'px'}, 2000, function () {//通过取负margin值，隐藏第一行
                _field.css('marginTop', 0).appendTo(_wrap);//隐藏后，将该行的margin值置零，并插入到最后，实现无缝滚动
            })
        }, _interval);//滚动间隔时间取决于_interval
    }).trigger('mouseleave');//函数载入时，模拟执行mouseleave，即自动滚动
});