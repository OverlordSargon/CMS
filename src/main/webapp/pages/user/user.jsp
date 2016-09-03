<%@include file="../head.jsp"%>

<form action="${action}" method="post">
    <div>
        <label for="">User name</label>
        <input type="text" name="username" value="${user.login}" ${disabled}>
    </div>
    <div>
        <label for="">User password</label>
        <input type="text" name="userdesc" value="${user.password}" ${disabled}>
    </div>

<c:choose>
    <c:when test="${action != \"/user\"}">
        <%--If U or C print the following name--%>
        <div class="roles_list">
            <div class="roles_list_label">
                Choose roles for new user:
            </div>
            <c:forEach items="${roles}" var="role">
                <div>
                    <input type="checkbox" name="roles" value="${role.id}">
                    <c:out value="${role.role}"></c:out>
                </div>
            </c:forEach>
        </div>
        <div>
            <button>
                <input type="submit" value="${button}">
            </button>
        </div>
        </form>
    </c:when>
    <c:otherwise>
        </form>
        <div>
            Executing the following roles:
            <c:forEach items="${user.roles}" var="role">
                <br><c:out value="${role.role}"></c:out>
            </c:forEach>
        </div>
        <%-- if we view, print additional buttons--%>
        <div class="btn-update">
            <form action="/updateuser" method="get">
                <input type="hidden"  name="id" value="${user.id}">
                <input type="submit" value="UPDATE">
            </form>
        </div>
        <div class="btn-delete">
            <form action="/deleteuser" method="post">
                <input type="hidden"  name="id" value="${user.id}">
                <input type="submit" value="DELETE">
            </form>
        </div>
    </c:otherwise>
</c:choose>
<div>
    <button type="button" name="back" onclick="history.back()">BACK</button>
    <button><a href="/users">Users</a></button>
</div>
<%@include file="../bottom.jsp"%>
