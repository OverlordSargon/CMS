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
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>

    <title>${title}</title>
    <%
        String logoutButt ;
        String links ="";
        String userName = " ";
        String disabledField = "";
        if(session.getAttribute("user") != null) {
            userName = session.getAttribute("user").toString();
            logoutButt ="<a href=\"/logout\">\n" +
                    "Logout\n" +
                    "</a>\n";
            Cookie[] cookies = request.getCookies();
        } else {
            logoutButt ="<a href=\"/login\">\n" +
                    "Login\n" +
                    "</a>\n";
        }
    %>
</head>
<body>
        <header>
            <div class = "logoleft1"></div>
            <div class="child"></div>
            <div class = "header-name">
                Capacity Management System
            </div>
            <div class = "logoright1"></div>
        </header>

        <nav>
            <%
                if( session.getAttribute("role")!=null) {
                    if (session.getAttribute("role").equals("Administrator")) {
                        links = "<a href=\"/main\">Main Page</a>\n" +
                                "<a href=\"/orders\">Orders</a>" +
                                "<a href=\"/skills\">skills</a>\n" +
                                "<a href=\"/roles\">roles</a>\n" +
                                "<a href=\"/users\">users</a>\n" +
                                "<a href=\"/admin/workers\">workers</a>" + logoutButt;
                    } else {
                        links = "<a href=\"/main\">Main Page</a>\n" +
                                "<a href=\"/orders\">Orders</a>" + logoutButt;
                        disabledField = "style=display:none";
                    }
                }
            %>
            <%=links%>
        </nav>
        <c:if test="${not empty cmsheader}">
            <div class="article-header">
                <h2 class="header">${cmsheader}</h2>
            </div>
        </c:if>
        <div class="messages">
            <c:choose>
                <c:when test="${errMessage != null}">
                    <div class="error_message">
                        <c:out value="${errMessage}"></c:out>
                    </div>
                </c:when>
            </c:choose>
            <c:choose>
                <c:when test="${sucMessage != null}">
                    <div class="success_message">
                        <c:out value="${sucMessage}"></c:out>
                    </div>
                </c:when>
            </c:choose>
            <c:choose>
            <c:when test="${infoMessage != null}">
                    <div class="info_message">
                        <c:out value="${infoMessage}"></c:out>
                    </div>
                </c:when>
            </c:choose>
        </div>
        <article class="change_block">