
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="funcionariLlocForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="funcionariLlocFormTitle.jsp" %>
 
  <c:set var="contexte" value="${funcionariLlocForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="funcionariLlocFormCorePre.jsp" %>

  <%@include file="funcionariLlocFormCore.jsp" %>

  <%@include file="funcionariLlocFormCorePost.jsp" %>

  <%@include file="funcionariLlocFormButtons.jsp" %>

  <c:if test="${not empty funcionariLlocForm.sections}">
     <c:set var="__basename" value="funcionariLloc" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${funcionariLlocForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/funcionariLlocFormModificable.jsp" %>
  </c:if>

</form:form>


