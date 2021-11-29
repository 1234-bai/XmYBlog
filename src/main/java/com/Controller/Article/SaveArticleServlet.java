package com.Controller.Article;

import com.Service.ArticleService;
import com.Util.CONSTANTS;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SaveArticleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        String title = req.getParameter(CONSTANTS.ARTICLES_DATA.TITLE);
        String content = req.getParameter(CONSTANTS.ARTICLES_DATA.CONTENT);
        String username = (String)req.getSession().getAttribute(CONSTANTS.USER_DATA.USERNAME);
        String summary = req.getParameter(CONSTANTS.ARTICLES_DATA.SUMMARY);
        if(summary != null){summary = CONSTANTS.getHtmlSummary(summary);}
        long createTime_ms = Long.parseLong(req.getParameter(CONSTANTS.ARTICLES_DATA.CREATE_DATE_MS));
        boolean isDraft = Boolean.parseBoolean(req.getParameter(CONSTANTS.ARTICLES_DATA.IS_DRAFT));
        try{
            if(new ArticleService().saveArticle(title, username, content, summary, createTime_ms, isDraft)){
                resp.getWriter().write(CONSTANTS.SUCCESS);
            } else {
                resp.getWriter().write(CONSTANTS.FAIL);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doGet(req, resp);
    }
}
