<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<div class="col-12">
	<ul class="nav nav-tabs" id="myTab" role="tablist"
		style="margin-bottom: 20px;">
		<li class="nav-item"><a class="nav-link active" id="home-tab"
			data-toggle="tab" href="#funcionaris" role="tab" aria-controls="home"
			aria-selected="true"><fmt:message key="funcionari.funcionari.plural"/></a></li>
		<li class="nav-item"><a class="nav-link" id="rols-tab"
			data-toggle="tab" href="#rols" role="tab" aria-controls="rols"
			aria-selected="false"><fmt:message key="rol.rol.plural"/></a></li>
		<li class="nav-item"><a class="nav-link" id="autoritzacio-tab"
			data-toggle="tab" href="#autoritzacio" role="tab"
			aria-controls="autoritzacio" aria-selected="false"><fmt:message key="autoritzacio.autoritzacio.plural"/></a>
		</li>
		<li class="nav-item"><a class="nav-link" id="historic-tab"
			data-toggle="tab" href="#historic" role="tab"
			aria-controls="historic" aria-selected="false"><fmt:message key="historicLloc.historicLloc.plural"/></a></li>
	</ul>
	<div class="tab-content" id="myTabContent">
		<div class="tab-pane fade show active" id="funcionaris" role="tabpanel"
			aria-labelledby="funcionaris-tab">

			<c:if test="${funcionaris.isEmpty()}">
				<div class="alert alert-warning" role="alert"><fmt:message key="lloc.admin.rols.sense"/></div>
			</c:if>

			<c:if test="${not funcionaris.isEmpty()}">
				<div class="row" style="margin-left: 0px;">
					<table
						class="table table-sm table-bordered table-striped table-genapp table-genapp-list"
						style="width: auto;">
						<thead>
							<tr>
								<th><fmt:message key="funcionari.numero"/></th>
								<th><fmt:message key="funcionari.nom"/></th>
								<th><fmt:message key="funcionariLloc.dataInici"/></th>
								<th><fmt:message key="funcionariLloc.dataFi"/></th>
								<th>&nbsp;</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="funcionariItem" items="${funcionaris}">
								<tr id="rol_rowid_${funcionariItem.funcionariID}">
									<td>${funcionariItem.numero}</td>
									<td>${funcionariItem.nom}&nbsp;${funcionariItem.llinatge1}&nbsp;${funcionariItem.llinatge2}</td>
									<td><fmt:formatDate pattern="${gen:getDateTimePattern()}" value="${funcionariItem.dataBaixa}" /></td>
									<td><fmt:formatDate pattern="${gen:getDateTimePattern()}" value="${funcionariItem.dataBaixa}" /></td>
									<td><a class="btn btn-primary btn-sm" href="<c:url value="/admin/funcionari/${funcionariItem.funcionariID}/edit"/>"><fmt:message key="detall"/></a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

			</c:if>


		</div>
		<div class="tab-pane fade" id="rols" role="tabpanel"
			aria-labelledby="rols-tab">

			<c:if test="${rolsFuncionari.isEmpty()}">
				<div class="alert alert-warning" role="alert"><fmt:message key="lloc.admin.rols.sense"/></div>
			</c:if>
			<c:if test="${not rolsFuncionari.isEmpty()}">
				<div class="row" style="margin-left: 0px;">
					<table
						class="table table-sm table-bordered table-striped table-genapp table-genapp-list"
						style="width: auto;">
						<thead>
							<tr>
								<th><fmt:message key="rol.codi"/></th>
								<th><fmt:message key="rol.nomID"/></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="rol" items="${rols}">
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
					href="<c:url value="/admin/llocrol/assignar/${lloc.llocID}"/>"><fmt:message key="lloc.assignar.nou.rol"/></a>
			</p>
		</div>
		
		<div class="tab-pane fade" id="autoritzacio" role="tabpanel"
			aria-labelledby="autoritzacio-tab">

			<c:if test="${isOamr > 0}">
				<p><fmt:message key="lloc.isoamr"/></p>
			</c:if>

			<c:if test="${not empty procediments}">
				<div class="row" style="margin-left: 0px;">
					<table
						class="table table-sm table-bordered table-striped table-genapp table-genapp-list"
						style="width: auto;">
						<thead>
							<tr>
								<th><fmt:message key="autoritzacio.codiSia" /></th>
								<th><fmt:message key="autoritzacio.procediment" /></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${procediments}" var="procedimentItem">
								<tr id="autoritzacio_rowid_${procedimentItem.key}">
									<td>${procedimentItem.key}</td>
									<td>${procedimentItem.value}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</c:if>

		</div>
		<div class="tab-pane fade" id="historic" role="tabpanel"
			aria-labelledby="historic-tab">

			<c:if test="${historic.isEmpty()}">
	<div class="alert alert-warning" role="alert" style="margin-top: 20px;"><fmt:message key="lloc.historic.buit"/></div>
</c:if>

<c:if test="${ not isNew && not historic.isEmpty()}">
	<div style="margin-top: 20px;">
		<table
			class="table table-sm table-bordered table-striped table-genapp table-genapp-list"
			style="width: auto;">
			<thead>
				<tr>
					<th><fmt:message key="historicLloc.dataCreacio" /></th>
					<th><fmt:message key="historicLloc.numeroCai"/></th>
					<th><fmt:message key="historicLloc.usuariID"/></th>
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
								class="far fa-eye" title="Veure Detall"></i></a></td>
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
							nouTd1.innerHTML = '<label style="font-weight:bold; text-align:right;"><fmt:message key="historic.numeroCai"/></label>';
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