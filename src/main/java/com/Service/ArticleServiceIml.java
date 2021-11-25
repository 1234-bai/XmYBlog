package com.Service;

import com.Entity.Article;

import java.sql.Date;

public interface ArticleServiceIml {

    Article[] readPersonArticles(String username);

    String readTheArticle(int articleID);

    boolean increaseSupportNums(int articleID);

    boolean decreaseSupportNums(int articleID);

    boolean increaseClickNums(int articleID);

    boolean decreaseClickNums(int articleID);

    boolean saveArticle(String title, String ownerName, String content, long createTime_ms);

}
