<script src="<c:url value="/js/select2.min.js"/>"></script>
<script src="<c:url value="/js/select2_i18n/${lang}.js"/>"></script>

<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<table id="tableauxtodelete">
	<tbody>
		<tr id="lloc_unitatsuperiorID_rowid">
			<td id="lloc_unitatsuperiorID_columnlabelid">
				<label>
					<fmt:message key="unitat.superior" />
				</label>
			</td>
			<td id="lloc_unitatsuperiorID_columnvalueid">
				<input id="lloc_unitatsuperiorID" type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.lloc.unitatID,__theForm.listOfUnitatForUnitatID)}"   />
			</td>
		</tr>
	</tbody>
</table>

<c:if test="${__theForm.view}">
<div class="col-12">
	<ul class="nav nav-tabs" id="myTab" role="tablist"
		style="margin-bottom: 20px;">
		<li class="nav-item">
			<a class="nav-link active" id="home-tab"
				data-toggle="tab" href="#funcionaris" role="tab" aria-controls="home"
				aria-selected="true"><fmt:message key="lloc.funcionari.pipella"/></a>
		</li>
		<li class="nav-item">
			<a class="nav-link" id="habilitacions-tab"
				data-toggle="tab" href="#habilitacions" role="tab" aria-controls="habilitacions"
				aria-selected="false"><fmt:message key="habilitacio.habilitacio.plural"/></a>
		</li>
		<li class="nav-item">
			<a class="nav-link" id="historic-tab"
				data-toggle="tab" href="#historic" role="tab"
				aria-controls="historic" aria-selected="false"><fmt:message key="lloc.historic.pipella"/></a>
		</li>
		<li class="nav-item">
			<a class="nav-link" id="historicfuncionaris-tab"
				data-toggle="tab" href="#historicfuncionaris" role="tab"
				aria-controls="historicfuncionaris" aria-selected="false"><fmt:message key="lloc.historicfuncionaris.pipella"/></a>
		</li>
	</ul>

	<div class="tab-content" id="myTabContent">
		<div class="tab-pane fade show active" id="funcionaris" role="tabpanel"
			aria-labelledby="funcionaris-tab">

			<c:if test="${funcionaris.isEmpty()}">
				<div class="alert alert-warning" role="alert"><fmt:message key="lloc.admin.funcionari.sense"/></div>
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
								<th><fmt:message key="funcionari.historic.pipella.cai"/></th>
								<!-- <th>&nbsp;</th> -->
							</tr>
						</thead>
						<tbody>
							<c:forEach var="funcionariItem" items="${funcionaris}">
								<tr id="habilitacio_rowid_${funcionariItem.funcionariID}">
									<td>${funcionariItem.numero}</td>
									<td>${funcionariItem.nom}&nbsp;${funcionariItem.llinatge1}&nbsp;${funcionariItem.llinatge2}</td>
									<td><fmt:formatDate pattern="dd/MM/yyyy" value="${funcionariItem.dataInici}" /></td>
									<td><fmt:formatDate pattern="dd/MM/yyyy" value="${funcionariItem.dataFi}" /></td>
									<td>${funcionariItem.numeroCai}</td>
									<!-- <td><a class="btn btn-primary btn-sm" href="<c:url value="/admin/funcionari/view/${funcionariItem.funcionariID}"/>"><fmt:message key="detall"/></a></td> -->
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</c:if>
		</div>

		<div class="tab-pane fade" id="habilitacions" role="tabpanel"
			aria-labelledby="habilitacions-tab">

			<c:if test="${habilitacions.isEmpty()}">
				<div class="alert alert-warning" role="alert"><fmt:message key="lloc.admin.habilitacions.sense"/></div>
			</c:if>
			<c:if test="${not habilitacions.isEmpty()}">
				<div class="row" style="margin-left: 0px;">
					<table
						class="table table-sm table-bordered table-striped table-genapp table-genapp-list"
						style="width: auto;">
						<thead>
							<tr>
								<th><fmt:message key="habilitacio.codi"/></th>
								<th><fmt:message key="habilitacio.nomID"/></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="habilitacio" items="${habilitacions}">
								<tr id="habilitacio_rowid_${habilitacio.habilitacioID}">
									<td>${habilitacio.codi}</td>
									<td>
										<c:set var="tmp">${habilitacio.nomID}</c:set>
										<c:if test="${not empty tmp}">${habilitacio.nom.traduccions[lang].valor}</c:if>
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

			<c:if test="${historic.isEmpty()}">
				<div class="alert alert-warning" role="alert" style="margin-top: 20px;">
					<fmt:message key="lloc.historic.buit"/>
				</div>
			</c:if>

			<c:if test="${ not isNew && not historic.isEmpty()}">
				<div style="margin-top: 20px;">
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
							<c:forEach var="h" items="${historic}">
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
											<span><fmt:message key="lloc.historic.canvisdepropietats"/></span>
											<a href="<c:url value="/admin/historiclloc/view/${h.historicLlocId}"/>" class="btn btn-secondary">
												<i class="far fa-eye" title="<fmt:message key="lloc.historic.veuredetall"/>">
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

		</div>

		<div class="tab-pane fade" id="historicfuncionaris" role="tabpanel"
			aria-labelledby="historicfuncionaris-tab">

			<c:if test="${funcionarisHistoric.isEmpty()}">
				<div class="alert alert-warning" role="alert">
					<fmt:message key="lloc.historicfuncionaris.sense"/>
				</div>
			</c:if>

			<c:if test="${not funcionarisHistoric.isEmpty()}">
				<div class="row" style="margin-left: 0px;">
					<table
						class="table table-sm table-bordered table-striped table-genapp table-genapp-list"
						style="width: auto;">
						<thead>
							<tr>
								<th><fmt:message key="funcionari.numero"/></th>
								<th><fmt:message key="tipusidentificacio.1"/></th>
								<th><fmt:message key="funcionari.nom"/></th>
								<th><fmt:message key="funcionariLloc.dataInici"/></th>
								<th><fmt:message key="funcionariLloc.dataFi"/></th>
								<th><fmt:message key="historic.numeroCai"/></th>
								<!-- <th>&nbsp;</th> -->
							</tr>
						</thead>
						<tbody>
							<c:forEach var="funcionariItem" items="${funcionarisHistoric}">
								<tr id="habilitacio_rowid_${funcionariItem.funcionariID}">
									<td>${funcionariItem.numero}</td>
									<td>${funcionariItem.identificador}</td>
									<td>${funcionariItem.nom}&nbsp;${funcionariItem.llinatge1}&nbsp;${funcionariItem.llinatge2}</td>
									<td><fmt:formatDate pattern="dd/MM/yyyy" value="${funcionariItem.dataInici}" /></td>
									<td><fmt:formatDate pattern="dd/MM/yyyy" value="${funcionariItem.dataFi}" /></td>
									<td>${funcionariItem.numeroCai}</td>
									<!-- <td><a class="btn btn-primary btn-sm" href="<c:url value="/admin/funcionari/view/${funcionariItem.funcionariID}"/>"><fmt:message key="detall"/></a></td> -->
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</c:if>
		</div>	
	</div>
</div>
</c:if>

<script type="text/javascript">
	function findUnitatByArrel(unitats, unitatId) {
		// Cerca la unitat amb el unitatId especificat
		let unitatTrobada = unitats.find(unitat => unitat.unitatID === unitatId);
		if (unitatTrobada) {
			// Obté el valor de "arrel" de la unitat trobada
			let arrel = unitatTrobada.arrel;
			if (arrel) {
				// Cerca i retorna la unitat amb el "codi" igual al valor de "arrel"
				return unitats.find(unitat => unitat.codi === arrel) || null;
			}
		}
		// Retorna null si no es troba cap unitat que compleixi els criteris
		return null;
	}

	function findUnitatSuperior(unitats, unitatId) {
		// Cerca la unitat inicial amb el unitatId especificat
		const unitatActual = unitats.find(unitat => unitat.unitatID === unitatId);
		if(!unitatActual) return null;
		return unitats.find(unitat => unitat.codi === unitatActual.superior) || null;
	}

	function findUnitatMare(unitats, unitatId) {
		// Cerca la unitat inicial amb el unitatId especificat
		let unitatMare = unitats.find(unitat => unitat.unitatID === unitatId);
		let unitatActual = unitatMare;

		// Itera fins que no hi hagi més "mares" (és a dir, fins que el camp "superior" sigui null o no es trobi cap coincidència)
		while (unitatActual && unitatActual.superior) {
			unitatActual = unitats.find(unitat => unitat.codi === unitatActual.superior);
			if(unitatActual != null){
				unitatMare = unitatActual; // Actualitza la unitat mare si es troba una nova unitat
			}
		}

		// Retorna l'última unitat trobada (la "mare")
		return unitatMare || null;
	}

	function findAllReferencingUnitats(unitats, codiInicial) {
		let result = [];
		let codisPendents = [codiInicial]; // Inicialitza amb el codi inicial

		while (codisPendents.length > 0) {
			let codiActual = codisPendents.shift(); // Extreu el primer codi de la llista

			// Cerca les unitats que tenen el camp "superior" igual al codi actual
			unitats.forEach(unitat => {
				if (unitat.superior === codiActual) {
					result.push(unitat); // Afegeix la unitat al resultat
					codisPendents.push(unitat.codi); // Afegeix el codi de la unitat trobada per continuar la cerca
				}
			});
		}

		return result; // Retorna totes les unitats trobades
	}

	let unitats = [];
	<c:forEach items="${unitatsPenjantDeLentitat}" var="item">
		unitats.push({
			"unitatID": +"${item.unitatID}",
			"codi": "${item.codi}",
			"arrel": "${item.arrel}",
			"superior": "${item.superior}",
			"cooficial": "${item.cooficial}",
			"denominacio": "${item.denominacio}"
		});
	</c:forEach>
	let entitats = [];
	<c:forEach items="${entitatsPenjantDeLentitat}" var="item">
		entitats.push({
			"entitatID": +"${item.entitatID}",
			"unitatID": "${item.unitatID}",
			"nom": "${item.nom}"
		});
	</c:forEach>

	function onChangeUnitatID(event) {
		let unitatIdSeleccionat = +event.value;
		let resultat = findUnitatMare(unitats, unitatIdSeleccionat);

		if (resultat) {
			console.log("Unitat trobada: ", resultat);
		} else {
			console.log("No s'ha trobat cap unitat amb el codi " + unitatIdSeleccionat + " a l'arrel.");
		}

		const unitatSuperior = findUnitatSuperior(unitats, unitatIdSeleccionat);

		let llocEntitatUnitatId = resultat ? resultat.unitatID : null; // Obté el unitatID de la unitat trobada
		let entitatTrobada = entitats.find(entitat => entitat.unitatID == llocEntitatUnitatId);
		let entitatId = entitatTrobada ? entitatTrobada.entitatID : null;
		document.getElementById("lloc.entitatID").value = entitatId;
		document.querySelector('[id="lloc.entitatID"]+input').value = entitatTrobada?.nom;
		// document.querySelector('[id="lloc.entitatID"]+input').value = "${gen:findValue(entitatId,__theForm.listOfEntitatForEntitatID)}";
		if(unitatSuperior){
			document.getElementById("lloc_unitatsuperiorID").value = unitatSuperior.codi + " " + ("${lang}".toUpperCase() == 'ES' ? unitatSuperior?.denominacio : unitatSuperior?.cooficial);
		}
		else{
			document.getElementById("lloc_unitatsuperiorID").value = null;
		}
	}
	
	function onSelectedHabilitacioLloc(select2) {
		const optionsSelected = getSelectedOptions(select2.target);
		console.log('You selected: ', getSelectedOptions(select2.target));
		let habilitacionsSeleccionades = "";
		if(optionsSelected && optionsSelected.length > 0){
			habilitacionsSeleccionades = optionsSelected.map(option => option.value).join(",");
		}
		$('#habilitacionsSeleccionadesId').val(habilitacionsSeleccionades);
	}

	document
			.addEventListener(
					"DOMContentLoaded",
					function(event) {
						const codiLlocInput = document.getElementById("lloc.codiLloc");
						if(codiLlocInput){
							codiLlocInput.placeholder = '${LLOC_CODILLOC_PLACEHOLDER}';
							
							const expansioLlocInput = document.getElementById("lloc.expansio");
							if (expansioLlocInput) {
								const toggleExpansioReadonly = () => {
									const teValor = codiLlocInput.value && codiLlocInput.value.trim() !== "";
									//volem conservar el comportament original. Per fer-ho, mirarem el comportament de codiLlocInput
									if(!codiLlocInput.readOnly){
										expansioLlocInput.readOnly = !teValor;
										if (!teValor) {
											expansioLlocInput.value = null;
										}
									}
								};

								// Estat inicial
								toggleExpansioReadonly();

								// Canvis mentre s'escriu o en perdre el focus
								codiLlocInput.addEventListener("input", toggleExpansioReadonly);
								codiLlocInput.addEventListener("change", toggleExpansioReadonly);
							}
						}
						document.getElementById("lloc.codiLlocPropi").placeholder = '${LLOC_CODILLOCPROPI_PLACEHOLDER}';

						const taula = document.getElementById("lloc_tableid");
						const tbody = taula.querySelector("tbody");

						if ("true" != "${isView}") {
							//afegeix input numero CAI
							const nouNumeroCaiTr = document.createElement("tr");
							nouNumeroCaiTr.id = "lloc_numerocai_rowid";

							const nouTd1NumeroCai = document.createElement("td");
							nouTd1NumeroCai.id = "lloc_numerocai_columnlabelid";
							nouTd1NumeroCai.innerHTML = '<label style="font-weight:bold; text-align:right;"><fmt:message key="historic.numeroCai"/></label>';
							nouNumeroCaiTr.appendChild(nouTd1NumeroCai);

							const nouTd2NumeroCai = document.createElement("td");
							nouTd2NumeroCai.id = "lloc_numerocai_columnvalueid";
							nouTd2NumeroCai.innerHTML = '<input type="text" maxlength="50" name="numerocai" id="numerocai" class="form-control w-100"></input>';
							nouNumeroCaiTr.appendChild(nouTd2NumeroCai);

							const observacionsTr = tbody.querySelector("#lloc_observacions_rowid");
							tbody.insertBefore(nouNumeroCaiTr, observacionsTr);

							//afegeix input habilitacions
							const optionsHabilitacions = [
								<c:forEach items="${habilitacionsTotes}" var="habilitacio">
									{ value: '${habilitacio.habilitacioID}', text: '${habilitacio.codi}' },
								</c:forEach>
							];
							const habilitacionsSeleccionades = [
							<c:forEach var="habilitacio" items="${habilitacions}">
								'${habilitacio.habilitacioID}',
							</c:forEach>
							];
							const llocHabilitacionsSelectId = "lloc_habilitacions_seleccionades_id";
							const nouHabilitacionsTr = createTrInputFormSelect("lloc_habilitacions_rowid", "lloc_habilitacions_columnlabelid", '<fmt:message key="habilitacio.habilitacio.plural"/>', "lloc_habilitacions_columnvalueid", "habilitacionsSeleccionadesId", llocHabilitacionsSelectId, "llocHabilitacionsSeleccionades", optionsHabilitacions, habilitacionsSeleccionades, onSelectedHabilitacioLloc, true);
							const dataDaltaTr = tbody.querySelector("#lloc_dataalta_rowid");
							tbody.insertBefore(nouHabilitacionsTr, dataDaltaTr);
							$('#' + llocHabilitacionsSelectId).select2(
								{
									placeholder: "",
									allowClear: true,
									language: "${lang}",
									minimumInputLength: 0,
									disabled: false
								}
							);

							//mostra observacions com a soft wrap i l'oculta
							const taObservacions = document.getElementById('lloc.observacions');
							if(taObservacions){
								taObservacions.wrap='soft';
							}
							const divDropdownObservacions = document.getElementById('dropdownMenuButton_observacions');
							if(divDropdownObservacions){
								divDropdownObservacions.parentElement.hidden = true;
							}
						}

						//oculta entitatid i mostra unitat superior
						const lloc_entitatID_rowid = document.getElementById("lloc_entitatID_rowid");
						const lloc_unitatsuperiorID_rowid = document.getElementById("lloc_unitatsuperiorID_rowid");
						tbody.insertBefore(lloc_unitatsuperiorID_rowid, lloc_entitatID_rowid);
						lloc_entitatID_rowid.hidden = true;
						const tableAuxToDelete = document.getElementById("tableauxtodelete");
						tableAuxToDelete.remove();
						let unitatInput = document.getElementById("lloc.unitatID");
						if(!unitatInput){
							unitatInput = document.getElementById("lloc_unitatID");
						}
						onChangeUnitatID({value: unitatInput.value});
					});
</script>