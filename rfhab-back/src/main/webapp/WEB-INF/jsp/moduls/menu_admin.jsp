<%@ page contentType="text/html;charset=UTF-8" language="java"
%><%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<c:set var="url" value="${urlActual}" />
<div>
  <h5>Menú</h5>
  <ul class="tree" style="margin: 3px; padding: 0px;">

	<!--  Funcionaris  -->
	<li style="list-style-type: disc; list-style-position: inside;">
      <a href="<c:url value="/admin/funcionari/list"/>">
        <span style="${(fn:contains(url, 'funcionari'))? "font-weight: bold;" : ""}"><fmt:message key="menu.admin.funcionaris" /></span>
      </a>
    </li>
    
	<!--  Historic de funcionaris -->
	<li style="list-style-type: disc; list-style-position: inside;">
      <a href="<c:url value="/admin/historic/list/"/>">
        <span style="${(fn:contains(url, 'historicFuncionaris'))? "font-weight: bold;" : ""}">Històric de canvis d'un funcionari</span>
      </a>
    </li>
    
	<!--  Activitat d'un funcionari -->
	<li style="list-style-type: disc; list-style-position: inside;">
      <a href="<c:url value="/admin/activitat/list"/>">
        <span style="${(fn:contains(url, 'activitat'))? "font-weight: bold;" : ""}">Activitats</span>
      </a>
    </li>
	
	
	<hr  style="margin-top: 6px;  margin-bottom: 6px;" />
	
	<!--  Autoritzacions -->
	<li style="list-style-type: disc; list-style-position: inside;">
      <a href="<c:url value="/admin/autoritzacio/list"/>">
        <span style="${(fn:contains(url, 'autoritzacio'))? "font-weight: bold;" : ""}">Autoritzacions</span>
      </a>
    </li>

  </ul>
</div>

