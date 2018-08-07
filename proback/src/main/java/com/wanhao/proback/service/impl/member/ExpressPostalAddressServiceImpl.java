/***
 * @pName proback
 * @name ExpressPostalAddressServiceImpl
 * @user HongWei
 * @date 2018/8/7
 * @desc
 */
package com.wanhao.proback.service.impl.member;

import com.wanhao.proback.bean.member.ExpressPostalAddress;
import com.wanhao.proback.dao.member.ExpressPostalAddressMapper;
import com.wanhao.proback.service.BaseServiceImpl;
import com.wanhao.proback.service.member.ExpressPostalAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpressPostalAddressServiceImpl extends BaseServiceImpl<ExpressPostalAddress> implements ExpressPostalAddressService {
    private final ExpressPostalAddressMapper expressPostalAddressMapper;

    @Autowired
    public ExpressPostalAddressServiceImpl(ExpressPostalAddressMapper expressPostalAddressMapper) {
        this.expressPostalAddressMapper = expressPostalAddressMapper;
    }

    /**
     * 获取发货地址列表 韦德 2018年8月7日22:56:12
     *
     * @return
     */
    @Override
    public List<ExpressPostalAddress> getPostalAddresses() {
        List<ExpressPostalAddress> list = expressPostalAddressMapper.selectAll();
        list.stream().forEach(exp -> {
            exp.setPhone(null);
            exp.setReal_name(null);
        });
        return list;
    }
}
