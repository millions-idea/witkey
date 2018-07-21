package com.wanhao.proback.service.impl.shop;

import com.github.pagehelper.PageHelper;
import com.wanhao.proback.bean.shop.Shop;
import com.wanhao.proback.dao.shop.ShopMapper;
import com.wanhao.proback.service.shop.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Created by LiuLiHao on 2018/7/21 14:48.
 * 描述：
 * 作者： LiuLiHao
 */
@Service
@Transactional
public class ShopServiceImpl implements ShopService {

    @Autowired
    ShopMapper shopMapper;


    @Override
    public void add(Shop shop) {
        shopMapper.insert(shop);
    }

    @Override
    public void update(Shop shop) {
        shopMapper.updateByPrimaryKey(shop);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Shop> getShops(Shop shop) {
        if (shop.getPage() != null && shop.getRows() != null) {
            PageHelper.startPage(shop.getPage(), shop.getRows());
        }
        Example example = new Example(Shop.class);

        Example.Criteria criteria = example.createCriteria();

        //店铺名
        if (shop.getShop_name() != null) {
            criteria.andEqualTo("shop_name",shop.getShop_name());
        }

        if (shop.getShop_url() != null) {
            criteria.andEqualTo("shop_url",shop.getShop_url());
        }

        return shopMapper.selectByExample(example);

    }
}
