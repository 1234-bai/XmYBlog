package com.Controller;

import com.Service.LoginService;
import com.Util.CONSTANTS;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        //数据提交，保证数据为合法输入
        String username = req.getParameter(CONSTANTS.LOGIN_DATA.USERNAME);
        String password =  req.getParameter(CONSTANTS.LOGIN_DATA.PASSWORD);
//        System.out.println("**Login" + username + "**" + password + "**");
        HttpSession httpSession = req.getSession();
        try {
            int userID = new LoginService().Login(username, password);
            httpSession.setAttribute(CONSTANTS.LOGIN_DATA.USERID, userID);
            if(userID >= 0){
                httpSession.setAttribute(CONSTANTS.LOGIN_DATA.USERNAME, username);
                resp.getWriter().write(CONSTANTS.LOGIN_DATA.LOGIN_SUCCESS);
            } else{
                resp.getWriter().write(CONSTANTS.LOGIN_DATA.LOGIN_FAIL);
            }
        }  catch (IOException e){
            e.printStackTrace();
            System.out.println("LoginServlet,doGet:无法重定向！");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        doGet(req, resp);
    }
}
