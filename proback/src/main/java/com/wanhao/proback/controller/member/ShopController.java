package com.wanhao.proback.controller.member;

import com.wanhao.proback.bean.shop.Shop;
import com.wanhao.proback.service.shop.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

    private static final String PREFIX = "shop/";

    /**
     * 到审核店铺页面
     * @return
     */
    @RequestMapping(value = "toAuthShop")
    public String toAuthShop(Model model){
        Shop shop = new Shop();
        List<Shop> shops = shopService.getShops(shop);
        model.addAttribute("shops",shops);

        return PREFIX +"auth-shop-list";
    }
}
