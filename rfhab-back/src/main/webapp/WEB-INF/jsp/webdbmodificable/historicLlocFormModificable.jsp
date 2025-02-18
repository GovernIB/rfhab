<div class="lead">
	<label style="font-size: 1.25rem; font-weight:bold;">Diferències</label>
</div>

<table class="table table-striped" border="0" syle="border-collapse: border-collapse; margin-top:20px;">
	<thead>
		<tr>
			<th scope="col">Camp</th>
			<th scope="col">ACTUAL</th>
			<th scope="col">ANTIC</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>Codi lloc</td>
			<td>${ actual.codiLloc }</td>
			<td>${ vell.codiLloc }</td>
		</tr>
		<tr>
			<td>Nom</td>
			<td>${ actual.nom }</td>
			<td>${ vell.nom }</td>
		</tr>
		<tr>
			<td>És personal OAMR?</td>
			<td>${ actual.personalOamr }</td>
			<td>${ vell.personalOamr }</td>
		</tr>
		<tr>
			<td>Unitat</td>
			<td>${ actual.unitatID }</td>
			<td>${ vell.unitatID }</td>
		</tr>
		</tr>
	</tbody>
</table>