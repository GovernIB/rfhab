<un:useConstants
  var="UserController"
  className="es.caib.rfhab.back.controller.user.UserController"
/>

<c:if test="${__theForm.view}">
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
			aria-controls="historic" aria-selected="false"><fmt:message key="funcionari.historic.pipella" /></a></li>
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
								<th><fmt:message key="lloc.codiLlocPropi" /></th>
								<th><fmt:message key="lloc.codiLloc" /></th>
								<th><fmt:message key="lloc.nomlf" /></th>
								<th><fmt:message key="lloc.personalOamr" /></th>
								<th><fmt:message key="lloc.dataassignaciolf" /></th>
								<th>&nbsp;</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="lloc" items="${llocItems}">
								<tr id="lloc_rowid_${lloc.lloc.llocID}">
									<td>${lloc.lloc.codiLlocPropi}</td>
									<td>${lloc.lloc.codiLloc}</td>
									<td>${lloc.lloc.nom}</td>
									<td align="center">
										<c:if test="${lloc.lloc.personalOamr > 1}">
											<i class="fa fa-check" aria-hidden="true"></i>
										</c:if> 
										<c:if test="${lloc.lloc.personalOamr < 2}">
											<i class="fa fa-times" aria-hidden="true"></i>
										</c:if>
									</td>
									<td><fmt:formatDate value="${lloc.dataInici}" /></td>
									<td><a class="btn btn-primary btn-sm"
										href="<c:url value="/admin/lloc/view/${lloc.lloc.llocID}"/>"><i
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
									<td>${gen:findValue(activitat.tipus, listOfValuesForTipus)}</td>
									<td>${activitat.registre}</td>
									<td>${activitat.tramit}</td>
									<td>${activitat.codiSia}</td>
									<td><fmt:formatDate pattern="${gen:getDateTimePattern()}"
											value="${activitat.dataCreacio}" /></td>
									<td>${gen:findValue(activitat.estat, listOfValuesForEstat)}</td>
									<td>
										<a href="<c:url value="/user/activitat/view/${activitat.activitatID}"/>"
											class="btn btn-primary btn-sm" target="_blank">
												<i class="far fa-eye" title="Veure detall"></i>
										</a>
										<c:if test="${activitat.arxiuDocumentID != null && !activitat.arxiuDocumentID.isEmpty()}">
											<a href="<c:url value="${UserController.CONTEXTWEB}modelconsentiment/${activitat.arxiuDocumentID}"/>"
												class="btn btn-success btn-sm" target="_blank">
													<i class="fa fa-file-download" 
														title="<fmt:message key="activitat.descarrega.modelconsentiment" />"></i>
											</a>
										</c:if>
									</td>
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
								<th><fmt:message key="funcionari.historic.pipella.data" /></th>
								<th><fmt:message key="funcionari.historic.pipella.cai"/></th>
								<th><fmt:message key="funcionari.historic.pipella.usuari"/></th>
								<th><fmt:message key="funcionari.historic.pipella.modificacio"/></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="h" items="${historicItems}">
								<tr>
									<td><fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss" value="${h.dataCreacio}" /></td>
									<td>${h.numeroCai}</td>
									<td>${h.usuariId}</td>
									<td>
										<c:if test="${ h.vell == null || h.nou == null }">
											${h.observacions}
										</c:if>

										<c:if test="${ h.vell != null && h.nou != null }">
											<c:set var="vell" value="${h.vell}" />
											<c:set var="nou" value="${h.nou}" />
											<!-- descomentar també del controlador, si es descomenta aquesta línia -->
											<!-- <c:set var="diferenciesDictionary" value="${diferenciesDictionary}" /> -->
											<!-- <%@include file="diferenciesTable.jsp" %> -->
											<span><fmt:message key="funcionari.historic.canvisdepropietats"/></span>
											<a href="<c:url value="/admin/historic/view/${h.historicId}"/>" class="btn btn-info">
												<i class="far fa-eye" title="<fmt:message key="funcionari.historic.veuredetall"/>">
												</i>
											</a>
										</c:if>
									</td>
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
					<table id="historicllocs-table"
						class="table table-sm table-bordered table-striped table-genapp table-genapp-list"
						style="width: auto;">
						<thead>
							<tr>
								<th><fmt:message key="lloc.codiLlocPropi" /></th>
								<th><fmt:message key="lloc.nomlf" /></th>
								<th><fmt:message key="funcionariLloc.dataInici" /></th>
								<th><fmt:message key="funcionariLloc.dataFi" /></th>
								<th><fmt:message key="funcionari.historic.pipella.cai" /></th>
								<!-- <th>&nbsp;</th> -->
							</tr>
						</thead>
						<tbody>
							<c:forEach var="lloc" items="${llocsHistoric}">
								<tr id="lloc_rowid_${lloc.lloc.llocID}">
									<td>${lloc.lloc.codiLlocPropi}</td>
									<td>${lloc.lloc.nom}</td>
									<td><fmt:formatDate pattern="dd/MM/yyyy" value="${lloc.dataInici}" /></td>
									<td><fmt:formatDate pattern="dd/MM/yyyy" value="${lloc.dataFi}" /></td>
									<td>${lloc.numeroCai}</td>
									<!-- <td>
										<a class="btn btn-primary btn-sm" href="<c:url value="/admin/lloc/view/${lloc.lloc.llocID}"/>">
											<i class="far fa-eye" title="Veure detall"></i>&nbsp;<fmt:message key="detall" />
										</a>
									</td> -->
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="row" style="margin-left: 12px;">
					<button class="btn btn-info" type="button" id="exportcsv" title="<fmt:message key="descarregar" />"
						onclick="downloadDataFromTable('#historicllocs-table', 'historic_llocs_${__theForm.funcionari.numero}.csv')">
						<i class="fas fa-file-csv"></i>
					</button>
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
</c:if>

<script type="text/javascript">
	document
			.addEventListener(
					"DOMContentLoaded",
					function(event) {

						document.getElementById("funcionari.numero").placeholder = '${FUNCIONARI_NUMERO_PLACEHOLDER}';
						document.getElementById("funcionari.correu").placeholder = '${CORREU_PLACEHOLDER}';

						const correuLabel = document.getElementById("funcionari_correu_columnlabelid")?.querySelector("label");
						const helpIcon = document.createElement("i");
						helpIcon.className = "fas fa-question-circle";
						helpIcon.title = "<fmt:message key="correu"/>";
						helpIcon.style.marginLeft = "5px";
						if(correuLabel){
							correuLabel.appendChild(helpIcon);
						}

						if ("true" != "${isView}") {

							const nouTrNumeroCai = document.createElement("tr");
							nouTrNumeroCai.id = "funcionari_numerocai_rowid";

							const nouTd1NumeroCai = document.createElement("td");
							nouTd1NumeroCai.id = "funcionari_numerocai_columnlabelid";
							nouTd1NumeroCai.innerHTML = '<label style="text-align:right"><fmt:message key="historic.numeroCai"/></label>';
							nouTrNumeroCai.appendChild(nouTd1NumeroCai);

							const nouTd2NumeroCai = document.createElement("td");
							nouTd2NumeroCai.id = "funcionari_numerocai_columnvalueid";
							nouTd2NumeroCai.innerHTML = '<input type="text" maxlength="50" name="numerocai" id="numerocai" class="form-control w-75"></input>';
							nouTrNumeroCai.appendChild(nouTd2NumeroCai);

							const taulaFuncionariForm = document
									.getElementById("funcionari_tableid");
							const cosFuncionariForm = taulaFuncionariForm.getElementsByTagName("tbody")[0];
							nouTrNumeroCai.hidden = true;
							cosFuncionariForm.appendChild(nouTrNumeroCai);
						}
					});		
</script>
