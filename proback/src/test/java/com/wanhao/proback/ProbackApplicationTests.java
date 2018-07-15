package com.wanhao.proback;

import com.wanhao.proback.bean.member.NameForbidden;
import com.wanhao.proback.service.admin.AdminService;
import com.wanhao.proback.service.member.NameForbiddenService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProbackApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    AdminService adminService;

    @Test
    public void testLoadAdmin(){
        NameForbidden nameForbidden = new NameForbidden();
        nameForbidden.setId(1);
        nameForbidden.setName("系统管理员,管理员,习近平,操你妈,操死你,烂逼,狗逼,狗币,你妈死了,你麻痹,你妈逼,习近平,胡锦涛,超管,网站制作者,网站老大,你爸爸,你爹死了,我操你爹,我是你爹");
        nameForbiddenService.update(nameForbidden);


    }

    @Autowired
    NameForbiddenService nameForbiddenService;

}
