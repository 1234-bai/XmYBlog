package com.Dao;

import com.Entity.Comment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommentDao {

    /**
     * 得到具体参数的全部评论
     * @param sql   SQL语句
     * @param params 参数数组
     * @return 返回得到的评论集合
     */
    private Comment[] getComments(String sql, Object[] params){
        ResultSet res = BaseDao.executeQuery(BaseDao.getConnection(), sql, params);
        ArrayList<Comment> comments = new ArrayList<>();
        try {
            while (res.next()){
                Comment comment = new Comment();
                comment.setCommentId(res.getInt("commentID"));
                comment.setOwner(res.getString("owner"));
                comment.setTargetArticleId(res.getInt("targetArticleID"));
                comment.setContent(res.getString("content"));
                comment.setCreateDate(res.getDate("createDate"));
                comments.add(comment);
            }
            BaseDao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments.toArray(new Comment[0]);
    }

    /**
     * 得到某个人的全部评论
     * @param owner 评论的发布者姓名
     * @return 此发布者的全部评论集合
     */
    public Comment[] getComments(String owner){
        String sql = "select * from myblog.comments where owner = ?";
        Object[] params = {owner};
        return getComments(sql, params);
    }

    /**
     * 得到某个文章的全部评论
     * @param articleID 文章的标识
     * @return 此文章对应的全部评论
     */
    public Comment[] getComments(long articleID){
        String sql = "select * from myblog.comments where targetArticleID = ?";
        Object[] params = {articleID};
        return getComments(sql, params);
    }

    /**
     * 添加一条评论
     * @param owner 发布者姓名
     * @param articleID 评论的文章id
     * @param content   文章内容
     * @param createDate   评论的发布日期
     * @return
     */
    public int addComment(String owner, long articleID, String content, java.sql.Date createDate) {
        String sql = "insert into " +
                "myblog.comments(owner, targetArticleID, content, createDate) " +
                "values (?, ?, ?, ?)";
        Object[] params = {owner, articleID, content, createDate};
        int influencedLines = BaseDao.executeUpdate(BaseDao.getConnection(), sql, params);
        BaseDao.close();
        return influencedLines;
    }
}
