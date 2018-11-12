package com.baizhi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Chapter {
    private String id;
    private String title;
    private Double bulk;
    private Integer duration;
    private String downPath;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date date;
    private Album album;

    @Override
    public String toString() {
        return "Chapter{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", bulk=" + bulk +
                ", duration=" + duration +
                ", downPath='" + downPath + '\'' +
                ", date=" + date +
                ", album=" + album +
                '}';
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

    public Double getBulk() {
        return bulk;
    }

    public void setBulk(Double bulk) {
        this.bulk = bulk;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getDownPath() {
        return downPath;
    }

    public void setDownPath(String downPath) {
        this.downPath = downPath;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Chapter(String id, String title, Double bulk, Integer duration, String downPath, Date date, Album album) {

        this.id = id;
        this.title = title;
        this.bulk = bulk;
        this.duration = duration;
        this.downPath = downPath;
        this.date = date;
        this.album = album;
    }

    public Chapter() {

    }
}
