function replaceFilterForSelectFilter(
  inputElement,
  newInputId,
  newInputName,
  label,
  options,
  wasSelect = false
) {
  // eliminam els fills per afegir un select com a nou filtre
  let parentContainer = inputElement.parentElement;
  if (wasSelect && parentContainer.parentElement) {
    parentContainer = parentContainer.parentElement;
  }
  parentContainer.classList.add("input-prepend");
  parentContainer.classList.remove("input-group");

  while (parentContainer.firstChild) {
    parentContainer.removeChild(parentContainer.firstChild);
  }

  const labelElement = document.createElement("span");
  labelElement.classList.add("add-on");
  labelElement.innerHTML = label;
  parentContainer.appendChild(labelElement);

  const selectElement = document.createElement("select");
  selectElement.classList.add("input-medium");
  //selectElement.name = "lloc.personalOamr";
  selectElement.id = newInputId;
  selectElement.name = newInputName;

  options.forEach((option) => {
    const optionElement = document.createElement("option");
    optionElement.value = option.value;
    optionElement.text = option.text;
    selectElement.appendChild(optionElement);
  });

  parentContainer.appendChild(selectElement);
}
