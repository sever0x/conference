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

    <form action="/eventRegistration" method="post">

        <div>
            <p>Event name</p>
            <input name="name" type="text" required>

            <div>
                <p>Event Describe</p>
                <textarea name="descr" cols="50" rows="10" required></textarea>
            </div>

            <p>Topic</p>
            <div class="topic-container">

                <div id="topic-main">
                    <input name="topic" type="text" required>
                </div>


                <p>Add new topic</p>
                <button type="button" id="topic-btn" class="topic-btn-add">+</button>
            </div>

            <div>
                <button type="submit" class="registerbtn">Submit</button>
            </div>
        </div>
        <form>
            <input type = "button" value = "Back" onclick="history.back()">
        </form>
    </form>

</div>
<script src="../js/topic.js"></script>
</body>

</html>