package com.wanhao.proback.service.member;

import com.wanhao.proback.bean.member.MemberBank;
import com.wanhao.proback.service.BaseService;

import java.util.List;

/**
 * Created by LiuLiHao on 2018/7/20 11:21.
 * 描述：
 * 作者： LiuLiHao
 */
public interface MemberBankService  extends BaseService<MemberBank> {

    /**
     * 分页查询
     * @param memberBank
     * @return
     */
    List<MemberBank> findByPages(MemberBank memberBank);

    void agreeAllBank(String id);

    void rejectAllBuyBank(String id, String reason);

}
