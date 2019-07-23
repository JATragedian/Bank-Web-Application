package com.epam.petersburg.ncr41.servlet.filters;

import com.epam.petersburg.ncr41.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "jspFilter", urlPatterns = {"*.jsp"})
public class RedirectFromJspFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            if (user.getRole() == 2) {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/main");
                requestDispatcher.forward(request, response);
                return;
            } else if (user.getRole() == 1) {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/admin");
                requestDispatcher.forward(request, response);
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
