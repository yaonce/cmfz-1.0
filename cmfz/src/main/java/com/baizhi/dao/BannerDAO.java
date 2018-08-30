package com.baizhi.dao;

import com.baizhi.entity.Banner;

import java.util.List;

public interface BannerDAO {
    List<Banner> queryAll();

    void insert(Banner banner);

    void delete(Integer id);

    void update(Banner banner);
}
