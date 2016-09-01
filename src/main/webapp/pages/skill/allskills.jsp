<%@include file="../head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
    <button>
        <a href="/newskill">Create new skill</a>
    </button>
</div>
<div class="table_header"> SKills </div>
<table>
    <tr>
        <th> ID </th>
        <th> Name </th>
        <th> Description </th>
        <th> Last update </th>
        <th> Actions</th>
    </tr>
    <c:forEach var="skill" items="${skills}">
        <tr>
            <td><c:out value="${skill.id}"/></td>
            <td><c:out value="${skill.name}"/></td>
            <td><c:out value="${skill.description}"/></td>
            <td><c:out value="${skill.updatedAt}"/></td>
            <td>
                <div class="btn-update">
                    <form action="/updateskill" method="get">
                        <input type="hidden"  name="id" value="${skill.id}">
                        <input type="submit" value="UPDATE">
                    </form>
                </div>
                <div class="btn-delete">
                    <form action="/deleteskill" method="post">
                        <input type="hidden"  name="id" value="${skill.id}">
                        <input type="submit" value="DELETE">
                    </form>
                </div>
            </td>
        </tr>
    </c:forEach>
</table>

<%@include file="../bottom.jsp"%>
