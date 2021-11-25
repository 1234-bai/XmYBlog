package com.Service;

import com.Dao.CommentDao;
import com.Entity.Comment;

public class CommentService {
    private final CommentDao commentDao = new CommentDao();

    public boolean addComment(String owner, long articleID, String content, long createTime_ms){
        return commentDao.addComment(owner, articleID, content, new java.sql.Date(createTime_ms)) == 1;
    }

    public Comment[] getArticleComments(long articleID){
        return commentDao.getComments(articleID);
    }
}
