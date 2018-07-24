package com.wanhao.proback.service.impl.shop;

import com.github.pagehelper.PageHelper;
import com.wanhao.proback.bean.shop.BuyerRequire;
import com.wanhao.proback.dao.shop.BuyerRequireMapper;
import com.wanhao.proback.service.shop.BuyerRequireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Created by LiuLiHao on 2018/7/24 16:21.
 * 描述：
 * 作者： LiuLiHao
 */
@Service
@Transactional
public class BuyerRequireImpl implements BuyerRequireService {

    @Autowired
    BuyerRequireMapper mapper;


    @Override
    @Transactional(readOnly = true)
    public List<BuyerRequire> getGoods(BuyerRequire require) {
        if (require.getPage() != null && require.getRows() != null) {
            PageHelper.startPage(require.getPage(), require.getRows());
        }

        Example example = new Example(BuyerRequire.class);

        return mapper.selectByExample(example);
    }

    @Override
    public void update(BuyerRequire require) {
        mapper.updateByPrimaryKeySelective(require);
    }

    @Override
    public void add(BuyerRequire require) {
        mapper.insertSelective(require);
    }

    @Override
    public void delete(int id) {
        BuyerRequire buyerRequire = new BuyerRequire();
        buyerRequire.setId(id);
        mapper.deleteByPrimaryKey(buyerRequire);
    }
}
