<%@include file="components/head.jsp"%>

<%
    //allow access only if session exists
    if(session.getAttribute("user") != null){
        response.sendRedirect("/main");
    }

%>
<div class="form-view">
    <form action="/login" method="post">
        <div>
            <label for="">Login</label>
            <input type="login" name = "ulog">
        </div>
        <div>
            <label for="">Password</label>
            <input type="password" name="upass">
        </div>
        <input type="submit" class="btn create">
    </form>
</div>
<%@include file="components/bottom.jsp"%>
