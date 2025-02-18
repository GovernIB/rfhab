<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${autoritzacioFilterForm.contexte}"/>
  <c:set var="formName" value="autoritzacio" />
  <c:set var="__theFilterForm" value="${autoritzacioFilterForm}" />
  <c:if test="${empty autoritzacioFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="autoritzacio.autoritzacio"/>
  </c:if>
  <c:if test="${not empty autoritzacioFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${autoritzacioFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty autoritzacioFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="autoritzacio.autoritzacio"/>
  </c:if>
  <c:if test="${not empty autoritzacioFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${autoritzacioFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.autoritzacio.submit();  
  }
</script>
