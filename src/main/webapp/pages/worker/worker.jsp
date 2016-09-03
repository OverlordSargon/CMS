<%@include file="../head.jsp"%>

<%--FORM BEGIN--%>
<form action="${action}" method="post">
    <%-- set name --%>
    <div>
        <label for=""> Name </label>
        <input type="text" name="workername" value="${worker.name}" ${disabled} >
    </div>

    <%-- set telephone --%>
    <div>
        <label for=""> Telephone number </label>
        <input type="text" name="workertele" value="${worker.telephone}" ${disabled} >
    </div>

    <div>
        <%-- choose begin worker date--%>
        <div>
            <label for=""> Begin date </label>
            <input type="text" name="begindate" value="" ${disabled} placeholder="dd-MM-y" >
        </div>

        <%-- choose end worker date--%>
        <div>
            <label for=""> End date </label>
            <input type="text" name="enddate" value="" ${disabled} placeholder="dd-MM-y">
        </div>
    </div>

    <div>
        <%--choose time from--%>
        <div>
            <label for=""> Work from: </label>
            <input type="text" name="begintime" value="" ${disabled}>
        </div>

        <%--choose time to--%>
        <div>
            <label for=""> to: </label>
            <input type="text" name="endtime" value="" ${disabled}>
        </div>
        <%-- choose break for 1 hour--%>
        <div>
            <label for=""> Begin 1 hour break: </label>
            <input type="text" name="breakhour" value="" ${disabled}>
        </div>
    </div>

<c:choose>
    <c:when test="${action != \"/worker\"}">
        <%--If U or C print the following name--%>
                <%-- choose skills --%>
        <div class="list">
            <div class="list_label">
                Choose skills for worker:
            </div>
            <c:forEach items="${skills}" var="skill">
                <div>
                    <input type="checkbox" name="skills" value="${skill.id}">
                    <c:out value="${skill.name}"></c:out>
                </div>
            </c:forEach>
        </div>


        <div>
            <button>
                <input type="submit" value="${button}">
            </button>
        </div>
        </form>
</form>
    </c:when>
    <%--FORM END--%>
    <%--BUTTONS--%>
    <c:otherwise>
        </form>
        <div>
            Worker ${worker.name} have the following skills:
            <c:forEach items="${worker.skills}" var="skill">
                <br><c:out value="${skill.name}"></c:out>
            </c:forEach>
        </div>
        <%-- if we view, print additional buttons--%>
        <div>
            <div class="btn-update">
                <form action="/updateworker" method="get">
                    <input type="hidden"  name="id" value="${worker.id}">
                    <input type="submit" value="UPDATE">
                </form>
            </div>
            <div class="btn-delete">
                <form action="/deleteworker" method="post">
                    <input type="hidden"  name="id" value="${worker.id}">
                    <input type="submit" value="DELETE">
                </form>
            </div>
        </div>
    </c:otherwise>
</c:choose>
<div>
    <button type="button" name="back" onclick="history.back()">BACK</button>
    <button><a href="/workers">Workers</a></button>
</div>
<%@include file="../bottom.jsp"%>
