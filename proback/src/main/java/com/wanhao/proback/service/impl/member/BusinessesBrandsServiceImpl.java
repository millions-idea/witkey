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
        String where = " AND isEnable=1";
        if(condition != null) {
            where = " AND `name` LIKE '%" + condition + "%' OR business_id LIKE '%" + condition + "%'";
        }
        return businessBrandsMapper.selectBy(where);
    }
}
