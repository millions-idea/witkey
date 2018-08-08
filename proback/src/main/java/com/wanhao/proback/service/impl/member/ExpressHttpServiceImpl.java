/***
 * @pName proback
 * @name ExpressNetServiceImpl
 * @user HongWei
 * @date 2018/8/8
 * @desc
 */
package com.wanhao.proback.service.impl.member;

import com.wanhao.proback.bean.member.ExpressHttpParam;
import com.wanhao.proback.service.member.ExpressHttpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 * 第三方快递系统网络服务实现抽象类
 */
public abstract class ExpressHttpServiceImpl implements ExpressHttpService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 购买快递 韦德 2018年8月8日22:21:08
     *
     * @param expressHttpParam
     * @return
     */
    public abstract String buyExpress(ExpressHttpParam expressHttpParam);
}
