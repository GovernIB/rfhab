<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${sincroUnitatsFilterForm.contexte}"/>
  <c:set var="formName" value="sincroUnitats" />
  <c:set var="__theFilterForm" value="${sincroUnitatsFilterForm}" />
  <c:if test="${empty sincroUnitatsFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="sincroUnitats.sincroUnitats"/>
  </c:if>
  <c:if test="${not empty sincroUnitatsFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${sincroUnitatsFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty sincroUnitatsFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="sincroUnitats.sincroUnitats"/>
  </c:if>
  <c:if test="${not empty sincroUnitatsFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${sincroUnitatsFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.sincroUnitats.submit();  
  }
</script>
