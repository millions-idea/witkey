/***
 * @pName proback
 * @name ExpressGoodsService
 * @user HongWei
 * @date 2018/8/2
 * @desc
 */
package com.wanhao.proback.service.member;

import com.wanhao.proback.bean.member.ExpressGoods;
import com.wanhao.proback.bean.member.ExpressGoodsJsonView;
import com.wanhao.proback.bean.member.ExpressGoodsView;
import com.wanhao.proback.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ExpressGoodsService extends BaseService<ExpressGoods> {
    /**
     * 获取快递平台商品集合 韦德 2018年8月2日23:34:08
     * @param page
     * @param limit
     * @param condition
     * @return
     */
    List<ExpressGoodsView> getGoodsLimit(Integer page, String limit, String condition);

    /**
     * 查询快递商品记录总数 韦德 2018年8月2日23:43:13
     * @return
     */
    int getGoodsCount();

    /**
     * 删除商品 韦德 2018年8月3日21:49:02
     * @param id
     */
    void deleteBy(String id);

    /**
     * 获取快递平台商品集合 韦德 2018年8月7日23:44:22
     * @return
     */
    List<ExpressGoodsJsonView> getGoods();

    /**
     * 根据id查询商品信息 韦德 2018年8月8日16:22:42
     * @param goodsId
     * @return
     */
    ExpressGoodsView getById(Integer goodsId);

    /**
     * 通过id数组查询商品信息-不去重 韦德 2018年8月8日19:39:28
     * @param goodsList
     * @return
     */
    List<ExpressGoods> getInIdByUnionAll(List<Integer> goodsList) ;
}
