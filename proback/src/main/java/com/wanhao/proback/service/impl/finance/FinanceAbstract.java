/***
 * @pName proback
 * @name FinanceServiceAbstract
 * @user HongWei
 * @date 2018/8/5
 * @desc
 */
package com.wanhao.proback.service.impl.finance;

import com.wanhao.proback.dao.finance.MoneysMapper;
import com.wanhao.proback.dao.finance.TransactionsMapper;
import com.wanhao.proback.dao.finance.WalletsMapper;
import org.springframework.beans.factory.annotation.Autowired;

/***
 * 财务模块抽象业务类
 */
public abstract class FinanceAbstract {
    protected final TransactionsMapper transactionsMapper;
    protected final MoneysMapper moneysMapper;
    protected final WalletsMapper walletsMapper;

    @Autowired
    protected FinanceAbstract(TransactionsMapper transactionsMapper, MoneysMapper moneysMapper, WalletsMapper walletsMapper) {
        this.transactionsMapper = transactionsMapper;
        this.moneysMapper = moneysMapper;
        this.walletsMapper = walletsMapper;
    }


}
