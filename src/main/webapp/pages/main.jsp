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
        } else {
            response.sendRedirect("/login");
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
            <h1> Welcome to CMS! </h1>
            <div>
                Here You can no longer bother about your workers capacity!
            </div>
        </div>
        <div>
            ${accessmessage}
        </div>
    </div>



<%@include file="bottom.jsp"%>

