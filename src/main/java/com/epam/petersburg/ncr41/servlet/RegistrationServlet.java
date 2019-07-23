package com.epam.petersburg.ncr41.servlet;

import com.epam.petersburg.ncr41.executors.PasswordHashing;
import com.epam.petersburg.ncr41.model.User;
import com.epam.petersburg.ncr41.service.RegistrationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User(req.getParameter("First Name"), req.getParameter("Last Name"), req.getParameter("e-mail"), PasswordHashing.hashPass(req.getParameter("password").toCharArray()));
        RegistrationService service = new RegistrationService(user);
        boolean creationStatus = service.saveUser();
        if (!creationStatus) {
            req.setAttribute("UserExist", user.getEmail());
            doGet(req, resp);
        } else {
            req.setAttribute("UserNotExist", user.getEmail());
            doGet(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/registration.jsp").forward(req, resp);
    }
}
