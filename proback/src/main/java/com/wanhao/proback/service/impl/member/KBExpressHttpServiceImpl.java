/***
 * @pName proback
 * @name KongBaoExpressNetServiceImpl
 * @user HongWei
 * @date 2018/8/8
 * @desc
 */
package com.wanhao.proback.service.impl.member;


import com.wanhao.proback.bean.member.ExpressHttpParam;
import com.wanhao.proback.service.member.ExpressHttpService;
import com.wanhao.proback.utils.JsonUtil;
import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.http.Header;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * 空包网快递系统网络请求服务实现类
 */
@Service("KBExpressHttpServiceImpl")
public class KBExpressHttpServiceImpl extends ExpressHttpServiceImpl implements ExpressHttpService {
    /**
     * 购买快递 韦德 2018年8月8日22:21:08
     *
     * @param expressHttpParam
     * @return
     */
    @Override
    public String buyExpress(ExpressHttpParam expressHttpParam) {
        return null;
    }

}
