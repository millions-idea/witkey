package com.wanhao.proback.service.impl.shop;

import com.github.pagehelper.PageHelper;
import com.wanhao.proback.bean.shop.Goods;
import com.wanhao.proback.dao.shop.GoodsMapper;
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
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    GoodsMapper mapper;


    @Override
    @Transactional(readOnly = true)
    public List<Goods> getGoods(Goods goods) {
        if (goods.getPage() != null && goods.getRows() != null) {
            PageHelper.startPage(goods.getPage(), goods.getRows());
        }

        Example example = new Example(Goods.class);

        return mapper.selectByExample(example);
    }

    @Override
    public void update(Goods goods) {
        mapper.updateByPrimaryKeySelective(goods);
    }

    @Override
    public void add(Goods goods) {
        mapper.insertSelective(goods);
    }

    @Override
    public void delete(int id) {
        Goods goods = new Goods();
        goods.setId(id);
        mapper.deleteByPrimaryKey(goods);
    }
}
