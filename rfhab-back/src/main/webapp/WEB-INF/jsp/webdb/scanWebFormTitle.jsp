<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(scanWebForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(scanWebForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty scanWebForm.titleCode}">
    <fmt:message key="${scanWebForm.titleCode}" >
      <fmt:param value="${scanWebForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty scanWebForm.entityNameCode}">
      <fmt:message var="entityname" key="scanWeb.scanWeb"/>
    </c:if>
    <c:if test="${not empty scanWebForm.entityNameCode}">
      <fmt:message var="entityname" key="${scanWebForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${scanWebForm.nou?'genapp.createtitle':(scanWebForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty scanWebForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(scanWebForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(scanWebForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${scanWebForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>