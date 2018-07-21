package com.wanhao.proback.controller.intf.member;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.wanhao.proback.annotaion.ISLogin;
import com.wanhao.proback.bean.member.Member;
import com.wanhao.proback.bean.member.MemberBank;
import com.wanhao.proback.bean.member.MemberTaoBao;
import com.wanhao.proback.bean.shop.Shop;
import com.wanhao.proback.service.member.MemberBankService;
import com.wanhao.proback.service.member.MemberService;
import com.wanhao.proback.service.member.MemberTaoBaoService;
import com.wanhao.proback.service.shop.ShopService;
import com.wanhao.proback.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

/**
 * Created by LiuLiHao on 2018/7/20 21:30.
 * 描述：会员接口
 * 作者： LiuLiHao
 */
@RequestMapping(value = "intf_member")
@Controller("intf_member")
public class MemberController {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberTaoBaoService taoBaoService;

    @Autowired
    MemberBankService bankService;
    /**
     * 注册
     *
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
    public void register(HttpServletRequest request, Integer is_seller, String username,
                         String password, String real_name,
                         String email, String mobile,
                         String qq, Integer invite_id,
                         String sheng, String shi, HttpServletResponse response) {
        JsonObject jsonObject = new JsonObject();

        if (IsNullUtils.isContainsNull(username,password,real_name,email,mobile,qq,sheng,shi)  ) {
            //信息不完整
            ResponseUtils.retnFailMsg(response, jsonObject);
            return;
        }
        Member member = new Member();
        member.setIs_seller(is_seller);
        member.setMobile(mobile);
        //判断用户是否存在
        List<Member> temp = memberService.loginMember(member);
        if (temp != null && temp.size() > 0) {
            jsonObject.addProperty(Constants.ERROR, 1);
            jsonObject.addProperty(Constants.MESSAGE, "该手机号已经注册!");
            ResponseUtils.renderJson(response, jsonObject.toString());
            return;
        }

        //rsa 解密
//        byte[] decode;
//        try {
//            decode = Base64Utils.decode(password);
//            byte[] content = RSAUtil.decryptByPrivateKey(decode, Constants.PRI_KEY);
//            //解密结果
//            password = new String(content);
//        } catch (Exception e) {
//
//        }

        member.setPassword(password);
        member.setReal_name(real_name);
        member.setEmail(email);
        member.setMobile(mobile);
        member.setQq(qq);
        member.setSheng(sheng);
        member.setShi(shi);
        if (invite_id != null) {
            member.setInvite_id(invite_id);
        }
        //记录注册ip
        String ipAdrress = IpUtils.getIpAdrress(request);
        member.setRegist_ip(ipAdrress);


        memberService.addMember(member);
        jsonObject.addProperty("error", "0");
        ResponseUtils.renderJson(response, jsonObject.toString());
    }

    /**
     * 登录
     *
     * @return
     */
    @PostMapping(value = "login")
    public void login(HttpServletRequest request, HttpServletResponse response, String username, String password) {

        JsonObject jsonObject = new JsonObject();
        //字段不能为空
        if (IsNullUtils.isContainsNull(username,password)) {
            jsonObject.addProperty(Constants.ERROR, "1");
            jsonObject.addProperty(Constants.MESSAGE, "密码错误!");
            ResponseUtils.renderJson(response, jsonObject.toString());
            return;
        }
        //记录登录ip
        String ipAdrress = IpUtils.getIpAdrress(request);
        //rsa 解密
        byte[] decode;
        try {
            decode = Base64Utils.decode(password);
            byte[] content = RSAUtil.decryptByPrivateKey(decode, Constants.PRI_KEY);
            //解密结果
            ///password = new String(content);
            Member member = new Member();
            member.setUsername(username);
            member.setPassword(password);

            List<Member> members = memberService.loginMember(member);
            if (members != null && members.size() > 0) {
                //登录成功
                Member save = members.get(0);
                String location = IpUtils.getLocation(ipAdrress);
                //保存登录ip
                save.setLogin_ip(ipAdrress + location);

                //uuid返回给前台
                String uuid = UUID.randomUUID().toString();
                save.setToken(uuid);

                //暂时保存到context
                ServletContext servletContext = request.getServletContext();
                servletContext.setAttribute(Constants.TOKEN, uuid);

                jsonObject.addProperty(Constants.TOKEN, uuid);
                jsonObject.addProperty(Constants.ERROR, "0");
                jsonObject.addProperty(Constants.MESSAGE, "登录成功!");
                //保存到session
                request.getSession().setAttribute(Constants.USER, save);

                //返回用户信息
                Gson gson = new Gson();
                save.setPassword(null);
                String mem = gson.toJson(save);

                jsonObject.addProperty("member", mem);

            } else {
                jsonObject.addProperty(Constants.ERROR, "1");
                jsonObject.addProperty(Constants.MESSAGE, "密码错误!");
            }

        } catch (Exception e) {
            jsonObject.addProperty(Constants.ERROR, "1");
            jsonObject.addProperty(Constants.MESSAGE, "密码错误!");
        }

        ResponseUtils.renderJson(response, jsonObject.toString());
        // return jsonObject;
    }


    /**
     * 实名认证
     *
     * @param request
     * @param response
     * @param realname
     * @param idcard
     * @param url1
     * @param url2
     * @param url3
     */
    @PostMapping(value = "realName")
    @ISLogin
    public void realName(HttpServletRequest request, HttpServletResponse response,
                         String realname, String idcard,
                         String url1, String url2,
                         String url3) {
        JsonObject jsonObject = new JsonObject();

        if (IsNullUtils.isContainsNull(realname,idcard,url1,url2,url3)) {
            //信息不完整
            ResponseUtils.retnFailMsg(response, jsonObject);
            return;
        }
        //保存信息
        Member member = (Member) request.getSession().getAttribute(Constants.USER);
        member.setReal_name(realname);
        member.setId_card(idcard);
        member.setZheng(url1);
        member.setFan(url2);
        member.setShou_chi(url3);
        memberService.updateMember(member);
        ResponseUtils.retnSuccessMsg(response, jsonObject);
    }


    /**
     * 绑定买号
     * 买家号
     *
     * @param request
     * @param response
     * @param catid
     * @param account
     * @param url1
     * @param url2
     * @param url3
     * @param url4
     * @param url5
     * @param account_gender
     * @param honor
     * @param age_range
     * @param taoqi
     * @param buy_class
     * @param truename
     * @param mobile
     * @param province
     * @param city
     * @param address
     */
    @ISLogin
    @PostMapping(value = "bindBuyAccount")
    public void bindBuyAccount(HttpServletRequest request, HttpServletResponse response,
                               Integer catid, String account,
                               String url1, String url2,
                               String url3, String url4,
                               String url5, String account_gender,
                               String honor, String age_range, Integer taoqi,
                               Integer[] buy_class, String truename,
                               String mobile, String province,
                               String city, String address) {
        JsonObject jsonObject = new JsonObject();

        if (catid == null || IsNullUtils.isContainsNull(account, account_gender, honor, age_range, truename, mobile, province, city, address) ){
            //信息不完整
            ResponseUtils.retnFailMsg(response, jsonObject);
            return;
        }

        //保存信息
        Member member = (Member) request.getSession().getAttribute(Constants.USER);

        MemberTaoBao memberTaoBao = new MemberTaoBao();

        //判断这个号是否已经绑定过了
        memberTaoBao.setAccount(account);
        switch (catid){
            case 4://淘宝
                memberTaoBao.setAccount_type("淘宝试用");
                //拼接类别
                StringBuilder sb = new StringBuilder();
                if (buy_class!=null && buy_class.length>0){
                    for (int i=0;i<buy_class.length;i++){
                        if (i==buy_class.length-1){
                            sb.append(buy_class[i]);
                        }else {
                            sb.append(buy_class[i]).append(",");
                        }
                    }
                }
                break;
            case 5://阿里巴巴
                memberTaoBao.setAccount_type("阿里巴巴试用");
                break;
            case 40://京东
                memberTaoBao.setAccount_type("京东试用");
                break;
            case 6://拼多多
                memberTaoBao.setAccount_type("拼多多试用");
                break;
            case 7://蘑菇街试用
                memberTaoBao.setAccount_type("蘑菇街试用");
                break;
            case 8://美丽说试用
                memberTaoBao.setAccount_type("美丽说试用");
                break;
        }
        List<MemberTaoBao> list = taoBaoService.queryMemberBuyList(memberTaoBao);
        if (list!=null && list.size()>0){
            //已经绑定过了
            jsonObject.addProperty(Constants.ERROR,1);
            jsonObject.addProperty(Constants.MESSAGE,"该账号已经绑定过了! 不要重复绑定");
        }
        //会员Id
        Integer mem_id = member.getId();
        memberTaoBao.setMem_id(mem_id);

        memberTaoBao.setAccount_gender(account_gender);
        memberTaoBao.setAddress(address);
        memberTaoBao.setCity(city);
        memberTaoBao.setPro_name(province);
        memberTaoBao.setHonor(honor);
        memberTaoBao.setTruename(truename);
        memberTaoBao.setTaoqi(taoqi);
        //实名截图
        memberTaoBao.setShoot_real_name(url1);
        //芝麻信用
        memberTaoBao.setShoot_zhi_ma(url2);
        //信用截图
        memberTaoBao.setShoot_honor(url3);
        //淘气值截图
        memberTaoBao.setShoot_taoqi(url4);
        //花呗额度截图
        memberTaoBao.setShoot_huabei(url5);
        taoBaoService.addMemberTaoBao(memberTaoBao);
        //成功
        ResponseUtils.retnSuccessMsg(response,jsonObject,"操作完成,下一步由管理员审核");
    }

    /**
     * 查询买号列表
     */
    @ISLogin
    @RequestMapping(value = "queryBuyAccountList")
    public void queryBuyAccountList(Integer memid,HttpServletRequest request, HttpServletResponse response){
        JsonObject jsonObject = new JsonObject();

        if (memid!=null){
            MemberTaoBao memberTaoBao = new MemberTaoBao();
            memberTaoBao.setMem_id(memid);
            List<MemberTaoBao> list = taoBaoService.queryMemberBuyList(memberTaoBao);
            String s = GsonUtils.toJson(list);
            jsonObject.addProperty("list",s);
            //返回
            ResponseUtils.retnSuccessMsg(response,jsonObject);
        }

    }

    /**
     * 绑定银行卡
     */
    @ISLogin
    @PostMapping(value = "bindBank")
    public void bindBank(HttpServletRequest request, HttpServletResponse response,
                         String bank_type,String bank_num,
                         String bank_mobile,String bank_username,
                         String bank_id_card,Integer memid){
        JsonObject jsonObject = new JsonObject();

        if (memid == null || IsNullUtils.isContainsNull(bank_type, bank_num, bank_mobile, bank_username
                , bank_id_card) ){
            //信息不完整
            ResponseUtils.retnFailMsg(response, jsonObject);
            return;
        }
        //是否已经绑定过
        MemberBank bank = new MemberBank();
        bank.setBank_num(bank_num);

        List<MemberBank> list = bankService.findByPages(bank);
        if (list!=null && list.size()>0){
            ResponseUtils.retnFailMsg(response,jsonObject,"已经绑定过了,不能重复绑定");
            return;
        }

        bank.setMem_id(memid);
        bank.setBank_type(bank_type);
        bank.setBank_mobile(bank_mobile);
        bank.setBank_username(bank_username);
        bank.setBank_id_card(bank_id_card);
        bankService.add(bank);

        ResponseUtils.retnSuccessMsg(response,jsonObject,"已提交,由管理员审核");
    }



    @Autowired
    ShopService shopService;

    @ISLogin
    @PostMapping(value = "bindShop")
    public void bindShop(HttpServletRequest request, HttpServletResponse response,
                         String shop_type,String shop_name,
                         String shop_url,String shop_wangwang,
                         String send_province,String send_city,
                         String send_town,String shop_img,Integer memid){

        JsonObject jsonObject = new JsonObject();

        if (IsNullUtils.isContainsNull(shop_type, shop_name, shop_url, shop_wangwang
                , send_province,send_city,send_town,shop_img) ){
            //信息不完整
            ResponseUtils.retnFailMsg(response, jsonObject);
            return;
        }
        //是否已经绑定过
        Shop shop = new Shop();
        shop.setShop_url(shop_url);
        shop.setShop_name(shop_name);

        List<Shop> shops = shopService.getShops(shop);
        if (shops!=null && shops.size()>0){
            ResponseUtils.retnFailMsg(response,jsonObject,"该店铺已经绑定过,不能重复绑定");
            return;
        }
        shop.setShop_type(shop_type);
        shop.setShop_wangwang(shop_wangwang);
        shop.setSend_city(send_city);
        shop.setSend_town(send_town);
        shop.setSend_province(send_province);
        shop.setMem_id(memid);

        //保存
        shopService.add(shop);
        ResponseUtils.retnSuccessMsg(response,jsonObject,"申请完成,等待审核");

    }


    /**
     * 绑定收款号
     */
    @ISLogin
    @PostMapping(value = "bindCash")
    public void bindCash(HttpServletRequest request, HttpServletResponse response,
                         String alipay,String bank_type,
                         String bank_num,String bank_province,
                         String bank_city,String bank_addr,Integer memid){
        JsonObject jsonObject = new JsonObject();

        if (memid == null || IsNullUtils.isContainsNull(bank_type,bank_province, bank_num, bank_city, bank_addr
                , alipay) ){
            //信息不完整
            ResponseUtils.retnFailMsg(response, jsonObject);
            return;
        }
        //是否已经绑定过
        MemberBank bank = new MemberBank();
        bank.setBank_num(bank_num);

//        List<MemberBank> list = bankService.findByPages(bank);
//        if (list!=null && list.size()>0){
//            ResponseUtils.retnFailMsg(response,jsonObject,"已经绑定过了,不能重复绑定");
//            return;
//        }

        bank.setMem_id(memid);
        bank.setBank_province(bank_province);
        bank.setBank_city(bank_city);
        bank.setBank_addr(bank_addr);
        bank.setBank_type(bank_type);
        bank.setAlipay(alipay);
        bankService.add(bank);

        ResponseUtils.retnSuccessMsg(response,jsonObject,"已提交,由管理员审核");
    }

}
