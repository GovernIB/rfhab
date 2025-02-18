<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(unitatForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(unitatForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty unitatForm.titleCode}">
    <fmt:message key="${unitatForm.titleCode}" >
      <fmt:param value="${unitatForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty unitatForm.entityNameCode}">
      <fmt:message var="entityname" key="unitat.unitat"/>
    </c:if>
    <c:if test="${not empty unitatForm.entityNameCode}">
      <fmt:message var="entityname" key="${unitatForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${unitatForm.nou?'genapp.createtitle':(unitatForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty unitatForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(unitatForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(unitatForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${unitatForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>