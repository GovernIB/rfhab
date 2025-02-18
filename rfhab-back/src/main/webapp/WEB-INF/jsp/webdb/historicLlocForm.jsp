
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="historicLlocForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="historicLlocFormTitle.jsp" %>
 
  <c:set var="contexte" value="${historicLlocForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="historicLlocFormCorePre.jsp" %>

  <%@include file="historicLlocFormCore.jsp" %>

  <%@include file="historicLlocFormCorePost.jsp" %>

  <%@include file="historicLlocFormButtons.jsp" %>

  <c:if test="${not empty historicLlocForm.sections}">
     <c:set var="__basename" value="historicLloc" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${historicLlocForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/historicLlocFormModificable.jsp" %>
  </c:if>

</form:form>


