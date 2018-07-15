package com.wanhao.proback.controller.member;

import com.wanhao.proback.bean.member.NameForbidden;
import com.wanhao.proback.service.member.NameForbiddenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(value = "forbidden")
    public String toForbidden(Map<String,Object> map){
        //查询出来
        NameForbidden forbidden = nameForbiddenService.query();
        map.put("forbidden",forbidden.getName());
        return "member/set-forbidden";
    }

    /**
     * 设置禁用
     * @param map
     * @return
     */
    @RequestMapping(value = "forbidden",method = RequestMethod.POST)
    public String setForbidden(Map<String,Object> map){
        String name_forbidden = (String) map.get("name_forbidden");
        //保存到数据库
        NameForbidden nameForbidden = new NameForbidden();
        nameForbidden.setName(name_forbidden);
        nameForbidden.setId(1);

        nameForbiddenService.update(nameForbidden);

        //查询出来
        NameForbidden forbidden = nameForbiddenService.query();
        map.put("forbidden",name_forbidden);
        return "member/set-forbidden";
    }
}
