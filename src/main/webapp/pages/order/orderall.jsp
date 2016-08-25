<%@include file="../head.jsp"%>

<div>
  Orders
<c:forEach items="${orders}" var="item">
  ${item.toString()}<br>
</c:forEach>
  <p>${order}</p>
</div>

<table>
    ${html}
</table>

<%@include file="../bottom.jsp"%>

