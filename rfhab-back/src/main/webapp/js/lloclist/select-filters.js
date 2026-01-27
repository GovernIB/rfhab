function replaceUnitatsFilter(options) {
  const inputUnitatsLabel = "Unitat: ";

  const inputUnitatsName = "unitatIDDesde";
  const inputFinsUnitatsName = "unitatIDFins";

  let inputUnitats = document.getElementById(inputUnitatsName);
  if (inputUnitats) {
    replaceFilterForSelectFilter(
      inputUnitats,
      inputUnitatsName,
      inputUnitatsName,
      inputUnitatsLabel,
      options,
      false,
      inputFinsUnitatsName,
      inputFinsUnitatsName
    );
    return;
  }
}

function replaceOamrFilter() {
  const inputPersonalOamrLabel = "Personal OAMR: ";
  const options = [
    {
      value: "",
      text: "Tots",
    },
    {
      value: "2",
      text: "Si",
    },
    {
      value: "1",
      text: "No",
    },
  ];

  const inputPersonalOamrName = "personalOamr";
  const inputAdedFieldPersonalOamrName = "lloc.personalOamr";
  const inputSelectPersonalOamrId = "lloc_personalOamr_select";
  const inputSelectPersonalOamrName = "personalOamrSelect";

  let inputPersonalOamr = document.getElementById(
    inputAdedFieldPersonalOamrName
  );
  if (inputPersonalOamr) {
    replaceFilterForSelectFilter(
      inputPersonalOamr,
      inputAdedFieldPersonalOamrName,
      inputAdedFieldPersonalOamrName,
      inputPersonalOamrLabel,
      options
    );
    return;
  }

  inputPersonalOamr = document.getElementById(inputPersonalOamrName);
  if (inputPersonalOamr) {
    replaceFilterForSelectFilter(
      inputPersonalOamr,
      inputPersonalOamrName,
      inputPersonalOamrName,
      inputPersonalOamrLabel,
      options
    );
    return;
  }

  inputPersonalOamr = document.getElementById(inputSelectPersonalOamrId);
  if (inputPersonalOamr) {
    replaceFilterForSelectFilter(
      inputPersonalOamr,
      inputSelectPersonalOamrName,
      inputSelectPersonalOamrName,
      inputPersonalOamrLabel,
      options,
      true
    );
    return;
  }
}

function addActiusSelectFilter(filterCookieName) {
  const options = [
    {
      value: "",
      text: "Tots",
    },
    {
      value: "1",
      text: "Sí",
    },
    {
      value: "0",
      text: "No",
    },
  ];
  const label = "Actius: ";

  let formFilterContainer = document.querySelector("#FilterDiv > .form-inline");
  const actiusSelect = addNewSelectFilterToForm(
    formFilterContainer,
    options,
    label,
    "actius-segons-databaixa-id",
    "actiusSegonsDatabaixaName"
  );

  if (actiusSelect && filterCookieName) {
    //valor per defecte
    document.cookie =
      filterCookieName +
      "=" +
      encodeURIComponent(actiusSelect.value) +
      "; path=/; Secure; SameSite=Strict";

    actiusSelect.addEventListener("change", function () {
      document.cookie =
        filterCookieName +
        "=" +
        encodeURIComponent(actiusSelect.value) +
        "; path=/; Secure; SameSite=Strict";
    });
  }

  return actiusSelect;
}

function addAssignatsLlocSelectFilter(filterCookieName) {
  const options = [
    {
      value: "",
      text: "Tots",
    },
    {
      value: "1",
      text: "Sí",
    },
    {
      value: "0",
      text: "No",
    },
  ];
  const label = "Assignats a Lloc: ";

  let formFilterContainer = document.querySelector("#FilterDiv > .form-inline");
  const actiusSelect = addNewSelectFilterToForm(
    formFilterContainer,
    options,
    label,
    "assignats-a-lloc-id",
    "assignatsAllocName"
  );

  if (actiusSelect && filterCookieName) {
    //valor per defecte
    document.cookie =
      filterCookieName +
      "=" +
      encodeURIComponent(actiusSelect.value) +
      "; path=/; Secure; SameSite=Strict";

    actiusSelect.addEventListener("change", function () {
      document.cookie =
        filterCookieName +
        "=" +
        encodeURIComponent(actiusSelect.value) +
        "; path=/; Secure; SameSite=Strict";
    });
  }
}

function addOamrSelectFilter(filterCookieName) {
  const inputPersonalOamrLabel = "OAMR: ";
  const options = [
    {
      value: "",
      text: "Tots",
    },
    {
      value: "2",
      text: "Sí",
    },
    {
      value: "1",
      text: "No",
    },
  ];
  const inputAdedFieldPersonalOamrName = "lloc.personalOamr";

  let formFilterContainer = document.querySelector("#FilterDiv > .form-inline");
  const actiusSelect = addNewSelectFilterToForm(
    formFilterContainer,
    options,
    inputPersonalOamrLabel,
    inputAdedFieldPersonalOamrName,
    inputAdedFieldPersonalOamrName
  );

  if (actiusSelect && filterCookieName) {
    //valor per defecte
    document.cookie =
      filterCookieName +
      "=" +
      encodeURIComponent(actiusSelect.value) +
      "; path=/; Secure; SameSite=Strict";

    actiusSelect.addEventListener("change", function () {
      document.cookie =
        filterCookieName +
        "=" +
        encodeURIComponent(actiusSelect.value) +
        "; path=/; Secure; SameSite=Strict";
    });
  }
}
