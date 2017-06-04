<%@include file="../components/head.jsp" %>
<%
    if (session == null) {
%>
<jsp:forward page="/login"/>
<%
    }
%>
<div class="grid-view">

    <div>
        <button class="cmsbutton">
            <a href="/order_create">Создать новый заказ</a>
        </button>
    </div>
    <div class="table_header"> Заказы</div>

    <c:choose>
        <c:when test="${orders != null}">
            <table class="cmstable">
                <tr>
                    <th <%=disabledField%> > ID</th>
                    <th> Номер заказа</th>
                    <th> Описание</th>
                    <th> День</th>
                    <th> Начало</th>
                    <th> Конец</th>
                    <th> Имя клиента</th>
                    <th> Номер телефона</th>
                    <th> Рабочий</th>
                    <th <%=disabledField%> > Последнее обнволение</th>
                    <th> Действия </th>
                </tr>
                <c:forEach var="order" items="${orders}">
                    <tr>
                        <td <%=disabledField%> ><c:out value="${order.id}"/></td>
                        <td><c:out value="${order.ordNumber}"/></td>
                        <td><c:out value="${order.description}"/></td>
                        <td>
                            <fmt:formatDate pattern="dd-MM-y" value="${order.date}"/>
                        </td>
                        <td>
                            <fmt:formatDate pattern="HH:mm:ss" value="${order.from}"/>
                        </td>
                        <td>
                            <fmt:formatDate pattern="HH:mm:ss" value="${order.to}"/>
                        </td>
                        <td><c:out value="${order.clientName}"/></td>
                        <td><c:out value="${order.telNumber}"/></td>
                        <td>
                            <div>
                                <div class="attr-view">
                                    <form action="/view_worker" method="get">
                                        <input type="hidden" name="id" value="${order.worker.id}">
                                        <input type="submit" class="btn view" value="${order.worker.name}">
                                    </form>
                                </div>
                            </div>
                        </td>
                        <td <%=disabledField%> >
                            <fmt:formatDate pattern="MM-dd-y HH:mm:ss" value="${order.updatedAt}"/>
                        </td>
                        <td>
                            <div class="btn-view">
                                <form action="/view_order" method="get">
                                    <input type="hidden" name="id" value="${order.id}">
                                    <input type="submit" value="Просмотр" class="btn view">
                                </form>
                            </div>
                            <div class="btn-update">
                                <form action="/update_order" method="get">
                                    <input type="hidden" name="id" value="${order.id}">
                                    <input type="submit" value="Обновить" class="btn renew">
                                </form>
                            </div>
                            <div class="btn-delete">
                                <form action="/delete_order" method="get">
                                    <input type="hidden" name="id" value="${order.id}">
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
