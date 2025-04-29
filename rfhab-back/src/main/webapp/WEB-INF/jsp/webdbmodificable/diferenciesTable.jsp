<c:if test="${ vell == null || nou == null }">
	<h5>${observacions}</h5>
</c:if>

<c:if test="${ vell != null && nou != null }">
	<div class="lead">
		<label style="font-size: 1.25rem; font-weight:bold;">
			<fmt:message key="historiclloc.diferencies"/>
		</label>
	</div>
	
	<table class="table table-striped" border="0" syle="border-collapse: border-collapse; margin-top:20px;">
		<thead>
			<tr>
				<th scope="col"><fmt:message key="historiclloc.camp"/></th>
				<th scope="col"><fmt:message key="historiclloc.nou"/></th>
				<th scope="col"><fmt:message key="historiclloc.antic"/></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="field" items="${nou['class'].declaredFields}">
				<c:if test="${nou[field.name] != null || vell[field.name] != null}">
					<c:if test="${nou[field.name] != vell[field.name]}">
						<tr>
							<td><c:out value="${diferenciesDictionary[field.name]}"/></td>
							<td style="color:green"><c:out value="${nou[field.name]}"/></td>
							<td style="color:red"><c:out value="${vell[field.name]}"/></td>
						</tr>
					</c:if>
				</c:if>
			</c:forEach>
			</tr>
		</tbody>
	</table>
</c:if>
