package com.wanhao.proback;

import com.wanhao.proback.bean.Area;
import com.wanhao.proback.bean.admin.Admin;
import com.wanhao.proback.bean.member.Member;
import com.wanhao.proback.bean.member.MemberTaoBao;
import com.wanhao.proback.service.AreaService;
import com.wanhao.proback.service.admin.AdminService;
import com.wanhao.proback.service.member.MemberService;
import com.wanhao.proback.service.member.MemberTaoBaoService;
import com.wanhao.proback.service.member.NameForbiddenService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProbackApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    AdminService adminService;

    @Autowired
    MemberService memberService;

    @Test
    public void testLoadAdmin(){
        List<Admin> list = adminService.findAll();
        System.out.println(list);
    }

    @Test
    public void testMember(){
        Member member = new Member();
        member.setUsername("张");
        List<Member> members = memberService.getMembers(member);
        System.out.println(members);
    }

    @Autowired
    NameForbiddenService nameForbiddenService;

    @Autowired
    AreaService areaService;

    @Test
    public void testArea(){
        List<Area> allProvince = areaService.getAllProvince();
        System.out.println(allProvince);
    }

    @Autowired
    MemberTaoBaoService taoBaoService;

    @Test
    public void testTao(){
        MemberTaoBao one = taoBaoService.getOne(4);
        System.out.println(one);
    }
}
