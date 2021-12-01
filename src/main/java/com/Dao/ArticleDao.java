package com.Dao;

import com.Entity.Article;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class ArticleDao extends BaseDao{

    public int addArticle(
            String title,
            String ownerName,
            String content,
            String summary,
            Date createDate,
            boolean isDraft
    ){
        String sql = "insert into " +
                     "myblog.articles(title, ownerName, content, summary, createDate, lastChangeDate,  isDraft) " +
                     "values(?, ?, ?, ?, ?, ?, ?)";
        Object[] params = {title, ownerName, content, summary, createDate, createDate, isDraft};
        return executeUpdate(getConnection(), sql, params);
    }

    public Article getArticle(int articleID){
        String sql = "select * from myblog.articles where articleID = ?";
        Object[] params = {articleID};
        ResultSet res = executeQuery(getConnection(), sql, params);
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
        ResultSet res = executeQuery(getConnection(), sql, params);
        Vector<Article>resultGroup = new Vector<>();
        try {
            while(res.next()){
                Article art = new Article();
                setArticleProperties(res, art);
                resultGroup.add(art);
            }
            close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        if(resultGroup.isEmpty()) return null;
        return resultGroup.toArray(new Article[0]);
    }

    public int deleteArticle(int articleID){
        String sql = "delete from myblog.articles where articleID = ?";
        Object[] params = {articleID};
        return execute(sql, params);
    }

    public int updateSupportNums(int articleID, long supportNums){
        String sql = "update myblog.articles set supportNums = ? where articleID = ?";
        Object[] params = {supportNums,articleID,};
        return execute(sql, params);
    }
    

    public int updateClickNums(int articleID, long clickNums) {
        String sql = "update myblog.articles set clickNums = ? where articleID = ?";
        Object[] params = {clickNums,articleID};
        return execute(sql, params);
    }

    

    public int updateCommentNums(int articleID, long commentNums) {
        String sql = "update myblog.articles set commentNums = ? where articleID = ?";
        Object[] params = {commentNums,articleID};
        return execute(sql, params);
    }
    

    public int updateContent(int articleID, String content, String summary) {
        String sql = "update myblog.articles set content = ?, summary = ? where articleID = ?";
        Object[] params = {content, summary, articleID};
        return  execute(sql, params);
    }

    public int updateTitle(int articleID, String newTitle) {
        String sql = "update myblog.articles set title = ? where articleID = ?";
        Object[] params = {newTitle,articleID,};
        return execute(sql, params);
    }

    public int updateLastChangeDate(int articleID, java.sql.Date lastChangeDate) {
        String sql = "update myblog.articles set lastChangeDate = ? where articleID = ?";
        Object[] params = {lastChangeDate, articleID};
        return execute(sql, params);
    }

    public int updateArticle(
            int articleID,
            String newTitle,
            String content,
            String summary,
            java.sql.Date lastChangeDate,
            boolean isDraft
    ){
        String sql = "update myblog.articles " +
                    "set title = ?, content = ?, summary = ?, lastChangeDate = ?, isDraft = ? " +
                    "where articleID = ?";
        Object[] params = {newTitle, content, summary, lastChangeDate, isDraft, articleID};
        return execute(sql, params);
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
        art.setSummary(res.getString("summary"));
        art.setDraft(res.getBoolean("isDraft"));
    }

    private int execute(String sql, Object[] params){
        int influencedLines = executeUpdate(getConnection(), sql, params);
        close();
        return influencedLines;
    }
}
