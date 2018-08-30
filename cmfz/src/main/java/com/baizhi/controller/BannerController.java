package com.baizhi.controller;

import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/banner")
public class BannerController {
    @Autowired
    private BannerService bannerService;

    @RequestMapping("/queryAll")
    @ResponseBody
    public List<Banner> queryAll() {
        return bannerService.queryAll();
    }

    @RequestMapping("/add")
    @ResponseBody
    public void add(Banner banner) {
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
