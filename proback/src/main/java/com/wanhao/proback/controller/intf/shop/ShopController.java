package com.wanhao.proback.controller.intf.shop;

import com.google.gson.JsonObject;
import com.wanhao.proback.annotaion.ISLogin;
import com.wanhao.proback.bean.member.Member;
import com.wanhao.proback.bean.shop.BuyerRequire;
import com.wanhao.proback.bean.shop.Goods;
import com.wanhao.proback.bean.shop.Shop;
import com.wanhao.proback.bean.shop.TryRequire;
import com.wanhao.proback.service.member.MemberService;
import com.wanhao.proback.service.shop.GoodsService;
import com.wanhao.proback.service.shop.ShopService;
import com.wanhao.proback.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by LiuLiHao on 2018/7/23 21:42.
 * 描述： 商家操作
 * 作者： LiuLiHao
 */
@Controller(value = "intf_shop")
public class ShopController {

    @Autowired
    MemberService memberService;

    @Autowired
    ShopService shopService;

    /**
     * 发布任务
     */
    @ISLogin
    @RequestMapping(value = "pubTask")
    public void pubTask(HttpServletRequest request, HttpServletResponse response,
                        String catetype, String link_url,
                        String goods_img, String search_word,
                        String goods_format, String template_name,
                        Integer shop_id, Integer goods_type,
                        Integer search_type, Integer task_count,
                        Integer return_type, Integer pay_type,
                        Integer time_divider, Integer task_safe,
                        Integer save_template, Integer memid,
                        Double price, Double append_money,
                        Integer zhiding_pinglun, Integer bask_img,
                        Integer task_rec_time, String device,
                        Integer need_bi_san_jia, Integer need_chat,
                        Integer need_look_comment, Integer need_add_buy_cart,
                        Integer need_col_goods, String yuyue_xiadan,
                        String comment_content, String confirm_time_type,
                        String remark, Integer day_limit,
                        Integer shangbao_limit, Integer taoqi_limit,
                        Integer gender_limit, String honor_limit,
                        String forbidden_area, String age_limit,
                        String always_buy_class) {

        //        if (IsNullUtils.isNull(catetype, link_url, search_word, goods_format, shop_id,
//                goods_type, search_type, task_count, return_type, pay_type, time_divider,
//                task_safe, save_template, memid, price, append_money, bask_img, device,
//                need_look_comment, need_add_buy_cart, need_col_goods, yuyue_xiadan,
//                confirm_time_type, day_limit, shangbao_limit, taoqi_limit, gender_limit, honor_limit,
//                forbidden_area, age_limit, always_buy_class)) {
//
//        }

        JsonObject jsonObject = new JsonObject();
        //查询用户是否是商户
        Member member = new Member(memid);

        Member dbMember = memberService.getMember(member);

        if (dbMember==null || dbMember.getIs_seller()==null || dbMember.getIs_seller()==0){
            ResponseUtils.retnFailMsg(response,jsonObject,"操作失败...");
            return;
        }
        //查看店铺是否审核通过
        Shop shop = shopService.getByPk(shop_id);
        if (shop==null || shop.getIs_pass()==null || shop.getIs_pass()==0){
            //审核未通过
            ResponseUtils.retnFailMsg(response,jsonObject,"抱歉,您的店铺还未审核通过不能发布任务...");
            return;
        }

        BuyerRequire buyerRequire = new BuyerRequire();
        buyerRequire.setDay_limit(day_limit);
        buyerRequire.setShangbao_limit(shangbao_limit);
        buyerRequire.setTaoqi_limit(taoqi_limit);
        buyerRequire.setGender_limit(gender_limit);
        buyerRequire.setHonor_limit(honor_limit);
        buyerRequire.setForbidden_area(forbidden_area);
        buyerRequire.setAge_limit(age_limit);
        buyerRequire.setMemid(memid);
        buyerRequire.setAlways_buy_class(always_buy_class);

        TryRequire tryRequire = new TryRequire();
        tryRequire.setYuyue_xiadan(yuyue_xiadan);
        tryRequire.setMemid(memid);
        tryRequire.setComment_content(comment_content);
        tryRequire.setConfirm_time_type(confirm_time_type);
        tryRequire.setRemark(remark);
        tryRequire.setZhiding_pinglun(zhiding_pinglun);
        tryRequire.setBask_img(bask_img);
        tryRequire.setTask_rec_time(task_rec_time);
        tryRequire.setDevice(device);
        tryRequire.setNeed_bi_san_jia(need_bi_san_jia);
        tryRequire.setNeed_chat(need_chat);
        tryRequire.setNeed_look_comment(need_look_comment);
        tryRequire.setNeed_add_buy_cart(need_add_buy_cart);
        tryRequire.setNeed_col_goods(need_col_goods);



        Goods goods = new Goods();
        goods.setTime_divider(time_divider);
        goods.setTask_safe(task_safe);
        goods.setSave_template(save_template);
        goods.setPrice(price);
        goods.setAppend_money(append_money);
        goods.setCatetype(catetype);
        goods.setGoods_format(goods_format);
        goods.setGoods_img(goods_img);
        goods.setLink_url(link_url);
        goods.setTemplate_name(template_name);
        goods.setGoods_class_id(goods_type);
        goods.setReturn_type(return_type);
        goods.setMemid(memid);
        goods.setSearch_word(search_word);
        goods.setTask_count(task_count);
        goods.setShop_id(shop_id);
        goods.setSearch_type(search_type);
        goods.setPay_type(pay_type);

    }


    @Autowired
    GoodsService goodsService;

    /**
     * 加载用户任务列表
     */
    @ISLogin
    @RequestMapping(value = "loadUserTaskList")
    public void loadUserTaskList(HttpServletRequest request, HttpServletResponse response,
                                 Integer memid){
        if (memid!=null){
            JsonObject jsonObject = new JsonObject();
            //判断用户是否已经绑定账号
            Member member = new Member(memid);
            Member dbMember = memberService.getMember(member);
            if (dbMember==null || dbMember.getIs_bind_buy_account()==null ||
                    dbMember.getIs_bind_buy_account()==0){
                //没有绑定买号
                ResponseUtils.retnFailMsg(response,jsonObject,"没有绑定买号,不能浏览任务...");
                return;
            }
            Goods goods = new Goods();
            goods.setPay_type(1);
            //查询任务列表
            goodsService.getGoods(goods);

        }
    }
}
