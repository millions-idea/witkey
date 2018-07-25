package com.wanhao.proback.bean.member;

import com.wanhao.proback.bean.BaseBean;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Table;
import java.util.Date;

/**
 * Created by LiuLiHao on 2018/7/25 18:56.
 * 描述： 提现
 * 作者： LiuLiHao
 */
@Table(name = "tb_tixian")
@Setter
@Getter
@ToString
public class TiXian extends BaseBean {
    private Integer memid;
    private Integer flag;
    private Double money;
    private Double shouxu;
    private String shoukuanren;
    private String shoukuan_fangshi;
    private String shoukuan_zhaghao;

    private Date shenqing_shijian;
    private Date shouli_shijian;

}
