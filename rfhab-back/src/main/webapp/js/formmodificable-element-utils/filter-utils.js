function replaceFilterForSelectFilter(
  inputElement,
  newInputId,
  newInputName,
  isSelect = false
) {
  // eliminam els fills per afegir un select com a nou filtre
  let parentContainer = inputElement.parentElement;
  if (isSelect && parentContainer.parentElement) {
    parentContainer = parentContainer.parentElement;
  }

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
  selectElement.id = newInputId;
  selectElement.name = newInputName;

  const optionBlanc = document.createElement("option");
  optionBlanc.value = "";
  optionBlanc.text = "Tots";
  selectElement.appendChild(optionBlanc);

  const optionYes = document.createElement("option");
  optionYes.value = "1";
  optionYes.text = "Si";
  // optionYes.text = "S&iacute;";
  selectElement.appendChild(optionYes);

  const optionNo = document.createElement("option");
  optionNo.value = "0";
  optionNo.text = "No";
  selectElement.appendChild(optionNo);

  parentContainer.appendChild(selectElement);
}
