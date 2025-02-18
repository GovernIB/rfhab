
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="rolForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="rolFormTitle.jsp" %>
 
  <c:set var="contexte" value="${rolForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="rolFormCorePre.jsp" %>

  <%@include file="rolFormCore.jsp" %>

  <%@include file="rolFormCorePost.jsp" %>

  <%@include file="rolFormButtons.jsp" %>

  <c:if test="${not empty rolForm.sections}">
     <c:set var="__basename" value="rol" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${rolForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/rolFormModificable.jsp" %>
  </c:if>

</form:form>


