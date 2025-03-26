<style type="text/css">
.search-query {
	width: auto !important;
}
</style>

<script type="text/javascript">
	$(document).ready(function() {
		document.getElementById('FilterDiv').style.display = 'inherit';
		document.getElementById('FilterButton').style.display = 'none';

		// eliminam els fills per afegir un select com a nou filtre
		const inputPersonalOamr = document.getElementById("lloc.personalOamr");
		if (inputPersonalOamr) {

			const parentContainer = inputPersonalOamr.parentElement;

			while (parentContainer.firstChild) {
				parentContainer.removeChild(parentContainer.firstChild);
			}

			const labelElement = document.createElement("span");
			labelElement.classList.add("add-on");
			labelElement.innerHTML = "Personal OAMR: ";
			parentContainer.appendChild(labelElement);

			const selectElement = document.createElement("select");
			selectElement.classList.add("input-medium");
			//selectElement.name = "lloc.personalOamr";
			selectElement.id = "lloc.personalOamr";

			const optionBlanc = document.createElement("option");
			optionBlanc.value = "";
			optionBlanc.text = "Tots";
			selectElement.appendChild(optionBlanc);

			const optionYes = document.createElement("option");
			optionYes.value = "1";
			optionYes.text = "Si";
			selectElement.appendChild(optionYes);

			const optionNo = document.createElement("option");
			optionNo.value = "0";
			optionNo.text = "No";
			selectElement.appendChild(optionNo);

			parentContainer.appendChild(selectElement);

		}

	});
</script>