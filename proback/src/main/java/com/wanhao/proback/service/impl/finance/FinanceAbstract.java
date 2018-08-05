/***
 * @pName proback
 * @name FinanceServiceAbstract
 * @user HongWei
 * @date 2018/8/5
 * @desc
 */
package com.wanhao.proback.service.impl.finance;

import com.wanhao.proback.dao.member.MoneysMapper;
import com.wanhao.proback.dao.member.TransactionsMapper;
import com.wanhao.proback.dao.member.WalletsMapper;
import org.springframework.beans.factory.annotation.Autowired;

/***
 * 财务模块抽象业务类
 */
public abstract class FinanceAbstract {
    private final TransactionsMapper transactionsMapper;
    private final MoneysMapper moneysMapper;
    private final WalletsMapper walletsMapper;

    @Autowired
    protected FinanceAbstract(TransactionsMapper transactionsMapper, MoneysMapper moneysMapper, WalletsMapper walletsMapper) {
        this.transactionsMapper = transactionsMapper;
        this.moneysMapper = moneysMapper;
        this.walletsMapper = walletsMapper;
    }


}
