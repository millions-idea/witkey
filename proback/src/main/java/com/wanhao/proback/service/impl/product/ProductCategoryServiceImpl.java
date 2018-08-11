/***
 * @pName proback
 * @name ProductCategoryServiceImpl
 * @user HongWei
 * @date 2018/8/10
 * @desc
 */
package com.wanhao.proback.service.impl.product;

import com.wanhao.proback.bean.product.ProductCategory;
import com.wanhao.proback.dao.product.ProductCategoryMapper;
import com.wanhao.proback.service.BaseServiceImpl;
import com.wanhao.proback.service.product.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    private final ProductCategoryMapper productCategoryMapper;

    @Autowired
    public ProductCategoryServiceImpl(ProductCategoryMapper productCategoryMapper) {
        this.productCategoryMapper = productCategoryMapper;
    }

    @Override
    public List<ProductCategory> getCategoryList() {
        return productCategoryMapper.selectAll();
    }

    @Override
    public void update(ProductCategory v) {

    }

    @Override
    public void add(ProductCategory v) {

    }

    @Override
    public void delete(Integer id) {

    }
}
