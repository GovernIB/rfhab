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