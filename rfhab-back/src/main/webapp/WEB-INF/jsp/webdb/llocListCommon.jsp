<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${llocFilterForm.contexte}"/>
  <c:set var="formName" value="lloc" />
  <c:set var="__theFilterForm" value="${llocFilterForm}" />
  <c:if test="${empty llocFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="lloc.lloc"/>
  </c:if>
  <c:if test="${not empty llocFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${llocFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty llocFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="lloc.lloc"/>
  </c:if>
  <c:if test="${not empty llocFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${llocFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.lloc.submit();  
  }
</script>
