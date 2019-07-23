package com.epam.petersburg.ncr41.servlet;

import com.epam.petersburg.ncr41.model.Transaction;
import com.epam.petersburg.ncr41.model.User;
import com.epam.petersburg.ncr41.service.PaymentHistoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/paymentsHistory")
public class PaymentsHistoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User currentUser = (User)session.getAttribute("user");
        PaymentHistoryService paymentHistoryService = new PaymentHistoryService(currentUser);
        List<Transaction> transactionList = paymentHistoryService.getUsersTransactions();
        req.setAttribute("transactions", transactionList);
        req.getRequestDispatcher("paymentsHistory.jsp").forward(req, resp);
    }
}
