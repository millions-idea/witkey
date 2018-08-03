/***
 * @pName proback
 * @name BusinessBrandsController
 * @user HongWei
 * @date 2018/8/3
 * @desc
 */
package com.wanhao.proback.controller.member;

import com.wanhao.proback.bean.member.BusinessBrands;
import com.wanhao.proback.service.member.BusinessBrandsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/business-brands")
public class BusinessBrandsController {
    @Autowired
    private BusinessBrandsService businessBrandsService;

    /**
     * 根据查询条件获取品牌电商公司数据 韦德 2018年8月3日11:48:50
     * @param condition
     * @return
     */
    @RequestMapping("/get")
    @ResponseBody
    public List<BusinessBrands> getBusinesses(String condition){
        return businessBrandsService.getBusinessesBy(condition);
    }
}
