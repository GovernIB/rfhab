<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${rolFilterForm.contexte}"/>
  <c:set var="formName" value="rol" />
  <c:set var="__theFilterForm" value="${rolFilterForm}" />
  <c:if test="${empty rolFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="rol.rol"/>
  </c:if>
  <c:if test="${not empty rolFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${rolFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty rolFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="rol.rol"/>
  </c:if>
  <c:if test="${not empty rolFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${rolFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.rol.submit();  
  }
</script>
