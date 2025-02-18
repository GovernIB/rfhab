<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${activitatFilterForm.contexte}"/>
  <c:set var="formName" value="activitat" />
  <c:set var="__theFilterForm" value="${activitatFilterForm}" />
  <c:if test="${empty activitatFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="activitat.activitat"/>
  </c:if>
  <c:if test="${not empty activitatFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${activitatFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty activitatFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="activitat.activitat"/>
  </c:if>
  <c:if test="${not empty activitatFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${activitatFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.activitat.submit();  
  }
</script>
