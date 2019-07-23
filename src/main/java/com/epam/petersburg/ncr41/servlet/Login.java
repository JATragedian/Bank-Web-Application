package com.epam.petersburg.ncr41.servlet;

import com.epam.petersburg.ncr41.service.LoginService;
import com.epam.petersburg.ncr41.dao.impl.RoleDaoImpl;
import com.epam.petersburg.ncr41.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Logger logger = LogManager.getLogger(Login.class);

        String login = request.getParameter("e-mail");
        String password = request.getParameter("password");
        User user = new LoginService().verification(login, password);

        PrintWriter pr = response.getWriter();
        String message;

        if (user == null) {
            message = "Wrong e-mail or password. Try again.";
            pr.println("<html><body>");
            pr.println("<script type=\"text/javascript\">");
            pr.println("alert('" + message + "')");
            pr.println("window.location.href='login'");
            pr.println("</script>");
            pr.println("</body></html>");
            logger.debug("Attempt to login failed");

        } else {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setMaxInactiveInterval(5 * 60);
            String role = new RoleDaoImpl().findOne(user.getRole()).getRole();
            request.setAttribute("user", user);
            if (role.equals("admin")) {
                response.sendRedirect(request.getContextPath() + "/admin");
                logger.debug("Admin login successful");
            } else if (role.equals("user")) {
                response.sendRedirect(request.getContextPath() + "/main");
                logger.debug("User login successful");
            }
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login.jsp");
        requestDispatcher.forward(request, response);

    }
}
