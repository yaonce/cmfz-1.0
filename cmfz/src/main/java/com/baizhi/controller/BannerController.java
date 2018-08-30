package com.baizhi.controller;

import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/banner")
public class BannerController {
    @Autowired
    private BannerService bannerService;

    @RequestMapping("/queryAll")
    @ResponseBody
    public Map<String, Object> queryAll(Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<>();
        List<Banner> banners = bannerService.queryAll(page, rows);
        Integer count = bannerService.count();
        System.out.println(count);
        map.put("total", count);
        map.put("rows", banners);
        return map;
    }

    @RequestMapping("/add")
    @ResponseBody
    public void add(Banner banner, MultipartFile img, HttpServletRequest request) {
        //需要上传的文件夹
        String upload = "upload";
        //项目绝对路径
        String realPath = request.getServletContext().getRealPath("/");
        String uploadFilePath = realPath + upload;
        //创建项目
        File file = new File(uploadFilePath);
        //如果没有创建文件夹
        if (!file.exists()) {
            file.mkdir();
        }
        //获取原生的名字
        String originalFilename = img.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        //拆分出后缀
        String extension = FilenameUtils.getExtension(originalFilename);
        String baseName = FilenameUtils.getBaseName(originalFilename);
        //起新的名字
        String newName = baseName + uuid + "." + extension;
        //上传
        try {
            img.transferTo(new File(uploadFilePath, newName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //入库
        banner.setImgPath("/" + upload + "/" + newName);
        banner.setCreateDate(new Date());

        bannerService.insert(banner);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public void delete(Integer id) {
        bannerService.delete(id);
    }

    @RequestMapping("/update")
    @ResponseBody
    public void update(Banner banner) {
        banner.setCreateDate(new Date());
        bannerService.update(banner);
    }
}
