/***
 * @pName proback
 * @name MaxExceptionsService
 * @user HongWei
 * @date 2018/8/5
 * @desc
 */
package com.wanhao.proback.service.finance;

import com.wanhao.proback.bean.finance.MaxExceptions;
import com.wanhao.proback.service.BaseService;
import org.springframework.scheduling.annotation.Async;

/**
 * 重要异常日志业务接口
 */
public interface MaxExceptionsService extends BaseService<MaxExceptions> {
    void asyncInsert(MaxExceptions maxExceptions);
}
