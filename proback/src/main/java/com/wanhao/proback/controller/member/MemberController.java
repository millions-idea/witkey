package com.wanhao.proback.controller.member;

import com.google.gson.JsonObject;
import com.wanhao.proback.bean.member.*;
import com.wanhao.proback.bean.util.JsonArrayResult;
import com.wanhao.proback.bean.util.JsonResult;
import com.wanhao.proback.service.AreaService;
import com.wanhao.proback.service.member.*;
import com.wanhao.proback.utils.GsonUtils;
import com.wanhao.proback.utils.IsNullUtils;
import com.wanhao.proback.utils.ResponseUtils;
import org.apache.ibatis.binding.BindingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by LiuLiHao on 2018/7/14 17:43.
 * 描述： 会员管理
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
    AreaService areaService;

    private static final String PREFIX = "express/";

//////////////////////////////分割线 会员管理部分///////////////////////////////////

    @GetMapping("/list")
    public String index(){
        return "v2/member/index";
    }

    @GetMapping("/view")
    public String view(Member param, final Model model, BindingException result){
        model.addAttribute("model", param);
        return "v2/member/view";
    }

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }

    /**
     * @return
     */
    @RequestMapping("/getLimit")
    @ResponseBody
    public JsonArrayResult<BusinessBrands> getBusinessLimit(Integer page,String cond,
                                                            String content,String gender){
        Member member = new Member();
        //判断条件查询
        if (IsNullUtils.isNotNull(cond,content)){
            switch (cond){
                case "0":
                    break;
                case "1"://代理商
                    break;
                case "2"://会员名
                    member.setUsername(content);
                    break;
                case "4"://真实姓名
                    member.setReal_name(content);
                    break;
                case "5"://手机号码
                    member.setMobile(content);
                    break;
                case "7"://QQ
                    member.setQq(content);
                    break;
                case "12"://推荐人id
                    member.setInvite_id(Integer.valueOf(content));
                    break;
                case "14"://身份证号
                    member.setId_card(content);
                    break;
            }
        }
        //性别限制
        if (IsNullUtils.isNotNull(gender)){
            member.setGender(gender);
        }
        //分页
        if (page!=null && page>0){
            member.setPage(page);
        }
        List<Member> members = memberService.getMembers(member);

        return new JsonArrayResult(0, members);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @GetMapping("/delete")
    @ResponseBody
    public JsonResult delete(String id){
        memberService.delete(id);
        return new JsonResult(0);
    }

    @GetMapping("/addView")
    public String addView(){
        return "v2/member/add";
    }

    @GetMapping("/editView")
    public String editView(Member param, final Model model,BindingException result){
        model.addAttribute("model", param);
        return "v2/member/edit";
    }

    @PostMapping("/edit")
    @ResponseBody
    public JsonResult edit(Member param){
        memberService.updateMember(param);
        return new JsonResult(0);
    }

    /**
     * 添加会员
     * @param param
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public JsonResult add(Member param) {
        memberService.addMember(param);
        return new JsonResult(0);
    }

//////////////////////////////分割线 会员管理部分结束///////////////////////////////////
//////////////////////////////分割线 设置禁用名部分///////////////////////////////////

    @GetMapping(value = "toForbiddenHtml")
    public String toForbiddenHtml(){
        return "v2/setting/membername/index";
    }

    @GetMapping(value = "forbidden")
    public void toForbidden(HttpServletResponse response) {
        //查询出来
        NameForbidden forbidden = nameForbiddenService.query();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name",GsonUtils.toJson(forbidden.getName()));
        ResponseUtils.retnSuccessMsg(response,jsonObject);
    }

    /**
     * 设置禁用用户名
     *
     * @return
     */
    @PostMapping(value = "setForbidden")
    public void setForbidden(String name_forbidden,HttpServletResponse response) {
        //保存到数据库
        NameForbidden nameForbidden = new NameForbidden();
        nameForbidden.setName(name_forbidden);
        nameForbidden.setId(1);
        nameForbiddenService.update(nameForbidden);

        ResponseUtils.retnSuccessMsg(response,new JsonObject());
    }
    //////////////////////////////分割线 设置禁用名部分///////////////////////////////////

    //////////////////////////实名认证部分开始////////////////////////

    /**
     * 实名认证审核页面
     *
     * @return
     */
    @GetMapping(value = "toMemberRealNameAuth")
    public String toMemberAuth() {
        return "v2/authentication/realname/index";
    }

    /**
     * 查询提交了实名信息的会员列表
     * @return
     */
    @RequestMapping(value = "queryMemberAuth")
    @ResponseBody
    public JsonArrayResult<Member> queryMemberAuth(Integer page,String cond,
                                                   String content,String gender,Integer is_auth,
                                  HttpServletResponse response) {
        Member member = new Member();
        //判断条件查询
        if (IsNullUtils.isNotNull(cond,content)){
            switch (cond){
                case "0":
                    break;
                case "1"://代理商
                    break;
                case "2"://会员名
                    member.setUsername(content);
                    break;
                case "4"://真实姓名
                    member.setReal_name(content);
                    break;
                case "5"://手机号码
                    member.setMobile(content);
                    break;
                case "7"://QQ
                    member.setQq(content);
                    break;
                case "12"://推荐人id
                    member.setInvite_id(Integer.valueOf(content));
                    break;
                case "14"://身份证号
                    member.setId_card(content);
                    break;
            }
        }

        //是否认证了
        if (is_auth!=null && !is_auth.equals(4)){
            member.setIs_real_name(is_auth);
        }else {
            member.setIs_real_name(1);
        }

        //性别限制
        if (IsNullUtils.isNotNull(gender)){
            member.setGender(gender);
        }
        //分页
        if (page!=null && page>0){
            member.setPage(page);
        }

        //查询出来
        List<Member> members = memberService.getMembers(member);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("members",GsonUtils.toJson(members));

        //ResponseUtils.retnSuccessMsg(response,jsonObject);
        return new JsonArrayResult(0, members);

    }

    @GetMapping("/viewRealNameImg")
    public String viewRealNameImg(Member param, final Model model){
        model.addAttribute("model", param);
        return "v2/authentication/realname/view";
    }


    /**
     * 同意实名
     */
    @RequestMapping(value = "agreeAuth")
    @ResponseBody
    public JsonResult agreeAuth(String id,HttpServletResponse response){
        memberService.agreeAll(id);
        return new JsonResult(0);
    }

    /**
     * 拒绝实名
     */
    @RequestMapping(value = "rejectAuth")
    @ResponseBody
    public JsonResult rejectAuth(String id,String reason,HttpServletResponse response){
        memberService.rejectAll(id,reason);
        return new JsonResult(0);
    }
    //////////////////////////实名认证部分结束////////////////////////

    //////////////////////////会员买号认证部分开始////////////////////////////

    @Autowired
    MemberTaoBaoService memberTaoBaoService;

    /**
     * 跳转到买号列表页面
     * @return
     */
    @RequestMapping(value = "toAuthAccount")
    public String toAuthAccount(){
        return "v2/authentication/buy-account/index";
    }

    /**
     * 条件查询买号列表
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getAuthAccount")
    public JsonArrayResult<MemberTaoBao> getAuthAccount(Integer page,String cond,
                                                        String content,String gender,Integer is_auth,
                                                        HttpServletResponse response) {
        MemberTaoBao memberTaoBao = new MemberTaoBao();
        //判断条件查询
        if (IsNullUtils.isNotNull(cond,content)){
            switch (cond){
                case "0":
                    break;
                case "2": //会员名
                    break;
                case "3"://会员id
                    memberTaoBao.setMem_id(Integer.valueOf(content));
                    break;
            }
        }

        //买号性别
        if (IsNullUtils.isNotNull(gender)&& !gender.equals("0")){
            memberTaoBao.setAccount_gender(gender);
        }
        //分页
        if (page!=null && page>0){
            memberTaoBao.setPage(page);
        }
        //是否已经认证
        if (is_auth!=null && !is_auth.equals(4)){
            memberTaoBao.setIs_pass(is_auth);
        }else {
            //默认查询没有认证的
            memberTaoBao.setIs_pass(0);
        }

        List<MemberTaoBao> taoBaos = memberTaoBaoService.queryMemberBuyList(memberTaoBao);

        return new JsonArrayResult(0, taoBaos);
    }

    /**
     * 同意买号
     */
    @RequestMapping(value = "agreeBuyAccount")
    @ResponseBody
    public JsonResult agreeBuyAccount(String id,HttpServletResponse response){
        memberTaoBaoService.agreeAllBuyAccount(id);
        return new JsonResult(0);
    }

    /**
     * 拒绝买号
     */
    @RequestMapping(value = "rejectBuyAccount")
    @ResponseBody
    public JsonResult rejectBuyAccount(String id,String reason,HttpServletResponse response){
        memberTaoBaoService.rejectAllBuyAccount(id,reason);
        return new JsonResult(0);
    }

    /**
     * 买号截图页面
     * @param param
     * @param model
     * @return
     */
    @GetMapping("/viewBuyAccountImg")
    public String viewBuyAccountImg(MemberTaoBao param, final Model model){
        model.addAttribute("model", param);
        return "v2/authentication/buy-account/view";
    }

    //////////////////////////会员银行卡认证////////////////////////////

    @Autowired
    MemberBankService memberBankService;

    /**
     * 页面跳转
     * @return
     */
    @RequestMapping(value = "toAuthBankHtml")
    public String toAuthBankHtml() {
        return "v2/authentication/bank/index";
    }

        /**
         * 查询记录
         * @return
         */
    @RequestMapping(value = "toAuthBank")
    @ResponseBody
    public JsonArrayResult<MemberBank> toAuthBank(Integer page,String cond,
                                                  String content,Integer is_auth,
                                                  HttpServletResponse response) {

        MemberBank memberBank = new MemberBank();

        //判断条件查询
        if (IsNullUtils.isNotNull(cond,content)){
            switch (cond){
                case "0":
                    break;
                case "2": //银行卡号
                    memberBank.setBank_num(content);
                    break;
                case "1"://会员id
                    memberBank.setMem_id(Integer.valueOf(content));
                    break;
                case "3"://卡类型
                    memberBank.setBank_type(content);
                    break;
                case "4"://姓名
                    memberBank.setBank_username(content);
                    break;
            }
        }
        //判断查询条件
        if (is_auth!=null && !is_auth.equals(4)){
            memberBank.setIs_auth(is_auth);
        }
        if (page!=null && page>0){
            memberBank.setPage(page);
        }
        List<MemberBank> banks = memberBankService.findByPages(memberBank);
        return new JsonArrayResult(0, banks);
    }


    /**
     * 同意银行卡
     */
    @RequestMapping(value = "agreeMemberBank")
    @ResponseBody
    public JsonResult agreeMemberBank(String id,HttpServletResponse response){
        memberBankService.agreeAllBank(id);
        return new JsonResult(0);
    }

    /**
     * 拒绝银行卡
     */
    @RequestMapping(value = "rejectMemberBank")
    @ResponseBody
    public JsonResult rejectMemberBank(String id,String reason,HttpServletResponse response){
        memberBankService.rejectAllBuyBank(id,reason);
        return new JsonResult(0);
    }

    @GetMapping("/viewBankDetail")
    public String viewBankDetail(MemberBank param, final Model model){
        model.addAttribute("model", param);
        return "v2/authentication/bank/view";
    }


///////////////////////////////////////////////////////////
    @Autowired
    TiXianService tiXianService;

    //提现页面
    @RequestMapping(value = "tiXianHtml")
    public String tiXianHtml(){
        return "v2/authentication/withdraw-deposit/index";
    }


   /**
    * 审核提现
    *
    * @return
    */
    @RequestMapping(value = "toTiXian")
    @ResponseBody
    public JsonArrayResult<TiXian> toTiXian(Integer page,String cond,
                                            String content,Integer is_auth,
                                            HttpServletResponse response) {
        TiXian tiXian = new TiXian();
        //判断条件查询
        if (IsNullUtils.isNotNull(cond,content)){
            switch (cond){
                case "0":
                    break;
                case "1"://会员id
                    tiXian.setMemid(Integer.valueOf(content));
                    break;
                case "2"://真实姓名
                    tiXian.setReal_name(content);
                    break;
            }
        }
        //判断查询条件
        if (is_auth!=null && !is_auth.equals(4)){
            tiXian.setFlag(is_auth);
        }
        if (page!=null && page>0){
            tiXian.setPage(page);
        }

        List<TiXian> datas = tiXianService.getTiXianData(tiXian);

        return new JsonArrayResult(0, datas);
    }



    /**
     * 同意提现
     */
    @RequestMapping(value = "agreeTiXian")
    @ResponseBody
    public JsonResult agreeTiXian(String id,HttpServletResponse response){
        tiXianService.agreeAllTiXian(id);
        return new JsonResult(0);
    }

    /**
     * 拒绝提现
     */
    @RequestMapping(value = "rejectTiXian")
    @ResponseBody
    public JsonResult rejectTiXian(String id,String reason,HttpServletResponse response){
        tiXianService.rejectAllTiXian(id,reason);
        return new JsonResult(0);
    }



}
