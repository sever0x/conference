<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 15.12.2022
  Time: 10:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<body>
<form action="<%= request.getContextPath() %>/changeEvent" method="post">

        <div>
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" cut value="${event.name}"/><br><br>
            <label for="name">Describe:</label>
            <input type="text" id="describe" name="Describe" cut value="${event.describe}"/><br><br>
            <%--<label for="name">Date:</label>--%>
            <%--<input type="datetime-local" id="date" name="Date" cut value= "${event.date}"/><br><br>--%>
            <label for="name">Place:</label>
            <input type="text" id="place" name="Place" cut value="${event.place}"/><br><br>
            <%--<label for="name">Topic:</label>--%>
            <%--<input type="text" id="topic" name="Topic" value= "${event.topics}"/><br><br>--%>
            <button type="submit" class="registerbtn">Register</button>
        </div>

</form>
</body>
</html>
