/***
 * @pName proback
 * @name FinanceAuthenticationInterceptor
 * @user HongWei
 * @date 2018/8/5
 * @desc
 */
package com.wanhao.proback.interceptor;

import com.wanhao.proback.annotaion.FinanceToken;
import com.wanhao.proback.exception.FinanceException;
import com.wanhao.proback.utils.Base64Utils;
import com.wanhao.proback.utils.Constants;
import com.wanhao.proback.utils.RSAUtil;
import com.wanhao.proback.utils.RequestUtil;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Enumeration;

/***
 * 财务验证拦截器
 */
public class FinanceAuthenticationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 只拦截method级别的处理器
        if (!(handler instanceof HandlerMethod)) return true;
        // 只拦截financeToken注解过的方法
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 判断接口是否需要登录
        FinanceToken financeToken = method.getAnnotation(FinanceToken.class);
        if (financeToken != null){
            String url = RequestUtil.getParameters(request);
            String sign = request.getParameter("sign");
            try {
                if(sign != null && getDecrypt(sign).equals(url)) return true;
            } catch (Exception e) {
                throw new FinanceException(e, FinanceException.Errors.SIGN_ERROR, "验签失败");
            }
            throw new FinanceException(FinanceException.Errors.SIGN_ERROR, "验签失败");
        }
        return true;
    }



    /**
     * 快速加密
     * @param body
     * @return
     * @throws Exception
     */
    private String getEncrypt(String body) throws Exception {
        // formUid=1&toUid=2&amount=1.9&remark=充值&token=
        byte[] bytes = RSAUtil.encryptByPublicKey(body.getBytes(), Constants.PUB_KEY);
        return  Base64Utils.encode(bytes);
    }

    /**
     * 快速解密
     * @param body
     * @return
     * @throws Exception
     */
    private String getDecrypt(String body) throws Exception {
        byte[] decode = Base64Utils.decode(body);
        return new String(RSAUtil.decryptByPrivateKey(decode, Constants.PRI_KEY), "UTF-8");
    }
}
