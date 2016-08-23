<%@include file="head.jsp"%>
    <style>
        <%@include file="/css/cmsMain.css"%>
    </style>
<%
    //allow access only if session exists
    String user = null;
    if(session.getAttribute("user") == null){
        response.sendRedirect("login.html");
    }else user = (String) session.getAttribute("user");
    String userName = null;
    String sessionID = null;
    Cookie[] cookies = request.getCookies();
    if(cookies !=null){
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("user")) userName = cookie.getValue();
            if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
        }
    }
%>
    <div class="bodydiv">
        <div class="header">Hello ${userName}</div>
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
