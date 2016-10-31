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
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-theme.min.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/adaptive.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/cms.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/itempage.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/table.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/table.css" />">

    <title>${title}</title>
    <%
        String logoutButt = "<li><a href=\"/\">Login</a></li>";
        String links ="";
        String userName = " ";
        String disabledField = "";
    %>
    <%
        links =
//                "<li><a href=\"/main\">Main Page</a></li>\n" +
                "<li><a href=\"/orders\">Orders</a></li>" +
                "<li><a href=\"/skills\">skills</a></li>\n" +
                "<li><a href=\"/roles\">roles</a></li>\n" +
                "<li><a href=\"/users\">users</a></li>\n" +
                "<li><a href=\"/workers\">workers</a></li>" + logoutButt;
    %>
</head>
<body>
    <header class="pheader" id="best">
        <div class="container">
            <div class="row">
                <div class="main-header">
                    <a href="/">
                        <div class="logo">
                            СMS
                            <div class="label_logo">Capacity Management System</div>
                        </div>
                    </a>
                    <div class="menu">
                        <nav class="main-menu">
                            <ul>
                                <%=links%>
                            </ul>
                        </nav>
                    </div>
                    <div class="pop-menu popup" id="popup">
                        <ul>
                            <%=links%>
                        </ul>
                    </div>
                    <div  id="trig-menu" class="search trig-menu">
                        <div  style="float: right;" >
                            <div class="glyphicon glyphicon-align-justify"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </header>

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