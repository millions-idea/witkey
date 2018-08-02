/***
 * @pName proback
 * @name ExpressPlatformService
 * @user HongWei
 * @date 2018/8/1
 * @desc 快递空包平台服务类
 */
package com.wanhao.proback.service.member;

import com.wanhao.proback.bean.member.ExpressPlatform;
import com.wanhao.proback.service.BaseService;

import java.util.List;

/**
 * 快递空包平台服务类
 */
public interface ExpressPlatformService extends BaseService<ExpressPlatform> {
    /**
     * 查询所有平台集合 韦德 2018年8月1日14:42:24
     * @return
     * @param page
     * @param limit
     * @param condition
     */
    List<ExpressPlatform> getPlatforms(Integer page, String limit, String condition);

    void delete(String exp_id);

    /**
     * 查询记录总数 韦德 2018年8月2日18:53:51
     * @return
     */
    int getPlatformCount();
}
