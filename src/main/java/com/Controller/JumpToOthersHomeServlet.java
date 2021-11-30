package com.Controller;

import com.Service.UserService;
import com.Util.CONSTANTS;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class JumpToOthersHomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String showName = request.getParameter(CONSTANTS.SHOW_NAME);
        if(new UserService().haveUser(showName)){
            HttpSession session = request.getSession();
            session.setAttribute(CONSTANTS.SHOW_NAME, showName);
            session.setAttribute(CONSTANTS.ARTICLES_DATA.MANAGE_ARTICLES, false);
            response.getWriter().write(CONSTANTS.SUCCESS);
        } else{
            response.getWriter().write(CONSTANTS.FAIL);
        }
    }
}
