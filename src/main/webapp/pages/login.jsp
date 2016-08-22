<%@include file="head.jsp"%>
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
