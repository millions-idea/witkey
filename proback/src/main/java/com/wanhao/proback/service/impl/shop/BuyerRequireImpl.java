package com.wanhao.proback.service.impl.shop;

import com.github.pagehelper.PageHelper;
import com.wanhao.proback.bean.shop.BuyerRequire;
import com.wanhao.proback.dao.shop.BuyerRequireMapper;
import com.wanhao.proback.service.BaseServiceImpl;
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
public class BuyerRequireImpl extends BaseServiceImpl<BuyerRequire>
        implements BuyerRequireService {

    @Autowired
    BuyerRequireMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<BuyerRequire> getBuyerRequires(BuyerRequire require) {
        if (require.getPage() != null && require.getRows() != null) {
            PageHelper.startPage(require.getPage(), require.getRows());
        }

        Example example = new Example(BuyerRequire.class);
        Example.Criteria criteria = example.createCriteria();
        if (require.getGoods_id()!=null){
            criteria.andEqualTo("goods_id",require.getGoods_id());
        }
        return mapper.selectByExample(example);
    }

}
