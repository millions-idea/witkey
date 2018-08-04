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
import com.wanhao.proback.dao.member.ExpressGoodsMapper;
import com.wanhao.proback.dao.member.ExpressOrdersMapper;
import com.wanhao.proback.dao.utils.ConditionUtil;
import com.wanhao.proback.service.member.ExpressOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpressOrdersServiceImpl implements ExpressOrdersService {
    private final ExpressOrdersMapper expressOrdersMapper;

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
            where = " AND (" + ConditionUtil.like("remark", condition, true, "t1");
            where += " OR " + ConditionUtil.like("order_id", condition, true, "t1") + ")";
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