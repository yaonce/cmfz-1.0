package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    @RequestMapping("/show")
    public List<Album> show() {
        return albumService.queryAll();
    }

    @RequestMapping("/queryAll")
    public Map<String, Object> queryAll() {
        Map<String, Object> map = new HashMap<>();
        List<Album> albums = albumService.queryAll();
        map.put("total", 3);
        map.put("rows", albums);
        return map;
    }

    @RequestMapping("/queryById")
    public Album queryById(Integer id) {
        return albumService.queryById(id);
    }

    @RequestMapping("/add")
    public void add(Album album, MultipartFile imgPath, HttpServletRequest request) {
        //需要上传的文件夹
        String upload = "upload";
        //项目绝对路径
        String realPath = request.getServletContext().getRealPath("/");
        String uploadRealPath = realPath + upload;
        //创建项目
        File file = new File(uploadRealPath);
        //如果没有创建文件夹
        if (!file.exists()) file.mkdir();
        //获取原生的名字
        String originalFilename = imgPath.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        //拆分文件名字
        String baseName = FilenameUtils.getBaseName(originalFilename);
        String extension = FilenameUtils.getExtension(originalFilename);
        //起新的名字
        String newName = baseName + "_" + uuid + "." + extension;
        //上传
        try {
            imgPath.transferTo(new File(uploadRealPath, newName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //入库
        album.setCorverImg(newName);
        album.setCreateDate(new Date());
        albumService.insert(album);
    }
}
