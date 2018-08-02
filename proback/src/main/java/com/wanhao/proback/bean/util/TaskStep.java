package com.wanhao.proback.bean.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiuLiHao on 2018/8/2 10:11.
 * 描述： 任务步骤描述 返回给前台使用 数据库不存
 * 作者： LiuLiHao
 */
@Setter
@Getter
@ToString
public class TaskStep implements Serializable {

    private int allStep;
    private int currentStep;
    //当前步骤到了哪一步
    private String step;
    //显示任务提示
    private List<String> stepInfos = new ArrayList<>();

    //参数
    private String[] params;

    //添加提示
    public void addInfo(String info){
        this.stepInfos.add(info);
    }


}
