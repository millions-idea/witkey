package com.wanhao.proback;

import com.wanhao.proback.bean.admin.Admin;
import com.wanhao.proback.service.admin.AdminService;
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
        Admin one = adminService.findByName("admin");
        System.out.println(one);
    }
}
