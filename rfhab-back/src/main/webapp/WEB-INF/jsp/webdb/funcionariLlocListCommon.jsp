<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${funcionariLlocFilterForm.contexte}"/>
  <c:set var="formName" value="funcionariLloc" />
  <c:set var="__theFilterForm" value="${funcionariLlocFilterForm}" />
  <c:if test="${empty funcionariLlocFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="funcionariLloc.funcionariLloc"/>
  </c:if>
  <c:if test="${not empty funcionariLlocFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${funcionariLlocFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty funcionariLlocFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="funcionariLloc.funcionariLloc"/>
  </c:if>
  <c:if test="${not empty funcionariLlocFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${funcionariLlocFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.funcionariLloc.submit();  
  }
</script>
