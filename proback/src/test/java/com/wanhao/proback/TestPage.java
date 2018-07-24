package com.wanhao.proback;

import com.wanhao.proback.bean.util.InviteResult;
import com.wanhao.proback.bean.vip.Vip;
import com.wanhao.proback.dao.member.MemberMapper;
import com.wanhao.proback.service.member.MemberService;
import com.wanhao.proback.service.vip.VipService;
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

    @Autowired
    MemberMapper memberMapper;

    @Test
    public void testQ(){
        List<InviteResult> inviteData = memberMapper.getInviteData();
        System.out.println(inviteData);
    }

    @Autowired
    VipService vipService;


    @Test
    public void testFirst(){
        List<InviteResult> inviteDataByMonth = memberMapper.getInviteDataByMonth();

        System.out.println(inviteDataByMonth);
    }
    @Test
    public void testVIp(){
        List<Vip> all = vipService.getAll();
        System.out.println(all);
    }
}
