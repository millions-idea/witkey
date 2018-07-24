package com.wanhao.proback;

import com.wanhao.proback.bean.shop.Shop;
import com.wanhao.proback.service.shop.ShopService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

/**
 * Created by LiuLiHao on 2018/7/24 16:37.
 * 描述： 添加任务商品
 * 作者： LiuLiHao
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestShop {


    @Autowired
    ShopService shopService;

    @Test
    public void testAdd(){

        Random random = new Random();

        String[] shopType = {"淘宝试用","京东试用","拼多多试用","蘑菇街试用","美丽说试用",
                    "淘宝访问","京东访问"};
        for (int i=0;i<=50;i++){
            Shop shop = new Shop();

            shop.setIs_pass(1);
            shop.setMem_id(random.nextInt(35));
            shop.setShop_type(shopType[random.nextInt(shopType.length)]);
            shop.setShop_url("taobao.com"+i);
            shop.setShop_wangwang("jingdong.com");
            shop.setRemark("没有备注");
            shop.setShop_name(shopType[random.nextInt(shopType.length)] + i+"号店");
            shopService.add(shop);
            System.out.println("添加完成");
        }
    }
}
