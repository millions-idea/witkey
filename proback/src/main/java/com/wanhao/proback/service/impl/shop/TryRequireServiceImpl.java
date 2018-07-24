package com.wanhao.proback.service.impl.shop;

import com.github.pagehelper.PageHelper;
import com.wanhao.proback.bean.shop.TryRequire;
import com.wanhao.proback.dao.shop.TryRequireMapper;
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
public class TryRequireServiceImpl implements TryRequireService {
    @Autowired
    TryRequireMapper mapper;


    @Override
    @Transactional(readOnly = true)
    public List<TryRequire> getGoods(TryRequire require) {
        if (require.getPage() != null && require.getRows() != null) {
            PageHelper.startPage(require.getPage(), require.getRows());
        }

        Example example = new Example(TryRequire.class);

        return mapper.selectByExample(example);
    }

    @Override
    public void update(TryRequire require) {
        mapper.updateByPrimaryKeySelective(require);
    }

    @Override
    public void add(TryRequire require) {
        mapper.insertSelective(require);
    }

    @Override
    public void delete(int id) {
        TryRequire tryRequire = new TryRequire();
        tryRequire.setId(id);
        mapper.deleteByPrimaryKey(tryRequire);

    }
}
