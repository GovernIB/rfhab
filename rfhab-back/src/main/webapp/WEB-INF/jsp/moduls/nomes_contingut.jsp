<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include	file="/WEB-INF/jsp/moduls/includes.jsp"%>

<tiles:importAttribute name="contingut" />

<div class="row">

    <div id="contingut" class="col-12">

		<!--  Missatges  -->
		<jsp:include page="/WEB-INF/jsp/moduls/missatges.jsp" />

		<!-- Contingut de la pagina -->
		<tiles:insertAttribute name="contingut">
		</tiles:insertAttribute>

		<!-- FINAL DIV CONTINGUT -->
	</div>

	<div class="clearfix"></div>

</div>

