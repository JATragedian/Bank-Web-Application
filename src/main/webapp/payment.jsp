<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages" />

<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <title>Payment</title>
</head>
<body>
<div class="container">

    <div class="p-3 mb-2 bg-primary text-white"> <fmt:message key="label.payment"/></div>

    <div class="row">

    </div>

    <div class="row">
        <div class="col">
            <p class="float-right">
                    <form action="logout">
                        <p class="float-right">
                          <button type="submit" class="btn btn-outline-primary"><fmt:message key="label.logout"/></button>
                        </p>
                    </form>
            </p>
        </div>
    </div>

    <div class="row">
        <div class="p-3 mb-2 col">
            <button onclick="location.href='main'" class="btn btn-outline-primary"><fmt:message key="label.homepage"/></button>
        </div>

        <div class="p-3 mb-2 col">
            <button onclick="location.href='block'" class="btn btn-outline-primary center-block"><fmt:message key="label.block_account"/>
            </button>
        </div>

        <div class="p-3 mb-2  float-right">
            <button onclick="location.href='paymentsHistory'" class="btn btn-outline-primary"><fmt:message key="label.payment_history"/>
            </button>
        </div>
    </div>

    <div class="mb-2 row ">
        <div class="  col ">

            <form  method="post">
                <div class="col form-inline table-primary">
                    <div class="p-4 mb-2 dropdown">
                        <input type="text" id="inputCard" placeholder="<fmt:message key="label.card"/>" name="card">
                    </div>

                    <div class="p-4 mb-2 controls">
                        <input type="text" id="inputAmount" placeholder="<fmt:message key="label.amount"/>" name="amount">
                    </div>

                    <div class="p-4 mb-2 controls">
                        <input type="text" id="inputAccount" placeholder="<fmt:message key="label.target_account"/>" name="targetAccount">
                    </div>


                    <button type="submit" class="btn btn-primary btn-lg"><fmt:message key="label.pay"/></button>

                </div>
            </form>
        </div>
    </div>
        <p></p>
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
