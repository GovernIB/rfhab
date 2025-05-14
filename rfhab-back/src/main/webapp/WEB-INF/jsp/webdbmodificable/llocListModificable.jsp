<style type="text/css">
  .search-query {
    width: auto !important;
  }
</style>

<script type="text/javascript">
  function replaceOamrFilter() {
    const inputPersonalOamrLabel = "Personal OAMR: ";
    const options = [
      {
        value: "",
        text: "Tots",
      },
      {
        value: "1",
        text: "Si",
      },
      {
        value: "0",
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

  $(document).ready(function () {
    document.getElementById("FilterDiv").style.display = "inherit";
    document.getElementById("FilterButton").style.display = "none";

    replaceOamrFilter();
  });
</script>
