<%@include file="../components/head.jsp"%>

<div class="form-view">
    <%--FORM BEGIN--%>
    <form action="${action}" method="post" >
        <%-- set name --%>
            <spring:bind path="worker.name">
            <div class="input_div">
                <label for=""> Name </label>
                    <input type="text" name="name" value="${worker.name}" ${disabled} >
            </div>
            </spring:bind>

        <%-- set telephone --%>
            <spring:bind path="worker.telephone">
            <div class="input_div">
                <label for=""> Telephone number </label>
                <input type="text" name="telephone" value="${worker.telephone}" ${disabled} >
            </div>
            </spring:bind>

        <c:choose>
        <c:when test="${action != \"/worker\"}">
            <%--If U or C print the following name--%>
            <%-- choose skills --%>
        <div>
                <%-- choose begin worker date--%>
            <div class=" input_div " >
                <label for=""> Begin date </label>
                <input id="begindatepicker" type="text" name="begindate" value="${dates.begindate}" ${disabled} placeholder="day-month-year" >
            </div>
            <%-- choose end worker date--%>
            <div class="input_div">
                <label for=""> End date </label>
                <input id="enddatepicker" type="text" name="enddate" value="${dates.enddate}" ${disabled} placeholder="day-month-year">
            </div>
        </div>

        <div>
                <%--choose time from--%>
            <div class="input_div">
                <label for=""> Work from: </label>
                <input id="beginhourpicker" type="text" name="beginhour"
                       <c:if test="${not empty dates.beginhour}">
                           value="${dates.beginhour}:00"
                        </c:if>
                       ${disabled} placeholder="hour:00">
            </div>

                <%--choose time to--%>
            <div class="input_div">
                <label for=""> to: </label>
                <input id="endhourpicker" type="text" name="endhour"
                    <c:if test="${not empty dates.endhour}">
                           value="${dates.endhour}:00"
                    </c:if>
                    ${disabled} placeholder="hour:00">
            </div>

                <%-- choose break for 1 hour--%>
            <div class="input_div">
                <label for=""> Begin 1 hour break: </label>
                <input id="breakpicker" type="text" name="breakstart"
                    <c:if test="${not empty dates.breakstart}">
                        value="${dates.breakstart}:00"
                    </c:if>
                ${disabled} placeholder="hour:00">
            </div>
        </div>

        <spring:bind path="worker.skills">
        <div class="list">
            <div class="list_label">
                Choose skills for worker:
            </div>
            <c:forEach items="${skills}" var="skill" varStatus="count">
                <div >
                    <div class="checklabel">
                        <c:out value="${skill.name}"></c:out>
                        <input type="checkbox" class="checkboxform" name="skills[${count.index}].id"
                        <c:forEach items="${workerskills}" var="workerskill">
                                <c:if test="${skill.id == workerskill.id}">
                                       checked
                                </c:if>
                            </c:forEach>
                       value="${skill.id}">
                    </div>
                </div>
            </c:forEach>
        </div>
        </spring:bind>


        <div>
            <input type="submit" class="btn create" value="${button}">
        </div>
    </form>
    </form>

    <script type="text/javascript">
        $(function () {
            $('#begindatepicker,#enddatepicker').datetimepicker(
                {
                    pickTime: false,
                    language: 'ru',
                    minDate: new Date(),
                    format: 'DD-MM-YYYY'
                });
            $('#beginhourpicker,#endhourpicker,#breakpicker').datetimepicker(
                {
                    pickDate: false,
                    language: 'ru',
                    useMinutes: false,
                    defaultDate:"1/1/1900 00:00"
                });
        });
    </script>
    </c:when>
        <%--FORM END--%>
        <%--BUTTONS--%>
    <c:otherwise>
        </form>
        <div class="workerinfo">
            <div class="worker_skills_info col-xs-12">
                Worker has the following skills:
                <div class="attr-view ">
                <spring:bind path="worker.skills">
                    <c:forEach items="${worker.skills}" var="skill">
                        <div class="col-xs-3">
                            <form action="/viewskill" method="get">
                                <input type="hidden"  name="id" value="${skill.id}">
                                <input type="submit" value="${skill.name}" class="btn view">
                            </form>
                        </div>
                    </c:forEach>
                </spring:bind>
                </div>
            </div>
            <div class="worker_schedule_info">
                Worker schedule:
                <div class="worker_days">
                    <div>First day: ${firstday}</div>
                    <div>Last day: ${lastday}</div>
                </div>
                <div>
                    <div class="worker_time"> Working from ${firsthour}:00 to ${pausehour}:00; from ${pausehour+1}:00  to ${lasthour}:00</div>
                    <%--<div> Pause from ${pausehour}:00 to ${pausehour+1}:00 </div>--%>
                </div>
            </div>
        </div>
        <c:if test="${fn:length(orders) > 0}">
            <div class="grid-view">
                <p style="text-align: center">Execute the following orders:</p>
                <c:forEach items="${orders}" var="order">
                    <div class="info-frame">
                        <table class="cmstable">
                            <tr><th>Order: <c:out value="${order.ordNumber}"></c:out></th></tr>
                            <tr><td>At: <fmt:formatDate pattern="dd-MM-y" value="${order.date}" /></td></tr>
                            <tr><td>From: <fmt:formatDate pattern="HH:mm:ss" value="${order.from}" /></td></tr>
                            <tr><td>To:  <fmt:formatDate pattern="HH:mm:ss" value="${order.to}" /></td></tr>
                        </table>
                    </div>
                </c:forEach>
            </div>
        </c:if>
        <%-- if we view, print additional buttons--%>
        <div class="buttons-ud">
            <div class="btn-update">
                <form action="/admin/updateworker" method="get">
                    <input type="hidden"  name="id" value="${worker.id}">
                    <input type="submit" value="UPDATE" class="btn renew">
                </form>
            </div>
            <div class="btn-delete">
                <form action="/admin/deleteworker" method="post">
                    <input type="hidden"  name="id" value="${worker.id}">
                    <input type="submit" value="DELETE" class="btn delete">
                </form>
            </div>
        </div>
    </c:otherwise>
    </c:choose>
    <div>
        <button type="button" name="back" onclick="history.back()">BACK</button>
        <button><a href="/admin/workers">Workers</a></button>
    </div>
</div>
<%@include file="../components/bottom.jsp"%>
