package com.wanhao.proback.config;

import com.wanhao.proback.annotaion.ISLogin;
import com.wanhao.proback.bean.member.Member;
import com.wanhao.proback.exception.FormRepeatException;
import com.wanhao.proback.exception.TokenInvalidException;
import com.wanhao.proback.service.member.MemberService;
import com.wanhao.proback.utils.Constants;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by LiuLiHao on 2018/7/21 10:50.
 * 描述： 判断是否已经登录
 * 作者： LiuLiHao
 */
@Aspect
@Component
public class ISLoginContract {
    private  final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    MemberService memberService;

    @Autowired
    RedisTemplate redisTemplate;

    @Before("within(@org.springframework.stereotype.Controller *) && @annotation(isLogin)")
    public void testToken(final JoinPoint joinPoint, ISLogin isLogin){
        try {
            if (isLogin != null) {
                //获取 joinPoint 的全部参数
                Object[] args = joinPoint.getArgs();
                HttpServletRequest request = null;
                HttpServletResponse response = null;
                for (int i = 0; i < args.length; i++) {
                    //获得参数中的 request && response
                    if (args[i] instanceof HttpServletRequest) {
                        request = (HttpServletRequest) args[i];
                    }
                    if (args[i] instanceof HttpServletResponse) {
                        response = (HttpServletResponse) args[i];
                    }
                }
                //从请求头获取
                String mobile1 = request.getHeader("mobile");
                if (mobile1!=null){
                    System.out.println(mobile1);
                }
                String sendToken2 = request.getHeader("token");
                if (sendToken2!=null){
                    System.out.println(sendToken2);
                }

                //redis里面的缓存
                String mobile = (String) redisTemplate.opsForValue().get(sendToken2);

                if (StringUtils.isNotBlank(mobile) && mobile.equals(mobile1)) {
                    //正确
                    System.out.println("正确的token");
                    Member member = new Member();
                    member.setMobile(mobile1);
                    List<Member> members = memberService.loginMemberByMobile(member);

                    //放入session
                    request.getSession().setAttribute(Constants.USER,members.get(0));
                }else {
                    //错误
                    System.out.println("错误的token");
                    throw new TokenInvalidException("登录信息不正确,请从新登录");
                }

            }

        }  catch (Exception e){
            throw e;
        }
    }

}
