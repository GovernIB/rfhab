<%@page import="es.caib.rfhab.back.security.LoginInfo"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%><%@include
	file="/WEB-INF/jsp/moduls/includes.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
	xml:lang="<c:out value="${pageContext.response.locale.language}"/>"
	lang="<c:out value="${pageContext.response.locale.language}"/>">
<head>

<c:if test="${loginInfo.needConfigUser}">
	<%
	LoginInfo.getInstance().setNeedConfigUser(false);
	%>

	<c:redirect url="/usuari/nou/${loginInfo.usuariPersona.usuariID}/check" />
</c:if>

<%@ include file="/WEB-INF/jsp/moduls/imports.jsp"%>
</head>
<body class="header-content-footer">

	<!--  INICI CAPÇALERA -->

	<tiles:insertAttribute name="cap">
		<tiles:putAttribute name="data" value="${data}" />
	</tiles:insertAttribute>


	<!--  PIPELLES -->
	<main id="main-content">
	<div class="row-fluid container main" style="max-width: none;">

		<ul class="nav nav-tabs custom-submenu">

			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<li class="nav-item"><a
					class="nav-link ${(pipella eq 'admin')?'active' : '' }"
					href="<c:url value="/canviarPipella/admin"/>"><fmt:message
							key="rol.admin" /></a></li>
			</sec:authorize>

			<sec:authorize access="hasRole('ROLE_SUPER')">
				<li class="nav-item"><a
					class="nav-link ${(pipella eq 'super')?'active' : '' }"
					href="<c:url value="/canviarPipella/super"/>"><fmt:message
							key="rol.super" /></a></li>
			</sec:authorize>

			<sec:authorize access="hasRole('ROLE_SUPER')">
				<li class="nav-item"><a
					class="nav-link ${(pipella eq 'webdb')?'active' : '' }"
					href="<c:url value="/canviarPipella/webdb"/>"><fmt:message
							key="rol.webdb" /></a></a></li>
			</sec:authorize>

			<sec:authorize access="hasRole('ROLE_SUPER')">
				<c:if test="${rfh:isDesenvolupament()}">
					<li class="nav-item"><a
						class="nav-link ${(pipella eq 'desenvolupament')?'active' : '' }"
						href="<c:url value="/canviarPipella/desenvolupament"/>"> <fmt:message
								key="desenvolupament" />
					</a></li>
				</c:if>
			</sec:authorize>

		</ul>

		<%-- INICI MENU + CONTINGUT --%>
		<div class="well well-white" style="padding: 10px">
			<tiles:insertAttribute name="menu_i_contingut">
				<tiles:putAttribute name="menu" value="${menu_tile}" />
				<tiles:putAttribute name="contingut" value="${contingut_tile}" />
			</tiles:insertAttribute>
			<%-- FINAL MENU + CONTINGUT --%>
		</div>

		<%-- FINAL DIV PIPELLES --%>
	</div>
	</main>


	<tiles:insertAttribute name="peu"></tiles:insertAttribute>

</body>
</html>
