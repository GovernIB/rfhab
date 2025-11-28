<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(habilitacioForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(habilitacioForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty habilitacioForm.titleCode}">
    <fmt:message key="${habilitacioForm.titleCode}" >
      <fmt:param value="${habilitacioForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty habilitacioForm.entityNameCode}">
      <fmt:message var="entityname" key="habilitacio.habilitacio"/>
    </c:if>
    <c:if test="${not empty habilitacioForm.entityNameCode}">
      <fmt:message var="entityname" key="${habilitacioForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${habilitacioForm.nou?'genapp.createtitle':(habilitacioForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty habilitacioForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(habilitacioForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(habilitacioForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${habilitacioForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>