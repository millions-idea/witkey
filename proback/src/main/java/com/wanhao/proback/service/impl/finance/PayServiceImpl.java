/***
 * @pName proback
 * @name PayServiceImpl
 * @user HongWei
 * @date 2018/8/5
 * @desc
 */
package com.wanhao.proback.service.impl.finance;

import com.wanhao.proback.annotaion.AspectLog;
import com.wanhao.proback.bean.finance.TransferParam;
import com.wanhao.proback.dao.member.MoneysMapper;
import com.wanhao.proback.dao.member.TransactionsMapper;
import com.wanhao.proback.dao.member.WalletsMapper;
import com.wanhao.proback.service.finance.PayService;
import org.springframework.stereotype.Service;

/***
 * 财务交易业务实现类
 */
@Service
public class PayServiceImpl extends FinanceAbstract implements PayService {

    protected PayServiceImpl(TransactionsMapper transactionsMapper, MoneysMapper moneysMapper, WalletsMapper walletsMapper) {
        super(transactionsMapper, moneysMapper, walletsMapper);
    }

    /**
     * 转账
     *
     * @param transferParam
     */
    @Override
    @AspectLog(description = "转账")
    public void transfer(TransferParam transferParam) {
        throw new RuntimeException("测试");
    }
}
