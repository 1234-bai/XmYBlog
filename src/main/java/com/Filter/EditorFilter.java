package com.Filter;

import com.Util.CONSTANTS;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class EditorFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig){

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain){
        HttpSession httpSession = ((HttpServletRequest) servletRequest).getSession();
        String username = (String)httpSession.getAttribute(CONSTANTS.USER_DATA.USERNAME);
        try {
            if(username == null){
                ((HttpServletResponse)servletResponse).sendRedirect("Login.jsp");
            }
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {

    }
}
