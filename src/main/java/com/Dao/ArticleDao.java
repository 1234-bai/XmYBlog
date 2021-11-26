package com.Dao;

import com.Entity.Article;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class ArticleDao {

    public int addArticle(String title, String ownerName, String content, Date createDate){
        String sql = "insert into " +
                     "myblog.articles(title, ownerName, content, createDate, lastChangeDate) " +
                     "values(?, ?, ?, ?, ?)";
        Object[] params = {title, ownerName, content, createDate, createDate};
        return BaseDao.executeUpdate(BaseDao.getConnection(), sql, params);
    }

    public Article getArticle(int articleID){
        String sql = "select * from myblog.articles where articleID = ?";
        Object[] params = {articleID};
        ResultSet res = BaseDao.executeQuery(BaseDao.getConnection(), sql, params);
        Article art = null;
        try {
            if(res.next()){
                art = new Article();
                setArticleProperties(res, art);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return art;
    }

    public Article[] getArticlesAll(String username){
        String sql = "select * from myblog.articles where ownerName = ?";
        Object[] params = {username};
        ResultSet res = BaseDao.executeQuery(BaseDao.getConnection(), sql, params);
        Vector<Article>resultGroup = new Vector<>();
        try {
            while(res.next()){
                Article art = new Article();
                setArticleProperties(res, art);
                resultGroup.add(art);
            }
            BaseDao.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        if(resultGroup.isEmpty()) return null;
        return resultGroup.toArray(new Article[0]);
    }

    public int deleteArticle(int articleID, String title){
        return -1;
    }

    public int updateTitle(int articleID, int userID, String newTitle) {
        return -1;
    }

    public int updateUsername(int articleID, String username) {
        return -1;
    }
    

    public int updateSupportNums(int articleID, long supportNums){
        String sql = "update myblog.articles set supportNums = ? where articleID = ?";
        Object[] params = {supportNums,articleID,};
        int influencedLines = BaseDao.executeUpdate(BaseDao.getConnection(), sql, params);
        BaseDao.close();
        return  influencedLines;
    }
    

    public int updateClickNums(int articleID, long clickNums) {
        String sql = "update myblog.articles set clickNums = ? where articleID = ?";
        Object[] params = {clickNums,articleID};
        int influencedLines = BaseDao.executeUpdate(BaseDao.getConnection(), sql, params);
        BaseDao.close();
        return influencedLines;
    }
    

    public int updateLastChangeDate(int articleID, java.sql.Date lastChangeDate) {
        return -1;
    }
    

    public int updateCommentNums(int articleID, long commentNums) {
        String sql = "update myblog.articles set commentNums = ? where articleID = ?";
        Object[] params = {commentNums,articleID};
        int influencedLines = BaseDao.executeUpdate(BaseDao.getConnection(), sql, params);
        BaseDao.close();
        return influencedLines;
    }
    

    public int updateContent(int articleID, String content) {
        return -1;
    }

    private void setArticleProperties(ResultSet res, Article art) throws SQLException {
        art.setArticleId(res.getInt("articleID"));
        art.setTitle(res.getString("title"));
        art.setContent(res.getString("content"));
        art.setCreateDate(res.getDate("createDate"));
        art.setLastChangeDate(res.getDate("lastChangeDate"));
        art.setClickNums(res.getLong("clickNums"));
        art.setSupportNums(res.getLong("supportNums"));
        art.setCommentNums(res.getLong("commentNums"));
        art.setOwnerName(res.getString("ownerName"));
    }
}
