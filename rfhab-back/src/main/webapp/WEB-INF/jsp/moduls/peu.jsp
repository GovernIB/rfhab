<%@page import="es.caib.rfhab.logic.utils.LogicUtils"%>
<%@page import="es.caib.rfhab.commons.utils.Configuracio"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<footer id="footer">

		<!-- Esquerra -->
		<div id="peu-esquerra">
			<strong class="font-weight-bold h6"> ${versio.projectName}
				v${versio.version}<%=Configuracio.isCAIB() ? "-caib" : ""%>

			</strong> <br /> <small> Build: ${versio.buildTime} <br /> JDK: ${version.jdkVersion} <br />
			<fmt:message key="revisio" />: 
			<c:if test="${empty versio.scmRevision}">
				<fmt:message key="scmversion.msg" />
			</c:if>
			<c:if test="${not empty versio.scmRevision}">${versio.scmRevision}</c:if>
			<br />
			<span style="padding-top: 2px">
			 <i><fmt:message key="desenvolupatper" /></i></span>
			 </small>
		</div>


		<!-- Dreta -->
		<div id="peu-dreta" >
			<a href="https://www.caib.es/" target="_blank"> <img
				src="<c:url value="/img/app-logo-bn.png"/>" 
				style="height: 35px;" 
				alt="Govern de les Illes Balears" />
			</a> <br />

			<!-- Button to trigger modal -->
			<small><a href="#modalAjuda" role="button"
				data-toggle="modal"><fmt:message key="ajuda.necessitau" /></a></small>
		</div>


    <!-- Modal -->
    <div id="modalAjuda" class="modal" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="modal-title h5">
                        <fmt:message key="ajuda.titol" />
                    </div>
                    <button type="button" class="close"
                        data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <p>
                        <fmt:message key="ajuda.missatge" />
                    </p>
                    <ul>
                        <li><fmt:message key="ajuda.viatelefon" /><%=Configuracio.getAjudaViaTelefon()%></li>
                        <li><fmt:message key="ajuda.viaweb" /><%=Configuracio.getAjudaViaWeb()%></li>
                        <li><fmt:message key="ajuda.viaemail" />
                            <a href="mailto: help@help.hl"><%=Configuracio.getAjudaViaEmail()%></a>
                        </li>
                    </ul>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary"
                        data-dismiss="modal">
                        <fmt:message key="tancar" />
                    </button>
                </div>
            </div>
        </div>
    </div>
</footer>
<style>
#footer {
	padding: 6px 6rem 6px;
	background-color: #4d4d4d;
	color: white;
	display: flex;
	justify-content: space-between;
	align-items: center;
	
	margin-top: auto;
}

#peu-dreta {
	display: flex;
	flex-direction: column;
	text-align: right;
	gap: 3px;
	width: 25rem;
}

#peu-esquerra {
	width: 25rem;
}

#peu-esquerra small,
#peu-esquerra small *,
#peu-dreta small,
#peu-dreta small *,
#peu-dreta a,
#peu-dreta a:hover,
#peu-dreta a:focus,
#peu-dreta a:active {
	color: #ffffff !important;
}

#modalAjuda {
	color: black;
}
</style>