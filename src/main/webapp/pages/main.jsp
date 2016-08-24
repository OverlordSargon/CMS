<%@include file="head.jsp"%>
    <div class="bodydiv">
    <%
        String roles = "";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("role")) {
                    roles += " Role: "+cookie.getValue();
                }
            }
        }
    %>
        <div class="header">Hello <%=userName %> <%= roles %></div>
        <div class="info">
            <div>U`ve entered ${param}</div>
            <div class="formdiv">
                <form action="/order">
                    <input type="text" name="ordernum">
                    <button> Кнопка
                        <input type="submit">
                    </button>
                </form>
            </div>
        </div>
    </div>
    <div class="message">
        <p>Param1 ${par1}</p>
        <p>Param2 ${par2}</p>
        <p>message ${message}</p>
    </div>


<%@include file="bottom.jsp"%>
