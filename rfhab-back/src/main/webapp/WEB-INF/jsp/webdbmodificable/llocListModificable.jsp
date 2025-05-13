<style type="text/css">
  .search-query {
    width: auto !important;
  }
</style>

<script type="text/javascript">
  function replaceOamrFilter() {
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
        inputAdedFieldPersonalOamrName
      );
      return;
    }

    inputPersonalOamr = document.getElementById(inputPersonalOamrName);
    if (inputPersonalOamr) {
      replaceFilterForSelectFilter(
        inputPersonalOamr,
        inputPersonalOamrName,
        inputPersonalOamrName
      );
      return;
    }

    inputPersonalOamr = document.getElementById(inputSelectPersonalOamrId);
    if (inputPersonalOamr) {
      replaceFilterForSelectFilter(
        inputPersonalOamr,
        inputSelectPersonalOamrName,
        inputSelectPersonalOamrName,
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
