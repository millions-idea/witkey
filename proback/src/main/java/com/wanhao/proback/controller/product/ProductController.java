/***
 * @pName proback
 * @name ProductController
 * @user HongWei
 * @date 2018/8/11
 * @desc
 */
package com.wanhao.proback.controller.product;

import com.wanhao.proback.bean.product.ProductCategory;
import com.wanhao.proback.bean.product.ProductView;
import com.wanhao.proback.bean.product.TryType;
import com.wanhao.proback.bean.util.JsonArrayResult;
import com.wanhao.proback.bean.util.JsonResult;
import com.wanhao.proback.service.product.ProductService;
import com.wanhao.proback.service.product.TryTypeService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private TryTypeService tryTypeService;

    @Autowired
    private ProductService productService;


    @GetMapping("/web/getTryTypeList")
    @ResponseBody
    public JsonArrayResult<TryType> getTryTypeList(){
        return new JsonArrayResult(0, tryTypeService.selectAll());
    }


    @GetMapping("/web/getProductByCode")
    @ResponseBody
    public ProductView getProductByCode(String code){
        return productService.getProductByCode(code);
    }


}
