function addNewSelectFilterToContainer(
  parentContainer,
  options,
  label,
  newInputId,
  newInputName,
  description = null
) {
  //estils per quan son selects o desde i fins
  parentContainer.classList.remove("input-group");
  parentContainer.classList.add("input-prepend");

  //afegim etiqueta amb el títol
  const labelElement = document.createElement("label");
  labelElement.classList.add("add-on");
  labelElement.setAttribute("for", newInputId);
  labelElement.textContent = description ? label + " " + description : label;
  parentContainer.appendChild(labelElement);

  //afegim nou select
  const selectElement = createSelect(newInputId, newInputName, options);
  if (description) {
    selectElement.title = description;
  }
  parentContainer.appendChild(selectElement);

  return selectElement;
}

function addNewTextInputFilterToContainer(
  parentContainer,
  placeholder,
  label,
  newInputId,
  newInputName
) {
  //estils per quan son selects o desde i fins
  parentContainer.classList.remove("input-group");
  parentContainer.classList.add("input-prepend");

  //afegim etiqueta amb el títol
  const labelElement = document.createElement("label");
  labelElement.classList.add("add-on");
  labelElement.setAttribute("for", newInputId);
  labelElement.textContent = label;
  parentContainer.appendChild(labelElement);

  //afegim nou select
  const textInputElement = createTextInput(newInputId, newInputName, placeholder);
  parentContainer.appendChild(textInputElement);

  return textInputElement;
}

function addNewSelectFilterToForm(
  formFilterContainer,
  options,
  label,
  newInputId,
  newInputName,
  description = null
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
    newInputName,
    description
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
  newInputNameFins = null,
  description = null
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
    newInputName,
    description
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

  return selectElement;
}

function replaceFilterForTextInputFilter(
  inputElement,
  newInputId,
  newInputName,
  label,
  placeholder,
  wasSelect = false,
  newInputIdFins = null,
  newInputNameFins = null
) {
  let parentContainer = inputElement.parentElement;
  // si el input es un select, el parentContainer es el div que conté el select
  if (wasSelect && parentContainer.parentElement) {
    parentContainer = parentContainer.parentElement;
  }

  // eliminam els fills per afegir un text input com a nou filtre
  while (parentContainer.firstChild) {
    parentContainer.removeChild(parentContainer.firstChild);
  }

  //afegim nou text input
  const textInputElement = addNewTextInputFilterToContainer(
    parentContainer,
    placeholder,
    label,
    newInputId,
    newInputName
  );

  //si era desde i fins, afegim un segon text input, ocult i que sempre tengui el mateix valor que el primer
  if (newInputNameFins) {
    const textInputElementFins = createTextInput(
      newInputIdFins,
      newInputNameFins,
      placeholder
    );
    textInputElement.addEventListener("change", () => {
      textInputElementFins.value = textInputElement.value;
    });
    textInputElementFins.style.display = "none";
    parentContainer.appendChild(textInputElementFins);
  }
}

function replaceFilterForTextInputFilterWithOtherName(
  inputElement,
  newInputId,
  newInputName,
  label,
  placeholder,
  wasSelect = false
) {
  let parentContainer = inputElement.parentElement;
  // si el input es un select, el parentContainer es el div que conté el select
  if (wasSelect && parentContainer.parentElement) {
    parentContainer = parentContainer.parentElement;
  }

  // eliminam els fills per afegir un text input com a nou filtre
  while (parentContainer.firstChild) {
    parentContainer.removeChild(parentContainer.firstChild);
  }

  //afegim nou text input
  const textInputElement = addNewTextInputFilterToContainer(
    parentContainer,
    placeholder,
    label,
    newInputId,
    newInputName
  );
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

function createTextInput(id, name, placeholder) {
  const inputElement = document.createElement("input");
  inputElement.classList.add("search-query");
  inputElement.classList.add("input-medium");
  inputElement.id = id;
  inputElement.name = name;
  inputElement.placeholder = placeholder;
  inputElement.type = "text";

  return inputElement;
}
