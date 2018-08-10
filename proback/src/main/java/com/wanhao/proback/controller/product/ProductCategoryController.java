/***
 * @pName proback
 * @name ProductCategoryController
 * @user HongWei
 * @date 2018/8/10
 * @desc
 */
package com.wanhao.proback.controller.product;

import com.wanhao.proback.bean.product.ProductCategory;
import com.wanhao.proback.bean.util.JsonArrayResult;
import com.wanhao.proback.service.product.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/product-category")
public class ProductCategoryController {
    @Autowired
    private ProductCategoryService productCategoryService;


    @GetMapping("/web/get")
    @ResponseBody
    public JsonArrayResult<ProductCategory> getList(){
        return new JsonArrayResult(0, productCategoryService.getCategoryList());
    }

}
