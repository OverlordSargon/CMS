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

    <title>${title}</title>
    <%
        String sucMessage = "";
        String errMessage = "";
        String infoMessage = "";
        String logoutButt ;
        String userName = " ";
        if(session.getAttribute("user") != null) {
            logoutButt = "<a>\n" +
                    "<form action=\"/logout\" method=\"post\">" +
                    "<input type=\"submit\" value=\"Logout\" ></form>" +
                    "</a>";
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("user")) {
                        userName = cookie.getValue();
                    }
                    switch (cookie.getName()) {
                        case "sucMessage":
                            sucMessage = cookie.getValue();
                            break;
                        case "errMessage":
                            errMessage = cookie.getValue();
                            break;
                        case "infoMessage":
                            infoMessage = cookie.getValue();
                    }
                }
            }
        } else {
            logoutButt ="<a href=\"/login\">\n" +
                    "Login\n" +
                    "</a>\n";
        }
    %>
</head>
<body>
    <header>
        <div class = "logoleft"></div>
        <div class="child"></div>
        <div class = "header-name">
            Content Management System <%=userName%>
        </div>
        <div class = "logoright"></div>
    </header>



    <nav>
        <a href="/main">Main Page</a>
        <a href="/orders">Orders</a>
        <a href="/skills">skills</a>
        <a href="/roles">roles</a>
        <a href="/users">users</a>
        <a href="/workers">workers</a>
        <%=logoutButt%>
    </nav>

    <div class="messages">
        <c:choose>
            <c:when test="${errMessage != null}">
                <div class="error_message">
                    <c:out value="${errMessage}"></c:out>
                </div>
            </c:when>
            <c:when test="${sucMessage != null}">
                <div class="success_message">
                    <c:out value="${sucMessage}"></c:out>
                </div>
            </c:when>
            <c:when test="${infoMessage != null}">
                <div class="info_message">
                    <c:out value="${infoMessage}"></c:out>
                </div>
            </c:when>
        </c:choose>
    </div>
    <article class="change_block">