
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="historicForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="historicFormTitle.jsp" %>
 
  <c:set var="contexte" value="${historicForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="historicFormCorePre.jsp" %>

  <%@include file="historicFormCore.jsp" %>

  <%@include file="historicFormCorePost.jsp" %>

  <%@include file="historicFormButtons.jsp" %>

  <c:if test="${not empty historicForm.sections}">
     <c:set var="__basename" value="historic" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${historicForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/historicFormModificable.jsp" %>
  </c:if>

</form:form>


