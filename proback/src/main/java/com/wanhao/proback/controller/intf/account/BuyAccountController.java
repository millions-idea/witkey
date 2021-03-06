package com.wanhao.proback.controller.intf.account;

import com.google.gson.JsonObject;
import com.wanhao.proback.annotaion.ISLogin;
import com.wanhao.proback.bean.member.MemberTaoBao;
import com.wanhao.proback.service.member.MemberTaoBaoService;
import com.wanhao.proback.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by LiuLiHao on 2018/7/23 12:03.
 * 描述：买号操作
 * 作者： LiuLiHao
 */
@RequestMapping("intf_account")
@Controller(value = "intf_account")
public class BuyAccountController {

    @Autowired
    MemberTaoBaoService taoBaoService;

    /**
     * 删除买号
     */
    @ISLogin
    @PostMapping(value = "deleteBuyAccount")
    public void deleteBuyAccount(HttpServletRequest request, HttpServletResponse response,
                                 Integer account_id){
        JsonObject jsonObject = new JsonObject();

        if (account_id!=null){
            taoBaoService.delete(account_id);
            ResponseUtils.retnSuccessMsg(response,jsonObject,"删除完成!");
            return;
        }
        ResponseUtils.retnFailMsg(response,jsonObject,"请选择要删除的买号!");
        return;
    }


    /**
     * 改变买号状态
     */
    @ISLogin
    @RequestMapping(value = "disableAccount")
    public void disableAccount(HttpServletRequest request, HttpServletResponse response,
                               Integer account_id){
        JsonObject jsonObject = new JsonObject();
        if (account_id!=null){
            MemberTaoBao taoBao = new MemberTaoBao();
            Integer disable = taoBao.getDisable();
            if (disable==null){
                taoBao.setDisable(0);
            }else {
                taoBao.setDisable(disable==1?0:1);
            }
            taoBao.setId(account_id);
            //保存更新
            taoBaoService.update(taoBao);
            ResponseUtils.retnSuccessMsg(response,jsonObject,"修改完成");
        }else {
            ResponseUtils.retnFailMsg(response,jsonObject);
        }
    }

}
