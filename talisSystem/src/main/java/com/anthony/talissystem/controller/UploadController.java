package com.anthony.talissystem.controller;

import com.anthony.talissystem.pojo.Result;
import com.anthony.talissystem.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@Slf4j
public class UploadController {

    @Autowired
    private AliOSSUtils aliOSSUtils;
//    本地存储
//    @PostMapping("/upload")
//    public Result upload(String username, Integer age, MultipartFile image) throws IOException {
//        log.info("文件上传:{},{},{}",username,age,image);
//        //获取原始文件名
//        String originalFilename=image.getOriginalFilename();
//
//        //构造唯一文件名(防止重复文件名导致文件丢失)
//        //获取文件扩展名
//        int index = originalFilename.lastIndexOf(".");
//        String extname = originalFilename.substring(index);
//        String newFilename = UUID.randomUUID().toString() + extname;
//        log.info("New FileName:{}",newFilename);
//
//        //将文件存储在服务器的磁盘目录中
//        image.transferTo(new File("C:\\Users\\w3113\\Desktop\\temporary\\"+newFilename));
//        return Result.success();
//    }

    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws IOException {
        log.info("Try to TRANSFER file :{}",image.getOriginalFilename());
        //使用OSS工具类进行上传
        String url = aliOSSUtils.upload(image);
        log.info("File TRANSFER completed,url:{}",url);
        //传递url
        return Result.success(url);
    }
}
