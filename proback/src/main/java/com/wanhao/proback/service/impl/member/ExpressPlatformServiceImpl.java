/***
 * @pName proback
 * @name ExpressPlatformServiceImpl
 * @user HongWei
 * @date 2018/8/1
 * @desc
 */
package com.wanhao.proback.service.impl.member;

import com.wanhao.proback.bean.member.ExpressPlatform;
import com.wanhao.proback.service.member.ExpressPlatformService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpressPlatformServiceImpl implements ExpressPlatformService {
    /**
     * 查询所有平台集合 韦德 2018年8月1日14:42:24
     *
     * @return
     */
    @Override
    public List<ExpressPlatform> getPlatforms() {
        return null;
    }

    @Override
    public void update(ExpressPlatform v) {

    }

    @Override
    public void add(ExpressPlatform v) {

    }

    @Override
    public void delete(Integer id) {

    }
}
