package com.baizhi.dao;

import com.baizhi.entity.Banner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BannerDAO {
    List<Banner> queryAll(@Param("index") Integer index, @Param("num") Integer num);

    void insert(Banner banner);

    void delete(Integer id);

    void update(Banner banner);

    Integer count();
}
