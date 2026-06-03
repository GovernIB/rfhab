
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="sincroUnitatsForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="sincroUnitatsFormTitle.jsp" %>
 
  <c:set var="contexte" value="${sincroUnitatsForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="sincroUnitatsFormCorePre.jsp" %>

  <%@include file="sincroUnitatsFormCore.jsp" %>

  <%@include file="sincroUnitatsFormCorePost.jsp" %>

  <%@include file="sincroUnitatsFormButtons.jsp" %>

  <c:if test="${not empty sincroUnitatsForm.sections}">
     <c:set var="__basename" value="sincroUnitats" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${sincroUnitatsForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/sincroUnitatsFormModificable.jsp" %>
  </c:if>

</form:form>


