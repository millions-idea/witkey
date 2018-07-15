package com.wanhao.proback.bean.member;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by LiuLiHao on 2018/7/15 22:05.
 * 描述：
 * 作者： LiuLiHao
 */
@Table(name = "tb_name_forbidden")
@Setter
@Getter
public class NameForbidden implements Serializable {
    @Id
    @Column(name = "id")
    private Integer id;
    private String name;
    
}
