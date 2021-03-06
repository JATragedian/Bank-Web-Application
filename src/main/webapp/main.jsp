<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages" />

<%@ page import="com.epam.petersburg.ncr41.model.Account" %>
<%@ page import="com.epam.petersburg.ncr41.model.Card" %>
<%@ page import="java.util.Map" %>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <title>Main</title>
</head>
<body>
<div class="container">


    <div class="p-3 mb-2 bg-primary text-white"> <fmt:message key="label.main"/></div>
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
            <button onclick="location.href='payment'" class="btn btn-outline-primary"><fmt:message key="label.make_payment"/></button>
        </div>
        <div class="p-3 mb-2 col">
            <button onclick="location.href='block'" class="btn btn-outline-primary"><fmt:message key="label.block_account"/></button>
        </div>
        <div class="p-3 mb-2 float-right">
            <button onclick="location.href='paymentsHistory'" class="btn btn-outline-primary"><fmt:message key="label.payment_history"/></button>
        </div>

    </div>
    <div class="col">
        <form method="get" class="form-inline">
            <label class=" p-3 mb-2  control-label" for="inputAccount"><fmt:message key="label.account"/></label>
            <div class="p-3 mb-2  controls">
                <input type="text" id="inputAccount" placeholder="<fmt:message key="label.account"/>" name="account">
            </div>
            <button type="submit" class=" p-3 mb-2  btn btn-outline-primary"><fmt:message key="label.filter_account"/></button>
        </form>
    </div>



    <div class="row">
        <div class="col">
            <table class="table table-hover">
                <thead>
                <tr class="bg-primary text-white">
                    <th scope="col"><fmt:message key="label.account"/></th>
                    <th scope="col"><fmt:message key="label.card"/></th>
                    <th scope="col"><fmt:message key="label.balance"/></th>
                    <th scope="col"><fmt:message key="label.status"/></th>

                </tr>
                </thead>
                <tbody>
                <%
                    Map<Card, Account> accountMap = (Map<Card, Account>) request.getAttribute("accounts");
                    for (Map.Entry<Card, Account> entry : accountMap.entrySet())
                    {
                %>
                <tr>
                    <td><%= entry.getValue().getAccountID() %></td>
                    <td><%= entry.getKey().getCardNumber() %></td>
                    <td><%= entry.getValue().getBalance() %></td>
                 <% String status;
                 if (entry.getValue().isBlocked()) {
                     status = "Blocked";
                 } else {
                     status = "Active";
                 } %>
                    <td><%= status %></td>
                </tr>
                <%}%>
                </tbody>
            </table>
        </div>
    </div>
        <div class="p-3 mb-2 bg-primary text-white">
            <a href="?sessionLocale=en" style="color:white"> <fmt:message key="label.lang.en"/></a>
            <strong>  -  </strong>
            <a href="?sessionLocale=ru" style="color:white"> <fmt:message key="label.lang.ru"/></a>
        </div>



</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>


</body>
