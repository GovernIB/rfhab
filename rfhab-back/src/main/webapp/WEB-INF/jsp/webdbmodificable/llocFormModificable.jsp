<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<div class="col-12">
	<ul class="nav nav-tabs" id="myTab" role="tablist"
		style="margin-bottom: 20px;">
		<li class="nav-item">
			<a class="nav-link active" id="home-tab"
				data-toggle="tab" href="#funcionaris" role="tab" aria-controls="home"
				aria-selected="true"><fmt:message key="lloc.funcionari.pipella"/></a>
		</li>
		<li class="nav-item">
			<a class="nav-link" id="rols-tab"
				data-toggle="tab" href="#rols" role="tab" aria-controls="rols"
				aria-selected="false"><fmt:message key="rol.rol.plural"/></a>
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
									<td><fmt:formatDate pattern="${gen:getDateTimePattern()}" value="${funcionariItem.dataInici}" /></td>
									<td><fmt:formatDate pattern="${gen:getDateTimePattern()}" value="${funcionariItem.dataFi}" /></td>
									<td><a class="btn btn-primary btn-sm" href="<c:url value="/admin/funcionari/view/${funcionariItem.funcionariID}"/>"><fmt:message key="detall"/></a></td>
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
					href="<c:url value="/admin/llochabilitacio/assignar/${lloc.llocID}"/>"><fmt:message key="lloc.assignar.nou.rol"/></a>
			</p>
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
											class="far fa-eye" title="<fmt:message key="lloc.historic.veuredetall"/>"></i></a></td>
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
								<th><fmt:message key="funcionari.nom"/></th>
								<th><fmt:message key="funcionariLloc.dataInici"/></th>
								<th><fmt:message key="funcionariLloc.dataFi"/></th>
								<th>&nbsp;</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="funcionariItem" items="${funcionarisHistoric}">
								<tr id="rol_rowid_${funcionariItem.funcionariID}">
									<td>${funcionariItem.numero}</td>
									<td>${funcionariItem.nom}&nbsp;${funcionariItem.llinatge1}&nbsp;${funcionariItem.llinatge2}</td>
									<td><fmt:formatDate pattern="${gen:getDateTimePattern()}" value="${funcionariItem.dataInici}" /></td>
									<td><fmt:formatDate pattern="${gen:getDateTimePattern()}" value="${funcionariItem.dataFi}" /></td>
									<td><a class="btn btn-primary btn-sm" href="<c:url value="/admin/funcionari/view/${funcionariItem.funcionariID}"/>"><fmt:message key="detall"/></a></td>
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
	<c:forEach items="${unitats}" var="item">
		unitats.push({
			"unitatID": +"${item.unitatID}",
			"codi": "${item.codi}",
			"arrel": "${item.arrel}",
			"superior": "${item.superior}"
		});
	</c:forEach>
	let entitats = [];
	<c:forEach items="${entitats}" var="item">
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

		let llocEntitatUnitatId = resultat ? resultat.unitatID : null; // Obté el unitatID de la unitat trobada
		let entitatTrobada = entitats.find(entitat => entitat.unitatID == llocEntitatUnitatId);
		let entitatId = entitatTrobada ? entitatTrobada.entitatID : null;
		document.getElementById("lloc.entitatID").value = entitatId;
		document.querySelector('[id="lloc.entitatID"]+input').value = entitatTrobada?.nom;
		// document.querySelector('[id="lloc.entitatID"]+input').value = "${gen:findValue(entitatId,__theForm.listOfEntitatForEntitatID)}";
	}
	
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