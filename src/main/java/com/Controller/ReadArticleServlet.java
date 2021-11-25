package com.Controller;

import com.Service.ArticleService;
import com.Util.CONSTANTS;

import javax.servlet.http.*;
import java.io.IOException;

public class ReadArticleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        String articleIDStr = request.getParameter(CONSTANTS.ARTICLES_DATA.ARTICLE_ID);
        int articleID = Integer.parseInt(articleIDStr);
        ArticleService article = new ArticleService();
        String content = article.readTheArticle(articleID);
        try {
            if(content == null){
                response.getWriter().write(CONSTANTS.ARTICLES_DATA.FAIL);
            } else{ //成功获得文章内容。然后增加点击量
                article.increaseClickNums(articleID);
                response.getWriter().write(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
