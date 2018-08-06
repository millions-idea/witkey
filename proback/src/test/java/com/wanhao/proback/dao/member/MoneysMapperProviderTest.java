package com.wanhao.proback.dao.member;

import com.google.common.collect.Lists;
import com.wanhao.proback.bean.finance.Moneys;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
public class MoneysMapperProviderTest {
    @Test
    public void testGetFieldNames()  {
        System.out.println(getColumns(Moneys.class, Lists.newArrayList("log_id")));
    }

    /**
     * 根据db类字段生成columns数据
     * @param clazz
     * @param excludePatterns
     * @return
     */
    private String getColumns(Class clazz, List<String> excludePatterns)  {
        StringBuffer buffer = new StringBuffer();
        Field[] aClassFields = clazz.getDeclaredFields();
        Arrays.stream(aClassFields)
                .forEach(item -> {
                    item.setAccessible(true);
                    if(!excludePatterns.contains(item.getName())){
                        buffer.append(item.getName() + ",");
                    }
                });
        return buffer.toString().substring(0, buffer.toString().length()-1);
    }

}