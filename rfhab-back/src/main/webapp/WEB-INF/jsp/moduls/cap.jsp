<%@page import="es.caib.rfhab.persistence.EntitatJPA"%>
<%@page import="java.util.Map"%>
<%@page import="org.springframework.context.i18n.LocaleContextHolder"%>
<%@page import="es.caib.rfhab.commons.utils.Version"%>
<%@page import="es.caib.rfhab.back.security.LoginInfo"%>
<%@page import="java.util.Locale"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<c:set var="url" value="${urlActual}" />

<header>
	<!-- Header -->
	<nav id="nav-cap" class="navbar navbar-expand-md navbar-dark">

		<button class="navbar-toggler botoMobil" type="button"
			data-toggle="collapse" data-target="#navbarCollapse"
			aria-controls="navbarCollapse" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<!-- Logo i nom aplicació -->
		<div id="logoEntitatContainer" class="logoEntitat">
			<a href="https://governdigital.fundaciobit.org/rfhabback"> <img
				src="<c:url value="/img/logo-caib.png"/>"
				alt="Govern de les Illes Balears" style="height: 55px;" />
			</a>
		</div>

		<div id="logoRfhabContainer" class="logoEntitat">
			<img src="<c:url value="/img/app-logo.png"/>" style="height: 4rem;"
				alt="RFHab" title="RFHab" />
		</div>

		<%
		Version version = new Version();

		LoginInfo loginInfo = LoginInfo.getInstance();
		Map<Long, EntitatJPA> entitats = loginInfo.getEntitats();

		System.out.println("Entitats: " + entitats.size());
		%>

		<div id="menuCapContainer">

			<ul class="navbar-nav mobil">

				<%-- ENTITAT DE L'USUARI --%>

				<li id="entitatInfoContainer" class="menuCapItem dropdown"><i
					class="fas fa-university"></i> <span class="dropdown-toggle"
					type="button" id="dropdownMenuEntitat" data-toggle="dropdown"
					aria-haspopup="true" aria-expanded="false">
						<%
						if (loginInfo.getEntitatIDActual() != null) {
						%><%=entitats.get(loginInfo.getEntitatIDActual()).getNom()%>
						<%
						} else {
						%>Null<%
						}
						%>
				</span>

					<div class="dropdown-menu dropdown-menu-right"
						aria-labelledby="dropdownMenuEntitat">

						<%
						if (entitats != null && !entitats.isEmpty()) {
							for (Long key : entitats.keySet()) {
						%>
						<a class="dropdown-item"
							href="<%=request.getContextPath() + "/common/canviarEntitat/" + key%>"><%=entitats.get(key).getNom()%></a>
						<%
						}
						}
						%>

					</div></li>

				<%--  PIPELLES SEGONS EL ROL DE L'USUARI --%>

				<c:if test="${rfh:hasRole('ROLE_ADMIN')}">
					<li id="rolInfoContainer" class="menuCapItem dropdown"
						onclick="location='<c:url value="/canviarPipella/admin"/>'">
						<i class="fas fa-address-card"></i> <span class="dropdown-toggle"
						type="button" id="dropdownMenuRol" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false"> <c:if
								test="${not empty pipella}">
								<fmt:message key="rol.${pipella}" />
							</c:if> <c:if test="${empty pipella}">
								<fmt:message key="inici" />
							</c:if>
					</span>
						<div class="dropdown-menu dropdown-menu-right"
							aria-labelledby="dropdownMenuRol">
							<sec:authorize access="hasRole('ROLE_ADMIN')">
								<c:if test="${pipella ne 'admin'}">
									<a class="dropdown-item"
										href="<c:url value="/canviarPipella/admin"/>"><fmt:message
											key="rol.admin" /></a>
								</c:if>
							</sec:authorize>
							<sec:authorize access="hasRole('ROLE_USER')">
								<c:if test="${pipella ne 'user'}">
									<a class="dropdown-item"
										href="<c:url value="/canviarPipella/user"/>"><fmt:message
											key="rol.user" /></a>
								</c:if>
							</sec:authorize>

							<sec:authorize access="hasRole('ROLE_SUPER')">
								<c:if test="${pipella ne 'super'}">
									<a class="dropdown-item"
										href="<c:url value="/canviarPipella/super"/>"><fmt:message
											key="rol.super" /></a>
								</c:if>
							</sec:authorize>

							<sec:authorize access="hasRole('ROLE_ADMIN')">
								<c:if test="${pipella ne 'webdb'}">
									<a class="dropdown-item"
										href="<c:url value="/canviarPipella/webdb"/>"><fmt:message
											key="rol.webdb" /></a>
								</c:if>
							</sec:authorize>
						</div>
					</li>
				</c:if>

				<%--  CONFIGURACIÓ DE L'USUARI AMB MENU D'IDIOMES  --%>

				<li id="userInfoContainer" class="menuCapItem dropdown"><i
					class="fa fa-user"></i> <span class="dropdown-toggle" type="button"
					id="dropdownMenuUser" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false"> <%=loginInfo.getUsuariPersona().getNom() + " " + loginInfo.getUsuariPersona().getLlinatge1() + " ("
		+ loginInfo.getUsuariPersona().getUsername() + ")"%>
				</span>

					<div class="dropdown-menu  dropdown-menu-right"
						aria-labelledby="dropdownMenuUser">

						<c:if test="${empty loginInfo}">
							<a class="dropdown-item"
								href="<c:url value="/common/principal.html"></c:url>"> <i
								class="fas fa-sign-in-alt"></i> Login
							</a>
						</c:if>

						<c:if test="${not empty loginInfo}">

							<c:if test="${pipella eq 'super'}">
								<a class="dropdown-item"
									href="<c:url value="/superadmin/usuari/${loginInfo.getUsuariPersona().getUsuariID()}/edit"></c:url>">
									<fmt:message key="menu.usuari" />
								</a>
							</c:if>
							<c:if test="${pipella ne 'super'}">
								<a class="dropdown-item"
									href="<c:url value="/${pipella}/usuari/${loginInfo.getUsuariPersona().getUsuariID()}/edit"></c:url>">
									<fmt:message key="menu.usuari" />
								</a>
							</c:if>

							<hr style="margin: 6px 6px;" />

							<div id="titol-idiomes" class="dropdown-item">
								<fmt:message key="idiomes" />
							</div>

							<c:forEach var="idioma" items="${idiomes}" varStatus="status">
								<c:set var="idiomaID" value="${idioma.idiomaID}" />
								<a class="dropdown-item" href="?lang=${idiomaID}"> <img
									src="<c:url value="/img/${idiomaID}_petit_${lang eq idiomaID? 'on' : 'off'}.gif"/>"
									alt="${idiomaID}" style="margin-right: 0.5rem;" width="17"
									height="14" border="0" />${idioma.nom}
								</a>
							</c:forEach>


							<a class="dropdown-item"
								href="<c:url value="/logout"></c:url>"> <i
								class="fas fa-sign-out-alt"></i> <fmt:message key="sortir" />
							</a>

						</c:if>

					</div></li>
			</ul>
		</div>
		<!-- Fi Logo i nom aplicació -->

		<!-- Botons -->
		<c:if test="${not empty pipella}">
		<div id="botoneraCapContainer" class="collapse navbar-collapse"
			id="navbarCollapse">

			<ul class="navbar-nav mobil">

				<li class="dropdown">
					<button class="btn btn-secondary dropdown-toggle" type="button"
						id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false">
						<i class="fas fa-bars"></i>
						<fmt:message key="menu" />
					</button>
					<div class="dropdown-menu dropdown-menu-right"
						aria-labelledby="dropdownMenu1">

						<%-- MENU ADMINISTRADOR ENTITATS --%>
						<sec:authorize access="hasRole('ROLE_ADMIN')">
							<c:if test="${fn:contains(url, '/admin/')}">

								<a class="dropdown-item"
									href="<c:url value="/admin/funcionari/list"/>"> <i
									class="fa fa-user"></i> <fmt:message
										key="menu.admin.funcionaris" />
								</a>
								<a class="dropdown-item"
									href="<c:url value="/admin/lloc/list"/>"> <i
									class="fas fa-desktop"></i> <fmt:message
										key="menu.admin.places" />
								</a>
								<a class="dropdown-item" href="<c:url value="/admin/rol/list"/>">
									<i class="fas fa-key"></i> <fmt:message key="menu.admin.rols" />
								</a>

							</c:if>
						</sec:authorize>

						<%-- MENU ADMINISTRADOR ENTITATS --%>
						<sec:authorize access="hasRole('ROLE_SUPER')">
							<c:if test="${fn:contains(url, '/superadmin/')}">
								<a class="dropdown-item"
									href="<c:url value="/superadmin/unitat/list"/>"> <i
									class="fas fa-sitemap"></i> <fmt:message
										key="menu.super.unitats" />
								</a>
								<a class="dropdown-item"
									href="<c:url value="/superadmin/entitat/list"/>"> <i
									class="far fa-building"></i> <fmt:message
										key="menu.super.entitats" />
								</a>
								<a class="dropdown-item"
									href="<c:url value="/superadmin/usuari/list"/>"> <i
									class="fas fa-users"></i> <fmt:message key="menu.super.usuaris" />
								</a>
								<a class="dropdown-item"
									href="<c:url value="/superadmin/plugin/list"/>"> <i
									class="fas fa-plug"></i> <fmt:message key="menu.super.plugins" />
								</a>
							</c:if>
						</sec:authorize>
					</div>
				</li>
			</ul>
			<!-- FI Botons -->
		</div>
		</c:if>
	</nav>
</header>
<!-- FI Header -->

<!-- CAPÇALERA MODERNA -->
<script type="text/javascript">
	$('.subtitolMay').css('font-size', '1rem !important');
</script>

<style>
header {
	/* 	position: fixed;
	top: 0;
	left: 0;
	right: 0;
	bottom: 5rem;
	height: 7rem; */
	z-index: 10;
	background-color: #fff;
	margin-bottom: 2rem;
}

#nav-cap {
	box-shadow: 0 .5rem 1rem rgba(0, 0, 0, .15);
	padding: 0 5rem;
	height: 6rem;
}

.subtitolMay {
	font-size: 1rem;
}

#menu_i_contingut {
	padding: 0rem 8rem;
	/* 	padding-top: 8rem; */
}

#logoEntitatContainer {
	border-right: 1px solid black;
}

#botoneraCapContainer {
	margin-top: 2.5rem;
	/*   margin-bottom: 1rem; */
}

#botoneraCapContainer button {
	padding: 0.2rem 0.65rem;
}

#menuCapContainer {
	position: absolute;
	top: 11px;
	right: 88px;
	display: flex;
	color: black;
}

.menuCapItem span {
	color: black;
	text-transform: uppercase;
	margin-left: 4px;
}

#menuCapContainer li {
	padding: 0 1rem;
	border-right: 1px solid black;
	cursor: pointer;
}

#menuCapContainer li:last-child {
	border: none;
	padding-right: 0px;
}

h1 {
	color: darkgreen;
}

#FilterButton, #GroupButton {
	background-color: #E1E1E1;
	border-color: #E1E1E1;
	margin-left: 3px;
}

#FilterButton:hover, #GroupButton:hover {
	background-color: #A1A1A1;
	border-color: #A1A1A1;
}

.dropdown .btn-secondary {
	margin: 0 5px;
}

.dropdown-menu {
	margin-top: 0px;
}

#titol-idiomes {
	color: #314b87;
	font-weight: bold;
}

#titol-idiomes:hover {
	background-color: transparent;
}

#nomApp {
	text-transform: uppercase;
	font-size: 2rem;
	margin: 0;
	font-family: 'Montserrat', serif;
	color: black;
}
</style>


<script>
	function cridarOpcioMenu(menuID, tipus) {
		window.location = '<c:url value="/user/menu/show/"/>' + menuID + '/'
				+ tipus + '/' + btoa(window.location);
	}
</script>

