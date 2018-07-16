package com.wanhao.proback;

import com.wanhao.proback.bean.admin.Admin;
import com.wanhao.proback.service.admin.AdminService;
import com.wanhao.proback.service.member.NameForbiddenService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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
        List<Admin> list = adminService.findAll();
        System.out.println(list);

    }

    @Autowired
    NameForbiddenService nameForbiddenService;

}
