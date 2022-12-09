<%--
  Created by IntelliJ IDEA.
  User: y.chernonog
  Date: 01.12.2022
  Time: 14:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page  contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <style>
        .container {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            min-height: 100vh;
        }


        #word {
            font-size: 200%;
            font-family: Arial, Helvetica, sans-serif;
            font-size: 50px;
            background: -webkit-linear-gradient(-45deg, #ee7752, #e73c7e, #23a6d5, #23d5ab);
            -webkit-background-clip: text;
            -webkit-text-fill-color: transparent;
            animation: gradient 15s ease infinite;
        }

    </style>

    <title>Account</title>
</head>

<body>
    <div class="container">
        <!-- <select class="change-lang">
            <option value="ua">UA</option>
            <option value="en">EN</option>
        </select> -->

        <!-- <div class="lang df">
            <button value="en" class="active" id="lang-en">EN</button>
            <button value="ua" id="lang-ua">UA</button>
        </div> -->

        <!-- <button class="change-lang" value="en">EN</button> -->
        <div id="word-en" class="lng-text cssanimation hu__hu__">Oops. It will be a digital paper</div>
        <div id="word-ua" class="lng-text cssanimation hu__hu__" style="display: none;">Ой-йой. Мабудь буде якийсь цифровий папірець</div>
        
    </div>



    <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/1.19.1/TweenMax.min.js"></script> 
    <script type="text/javascript" src="../webapp/html/js/cssanimation-gsap.js"></script>
    <script type="module" src="../webapp/html/js/lang.js"></script>
<script type="module" src="../webapp/html/changeLang.js"></script>
</body>




</html>
