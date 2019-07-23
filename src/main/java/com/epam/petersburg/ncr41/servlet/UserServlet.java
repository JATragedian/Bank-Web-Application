package com.epam.petersburg.ncr41.servlet;

import com.epam.petersburg.ncr41.model.Account;
import com.epam.petersburg.ncr41.model.Card;
import com.epam.petersburg.ncr41.model.User;
import com.epam.petersburg.ncr41.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebServlet("/main")
public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String number = request.getParameter("account");
        Map<Card, Account> accountMap = new UserService().getCardsWithAccount(user.getUserId(), number);
        request.setAttribute("accounts", accountMap);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/main.jsp");
        requestDispatcher.forward(request, response);
    }
}
