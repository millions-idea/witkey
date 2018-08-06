package com.wanhao.proback.service.impl.member;

import com.github.pagehelper.PageHelper;
import com.wanhao.proback.bean.member.Member;
import com.wanhao.proback.bean.member.MemberView;
import com.wanhao.proback.bean.util.InviteResult;
import com.wanhao.proback.dao.member.MemberMapper;
import com.wanhao.proback.service.member.MemberService;
import com.wanhao.proback.utils.GsonUtils;
import com.wanhao.proback.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by LiuLiHao on 2018/7/16 12:29.
 * 描述：会员操作
 * 作者： LiuLiHao
 */
@Service
@Transactional
@CacheConfig(cacheNames = "express")
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberMapper memberMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    @Transactional(readOnly = true)
    public List<Member> getMembers(Member member) {
        if (member.getPage() != null && member.getRows() != null) {
            PageHelper.startPage(member.getPage(), member.getRows());
        }
        Example example = new Example(Member.class);

        Example.Criteria criteria = example.createCriteria();
        //会员名
        if (member.getUsername() != null && member.getUsername().length() > 0) {
            criteria.andLike("username", "%" + member.getUsername() + "%");
        }

        //代理商
        if (member.getProxy() != null && member.getProxy().length() > 0) {
            criteria.andLike("proxy", "%" + member.getProxy() + "%");
        }

        //真实姓名
        if (member.getReal_name() != null && member.getReal_name().length() > 0) {
            criteria.andLike("real_name", "%" + member.getReal_name() + "%");
        }

        //手机号
        if (member.getMobile() != null && member.getMobile().length() > 0) {
            criteria.andLike("mobile", member.getMobile() + "%");
        }
        //推荐人
        if (member.getInvite_id() != null && member.getInvite_id() > 0) {
            criteria.andLike("invite_id", member.getInvite_id() + "%");
        }
        //真实姓名
        if (member.getReal_name() != null && member.getReal_name().length() > 0) {
            criteria.andLike("real_name", "%" + member.getReal_name() + "%");
        }
        //用户类型
        if (member.getVipmodel() != null && member.getVipmodel().length() > 0) {
            criteria.andEqualTo("real_name", member.getVipmodel());
        }
        //是否已实名
        if (member.getIs_real_name() != null && member.getIs_real_name() > 0) {
            criteria.andEqualTo("is_real_name", member.getIs_real_name());
        }
        //性别
        if (member.getGender() != null && member.getGender().length() > 0 && !member.getGender().equals("0")) {
            criteria.andEqualTo("gender", member.getGender());
        }
        //所在地区
        if (member.getSheng() != null && member.getSheng().length() > 0) {
            criteria.andEqualTo("sheng", member.getSheng());
        }

        //是否已经实名
        if (member.getIs_real_name() != null) {
            criteria.andEqualTo("is_real_name", member.getIs_real_name());
        }

        //是否上传了图片
        if (member.getZheng() != null) {
            criteria.andIsNotNull("zheng");
        }
        if (member.getFan() != null) {
            criteria.andIsNotNull("fan");
        }
        if (member.getShou_chi() != null) {
            criteria.andIsNotNull("shou_chi");
        }

        return memberMapper.selectByExample(example);

    }

    @Override
    @Transactional(readOnly = true)
    public Member getMember(Member member) {
        Member dbMem = memberMapper.selectByPrimaryKey(member);
        //放入缓存
        redisTemplate.opsForValue().set("member_"+member.getId(),dbMem);
        return dbMem;
    }

    @Override
    public void updateMember(Member member) {
        memberMapper.updateByPrimaryKeySelective(member);
        //清除缓存
        redisTemplate.delete("member_"+member.getId());

    }

    @Override
    public void addMember(Member member) {
        int i = memberMapper.insertSelective(member);
        if (i<=0) throw new RuntimeException("添加失败");
    }

    @Override
    @Transactional(readOnly = true)
    //@Cacheable(key = "#p0.username")
    public List<Member> loginMember(Member member) {
        Example example = new Example(Member.class);

        Example.Criteria criteria = example.createCriteria();
        //会员名
        criteria.andEqualTo("username", member.getUsername());
        //密码
        criteria.andEqualTo("password", member.getPassword());

        //手机号
        if (member.getMobile() != null && member.getMobile().length() > 0) {
            criteria.andEqualTo("mobile", member.getMobile());
        }
        return memberMapper.selectByExample(example);
    }

    @Override
    @Transactional(readOnly = true)
    // @Cacheable(key = "#p0.mobile")
    public List<Member> loginMemberByMobile(Member member) {
        Example example = new Example(Member.class);

        Example.Criteria criteria = example.createCriteria();
        //手机号
        criteria.andEqualTo("mobile", member.getMobile());
        //密码
        //criteria.andEqualTo("token", express.getToken());

        return memberMapper.selectByExample(example);
    }

    @Override
    //@Cacheable(key = "#invite_name")
    @Transactional(readOnly = true)
    public Member getMemberByUserName(String invite_name) {
        Example example = new Example(Member.class);

        Example.Criteria criteria = example.createCriteria();
        Member member = new Member();
        member.setUsername(invite_name);
        //手机号
        criteria.andEqualTo("username", member.getUsername());
        List<Member> members = memberMapper.selectByExample(example);
        if (member != null && members.size() > 0) {
            return members.get(0);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Member> getMemberFristInvite(Integer id) {
        Example example = new Example(Member.class);

        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("invite_id", id);
        return memberMapper.selectByExample(example);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Member> getMemberSecondInvite(Integer memid) {
        LinkedList<Member> members = new LinkedList<>();

        List<Member> memberFristInvite = getMemberFristInvite(memid);
        //查询一级下线
        if (memberFristInvite!=null && memberFristInvite.size()>0){

            for (Member member : memberFristInvite) {
                Integer id = member.getId();
                List<Member> list = getMemberFristInvite(id);
                if (list!=null && list.size()>0){
                    members.addAll(list);
                }
            }

        }
        return members;
    }

    @Override
    @Transactional(readOnly = true)
    public List<InviteResult> getInviteData() {
        return memberMapper.getInviteData();
    }

    @Override
    @Transactional(readOnly = true)
    public List<InviteResult> getInviteDataByWeek() {
        return memberMapper.getInviteDataByWeek();
    }

    @Override
    @Transactional(readOnly = true)
    public List<InviteResult> getInviteDataByMonth() {
        return memberMapper.getInviteDataByMonth();
    }

    @Override
    public void delete(String id) {
        int i = memberMapper.deleteAll(id);
        if (i<=0) throw new RuntimeException("删除失败");
    }

    @Override
    public void agreeAll(String id) {
        memberMapper.agreeAll(id);
    }

    @Override
    public void rejectAll(String id, String reason) {
        memberMapper.rejectAll(id,reason);
    }

    /**
     * 根据id查询用户,钱包信息 韦德 2018年8月7日00:18:34
     *
     * @param id
     * @return
     */
    @Override
    public MemberView getMemberByIdForDB(Integer id) {
        MemberView memberView = memberMapper.selectById(id);
        if(memberView != null) redisTemplate.opsForValue().set("member_"+memberView.getId(), JsonUtil.getJson(memberView),45, TimeUnit.MINUTES);
        return memberView;
    }

    /**
     * 根据id查询用户的缓存 韦德 2018年8月7日00:32:41
     *
     * @param id
     * @return
     */
    @Override
    public MemberView getMemberByIdForCache(Integer id) {
        Object obj = redisTemplate.opsForValue().get("member_" + id);
        if(obj != null) return JsonUtil.getModel(String.valueOf(obj), MemberView.class);
        return null;
    }

    /**
     * 根据id查询用户视图信息-先查缓存后查数据库 韦德 2018年8月7日00:37:52
     *
     * @param id
     * @return
     */
    @Override
    public MemberView getMemberById(Integer id) {
        MemberView memberView = this.getMemberByIdForCache(id);
        if(memberView == null) memberView = this.getMemberByIdForDB(id);
        return memberView;
    }
}
