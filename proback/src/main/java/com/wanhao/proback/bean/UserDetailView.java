/***
 * @pName proback
 * @name UserDetailView
 * @user HongWei
 * @date 2018/8/5
 * @desc
 */
package com.wanhao.proback.bean;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UserDetailView extends org.springframework.security.core.userdetails.User {
    private Integer user_id;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public UserDetailView(Integer user_id,String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.user_id = user_id;
    }

    public UserDetailView(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }
}

