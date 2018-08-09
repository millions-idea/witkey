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
import com.wanhao.proback.exception.NormalException;
import com.wanhao.proback.service.BaseServiceImpl;
import com.wanhao.proback.service.member.ExpressPostalAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
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
     * @param userId
     * @return
     */
    @Override
    public List<ExpressPostalAddress> getPostalAddresses(Integer userId) {
        List<ExpressPostalAddress> list = expressPostalAddressMapper.selectByOrder(userId);
        return list;
    }

    @Override
    public void delete(Integer id) {
        ExpressPostalAddress expressPostalAddress = new ExpressPostalAddress();
        expressPostalAddress.setAddress_id(id);
        expressPostalAddressMapper.delete(expressPostalAddress);
    }

    @Override
    public void update(ExpressPostalAddress v) {
        expressPostalAddressMapper.updateById(v);
    }

    /**
     * 创建并限制添加数量 韦德 2018年8月9日17:29:47
     *
     * @param param
     */
    @Override
    public void addExpressAddress(ExpressPostalAddress param) {
        int count = expressPostalAddressMapper.selectCreateCount(param.getUser_id());
        if(count + 1 > 10) throw new NormalException("添加已达到上限");
        if(param.getSort() == null) param.setSort(0);
        param.setAdd_date(new Date());
        expressPostalAddressMapper.insert(param);
    }
}
