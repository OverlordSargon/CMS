<%@include file="../components/head.jsp"%>

<div class="form-view">
    <form action="${action}" method="post">
        <div class="input_div">
            <label for="">Skill name</label>
            <input type="text" name="skillname" value="${skill.name}" ${disabled}>
        </div>
        <div class="input_div">
            <label for="">Skill description</label>
            <input type="text" name="skilldesc" value="${skill.description}" ${disabled}>
        </div>
    </form>
        <%-- if we view, print additional buttons--%>
        <div class="buttons-ud">
            <div class="btn-update">
                <form action="/update_skill" method="get">
                    <input type="hidden"  name="id" value="${skill.id}">
                    <input type="submit" value="UPDATE" class="btn renew">
                </form>
            </div>
            <div class="btn-delete">
                <form action="/delete_skill" method="post">
                    <input type="hidden"  name="id" value="${skill.id}">
                    <input type="submit" value="DELETE" class="btn delete">
                </form>
            </div>
        </div>
    <div>
        <button type="button" name="back" onclick="history.back()">BACK</button>
        <button><a href="/roles">Skills</a></button>
    </div>
</div>
<%@include file="../components/bottom.jsp"%>
