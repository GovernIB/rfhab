
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="unitatForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="unitatFormTitle.jsp" %>
 
  <c:set var="contexte" value="${unitatForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="unitatFormCorePre.jsp" %>

  <%@include file="unitatFormCore.jsp" %>

  <%@include file="unitatFormCorePost.jsp" %>

  <%@include file="unitatFormButtons.jsp" %>

  <c:if test="${not empty unitatForm.sections}">
     <c:set var="__basename" value="unitat" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${unitatForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/unitatFormModificable.jsp" %>
  </c:if>

</form:form>


