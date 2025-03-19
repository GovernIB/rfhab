<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<div class="col-12">
	<ul class="nav nav-tabs" id="myTab" role="tablist"
		style="margin-bottom: 20px;">
		<li class="nav-item"><a class="nav-link active" id="home-tab"
			data-toggle="tab" href="#home" role="tab" aria-controls="home"
			aria-selected="true"><fmt:message key="lloc.admin.funcionari"/>x</a></li>
		<li class="nav-item"><a class="nav-link" id="rols-tab"
			data-toggle="tab" href="#rols" role="tab" aria-controls="rols"
			aria-selected="false">Rols</a></li>
		<li class="nav-item"><a class="nav-link" id="autoritzacio-tab"
			data-toggle="tab" href="#autoritzacio" role="tab"
			aria-controls="autoritzacio" aria-selected="false">Autoritzacions</a>
		</li>
		<li class="nav-item"><a class="nav-link" id="historic-tab"
			data-toggle="tab" href="#historic" role="tab"
			aria-controls="historic" aria-selected="false">Històric</a></li>
	</ul>
	<div class="tab-content" id="myTabContent">
		<div class="tab-pane fade show active" id="home" role="tabpanel"
			aria-labelledby="home-tab">


		</div>
		<div class="tab-pane fade" id="rols" role="tabpanel"
			aria-labelledby="rols-tab">

			<c:if test="${rolsFuncionari.isEmpty()}">
				<div class="alert alert-warning" role="alert">Aquest funcionari no té cap rol assignat</div>
			</c:if>
			<c:if test="${not rolsFuncionari.isEmpty()}">
				<div class="row" style="margin-left: 0px;">
					<table
						class="table table-sm table-bordered table-striped table-genapp table-genapp-list"
						style="width: auto;">
						<thead>
							<tr>
								<th>CODI</th>
								<th>NOMID</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="rol" items="${rolItems}">
								<tr id="rol_rowid_${rol.rolID}">
									<td>${rol.codi}</td>
									<td><c:set var="tmp">${rol.nomID}</c:set> <c:if
											test="${not empty tmp}">${rol.nom.traduccions[lang].valor}</c:if>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</c:if>
			<p>
				<a class="btn btn-primary btn-sm"
					href="<c:url value="/admin/funcionarirol/assignar/${funcionari.funcionariID}"/>">Assignar nou rol</a>
			</p>
		</div>
		
		<div class="tab-pane fade" id="autoritzacio" role="tabpanel"
			aria-labelledby="autoritzacio-tab">

			<c:if test="${isOamr}">
				<p>És personal OAMR</p>
			</c:if>

			<c:if test="${not empty autoritzacioItems}">
				<div class="row" style="margin-left: 0px;">
					<table
						class="table table-sm table-bordered table-striped table-genapp table-genapp-list"
						style="width: auto;">
						<thead>
							<tr>
								<th>DATACREACIO</th>
								<th>NUMEROCAI</th>
								<th>PLAÇA</th>
								<th>PROCEDIMENT</th>
								<th>CODISIA</th>
								<th>DATAINICI</th>
								<th>DATAFI</th>
								<th>&nbsp;</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${autoritzacioItems}" var="autoritzacio">
								<tr id="autoritzacio_rowid_${autoritzacio.autoritzacioID}">
									<td><fmt:formatDate pattern="${gen:getDateTimePattern()}"
											value="${autoritzacio.dataCreacio}" /></td>
									<td>${autoritzacio.cai}</td>
									<td>${autoritzacio.llocID}</td>
									<td>${autoritzacio.procediment}</td>
									<td>${autoritzacio.codiSia}</td>
									<td><fmt:formatDate pattern="${gen:getDateTimePattern()}"
											value="${autoritzacio.dataInici}" /></td>
									<td><fmt:formatDate pattern="${gen:getDateTimePattern()}"
											value="${autoritzacio.dataFi}" /></td>
									<td><a
										href="<c:url value="/admin/autoritzacio/view/${autoritzacio.autoritzacioID}"/>"
										class="btn btn-primary btn-sm"><i class="far fa-eye"
											title="Veure detall"></i></a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</c:if>

			<p>
				<a class="btn btn-primary btn-sm"
					href="<c:url value="/admin/autoritzacio/assignar/${funcionari.funcionariID}"/>">Autoritzar</a>
			</p>
		</div>
		<div class="tab-pane fade" id="historic" role="tabpanel"
			aria-labelledby="historic-tab">

			<c:if test="${historic.isEmpty()}">
	<div class="alert alert-warning" role="alert" style="margin-top: 20px;">No
		hi ha cap registre històric per a aquest lloc.</div>
</c:if>

<c:if test="${ not isNew && not historic.isEmpty()}">
	<div style="margin-top: 20px;">
		<h5>Històric de canvis:</h5>

		<table
			class="table table-sm table-bordered table-striped table-genapp table-genapp-list"
			style="width: auto;">
			<thead>
				<tr>
					<th>Data</th>
					<th>CAI</th>
					<th>Usuari</th>
					<th>&nbsp;</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="h" items="${historic}">
					<tr>
						<td>${h.value6}</td>
						<td>${h.value2}</td>
						<td>${h.value3}&nbsp;${h.value4}&nbsp;${h.value5}</td>
						<td><a href="<c:url value="/admin/historiclloc/view/${h.value1}"/>" class="btn btn-secondary"><i
								class="far fa-eye" title="Veure detall"></i></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</c:if>

		</div>
	</div>
</div>


<script type="text/javascript">
	document
			.addEventListener(
					"DOMContentLoaded",
					function(event) {

						document.getElementById("lloc.codiLloc").placeholder = "PFH_XXXXXXX";

						if ("true" != "${isView}") {

							var nouTr = document.createElement("tr");
							nouTr.id = "lloc_numerocai_rowid";

							var nouTd1 = document.createElement("td");
							nouTd1.id = "lloc_numerocai_columnlabelid";
							nouTd1.innerHTML = "<label>Número CAI (*)</label>";
							nouTr.appendChild(nouTd1);

							var nouTd2 = document.createElement("td");
							nouTd2.id = "lloc_numerocai_columnvalueid";
							nouTd2.innerHTML = '<input type="text" name="numerocai" id="numerocai" class="form-control w-75"></input>';
							nouTr.appendChild(nouTd2);

							var taula = document.getElementById("lloc_tableid");
							taula.appendChild(nouTr);

						}

					});
</script>