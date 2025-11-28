<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${habilitacioFilterForm.contexte}"/>
  <c:set var="formName" value="habilitacio" />
  <c:set var="__theFilterForm" value="${habilitacioFilterForm}" />
  <c:if test="${empty habilitacioFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="habilitacio.habilitacio"/>
  </c:if>
  <c:if test="${not empty habilitacioFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${habilitacioFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty habilitacioFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="habilitacio.habilitacio"/>
  </c:if>
  <c:if test="${not empty habilitacioFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${habilitacioFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.habilitacio.submit();  
  }
</script>
