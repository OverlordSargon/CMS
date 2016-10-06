<%@include file="../head.jsp"%>
<%
    if (session == null)
    {
        %><jsp:forward page="/login" /><%
    }
%>
<div class="grid-view">

    <div>
        <button class="cmsbutton">
            <a href="/neworder">Create new order</a>
        </button>
    </div>
    <div class="table_header"> Orders </div>

    <c:choose>
        <c:when test="${orders != null}">
            <table>
                <tr>
                    <th <%=disabledField%> > ID </th>
                    <th> Order number </th>
                    <th> Description </th>
                    <th> Day </th>
                    <th> Start </th>
                    <th> End </th>
                    <th> Client name </th>
                    <th> Client telephone </th>
                    <th> Worker </th>
                    <th <%=disabledField%> > Last update</th>
                    <th> Actions</th>
                </tr>
                <c:forEach var="order" items="${orders}">
                    <tr>
                        <td <%=disabledField%> ><c:out value="${order.id}"/></td>
                        <td><c:out value="${order.ordNumber}"/></td>
                        <td><c:out value="${order.description}"/></td>
                        <td>
                            <fmt:formatDate pattern="dd-MM-y" value="${order.date}" />
                        </td>
                        <td>
                            <fmt:formatDate pattern="HH:mm:ss" value="${order.from}" />
                        </td>
                        <td>
                            <fmt:formatDate pattern="HH:mm:ss" value="${order.to}" />
                        </td>
                        <td><c:out value="${order.clientName}"/></td>
                        <td><c:out value="${order.telNumber}"/></td>
                        <td>
                            <div>
                                <div class="attr-view">
                                    <form action="/admin/viewworker" method="get">
                                        <input type="hidden"  name="id" value="${order.worker.id}">
                                        <input type="submit" class="btn view" value="${order.worker.name}">
                                    </form>
                                </div>
                            </div>
                        </td>
                        <td <%=disabledField%> >
                            <fmt:formatDate pattern="MM-dd-y HH:mm:ss" value="${order.updatedAt}" />
                        </td>
                        <td>
                            <div class="btn-view">
                                <form action="/vieworder" method="get">
                                    <input type="hidden"  name="id" value="${order.id}">
                                    <input type="submit" value="VIEW" class="btn view">
                                </form>
                            </div>
                            <div class="btn-update">
                                <form action="/updateorder" method="get">
                                    <input type="hidden"  name="id" value="${order.id}">
                                    <input type="submit" value="UPDATE" class="btn renew">
                                </form>
                            </div>
                            <div class="btn-delete">
                                <form action="/deleteorder" method="get">
                                    <input type="hidden"  name="id" value="${order.id}">
                                    <input type="submit" value="DELETE" class="btn delete">
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
<%@include file="../bottom.jsp"%>
