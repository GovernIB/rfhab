<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(funcionariRolForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(funcionariRolForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty funcionariRolForm.titleCode}">
    <fmt:message key="${funcionariRolForm.titleCode}" >
      <fmt:param value="${funcionariRolForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty funcionariRolForm.entityNameCode}">
      <fmt:message var="entityname" key="funcionariRol.funcionariRol"/>
    </c:if>
    <c:if test="${not empty funcionariRolForm.entityNameCode}">
      <fmt:message var="entityname" key="${funcionariRolForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${funcionariRolForm.nou?'genapp.createtitle':(funcionariRolForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty funcionariRolForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(funcionariRolForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(funcionariRolForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${funcionariRolForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>