package com.Service;

import com.Dao.ArticleDao;
import com.Entity.Article;

public class ArticleService implements ArticleServiceIml{

    private final ArticleDao articleDao = new ArticleDao();

    public Article[] readPersonArticles(String username){
        if(username == null) {return null;}
        return articleDao.getArticlesAll(username);
    }

    public String readTheArticle(int articleID){
        try{
            Article article = articleDao.getArticle(articleID);
            return article.getContent();
        }catch (NullPointerException e){
            return null;
        }
    }

    public boolean increaseSupportNums(int articleID){
        long oldSupportNums = articleDao.getArticle(articleID).getSupportNums();
        return articleDao.updateSupportNums(articleID,oldSupportNums + 1) == 1;
    }

    @Override
    public boolean decreaseSupportNums(int articleID) {
        long oldSupportNums = articleDao.getArticle(articleID).getSupportNums();
        if(oldSupportNums <= 0){ return true;}  //为0的话啥也不干
        return articleDao.updateSupportNums(articleID,oldSupportNums - 1) == 1;
    }

    @Override
    public boolean increaseClickNums(int articleID) {
        long oldClickNums = articleDao.getArticle(articleID).getClickNums();
        return articleDao.updateClickNums(articleID,oldClickNums + 1) == 1;
    }

    @Override
    public boolean decreaseClickNums(int articleID) {
        long oldClickNums = articleDao.getArticle(articleID).getClickNums();
        if(oldClickNums <= 0){ return true;}  //为0的话啥也不干
        return articleDao.updateClickNums(articleID,oldClickNums - 1) == 1;
    }

    public boolean saveArticle(String title, String ownerName, String content, long createTime_ms) {
        return (articleDao.addArticle(title, ownerName, content, new java.sql.Date(createTime_ms)) == 1);
    }
}
