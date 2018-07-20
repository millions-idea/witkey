package com.wanhao.proback;

import com.github.pagehelper.PageInfo;
import com.wanhao.proback.bean.member.Member;
import com.wanhao.proback.service.member.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by LiuLiHao on 2018/7/20 12:54.
 * 描述：
 * 作者： LiuLiHao
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestPage {

    @Autowired
    MemberService memberService;

    @Test
    public void testQ(){
        Member member = new Member();
        member.setPage(2);
        member.setRows(5);
        List<Member> members = memberService.getMembers(member);
        PageInfo<Member> info = new PageInfo<>(members);
        System.out.println(info);
    }
}
