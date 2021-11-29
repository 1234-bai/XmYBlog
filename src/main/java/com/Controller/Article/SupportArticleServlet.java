package com.Controller.Article;

import com.Service.ArticleService;
import com.Util.CONSTANTS;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class SupportArticleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        String articleID = request.getParameter(CONSTANTS.ARTICLES_DATA.ARTICLE_ID);
        String numChangeType = request.getParameter(CONSTANTS.ARTICLES_DATA.NUM_CHANGE_TYPE);
        boolean success;
        if(numChangeType.equals(CONSTANTS.ARTICLES_DATA.INCREASE)){
            success = new ArticleService().increaseSupportNums(Integer.parseInt(articleID));
        } else {
            success = new ArticleService().decreaseSupportNums(Integer.parseInt(articleID));
        }
        try {
            if(success){
                response.getWriter().write(CONSTANTS.SUCCESS);
            } else{
                response.getWriter().write(CONSTANTS.FAIL);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        doGet(request, response);
    }
}
