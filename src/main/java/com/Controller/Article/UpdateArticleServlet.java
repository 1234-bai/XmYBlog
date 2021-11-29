package com.Controller.Article;

import com.Service.ArticleService;
import com.Util.CONSTANTS;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class UpdateArticleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter(CONSTANTS.ARTICLES_DATA.TITLE);
        String content = req.getParameter(CONSTANTS.ARTICLES_DATA.CONTENT);
        int articleID = Integer.parseInt(req.getParameter(CONSTANTS.ARTICLES_DATA.ARTICLE_ID));
        String summary = req.getParameter(CONSTANTS.ARTICLES_DATA.SUMMARY);
        if(summary != null){summary = CONSTANTS.getHtmlSummary(summary);}
        long lastChangeTime_ms = Long.parseLong(req.getParameter(CONSTANTS.ARTICLES_DATA.CREATE_DATE_MS));
        boolean isDraft = Boolean.parseBoolean(req.getParameter(CONSTANTS.ARTICLES_DATA.IS_DRAFT));
        try{
            if(new ArticleService().updateArticle(articleID, title, content, summary, lastChangeTime_ms, isDraft)){
                resp.getWriter().write(CONSTANTS.SUCCESS);
            } else {
                resp.getWriter().write(CONSTANTS.FAIL);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
