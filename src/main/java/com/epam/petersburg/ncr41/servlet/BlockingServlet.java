package com.epam.petersburg.ncr41.servlet;

import com.epam.petersburg.ncr41.model.User;
import com.epam.petersburg.ncr41.service.BlockingService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/block")
public class BlockingServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String accountID = request.getParameter("Account");
        String result = BlockingService.INSTANCE.block(accountID,user);

        request.setAttribute("blockingResult", result);
        response.setHeader("Refresh", "5;url=main");
        doGet(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/block.jsp").forward(request, response);

    }

}

