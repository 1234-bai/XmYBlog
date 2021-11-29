package com.Controller.Article;

import com.Entity.Article;
import com.Service.ArticleService;
import com.Util.CONSTANTS;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.*;
import java.io.IOException;

public class ReadArticleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        boolean otherReading = true;
        String username = (String)request.getSession().getAttribute(CONSTANTS.USER_DATA.USERNAME);
        if(username != null){
            String showName = (String)request.getSession().getAttribute(CONSTANTS.SHOW_NAME);
            if(username.equals(showName)){
                otherReading = false;
            }
        }
        String articleIDStr = request.getParameter(CONSTANTS.ARTICLES_DATA.ARTICLE_ID);
        int articleID = Integer.parseInt(articleIDStr);
        ArticleService articleService = new ArticleService();
        Article article = articleService.getArticleInfo(articleID);
        try {
            if(article == null){
                response.getWriter().write("{\"fail\": true}");
            } else{ //成功获得文章内容。然后增加点击量
                if(otherReading) articleService.increaseClickNums(articleID);
                response.getWriter().write(JSONObject.toJSONString(article));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
