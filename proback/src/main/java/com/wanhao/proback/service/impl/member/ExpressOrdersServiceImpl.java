/***
 * @pName proback
 * @name ExpressOrdersServiceImpl
 * @user HongWei
 * @date 2018/8/4
 * @desc
 */
package com.wanhao.proback.service.impl.member;

import com.wanhao.proback.bean.member.ExpressOrders;
import com.wanhao.proback.bean.member.ExpressOrdersView;
import com.wanhao.proback.bean.member.MerchantExpressOrdersParam;
import com.wanhao.proback.dao.member.ExpressOrdersMapper;
import com.wanhao.proback.dao.utils.ConditionUtil;
import com.wanhao.proback.service.member.ExpressHttpService;
import com.wanhao.proback.service.member.ExpressOrdersService;
import com.wanhao.proback.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class ExpressOrdersServiceImpl implements ExpressOrdersService {
    private final ExpressOrdersMapper expressOrdersMapper;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 空包网快递系统
     */
    @Autowired
    @Qualifier("KBExpressHttpServiceImpl")
    private ExpressHttpService expressHttpService;

    @Autowired
    public ExpressOrdersServiceImpl(ExpressOrdersMapper expressOrdersMapper) {
        this.expressOrdersMapper = expressOrdersMapper;
    }

    /**
     * 获取-不带分页 韦德 2018年8月4日10:06:17
     *
     * @param condition
     * @return
     */
    @Override
    public List<ExpressOrdersView> getOrdersBy(String condition) {
        String where = " AND 1=1";
        if(condition != null) {
            where = " AND (" + ConditionUtil.like("remark", condition, false, null);
            where += " OR " + ConditionUtil.like("order_id", condition, false, null) + ")";
        }
        List<ExpressOrdersView> list = expressOrdersMapper.selectBy(where);
        return list;
    }

    /**
     * 获取-带分页 韦德 2018年8月4日10:06:17
     *
     * @param page
     * @param limit
     * @param condition
     * @return
     */
    @Override
    public List<ExpressOrdersView> getOrdersLimit(Integer page, String limit, String condition) {
        // 计算分页位置
        if(!limit.equalsIgnoreCase("-1")){
            page = page - 1;
            if (page != 0){
                page = page * Integer.valueOf(limit);
            }
        }

        // 封装查询条件
        String where = " AND 1=1";
        if(condition != null) {
            where = " AND (" + ConditionUtil.like("username", condition, true, "t3");
            where += " OR " + ConditionUtil.like("real_name", condition, true, "t2");
            where += " OR " + ConditionUtil.like("phone", condition, true, "t2");
            where += " OR " + ConditionUtil.like("address", condition, true, "t2");
            where += " OR " + ConditionUtil.like("recv_address", condition, true, "t1");
            where += " OR " + ConditionUtil.like("remark", condition, true, "t1");
            where += " OR " + ConditionUtil.like("remark", condition, true, "t2");
            where += " OR " + ConditionUtil.like("express_id", condition, true, "t1") + ")";
        }

        List<ExpressOrdersView> list = expressOrdersMapper.selectLimit(page, limit, where);

        return list;
    }

    /**
     * 删除 韦德 2018年8月4日10:06:17
     *
     * @param id
     */
    @Override
    public void deleteBy(String id) {
        int res = expressOrdersMapper.deleteBy(id);
        if(res <= 0) throw new RuntimeException("删除失败");
    }

    /**
     * 获取记录总数 韦德 2018年8月4日10:06:17
     *
     * @return
     */
    @Override
    public int getOrdersCount() {
        return expressOrdersMapper.count();
    }

    /**
     * 编辑运单号 韦德 2018年8月4日23:24:46
     *
     * @param param
     */
    @Override
    public void updateExpressId(ExpressOrders param) {
        int res = expressOrdersMapper.updateExpressId(param);
        if(res <= 0) throw new RuntimeException("编辑失败");
    }

    /**
     * 快递发货  韦德 2018年8月5日00:14:32
     *
     * @param param
     */
    @Override
    public void toSendOut(ExpressOrders param) {
        param.setStatus(1);

        List<String> strings = this.sendOut(String.valueOf(param.getOrder_id()));
        if(strings.size() == 0) throw new RuntimeException("第三方接口快递发货失败");
        param.setExpress_id(strings.get(0));

        int res = expressOrdersMapper.updateStatus(param);
        if(res <= 0) throw new RuntimeException("一键发货失败");
    }

    /**
     * 批量发货 韦德 2018年8月5日01:04:28
     *
     * @param id
     */
    @Override
    public void batchToSendOut(String id) {
        List<String> expresses = this.sendOut(id);
        if(expresses.size() == 0) throw new RuntimeException("第三方接口快递发货失败");

        String[] split = id.split(",");
        if(split.length != expresses.size()){
            logger.error(split.length + "长度不一致" + JsonUtil.getJson(split));
            logger.error(expresses.size() + "长度不一致" + JsonUtil.getJson(expresses));
            throw new RuntimeException("第三方接口快递发货失败");
        }

        List<ExpressOrders> expressOrders = new ArrayList<>();
        for (int i = 0; i < expresses.size(); i++) {
            ExpressOrders expressOrder = new ExpressOrders();
            expressOrder.setOrder_id(Integer.valueOf(split[i]));
            expressOrder.setExpress_id(expresses.get(i));
            expressOrders.add(expressOrder);
        }
        logger.info(JsonUtil.getJson(expressOrders));


        int res = expressOrdersMapper.batchToSendOut(expressOrders);
        if(res <= 0) throw new RuntimeException("批量发货失败");
    }

    /**
     * 商家添加快递代发订单 韦德 2018年8月8日01:07:47
     *
     * @param param
     */
    @Override
    public void addMerchantOrder(MerchantExpressOrdersParam param) {
        int res = expressOrdersMapper.insertSingleForMerchant(param);
        if(res <= 0) throw new RuntimeException("添加失败");
    }

    /**
     * 快递发货 韦德 2018年8月8日14:12:44
     *
     * @param orderId
     * @return
     */
    @Override
    public List<String> sendOut(String orderId) {
        expressHttpService.buyExpress(null);
        String[] strings = orderId.split(",");
        List<String> expressList = new ArrayList<>();
        Arrays.stream(strings).forEach(str -> {
            expressList.add(UUID.randomUUID().toString());
        });
        logger.info("expressOrders-sendOut_result" + JsonUtil.getJson(expressList));
        return expressList;
    }

    /**
     * 根据用户id查询商家代发快递记录 韦德 2018年8月9日10:24:09
     *
     * @param userId
     * @return
     */
    @Override
    public List<ExpressOrdersView> getMerchantExpressOrders(Integer userId) {
        List<ExpressOrdersView> list = expressOrdersMapper.selectByUid(userId);
        return list;
    }

    @Override
    public void update(ExpressOrders v) {
        int res = expressOrdersMapper.updateSingle(v);
        if(res <= 0) throw new RuntimeException("编辑失败");
    }

    @Override
    public void add(ExpressOrders v) {
        int res = expressOrdersMapper.insertSingle(v);
        if(res <= 0) throw new RuntimeException("添加失败");
    }

    @Override
    public void delete(Integer id) {

    }
}
