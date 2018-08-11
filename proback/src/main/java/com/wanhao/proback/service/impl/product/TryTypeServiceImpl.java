/***
 * @pName proback
 * @name TryTypeServiceImpl
 * @user HongWei
 * @date 2018/8/11
 * @desc
 */
package com.wanhao.proback.service.impl.product;

import com.wanhao.proback.bean.product.TryType;
import com.wanhao.proback.dao.product.TryTypeMapper;
import com.wanhao.proback.service.BaseServiceImpl;
import com.wanhao.proback.service.product.TryTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TryTypeServiceImpl extends BaseServiceImpl<TryType> implements TryTypeService {
    private final TryTypeMapper tryTypeMapper;

    @Autowired
    public TryTypeServiceImpl(TryTypeMapper tryTypeMapper) {
        this.tryTypeMapper = tryTypeMapper;
    }

    /**
     * 查询全部类型 韦德 2018年8月11日12:26:04
     *
     * @return
     */
    @Override
    public List<TryType> selectAll() {
        return tryTypeMapper.selectAll();
    }
}
