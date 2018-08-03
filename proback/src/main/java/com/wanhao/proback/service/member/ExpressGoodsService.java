/***
 * @pName proback
 * @name ExpressGoodsService
 * @user HongWei
 * @date 2018/8/2
 * @desc
 */
package com.wanhao.proback.service.member;

import com.wanhao.proback.bean.member.ExpressGoods;
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
    List<ExpressGoodsView> getGoodses(Integer page, String limit, String condition);


    /**
     * 查询快递商品记录总数 韦德 2018年8月2日23:43:13
     * @return
     */
    int getGoodsCount();
}
