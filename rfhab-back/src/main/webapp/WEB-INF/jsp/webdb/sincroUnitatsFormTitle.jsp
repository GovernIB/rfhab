<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(sincroUnitatsForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(sincroUnitatsForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty sincroUnitatsForm.titleCode}">
    <fmt:message key="${sincroUnitatsForm.titleCode}" >
      <fmt:param value="${sincroUnitatsForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty sincroUnitatsForm.entityNameCode}">
      <fmt:message var="entityname" key="sincroUnitats.sincroUnitats"/>
    </c:if>
    <c:if test="${not empty sincroUnitatsForm.entityNameCode}">
      <fmt:message var="entityname" key="${sincroUnitatsForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${sincroUnitatsForm.nou?'genapp.createtitle':(sincroUnitatsForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty sincroUnitatsForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(sincroUnitatsForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(sincroUnitatsForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${sincroUnitatsForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>