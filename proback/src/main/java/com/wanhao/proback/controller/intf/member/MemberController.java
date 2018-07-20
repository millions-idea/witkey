package com.wanhao.proback.controller.intf.member;

import com.google.gson.JsonObject;
import com.wanhao.proback.bean.member.Member;
import com.wanhao.proback.service.member.MemberService;
import com.wanhao.proback.utils.Base64Utils;
import com.wanhao.proback.utils.Constants;
import com.wanhao.proback.utils.IpUtils;
import com.wanhao.proback.utils.RSAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

/**
 * Created by LiuLiHao on 2018/7/20 21:30.
 * 描述：会员接口
 * 作者： LiuLiHao
 */
@RequestMapping(value = "member")
@Controller("intf_member")
public class MemberController {

    @Autowired
    MemberService memberService;

    /**
     * 注册
     * @param is_seller
     * @param username
     * @param password
     * @param real_name
     * @param email
     * @param mobile
     * @param qq
     * @param invite_id
     * @param sheng
     * @param shi
     * @return
     */
    @PostMapping(value = "register")
    @ResponseBody
    public JsonObject register(HttpServletRequest request,Integer is_seller,String username,
                         String password, String real_name,
                         String email,String mobile,
                         String qq,Integer invite_id,
                         String sheng,String shi){
        JsonObject jsonObject = new JsonObject();

        Member member = new Member();
        member.setIs_seller(is_seller);
        member.setMobile(mobile);
        //判断用户是否存在
        List<Member> temp = memberService.loginMember(member);
        if (temp!=null && temp.size()>0){
            jsonObject.addProperty(Constants.ERROR,1);
            jsonObject.addProperty(Constants.MESSAGE,"该手机号已经注册!");
            return jsonObject;
        }

        //rsa 解密
        byte[] decode;
        try {
            decode = Base64Utils.decode(password);
            byte[] content = RSAUtil.decryptByPrivateKey(decode, Constants.PRI_KEY);
            //解密结果
            password = new String(content);
        } catch (Exception e) {

        }

        member.setPassword(password);
        member.setReal_name(real_name);
        member.setEmail(email);
        member.setMobile(mobile);
        member.setQq(qq);
        member.setSheng(sheng);
        member.setShi(shi);
        if (invite_id!=null){
            member.setInvite_id(invite_id);
        }
        //记录注册ip
        String ipAdrress = IpUtils.getIpAdrress(request);
        member.setRegist_ip(ipAdrress);


        memberService.addMember(member);
        jsonObject.addProperty("error","0");
        return jsonObject;
    }

    /**
     * 登录
     * @return
     */
    @RequestMapping(value = "login")
    @ResponseBody
    public JsonObject login(HttpServletRequest request, String username, String password){

        JsonObject jsonObject = new JsonObject();

        //记录登录ip
        String ipAdrress = IpUtils.getIpAdrress(request);
        //rsa 解密
        byte[] decode;
        try {
            decode = Base64Utils.decode(password);
            byte[] content = RSAUtil.decryptByPrivateKey(decode, Constants.PRI_KEY);
            //解密结果
            password = new String(content);
            Member member = new Member();
            member.setUsername(username);
            member.setPassword(password);

            List<Member> members = memberService.loginMember(member);
            if (members!=null && members.size()>0){
                //登录成功
                Member save = members.get(0);
                String location = IpUtils.getLocation(ipAdrress);
                save.setLogin_ip(ipAdrress + location);

                //uuid返回给前台
                String uuid = UUID.randomUUID().toString();
                member.setToken(uuid);
                jsonObject.addProperty(Constants.TOKEN,uuid);
                jsonObject.addProperty(Constants.ERROR,0);
                jsonObject.addProperty(Constants.MESSAGE,"登录成功!");
            }

        } catch (Exception e) {
            jsonObject.addProperty(Constants.ERROR,1);
            jsonObject.addProperty(Constants.MESSAGE,"密码错误!");
        }

        return jsonObject;
    }



}
