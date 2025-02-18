
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="scanWebForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="scanWebFormTitle.jsp" %>
 
  <c:set var="contexte" value="${scanWebForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="scanWebFormCorePre.jsp" %>

  <%@include file="scanWebFormCore.jsp" %>

  <%@include file="scanWebFormCorePost.jsp" %>

  <%@include file="scanWebFormButtons.jsp" %>

  <c:if test="${not empty scanWebForm.sections}">
     <c:set var="__basename" value="scanWeb" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${scanWebForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/scanWebFormModificable.jsp" %>
  </c:if>

</form:form>


