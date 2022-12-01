<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=utf-8" %>
<html>
<body>
<div>
    <h3>Users Information</h3>
</div>

<table border="1">
    <tr>
        <th>Login</th>
        <th>Password</th>
    </tr>
    <jsp:useBean id="users" scope="request" type="java.util.List"/>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>
                <c:out value="${user.login}"/>
            </td>
            <td>${user.password}</td>
        </tr>
    </c:forEach>
</table>
<br>
<a href="/register">Register</a>
</body>
</html>
