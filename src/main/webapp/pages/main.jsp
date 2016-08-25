<%@include file="head.jsp"%>
    <div class="bodydiv">
    <%
        String role = "";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("role")) {
                    role = cookie.getValue();
                }
            }
        }
    %>
    <%
        String adminBlock = "";
        if ( role.equals("Administrator") ) {
            adminBlock = "/orders";
        } else {
            adminBlock = "/orders";
        }
    %>
        <div class="header">Hello <%=userName %> <%= role %></div>
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
        <button>
            <a href="<%=adminBlock%>">
                BUTTON
            </a>
        </button>
        <button>
            <a href="/allskills">allskills</a>
        </button>
        <p>Param1 ${par1}</p>
        <p>Param2 ${par2}</p>
        <p>message ${message}</p>
    </div>


<%@include file="bottom.jsp"%>
