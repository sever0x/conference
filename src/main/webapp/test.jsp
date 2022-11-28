<%@ page import="com.conference.model.User" %>
<%@ page import="java.util.ArrayList" %>
<html>
<body>
<h3>Users Information</h3>
<br>
<%
    ArrayList<User> users = (ArrayList<User>) request.getAttribute("users");

    for (User user : users) {
        response.getWriter().println("Login: " + user.getLogin() + " | Password: " + user.getPassword());
    }
%>
</body>
</html>
