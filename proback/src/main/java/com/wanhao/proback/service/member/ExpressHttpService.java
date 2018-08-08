/***
 * @pName proback
 * @name ExpressNetService
 * @user HongWei
 * @date 2018/8/8
 * @desc
 */
package com.wanhao.proback.service.member;

import com.wanhao.proback.bean.member.ExpressHttpParam;

/**
 * 第三方快递系统网络请求服务
 */
public interface ExpressHttpService {
    /**
     * 购买快递 韦德 2018年8月8日22:21:08
     * @param expressHttpParam
     * @return
     */
    String buyExpress(ExpressHttpParam expressHttpParam);
}
