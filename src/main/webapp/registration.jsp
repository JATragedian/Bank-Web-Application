<%--
  Created by IntelliJ IDEA.
  User: Maks
  Date: 14.07.2019
  Time: 22:26
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages" />

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet"
    href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <title>Registration</title>
</head>
<body>
<div class="container">
    <form method="post" oninput='password2.setCustomValidity(password2.value != password.value ? "Passwords do not match." : "")'>
        <div class="p-3 mb-2 bg-primary text-white"> <fmt:message key="label.registration_form"/> </div>
        <div class="control-group">
            <label class="control-label" for="inputFirstName"><fmt:message key="label.first_name"/></label>
            <div class="controls">
                <input type="text" id="inputFirstName" required="required" pattern="[A-Za-z]{1,30}" oninput="setCustomValidity('')"
                 oninvalid="setCustomValidity('Please enter on Alphabets ')" placeholder="<fmt:message key="label.first_name"/>" name="First Name">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="inputLastName"><fmt:message key="label.last_name"/></label>
            <div class="controls">
                <input type="text" id="inputLastName"  required="required" pattern="[A-Za-z]{1,30}" oninput="setCustomValidity('')"
                 oninvalid="setCustomValidity('Please enter on Alphabets ')" placeholder="<fmt:message key="label.last_name"/>" name="Last Name">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="inputEmail"><fmt:message key="label.email"/></label>
            <div class="controls">
                <input type="email" id="inputEmail" minlength="3" placeholder="<fmt:message key="label.email"/>" name="e-mail">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="inputPassword"><fmt:message key="label.password"/></label>
            <div class="controls">
                <input type="password" id="inputPassword" minlength="5" placeholder="<fmt:message key="label.password"/>" name="password">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="confirmPassword"><fmt:message key="label.confirm_password"/></label>
            <div class="controls">
                <input type="password" id="confirmPassword" minlength="5" placeholder="<fmt:message key="label.confirm_password"/>" name="password2">
            </div>
        </div>
        <br>


        <div class="m-1  row">
        <button type="submit" class="m-1 btn btn-outline-primary"><fmt:message key="label.registration"/></button>
        <div class="m-1 bg-white text-danger " >
        <%
           if (request.getAttribute("UserExist") != null) {
               out.println("<p>E-mail '" + request.getAttribute("UserExist") + "' already exist!</p>");
               }
        %>
        </div>
        <div class="p-1 m-1 bg-white text-success ">
        <%
           if (request.getAttribute("UserNotExist") != null) {
               out.println("<p>New user '" + request.getAttribute("UserNotExist") + "' has been created! Press 'Back to login page' to login </p>");

               }
         %>
         </div>
         </div>


    </form>
             <div class="p-1 m-1 bg-white text-success ">
                         <button onclick="location.href='login'" class="btn btn-outline-primary"><fmt:message key="label.back_to_login"/></button>
             </div>
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
</html>
