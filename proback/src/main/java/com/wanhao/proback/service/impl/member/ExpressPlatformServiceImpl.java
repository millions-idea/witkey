/***
 * @pName proback
 * @name ExpressPlatformServiceImpl
 * @user HongWei
 * @date 2018/8/1
 * @desc
 */
package com.wanhao.proback.service.impl.member;

import com.google.common.collect.Lists;
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
     * @param page
     * @param limit
     */
    @Override
    public List<ExpressPlatform> getPlatforms(Integer page, Integer limit) {
        return Lists.newArrayList(
                new ExpressPlatform(1, "空包100网", "http://www.kongbao100.com"),
                new ExpressPlatform(2, "刷宝网", "http://www.shuabaokb.com"),
                new ExpressPlatform(3, "空包100网", "http://www.kongbao100.com"),
                new ExpressPlatform(4, "刷宝网", "http://www.shuabaokb.com"),
                new ExpressPlatform(5, "空包100网", "http://www.kongbao100.com"),
                new ExpressPlatform(6, "刷宝网", "http://www.shuabaokb.com"),
                new ExpressPlatform(7, "空包100网", "http://www.kongbao100.com"),
                new ExpressPlatform(8, "刷宝网", "http://www.shuabaokb.com"),
                new ExpressPlatform(9, "空包100网", "http://www.kongbao100.com"),
                new ExpressPlatform(10, "刷宝网", "http://www.shuabaokb.com"),
                new ExpressPlatform(11, "空包100网", "http://www.kongbao100.com"),
                new ExpressPlatform(12, "刷宝网", "http://www.shuabaokb.com"),
                new ExpressPlatform(13, "空包100网", "http://www.kongbao100.com"),
                new ExpressPlatform(14, "刷宝网", "http://www.shuabaokb.com"),
                new ExpressPlatform(15, "空包100网", "http://www.kongbao100.com"),
                new ExpressPlatform(16, "刷宝网", "http://www.shuabaokb.com"),
                new ExpressPlatform(17, "空包100网", "http://www.kongbao100.com"),
                new ExpressPlatform(18, "刷宝网", "http://www.shuabaokb.com"),
                new ExpressPlatform(19, "空包100网", "http://www.kongbao100.com"),
                new ExpressPlatform(20, "刷宝网", "http://www.shuabaokb.com"),
                new ExpressPlatform(21, "刷宝网", "http://www.shuabaokb.com"),
                new ExpressPlatform(22, "空包100网", "http://www.kongbao100.com"),
                new ExpressPlatform(23, "刷宝网", "http://www.shuabaokb.com"),
                new ExpressPlatform(24, "空包100网", "http://www.kongbao100.com"),
                new ExpressPlatform(25, "刷宝网", "http://www.shuabaokb.com"),
                new ExpressPlatform(26, "空包100网", "http://www.kongbao100.com"),
                new ExpressPlatform(27, "刷宝网", "http://www.shuabaokb.com"),
                new ExpressPlatform(28, "空包100网", "http://www.kongbao100.com"),
                new ExpressPlatform(29, "刷宝网", "http://www.shuabaokb.com"),
                new ExpressPlatform(30, "空包100网", "http://www.kongbao100.com"),
                new ExpressPlatform(31, "刷宝网", "http://www.shuabaokb.com")
        );
    }

    /**
     * 删除快递空包平台(支持多个) 韦德 2018年8月1日23:09:44
     * @param exp_id
     */
    @Override
    public void delete(String exp_id) {

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
