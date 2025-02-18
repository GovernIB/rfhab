<div class="col-12">
	<ul class="nav nav-tabs" id="myTab" role="tablist"
		style="margin-bottom: 20px;">
		<li class="nav-item"><a class="nav-link active" id="home-tab"
			data-toggle="tab" href="#home" role="tab" aria-controls="home"
			aria-selected="true">Plaça</a></li>
		<li class="nav-item"><a class="nav-link" id="rols-tab"
			data-toggle="tab" href="#rols" role="tab" aria-controls="rols"
			aria-selected="false">Rols</a></li>
		<li class="nav-item"><a class="nav-link" id="activitat-tab"
			data-toggle="tab" href="#activitat" role="tab"
			aria-controls="activitat" aria-selected="false">Activitat</a></li>
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

			<c:if test="${not llocItems.isEmpty()}">
				<div class="row" style="margin-left: 12px;">
					<table
						class="table table-sm table-bordered table-striped table-genapp table-genapp-list"
						style="width: auto;">
						<thead>
							<tr>
								<th>LLOCID</th>
								<th>CODILLOC</th>
								<th>NOM</th>
								<th>PERSONALOAMR</th>
								<th>DATABAIXA</th>
								<th>&nbsp;</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="lloc" items="${llocItems}">
								<tr id="lloc_rowid_${lloc.llocID}">
									<td>${lloc.llocID}</td>
									<td>${lloc.codiLloc}</td>
									<td>${lloc.nom}</td>
									<td align="center"><c:if test="${lloc.personalOamr > 0}">
											<i class="fa fa-check" aria-hidden="true"></i>
										</c:if> <c:if test="${lloc.personalOamr < 1}">
											<i class="fa fa-times" aria-hidden="true"></i>
										</c:if></td>
									<td><fmt:formatDate pattern="${gen:getDateTimePattern()}"
											value="${lloc.dataBaixa}" /></td>
									<td><a class="btn btn-primary btn-sm"
										href="<c:url value="/admin/lloc/view/${lloc.llocID}"/>"><i
											class="far fa-eye" title="Veure detall"></i>&nbsp;Detall</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</c:if>

			<c:if test="${llocItems.isEmpty()}">
				<div class="row" style="margin-left: 12px;">
					<div class="alert alert-warning" role="alert">Aquest
						funcionari no té cap plaça assignada</div>
					<p>
						<a class="btn btn-primary btn-sm"
							href="<c:url value="/admin/funcionarilloc/assignarfuncionari/${funcionari.funcionariID}"/>">Assignar
							una plaça</a>
					</p>
				</div>
			</c:if>


		</div>
		<div class="tab-pane fade" id="rols" role="tabpanel"
			aria-labelledby="rols-tab">

			<c:if test="${rolsFuncionari.isEmpty()}">
				<div class="alert alert-warning" role="alert">Aquest
					funcionari no té cap rol assignat</div>
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
					href="<c:url value="/admin/funcionarirol/assignar/${funcionari.funcionariID}"/>">Assignar
					un rol</a>
			</p>
		</div>
		<div class="tab-pane fade" id="activitat" role="tabpanel"
			aria-labelledby="activitat-tab">

			<c:if test="${activitatItems.isEmpty()}">
				<div class="alert alert-warning" role="alert">No hi ha cap
					activitat enregistrada.</div>
			</c:if>
			<c:if test="${not activitatItems.isEmpty()}">

				<div class="row">
					<table
						class="table table-sm table-bordered table-striped table-genapp table-genapp-list"
						style="width: auto;">
						<thead>
							<tr>
								<th>TIPUS</th>
								<th>REGISTRE</th>
								<th>TRAMIT</th>
								<th>CODISIA</th>
								<th>DATACREACIO</th>
								<th>ESTAT</th>
								<th>&nbsp;</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="activitat" items="${activitatItems}">
								<tr id="activitat_rowid_${activitat.activitatID}">
									<td>${activitat.tipus}</td>
									<td>${activitat.tipus}</td>
									<td>${activitat.tramit}</td>
									<td>${activitat.codiSia}</td>
									<td><fmt:formatDate pattern="${gen:getDateTimePattern()}"
											value="${activitat.dataCreacio}" /></td>
									<td>${activitat.estat}</td>
									<td><a
										href="<c:url value="/admin/activitat/view/${activitat.activitatID}"/>"
										class="btn btn-primary btn-sm" target="_blank"><i
											class="far fa-eye" title="Veure detall"></i></a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</c:if>



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

			<c:if test="${not empty historicItems}">

				<div class="row" style="margin-left: 0px;">
					<table
						class="table table-sm table-bordered table-striped table-genapp table-genapp-list"
						style="width: auto;">
						<thead>
							<tr>
								<th>DATACREACIO</th>
								<th>NUMEROCAI</th>
								<th>USUARIID</th>
								<th>&nbsp;</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="historic" items="${historicItems}">
								<tr id="historic_rowid_${historic.value1}">
									<td><fmt:formatDate pattern="${gen:getDateTimePattern()}"
											value="${historic.value6}" /></td>
									<td>${historic.value2}</td>
									<td>${historic.value3}&nbsp;${historic.value4}&nbsp;${historic.value5}</td>
									<td><a
										href="<c:url value="/admin/historic/view/${historic.value1}"/>"
										class="btn btn-primary btn-sm"><i class="far fa-eye"
											title="Veure detall"></i></a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</c:if>

			<c:if test="${empty historicItems}">
				<div class="alert alert-info" role="alert">No hi ha històric
					de canvis</div>
			</c:if>

		</div>
	</div>
</div>

<!--   div class="container">
    <ul class="nav nav-tabs" id="myTab" role="tablist">
        <li class="nav-item">
            <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">Home</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false">Profile</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" id="contact-tab" data-toggle="tab" href="#contact" role="tab" aria-controls="contact" aria-selected="false">Contact</a>
        </li>
    </ul>
    <div class="tab-content" id="myTabContent">
        <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">home tab</div>
        <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">profile tab</div>
        <div class="tab-pane fade" id="contact" role="tabpanel" aria-labelledby="contact-tab">contact tab</div>
    </div>
</div -->

<script type="text/javascript">
	document
			.addEventListener(
					"DOMContentLoaded",
					function(event) {

						if ("true" != "${isView}") {

							var nouTr = document.createElement("tr");
							nouTr.id = "funcionari_numerocai_rowid";

							var nouTd1 = document.createElement("td");
							nouTd1.id = "funcionari_numerocai_columnlabelid";
							nouTd1.innerHTML = "<label>Número CAI (*)</label>";
							nouTr.appendChild(nouTd1);

							var nouTd2 = document.createElement("td");
							nouTd2.id = "funcionari_numerocai_columnvalueid";
							nouTd2.innerHTML = '<input type="text" name="numerocai" id="numerocai" class="form-control w-75"></input>';
							nouTr.appendChild(nouTd2);

							var taula = document
									.getElementById("funcionari_tableid");
							var cos = taula.getElementsByTagName("tbody")[0];
							cos.appendChild(nouTr);

						}

					});
</script>
