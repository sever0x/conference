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
            <th>Action</th>
        </tr>
        <%--        <jsp:useBean id="event" scope="request" type="java.util.List"/>--%>
        <c:forEach items="${event.topics}" var="topic">
            <tr>
                <td>${topic.name}</td>

                <td>
                    <c:if test="${user.userHasTopic(topic.id) == false && user.getTopicStatus(topic.id)==0}">
                    <form action="/joinToTopic/${topic.id}${0}" method="get">

                        <input type="submit" value="Join">
                    </form>
                    </c:if>
                    <c:if test="${user.userHasTopic(topic.id) == true&& user.getTopicStatus(topic.id)==0}">
                    <form action="/deleteTopicFromUser/${topic.id}${0}" method="get">
                        <input type="submit" value="UnJoin">
                    </form>
                    </c:if>
                <td>
                    <c:if test="${user.userHasTopic(topic.id) == false&& user.getTopicStatus(topic.id)==0}">
                        <form action="/joinToTopic/${topic.id}${1}" method="get">
                            <input type="submit" value="Join As Free Speaker">
                        </form>
                    </c:if>
                    <c:if test="${user.userHasTopic(topic.id) == true&& user.getTopicStatus(topic.id)==0}">
                        <form action="/deleteTopicFromUser/${topic.id}/${1}" method="get">
                            <input type="submit" value="UnJoin As Free Speaker">
                        </form>
                    </c:if>
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
