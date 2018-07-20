package com.wanhao.proback.service.member;

import com.wanhao.proback.bean.member.MemberBank;

import java.util.List;

/**
 * Created by LiuLiHao on 2018/7/20 11:21.
 * 描述：
 * 作者： LiuLiHao
 */
public interface MemberBankService  {
    /**
     * 分页查询
     * @param memberBank
     * @return
     */
    List<MemberBank> findByPages(MemberBank memberBank);

    /**
     * 添加
     * @param bank
     */
    void update(MemberBank bank);
}
