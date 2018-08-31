package com.baizhi.entity;

import java.io.Serializable;

public class Chapter implements Serializable {
    private String id;
    private String title;
    private String duration;
    private String size;
    private String audioPath;
    private Integer albumId;

    public Chapter() {
    }

    public Chapter(String id, String title, String duration, String size, String audioPath, Integer albumId) {

        this.id = id;
        this.title = title;
        this.duration = duration;
        this.size = size;
        this.audioPath = audioPath;
        this.albumId = albumId;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getAudioPath() {
        return audioPath;
    }

    public void setAudioPath(String audioPath) {
        this.audioPath = audioPath;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", duration='" + duration + '\'' +
                ", size='" + size + '\'' +
                ", audioPath='" + audioPath + '\'' +
                ", albumId=" + albumId +
                '}';
    }
}
