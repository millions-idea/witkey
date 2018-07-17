package com.wanhao.profront.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by LiuLiHao on 2018/7/17 14:02.
 * 描述： 文件上传
 * 作者： LiuLiHao
 */
@Controller
@RequestMapping(value = "fileup")
public class FileupLoadController {

    private static final String PREFIX = "fileup";

    /**
     * 实现文件上传
     * */
    @RequestMapping(value="fileUpload",method = RequestMethod.POST)
    @ResponseBody
    public String fileUpload(MultipartFile file){

        if(file.isEmpty()){
            return "false";
        }
        String fileName = file.getOriginalFilename();

        String path = System.getProperty("user.dir") + "/uploadFile" ;
        File dest = new File(path + "/" + fileName);
        if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest); //保存文件
            return "true";
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return "false";
        } catch (IOException e) {
            e.printStackTrace();
            return "false";
        }
    }
}
