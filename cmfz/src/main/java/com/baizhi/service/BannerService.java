package com.baizhi.service;

import com.baizhi.entity.Banner;

import java.util.List;

public interface BannerService {
    List<Banner> queryAll();

    void insert(Banner banner);

    void delete(Integer id);

    void update(Banner banner);
}
