package com.chihyun.News.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
@Entity
@Table(name = "News")
public class NewsVO {
    @Id
    @Column(name = "newsNo")
    private Integer newsNo;
    @Column(name = "newsTitle")
    private String newsTitle;
    @Column(name = "newsContent")
    private String newsContent;
    @Column(name = "postTime")
    private Date postTime;

    public Integer getNewsNo() {
        return newsNo;
    }

    public void setNewsNo(Integer newsNo) {
        this.newsNo = newsNo;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }
}
