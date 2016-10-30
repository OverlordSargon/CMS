<%@include file="../components/head.jsp"%>
<div class="form-view">
    <form action="${action}" method="post">
        <div class="input_div">
            <label for="">User name</label>
            <input type="text" name="username" value="${user.login}" ${disabled}>
        </div>
        <div class="input_div">
            <label for="">User password</label>
            <input type="text" name="password" value="${user.password}" ${disabled}>
        </div>
    </form>
    <div>
        Executing the following roles:
        <c:forEach items="${user.roles}" var="role">
            <br><c:out value="${role.role}"></c:out>
        </c:forEach>
    </div>
    <%-- if we view, print additional buttons--%>
    <div class="buttons-ud">
        <div class="btn-update">
            <form action="/update_user" method="get">
                <input type="hidden"  name="id" value="${user.id}">
                <input type="submit" value="UPDATE" class="btn renew">
            </form>
        </div>
        <div class="btn-delete">
            <form action="/delete_user" method="post">
                <input type="hidden"  name="id" value="${user.id}">
                <input type="submit" value="DELETE" class="btn delete">
            </form>
        </div>
    </div>
    <div>
        <button type="button" name="back" onclick="history.back()">BACK</button>
        <button><a href="/users">Users</a></button>
    </div>
</div>
<%@include file="../components/bottom.jsp"%>
