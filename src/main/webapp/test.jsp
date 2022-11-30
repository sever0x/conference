<%@ page import="com.conference.model.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=utf-8" %>
<html>
<body>
<div>
    <h3>Users Information</h3>
</div>
<div>
<%
    ArrayList<User> users = (ArrayList<User>) request.getAttribute("users");

    for (User user : users) {
        response.getWriter().println("Login: " + user.getLogin() + " | Password: " + user.getPassword());
    }
%>
</div>

<a href="register.jsp">Register</a>
</body>
</html>
