
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="activitatForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="activitatFormTitle.jsp" %>
 
  <c:set var="contexte" value="${activitatForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="activitatFormCorePre.jsp" %>

  <%@include file="activitatFormCore.jsp" %>

  <%@include file="activitatFormCorePost.jsp" %>

  <%@include file="activitatFormButtons.jsp" %>

  <c:if test="${not empty activitatForm.sections}">
     <c:set var="__basename" value="activitat" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${activitatForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/activitatFormModificable.jsp" %>
  </c:if>

</form:form>


