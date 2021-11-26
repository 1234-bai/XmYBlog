package com.Controller;

import com.Entity.Comment;
import com.Service.CommentService;
import com.Util.CONSTANTS;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.*;
import java.io.IOException;

public class readCommentsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        long articleID = Long.parseLong(request.getParameter(CONSTANTS.COMMENT_DATA.ARTICLE_ID));
        Comment[] comments = new CommentService().getArticleComments(articleID);
        try {
            if(comments == null){
                response.getWriter().write("{\"empty\":true}");
            } else{ //成功获得文章内容。然后增加点击量
                response.getWriter().write(JSONObject.toJSONString(comments));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
