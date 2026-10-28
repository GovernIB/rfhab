<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<span class="llistaformularititol">
 <c:choose>
  <c:when test="${fn:startsWith(funcionariLlocForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(funcionariLlocForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty funcionariLlocForm.titleCode}">
    <fmt:message key="${funcionariLlocForm.titleCode}" >
      <fmt:param value="${funcionariLlocForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty funcionariLlocForm.entityNameCode}">
      <fmt:message var="entityname" key="funcionariLloc.funcionariLloc"/>
    </c:if>
    <c:if test="${not empty funcionariLlocForm.entityNameCode}">
      <fmt:message var="entityname" key="${funcionariLlocForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${funcionariLlocForm.nou?'genapp.createtitle':(funcionariLlocForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></span>
  <c:if test="${not empty funcionariLlocForm.subTitleCode}">
<span class="llistaformularisubtitol">
<c:set var="subtitleTranslated" value="${fn:startsWith(funcionariLlocForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(funcionariLlocForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${funcionariLlocForm.subTitleCode}" />
</c:if>
</span>
  </c:if>
</div>