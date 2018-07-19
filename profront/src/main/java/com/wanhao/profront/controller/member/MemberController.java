package com.wanhao.profront.controller.member;

import com.wanhao.profront.bean.member.Member;
import com.wanhao.profront.bean.member.MemberTaoBao;
import com.wanhao.profront.bean.member.NameForbidden;
import com.wanhao.profront.service.member.MemberService;
import com.wanhao.profront.service.member.MemberTaoBaoService;
import com.wanhao.profront.service.member.NameForbiddenService;
import com.wanhao.profront.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static com.wanhao.profront.controller.MobileCodeController.MOBILE_CODE;

/**
 * Created by LiuLiHao on 2018/7/16 16:50.
 * 描述：
 * 作者： LiuLiHao
 */
@Controller
@RequestMapping(value = "member")
public class MemberController {

    @Autowired
    NameForbiddenService nameForbiddenService;

    @Autowired
    MemberService memberService;

    @Autowired
    MemberTaoBaoService taoBaoService;

    private static final String PREFIX = "member/";

    /**
     * 跳转到注册
     * @return
     */
    @GetMapping(value = "register")
    public String toRegister(){
        return "member/register";
    }
    /**
     * 注册
     * @return
     */
    @PostMapping(value = "register")
    public String register(Member member,String mobile_code, Model model,HttpSession session){
        //判断手机验证码是否正确 todo
        if (org.apache.commons.lang.StringUtils.isNotBlank(mobile_code)){
            String validateCode= (String) session.getAttribute(MOBILE_CODE);
                if (!mobile_code.equals(validateCode)){
                    //验证码错误
                    model.addAttribute("err","短信验证码不正确");
                    return "member/register";
                }
        }
        //检查是否包含禁用名字
        NameForbidden nameForbidden = nameForbiddenService.query();
        if (!StringUtils.isEmpty(nameForbidden)){
            String[] strings = nameForbidden.getName().split(",");
            if (strings!=null && strings.length>0){
                for (String s : strings) {
                    if (member.getUsername()!=null && member.getUsername().contains(s)){
                        //禁用
                        model.addAttribute("err","禁用的用户名");
                        return "member/register";
                    }
                }
            }
        }
        memberService.addMember(member);
        //判断用户类型 如果是商户 成功直接跳转到登录后台，提示需要先绑定 掌柜账号
        if (member.getIs_seller()==1){
            return "";
        }

        //注册成功暂时返回首页
        return "index";
    }

    /**
     * 到登录
     * @return
     */
    @GetMapping(value = "toLogin")
    public String toLogin(@RequestParam(value = "error", required = false) String error,
                          @RequestParam(value = "logout", required = false) String logout,
                          HttpServletRequest request, Model model){
        if (error != null) {
            model.addAttribute("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
        }
        if (logout != null) {
            model.addAttribute("msg", "你已经成功退出");
        }
        return "member/login";
    }

    /**
     * 登录成功跳转
     * @return
     */
    @RequestMapping(value = "memberIndex")
    public String memberIndex(Model model,HttpSession session){

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();

        Member member = memberService.findByName(username);

        //暂时放入session
        session.setAttribute("member",member);
        session.setAttribute(Constants.USER,username);
        return PREFIX+"index";
    }

    //自定义错误类型
    private String getErrorMessage(HttpServletRequest request, String key){
        Exception exception =
                (Exception) request.getSession().getAttribute(key);
        String error;
        if (exception instanceof BadCredentialsException) {
            error = "不正确的用户名或密码";
        }else if(exception instanceof LockedException) {
            error = exception.getMessage();
        }else{
            error = "不正确的用户名或密码";
        }
        return error;
    }

    /**
     * 进入忘记密码设置
     * @return
     */
    @GetMapping(value = "forget")
    public String toForget(){
        return PREFIX+"forget";
    }



    /**
     * 登录
     * @return
     */
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String login(@RequestParam(value = "error", required = false)
                                    String error,Model model){
        if (error != null) {
            return "member/index";
        }
        return "forward:/member/memberIndex";
    }

    ///////////////////////认证  auth 部分开始//////////////////////////
    /**
     * 去实名认证
     * @return
     */
    @RequestMapping(value = "goRealName",method = RequestMethod.GET)
    public String goRealName(HttpSession session,Model model){
        //放入version到前台
        String mName = (String) session.getAttribute(Constants.USER);
        Member member = memberService.findByName(mName);
        session.setAttribute("member",member);
        model.addAttribute("member",member);

        return PREFIX+"auth/auth-realname";
    }

    /**
     * 实名
     * @return
     */
    @PostMapping(value = "realName")
    public String realName(String realname, String idcard,
                           String url1, String url2,
                           String url3, HttpSession session ,Model model){
        String mName = (String) session.getAttribute(Constants.USER);
        Member member = memberService.findByName(mName);

        //到后台审核
        member.setReal_name(realname);
        member.setId_card(idcard);
        member.setZheng(url1);
        member.setFan(url2);
        member.setShou_chi(url3);
        memberService.updateMember(member,member.getVersion());

        model.addAttribute("temp_real_name",realname);
        //更新
        return PREFIX+ "auth/auth-realname-success";
    }

    /**
     * 去认证手机号
     * @return
     */
    @GetMapping(value = "realMobile")
    public String goRealMobile( Model model,HttpSession session){
        //放入手机号到前台
        String mName = (String) session.getAttribute(Constants.USER);
        Member member = memberService.findByName(mName);
        model.addAttribute("mobile",member.getMobile());
        session.setAttribute("member",member);
        model.addAttribute("member",member);
        return PREFIX + "auth/auth-mobile-start";
    }

    /**
     * 去认证手机号
     * @return
     */
    @GetMapping(value = "inputRealMobile")
    public String inputMobile(Model model,HttpSession session){
        //放入手机号到前台
        String mName = (String) session.getAttribute(Constants.USER);
        Member member = memberService.findByName(mName);
        session.setAttribute("member",member);
        model.addAttribute("member",member);
        return PREFIX + "auth/auth-mobile-input";
    }

    /**
     * 认证手机号
     * @return
     */
    @PostMapping(value = "realMobile")
    public String realMobile(String auth_code,Model model,HttpSession session){
        //获取手机号
        String validateCode= (String) session.getAttribute(MOBILE_CODE);
        if (org.apache.commons.lang.StringUtils.isNotBlank(auth_code)){
            if (auth_code.equals(validateCode)){
                String name = (String) session.getAttribute(Constants.USER);
                Member member = memberService.findByName(name);
                member.setIs_real_mobile(1);
                //保存
                memberService.updateMember(member,member.getVersion());
                model.addAttribute("err_msg","恭喜认证成功");
            }else {
                model.addAttribute("err_msg","短信验证码错误");
            }
        }

        return PREFIX + "auth/auth-mobile-success";
    }


    /**
     * 去认证银行卡
     * @return
     */
    @GetMapping(value = "realBank")
    public String inputBank(HttpSession session){
        //放入用户姓名和身份证号到前台
        return PREFIX + "auth/auth-bank-start";
    }



    ///////////////////////认证  auth 部分结束/////////////////////////


    ///////////////////////买号列表添加  account 部分开始//////////////////////////
    /**
     * 到添加买号页面
     * @return
     */
    @GetMapping(value = "bindByTaoBao")
    public String toBindBuy(){
        return PREFIX +"bind/bind-buy-taobao";
    }

    /**
     * 买号添加
     * @return
     */
    @PostMapping(value = "buyAccountAdd")
    public String buyAccountAdd(MemberTaoBao taoBao, BindingResult result,String url1,
                                String url2, String url3,
                                String url4, String[] buy_class,
                                Model model,HttpSession session){
        String username  = (String) session.getAttribute(Constants.USER);

        //判断类型
        if (org.apache.commons.lang.StringUtils.isNotBlank(taoBao.getCatid())){
            switch (taoBao.getCatid()){
                case "4"://淘宝
                    taoBao.setAccount_type("淘宝试用");
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
                case "5"://阿里巴巴
                    taoBao.setAccount_type("阿里巴巴试用");
                    break;
                case "40"://京东
                    taoBao.setAccount_type("京东试用");
                    break;
                case "6"://拼多多
                    taoBao.setAccount_type("拼多多试用");
                    break;
                case "7"://蘑菇街试用
                    taoBao.setAccount_type("蘑菇街试用");
                    break;
                case "8"://美丽说试用
                    taoBao.setAccount_type("美丽说试用");
                    break;
            }
        }

        //判断账号是否已经绑定过
        MemberTaoBao search = new MemberTaoBao();
        search.setAccount(taoBao.getAccount());
        search.setAccount_type(taoBao.getAccount_type());
        //查询
        List<MemberTaoBao> baos = taoBaoService.queryMemberBuyList(search);
        if (baos!=null && baos.size()>0){
            //已经绑定过了 不能再绑定
            model.addAttribute(Constants.MESSAGE,"该账号已经绑定过了,不能重复绑定!");
            return "message";

        }
        //关联
        Member member = memberService.findByName(username);
        taoBao.setShoot_real_name(url1);
        taoBao.setShoot_zhi_ma(url2);
        taoBao.setShoot_honor(url3);
        taoBao.setShoot_taoqi(url4);
        taoBao.setMem_id(member.getId());


        //保存
        taoBaoService.addMemberTaoBao(taoBao);
        return PREFIX+"bind/bind-result";
    }

    /**
     *
     *买号列表
     * @return
     */
    @GetMapping(value = "accountList")
    public String accountList(Model model,HttpSession session){
        String username  = (String) session.getAttribute(Constants.USER);
        Member member = memberService.findByName(username);
        MemberTaoBao memberTaoBao = new MemberTaoBao();
        memberTaoBao.setMem_id(member.getId());
        //查询会员的买号列表
        List<MemberTaoBao> taoBaos = taoBaoService.queryMemberBuyList(memberTaoBao);
        model.addAttribute("taoBaos",taoBaos);

        return PREFIX+"bind/bind-result";
    }
}
