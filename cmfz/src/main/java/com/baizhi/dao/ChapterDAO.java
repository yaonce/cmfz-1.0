package com.baizhi.dao;

import com.baizhi.entity.Chapter;

import java.util.List;

public interface ChapterDAO {
    List<Chapter> queryALl();

    void insert(Chapter chapter);
}
