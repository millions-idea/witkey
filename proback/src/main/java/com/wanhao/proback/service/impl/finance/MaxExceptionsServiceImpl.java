/***
 * @pName proback
 * @name MaxExceptionsServiceImpl
 * @user HongWei
 * @date 2018/8/5
 * @desc
 */
package com.wanhao.proback.service.impl.finance;

import com.wanhao.proback.bean.finance.MaxExceptions;
import com.wanhao.proback.dao.finance.MaxExceptionsMapper;
import com.wanhao.proback.service.BaseServiceImpl;
import com.wanhao.proback.service.finance.MaxExceptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 重要异常日志业务实现类
 */
@Service
public class MaxExceptionsServiceImpl extends BaseServiceImpl<MaxExceptions> implements MaxExceptionsService {
    private final MaxExceptionsMapper maxExceptionsMapper;

    @Autowired
    public MaxExceptionsServiceImpl(MaxExceptionsMapper maxExceptionsMapper) {
        this.maxExceptionsMapper = maxExceptionsMapper;
    }

    @Override
    @Async
    public void asyncInsert(MaxExceptions maxExceptions) {
        maxExceptionsMapper.insertSingle(maxExceptions);
    }
}
