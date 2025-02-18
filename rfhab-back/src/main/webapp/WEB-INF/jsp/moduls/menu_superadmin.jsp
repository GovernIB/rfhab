<%@ page contentType="text/html;charset=UTF-8" language="java"
%><%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<c:set var="url" value="${urlActual}" />
<div>
  <h5>Menú</h5>
  <ul class="tree" style="margin: 3px; padding: 0px;">

	<li style="list-style-type: disc; list-style-position: inside;">
      <a href="<c:url value="/superadmin/unitat/list"/>">
        <span style="${(fn:contains(url, 'superadmin/unitat'))? "font-weight: bold;" : ""}"><fmt:message key="menu.super.unitats" /></span>
      </a>
    </li>

	<li style="list-style-type: disc; list-style-position: inside;">
      <a href="<c:url value="/superadmin/entitat/list"/>">
        <span style="${(fn:contains(url, 'superadmin/entitat'))? "font-weight: bold;" : ""}"><fmt:message key="menu.super.entitats" /></span>
      </a>
    </li>
    
    <li style="list-style-type: disc; list-style-position: inside;">
      <a href="<c:url value="/superadmin/usuari/list"/>">
        <span style="${(fn:contains(url, 'superadmin/usuari'))? "font-weight: bold;" : ""}"><fmt:message key="menu.super.usuaris" /></span>
      </a>
    </li>
    
    <li style="list-style-type: disc; list-style-position: inside;">
      <a href="<c:url value="/superadmin/idioma/list"/>">
        <span style="${(fn:contains(url, 'superadmin/idioma'))? "font-weight: bold;" : ""}"><fmt:message key="menu.super.idiomes" /></span>
      </a>
    </li>
    
    <li style="list-style-type: disc; list-style-position: inside;">
      <a href="<c:url value="/superadmin/plugin/list"/>">
        <span style="${(fn:contains(url, 'superadmin/plugin'))? "font-weight: bold;" : ""}"><fmt:message key="menu.super.plugins" /></span>
      </a>
    </li>
   
  </ul>
</div>

