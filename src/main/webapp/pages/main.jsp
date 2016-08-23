<%@include file="head.jsp"%>
    <style>
        <%@include file="/css/cmsMain.css"%>
    </style>

    <div class="bodydiv">
        <div class="header">Hello <%=userName %> </div>
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
