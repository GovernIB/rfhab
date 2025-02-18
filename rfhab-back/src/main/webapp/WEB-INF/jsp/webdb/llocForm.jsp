
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="llocForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="llocFormTitle.jsp" %>
 
  <c:set var="contexte" value="${llocForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="llocFormCorePre.jsp" %>

  <%@include file="llocFormCore.jsp" %>

  <%@include file="llocFormCorePost.jsp" %>

  <%@include file="llocFormButtons.jsp" %>

  <c:if test="${not empty llocForm.sections}">
     <c:set var="__basename" value="lloc" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${llocForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/llocFormModificable.jsp" %>
  </c:if>

</form:form>


