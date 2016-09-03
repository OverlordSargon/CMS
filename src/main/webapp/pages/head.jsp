<%--
  Created by IntelliJ IDEA.
  User: nzaitsev
  Date: 22.08.2016
  Time: 12:48
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
            <div class="logotext" >Content Management System <%=userName%></div>
        </div>
        <div class = "logoright"></div>
    </header>

    <div class="messages">
        <div class="error_message">
            ${errMessage}
        </div>
        <div class="success_message">
            ${sucMessage}
        </div>
        <div class="info_message">
            ${infoMessage}
        </div>
    </div>

    <menu>
        <li>
            <a href="/main">
                <button>Main Page</button>
            </a>
        </li>
        <li>
            <button>
                <a href="/orders">Orders</a>
            </button>
        </li>
        <li>
            <button>
                <a href="/skills">skills</a>
            </button>
        </li>
        <li>
            <button>
                <a href="/roles">roles</a>
            </button>
        </li>
        <li>
            <button>
                <a href="/users">users</a>
            </button>
        </li>
        <li>
            <button>
                <a href="/workers">workers</a>
            </button>
        </li>

        <%=logoutButt%>
    </menu>
    <aside>
        ${message}
    </aside>