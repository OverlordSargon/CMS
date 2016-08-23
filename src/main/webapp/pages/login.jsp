<%@include file="head.jsp"%>

<%
    //allow access only if session exists
    if(session.getAttribute("user") != null){
        response.sendRedirect("/main");
    }

%>

<style>
    <%@include file="/css/cmsMain.css"%>
</style>
<div class="bodydiv">
    <div>Try to login</div>
    <form action="/login" method="post">
        <input type="login" name = "ulog">
        <input type="password" name="upass">
        <input type="submit">
    </form>
</div>
<%@include file="bottom.jsp"%>
