package com.wanhao.proback.controller.admin;

import com.wanhao.proback.bean.admin.Admin;
import com.wanhao.proback.service.admin.AdminService;
import com.wanhao.proback.utils.IpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * Created by LiuLiHao on 2018/7/14 18:51.
 * 描述：管理员操作
 * 作者： LiuLiHao
 */
@RequestMapping(value = "myAdmin")
@Controller
public class AdminController {

    @Autowired
    AdminService adminService;

    /**
     * 跳转到登录页
     * @return
     */
    @RequestMapping(value = "toLogin",method = RequestMethod.GET)
    public String toLogin( @RequestParam(value = "error", required = false) String error,
                           @RequestParam(value = "logout", required = false) String logout,
                           HttpServletRequest request,Model model
    ){
        if (error != null) {
            model.addAttribute("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
        }
        if (logout != null) {
            model.addAttribute("msg", "你已经成功退出");
        }
        return "admin/login";
    }

    /**
     * 登录
     * @return
     */
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String login(@RequestParam Map<String,Object> map, HttpServletRequest request){
        if (map.get("error")!=null){
            map.put("msg","密码错误");
            return "admin/login";
        }
        //获取登录ip
        String remoteAddr = request.getRemoteAddr();
        String location = IpUtils.getLocation(remoteAddr);
        map.put("ip","IP : " + remoteAddr + location);
        String username = (String) map.get("username");

        Admin admin = adminService.findByName(username);
        admin.setIp("IP : " + remoteAddr + location);
        admin.setLogin_time(new Date());
        map.put("admin",admin);

        return "admin/index";
    }


    //自定义错误类型
    private String getErrorMessage(HttpServletRequest request, String key){
        Exception exception =
                (Exception) request.getSession().getAttribute(key);
        String error;
        if (exception instanceof BadCredentialsException) {
            error = "不正确的用户名或密码";
        }else if(exception instanceof LockedException) {
            error = exception.getMessage();
        }else{
            error = "不正确的用户名或密码";
        }
        return error;
    }

    /**
     * 到后台首页
     * @return
     */
    @RequestMapping(value = "systemIndex")
    public String systemIndex(){

        return "admin/index";
    }

    ///////////////////frame部分////////////////
    @RequestMapping(value = "left")
    public String left(){
        return "admin/frame/left";
    }

}
