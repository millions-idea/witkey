package com.wanhao.profront.controller;

import com.google.gson.JsonObject;
import com.wanhao.profront.utils.Constants;
import com.wanhao.profront.utils.ResponseUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

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
     */
    @RequestMapping(value = "fileUpload", method = RequestMethod.POST)
    @ResponseBody
    public void fileUpload(MultipartFile file, HttpServletResponse response) {
        JsonObject errObj = new JsonObject();
        errObj.addProperty("error", "1");


        //文件不存在
        if (file == null || file.isEmpty()) {
            ResponseUtils.renderJson(response, errObj.toString());
            return;
        }

        //动态生成图片名称 名称=日期毫秒+3位随机数字
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String format = dateFormat.format(new Date());

        //后缀名
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());

        //生成随机数
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            format += random.nextInt(10);
        }
        try {
            //获取跟目录
            File abspath = new File(ResourceUtils.getURL("classpath:").getPath());
            if (!abspath.exists())
                abspath = new File("");

            //如果上传目录为/static/images/upload/，则可以如下获取：
            File upload = new File(abspath.getAbsolutePath(), "static/images/upload/");
            if (!upload.exists())
                upload.mkdirs();

            //保存的路径
            String path = upload.getAbsoluteFile().getAbsolutePath();

            //最后的文件名
            String finalFileName = format + "." + extension;

            File dest = new File(path + "/" + finalFileName);
            if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
                dest.getParentFile().mkdir();
            }


            //保存文件
            file.transferTo(dest);

            //前台访问的地址
            String url = Constants.IMAGE_PATH +  finalFileName;

            //返回保存文件的地址
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("url", url);

            jsonObject.addProperty("path", finalFileName);
            //正常
            jsonObject.addProperty("error", "0");
            ResponseUtils.renderJson(response, jsonObject.toString());

        } catch (IllegalStateException e) {
            ResponseUtils.renderJson(response, errObj.toString());
        } catch (IOException e) {
            ResponseUtils.renderJson(response, errObj.toString());
        }
    }
}
