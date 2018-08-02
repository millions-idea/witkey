package com.wanhao.proback.service.impl.member;

import com.wanhao.proback.bean.member.ExpressPlatform;
import com.wanhao.proback.dao.member.ExpressPlatformMapper;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.isA;
import static org.junit.Assert.*;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

/**
 * 快递空包平台服务单元测试 韦德 2018年8月1日15:09:53
 */
@RunWith(SpringRunner.class)
public class ExpressPlatformServiceImplTest {
    private ExpressPlatformMapper expressPlatformMapper;

    @Before
    public void setUp() throws Exception {
        expressPlatformMapper = Mockito.mock(ExpressPlatformMapper.class);
    }

    /**
     * 查询所有平台集合 韦德 2018年8月1日15:16:48
     */
    @Test
    public void testGetPlatformsSuccess(){
        when(expressPlatformMapper.selectLimit(0,"30", null)).thenReturn(Lists.newArrayList(
                new ExpressPlatform(1, "空包100网", "http://www.kongbao100.com/", 1,0),
                new ExpressPlatform(1, "刷宝空包网", "http://www.shuabaokb.com", 1,0)
        ));
        List<ExpressPlatform> platforms = expressPlatformMapper.selectLimit(0,"30", null);
        assertNotNull("所有快递空包平台列表", platforms);
        verify(expressPlatformMapper).selectLimit(0,"30", null);
    }

}