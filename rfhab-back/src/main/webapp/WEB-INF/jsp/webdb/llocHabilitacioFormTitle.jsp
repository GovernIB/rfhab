<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(llocHabilitacioForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(llocHabilitacioForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty llocHabilitacioForm.titleCode}">
    <fmt:message key="${llocHabilitacioForm.titleCode}" >
      <fmt:param value="${llocHabilitacioForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty llocHabilitacioForm.entityNameCode}">
      <fmt:message var="entityname" key="llocHabilitacio.llocHabilitacio"/>
    </c:if>
    <c:if test="${not empty llocHabilitacioForm.entityNameCode}">
      <fmt:message var="entityname" key="${llocHabilitacioForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${llocHabilitacioForm.nou?'genapp.createtitle':(llocHabilitacioForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty llocHabilitacioForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(llocHabilitacioForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(llocHabilitacioForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${llocHabilitacioForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>