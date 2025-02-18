<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(funcionariForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(funcionariForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty funcionariForm.titleCode}">
    <fmt:message key="${funcionariForm.titleCode}" >
      <fmt:param value="${funcionariForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty funcionariForm.entityNameCode}">
      <fmt:message var="entityname" key="funcionari.funcionari"/>
    </c:if>
    <c:if test="${not empty funcionariForm.entityNameCode}">
      <fmt:message var="entityname" key="${funcionariForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${funcionariForm.nou?'genapp.createtitle':(funcionariForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty funcionariForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(funcionariForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(funcionariForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${funcionariForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>