<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${llocRolFilterForm.contexte}"/>
  <c:set var="formName" value="llocRol" />
  <c:set var="__theFilterForm" value="${llocRolFilterForm}" />
  <c:if test="${empty llocRolFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="llocRol.llocRol"/>
  </c:if>
  <c:if test="${not empty llocRolFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${llocRolFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty llocRolFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="llocRol.llocRol"/>
  </c:if>
  <c:if test="${not empty llocRolFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${llocRolFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.llocRol.submit();  
  }
</script>
