package com.wanhao.proback.controller.member;

import com.github.pagehelper.PageInfo;
import com.google.gson.JsonObject;
import com.wanhao.proback.bean.member.Member;
import com.wanhao.proback.bean.shop.Shop;
import com.wanhao.proback.service.member.MemberService;
import com.wanhao.proback.service.shop.ShopService;
import com.wanhao.proback.utils.ResponseUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by LiuLiHao on 2018/7/22 22:24.
 * 描述： 商户操作  审核店铺等
 * 作者： LiuLiHao
 */
@Controller
@RequestMapping(value = "shop")
public class ShopController {

    @Autowired
    ShopService shopService;

    @Autowired
    MemberService memberService;
    private static final String PREFIX = "shop/";

    /**
     * 到审核店铺页面
     * @return
     */
    @RequestMapping(value = "toAuthShop")
    public String toAuthShop(Model model,Shop shop ,String status,String condition,
                             String value){

        //判断条件查询
        if (StringUtils.isNotBlank(condition) && !condition.equals("0") && StringUtils.isNotBlank(value)){
            switch (condition){
                case "1"://会员Id查询
                    shop.setMem_id(Integer.valueOf(value));
                    break;
            }
        }
        //认证状态
        if (StringUtils.isNotBlank(status) && !status.equals("3") ){
            shop.setIs_pass(Integer.valueOf(status));
        }

        List<Shop> shops = shopService.getShops(shop);
        PageInfo<Shop> pageInfo = new PageInfo<>(shops);

        model.addAttribute("pageinfo",pageInfo);

        return PREFIX +"auth-shop-list";
    }

    /**
     * 绑定店铺--同意
     * @return
     */
    @PostMapping(value = "bindShopAgree")
    public void bindShopAgree(@RequestBody AuthData data, HttpServletResponse response){
        authShop(data,1,response);
    }


    /**
     * 绑定店铺--拒绝
     * @return
     */
    @PostMapping(value = "bindShopRefuse")
    public void bindShopRefuse(@RequestBody AuthData data, HttpServletResponse response){
        authShop(data,2,response);
    }


    /**
     * 绑定店铺--从新操作
     * @return
     */
    @PostMapping(value = "bindShopRedo")
    public void bindShopRedo(@RequestBody AuthData data, HttpServletResponse response){
        authShop(data,0,response);
    }

    /**
     * 店铺认证操作
     * @param data
     * @param type 1同意 2 拒绝 0从新实名
     * @param response
     */
    public void authShop(AuthData data,Integer type,HttpServletResponse response){
        JsonObject jsonObject = new JsonObject();
        if (data.data==null || data.data.length<=0){
            //返回错误
            jsonObject.addProperty("error","1");
            ResponseUtils.renderJson(response,jsonObject.toString());
            return;
        }

        Shop bank = new Shop();

        Member member = new Member();

        for (Integer auth : data.data) {
            bank.setId(auth);

            List<Shop> byPages = shopService.getShops(bank);

            if (byPages!=null && byPages.size()>0){
                Shop bank1 = byPages.get(0);
                bank1.setIs_pass(type);
                bank1.setRemark(data.reason);
                //保存更新
                shopService.update(bank1);
                if (type==1){
                    member.setId(bank1.getMem_id());
                    Member dbMember = memberService.getMember(member);
                    if (dbMember!=null){
                        if (dbMember.getIs_bind_shop()==null || dbMember.getIs_bind_shop()==0){
                            dbMember.setIs_bind_shop(1);
                            //保存为已绑定店铺
                            memberService.updateMember(member);
                        }
                    }
                }
            }
        }
        //返回结果
        jsonObject.addProperty("error","0");
        ResponseUtils.renderJson(response,jsonObject.toString());
    }



    //接收参数
    static class AuthData{
        public Integer[] data;
        public String reason;
    }
}
