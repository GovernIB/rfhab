
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="autoritzacioForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="autoritzacioFormTitle.jsp" %>
 
  <c:set var="contexte" value="${autoritzacioForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="autoritzacioFormCorePre.jsp" %>

  <%@include file="autoritzacioFormCore.jsp" %>

  <%@include file="autoritzacioFormCorePost.jsp" %>

  <%@include file="autoritzacioFormButtons.jsp" %>

  <c:if test="${not empty autoritzacioForm.sections}">
     <c:set var="__basename" value="autoritzacio" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${autoritzacioForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/autoritzacioFormModificable.jsp" %>
  </c:if>

</form:form>


