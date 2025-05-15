function addNewSelectFilterToContainer(
  parentContainer,
  options,
  label,
  newInputId,
  newInputName
) {
  //estils per quan son selects o desde i fins
  parentContainer.classList.remove("input-group");
  parentContainer.classList.add("input-prepend");

  //afegim etiqueta amb el títol
  const labelElement = document.createElement("span");
  labelElement.classList.add("add-on");
  labelElement.innerHTML = label;
  parentContainer.appendChild(labelElement);

  //afegim nou select
  const selectElement = createSelect(newInputId, newInputName, options);
  parentContainer.appendChild(selectElement);

  return selectElement;
}

function addNewSelectFilterToForm(
  formFilterContainer,
  options,
  label,
  newInputId,
  newInputName
) {
  //afegim contenidor del select
  const parentContainer = document.createElement("div");
  parentContainer.style.paddingRight = "4px";
  parentContainer.style.paddingBottom = "4px";
  formFilterContainer.appendChild(parentContainer);

  return addNewSelectFilterToContainer(
    parentContainer,
    options,
    label,
    newInputId,
    newInputName
  );
}

function replaceFilterForSelectFilter(
  inputElement,
  newInputId,
  newInputName,
  label,
  options,
  wasSelect = false,
  newInputIdFins = null,
  newInputNameFins = null
) {
  let parentContainer = inputElement.parentElement;
  // si el input es un select, el parentContainer es el div que conté el select
  if (wasSelect && parentContainer.parentElement) {
    parentContainer = parentContainer.parentElement;
  }

  // eliminam els fills per afegir un select com a nou filtre
  while (parentContainer.firstChild) {
    parentContainer.removeChild(parentContainer.firstChild);
  }

  //afegim nou select
  const selectElement = addNewSelectFilterToContainer(
    parentContainer,
    options,
    label,
    newInputId,
    newInputName
  );

  //si era desde i fins, afegim un segon select, ocult i que sempre tengui el mateix valor que el primer
  if (newInputNameFins) {
    const selectElementFins = createSelect(
      newInputIdFins,
      newInputNameFins,
      options
    );
    selectElement.addEventListener("change", () => {
      selectElementFins.value = selectElement.value;
    });
    selectElementFins.style.display = "none";
    parentContainer.appendChild(selectElementFins);
  }
}

function createSelect(id, name, options) {
  const selectElement = document.createElement("select");
  selectElement.classList.add("input-medium");
  selectElement.id = id;
  selectElement.name = name;

  options.forEach((option) => {
    const optionElement = document.createElement("option");
    optionElement.value = option.value;
    optionElement.text = option.text;
    selectElement.appendChild(optionElement);
  });
  return selectElement;
}
