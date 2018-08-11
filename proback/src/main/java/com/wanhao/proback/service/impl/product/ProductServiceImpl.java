/***
 * @pName proback
 * @name ProductServiceImpl
 * @user HongWei
 * @date 2018/8/11
 * @desc
 */
package com.wanhao.proback.service.impl.product;

import com.google.common.base.Joiner;
import com.wanhao.proback.bean.product.*;
import com.wanhao.proback.dao.product.ProductAttributeMapper;
import com.wanhao.proback.dao.product.ProductAttributeOptionMapper;
import com.wanhao.proback.dao.product.ProductMapper;
import com.wanhao.proback.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl  implements ProductService {
    private final ProductMapper productMapper;
    private final ProductAttributeMapper productAttributeMapper;
    private final ProductAttributeOptionMapper productAttributeOptionMapper;

    @Autowired
    public ProductServiceImpl(ProductMapper productMapper, ProductAttributeMapper productAttributeMapper, ProductAttributeOptionMapper productAttributeOptionMapper) {
        this.productMapper = productMapper;
        this.productAttributeMapper = productAttributeMapper;
        this.productAttributeOptionMapper = productAttributeOptionMapper;
    }


    @Override
    public void update(Product v) {

    }

    @Override
    public void add(Product v) {

    }

    @Override
    public void delete(Integer id) {

    }

    /**
     * 查询 韦德 2018年8月11日16:22:55
     * @param code
     * @return
     */
    @Override
    public ProductView getProductByCode(String code) {
        // 查询商品
        Product product = productMapper.selectByCode(code);
        if(product == null) return null;

        ProductView productView = new ProductView();
        productView.setProduct(product);

        // 查询属性
        List<ProductAttribute> attributes = productAttributeMapper.selectByCode(product.getProduct_code());
        if(!attributes.isEmpty()){
            // 查询属性选项
            List<Integer> attributeIdList = attributes.stream().map(item -> item.getAttr_id()).collect(Collectors.toList());
            String attributeIdJoin = Joiner.on(",").skipNulls().join(attributeIdList);
            List<ProductAttributeOption> attributeOptions = productAttributeOptionMapper.selectByAttrId(attributeIdJoin);
            attributes.stream().forEach(attr -> {
                List<ProductAttributeOption> options = attributeOptions.stream().filter(item -> item.getAttr_id() == attr.getAttr_id()).collect(Collectors.toList());
                attr.setOptions(options);
            });
            productView.setAttributes(attributes);
        }
        return productView;
    }
}
