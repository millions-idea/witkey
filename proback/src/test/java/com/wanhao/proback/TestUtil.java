package com.wanhao.proback;

import com.wanhao.proback.utils.IsNullUtils;
import org.junit.Test;

/**
 * Created by LiuLiHao on 2018/7/23 22:52.
 * 描述：
 * 作者： LiuLiHao
 */
public class TestUtil {

    @Test
    public void test1(){
        boolean aNull = IsNullUtils.isNull("gg");
        System.out.println(aNull);
    }
}
