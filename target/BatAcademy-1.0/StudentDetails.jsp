<%-- 
    Document   : UserDetailsStudent
    Created on : Aug 2, 2016, 6:13:30 PM
    Author     : geeta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        ${student.getFirstName()}
        
        <%-- <c:forEach var="course" items="${courses}">
             ${course.getCourseName()}
         </c:forEach> --%>
    </body>
</html>
