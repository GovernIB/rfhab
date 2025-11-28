<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${llocHabilitacioFilterForm.contexte}"/>
  <c:set var="formName" value="llocHabilitacio" />
  <c:set var="__theFilterForm" value="${llocHabilitacioFilterForm}" />
  <c:if test="${empty llocHabilitacioFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="llocHabilitacio.llocHabilitacio"/>
  </c:if>
  <c:if test="${not empty llocHabilitacioFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${llocHabilitacioFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty llocHabilitacioFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="llocHabilitacio.llocHabilitacio"/>
  </c:if>
  <c:if test="${not empty llocHabilitacioFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${llocHabilitacioFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.llocHabilitacio.submit();  
  }
</script>
