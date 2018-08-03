/***
 * @pName proback
 * @name BusinessesBrandsServiceImpl
 * @user HongWei
 * @date 2018/8/3
 * @desc
 */
package com.wanhao.proback.service.impl.member;

import com.wanhao.proback.bean.member.BusinessBrands;
import com.wanhao.proback.bean.member.ExpressGoodsView;
import com.wanhao.proback.dao.member.BusinessBrandsMapper;
import com.wanhao.proback.dao.utils.ConditionUtil;
import com.wanhao.proback.service.member.BusinessBrandsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessesBrandsServiceImpl implements BusinessBrandsService {
    private final BusinessBrandsMapper businessBrandsMapper;

    @Autowired
    public BusinessesBrandsServiceImpl(BusinessBrandsMapper businessBrandsMapper) {
        this.businessBrandsMapper = businessBrandsMapper;
    }

    /**
     * 获取电商公司集合 韦德 2018年8月3日11:37:57
     *
     * @param condition
     * @return
     */
    @Override
    public List<BusinessBrands> getBusinessesBy(String condition) {
        String where = " AND 1=1";
        if(condition != null) {
            where = " AND (`name` LIKE '%" + condition + "' OR  `name` LIKE '" + condition + "%' OR `name` LIKE '%" + condition + "%' OR business_id LIKE '%" + condition + "%')";
        }
        return businessBrandsMapper.selectBy(where);
    }

    /**
     * 获取电商公司集合 韦德 2018年8月3日11:37:57
     *
     * @param page
     * @param limit
     * @param condition
     * @return
     */
    @Override
    public List<BusinessBrands> getBusinessesLimit(Integer page, String limit, String condition) {
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
            where += " AND (" + ConditionUtil.like("name", condition, false, null);
            where += " OR business_id LIKE '%" + condition + "%')";
        }
        return businessBrandsMapper.selectLimit(page, limit, where);
    }

    /**
     * 删除品牌 韦德 2018年8月3日23:24:13
     *
     * @param id
     */
    @Override
    public void deleteBy(String id) {
        int res = businessBrandsMapper.deleteBy(id);
        if(res <= 0) throw new RuntimeException("删除失败");
    }

    /**
     * 获取记录总数 韦德 2018年8月3日23:56:22
     *
     * @return
     */
    @Override
    public int getBrandsCount() {
        return businessBrandsMapper.count();
    }

    @Override
    public void update(BusinessBrands v) {
        int res = businessBrandsMapper.updateSingle(v);
        if(res <= 0) throw new RuntimeException("编辑失败");
    }

    @Override
    public void add(BusinessBrands v) {
        int res = businessBrandsMapper.insertSingle(v);
        if(res <= 0) throw new RuntimeException("添加失败");
    }

    @Override
    public void delete(Integer id) {

    }
}
