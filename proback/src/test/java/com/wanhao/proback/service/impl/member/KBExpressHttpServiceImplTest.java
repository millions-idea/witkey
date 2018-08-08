package com.wanhao.proback.service.impl.member;


import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.wanhao.proback.bean.http.member.express.Info;
import com.wanhao.proback.bean.http.member.express.Items;
import com.wanhao.proback.bean.http.member.express.KongBaoExpressHttpParam;
import com.wanhao.proback.bean.http.member.express.PostAddrItem;
import com.wanhao.proback.utils.JsonUtil;
import com.wanhao.proback.utils.MD5Util;
import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.http.Header;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
public class KBExpressHttpServiceImplTest {

    private KongBaoExpressHttpParam kongBaoExpressHttpParam;

    @Before
    public void setUp() {
        String username = "52lingqian";
        String password = "52lingqian";
        String sign = MD5Util.encrypt32(username.concat(MD5Util.encrypt16(password)).concat(UUID.randomUUID().toString()));
        kongBaoExpressHttpParam = new KongBaoExpressHttpParam();

        kongBaoExpressHttpParam.setInfo(new Info(sign, UUID.randomUUID().toString(), "52lingqian"));
        kongBaoExpressHttpParam.setKdid(8);
        kongBaoExpressHttpParam.setNum(5);
        kongBaoExpressHttpParam.setKg(null);
        kongBaoExpressHttpParam.setItems(Lists.newArrayList(
                new Items("1608032354010100","张无忌，15888888888，广东省 广州市 番禺区 岭南大道321号 ，330006"),
                new Items("1608032354010101","张无忌，15888888888，广东省 广州市 番禺区 岭南大道321号 ，330006"),
                new Items("1608032354010102","张无忌，15888888888，广东省 广州市 番禺区 岭南大道321号 ，330006")
        ));
        kongBaoExpressHttpParam.setPostAddrItem(new PostAddrItem("北京","北京市","东城区","李逍遥","13421304686","紫禁城"));
    }


    @Test
    public void whenBuyExpressSuccess() throws UnirestException {
        System.out.println(JsonUtil.getJson(kongBaoExpressHttpParam));
        HttpResponse<String> response = Unirest.post("http://www.kongbao10000.com/TestAPI/BuyKddh")
                .header("Content-Type", "application/json")
                .body(JsonUtil.getJson(kongBaoExpressHttpParam))
                .asString();
        String body = response.getBody();
        System.out.println(body);
    }



}