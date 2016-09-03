<%@include file="../head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
    <button>
        <a href="/newrole">Create new role</a>
    </button>
</div>
<div class="table_header"> Roles </div>

    <c:choose>
        <c:when test="${roles != null}">
            <table>
                <tr>
                    <th> ID </th>
                    <th> Name </th>
                    <th> Description </th>
                    <th> Last update </th>
                    <th> Actions</th>
                </tr>
            <c:forEach var="role" items="${roles}">
                <tr>
                    <td><c:out value="${role.id}"/></td>
                    <td><c:out value="${role.role}"/></td>
                    <td><c:out value="${role.description}"/></td>
                    <td>
                        <fmt:formatDate pattern="MM-dd-y HH:mm:ss"  value="${role.updatedAt}" />
                    </td>
                    <td>
                        <div class="btn-view">
                            <form action="/viewrole" method="get">
                                <input type="hidden"  name="id" value="${role.id}">
                                <input type="submit" value="VIEW">
                            </form>
                        </div>
                        <div class="btn-update">
                            <form action="/updaterole" method="get">
                                <input type="hidden"  name="id" value="${role.id}">
                                <input type="submit" value="UPDATE">
                            </form>
                        </div>
                        <div class="btn-delete">
                            <form action="/deleterole" method="post">
                                <input type="hidden"  name="id" value="${role.id}">
                                <input type="submit" value="DELETE">
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
                Just no roles yet :)
            </div>
        </c:otherwise>
    </c:choose>

<%@include file="../bottom.jsp"%>
