<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages" />

<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <title>Login</title>
</head>
<body>
<div class="container">

    <form method="post" class="AVAST_PAM_loginform">
        <div class="p-3 mb-2 bg-primary text-white"> <fmt:message key="label.login"/> </div>
        <div class="control-group">
            <label class="control-label" for="inputEmail"><fmt:message key="label.email"/></label>
            <div class="controls">
                <input type="email" id="inputEmail" placeholder="<fmt:message key="label.email"/>" name="e-mail">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="inputPassword"><fmt:message key="label.password"/></label>
            <div class="controls">
                <input type="password" id="inputPassword" placeholder="<fmt:message key="label.password"/>" name="password">
            </div>
        </div>
        <br>
        <div class="control-group">
            <div class="controls">
                <button type="submit" class="btn btn-outline-primary"><fmt:message key="label.login"/></button>
            </div>
        </div>

    </form>
    <button onclick="location.href='registration'" class="btn btn-outline-primary"><fmt:message key="label.registration"/></button>

        <p></p>
        <div class="p-3 mb-2 bg-primary text-white">
            <a href="?sessionLocale=en" style="color:white"> <fmt:message key="label.lang.en"/></a>
            <strong>  -  </strong>
            <a href="?sessionLocale=ru" style="color:white"> <fmt:message key="label.lang.ru"/></a>
        </div>


</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>


</body>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
