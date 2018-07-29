package com.wanhao.proback.controller.intf.shop;

import com.github.pagehelper.PageInfo;
import com.google.gson.JsonObject;
import com.wanhao.proback.annotaion.ISLogin;
import com.wanhao.proback.bean.member.Member;
import com.wanhao.proback.bean.member.MemberTaoBao;
import com.wanhao.proback.bean.member.MemberTask;
import com.wanhao.proback.bean.shop.Goods;
import com.wanhao.proback.bean.shop.Shop;
import com.wanhao.proback.service.member.MemberService;
import com.wanhao.proback.service.member.MemberTaoBaoService;
import com.wanhao.proback.service.member.MemberTaskService;
import com.wanhao.proback.service.shop.GoodsService;
import com.wanhao.proback.service.shop.ShopService;
import com.wanhao.proback.utils.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by LiuLiHao on 2018/7/23 21:42.
 * 描述： 商家操作
 * 作者： LiuLiHao
 */
@Controller(value = "intf_shop")
@RequestMapping(value = "intf_shop")
public class ShopController {

    @Autowired
    MemberService memberService;

    @Autowired
    ShopService shopService;

    /**
     * 发布任务
     */
    @ISLogin
    @RequestMapping(value = "pubTask")
    public void pubTask(HttpServletRequest request, HttpServletResponse response,
                        String catetype, String link_url,
                        String goods_img, String search_word,
                        String goods_format, String template_name,
                        Integer shop_id, String shop_name,
                        Integer goods_type,
                        Integer search_type, Integer task_count,
                        Integer return_type, Integer yuancheng_type,
                        //Integer pay_type,
                        Integer time_divider, Integer task_safe,
                        Integer save_template, Integer memid,
                        Double price, String tao_kouling,
                        Double append_money,
                        Integer zhiding_pinglun, Integer bask_img,
                        Integer task_rec_time, String device,
                        Integer need_bi_san_jia, Integer need_chat,
                        Integer need_look_comment, Integer need_add_buy_cart,
                        Integer need_col_goods, String yuyue_xiadan,
                        String comment_content, String confirm_time_type,
                        String remark, Integer day_limit,
                        Integer shangbao_limit, Integer taoqi_limit,

                        Integer[] gender_limit, String honor_limit,
                        String forbidden_area, String[] age_limit,
                        String[] always_buy_class) {

        //        if (IsNullUtils.isNull(catetype, link_url, search_word, goods_format, shop_id,
//                goods_type, search_type, task_count, return_type, pay_type, time_divider,
//                task_safe, save_template, memid, price, append_money, bask_img, device,
//                need_look_comment, need_add_buy_cart, need_col_goods, yuyue_xiadan,
//                confirm_time_type, day_limit, shangbao_limit, taoqi_limit, gender_limit, honor_limit,
//                forbidden_area, age_limit, always_buy_class)) {
//
//        }

        JsonObject jsonObject = new JsonObject();
        //查询用户是否是商户
        Member member = new Member(memid);

        Member dbMember = memberService.getMember(member);

        //不是商家不能发布任务
        if (dbMember == null ||
                dbMember.getIs_seller() == null ||
                dbMember.getIs_seller() == 0) {
            ResponseUtils.retnFailMsg(response, jsonObject, "不是商家 不允许发布任务...");
            return;
        }
        //查看店铺是否审核通过
        Shop shop = shopService.getByPk(shop_id);
        if (shop == null || shop.getIs_pass() == null || shop.getIs_pass() == 0) {
            //审核未通过
            ResponseUtils.retnFailMsg(response, jsonObject, "抱歉,您的店铺还未审核通过不能发布任务...");
            return;
        }
        Goods goods = new Goods();

        switch (catetype) {
            case "4":
                goods.setCatetype("淘宝试用");
                break;
            case "5":
                goods.setCatetype("阿里巴巴试用");
                break;
            case "40":
                goods.setCatetype("京东试用");

                break;
            case "6":
                goods.setCatetype("拼多多试用");

                break;
            case "7":
                goods.setCatetype("蘑菇街试用");
                break;
            case "8":
                goods.setCatetype("美丽说试用");
                break;
            case "41":
                goods.setCatetype("淘宝访问");
                break;
            case "51":
                goods.setCatetype("阿里巴巴访问");
                break;
            case "401":
                goods.setCatetype("京东访问");
                break;
        }
        //获取付款方式
        if (return_type == 0) {
            //获取商家付款方式
            if (yuancheng_type == null) {
                return;
            }
            goods.setYuancheng_type(yuancheng_type);
        }
        //淘口令
        if (StringUtils.isNotBlank(tao_kouling)) {
            goods.setTao_kouling(tao_kouling);
        }
        //性别限制
        if (gender_limit != null && gender_limit.length > 0) {
            if (gender_limit.length == 1) {
                goods.setGender_limit(gender_limit[0]);
            } else {
                goods.setGender_limit(0);
            }
        }
        //年龄限制  1,2,3,4  15-25
        if (age_limit != null && age_limit.length > 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < gender_limit.length; i++) {
                if (i == gender_limit.length - 1) {
                    sb.append(gender_limit[i]);
                } else {
                    sb.append(gender_limit[i]).append(",");
                }
            }
            goods.setAge_limit(sb.toString());
        }

        //常买类型 1,2,3,5
        if (always_buy_class != null && always_buy_class.length > 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < always_buy_class.length; i++) {
                if (i == always_buy_class.length - 1) {
                    sb.append(always_buy_class[i]);
                } else {
                    sb.append(always_buy_class[i]).append(",");
                }
            }
            goods.setAlways_buy_class(sb.toString());
        }

        goods.setDay_limit(day_limit);
        goods.setShangbao_limit(shangbao_limit);
        goods.setTaoqi_limit(taoqi_limit);
        goods.setShop_name(shop_name);
        goods.setHonor_limit(honor_limit);
        goods.setForbidden_area(forbidden_area);

        goods.setYuyue_xiadan(yuyue_xiadan);
        goods.setMemid(memid);
        goods.setComment_content(comment_content);
        goods.setConfirm_time_type(confirm_time_type);
        goods.setRemark(remark);
        goods.setZhiding_pinglun(zhiding_pinglun);
        goods.setBask_img(bask_img);
        goods.setTask_rec_time(task_rec_time);
        goods.setDevice(device);
        goods.setNeed_bi_san_jia(need_bi_san_jia);
        goods.setNeed_chat(need_chat);
        goods.setNeed_look_comment(need_look_comment);
        goods.setNeed_add_buy_cart(need_add_buy_cart);
        goods.setNeed_col_goods(need_col_goods);


        goods.setTao_kouling(tao_kouling);
        goods.setTime_divider(time_divider);
        goods.setTask_safe(task_safe);
        goods.setSave_template(save_template);
        goods.setPrice(price);
        goods.setAppend_money(append_money);
        //  goods.setCatetype(catetype);
        goods.setGoods_format(goods_format);
        goods.setGoods_img(goods_img);
        goods.setLink_url(link_url);
        goods.setTemplate_name(template_name);
        goods.setGoods_class_id(goods_type);
        goods.setReturn_type(return_type);
        goods.setSearch_word(search_word);
        goods.setTask_count(task_count);
        goods.setShop_id(shop_id);
        goods.setSearch_type(search_type);

        //保存
        goodsService.add(goods);
        ResponseUtils.retnSuccessMsg(response, jsonObject, "发布成功");

    }


    @Autowired
    GoodsService goodsService;

    @Autowired
    MemberTaoBaoService taoBaoService;

    /**
     * 商家和买家
     * 加载任务列表
     */
    // @ISLogin
    @RequestMapping(value = "loadUserTaskList")
    public void loadUserTaskList(HttpServletRequest request, HttpServletResponse response,
                                 Integer memid, String order_by, Integer page,
                                 Integer class_type, Integer good_id) {
        if (memid != null) {
            JsonObject jsonObject = new JsonObject();
            //判断用户是否已经绑定账号
            Member member = new Member(memid);
            Member dbMember = memberService.getMember(member);

            //判断用户类型 是商家还是买家
            if (dbMember.getIs_seller() != null && dbMember.getIs_seller() == 1) {
                //是商家 只能显示自己发布的任务
                Goods goods = new Goods();
                goods.setMemid(memid);
                //商家自己的任务
                List<Goods> shopList = goodsService.getGoods(goods);
                if (shopList != null && shopList.size() > 0) {
                    //设置信息
                    List<String> colors = getColors(shopList);
                    //返回数据
                    PageInfo<Goods> pageInfo = new PageInfo<>(shopList);
                    jsonObject.addProperty("pageInfo", GsonUtils.toJson(pageInfo));

                    ResponseUtils.retnSuccessMsg(response, jsonObject);
                    return;
                } else {
                    ResponseUtils.retnFailMsg(response, jsonObject, "您还没有发布任务");
                    return;
                }
            }

            //不是商家的 需要绑定买家号
            if (dbMember == null || dbMember.getIs_bind_buy_account() == null ||
                    dbMember.getIs_bind_buy_account() == 0) {
                //没有绑定买号
                ResponseUtils.retnFailMsg(response, jsonObject, "没有绑定买号,不能浏览任务...");
                return;
            }
            Goods goods = new Goods();

            //排序
            String orderBy = "";
            //查询任务列表
            if (!IsNullUtils.isNull(order_by)) {
                switch (order_by) {
                    case "1"://时间排序
                        orderBy = "id desc ";
                        break;
                    case "2":
                        orderBy = "price desc ";
                        break;
                    case "3":
                        orderBy = "append_money desc ";
                        break;
                }
            }


            //id
            goods.setId(good_id);
            //排序
            goods.setOrderBY(orderBy);
            if (page != null && page > 0) {
                //分页
                goods.setPage(page);
            }


            //所属商品分类
            if (class_type != null) {
                goods.setGoods_class_id(class_type);
            }

            List<Goods> list = goodsService.getGoods(goods);
            List<String> colors = getColors(list);

            //返回pageInfo
            PageInfo<Goods> pageInfo = new PageInfo<>(list);

            jsonObject.addProperty("pageInfo", GsonUtils.toJson(pageInfo));
            ResponseUtils.retnSuccessMsg(response, jsonObject);
        }
    }


    //设置颜色类
    public List<String> getColors(List<Goods> list) {
        List<String> color_class = null;
        //构建requires
        if (list != null && list.size() > 0) {
            List<String> strings;
            for (Goods temp : list) {
                strings = new LinkedList<String>();
                color_class = new LinkedList<String>();

                Integer need_add_buy_cart = temp.getNeed_add_buy_cart();
                if (need_add_buy_cart != null && need_add_buy_cart == 1) {
                    strings.add("添加购物车");
                    color_class.add("ired");
                }
                //货比三家
                Integer need_bi_san_jia = temp.getNeed_bi_san_jia();
                if (need_bi_san_jia != null && need_bi_san_jia == 1) {
                    strings.add("货比三家");
                    color_class.add("iorange");

                }
                //拍前聊天
                Integer need_chat = temp.getNeed_chat();
                if (need_chat != null && need_chat == 1) {
                    strings.add("拍前聊天");
                    color_class.add("iyellow");

                }
                //收藏商品
                Integer need_col_goods = temp.getNeed_col_goods();
                if (need_col_goods != null && need_col_goods == 1) {
                    strings.add("收藏商品");
                    color_class.add("igreen");

                }
                //浏览评论
                Integer need_look_comment = temp.getNeed_look_comment();
                if (need_look_comment != null && need_look_comment == 1) {
                    strings.add("浏览评论");
                    color_class.add("icyan");

                }
                //年龄限制
                String age_limit = temp.getAge_limit();
                if (StringUtils.isNotBlank(age_limit)) {
                    strings.add(age_limit);
                    color_class.add("iblue");

                }
                //淘气值
                Integer taoqi_limit = temp.getTaoqi_limit();
                if (taoqi_limit != null && taoqi_limit == 1) {
                    strings.add("淘气值" + taoqi_limit);
                    color_class.add("iviolet");

                }
                //性别
                Integer gender_limit = temp.getGender_limit();
                if (gender_limit != null) {
                    if (gender_limit == 1) {
                        strings.add("男号");
                        color_class.add("igray");

                    } else if (gender_limit == 2) {
                        strings.add("女号");
                        color_class.add(".ired");

                    } else if (gender_limit == 0) {
                        strings.add("男号,女号");
                        color_class.add("inone");

                    }
                }
                //设备限制
                String device = temp.getDevice();
                if (StringUtils.isNotBlank(device)) {
                    strings.add(device);
                    color_class.add("ired");
                }
                //信誉限制
                String honor_limit = temp.getHonor_limit();
                if (StringUtils.isNotBlank(honor_limit)) {
                    strings.add(device);
                    color_class.add("icyan");

                } else {
                    strings.add("不限信誉");
                    color_class.add("iviolet");
                }

                temp.setRequires(strings);
                temp.setColor_class(color_class);
            }

        }
        return color_class;
    }

    /**
     * 接取任务
     */
    @ISLogin
    @RequestMapping(value = "preRecvTask")
    public void recvTask(HttpServletRequest request, HttpServletResponse response,
                         Integer memid, Integer goods_id) {
        JsonObject jsonObject = new JsonObject();
        if (IsNullUtils.isNull(memid, goods_id)) {
            ResponseUtils.retnFailMsg(response, jsonObject, "参数不完整");
            return;
        }
        //是否绑定了买家号
        Member member = new Member(memid);
        Member dbMember = memberService.getMember(member);
        if (dbMember != null && dbMember.getIs_seller() != null &&
                dbMember.getIs_seller() == 0 &&
                dbMember.getIs_bind_buy_account() != null &&
                dbMember.getIs_bind_buy_account() == 1) {
            //判断商品类目
            Goods goods = new Goods();
            goods.setId(goods_id);
            //查找任务对应的商品
            List<Goods> dbList = goodsService.getGoods(goods);
            if (dbList != null && dbList.size() > 0) {
                Goods dbGood = dbList.get(0);
                //判断是哪个类型的试用
                String catetype = dbGood.getCatetype();
                MemberTaoBao taoBao = new MemberTaoBao();
                taoBao.setAccount_type(catetype);
                taoBao.setIs_pass(1);
                List<MemberTaoBao> baos = taoBaoService.queryMemberBuyList(taoBao);
                if (baos == null || baos.size() == 0) {
                    //没有对应的买号 不能接
                    ResponseUtils.retnFailMsg(response, jsonObject, "您没有对应的买号,先绑定买号");
                    return;
                }
            }


            //检查会员是否满足条件
            String forbidden_area = dbList.get(0).getForbidden_area();
            String ipAdrress = IpUtils.getIpAdrress(request);
            String location = IpUtils.getLocation(ipAdrress);
            //获取请求IP地址
            if (StringUtils.isNotBlank(location)) {
                String[] split = location.split(",");
                if (split != null && split.length > 0) {
                    if (StringUtils.isNotBlank(forbidden_area)) {
                        if (forbidden_area.contains(split[0]) || forbidden_area.contains(split[1])) {
                            //在禁止地区内
                            ResponseUtils.retnFailMsg(response, jsonObject, "您在禁止地区内,不能试用");
                            return;
                        }
                    }
                }
            }
        }

        //查询对应的买号列表
        Goods goods = new Goods();
        goods.setId(goods_id);
        //查找任务对应的商品
        List<Goods> dbList = goodsService.getGoods(goods);
        if (dbList != null && dbList.size() > 0) {
            Goods dbGood = dbList.get(0);
            MemberTaoBao taoBao = new MemberTaoBao();
            String catetype = dbGood.getCatetype();

            taoBao.setAccount_type(catetype);
            taoBao.setIs_pass(1);
            List<MemberTaoBao> baos = taoBaoService.queryMemberBuyList(taoBao);
            if (baos != null && baos.size() > 0) {
                jsonObject.addProperty("list", GsonUtils.toJson(baos));
                ResponseUtils.retnSuccessMsg(response, jsonObject, "查询成功");
            }
        }
    }

    @Autowired
    MemberTaskService taskService;

    /**
     * 使用买家号
     * 接取任务
     */
    @ISLogin
    @RequestMapping(value = "pickTask")
    public void pickTask(HttpServletRequest request, HttpServletResponse response,
                         Integer memid, Integer goods_id,
                         Integer buy_id) {
        JsonObject jsonObject = new JsonObject();
        if (IsNullUtils.isNull(memid, goods_id, buy_id)) {
            ResponseUtils.retnFailMsg(response, jsonObject);
            return;
        }
        //判断任务试用类型 检查是否符合
        Goods goods = new Goods();
        goods.setId(goods_id);
        List<Goods> dbList = goodsService.getGoods(goods);
        if (dbList != null && dbList.size() > 0) {
            Goods dbGood = dbList.get(0);
            //试用类型
            String catetype = dbGood.getCatetype();

            //买家账号信息
            MemberTaoBao taoBao = new MemberTaoBao();
            taoBao.setId(buy_id);
            List<MemberTaoBao> memberTaoBaos = taoBaoService.queryMemberBuyList(taoBao);
            if (memberTaoBaos == null || memberTaoBaos.size() <= 0) {
                return;
            }
            //买号
            MemberTaoBao buyAccount = memberTaoBaos.get(0);


            //接任务限制
            Integer day_limit = dbGood.getDay_limit();
            MemberTask queryTask = new MemberTask();
            queryTask.setMemid(memid);
            queryTask.setGoods_id(goods_id);
            if (day_limit != null && day_limit > 0) {
                //接任务相隔的天数
                List<MemberTask> datas = taskService.getDatas(queryTask);
                if (datas != null && datas.size() > 0) {
                    //已经接过任务了
                    ResponseUtils.retnFailMsg(response, jsonObject, "时间未到,禁止接同一掌柜任务");
                    return;
                }
            }

            //用户
            Member member = new Member(memid);
            Member sbMember = memberService.getMember(member);

            //商保限制
            Integer shangbao_limit = dbGood.getShangbao_limit();
            if (shangbao_limit != null && shangbao_limit == 1) {

                if (sbMember == null || sbMember.getPermis_money() == null ||
                        sbMember.getPermis_money() <= 0) {
                    //没有商保
                    ResponseUtils.retnFailMsg(response, jsonObject, "没有商保,不能接取试用");
                    return;
                }
            }

            //买号信誉限制
            String honor_limit = dbGood.getHonor_limit();
            if (StringUtils.isNotBlank(honor_limit)) {
                //todo
            }

            //性别限制
            Integer gender_limit = dbGood.getGender_limit();
            if (gender_limit != null && gender_limit != 0) {
                String account_gender = buyAccount.getAccount_gender();

                if (gender_limit == 1) {
                    if (!IsNullUtils.isNull(account_gender) &&
                            account_gender.contains("女")) {
                        ResponseUtils.retnFailMsg(response, jsonObject, "性别不符合要求");
                        return;
                    }
                } else if (gender_limit == 2) {
                    if (!IsNullUtils.isNull(account_gender) &&
                            account_gender.contains("男")) {
                        ResponseUtils.retnFailMsg(response, jsonObject, "性别不符合要求");
                        return;
                    }
                }
            }

            //年龄限制
            String age_limit = dbGood.getAge_limit();
            if (StringUtils.isNotBlank(age_limit) || !age_limit.contains(buyAccount.getAge_range())) {
                //不符合
                ResponseUtils.retnFailMsg(response, jsonObject, "年龄不符合要求");
                return;
            }

            //常购类目限制
            String always_buy_class = dbGood.getAlways_buy_class();
            String always_class = buyAccount.getAlways_class();

            if (StringUtils.isNotBlank(always_class) &&
                    StringUtils.isNotBlank(always_buy_class)) {
                //遍历
                String[] goodArr = always_buy_class.split(",");
                String[] userArr = always_class.split(",");
                boolean flag = false;

                if (goodArr != null && goodArr.length > 0 && userArr != null && userArr.length > 0) {
                    for (int i = 0; i < goodArr.length; i++) {
                        for (int j = 0; j < userArr.length; j++) {
                            if (goodArr[i].equals(userArr[j])) {
                                flag = true;
                                break;
                            }
                        }
                    }
                }
                if (!flag) {
                    ResponseUtils.retnFailMsg(response, jsonObject, "常购类目不符合要求");
                    return;
                }
            }


            if (!IsNullUtils.isNull(catetype)) {
                switch (catetype) {
                    case "淘宝试用":
                        //淘气值限制
                        Integer taoqi_limit = dbGood.getTaoqi_limit();
                        if (taoqi_limit != null && taoqi_limit >= 0) {
                            Integer taoqi = buyAccount.getTaoqi();
                            if (taoqi == null || taoqi < taoqi_limit) {
                                //不符合要求
                                ResponseUtils.retnFailMsg(response, jsonObject, "淘气值不符合要求");
                                return;
                            }
                        }

                        break;
                }
            }
        }


        MemberTask memberTask = new MemberTask();
        memberTask.setGoods_id(goods_id);
        memberTask.setIs_finsh(0);
        memberTask.setPick_time(new Date());
        memberTask.setMemid(memid);
        memberTask.setBuy_account_id(buy_id);
        taskService.add(memberTask);
        ResponseUtils.retnSuccessMsg(response, jsonObject, "任务接取成功");
    }


    /**
     * 开始任务
     */
    @ISLogin
    @RequestMapping(value = "statTask")
    public void statTask(HttpServletRequest request, HttpServletResponse response,
                         Integer task_id, Integer memid) {
        JsonObject jsonObject = new JsonObject();

        //加载商家提示信息
        MemberTask memberTask = new MemberTask();
        memberTask.setId(task_id);
        //查找任务
        List<MemberTask> datas = taskService.getDatas(memberTask);
        if (datas != null && datas.size() > 0) {
            MemberTask dbTask = datas.get(0);
            if (dbTask.getMemid() != memid) {
                //非法请求
                ResponseUtils.retnFailMsg(response, jsonObject, "非法请求");
                return;
            }
            //查找提示信息
            Integer goods_id = dbTask.getGoods_id();
            Goods goods = new Goods();
            goods.setId(goods_id);

            List<Goods> dbGood = goodsService.getGoods(goods);

            if (dbGood != null && dbGood.size() > 0) {
                //备注信息
                String remark = dbGood.get(0).getRemark();
            }
        }
    }

    /**
     * 删除店铺
     */
    @ISLogin
    @RequestMapping(value = "deleteShop")
    public void deleteShop(HttpServletRequest request, HttpServletResponse response,
                           Integer memid, Integer shop_id) {
        JsonObject jsonObject = new JsonObject();
        if (IsNullUtils.isNull(memid, shop_id)) {
            ResponseUtils.retnFailMsg(response, jsonObject, "参数不完整");
            return;
        }
        shopService.delete(shop_id);
        ResponseUtils.retnFailMsg(response, jsonObject, "操作完成");
    }
}
