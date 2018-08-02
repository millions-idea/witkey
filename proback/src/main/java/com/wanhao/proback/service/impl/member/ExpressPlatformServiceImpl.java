/***
 * @pName proback
 * @name ExpressPlatformServiceImpl
 * @user HongWei
 * @date 2018/8/1
 * @desc
 */
package com.wanhao.proback.service.impl.member;

import com.wanhao.proback.bean.member.ExpressPlatform;
import com.wanhao.proback.dao.member.ExpressPlatformMapper;
import com.wanhao.proback.service.member.ExpressPlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpressPlatformServiceImpl implements ExpressPlatformService {

    private final ExpressPlatformMapper expressPlatformMapper;

    @Autowired
    public ExpressPlatformServiceImpl(ExpressPlatformMapper expressPlatformMapper) {
        this.expressPlatformMapper = expressPlatformMapper;
    }

    /**
     * 查询所有平台集合 韦德 2018年8月1日14:42:24
     *
     * @return
     * @param page
     * @param limit
     */
    @Override
    public List<ExpressPlatform> getPlatforms(Integer page, Integer limit) {
        page = page - 1;
        return expressPlatformMapper.selectLimit(page, limit);
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
