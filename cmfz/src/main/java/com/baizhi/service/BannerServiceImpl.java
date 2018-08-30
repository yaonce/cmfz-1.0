package com.baizhi.service;

import com.baizhi.dao.BannerDAO;
import com.baizhi.entity.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerDAO bannerDAO;

    @Override
    public List<Banner> queryAll() {

        return bannerDAO.queryAll();
    }

    @Override
    public void insert(Banner banner) {
        bannerDAO.insert(banner);
    }

    @Override
    public void delete(Integer id) {
        bannerDAO.delete(id);
    }

    @Override
    public void update(Banner banner) {
        bannerDAO.update(banner);
    }
}
