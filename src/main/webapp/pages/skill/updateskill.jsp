<%@include file="../head.jsp"%>

<form action="/updateskill" method="post">
    <div>
        <label for="">Skill name</label>
        <input type="text" name="skillname" value="${skillName}">
    </div>
    <div>
        <label for="">Skill description</label>
        <input type="text" name="skilldesc" value="${skillDesc}">
    </div>
    <div>
        <button>
            <input type="submit" value="Create">
        </button>
    </div>
</form>

<%@include file="../bottom.jsp"%>
