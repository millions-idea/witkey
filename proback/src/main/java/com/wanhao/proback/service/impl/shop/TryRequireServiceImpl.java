package com.wanhao.proback.service.impl.shop;

import com.github.pagehelper.PageHelper;
import com.wanhao.proback.bean.shop.TryRequire;
import com.wanhao.proback.dao.shop.TryRequireMapper;
import com.wanhao.proback.service.BaseServiceImpl;
import com.wanhao.proback.service.shop.TryRequireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Created by LiuLiHao on 2018/7/24 16:20.
 * 描述：
 * 作者： LiuLiHao
 */
@Service
@Transactional
public class TryRequireServiceImpl extends BaseServiceImpl<TryRequire> implements TryRequireService {
    @Autowired
    TryRequireMapper mapper;


    @Override
    @Transactional(readOnly = true)
    public List<TryRequire> get(TryRequire require) {
        if (require.getPage() != null && require.getRows() != null) {
            PageHelper.startPage(require.getPage(), require.getRows());
        }

        Example example = new Example(TryRequire.class);
        Example.Criteria criteria = example.createCriteria();

        if (require.getGoods_id()!=null){
            criteria.andEqualTo("goods_id",require.getGoods_id());
        }
        return mapper.selectByExample(example);
    }
}
