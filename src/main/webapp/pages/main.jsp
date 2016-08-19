<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Capacity management system </title>
        <style>
            <%@include file="cmsMain.css"%>
        </style>
    </head>
    <body>
        <div class="bodydiv">
            <div class="header">Hello ${name}</div>
            <div class="info">
                <div>U`ve entered ${param}</div>
                <div>Date: ${date}</div>
                <div class="formdiv">
                    <form action="/order">
                        <input type="text" name="ordernum">
                        <button> Кнопка
                            <input type="submit">
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>