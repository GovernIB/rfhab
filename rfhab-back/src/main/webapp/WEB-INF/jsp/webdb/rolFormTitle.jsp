<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(rolForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(rolForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty rolForm.titleCode}">
    <fmt:message key="${rolForm.titleCode}" >
      <fmt:param value="${rolForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty rolForm.entityNameCode}">
      <fmt:message var="entityname" key="rol.rol"/>
    </c:if>
    <c:if test="${not empty rolForm.entityNameCode}">
      <fmt:message var="entityname" key="${rolForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${rolForm.nou?'genapp.createtitle':(rolForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty rolForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(rolForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(rolForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${rolForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>