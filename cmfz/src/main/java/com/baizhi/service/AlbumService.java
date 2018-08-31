package com.baizhi.service;

import com.baizhi.entity.Album;

import java.util.List;

public interface AlbumService {
    List<Album> queryAll();

    Album queryById(Integer id);

    void insert(Album album);
}
