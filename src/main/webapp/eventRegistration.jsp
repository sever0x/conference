<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 12.12.2022
  Time: 10:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../css/style.css">
</head>

<body>
<div class="container">
    <form action="<%= request.getContextPath() %>/eventRegistration" method="post">

        <div>
            <p>Event name</p>
            <input name="name" type="text">

            <div>
                <p>Event Describe</p>
                <textarea name="descr" cols="50" rows="10"></textarea>
            </div>

            <p>Topic</p>
            <input name="topic" type="text">
            <input name="topic" type="text">

            <div>

                <button type="submit" class="registerbtn">Submit</button>
            </div>
        </div>
    </form>

</div>
</body>

</html>