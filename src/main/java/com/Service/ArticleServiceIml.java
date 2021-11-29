package com.Service;

import com.Entity.Article;

import java.sql.Date;

public interface ArticleServiceIml {

    Article[] getPersonArticles(String username);

    String getArticleContent(int articleID);

    boolean increaseSupportNums(int articleID);

    boolean decreaseSupportNums(int articleID);

    boolean increaseClickNums(int articleID);

    boolean decreaseClickNums(int articleID);

    boolean saveArticle(String title, String ownerName, String content, String summary, long createTime_ms, boolean isDraft);

    boolean increaseCommentNums(int articleID);

    boolean decreaseCommentNums(int articleID);

    boolean deleteArticle(int articleID);

    boolean updateArticle(int articleID, String title, String content, String summary, long lastEditTime_ms, boolean isDraft);
}
