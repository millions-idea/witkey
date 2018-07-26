package com.wanhao.proback.service.impl.shop;

import com.github.pagehelper.PageHelper;
import com.wanhao.proback.bean.shop.Goods;
import com.wanhao.proback.dao.shop.GoodsMapper;
import com.wanhao.proback.service.BaseServiceImpl;
import com.wanhao.proback.service.shop.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Created by LiuLiHao on 2018/7/24 16:18.
 * 描述：
 * 作者： LiuLiHao
 */
@Service
@Transactional
public class GoodsServiceImpl extends BaseServiceImpl<Goods> implements GoodsService {

    @Autowired
    GoodsMapper mapper;


    @Override
    @Transactional(readOnly = true)
    public List<Goods> getGoods(Goods goods) {

        if (goods.getPage() != null && goods.getRows() != null && goods.getOrderBY() != null) {
            //带排序
            PageHelper.startPage(goods.getPage(), goods.getRows(), goods.getOrderBY());
        } else if (goods.getPage() != null && goods.getRows() != null) {
            PageHelper.startPage(goods.getPage(), goods.getRows());
        }

        Example example = new Example(Goods.class);
        Example.Criteria criteria = example.createCriteria();
        //id
        if (goods.getId() != null) {
            criteria.andEqualTo("id", goods.getId());
        }
        //会员id
        if (goods.getMemid() != null) {
            criteria.andEqualTo("memid", goods.getMemid());
        }
        //类型
        if (goods.getGoods_class_id() != null) {
            criteria.andEqualTo("goods_class_id", goods.getGoods_class_id());
        }

        return mapper.selectByExample(example);
    }
}
