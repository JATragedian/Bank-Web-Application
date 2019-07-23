package com.epam.petersburg.ncr41.servlet;

import com.epam.petersburg.ncr41.model.User;
import com.epam.petersburg.ncr41.service.PaymentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/payment")
public class PaymentServlet extends HttpServlet {


    private static final Logger LOGGER = LogManager.getLogger(PaymentService.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String card = request.getParameter("card");
        String amount = request.getParameter("amount");
        String targetAccount = request.getParameter("targetAccount");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        PaymentService paymentService = new PaymentService();
        String result = paymentService.makePayment(card, amount, targetAccount, user);
        LOGGER.debug("Payment attempt. Result" + result);

        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<script type=\"text/javascript\">");
        out.println("alert('" + result + "')");
        out.println("window.location.href='main'");
        out.println("</script>");
        out.println("</body></html>");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/payment.jsp").forward(request, response);

    }

}
