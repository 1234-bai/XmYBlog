package com.Filter;


import com.Util.CONSTANTS;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String username = (String)((HttpServletRequest)servletRequest).getSession().getAttribute(CONSTANTS.LOGIN_DATA.USERNAME);
        if(username != null){
            ((HttpServletResponse)servletResponse).sendRedirect("../Home.jsp");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
