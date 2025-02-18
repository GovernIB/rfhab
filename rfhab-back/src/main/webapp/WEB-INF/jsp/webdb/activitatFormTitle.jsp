<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(activitatForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(activitatForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty activitatForm.titleCode}">
    <fmt:message key="${activitatForm.titleCode}" >
      <fmt:param value="${activitatForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty activitatForm.entityNameCode}">
      <fmt:message var="entityname" key="activitat.activitat"/>
    </c:if>
    <c:if test="${not empty activitatForm.entityNameCode}">
      <fmt:message var="entityname" key="${activitatForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${activitatForm.nou?'genapp.createtitle':(activitatForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty activitatForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(activitatForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(activitatForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${activitatForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>