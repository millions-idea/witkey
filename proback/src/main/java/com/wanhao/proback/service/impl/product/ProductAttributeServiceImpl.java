/***
 * @pName proback
 * @name ProductAttributeServiceImpl
 * @user HongWei
 * @date 2018/8/11
 * @desc
 */
package com.wanhao.proback.service.impl.product;

import com.wanhao.proback.bean.product.ProductAttribute;
import com.wanhao.proback.dao.product.ProductAttributeMapper;
import com.wanhao.proback.service.product.ProductAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductAttributeServiceImpl implements ProductAttributeService {
    private final ProductAttributeMapper productAttributeMapper;

    @Autowired
    public ProductAttributeServiceImpl(ProductAttributeMapper productAttributeMapper) {
        this.productAttributeMapper = productAttributeMapper;
    }


    @Override
    public void update(ProductAttribute v) {

    }

    @Override
    public void add(ProductAttribute v) {

    }

    @Override
    public void delete(Integer id) {

    }

    /**
     * 根据商品id查询属性列表 韦德 2018年8月11日16:27:37
     *
     * @param productCode
     * @return
     */
    @Override
    public List<ProductAttribute> getAttributesByCode(String productCode) {
        return productAttributeMapper.selectByCode(productCode);
    }
}
