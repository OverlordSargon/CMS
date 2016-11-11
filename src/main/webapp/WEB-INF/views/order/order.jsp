<%@include file="../components/head.jsp"%>
<%--FORM BEGIN--%>
<div class="form-view">
    <form action="${action}" method="post">
        <input type="hidden" name = "id" value="${order.id}">
        <div>
            <label for=""> Order number </label>
            <input type="text" name="ordNumber" value="${order.ordNumber}" ${disabled} >
        </div>
        <div>
            <label for=""> Description </label>
            <input type="text" name="description" value="${order.description}" ${disabled} >
        </div>

        <div>
            <label for=""> Day </label>
            <input id="orderdatepicker" type="datetime" placeholder="day-month-year" name="orderDay"
                   value="<fmt:formatDate pattern="dd-MM-y" value="${order.date}"/>" ${disabled} >
        </div>

        <div>
            <label for=""> Start time </label>
            <input id="beginhourpicker" type="datetime" placeholder="hour:00" name="orgerBeginHour"
                   value="<fmt:formatDate pattern="HH:mm" value="${order.from}" />" ${disabled} >
        </div>


        <div>
            <label for=""> End time </label>
            <input id="endhourpicker" type="datetime" placeholder="hour:00" name="orgerEndHour"
                   value="<fmt:formatDate pattern="HH:mm" value="${order.to}" />" ${disabled} >
        </div>

        <div>
            <label for=""> Client name </label>
            <input type="text" name="clientName" value="${order.clientName}" ${disabled} >
        </div>

        <div>
            <label for=""> Client telephone number </label>
            <input type="text" name="telNumber" value="${order.telNumber}" ${disabled} >
        </div>
        <script type="text/javascript">
            $(function () {
                $('#orderdatepicker').datetimepicker(
                        {
                            pickTime: false,
                            language: 'ru',
                            minDate: new Date(),
                            format: 'DD-MM-YYYY'
                        });
                $('#beginhourpicker').datetimepicker(
                        {
                            pickDate: false,
                            language: 'ru',
                            useMinutes: false,
                            defaultDate:"1/1/1900 09:00"
                        });
                $('#endhourpicker').datetimepicker(
                        {
                            pickDate: false,
                            language: 'ru',
                            useMinutes: false,
                            defaultDate:"1/1/1900 11:00"
                        });
            });
        </script>
    <c:choose>
        <c:when test="${action != \"/order\" and action !=\"/deleteorder\"}">

            <%--If U or C print the following name--%>
            <div class="list">
                <div class="list_label">
                    Choose worktype:
                </div>
                <select name="skill" size="1" id="">
                    <c:forEach items="${skills}" var="skill" >
                        <option value="${skill.id}">${skill.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div>
               <input type="submit" class="btn create" value="${button}">
            </div>
            </form>
        </c:when>
        <%--FORM END--%>
        <%--BUTTONS--%>
        <c:otherwise>
            <div>
                <label for=""> Worker </label>
                <input type="text" name="orderworker" value="${order.worker}" ${disabled} >
            </div>
            </form>
            <%-- if we view, print additional buttons--%>
            <div class="buttons-ud">
                <div class="btn-update">
                    <form action="/update_order" method="get">
                        <input type="hidden"  name="id" value="${order.id}">
                        <input type="submit" value="UPDATE" class="btn renew">
                    </form>
                </div>
                <div class="btn-delete">
                    <form action="/delete_order" method="post">
                        <input type="hidden"  name="id" value="${order.id}">
                        <input type="submit" value="DELETE" class="btn delete">
                    </form>
                </div>
            </div>
        </c:otherwise>
    </c:choose>
    <div>
        <button type="button" name="back" onclick="history.back()">BACK</button>
        <button><a href="/orders">Orders</a></button>
    </div>
</div>

<%@include file="../components/bottom.jsp"%>
