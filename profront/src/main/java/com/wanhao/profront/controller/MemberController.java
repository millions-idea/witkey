package com.wanhao.profront.controller;

import com.wanhao.profront.bean.member.Member;
import com.wanhao.profront.bean.member.NameForbidden;
import com.wanhao.profront.service.member.MemberService;
import com.wanhao.profront.service.member.NameForbiddenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    /**
     * 跳转到注册
     * @return
     */
    @GetMapping(value = "register")
    public String toRegister(){
        return "member/register";
    }

    /**
     * 注册
     * @return
     */
    @PostMapping(value = "register")
    public String register(Member member){
        //检查是否包含禁用名字
        NameForbidden nameForbidden = nameForbiddenService.query();
        if (!StringUtils.isEmpty(nameForbidden)){
            String[] strings = nameForbidden.getName().split(",");
            if (strings!=null && strings.length>0){
                for (String s : strings) {
                    if (member.getUsername()!=null && member.getUsername().contains(s)){
                        //禁用
                        return "";
                    }
                }
            }

        }
        memberService.addMember(member);

        //注册成功暂时返回首页
        return "index";
    }

}
