package com.wanhao.proback.controller.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by LiuLiHao on 2018/7/14 17:43.
 * 描述： 会员管理
 * 作者： LiuLiHao
 */
@Controller
@RequestMapping(value = "member")
public class MemberController {

    @RequestMapping(value = "forbidden")
    public String toForbidden(){
        return "member/set-forbidden";
    }
}
