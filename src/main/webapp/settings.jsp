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
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
  <form action="<%= request.getContextPath() %>/settings" method="post">
            
    <div class="container">
        <h1>The input elements</h1>
    <div>
        <label for="login">Login:</label>
        <input type="text" id="login" name="login" value="${user.login}"><br><br>
        <label for="email">Email :</label>
        <input type="email" id="email" name="email" value="${user.email}"><br><br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password"><br><br>
        <button type="submit" class="registerbtn"value="Submit">Submit</button>
    </div>
</div>
</form>
</body>
</body>
</html>

<!-- <form action="<%= request.getContextPath() %>/settings" method="post">
  <label for="login">Login:</label>
  <input type="text" id="login" name="login" value="${user.login}"><br><br>
  <label for="email">Email :</label>
  <input type="email" id="email" name="email" value="${user.email}"><br><br>
  <label for="password">Password:</label>
  <input type="password" id="password" name="password"><br><br>
  <input type="submit" value="Submit">
</form> -->