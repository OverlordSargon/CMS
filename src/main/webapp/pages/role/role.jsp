<%@include file="../head.jsp"%>
<div class="form-view">
    <form action="${action}" method="post">
        <div class="form-part">
            <label for="">Role name</label>
            <input type="text" name="rolename" value="${role.role}" ${disabled}>
        </div>
        <div>
            <label for="">Role description</label>
            <input type="text" name="roledesc" value="${role.description}" ${disabled}>
        </div>
    <c:choose>
        <c:when test="${action != \"/role\"}">
        <%--If U or C print the following name--%>
        <div>
           <input type="submit" class="btn create" value="${button}">
        </div>
        </form>
        </c:when>
        <c:otherwise>
        </form>
            <%-- if we view print additional buttons--%>
            <%--<div class="buttons-ud">--%>
                <%--<div class="">--%>
                    <%--<form action="/updaterole" method="get">--%>
                        <%--<input type="hidden"  name="id" value="${role.id}">--%>
                        <%--<input type="submit" value="UPDATE" class="btn renew">--%>
                    <%--</form>--%>
                <%--</div>--%>
                <%--<div class="btn-delete">--%>
                    <%--<form action="/deleterole" method="post">--%>
                        <%--<input type="hidden"  name="id" value="${role.id}">--%>
                        <%--<input type="submit" value="DELETE" class="btn delete">--%>
                    <%--</form>--%>
                <%--</div>--%>
            <%--</div>--%>
        </c:otherwise>
        </c:choose>

    <div class="buttons-back">
        <button type="button" name="back" onclick="history.back()">BACK</button>
        <button><a href="/roles">Roles</a></button>
    </div>
</div>
<%@include file="../bottom.jsp"%>
