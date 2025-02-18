
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="funcionariForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="funcionariFormTitle.jsp" %>
 
  <c:set var="contexte" value="${funcionariForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="funcionariFormCorePre.jsp" %>

  <%@include file="funcionariFormCore.jsp" %>

  <%@include file="funcionariFormCorePost.jsp" %>

  <%@include file="funcionariFormButtons.jsp" %>

  <c:if test="${not empty funcionariForm.sections}">
     <c:set var="__basename" value="funcionari" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${funcionariForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/funcionariFormModificable.jsp" %>
  </c:if>

</form:form>


