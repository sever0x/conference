<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=utf-8" %>
<html>
<body>
<c:if test="${user != null}">
    <h1>Hello, <c:out value="${user.login}"/></h1>

    <p><b>Login: </b> ${user.login}</p>
    <p><b>Email: </b> ${user.email}</p>
    <p><b>Password: </b> ${user.password}</p>
</c:if>

<div>
    <h3>Users Information</h3>
</div>

<table border="1">
    <tr>
        <th>Login</th>
        <th>Password</th>
    </tr>
    <jsp:useBean id="users" scope="request" type="java.util.List"/>
    <c:forEach items="${users}" var="u">
        <tr>
            <td>
                <c:out value="${u.login}"/>
            </td>
            <td>${u.password}</td>
        </tr>
    </c:forEach>
</table>
<br>
<a href="/register">Register</a>
<a href="/login">Login</a>
<a href="/logout">Logout</a>
</body>
</html>
