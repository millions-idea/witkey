package com.wanhao.proback;

import com.wanhao.proback.utils.Base64Utils;
import com.wanhao.proback.utils.Constants;
import com.wanhao.proback.utils.RSAUtil;
import org.junit.Test;

/**
 * Created by LiuLiHao on 2018/7/21 17:43.
 * 描述：
 * 作者： LiuLiHao
 */
public class TestRsa {

    @Test
    public void test1() throws Exception {
        byte[] bytes = RSAUtil.encryptByPublicKey("test".getBytes(), Constants.PUB_KEY);
        String encode = Base64Utils.encode(bytes);
        System.out.println(encode);
    }
}
