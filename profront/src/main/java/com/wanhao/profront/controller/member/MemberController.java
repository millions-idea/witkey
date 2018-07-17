package com.wanhao.profront.controller.member;

import com.wanhao.profront.bean.member.Member;
import com.wanhao.profront.bean.member.NameForbidden;
import com.wanhao.profront.service.member.MemberService;
import com.wanhao.profront.service.member.NameForbiddenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by LiuLiHao on 2018/7/16 16:50.
 * 描述：
 * 作者： LiuLiHao
 */
@Controller
@RequestMapping(value = "member")
public class MemberController {

    @Autowired
    NameForbiddenService nameForbiddenService;

    @Autowired
    MemberService memberService;

    private static final String PREFIX = "member/";

    /**
     * 跳转到注册
     * @return
     */
    @GetMapping(value = "register")
    public String toRegister(){
        return "member/register";
    }

    /**
     * 到登录
     * @return
     */
    @GetMapping(value = "login")
    public String toLogin(@RequestParam(value = "error", required = false) String error,
                          @RequestParam(value = "logout", required = false) String logout,
                          HttpServletRequest request, Model model){
        if (error != null) {
            model.addAttribute("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
        }
        if (logout != null) {
            model.addAttribute("msg", "你已经成功退出");
        }
        return "member/login";
    }

    /**
     * 登录成功跳转
     * @return
     */
    @RequestMapping(value = "memberIndex")
    public String memberIndex(Model model){

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        Member member = memberService.findByName(username);
        model.addAttribute("member",member);

        return PREFIX+"index";
    }

    //自定义错误类型
    private String getErrorMessage(HttpServletRequest request, String key){
        Exception exception =
                (Exception) request.getSession().getAttribute(key);
        String error;
        if (exception instanceof BadCredentialsException) {
            error = "不正确的用户名或密码";
        }else if(exception instanceof LockedException) {
            error = exception.getMessage();
        }else{
            error = "不正确的用户名或密码";
        }
        return error;
    }

    /**
     * 进入忘记密码设置
     * @return
     */
    @GetMapping(value = "forget")
    public String toForget(){
        return PREFIX+"forget";
    }

    /**
     * 注册
     * @return
     */
    @PostMapping(value = "register")
    public String register(Member member,String mobile_code, Model model){
        //判断手机验证码是否正确 todo
        if (mobile_code!=null){

        }
        //检查是否包含禁用名字
        NameForbidden nameForbidden = nameForbiddenService.query();
        if (!StringUtils.isEmpty(nameForbidden)){
            String[] strings = nameForbidden.getName().split(",");
            if (strings!=null && strings.length>0){
                for (String s : strings) {
                    if (member.getUsername()!=null && member.getUsername().contains(s)){
                        //禁用
                        model.addAttribute("err","禁用的用户名");
                        return "member/register";
                    }
                }
            }

        }
        memberService.addMember(member);

        //注册成功暂时返回首页
        return "index";
    }


    /**
     * 登录
     * @return
     */
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String login(@RequestParam(value = "error", required = false) String error,Model model){
        if (error != null) {

            return "member/login";
        }
        return "forward:/member/memberIndex";
    }

    /**
     * 去实名认证
     * @return
     */
    @RequestMapping(value = "goRealName",method = RequestMethod.GET)
    public String goRealName(){

        return PREFIX+"auth/realname";
    }
}
