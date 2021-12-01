package com.Controller.Article;

import com.Service.ArticleService;
import com.Util.CONSTANTS;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class DeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int articleID = Integer.parseInt(request.getParameter(CONSTANTS.ARTICLES_DATA.ARTICLE_ID));
        response.getWriter().write(
                new ArticleService().deleteArticle(articleID) ? CONSTANTS.SUCCESS : CONSTANTS.FAIL
        );
    }
}
