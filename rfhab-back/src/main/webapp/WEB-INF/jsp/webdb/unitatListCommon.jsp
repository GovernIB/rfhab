<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${unitatFilterForm.contexte}"/>
  <c:set var="formName" value="unitat" />
  <c:set var="__theFilterForm" value="${unitatFilterForm}" />
  <c:if test="${empty unitatFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="unitat.unitat"/>
  </c:if>
  <c:if test="${not empty unitatFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${unitatFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty unitatFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="unitat.unitat"/>
  </c:if>
  <c:if test="${not empty unitatFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${unitatFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.unitat.submit();  
  }
</script>
