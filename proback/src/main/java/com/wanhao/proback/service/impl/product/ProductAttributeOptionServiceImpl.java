/***
 * @pName proback
 * @name ProductAttributeOptionServiceImpl
 * @user HongWei
 * @date 2018/8/11
 * @desc
 */
package com.wanhao.proback.service.impl.product;

import com.wanhao.proback.bean.product.ProductAttributeOption;
import com.wanhao.proback.dao.product.ProductAttributeOptionMapper;
import com.wanhao.proback.service.product.ProductAttributeOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductAttributeOptionServiceImpl implements ProductAttributeOptionService {
    private final ProductAttributeOptionMapper productAttributeOptionMapper;

    @Autowired
    public ProductAttributeOptionServiceImpl(ProductAttributeOptionMapper productAttributeOptionMapper) {
        this.productAttributeOptionMapper = productAttributeOptionMapper;
    }


    @Override
    public void update(ProductAttributeOption v) {

    }

    @Override
    public void add(ProductAttributeOption v) {

    }

    @Override
    public void delete(Integer id) {

    }

    /**
     * 根据属性id查询属性选项列表 韦德 2018年8月11日16:59:49
     *
     * @param attrId
     * @return
     */
    @Override
    public List<ProductAttributeOption> selectByAttrId(String attrId) {
        return productAttributeOptionMapper.selectByAttrId(attrId);
    }
}
