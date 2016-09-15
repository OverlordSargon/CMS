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
        <div class="main">
            <h1> Hello <%=userName %> <%= role %> </h1>
        </div>
    </div>



<%@include file="bottom.jsp"%>

