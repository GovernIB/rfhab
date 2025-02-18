<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${funcionariFilterForm.contexte}"/>
  <c:set var="formName" value="funcionari" />
  <c:set var="__theFilterForm" value="${funcionariFilterForm}" />
  <c:if test="${empty funcionariFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="funcionari.funcionari"/>
  </c:if>
  <c:if test="${not empty funcionariFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${funcionariFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty funcionariFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="funcionari.funcionari"/>
  </c:if>
  <c:if test="${not empty funcionariFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${funcionariFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.funcionari.submit();  
  }
</script>
