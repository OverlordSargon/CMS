<%@include file="../head.jsp"%>

<form action="/newskill" method="post">
    <div>
        <label for="">Skill name</label>
        <input type="text" name="skillname">
    </div>
    <div>
        <label for="">Skill description</label>
        <input type="text" name="skilldesc">
    </div>
    <div>
        <button>
            <input type="submit" value="Create">
        </button>
    </div>
</form>

<%@include file="../bottom.jsp"%>
