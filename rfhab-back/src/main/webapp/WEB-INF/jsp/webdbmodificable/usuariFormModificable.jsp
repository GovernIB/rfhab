<c:if test="${not empty(entitats)}">
	<h3>Entitats assignades al usuari:</h3>

	<div class="row" style="margin-left: 0px;">
		<table
			class="table table-sm table-bordered table-striped table-genapp table-genapp-list"
			style="width: auto;">
			<thead>
				<tr>
					<th>Entitat</th>
					<th>Actiu</th>
					<th>&nbsp;</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${entitats}" var="entitat">
					<tr>
						<td>${entitat.nom}</td>
						<td><img height="18" width="18"
							src="<c:url value="/img/icn_alert_${entitat.actiu?'success':'error'}.png"/>"></td>
						<td><c:forEach items="${usuarisEntitats}" var="usuariEntitat">
								<c:if test="${usuariEntitat.entitatID == entitat.entitatID}">
									<a class="btn btn-danger" href="#myModal" role="button"
										onclick="openModal('<c:url value="${contexte}/${usuariEntitat.usuariEntitatID}/delete"/>','show');"
										title="<fmt:message key="genapp.delete"/>"> <i
										class="fas fa-trash icon-white"></i>
									</a>
								</c:if>
							</c:forEach></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</c:if>