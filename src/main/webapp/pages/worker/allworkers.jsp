<%@include file="../head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="grid-view">

    <div>
        <button class="cmsbutton">
            <a href="/newworker">Create new worker</a>
        </button>
    </div>
    <div class="table_header"> Workers </div>

    <c:choose>
        <c:when test="${workers != null}">
            <table>
                <tr>
                    <th> ID </th>
                    <th> Name </th>
                    <th> Telephone number </th>
                    <th> Skills </th>
                    <th> Last update </th>
                    <th> Actions</th>
                </tr>
                <c:forEach var="worker" items="${workers}">
                    <tr>
                        <td><c:out value="${worker.id}"/></td>
                        <td><c:out value="${worker.name}"/></td>
                        <td><c:out value="${worker.telephone}"/></td>
                        <td>
                            <c:forEach var="skill" items="${worker.skills}">
                                    <div class="attr-view">
                                        <form action="/viewskill" method="get">
                                            <input type="hidden"  name="id" value="${skill.id}">
                                            <input type="submit" value="${skill.name}" class="btn view">
                                        </form>
                                    </div>
                            </c:forEach>
                        </td>
                        <td>
                            <fmt:formatDate pattern="MM-dd-y HH:mm:ss"  value="${worker.updatedAt}" />
                        </td>
                        <td>
                            <div class="btn-view">
                                <form action="/viewworker" method="get">
                                    <input type="hidden"  name="id" value="${worker.id}">
                                    <input type="submit" value="VIEW" class="btn view">
                                </form>
                            </div>
                            <div class="btn-update">
                                <form action="/updateworker" method="get">
                                    <input type="hidden"  name="id" value="${worker.id}">
                                    <input type="submit" value="UPDATE" class="btn renew">
                                </form>
                            </div>
                            <div class="btn-delete">
                                <form action="/deleteworker" method="post">
                                    <input type="hidden"  name="id" value="${worker.id}">
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
                Just no workers yet :)
            </div>
        </c:otherwise>
    </c:choose>
</div>
<%@include file="../bottom.jsp"%>
