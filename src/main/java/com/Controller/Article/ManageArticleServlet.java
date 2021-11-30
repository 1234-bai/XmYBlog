package com.Controller.Article;

import com.Util.CONSTANTS;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class ManageArticleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute(CONSTANTS.USER_DATA.USERNAME);
        session.setAttribute(CONSTANTS.SHOW_NAME, username);
        session.setAttribute(CONSTANTS.ARTICLES_DATA.MANAGE_ARTICLES, true);
        response.sendRedirect("Home.jsp");
    }
}
