<%@ page import="java.util.ArrayList" %>
<%@ page import="com.epam.petersburg.ncr41.model.Transaction" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="com.epam.petersburg.ncr41.service.PaymentHistoryService" %>
<%@ page import="com.epam.petersburg.ncr41.model.User" %>
<%@ page import="com.epam.petersburg.ncr41.dao.interfaces.UserDao" %>
<%@ page import="com.epam.petersburg.ncr41.dao.impl.UserDaoImpl" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages" />

<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
    integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
    crossorigin="anonymous">

    <title>Payments History</title>

        <style CSS>
            .my-custom-scrollbar {
            position: relative;
            height: 600px;
            overflow: auto;
        }
            .table-wrapper-scroll-y {
            display: block;
        }
        </style>

  </head>
  <body>
    <div class="container">
        <div class="p-3 mb-2 bg-primary text-white"> <fmt:message key="label.payment_history"/></div>
                <div class="row">
                    <div class="col">
                    <form action="logout">
                        <p class="float-right">
                          <button type="submit" class="btn btn-outline-primary"><fmt:message key="label.logout"/></button>
                        </p>
                    </form>
                    </div>
                </div>


        <div class="row">
                <div class="p-3 mb-2 col">
                    <p class="col">
                      <button onclick="location.href='main'" class="btn btn-outline-primary"><fmt:message key="label.homepage"/></button>
                    </p>

                </div>
                <div class="p-3 mb-2 col">
                    <p class="col">
                      <button onclick="location.href='payment'" class="btn btn-outline-primary" style="justify-content: flex-end;"><fmt:message key="label.make_payment"/></button>
                    </p>
                </div>
                <div class="p-3 mb-2 float-right">
                    <p class="col">
                      <button onclick="location.href='block'" class="btn btn-outline-primary" style="justify-content: flex-end;"><fmt:message key="label.block_account"/></button>
                    </p>
                </div>
        </div>

        <div class="table-wrapper-scroll-y my-custom-scrollbar">
            <div class="col">
                <table class="table table-hover">
                              <thead>
                                <tr class= "bg-primary text-white">
                                  <th scope="col"><fmt:message key="label.date"/></th>
                                  <th scope="col"><fmt:message key="label.card"/></th>
                                  <th scope="col"><fmt:message key="label.amount"/></th>
                                  <th scope="col"><fmt:message key="label.target_account"/></th>
                                </tr>
                              </thead>
                              <tbody>
                              <%

                                  List<Transaction> userTransactionList = (List<Transaction>)request.getAttribute("transactions");
                                  for(Transaction transaction : userTransactionList)
                                  {
                              %>
                              <tr>
                                  <td><%= transaction.getDate() %></td>
                                  <td><%= transaction.getCardNumber() %></td>
                                  <td><%= transaction.getAmount() %></td>
                                  <td><%= transaction.getTargetAccountId() %></td>
                              </tr>
                              <%}%>
                              </tbody>
                </table>
            </div>
                            <div class="p-3 mb-2 bg-primary text-white">
                                <a href="?sessionLocale=en" style="color:white"> <fmt:message key="label.lang.en"/></a>
                                <strong>  -  </strong>
                                <a href="?sessionLocale=ru" style="color:white"> <fmt:message key="label.lang.ru"/></a>
                            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
  </body>
</html>