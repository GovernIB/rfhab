
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="funcionariRolForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="funcionariRolFormTitle.jsp" %>
 
  <c:set var="contexte" value="${funcionariRolForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="funcionariRolFormCorePre.jsp" %>

  <%@include file="funcionariRolFormCore.jsp" %>

  <%@include file="funcionariRolFormCorePost.jsp" %>

  <%@include file="funcionariRolFormButtons.jsp" %>

  <c:if test="${not empty funcionariRolForm.sections}">
     <c:set var="__basename" value="funcionariRol" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${funcionariRolForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/funcionariRolFormModificable.jsp" %>
  </c:if>

</form:form>


