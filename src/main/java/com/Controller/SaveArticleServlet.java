package com.Controller;

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
        long createTime_ms = Long.parseLong(req.getParameter(CONSTANTS.ARTICLES_DATA.CREATE_DATE_MS));
        System.out.println("************");
        System.out.println(title);
        System.out.println(content);
        System.out.println(username);
        System.out.println(createTime_ms);
        System.out.println("************");
        System.out.println(content.length());
//        System.out.println(req.getParameter("editor-html-code"));
        try{
            if(new ArticleService().saveArticle(title, username, content, createTime_ms)){
                resp.getWriter().write(CONSTANTS.ARTICLES_DATA.SUCCESS);
            } else {
                resp.getWriter().write(CONSTANTS.ARTICLES_DATA.FAIL);
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
