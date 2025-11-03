function replaceTipusActivitatFilter(options) {
  const inputTipusActivitatLabel = "Tipus: ";

  const inputTipusActivitatName = "tipus";
  const inputAdedFieldTipusActivitatName = "activitat.tipus";
  const inputSelectTipusActivitatId = "activitat_tipus_select";
  const inputSelectTipusActivitatName = "tipusSelect";

  let inputTipusActivitat = document.getElementById(
    inputAdedFieldTipusActivitatName
  );
  if (inputTipusActivitat) {
    replaceFilterForSelectFilter(
      inputTipusActivitat,
      inputAdedFieldTipusActivitatName,
      inputAdedFieldTipusActivitatName,
      inputTipusActivitatLabel,
      options
    );
    return;
  }

  inputTipusActivitat = document.getElementById(inputTipusActivitatName);
  if (inputTipusActivitat) {
    replaceFilterForSelectFilter(
      inputTipusActivitat,
      inputTipusActivitatName,
      inputTipusActivitatName,
      inputTipusActivitatLabel,
      options
    );
    return;
  }

  inputTipusActivitat = document.getElementById(inputSelectTipusActivitatId);
  if (inputTipusActivitat) {
    replaceFilterForSelectFilter(
      inputTipusActivitat,
      inputSelectTipusActivitatName,
      inputSelectTipusActivitatName,
      inputTipusActivitatLabel,
      options,
      true
    );
    return;
  }
}

function replaceFuncionarisFilter(inputFuncionarisLabel, placeholder) {
  inputFuncionarisLabel += ": ";

  const oldInputFuncionarisName = "funcionariIDDesde";
  const inputFuncionarisName = "activitatfuncionarisnom";

  let inputFuncionaris = document.getElementById(oldInputFuncionarisName);
  if (inputFuncionaris) {
    replaceFilterForTextInputFilterWithOtherName(
      inputFuncionaris,
      inputFuncionarisName,
      inputFuncionarisName,
      inputFuncionarisLabel,
      placeholder,
      false
    );
    return;
  }
}

function addFuncionarisNifFilter(inputFuncionarisNifLabel, placeholder) {
  inputFuncionarisNifLabel += ": ";

  const elementWhichParentContainerToDuplicate = "activitatfuncionarisnom";
  const inputFuncionarisNifName = "activitatfuncionarisnif";

  let parentContainerToDuplicate = document.getElementById(
    elementWhichParentContainerToDuplicate
  ).parentElement;
  const parentContainer = parentContainerToDuplicate.cloneNode(false);
  parentContainerToDuplicate.parentElement.insertBefore(
    parentContainer,
    parentContainerToDuplicate.nextSibling
  );
  const textInputElement = addNewTextInputFilterToContainer(
    parentContainer,
    placeholder,
    inputFuncionarisNifLabel,
    inputFuncionarisNifName,
    inputFuncionarisNifName
  );
}
