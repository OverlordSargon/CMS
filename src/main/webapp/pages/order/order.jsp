<%@include file="../head.jsp"%>
<style>
    <%@include file="/js/jquery.datetimepicker.css"%>
</style>
<script>
    <%@include file="/js/jquery.js"%>
    <%@include file="/js/jquery.datetimepicker.js"%>
    $.datetimepicker.setLocale('en');
    jQuery('#datetimepicker').datetimepicker();
</script>
<%--FORM BEGIN--%>
<div class="form-view">
    <form action="${action}" method="post">
        <div>
            <label for=""> Order number </label>
            <input type="text" name="ordernum" value="${order.name}" ${disabled} >
        </div>
        <div>
            <label for=""> Description </label>
            <input type="text" name="orderdesc" value="${order.description}" ${disabled} >
        </div>

        <div>
            <label for=""> Start time </label>
            <input type="datetime" placeholder="MM-dd-y HH:mm" name="orderfrom" value="${order.from}" ${disabled} >
        </div>


        <div>
            <label for=""> End time </label>
            <input type="datetime" placeholder="MM-dd-y HH:mm" name="orderto" value="${order.to}" ${disabled} >
        </div>

        <div>
            <label for=""> Client name </label>
            <input type="text" name="ordercname" value="${order.clientName}" ${disabled} >
        </div>

        <div>
            <label for=""> Client telephone number </label>
            <input type="text" name="ordertele" value="${order.telNumber}" ${disabled} >
        </div>

    <c:choose>
        <c:when test="${action != \"/order\"}">

            <%--If U or C print the following name--%>
            <div class="list">
                <div class="list_label">
                    Choose worktype:
                </div>
                <select name="orderworktype" size="5" id="">
                    <c:forEach items="${skills}" var="skill" >
                        <option value="${skill.id}">${skill.name}</option>
                    </c:forEach>
                </select>
            </div>
            <%--If U or C print the following name--%>
            <%--<div class="list">--%>
                <%--<div class="list_label">--%>
                    <%--Choose worker:--%>
                <%--</div>--%>
                <%--<select name="orderworker" size="5" id="">--%>
                <%--<c:forEach items="${workers}" var="worker" >--%>
                            <%--<option value="${worker.id}">${worker.name}</option>--%>
                <%--</c:forEach>--%>
                <%--</select>--%>
            <%--</div>--%>
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
                    <form action="/updateorder" method="get">
                        <input type="hidden"  name="id" value="${order.id}">
                        <input type="submit" value="UPDATE" class="btn renew">
                    </form>
                </div>
                <div class="btn-delete">
                    <form action="/deleteorder" method="post">
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
<%@include file="../bottom.jsp"%>