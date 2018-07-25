package com.wanhao.proback.service.impl.member;

import com.github.pagehelper.PageHelper;
import com.wanhao.proback.bean.member.MemberBank;
import com.wanhao.proback.dao.member.MemberBankMapper;
import com.wanhao.proback.service.BaseServiceImpl;
import com.wanhao.proback.service.member.MemberBankService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Created by LiuLiHao on 2018/7/20 11:21.
 * 描述：
 * 作者： LiuLiHao
 */
@Service
@Transactional
public class MemberBankServiceImpl extends BaseServiceImpl<MemberBank> implements MemberBankService {
    @Autowired
    MemberBankMapper bankMapper;


    @Override
    @Transactional(readOnly = true)
    public List<MemberBank> findByPages(MemberBank memberBank) {
        if (memberBank.getPage() != null && memberBank.getRows() != null) {
            PageHelper.startPage(memberBank.getPage(), memberBank.getRows());
        }
        Example example = new Example(MemberBank.class);

        Example.Criteria criteria = example.createCriteria();

        //id
        if (memberBank.getId() != null) {
            criteria.andEqualTo("id",memberBank.getId());
        }

        //会员id
        if (memberBank.getMem_id() != null) {
            criteria.andEqualTo("mem_id",memberBank.getMem_id());
        }
        //is_auth
        if (memberBank.getIs_auth() != null) {
            criteria.andEqualTo("is_auth",memberBank.getIs_auth());
        }
        //bank_username
        if (StringUtils.isNotBlank(memberBank.getBank_username())){
            criteria.andLike("bank_username",memberBank.getBank_username() + "%" );
        }

        //bank_num
        if (StringUtils.isNotBlank(memberBank.getBank_num())){
            criteria.andLike("bank_num",memberBank.getBank_num());
        }

        //type
        if (memberBank.getBank_type() != null) {
            criteria.andEqualTo("bank_type",memberBank.getBank_type());
        }
        return bankMapper.selectByExample(example);
    }

}
