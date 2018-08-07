/***
 * @pName proback
 * @name WalletsServiceImpl
 * @user HongWei
 * @date 2018/8/5
 * @desc
 */
package com.wanhao.proback.service.impl.finance;

import com.wanhao.proback.bean.finance.Wallets;
import com.wanhao.proback.dao.finance.WalletsMapper;
import com.wanhao.proback.service.BaseServiceImpl;
import com.wanhao.proback.service.finance.WalletsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 财务钱包业务实现类
 */
@Service
@Transactional
public class WalletsServiceImpl extends BaseServiceImpl<Wallets> implements WalletsService {

    @Autowired
    WalletsMapper walletsMapper;

    @Override
    @Transactional(readOnly = true)
    public Wallets selectOneByUid(Integer user_id) {
        return walletsMapper.selectOneByUid(user_id);
    }

}
