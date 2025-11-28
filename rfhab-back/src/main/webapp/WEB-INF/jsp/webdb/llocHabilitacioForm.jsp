
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="llocHabilitacioForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="llocHabilitacioFormTitle.jsp" %>
 
  <c:set var="contexte" value="${llocHabilitacioForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="llocHabilitacioFormCorePre.jsp" %>

  <%@include file="llocHabilitacioFormCore.jsp" %>

  <%@include file="llocHabilitacioFormCorePost.jsp" %>

  <%@include file="llocHabilitacioFormButtons.jsp" %>

  <c:if test="${not empty llocHabilitacioForm.sections}">
     <c:set var="__basename" value="llocHabilitacio" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${llocHabilitacioForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/llocHabilitacioFormModificable.jsp" %>
  </c:if>

</form:form>


