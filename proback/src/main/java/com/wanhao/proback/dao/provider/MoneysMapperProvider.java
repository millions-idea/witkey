/***
 * @pName proback
 * @name MoneysMapperProvider
 * @user HongWei
 * @date 2018/8/5
 * @desc
 */
package com.wanhao.proback.dao.provider;

import com.google.common.collect.Lists;
import com.wanhao.proback.bean.finance.Moneys;
import com.wanhao.proback.dao.utils.SqlBufferUtil;

import java.util.List;
import java.util.Map;

/***
 * 资金变化表sql扩展类
 */
public class MoneysMapperProvider {
    /**
     * 批量插入记录
     * @param map
     * @return
     */
    public String batchInsert(Map map) {
        List<Moneys> moneysList = (List<Moneys>) map.get("list");
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("INSERT INTO tb_moneys(%s) ", SqlBufferUtil.getColumns(Moneys.class, Lists.newArrayList("log_id"))));
        sb.append("VALUES");
        for (int i = 0; i < moneysList.size(); i++) {
            sb.append("(");
            sb.append(String.format("#{list[%d].%s},", i, "record_id"));
            sb.append(String.format("#{list[%d].%s},", i, "from_uid"));
            sb.append(String.format("#{list[%d].%s},", i, "trade_type"));
            sb.append(String.format("#{list[%d].%s},", i, "trade_amount"));
            sb.append(String.format("(SELECT balance FROM tb_wallets WHERE user_id = %s),", String.format("#{list[%d].%s}", i , "from_uid")));
            sb.append(String.format("#{list[%d].%s},", i, "remark"));
            sb.append("NOW()");
            sb.append(")");
            if (i < moneysList.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

}
