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
     * 注册
     * @return
     */
    @PostMapping(value = "register")
    public String register(Member member,String mobile_code, Model model){
        //判断手机验证码是否正确 todo
        if (mobile_code!=null){

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

        //注册成功暂时返回首页
        return "index";
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
    public String goRealName(){

        return PREFIX+"auth/realname";
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
        memberService.updateMember(member);

        model.addAttribute("temp_real_name",realname);
        //更新
        return PREFIX+ "auth/submit-success";
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
        return PREFIX + "auth/realmobile";
    }

    /**
     * 去认证手机号
     * @return
     */
    @GetMapping(value = "inputRealMobile")
    public String inputMobile(){
        //放入手机号到前台
        return PREFIX + "auth/inputmobile";
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
                memberService.updateMember(member);
                model.addAttribute("err_msg","恭喜认证成功");
            }else {
                model.addAttribute("err_msg","短信验证码错误");
            }
        }

        return PREFIX + "auth/successmobile";
    }



    ///////////////////////认证  auth 部分结束/////////////////////////

    /**
     * 到添加淘宝买号页面
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
        //关联
        Member member = memberService.findByName(username);
        taoBao.setShoot_real_name(url1);
        taoBao.setShoot_zhi_ma(url2);
        taoBao.setShoot_honor(url3);
        taoBao.setShoot_taoqi(url4);
        taoBao.setMem_id(member.getId());


        //保存
        taoBaoService.addMemberTaoBao(taoBao);
        return PREFIX+"bind/bind-success";
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

        return PREFIX+"bind/bind-success";
    }
}
