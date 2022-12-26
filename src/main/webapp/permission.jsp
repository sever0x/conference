<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=utf-8" %>
<html>
<head>
    <link rel="stylesheet" href="../css/editEvent.css">
    <style>
        .container span {
            position: absolute;
            top: 10px;
        }
    </style>
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
        <jsp:useBean id="list2" scope="request" type="java.util.List"/>
        <c:forEach items="${list2}" var="user">
            <c:if test="${user.role=='USER'&& user.permission ==1}">

                <td>${user.id}</td>
                <td>${user.login}</td>
                <td>${user.role}</td>
                <td>${user.permission}</td>

                <td>

                    <form action="/changeRoleToSpeaker/${user.id}" method="get">
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
<%--        <jsp:useBean id="list2" scope="request" type="java.util.List"/>--%>
        <c:forEach items="${list2}" var="user">
<%--            <c:if test="${user.role=='USER'&& user.permission ==1}">--%>
            <c:if test="${user.role!='MODERATOR'}">
                <td>${user.id}</td>
                <td>${user.login}</td>
                <td>${user.role}</td>
                <td>${user.permission}</td>

                <td>

                    <form action="/deleteUser/${user.id}" method="get">
                        <input type="submit" value="Delete user">
                    </form>
                    <form action="/changeRoleToUser/${user.id}" method="get">
                        <input type="submit" value="Change to user">
                    </form>

                </td>
            </c:if>

            </tr>
        </c:forEach>
        <form>
            <input type = "button" value = "Back" onclick="history.back()">
        </form>
    </table>

</div>
</body>
</html>
