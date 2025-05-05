<div class="col-12">
	<ul class="nav nav-tabs" id="myTab" role="tablist"
		style="margin-bottom: 20px;">
		<li class="nav-item"><a class="nav-link active" id="home-tab"
			data-toggle="tab" href="#home" role="tab" aria-controls="home"
			aria-selected="true"><fmt:message key="funcionari.lloc.pipella" /></a></li>
		<li class="nav-item"><a class="nav-link" id="activitat-tab"
			data-toggle="tab" href="#activitat" role="tab"
			aria-controls="activitat" aria-selected="false"><fmt:message key="activitat.activitat" /></a></li>
		<li class="nav-item"><a class="nav-link" id="historic-tab"
			data-toggle="tab" href="#historic" role="tab"
			aria-controls="historic" aria-selected="false"><fmt:message key="historic.historic" /></a></li>
		<li class="nav-item"><a class="nav-link" id="historicllocs-tab"
			data-toggle="tab" href="#historicllocs" role="tab"
			aria-controls="historicllocs" aria-selected="false"><fmt:message key="funcionari.historicllocs.pipella" /></a></li>
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
								<th><fmt:message key="lloc.llocID" /></th>
								<th><fmt:message key="lloc.codiLloc" /></th>
								<th><fmt:message key="lloc.nom" /></th>
								<th><fmt:message key="lloc.personalOamr" /></th>
								<th><fmt:message key="lloc.dataBaixa" /></th>
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
											class="far fa-eye" title="Veure detall"></i>&nbsp;<fmt:message key="detall" /></a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</c:if>

			<c:if test="${llocItems.isEmpty()}">
				<div class="row" style="margin-left: 12px;">
					<div class="alert alert-warning" role="alert"><fmt:message key="funcionari.sense.lloc" /></div>
					<p>
						<a class="btn btn-primary btn-sm"
							href="<c:url value="/admin/funcionarilloc/assignarfuncionari/${funcionari.funcionariID}"/>"><fmt:message key="funcionari.assignar.lloc" /></a>
					</p>
				</div>
			</c:if>


		</div>
		
		<div class="tab-pane fade" id="activitat" role="tabpanel"
			aria-labelledby="activitat-tab">

			<c:if test="${activitatItems.isEmpty()}">
				<div class="alert alert-warning" role="alert"><fmt:message key="funcionari.sense.activitat" /></div>
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
		
		<div class="tab-pane fade" id="historic" role="tabpanel"
			aria-labelledby="historic-tab">

			<c:if test="${not empty historicItems}">

				<div class="row" style="margin-left: 0px;">
					<table
						class="table table-sm table-bordered table-striped table-genapp table-genapp-list"
						style="width: auto;">
						<thead>
							<tr>
								<th><fmt:message key="historic.dataCreacio" /></th>
								<th><fmt:message key="historic.numeroCai"/></th>
								<th><fmt:message key="historic.usuariID"/></th>
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
				<div class="alert alert-info" role="alert">
					<fmt:message key="funcionari.historic.buit"/>
				</div>
			</c:if>

		</div>

		<div class="tab-pane fade" id="historicllocs" role="tabpanel"
			aria-labelledby="historicllocs-tab">

			<c:if test="${not llocsHistoric.isEmpty()}">
				<div class="row" style="margin-left: 12px;">
					<table
						class="table table-sm table-bordered table-striped table-genapp table-genapp-list"
						style="width: auto;">
						<thead>
							<tr>
								<th><fmt:message key="lloc.llocID" /></th>
								<th><fmt:message key="lloc.codiLloc" /></th>
								<th><fmt:message key="lloc.nom" /></th>
								<th><fmt:message key="lloc.personalOamr" /></th>
								<th><fmt:message key="funcionariLloc.dataInici" /></th>
								<th><fmt:message key="funcionariLloc.dataFi" /></th>
								<th>&nbsp;</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="lloc" items="${llocsHistoric}">
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
											value="${lloc.dataInici}" /></td>
									<td><fmt:formatDate pattern="${gen:getDateTimePattern()}"
											value="${lloc.dataFi}" /></td>
									<td><a class="btn btn-primary btn-sm"
										href="<c:url value="/admin/lloc/view/${lloc.llocID}"/>"><i
											class="far fa-eye" title="Veure detall"></i>&nbsp;<fmt:message key="detall" /></a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</c:if>

			<c:if test="${llocsHistoric.isEmpty()}">
				<div class="alert alert-warning" role="alert">
					<fmt:message key="funcionari.historicllocs.sense"/>
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

						document.getElementById("funcionari.numero").placeholder = '${FUNCIONARI_NUMERO_PLACEHOLDER}';

						if ("true" != "${isView}") {

							var nouTr = document.createElement("tr");
							nouTr.id = "funcionari_numerocai_rowid";

							var nouTd1 = document.createElement("td");
							nouTd1.id = "funcionari_numerocai_columnlabelid";
							nouTd1.innerHTML = '<label style="text-align:right"><fmt:message key="historic.numeroCai"/></label>';
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
