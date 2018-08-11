/*!入口文件 韦德 2018年8月7日22:43:35*/

/**
 * Created by Administrator on 2017/8/25.
 */
// 配置扩展方法路径
layui.config({
    base: '/v2/frame/static/js/'   // 模块目录
}).extend({                         // 模块别名
    vip_nav: 'vip_nav'
    , vip_tab: 'vip_tab'
    , vip_table: 'vip_table'
});


$(function () {
    layui.use(['form','laydate'], function(){
        var form = layui.form,
            laydate = layui.laydate;
        form.render();
        laydate.render({
            elem: "#date"
        });
    });
})