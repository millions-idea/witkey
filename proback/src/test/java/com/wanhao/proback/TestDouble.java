package com.wanhao.proback;

import com.wanhao.proback.utils.ComputeUtil;
import org.junit.Test;

/**
 * Created by LiuLiHao on 2018/7/25 21:59.
 * 描述： 测试double计算
 * 作者： LiuLiHao
 */
public class TestDouble {

    @Test
    public void test1(){
        System.out.println(0.05+0.01);
        System.out.println(1.0-0.42);
        System.out.println(4.015*100);
        System.out.println(123.3/100);


        Double aDouble = ComputeUtil.add(0.05, 0.01);
        System.out.println(aDouble);

        Double bDouble = ComputeUtil.add(1.0, -0.42);
        System.out.println(bDouble);

    }


}
