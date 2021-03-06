package com.wanhao.proback.service.member;

import com.wanhao.proback.bean.member.TiXian;
import com.wanhao.proback.service.BaseService;

import java.util.List;

/**
 * Created by LiuLiHao on 2018/7/25 19:00.
 * 描述：
 * 作者： LiuLiHao
 */
public interface TiXianService  extends BaseService<TiXian> {

    List<TiXian> getDatas(TiXian tiXian);


    /**
     * 今日提现数据
     * @return
     */
    List<TiXian> getTodayList(Integer id);

    List<TiXian> getTiXianData(TiXian tiXian);

    void agreeAllTiXian(String id);

    void rejectAllTiXian(String id, String reason);

}
