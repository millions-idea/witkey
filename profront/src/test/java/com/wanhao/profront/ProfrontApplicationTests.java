package com.wanhao.profront;

import com.wanhao.profront.bean.member.Member;
import com.wanhao.profront.service.member.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
}
