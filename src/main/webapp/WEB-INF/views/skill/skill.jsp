<%@include file="../components/head.jsp"%>

<div class="form-view">
        <form action="${action}" method="post">
        <div>
            <label for="">Skill name</label>
            <input type="text" name="skillname" value="${skill.name}" ${disabled}>
        </div>
        <div>
            <label for="">Skill description</label>
            <input type="text" name="skilldesc" value="${skill.description}" ${disabled}>
        </div>
    <c:choose>
        <c:when test="${action != \"/skill\"}">
            <%--If U or C print the following name--%>
        <div>
           <input type="submit" class="btn create" value="${button}">
        </div>
    </form>
        </c:when>
        <c:otherwise>
            </form>
            <%-- if we view, print additional buttons--%>
            <div class="buttons-ud">
                <div class="btn-update">
                    <form action="/updateskill" method="get">
                        <input type="hidden"  name="id" value="${skill.id}">
                        <input type="submit" value="UPDATE" class="btn renew">
                    </form>
                </div>
                <div class="btn-delete">
                    <form action="/deleteskill" method="post">
                        <input type="hidden"  name="id" value="${skill.id}">
                        <input type="submit" value="DELETE" class="btn delete">
                    </form>
                </div>
            </div>
        </c:otherwise>
        </c:choose>
    <div>
        <button type="button" name="back" onclick="history.back()">BACK</button>
        <button><a href="/roles">Skills</a></button>
    </div>
</div>
<%@include file="../components/bottom.jsp"%>
