package com.wanhao.proback;

import com.wanhao.proback.bean.shop.Goods;
import com.wanhao.proback.service.shop.GoodsService;
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
public class TestGoods {


    @Autowired
    GoodsService goodsService;


    @Test
    public void testAdd(){


        Random random = new Random();

        String[] shopType = {"淘宝试用","京东试用","拼多多试用","蘑菇街试用","美丽说试用",
                "淘宝访问","京东访问"};

        for (int i=0;i<25;i++){
            Goods goods = new Goods();
            int memid = random.nextInt(30);

            goods.setCatetype(random.nextInt(10)+"");
            goods.setPay_type(1);
            goods.setGoods_class_id(random.nextInt(10));
            goods.setPrice(random.nextDouble()*1000);
            goods.setSearch_type(random.nextInt(3));
            goods.setSave_template(random.nextInt(2));
            goods.setShop_id(random.nextInt(50));
            goods.setSearch_word("随便搜");
            goods.setTask_count(random.nextInt(30));
            goods.setAppend_money(random.nextDouble()*10);
            goods.setLink_url("www.taobao.com");
            goods.setMemid(memid);
            //保存商品
            goodsService.add(goods);
        }
    }
}
