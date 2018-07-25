package com.wanhao.proback.service;

import com.wanhao.proback.bean.BaseBean;
import com.wanhao.proback.utils.MyMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by LiuLiHao on 2018/7/25 19:21.
 * 描述：service 默认实现类
 * 作者： LiuLiHao
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {

    @Autowired
    protected MyMapper<T> myMapper;

    @Override
    public void update(T v) {
        myMapper.updateByPrimaryKeySelective(v);
    }

    @Override
    public void add(T v) {
        myMapper.insertSelective(v);
    }

    @Override
    public void delete(Integer id) {
        BaseBean baseBean = new BaseBean();
        baseBean.setId(id);
        myMapper.deleteByPrimaryKey(baseBean);
    }

}
