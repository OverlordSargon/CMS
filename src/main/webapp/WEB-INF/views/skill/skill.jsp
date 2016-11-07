<%@include file="../components/head.jsp"%>

<div class="form-view">
        <form action="${action}" method="post">
        <div class="input_div">
            <label for="">Skill name</label>
            <input type="text"  name="skillname" value="${skill.name}" ${disabled}>
        </div>
        <div class="input_div">
            <label for="">Skill description</label>
            <input type="text" class="" name="skilldesc" value="${skill.description}" ${disabled}>
        </div>
    <c:choose>
        <c:when test="${action != \"/skill\"}">
            <%--If U or C print the following name--%>
        <div>
           <input type="submit" class="btn create" value="${button}">
        </div>
    </form>
        </c:when>
        <c:otherwise>
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
        </c:otherwise>
        </c:choose>
    <div>
        <button type="button" name="back" onclick="history.back()">BACK</button>
        <button><a href="/roles">Skills</a></button>
    </div>
</div>
<script>
    $('form').validate({
        rules: {
            skillname: {
                minlength: 3,
                required: true
            },
            skilldesc: {
                minlength: 3,
                required: true
            }
        },
        highlight: function(element) {
            var id_attr = "#" + $( element ).attr("id") + "1";
            $(element).closest('.input_div').removeClass('has-success').addClass('has-error');
            $(id_attr).removeClass('glyphicon-ok').addClass('glyphicon-remove');
        },
        unhighlight: function(element) {
            var id_attr = "#" + $( element ).attr("id") + "1";
            $(element).closest('.input_div').removeClass('has-error').addClass('has-success');
            $(id_attr).removeClass('glyphicon-remove').addClass('glyphicon-ok');
        },
        errorElement: 'span',
        errorClass: 'help-block',
        errorPlacement: function(error, element) {
            if(element.length) {
                error.insertAfter(element);
            } else {
                error.insertAfter(element);
            }
        }
    });
</script>
<%@include file="../components/bottom.jsp"%>
