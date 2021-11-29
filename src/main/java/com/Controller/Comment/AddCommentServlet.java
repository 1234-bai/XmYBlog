package com.Controller.Comment;

import com.Service.ArticleService;
import com.Service.CommentService;
import com.Util.CONSTANTS;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class AddCommentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String owner = (String) session.getAttribute(CONSTANTS.COMMENT_DATA.OWNER);
        String content = request.getParameter(CONSTANTS.COMMENT_DATA.CONTENT);
        long articleID = Long.parseLong(request.getParameter(CONSTANTS.COMMENT_DATA.ARTICLE_ID));
        String createTime_ms = (request.getParameter(CONSTANTS.COMMENT_DATA.CREATE_TIME_MS));
        boolean addComment = new CommentService().addComment(owner, articleID, content, Long.parseLong(createTime_ms));
        if(addComment){
            new ArticleService().increaseCommentNums((int) articleID);
            response.getWriter().write(CONSTANTS.SUCCESS);
        } else{
            response.getWriter().write(CONSTANTS.FAIL);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
