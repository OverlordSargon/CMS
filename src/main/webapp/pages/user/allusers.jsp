<%@include file="../head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="grid-view">

    <div>
        <button class="cmsbutton">
            <a href="/newuser">Create new user</a>
        </button>
    </div>
    <div class="table_header"> Users </div>
    <c:choose>
        <c:when test="${users != null}">
    <table>
        <tr>
            <th> ID </th>
            <th> Name </th>
            <th> Roles </th>
            <th> Last update </th>
            <th> Actions</th>
        </tr>
        <c:forEach var="user" items="${users}">
            <tr>
                <td><c:out value="${user.id}"/></td>
                <td><c:out value="${user.login}"/></td>
                <td>
                    <c:forEach var="role" items="${user.roles}">
                        <div>
                            <div class="attr-view">
                                <form action="/viewrole" method="get">
                                    <input type="hidden"  name="id" value="${role.id}">
                                    <input type="submit" value="${role.role}">
                                </form>
                            </div>
                        </div>
                    </c:forEach>
                </td>
                <td>
                    <fmt:formatDate pattern="MM-dd-y HH:mm:ss"  value="${user.updatedAt}" />
                </td>
                <td>
                    <div class="btn-update">
                        <form action="/viewuser" method="get">
                            <input type="hidden"  name="id" value="${user.id}">
                            <input type="submit" value="VIEW" class="btn view">
                        </form>
                    </div>
                    <div class="btn-update">
                        <form action="/updateuser" method="get">
                            <input type="hidden"  name="id" value="${user.id}">
                            <input type="submit" value="UPDATE" class="btn renew">
                        </form>
                    </div>
                    <div class="btn-delete">
                        <form action="/deleteuser" method="post">
                            <input type="hidden"  name="id" value="${user.id}">
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
                Just no users yet :)
            </div>
        </c:otherwise>
    </c:choose>
</div>
<%@include file="../bottom.jsp"%>