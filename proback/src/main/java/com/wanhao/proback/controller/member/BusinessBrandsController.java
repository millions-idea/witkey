/***
 * @pName proback
 * @name BusinessBrandsController
 * @user HongWei
 * @date 2018/8/3
 * @desc
 */
package com.wanhao.proback.controller.member;

import com.wanhao.proback.bean.member.BusinessBrands;
import com.wanhao.proback.bean.member.ExpressPlatform;
import com.wanhao.proback.bean.util.JsonArrayResult;
import com.wanhao.proback.bean.util.JsonResult;
import com.wanhao.proback.service.member.BusinessBrandsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/business-brands")
public class BusinessBrandsController {
    @Autowired
    private BusinessBrandsService businessBrandsService;

    @GetMapping
    public String index(){
        return "v2/business-brands/index";
    }

    /**
     * 根据查询条件获取品牌电商公司数据 韦德 2018年8月3日11:48:50
     * @param condition
     * @return
     */
    @RequestMapping("/getLimit")
    @ResponseBody
    public JsonArrayResult<BusinessBrands> getBusinessLimit(Integer page, String limit, String condition){
        List<BusinessBrands> list = businessBrandsService.getBusinessesLimit(page, limit, condition);
        if (condition == null || condition.isEmpty()){
            int count = businessBrandsService.getBrandsCount();
            JsonArrayResult jsonArrayResult = new JsonArrayResult(0, list);
            jsonArrayResult.setCount(count);
            return jsonArrayResult;
        }
        return new JsonArrayResult(0, list);
    }

    @RequestMapping("/get")
    @ResponseBody
    public JsonArrayResult<BusinessBrands> getBusinesses(String condition){
        return new JsonArrayResult(0, businessBrandsService.getBusinessesBy(condition));
    }

    /**
     * 预览品牌信息 韦德 2018年8月3日23:11:37
     * @param param
     * @param model
     * @return
     */
    @GetMapping("/view")
    public String view(BusinessBrands param, final Model model){
        model.addAttribute("model", param);
        return "v2/business-brands/view";
    }

    /**
     * 编辑视图 韦德 2018年8月3日23:14:52
     * @param param
     * @param model
     * @return
     */
    @GetMapping("/editView")
    public String editView(BusinessBrands param, final Model model){
        model.addAttribute("model", param);
        return "v2/business-brands/edit";
    }

    /**
     * 编辑电商品牌 韦德 2018年8月3日23:16:14
     * @param param
     * @return
     */
    @PostMapping("/edit")
    @ResponseBody
    public JsonResult edit(BusinessBrands param){
        businessBrandsService.update(param);
        return new JsonResult(0);
    }

    /**
     * 删除品牌 韦德 2018年8月3日23:23:07
     * @param id
     * @return
     */
    @GetMapping("/delete")
    @ResponseBody
    public JsonResult delete(String id){
        businessBrandsService.deleteBy(id);
        return new JsonResult(0);
    }

    /**
     * 添加视图 韦德 2018年8月3日23:31:21
     * @return
     */
    @GetMapping("/addView")
    public String addView(){
        return "v2/business-brands/add";
    }


    /**
     * 添加品牌 2018年8月3日23:33:42
     * @param param
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public JsonResult add(BusinessBrands param) {
        businessBrandsService.add(param);
        return new JsonResult(0);
    }
}
