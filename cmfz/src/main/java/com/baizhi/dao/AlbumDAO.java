package com.baizhi.dao;

import com.baizhi.entity.Album;

import java.util.List;

public interface AlbumDAO {
    List<Album> queryAll();

    Album queryById(Integer id);

    void insert(Album album);
}
