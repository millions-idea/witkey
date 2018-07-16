package com.wanhao.proback.bean.member;

import com.wanhao.proback.bean.BaseBean;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Table;

/**
 * Created by LiuLiHao on 2018/7/15 22:05.
 * 描述：
 * 作者： LiuLiHao
 */
@Table(name = "tb_name_forbidden")
@Setter
@Getter
public class NameForbidden extends BaseBean {
    private String name;
    
}
