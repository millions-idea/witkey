/***
 * @pName proback
 * @name ExpressGoodsServiceImpl
 * @user HongWei
 * @date 2018/8/2
 * @desc
 */
package com.wanhao.proback.service.impl.member;

import com.google.common.collect.Lists;
import com.wanhao.proback.bean.member.ExpressGoods;
import com.wanhao.proback.bean.member.ExpressGoodsView;
import com.wanhao.proback.dao.member.ExpressGoodsMapper;
import com.wanhao.proback.dao.utils.ConditionUtil;
import com.wanhao.proback.service.member.ExpressGoodsService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ExpressGoodsServiceImpl implements ExpressGoodsService {
    private final ExpressGoodsMapper expressGoodsMapper;

    @Autowired
    public ExpressGoodsServiceImpl(ExpressGoodsMapper expressGoodsMapper) {
        this.expressGoodsMapper = expressGoodsMapper;
    }

    /**
     * 获取快递平台商品集合 韦德 2018年8月2日23:34:08
     *
     * @param page
     * @param limit
     * @param condition
     * @return
     */
    @Override
    public List<ExpressGoodsView> getGoodses(Integer page, String limit, String condition) {
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
            where = " AND (" + ConditionUtil.like("name", condition, true, "t3");
            where += " OR " + ConditionUtil.like("name", condition, true, "t1");
            where += " OR " + ConditionUtil.like("goods_id", condition, true, "t1") + ")";
        }

        List<ExpressGoodsView> expressGoodsViews = expressGoodsMapper.selectLimit(page, limit, where);

        // 计算视图字段值
        expressGoodsViews.stream().forEach(o -> {
            o.setSell_price((o.getPrice() * (100 + o.getRate())) / 100);
            o.setDiff_price(o.getSell_price() - o.getPrice());
        });

        return expressGoodsViews;
    }



    /**
     * 查询快递商品记录总数 韦德 2018年8月2日23:43:13
     *
     * @return
     */
    @Override
    public int getGoodsCount() {
        return expressGoodsMapper.count();
    }

    /**
     * 删除商品 韦德 2018年8月3日21:49:02
     *
     * @param id
     */
    @Override
    public void deleteBy(String id) {
        int res = expressGoodsMapper.deleteBy(id);
        if(res <= 0) throw new RuntimeException("删除失败");
    }

    @Override
    public void update(ExpressGoods v) {
        int res = expressGoodsMapper.updateSingle(v);
        if(res <= 0) throw new RuntimeException("编辑失败");
    }

    @Override
    public void add(ExpressGoods v) {
        int res = expressGoodsMapper.insertSingle(v);
        if(res <= 0) throw new RuntimeException("添加失败");
    }

    @Override
    public void delete(Integer id) {

    }
}
