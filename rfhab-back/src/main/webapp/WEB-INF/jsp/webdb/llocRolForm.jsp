
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="llocRolForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="llocRolFormTitle.jsp" %>
 
  <c:set var="contexte" value="${llocRolForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="llocRolFormCorePre.jsp" %>

  <%@include file="llocRolFormCore.jsp" %>

  <%@include file="llocRolFormCorePost.jsp" %>

  <%@include file="llocRolFormButtons.jsp" %>

  <c:if test="${not empty llocRolForm.sections}">
     <c:set var="__basename" value="llocRol" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${llocRolForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/llocRolFormModificable.jsp" %>
  </c:if>

</form:form>


