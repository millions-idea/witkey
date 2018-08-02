package com.wanhao.proback.service.impl.member;

import com.github.pagehelper.PageHelper;
import com.wanhao.proback.bean.member.MemberTask;
import com.wanhao.proback.dao.member.MemberTaskMapper;
import com.wanhao.proback.service.BaseServiceImpl;
import com.wanhao.proback.service.member.MemberTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Created by LiuLiHao on 2018/7/26 17:51.
 * 描述：
 * 作者： LiuLiHao
 */
@Service
@Transactional
public class MemberTaskServiceImpl extends BaseServiceImpl<MemberTask> implements MemberTaskService {


    @Autowired
    MemberTaskMapper taskMapper;

    @Override
    @Transactional(readOnly = true)
    public List<MemberTask> getDatas(MemberTask memberTask) {
        if (memberTask.getPage() != null && memberTask.getRows() != null) {
            PageHelper.startPage(memberTask.getPage(), memberTask.getRows());
        }

        Example example = new Example(memberTask.getClass());
        Example.Criteria criteria = example.createCriteria();

        //id
        if (memberTask.getId()!=null){
            criteria.andEqualTo("id",memberTask.getId());
        }

        //会员id查询
        if (memberTask.getBuy_memid()!=null){
            criteria.andEqualTo("buy_memid",memberTask.getBuy_memid());
        }
        //时间限制
        if (memberTask.getDay_limit()!=null && memberTask.getDay_limit()>0){
            criteria.andGreaterThan("pick_time","DATE_ADD(CURDATE(), INTERVAL -"+memberTask.getDay_limit()
                    +" DAY) ");
        }

        //goodsid查询
        if (memberTask.getGoods_id()!=null){
            criteria.andEqualTo("goods_id",memberTask.getGoods_id());
        }
        //buy_account_id查询
        if (memberTask.getBuy_account_id()!=null){
            criteria.andEqualTo("buy_account_id",memberTask.getBuy_account_id());
        }

        //flag
        if (memberTask.getTask_flag()!=null){
            criteria.andEqualTo("task_flag",memberTask.getTask_flag());
        }

        return taskMapper.selectByExample(example);
    }
}
