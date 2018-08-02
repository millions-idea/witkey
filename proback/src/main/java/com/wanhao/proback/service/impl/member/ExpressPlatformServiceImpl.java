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
     * @param condition
     */
    @Override
    public List<ExpressPlatform> getPlatforms(Integer page, String limit, String condition) {
        // 计算分页位置
        if(!limit.equalsIgnoreCase("-1")){
            page = page - 1;
            if (page != 0){
                page = page * Integer.valueOf(limit);
            }
        }

        // 封装查询条件
        String where = " AND 1=1";
        if(condition != null) {
            where = " AND `name` LIKE '%" + condition + "%' OR expp_id LIKE '%" + condition + "%'";
        }
        return expressPlatformMapper.selectLimit(page, limit, where);
    }

    /**
     * 删除快递空包平台(支持多个) 韦德 2018年8月1日23:09:44
     * @param expp_id
     */
    @Override
    public void delete(String expp_id) {
        int res = expressPlatformMapper.deleteSingle(expp_id);
        if(res <= 0) throw new RuntimeException("删除空包公司失败");
    }

    /**
     * 查询记录总数 韦德 2018年8月2日18:53:51
     *
     * @return
     */
    @Override
    public int getPlatformCount() {
        return expressPlatformMapper.count();
    }


    @Override
    public void update(ExpressPlatform v) {
        int res = expressPlatformMapper.updateSingle(v);
        if(res <= 0) throw new RuntimeException("编辑空包公司失败");
    }

    @Override
    public void add(ExpressPlatform v) {
        int res = expressPlatformMapper.insertSingle(v);
        if(res <= 0) throw new RuntimeException("添加空包公司失败");
    }

    @Override
    public void delete(Integer id) {

    }
}
