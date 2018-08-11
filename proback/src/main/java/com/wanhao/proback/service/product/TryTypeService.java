/***
 * @pName proback
 * @name TryTypeService
 * @user HongWei
 * @date 2018/8/11
 * @desc
 */
package com.wanhao.proback.service.product;

import com.wanhao.proback.bean.product.TryType;
import com.wanhao.proback.service.BaseService;

import java.util.List;

public interface TryTypeService extends BaseService<TryType> {
    /**
     * 查询全部类型 韦德 2018年8月11日12:26:04
     * @return
     */
    List<TryType> selectAll();
}
