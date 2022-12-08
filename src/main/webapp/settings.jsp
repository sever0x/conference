<%--
  Created by IntelliJ IDEA.
  User: Евгений
  Date: 08.12.2022
  Time: 22:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>The input element</h1>

<form action="<%= request.getContextPath() %>/settings" method="post">
    <label for="login">Login:</label>
    <input type="text" id="login" name="login" value="${user.login}"><br><br>
    <label for="email">Email :</label>
    <input type="email" id="email" name="email" value="${user.email}"><br><br>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password"><br><br>
    <input type="submit" value="Submit">
</form>
</body>
</body>
</html>
