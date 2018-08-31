package com.baizhi.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Album implements Serializable {
    private Integer id;
    private String title;
    private Integer count;
    private String corverImg;
    private Double score;
    private String author;
    private String broadCast;
    private String brife;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date publicDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;
    private String status;
    private List<Chapter> children;

    public Album() {
    }

    public Album(Integer id, String title, Integer count, String corverImg, Double score, String author, String broadCast, String brife, Date publicDate, Date createDate, String status, List<Chapter> children) {

        this.id = id;
        this.title = title;
        this.count = count;
        this.corverImg = corverImg;
        this.score = score;
        this.author = author;
        this.broadCast = broadCast;
        this.brife = brife;
        this.publicDate = publicDate;
        this.createDate = createDate;
        this.status = status;
        this.children = children;
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getCorverImg() {
        return corverImg;
    }

    public void setCorverImg(String corverImg) {
        this.corverImg = corverImg;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBroadCast() {
        return broadCast;
    }

    public void setBroadCast(String broadCast) {
        this.broadCast = broadCast;
    }

    public String getBrife() {
        return brife;
    }

    public void setBrife(String brife) {
        this.brife = brife;
    }

    public Date getPublicDate() {
        return publicDate;
    }

    public void setPublicDate(Date publicDate) {
        this.publicDate = publicDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Chapter> getChildren() {
        return children;
    }

    public void setChildren(List<Chapter> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", count=" + count +
                ", corverImg='" + corverImg + '\'' +
                ", score=" + score +
                ", author='" + author + '\'' +
                ", broadCast='" + broadCast + '\'' +
                ", brife='" + brife + '\'' +
                ", publicDate=" + publicDate +
                ", createDate=" + createDate +
                ", status='" + status + '\'' +
                ", children=" + children +
                '}';
    }
}
