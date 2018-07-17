package com.wanhao.profront.security;

import com.wanhao.profront.bean.member.Member;
import com.wanhao.profront.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Created by LiuLiHao on 2018/7/17 9:57.
 * 描述：
 * 作者： LiuLiHao
 */
@Component(value = "userDetailsService")
public class MemberDetailService implements UserDetailsService {

    @Autowired
    MemberService memberService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberService.findByName(username);
        if(member == null){
            throw new UsernameNotFoundException("用户名不存在");
        }

        // 封装用户信息，并返回。参数分别是：用户名，密码，todo 用户权限
        User user = new User(username, member.getPassword(),
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
        return user;
    }
}
