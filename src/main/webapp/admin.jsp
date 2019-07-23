<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages" />

<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<title>Admin</title>
</head>
<body>
<div class="container">

        <div class="p-3 mb-2 bg-primary text-white"> <fmt:message key="label.admin"/> </div>
                    <div class="col">
                    <form action="logout">
                        <p class="float-right">
                          <button type="submit" class="btn btn-outline-primary"><fmt:message key="label.logout"/></button>
                        </p>
                    </form>
                    </div>
             <div class="row">
             <div class="col">
                <form method="get" class="form-inline">
                <label class=" p-3 mb-2  control-label" for="inputEmail"><fmt:message key="label.email"/></label>
                    <div class="p-3 mb-2  controls">
                        <input type="text" id="inputEmail" placeholder="<fmt:message key="label.email"/>" name="e-mail">
                    </div>
                         <button type="submit" class=" p-3 mb-2  btn btn-outline-primary"><fmt:message key="label.filter_email"/></button>
                </div>
                </form>
             </div>
        <form method="post" >
        <div class="row">
            <div class="col">
                <table class="table table-hover ">
                              <thead>
                                <tr class= "bg-primary text-white">
                                  <th scope="col"><fmt:message key="label.first_name"/></th>
                                  <th scope="col"><fmt:message key="label.last_name"/></th>
                                  <th scope="col"><fmt:message key="label.e-mail"/></th>
                                  <th scope="col"><fmt:message key="label.account_number"/></th>
                                  <th scope="col"><fmt:message key="label.balance"/></th>
                                  <th scope="col"><fmt:message key="label.blocked"/></th>
                                  <th scope="col">  </th>
                                </tr>
                              </thead>
                              <tbody>
                              <%
                              List<List<String>> list =  (List<List<String>>)request.getAttribute("Users");
                                  for (int i = 0; i < list.size(); i++) { %>
                                  <tr>
                                  <%      List<String> row = list.get(i);
                                          for (int j = 0; j < row.size(); j++) {
                                          %><td><%out.println(row.get(j));%></td>
                                          <% } %>
                                   <td>
                                    <div class="form-check">
                                        <input class="form-check-input position-static" type="checkbox" id="blankCheckbox" name="checkBox" value=<%= row.get(3) %> >
                                    </div>
                                  </tr>
                                  <% } %>
                             </tbody>
                            </table>

               <nav aria-label="Navigation">
                   <ul class="pagination">
                       <c:if test="${currentPage != 1}">
                           <li class="page-item"><a class="page-link"
                               href="admin?recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}">Previous</a>
                           </li>
                       </c:if>

                       <c:if test="${nOfPages != 1}">
                       <c:forEach begin="1" end="${nOfPages}" var="i">
                           <c:choose>
                               <c:when test="${currentPage eq i}">
                                   <li class="page-item active"><a class="page-link">
                                           ${i} <span class="sr-only">(current)</span></a>
                                   </li>
                               </c:when>
                               <c:otherwise>
                                   <li class="page-item"><a class="page-link"
                                       href="admin?recordsPerPage=${recordsPerPage}&currentPage=${i}">${i}</a>
                                   </li>
                               </c:otherwise>
                           </c:choose>
                       </c:forEach>
                       </c:if>

                       <c:if test="${currentPage lt nOfPages}">
                           <li class="page-item"><a class="page-link"
                               href="admin?&recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}">Next</a>
                           </li>
                       </c:if>
                   </ul>
               </nav>

                  <div class="col">

                            <p class="float-right">
                                 <button type="submit" class="btn btn-outline-primary"><fmt:message key="label.unblock_account"/></button>
                            </p>
                        </form>
                  </div>
             </div>

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
