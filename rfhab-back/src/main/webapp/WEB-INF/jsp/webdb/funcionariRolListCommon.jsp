<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${funcionariRolFilterForm.contexte}"/>
  <c:set var="formName" value="funcionariRol" />
  <c:set var="__theFilterForm" value="${funcionariRolFilterForm}" />
  <c:if test="${empty funcionariRolFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="funcionariRol.funcionariRol"/>
  </c:if>
  <c:if test="${not empty funcionariRolFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${funcionariRolFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty funcionariRolFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="funcionariRol.funcionariRol"/>
  </c:if>
  <c:if test="${not empty funcionariRolFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${funcionariRolFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.funcionariRol.submit();  
  }
</script>
