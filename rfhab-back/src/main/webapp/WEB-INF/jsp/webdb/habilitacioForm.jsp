
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="habilitacioForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="habilitacioFormTitle.jsp" %>
 
  <c:set var="contexte" value="${habilitacioForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="habilitacioFormCorePre.jsp" %>

  <%@include file="habilitacioFormCore.jsp" %>

  <%@include file="habilitacioFormCorePost.jsp" %>

  <%@include file="habilitacioFormButtons.jsp" %>

  <c:if test="${not empty habilitacioForm.sections}">
     <c:set var="__basename" value="habilitacio" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${habilitacioForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/habilitacioFormModificable.jsp" %>
  </c:if>

</form:form>


