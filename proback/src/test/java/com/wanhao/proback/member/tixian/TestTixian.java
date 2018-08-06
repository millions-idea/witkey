package com.wanhao.proback.member.tixian;

import com.wanhao.proback.bean.member.TiXian;
import com.wanhao.proback.service.member.TiXianService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by LiuLiHao on 2018/8/6 21:23.
 * 描述：
 * 作者： LiuLiHao
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestTixian {

    @Autowired
    TiXianService tiXianService;

    @Test
    public void test1(){
        List<TiXian> tiXianData = tiXianService.getTiXianData(new TiXian());
        System.out.println(tiXianData);
    }
}
