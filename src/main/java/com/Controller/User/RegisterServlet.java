package com.Controller.User;

import com.Service.UserService;
import com.Util.CONSTANTS;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter(CONSTANTS.REGISTER_DATA.USERNAME);
        String pwd = req.getParameter(CONSTANTS.REGISTER_DATA.PASSWORD);
//        System.out.println("**"+name+"**"+pwd+"**");
        if(new UserService().Register(name, pwd)){
            resp.getWriter().write(CONSTANTS.REGISTER_DATA.REGISTER_SUCCESS);
        } else{
            resp.getWriter().write(CONSTANTS.REGISTER_DATA.REGISTER_ERROR);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
