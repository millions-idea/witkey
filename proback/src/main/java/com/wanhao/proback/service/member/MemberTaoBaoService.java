package com.wanhao.proback.service.member;


import com.wanhao.proback.bean.member.MemberTaoBao;
import com.wanhao.proback.service.BaseService;

import java.util.List;

/**
 * Created by LiuLiHao on 2018/7/18 21:00.
 * 描述：
 * 作者： LiuLiHao
 */
public interface MemberTaoBaoService extends BaseService<MemberTaoBao> {

    void addMemberTaoBao(MemberTaoBao taoBao);

    List<MemberTaoBao> queryMemberBuyList(MemberTaoBao taoBao);

    MemberTaoBao getOne(Integer id);

    /**
     * 通过
     * @param id
     */
    void agreeAllBuyAccount(String id);

    /**
     * 拒绝
     * @param id
     * @param reason
     */
    void rejectAllBuyAccount(String id, String reason);
}
