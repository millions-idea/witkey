package com.wanhao.proback;

import com.wanhao.proback.bean.shop.BuyerRequire;
import com.wanhao.proback.bean.shop.Goods;
import com.wanhao.proback.bean.shop.TryRequire;
import com.wanhao.proback.service.shop.BuyerRequireService;
import com.wanhao.proback.service.shop.GoodsService;
import com.wanhao.proback.service.shop.TryRequireService;
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

    @Autowired
    TryRequireService requireService;

    @Autowired
    BuyerRequireService buyerRequireService;

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
            //添加试用限制信息
            TryRequire require = new TryRequire();
            require.setMemid(memid);
            require.setGoods_id(goods.getId());
            require.setNeed_col_goods(1);
            require.setNeed_chat(1);
            require.setNeed_bi_san_jia(0);
            require.setNeed_add_buy_cart(1);
            require.setNeed_look_comment(1);
            require.setDevice("手机");
            require.setZhiding_pinglun(1);
            require.setComment_content("收到东西了,很好用,也很喜欢");
            requireService.add(require);

            //添加买家限制
            BuyerRequire buyerRequire = new BuyerRequire();
            buyerRequire.setMemid(memid);
            buyerRequire.setGoods_id(goods.getId());
            buyerRequire.setForbidden_area("北京");
            buyerRequire.setTaoqi_limit(random.nextInt(600));
            buyerRequireService.add(buyerRequire);
        }
    }
}
