package com.epam.petersburg.ncr41.servlet;

import com.epam.petersburg.ncr41.service.AdminService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    private static final String CURRENT_PAGE = "currentPage";
    private AdminService adminService = new AdminService();
    private static final int RECORD_PER_PAGE = 10;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("e-mail");
        int currentPage = 1;
        List<List<String>> user;

        if (req.getParameter(CURRENT_PAGE) != null) {
            currentPage = Integer.valueOf(req.getParameter(CURRENT_PAGE));
        }
        int nOfPages;
        if (email != null && !email.isEmpty()) {
            user = adminService.filterByEmail(email);
            nOfPages = 1;
        } else {
            user = adminService.getUsers(currentPage, RECORD_PER_PAGE);
            nOfPages = adminService.getSize() / RECORD_PER_PAGE;
            if (nOfPages % RECORD_PER_PAGE > 0) {
                nOfPages++;
            }
        }

        req.setAttribute("Users", user);
        req.setAttribute("nOfPages", nOfPages);
        req.setAttribute(CURRENT_PAGE, currentPage);
        req.setAttribute("RECORD_PER_PAGE", RECORD_PER_PAGE);
        req.getRequestDispatcher("/admin.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String[] checkBoxes = req.getParameterValues("checkBox");
        if (checkBoxes != null) {
            for (String s : checkBoxes
            ) {
                adminService.unblockUser(s);
            }
        }
        doGet(req, resp);

    }
}
