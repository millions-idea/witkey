package com.wanhao.proback;

import com.wanhao.proback.utils.Base64Utils;
import com.wanhao.proback.utils.Constants;
import com.wanhao.proback.utils.RSAUtil;
import org.junit.Test;

/**
 * Created by LiuLiHao on 2018/7/16 23:46.
 * 描述：
 * 作者： LiuLiHao
 */
public class Temp {


    //YRN4GyiT+mx8I7NySJnJiE+sEhiNAWcx8i/lHwURH4VZ4Yw+3Eh70fuf2L7eWFGCqRnogeJ6MC0Pit2Z5Cu9qWm7rX3AtC3vWbIA3GpCwJTlOlRPpyvPkzcYoWwXUBLuZrUJSu1OX5peGShEdfLOtiMm7B3S5iVNFo8s916SEpo=

    @Test
    public void test1(){
        byte[] decode;
        try {
            decode = Base64Utils.decode("YRN4GyiT+mx8I7NySJnJiE+sEhiNAWcx8i/lHwURH4VZ4Yw+3Eh70fuf2L7eWFGCqRnogeJ6MC0Pit2Z5Cu9qWm7rX3AtC3vWbIA3GpCwJTlOlRPpyvPkzcYoWwXUBLuZrUJSu1OX5peGShEdfLOtiMm7B3S5iVNFo8s916SEpo=");
            byte[] content = RSAUtil.decryptByPrivateKey(decode, Constants.PRI_KEY);
            //解密结果
            System.out.println("解密结果");
            System.out.println(new String(content));

        } catch (Exception e) {
            System.out.println("异常");
        }

    }
}
