<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${scanWebFilterForm.contexte}"/>
  <c:set var="formName" value="scanWeb" />
  <c:set var="__theFilterForm" value="${scanWebFilterForm}" />
  <c:if test="${empty scanWebFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="scanWeb.scanWeb"/>
  </c:if>
  <c:if test="${not empty scanWebFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${scanWebFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty scanWebFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="scanWeb.scanWeb"/>
  </c:if>
  <c:if test="${not empty scanWebFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${scanWebFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.scanWeb.submit();  
  }
</script>
