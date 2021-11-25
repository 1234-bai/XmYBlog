package com.Entity;


public class Comment {

  private long commentId;
  private String owner;
  private long targetArticleId;
  private String content;
  private java.sql.Date crateDate;


  public long getCommentId() {
    return commentId;
  }

  public void setCommentId(long commentId) {
    this.commentId = commentId;
  }


  public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }


  public long getTargetArticleId() {
    return targetArticleId;
  }

  public void setTargetArticleId(long targetArticleId) {
    this.targetArticleId = targetArticleId;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }


  public java.sql.Date getCrateDate() {
    return crateDate;
  }

  public void setCrateDate(java.sql.Date crateDate) {
    this.crateDate = crateDate;
  }

}
