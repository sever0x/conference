<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=utf-8" %>
<html>
    <head>
        <link rel="stylesheet" href="../css/editEvent.css">
        <title>All events</title>
    </head>
<body>
<div>
    <h3>All events</h3>
</div>
    <div class="container">
        <table>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Describe</th>
                <th>Date</th>
                <th>Place</th>
                <th>Action</th>
            </tr>
            <jsp:useBean id="events" scope="request" type="java.util.List"/>
            <c:forEach items="${events}" var="event">
                <tr>
                    <td>${event.id}</td>
                    <td>${event.name}</td>
                    <td>${event.describe}</td>
                    <td>${event.date}</td>
                    <td>${event.place}</td>
                    <td>
                        <form action="/edit/${event.id}" method="get" >
                            <input type="submit" value="Edit" >
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>