<%--
  Created by IntelliJ IDEA.
  User: nzaitsev
  Date: 22.08.2016
  Time: 12:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        <%@include file="/css/main.css"%>
    </style>
    <title>${title}</title>
    <%
        String logoutButt ;
        String userName = " ";
        String role = " ";
        if(session.getAttribute("user") != null) {
            logoutButt = "<li>\n" +
                    "<form action=\"/logout\" method=\"post\">" +
                    "<input type=\"submit\" value=\"Logout\" ></form>" +
                    "</li>";
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("user")) {
                        userName = cookie.getValue();
                    }
                    if ( cookie.getName().equals("role")) {
                        role = cookie.getValue();
                    }
                }
            }
        } else {
            logoutButt = "<li>\n" +
                    "<a href=\"/login\">\n" +
                    "<button>Login</button>\n" +
                    "</a>\n" +
                    "</li>";
        }
    %>
</head>
<body>
    <header>
        <div class = "logoleft"></div>
        <div class = "logo">
            <div class="logotext" >Content Management System <%=role%> <%=userName%></div>
        </div>
        <div class = "logoright"></div>
    </header>
    <menu>
        <li>
            <a href="/main">
                <button>Main Page</button>
            </a>
        </li>

        <%=logoutButt%>
    </menu>