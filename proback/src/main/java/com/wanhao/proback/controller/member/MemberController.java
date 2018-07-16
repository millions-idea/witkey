package com.wanhao.proback.controller.member;

import com.wanhao.proback.bean.Area;
import com.wanhao.proback.bean.member.Member;
import com.wanhao.proback.bean.member.NameForbidden;
import com.wanhao.proback.service.AreaService;
import com.wanhao.proback.service.member.MemberService;
import com.wanhao.proback.service.member.NameForbiddenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

/**
 * Created by LiuLiHao on 2018/7/14 17:43.
 * 描述： 会员管理
 * 作者： LiuLiHao
 */
@Controller
@RequestMapping(value = "member")
public class MemberController {
    @Autowired
    NameForbiddenService nameForbiddenService;

    @Autowired
    MemberService memberService;

    @Autowired
    AreaService areaService;

    @RequestMapping(value = "forbidden")
    public String toForbidden(Map<String,Object> map){
        //查询出来
        NameForbidden forbidden = nameForbiddenService.query();
        map.put("forbidden",forbidden.getName());
        return "member/set-forbidden";
    }

    /**
     * 设置禁用用户名
     * @return
     */
    @RequestMapping(value = "forbidden",method = RequestMethod.POST)
    public String setForbidden(String name_forbidden, Model model){
        //保存到数据库
        NameForbidden nameForbidden = new NameForbidden();
        nameForbidden.setName(name_forbidden);
        nameForbidden.setId(1);

        nameForbiddenService.update(nameForbidden);

        //查询出来
        model.addAttribute("forbidden",name_forbidden);
        return "member/set-forbidden";
    }

    /**
     * 会员列表
     * @return
     */
    @RequestMapping(value = "memberList")
    public String toMemberList(Map<String,Object> map){
        //查找第一页
        List<Member> members = memberService.getMembers(new Member());
        //查询省份
        List<Area> provinces = areaService.getAllProvince();
        map.put("members",members);
        map.put("provinces",provinces);
        return "member/member-list";
    }

    /**
     * 会员列表
     * @return
     */
    @PostMapping(value = "memberList")
    public String memberList(Member member,
                             String type,
                             String type_value,
                             Double minmoney,
                             Double maxmoney,

                             Model model){

        if (type!=null){
            switch (type){
                case "0":
                    break;
                case "1":
                    //代理商
                    member.setProxy(type_value);
                    break;
                case "2":
                    member.setUsername(type_value);
                    break;
                case "4":
                    member.setReal_name(type_value);
                    break;
                case "5":
                    member.setMobile(type_value);
                    break;
                case "7":
                    member.setQq(type_value);
                    break;
                case "13":
                    member.setInvite_id(Integer.valueOf(type_value));
                    break;
                case "14":
                    member.setId_card(type_value);
                    break;
            }
        }


       //查找第一页
        List<Member> members = memberService.getMembers(member);
        //查询省份
        List<Area> provinces = areaService.getAllProvince();
        model.addAttribute("members",members);
        model.addAttribute("provinces",provinces);
        return "member/member-list";
    }

    /**
     * 添加
     * @return
     */
    @GetMapping(value = "memberAdd")
    public String memberAdd(){

        return "member/add";
    }
}
