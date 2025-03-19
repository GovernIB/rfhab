<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(llocRolForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(llocRolForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty llocRolForm.titleCode}">
    <fmt:message key="${llocRolForm.titleCode}" >
      <fmt:param value="${llocRolForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty llocRolForm.entityNameCode}">
      <fmt:message var="entityname" key="llocRol.llocRol"/>
    </c:if>
    <c:if test="${not empty llocRolForm.entityNameCode}">
      <fmt:message var="entityname" key="${llocRolForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${llocRolForm.nou?'genapp.createtitle':(llocRolForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty llocRolForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(llocRolForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(llocRolForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${llocRolForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>