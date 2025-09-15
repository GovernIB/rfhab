function createSelectForInputForm(
  id,
  name,
  options,
  onChangeCallback,
  multiple
) {
  const selectElement = document.createElement("select");
  selectElement.classList.add("form-control");
  selectElement.classList.add("col-md-9-optional");
  selectElement.id = id;
  selectElement.name = name;
  selectElement.multiple = multiple;
  selectElement.onchange = onChangeCallback;

  options.forEach((option) => {
    const optionElement = document.createElement("option");
    optionElement.value = option.value;
    optionElement.text = option.text;
    if (option.selected) {
      optionElement.selected = option.selected;
      console.log("found selected option " + option.text, option.value);
    }
    selectElement.appendChild(optionElement);
  });
  return selectElement;
}

function createTrInputFormSelect(
  trId,
  trLabelId,
  labelText,
  trColumnId,
  invisibleInputSelectId,
  selectId,
  selectName,
  options,
  optionsSelected,
  onChangeCallback,
  multiple
) {
  const nouTr = document.createElement("tr");
  nouTr.id = trId;

  const nouTd1 = document.createElement("td");
  nouTd1.id = trLabelId;
  nouTd1.innerHTML =
    '<label style="font-weight:bold; text-align:right;">' +
    labelText +
    "</label>";
  nouTr.appendChild(nouTd1);

  const nouInputInvisible = document.createElement("input");
  nouInputInvisible.id = invisibleInputSelectId;
  nouInputInvisible.type = "hidden";
  nouInputInvisible.classList.add("form-control");
  nouInputInvisible.name = invisibleInputSelectId;
  const nouTd2 = document.createElement("td");
  nouTd2.id = trColumnId;

  if (optionsSelected && optionsSelected.length > 0) {
    nouInputInvisible.value = optionsSelected
      .map((option) => option)
      .join(",");
    //marca selected a les opcions
    options.forEach((opt) => {
      if (optionsSelected.includes(opt.value)) {
        opt.selected = true;
      }
    });
  } else {
    nouInputInvisible.value = optionsSelected;
  }

  const selectForInput = createSelectForInputForm(
    selectId,
    selectName,
    options,
    onChangeCallback,
    multiple
  );
  selectForInput.value = optionsSelected;

  nouTd2.appendChild(selectForInput);
  nouTd2.appendChild(nouInputInvisible);
  nouTr.appendChild(nouTd2);
  return nouTr;
}
