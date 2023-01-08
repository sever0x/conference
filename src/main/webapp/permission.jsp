<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=utf-8" %>
<html>
<head>
    <link rel="stylesheet" href="../css/editEvent.css">
    <title>All events</title>
</head>
<body>

<div class="container">
    <span>REQUEST</span>
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Role</th>
            <th>Permission</th>

        </tr>
        <jsp:useBean id="allUsers" scope="request" type="java.util.List"/>
        <c:forEach items="${allUsers}" var="user">
            <c:if test="${user.role=='USER'&& user.permission ==1}">

                <td>${user.id}</td>
                <td>${user.login}</td>
                <td>${user.role}</td>
                <td>${user.permission}</td>

                <td>

                    <form action="/request" method="post">
                        <input type="hidden" name="id" value="${user.id}">
                        <input type="submit" value="Change as a speaker">

                    </form>
                </td>
            </c:if>

            </tr>
        </c:forEach>
    </table>
        <br>
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Role</th>
            <th>Permission</th>

        </tr>
        <c:forEach items="${allUsers}" var="user">
<%--            <c:if test="${user.role=='USER'&& user.permission ==1}">--%>
            <c:if test="${user.role!='MODERATOR'}">
                <td>${user.id}</td>
                <td>${user.login}</td>
                <td>${user.role}</td>
                <td>${user.permission}</td>

                <td>

                    <form action="/deleteUser/${user.id}" method="post">
                        <input type="submit" value="Delete user">
                    </form>
                    <form action="/changeRoleToUser/${user.id}" method="post">
                        <input type="submit" value="Change to user">
                    </form>

                </td>
            </c:if>

            </tr>
        </c:forEach>
    </table>
    <form>
        <input class="back-btn" type="button" value="Back" onclick="history.back()">
    </form>
</div>
</body>
</html>
