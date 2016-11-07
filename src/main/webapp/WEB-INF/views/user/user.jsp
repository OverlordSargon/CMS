<%@include file="../components/head.jsp"%>
<div class="form-view">
    <form:form modelAttribute="user" action="${action}" method="post">
        <div class="input_div">
            <label for="">User name</label>
            <input path="login" type="text" name="login" value="${user.login}">
        </div>
        <div class="input_div">
            <label for="">User password</label>
            <input path="password" type="text" name="password" value="${user.password}">
        </div>

        <%--If U or C print the following name--%>
        <div class="roles_list">
            <div class="roles_list_label">
                Choose roles for new user:
            </div>
            <c:forEach items="${roles}" var="role" varStatus="count">
                <div>
                    <div class="checklabel">
                        <c:out value="${role.role}"></c:out>
                    </div>
                    <input type="checkbox" class="checkboxform" name="roles[${count.index}].id" value="${role.id}" />
                </div>
            </c:forEach>
        </div>
        <div>
           <input type="submit" class="btn create" value="${button}">
        </div>
        </form:form>
    <div>
        <button type="button" name="back" onclick="history.back()">BACK</button>
        <button><a href="/users">Users</a></button>
    </div>
</div>
<%@include file="../components/bottom.jsp"%>
