package com.wanhao.proback.interceptor;

import com.wanhao.proback.utils.Base64Utils;
import com.wanhao.proback.utils.Constants;
import com.wanhao.proback.utils.RSAUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class FinanceAuthenticationInterceptorTest {

    @Test
    public void testGet() throws Exception {
        Map<String, Object> keyMap = RSAUtil.genKeyPair();
        String publicKey = RSAUtil.getPublicKey(keyMap);
        String privateKey = RSAUtil.getPrivateKey(keyMap);
        System.err.println("publicKey: " + publicKey);
        System.err.println("privateKey：" + privateKey);
    }

    @Test
    public void testEncryptByPublicKey() throws Exception {
        // formUid=1&toUid=2&amount=1.9&remark=充值
        byte[] bytes = RSAUtil.encryptByPublicKey("formUid=1&toUid=2&amount=1.9&remark=充值".getBytes(), Constants.FINANCE_PUB_KEY);
        String encode = Base64Utils.encode(bytes);
        System.err.println(encode);
    }

    @Test
    public void testDecryptByPrivateKey() throws Exception {
        String body = "BHwoiLX76DiW+kO1/XrL3QothehFHUwiQ40AAVccT3HZhwb0U2QNWUiPGzKbcCN6n/JM31TMzCh48qfMTrBQQYTqOtuUMih8zjJbVcIAy5dtkLA8Ykxhv8JEztawKzl4MbEw/kxSZhEtx89kFwYQG2/YRtgvk2vibL3yl0j0WJw=";
        byte[] decode = Base64Utils.decode(body);
        String content = new String(RSAUtil.decryptByPrivateKey(decode, Constants.FINANCE_PRIVATE_KEY), "UTF-8");
        System.err.println(content);
    }
}