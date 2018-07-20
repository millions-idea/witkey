package com.wanhao.proback.controller.member;

import com.github.pagehelper.PageInfo;
import com.google.gson.JsonObject;
import com.wanhao.proback.bean.Area;
import com.wanhao.proback.bean.member.Member;
import com.wanhao.proback.bean.member.MemberBank;
import com.wanhao.proback.bean.member.MemberTaoBao;
import com.wanhao.proback.bean.member.NameForbidden;
import com.wanhao.proback.service.AreaService;
import com.wanhao.proback.service.member.MemberBankService;
import com.wanhao.proback.service.member.MemberService;
import com.wanhao.proback.service.member.MemberTaoBaoService;
import com.wanhao.proback.service.member.NameForbiddenService;
import com.wanhao.proback.utils.DateUtil;
import com.wanhao.proback.utils.ResponseUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

    private static final String PREFIX = "member/";


    @RequestMapping(value = "forbidden")
    public String toForbidden(Model model){
        //查询出来
        NameForbidden forbidden = nameForbiddenService.query();
        model.addAttribute("forbidden",forbidden.getName());
        return "member/set-forbidden";
    }

    /**
     * 设置禁用用户名
     * @return
     */
    @RequestMapping(value = "forbidden",method = RequestMethod.POST)
    public String setForbidden(String name_forbidden, Model model){
        //保存到数据库
        NameForbidden nameForbidden = new NameForbidden();
        nameForbidden.setName(name_forbidden);
        nameForbidden.setId(1);
        nameForbiddenService.update(nameForbidden);
        //查询出来
        model.addAttribute("forbidden",name_forbidden);
        return PREFIX+"set-forbidden";
    }

    /**
     * 会员列表
     * @return
     */
    @RequestMapping(value = "memberList")
    public String toMemberList(Map<String,Object> map){
        //查找第一页
        List<Member> members = memberService.getMembers(new Member());
        //查询省份
        List<Area> provinces = areaService.getAllProvince();
        map.put("members",members);
        map.put("provinces",provinces);
        return "member/member-list";
    }

    /**
     * 会员列表查询
     * @return
     */
    @PostMapping(value = "memberList")
    public String memberList(Member member,
                             String type,
                             String type_value,
                             Double minmoney,
                             Double maxmoney,
                             Model model){
        if (type!=null){
            switch (type){
                case "0":
                    break;
                case "1":
                    //代理商
                    member.setProxy(type_value);
                    break;
                case "2":
                    member.setUsername(type_value);
                    break;
                case "4":
                    member.setReal_name(type_value);
                    break;
                case "5":
                    member.setMobile(type_value);
                    break;
                case "7":
                    member.setQq(type_value);
                    break;
                case "13":
                    member.setInvite_id(Integer.valueOf(type_value));
                    break;
                case "14":
                    member.setId_card(type_value);
                    break;
            }
        }


       //查找第一页
        List<Member> members = memberService.getMembers(member);
        //查询省份
        List<Area> provinces = areaService.getAllProvince();
        model.addAttribute("members",members);
        model.addAttribute("provinces",provinces);
        return "member/member-list";
    }

    /**
     * 添加
     * @return
     */
    @GetMapping(value = "memberAdd")
    public String memberAdd(){

        return PREFIX+"add";
    }

    //////////////////////////认证部分////////////////////////
    /**
     * 实名认证审核页面
     * @return
     */
    @GetMapping(value = "toMemberRealNameAuth")
    public String toMemberAuth(Model model){
        Member member = new Member();
        member.setIs_real_name(0);
        member.setZheng("1");
        member.setFan("1");
        member.setShou_chi("1");
        //查询已经上传图片 并且没有通过认证的
        List<Member> members = memberService.getMembers(member);
        model.addAttribute("members",members);
        return PREFIX+"realname-auth";
    }

    /**
     *
     * @param condition 条件类型
     * @param value
     * @param auth_type 认证类型
     * @param status 状态
     * @return
     */
    @PostMapping(value = "queryMemberAuth")
    public String queryMemberAuth(Integer condition,String value,
                                  String fromtime,String totime,
                                  Integer auth_type,Integer status,Model model){
        Member member = new Member();
        //按照会员名查找
        if (condition!=null && condition.equals(1)){
            member.setUsername(value);
        }

        //是否实名
        if (auth_type!=null && auth_type.equals(1) && !status.equals(3)){
            member.setIs_real_name(status);
        }

        //查询出来
        List<Member> members = memberService.getMembers(member);

        List<Member> sm = members;

        //过滤时间要求
        if (StringUtils.isNotBlank(fromtime)){
            Date formatDate = DateUtil.getDateFromString(fromtime,"yyyy-MM-dd");
            long time = formatDate.getTime();
            sm  =new LinkedList<>();
            if (members!=null && members.size()>0){
                for (Member temp : members) {
                    if (temp.getReal_name_time().getTime() > time ){
                        sm.add(temp);
                    }
                }

            }
        }

        //限制结束时间
        List<Member> dm = sm;
        if (StringUtils.isNotBlank(totime)){
            Date toDate = DateUtil.getDateFromString(totime,"yyyy-MM-dd");
            long time = toDate.getTime();
            dm = new LinkedList<>();

            if (sm!=null && sm.size()>0){
                for (Member temp : sm) {
                    if (temp.getReal_name_time().getTime() < time){
                        dm.add(temp);
                    }
                }

            }
        }

        model.addAttribute("members",dm);

        return PREFIX+"realname-auth";
    }

    /**
     * 同意实名
     */
    @PostMapping(value = "memberAuth")
    public void memberAuth(@RequestBody AuthData data, HttpServletResponse response){
        auth(data,1,response);
    }

    /**
     * 拒绝实名
     * @param data
     * @param response
     */
    @PostMapping(value = "refuseAuth")
    public void refuseAuth(@RequestBody AuthData data, HttpServletResponse response){
        auth(data,2,response);
    }

    /**
     * 从新实名
     * @param data
     * @param response
     */
    @PostMapping(value = "redoAuth")
    public void redoAuth(@RequestBody AuthData data, HttpServletResponse response){
        auth(data,0,response);
    }

    /**
     * 实名操作
     * @param data
     * @param type 1同意 2 拒绝 0从新实名
     * @param response
     */
    public void auth(AuthData data,Integer type,HttpServletResponse response){
        JsonObject jsonObject = new JsonObject();
        if (data.data==null || data.data.length<=0){
            //返回错误
            jsonObject.addProperty("error","1");
            ResponseUtils.renderJson(response,jsonObject.toString());
            return;
        }
        Member member = new Member();
        for (Integer auth : data.data) {
            member.setId(auth);
            Member temp = memberService.getMember(member);
            temp.setIs_real_name(type);
            //说明信息
            temp.setRemark(data.reason);
            //保存
            memberService.updateMember(temp);
        }
        //返回结果
        jsonObject.addProperty("error","0");
        ResponseUtils.renderJson(response,jsonObject.toString());
    }



    //////////////////////////会员买号认证////////////////////////////

    @Autowired
    MemberTaoBaoService memberTaoBaoService;

    /**
     * 跳转到买号列表
     * @return
     */
    @GetMapping(value = "toAuthAccount")
    public String toAuthAccount(Model model){
        MemberTaoBao memberTaoBao = new MemberTaoBao();
        List<MemberTaoBao> taoBaos = memberTaoBaoService.queryMemberBuyList(memberTaoBao);
        model.addAttribute("taoBaos",taoBaos);

        return PREFIX +"auth/auth-account";
    }

    /**
     * 买号列表查询
     * @return
     */
    @GetMapping(value = "authAccountSearch")
    public String authAccount(MemberTaoBao memberTaoBao,Model model){

        return PREFIX +"auth/auth-account";
    }


    /**
     * 跳转到买号详细信息
     * @return
     */
    @GetMapping(value = "toAuthAccountDetail/{id}")
    public String toAuthAccountDetail(@PathVariable("id") Integer id,Model model){
        MemberTaoBao taoBao = memberTaoBaoService.getOne(id);
        model.addAttribute("taobao",taoBao);

        return PREFIX +"auth/auth-account-detail";
    }


    //////////////////////////会员银行卡认证////////////////////////////

    @Autowired
    MemberBankService memberBankService;

    /**
     * 到银行卡认证审核页面
     * @return
     */
    @RequestMapping(value = "toAuthBank")
    public String toAuthBank(Model model,MemberBank bank,Integer selectOps,String selectVal){
        MemberBank memberBank = new MemberBank();

        if (bank!=null && bank.getPage()!=null){
            memberBank.setPage(bank.getPage());
        }
        //判断查询条件
        if (selectOps!=null){
            switch (selectOps){
                case 1://姓名
                    memberBank.setBank_username(selectVal);
                    break;
                case 2://银行卡号
                    memberBank.setBank_num(selectVal);
                    break;
                case 3://卡类型
                    memberBank.setBank_type(selectVal);
                    break;
                case 4://会员id
                    memberBank.setMem_id(Integer.valueOf(selectVal));
                    break;
            }
        }
        List<MemberBank> banks = memberBankService.findByPages(memberBank);
        //返回pageinfo
        PageInfo<MemberBank> info = new PageInfo<MemberBank>(banks);
        //前台可以使用分页
        model.addAttribute("pageinfo",info);
        return PREFIX +"auth/auth-bank";
    }


    /**
     * 银行卡认证--同意
     * @return
     */
    @PostMapping(value = "authBankAgree")
    public void authBankAgree(@RequestBody AuthData data, HttpServletResponse response){
        authBank(data,1,response);
    }
    /**
     * 银行卡认证---拒绝
     * @return
     */
    @PostMapping(value = "authBankRefuse")
    public void authBankRefuse(@RequestBody AuthData data, HttpServletResponse response){
        authBank(data,3,response);
    }
    /**
     * 银行卡认证--从新填写
     * @return
     */
    @PostMapping(value = "authBankRedo")
    public void authBankRedo(@RequestBody AuthData data, HttpServletResponse response){
        authBank(data,2,response);
    }

    /**
     * 银行卡操作
     * @param data
     * @param type 1同意 2 拒绝 0从新实名
     * @param response
     */
    public void authBank(AuthData data,Integer type,HttpServletResponse response){
        JsonObject jsonObject = new JsonObject();
        if (data.data==null || data.data.length<=0){
            //返回错误
            jsonObject.addProperty("error","1");
            ResponseUtils.renderJson(response,jsonObject.toString());
            return;
        }

        MemberBank bank = new MemberBank();

        for (Integer auth : data.data) {
            bank.setId(auth);
            List<MemberBank> byPages = memberBankService.findByPages(bank);
            if (byPages!=null && byPages.size()>0){
                MemberBank bank1 = byPages.get(0);
                bank1.setIs_auth(type);
                bank1.setRemark(data.reason);
                //保存更新
                memberBankService.update(bank1);
            }
        }
        //返回结果
        jsonObject.addProperty("error","0");
        ResponseUtils.renderJson(response,jsonObject.toString());
    }



    //接收参数
    static class AuthData{
        public Integer[] data;
        public String reason;
    }
}
