package com.baizhi.service;

import com.baizhi.dao.AlbumDAO;
import com.baizhi.entity.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumDAO albumDAO;

    @Override
    public List<Album> queryAll() {
        return albumDAO.queryAll();
    }

    @Override
    public Album queryById(Integer id) {
        return albumDAO.queryById(id);
    }

    @Override
    public void insert(Album album) {
        albumDAO.insert(album);
    }


}
