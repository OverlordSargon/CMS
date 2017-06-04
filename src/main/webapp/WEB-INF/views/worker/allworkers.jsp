<%@include file="../components/head.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="grid-view">

    <div>
        <button class="cmsbutton">
            <a href="/create_worker">Create new worker</a>
        </button>
    </div>
    <div class="table_header"> Workers</div>

    <c:choose>
        <c:when test="${workers != null}">
            <table class="cmstable">
                <tr>
                    <th> ID</th>
                    <th> Имя</th>
                    <th> Телефон</th>
                    <th> Навыки</th>
                    <th> Последнее обнволение</th>
                    <th> Действия</th>
                </tr>
                <c:forEach var="worker" items="${workers}">
                    <tr>
                        <td><c:out value="${worker.id}"/></td>
                        <td><c:out value="${worker.name}"/></td>
                        <td><c:out value="${worker.telephone}"/></td>
                        <td>
                            <c:forEach var="skill" items="${worker.skills}">
                                <div class="attr-view">
                                    <form action="/view_skill" method="get">
                                        <input type="hidden" name="id" value="${skill.id}">
                                        <input type="submit" value="${skill.name}" class="btn view">
                                    </form>
                                </div>
                            </c:forEach>
                        </td>
                        <td>
                            <fmt:formatDate pattern="MM-dd-y HH:mm:ss" value="${worker.updatedAt}"/>
                        </td>
                        <td>
                            <div class="btn-view">
                                <form action="/view_worker" method="get">
                                    <input type="hidden" name="id" value="${worker.id}">
                                    <input type="submit" value="Просмотр" class="btn view">
                                </form>
                            </div>
                            <div class="btn-update">
                                <form action="/update_worker" method="get">
                                    <input type="hidden" name="id" value="${worker.id}">
                                    <input type="submit" value="Обновить" class="btn renew">
                                </form>
                            </div>
                            <div class="btn-delete">
                                <form action="/delete_worker" method="post">
                                    <input type="hidden" name="id" value="${worker.id}">
                                    <input type="submit" value="Удалить" class="btn delete">
                                </form>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>

        </c:otherwise>
    </c:choose>
</div>
<%@include file="../components/bottom.jsp" %>
