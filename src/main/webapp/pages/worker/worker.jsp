<%@include file="../head.jsp"%>
<div class="form-view">

    <%--FORM BEGIN--%>
    <form action="${action}" method="post">
        <%-- set name --%>
        <div>
            <label for=""> Name </label>
            <input type="text" name="workername" value="${worker.name}" ${disabled} >
        </div>

        <%-- set telephone --%>
        <div>
            <label for=""> Telephone number </label>
            <input type="text" name="workertele" value="${worker.telephone}" ${disabled} >
        </div>



        <c:choose>
        <c:when test="${action != \"/worker\"}">
            <%--If U or C print the following name--%>
            <%-- choose skills --%>
        <div>
                <%-- choose begin worker date--%>
            <div>
                <label for=""> Begin date </label>
                <input type="text" name="begindate" value="${firstday}" ${disabled} placeholder="day-month-year" >
            </div>

                <%-- choose end worker date--%>
            <div>
                <label for=""> End date </label>
                <input type="text" name="enddate" value="${lastday}" ${disabled} placeholder="day-month-year">
            </div>
        </div>

        <div>
                <%--choose time from--%>
            <div>
                <label for=""> Work from: </label>
                <input type="text" name="begintime"
                       <c:if test="${not empty firsthour}">
                           value="${firsthour}:00"
                        </c:if>
                       ${disabled} placeholder="hour:00">
            </div>

                <%--choose time to--%>
            <div>
                <label for=""> to: </label>
                <input type="text" name="endtime"
                    <c:if test="${not empty lasthour}">
                           value="${lasthour}:00"
                    </c:if>
                    ${disabled} placeholder="hour:00">
            </div>
                <%-- choose break for 1 hour--%>
            <div>
                <label for=""> Begin 1 hour break: </label>
                <input type="text" name="breakhour" value="" ${disabled} placeholder="hour:00">
            </div>
        </div>

        <div class="list">
            <div class="list_label">
                Choose skills for worker:
            </div>
            <c:forEach items="${skills}" var="skill">
                <div >
                    <div class="checklabel">
                        <c:out value="${skill.name}"></c:out>
                    </div>
                    <input type="checkbox" class="checkboxform" name="skills"
                        <c:if test="${fn}">

                        </c:if>
                       value="${skill.id}">
                </div>
            </c:forEach>
        </div>


        <div>
            <input type="submit" class="btn create" value="${button}">
        </div>
    </form>
    </form>
    </c:when>
        <%--FORM END--%>
        <%--BUTTONS--%>
    <c:otherwise>
        </form>
        <div class="workerinfo">
            <div class="worker_skills_info col-xs-12">
                Worker has the following skills:
                <div class="attr-view ">
                <c:forEach items="${worker.skills}" var="skill">
                    <div class="col-xs-3">
                        <form action="/viewskill" method="get">
                            <input type="hidden"  name="id" value="${skill.id}">
                            <input type="submit" value="${skill.name}" class="btn view">
                        </form>
                    </div>
                </c:forEach>
                </div>
            </div>
            <div class="worker_schedule_info">
                Worker schedule:
                <div class="worker_days">
                    <div>First day: ${firstday}</div>
                    <div>Last day: ${lastday}</div>
                </div>
                <div>
                    <div class="worker_time"> Working from ${firsthour}:00 ${pausehour}:00, from ${pausehour+1}:00  to ${lasthour}:00</div>
                    <%--<div> Pause from ${pausehour}:00 to ${pausehour+1}:00 </div>--%>
                </div>
            </div>
        </div>
        <c:if test="${fn:length(orders) > 0}">
            <div class="grid-view">
                <p style="text-align: center">Execute the following orders:</p>
                <c:forEach items="${orders}" var="order">
                    <div class="info-frame">
                        <table>
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
        </div>
    </c:otherwise>
    </c:choose>
    <div>
        <button type="button" name="back" onclick="history.back()">BACK</button>
        <button><a href="/workers">Workers</a></button>
    </div>
</div>
<%@include file="../bottom.jsp"%>
