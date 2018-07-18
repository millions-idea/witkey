package com.wanhao.profront;

import com.wanhao.profront.bean.member.Member;
import com.wanhao.profront.bean.member.MemberTaoBao;
import com.wanhao.profront.service.member.MemberService;
import com.wanhao.profront.service.member.MemberTaoBaoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProfrontApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    MemberService memberService;

    @Test
    public void testMem(){
        Member member = new Member();
        member.setUsername("你说谁");
        memberService.addMember(member);
    }

    @Autowired
    MemberTaoBaoService taoBaoService;

    @Test
    public void test(){
        MemberTaoBao memberTaoBao = new MemberTaoBao();
        memberTaoBao.setMem_id(5);
        taoBaoService.addMemberTaoBao(memberTaoBao);
    }

    @Test
    public void testQuery(){
        MemberTaoBao memberTaoBao = new MemberTaoBao();
        memberTaoBao.setMem_id(5);
        List<MemberTaoBao> list = taoBaoService.queryMemberBuyList(memberTaoBao);
        System.out.println(list.size());
    }
}
