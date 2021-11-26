package com.Entity;

import java.sql.Date;

public class Article {

  private long articleId;
  private String ownerName;
  private String title;
  private java.sql.Date createDate;
  private long supportNums;
  private long clickNums;
  private java.sql.Date lastChangeDate;
  private long commentNums;
  private String content;

  public Article() {
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public String getOwnerName() {
    return ownerName;
  }

  public void setOwnerName(String ownerName) {
    this.ownerName = ownerName;
  }


  public long getArticleId() {
    return articleId;
  }

  public void setArticleId(long articleId) {
    this.articleId = articleId;
  }


  public java.sql.Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(java.sql.Date createDate) {
    this.createDate = createDate;
  }


  public long getSupportNums() {
    return supportNums;
  }

  public void setSupportNums(long supportNums) {
    this.supportNums = supportNums;
  }


  public long getClickNums() {
    return clickNums;
  }

  public void setClickNums(long clickNums) {
    this.clickNums = clickNums;
  }


  public java.sql.Date getLastChangeDate() {
    return lastChangeDate;
  }

  public void setLastChangeDate(java.sql.Date lastChangeDate) {
    this.lastChangeDate = lastChangeDate;
  }


  public long getCommentNums() {
    return commentNums;
  }

  public void setCommentNums(long commentNums) {
    this.commentNums = commentNums;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

}
