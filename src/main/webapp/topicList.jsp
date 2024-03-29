<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=utf-8" %>
<html>
<head>
    <link rel="stylesheet" href="../css/editEvent.css">
    <title>All events</title>
</head>
<body>
<div class="container">

    <table>
        <tr>
            <th>Name</th>
            <th>Speaker</th>
            <th>Action</th>
        </tr>
        <c:forEach items="${topics}" var="topic">
            <tr>
                <td>${topic.name}</td>
                <td>${topic.speakerLogin}</td>
                <td>
                    <form action="/speaker/${eventId}" method="post">
                        <input type="hidden" name="topicId" value="${topic.id}">
                        <c:choose>
                            <c:when test="${topic.speakerLogin==null}">
                                <input type="submit" value="Propose as speaker">
                            </c:when>
                            <c:otherwise>
                                <input type="submit" value="Propose as speaker" disabled>
                            </c:otherwise>
                        </c:choose>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <form>
        <input class="back-btn" type="button" value="Back" onclick="history.back()">
    </form>
</div>
</body>
</html>
