<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${historicLlocFilterForm.contexte}"/>
  <c:set var="formName" value="historicLloc" />
  <c:set var="__theFilterForm" value="${historicLlocFilterForm}" />
  <c:if test="${empty historicLlocFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="historicLloc.historicLloc"/>
  </c:if>
  <c:if test="${not empty historicLlocFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${historicLlocFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty historicLlocFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="historicLloc.historicLloc"/>
  </c:if>
  <c:if test="${not empty historicLlocFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${historicLlocFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.historicLloc.submit();  
  }
</script>
