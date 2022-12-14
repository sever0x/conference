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
            <div class="topic-container">

                <div id="topic-main">
                    <input name="topic" type="text">
                </div>


                <p>Add new topic</p>
                <button id="topic-btn" class="topic-btn-add">+</button>
            </div>

            <div>
                <button type="submit" class="registerbtn">Submit</button>
            </div>
        </div>
    </form>

</div>

<script>
    const button = document.getElementById("topic-btn");
    const input_box = document.getElementById("topic-main");

    button.addEventListener(
        "click",
        () => {
            const input = document.createElement("input");
            input.name = "topic";
            input.type = "text";
            addNewTopicField(input);
        },
        false
    );

    function addNewTopicField(input) {
        // console.log(button);
        // console.log(input_box);
        input_box.append(input);
    }


</script>
</body>

</html>