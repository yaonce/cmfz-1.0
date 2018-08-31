package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;
import com.baizhi.service.AlbumService;
import com.baizhi.service.ChapterService;
import com.baizhi.util.Mp3Util;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;

@RestController
@RequestMapping("/chapter")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;
    @Autowired
    private AlbumService albumService;

    @RequestMapping("/add")
    public void add(Chapter chapter, MultipartFile audio, HttpServletRequest request) {
        // 文件大小
        Long size = audio.getSize() / 1024 / 1024;
        String s = size.toString() + "MB";

        // 下载路径
        String realPath = request.getServletContext().getRealPath("/");
        String upload = "upload";
        String uploadRealPath = realPath + upload;
        // 创建文件夹
        File file = new File(uploadRealPath);
        if (!file.exists()) file.mkdir();
        // 文件新名字
        String originalFilename = audio.getOriginalFilename();
        String baseName = FilenameUtils.getBaseName(originalFilename);
        String extension = FilenameUtils.getExtension(originalFilename);
        String newName = baseName + "_" + UUID.randomUUID().toString() + "." + extension;
        //上传
        try {
            audio.transferTo(new File(uploadRealPath, newName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //文件时长
        File file1 = new File(uploadRealPath + "/" + newName);
        String duration = Mp3Util.getDuration(file1);
        //入库
        chapter.setAudioPath("/" + upload + "/" + newName);
        chapter.setSize(s);
        chapter.setDuration(duration);
        chapterService.insert(chapter);
        //查询对应的album
        Album album = albumService.queryById(chapter.getAlbumId());
        //修改album的count值
    }

    @RequestMapping("/down")
    public void down(String title, String audioPath, HttpServletResponse response, HttpServletRequest request) {
        //获得文件路径
        String realPath = request.getServletContext().getRealPath("/");
        String filePath = realPath + "/" + audioPath;
        //查找
        File file = new File(filePath);
        // 获取最后一个/后的内容
        String substring = audioPath.substring(audioPath.lastIndexOf("/") + 1);
        // 文件后缀
        String extension = FilenameUtils.getExtension(substring);
        title += title + "." + extension;
        // 设置编码---其中一种
//        String a = null;
//        try {
//            a= new String(title.getBytes("utf-8"),"ISO-8859-1");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
        //设置响应表头
        response.setContentType("audio/mpeg");
        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(title, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            outputStream.write(FileUtils.readFileToByteArray(file));
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
