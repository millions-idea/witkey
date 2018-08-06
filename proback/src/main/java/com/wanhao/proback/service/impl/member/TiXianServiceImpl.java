package com.wanhao.proback.service.impl.member;

import com.github.pagehelper.PageHelper;
import com.wanhao.proback.bean.finance.TransferParam;
import com.wanhao.proback.bean.member.TiXian;
import com.wanhao.proback.dao.member.TiXianMapper;
import com.wanhao.proback.service.BaseServiceImpl;
import com.wanhao.proback.service.finance.PayService;
import com.wanhao.proback.service.member.TiXianService;
import com.wanhao.proback.utils.IsNullUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Created by LiuLiHao on 2018/7/25 19:00.
 * 描述：
 * 作者： LiuLiHao
 */
@Service
@Transactional
public class TiXianServiceImpl extends BaseServiceImpl<TiXian> implements TiXianService {

    @Autowired
    TiXianMapper tiXianMapper;

    @Override
    @Transactional(readOnly = true)
    public List<TiXian> getDatas(TiXian tiXian) {
        if (tiXian.getPage() != null && tiXian.getRows() != null) {
            PageHelper.startPage(tiXian.getPage(), tiXian.getRows());
        }

        Example example = new Example(tiXian.getClass());
        Example.Criteria criteria = example.createCriteria();
        //会员id查询
        if (tiXian.getMemid()!=null){
            criteria.andEqualTo("memid",tiXian.getMemid());
        }

        //id查询
        if (tiXian.getId()!=null){
            criteria.andEqualTo("id",tiXian.getId());
        }

        return tiXianMapper.selectByExample(example);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TiXian> getTodayList(Integer id) {
        return tiXianMapper.getTodayList(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TiXian> getTiXianData(TiXian tiXian) {
        StringBuilder sb = new StringBuilder();
        sb.append(" AND 1=1 ");
        //id
        if(tiXian.getId() != null) {
            sb.append(" AND t.id= "+tiXian.getId()+"  ");
        }
        //flag
        if(tiXian.getFlag() != null) {
            sb.append(" AND t.flag= "+tiXian.getFlag()+"  ");
        }
        //会员Id
        if(tiXian.getMemid() != null) {
            sb.append(" AND t.memid= "+tiXian.getMemid()+"  ");
        }

        //真实姓名
        if(tiXian.getReal_name() != null) {
            sb.append(" AND m.real_name= "+tiXian.getReal_name()+"  ");
        }

        return tiXianMapper.getDatas(sb.toString());
    }

    @Autowired
    private PayService payService;


    @Override
    public void agreeAllTiXian(String id) {
        TiXian tiXian = new TiXian();

        //拆分id
        if (IsNullUtils.isNotNull(id)){
            String[] split = id.split(",");
            if (IsNullUtils.arrayIsNotNull(split)){
                //循环设置
                for (String tixianId : split) {
                    tiXian.setId(Integer.valueOf(tixianId));
                    //数据库中的
                    TiXian dbTiXian = tiXianMapper.selectByPrimaryKey(tiXian);
                    Double money = dbTiXian.getMoney();
                    //todo 转账 39 为admin的id
                    TransferParam transferParam = new TransferParam(39, dbTiXian.getMemid(), money, "提现",
                            1, null);
                    payService.transfer(transferParam);
                    //没有异常就是交易成功
                    dbTiXian.setFlag(1);

                }

            }
        }
    }

    @Override
    public void rejectAllTiXian(String id, String reason) {
        //直接设置拒绝
        int i = tiXianMapper.rejectAllTiXian(id,reason);
        if (i<=0) throw new RuntimeException("更新失败");
    }


}
