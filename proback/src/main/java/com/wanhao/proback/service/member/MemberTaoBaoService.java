package com.wanhao.proback.service.member;


import com.wanhao.proback.bean.member.MemberTaoBao;

import java.util.List;

/**
 * Created by LiuLiHao on 2018/7/18 21:00.
 * 描述：
 * 作者： LiuLiHao
 */
public interface MemberTaoBaoService {

    void addMemberTaoBao(MemberTaoBao taoBao);

    List<MemberTaoBao> queryMemberBuyList(MemberTaoBao taoBao);

    MemberTaoBao getOne(Integer id);

}