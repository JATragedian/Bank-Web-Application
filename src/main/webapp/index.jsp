<html>
<body>
<%
    String redirectURL = request.getContextPath() + "/login";
    response.sendRedirect(redirectURL);

%>
</body>
</html>
