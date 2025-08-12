<c:if test="${ vell == null || nou == null }">
  <h5>${observacions}</h5>
</c:if>

<c:if test="${ vell != null && nou != null }">
  <div class="lead">
    <label style="font-size: 1.25rem; font-weight: bold">
      <fmt:message key="historiclloc.diferencies" />
    </label>
  </div>

  <%@include file="diferenciesTable.jsp" %>
</c:if>
