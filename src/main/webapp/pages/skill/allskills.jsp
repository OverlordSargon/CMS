<%@include file="../head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="grid-view">

    <div>
        <button  class="cmsbutton">
            <a href="/newskill">Create new skill</a>
        </button>
    </div>
    <div class="table_header"> SKills </div>
    <c:choose>
        <c:when test="${skills != null}">
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
                <td>
                    <fmt:formatDate pattern="MM-dd-y HH:mm:ss"  value="${skill.updatedAt}" />
                </td>
                <td>
                    <div class="btn-update">
                        <form action="/viewskill" method="get">
                            <input type="hidden"  name="id" value="${skill.id}">
                            <input type="submit" value="VIEW" class="btn view">
                        </form>
                    </div>
                    <div class="btn-update">
                        <form action="/updateskill" method="get">
                            <input type="hidden"  name="id" value="${skill.id}">
                            <input type="submit" value="UPDATE" class="btn renew">
                        </form>
                    </div>
                    <div class="btn-delete">
                        <form action="/deleteskill" method="get">
                            <input type="hidden"  name="id" value="${skill.id}">
                            <input type="submit" value="DELETE" class="btn delete">
                        </form>
                    </div>
                </td>
            </tr>
        </c:forEach>
        </table>
        </c:when>
        <c:otherwise>
            <div class="no_yet">
                Don`t Worry!<br>
                Just no skills yet :)
            </div>
        </c:otherwise>
    </c:choose>
</div>
<%@include file="../bottom.jsp"%>
