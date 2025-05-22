<style type="text/css">
  .search-query {
    width: auto !important;
  }
</style>

<script type="text/javascript">
    const unitatsOptions = [];
    console.log("unitatsFiltreCerca: ${unitatsFiltreCerca}");//todo:eliminar
    <c:forEach items="${unitatsFiltreCerca}" var="unitat">
      console.log("unitat: ${unitat.key} - ${unitat.value}");
      unitatsOptions.push({
        value: "${unitat.key}",
        text: "${unitat.value}",
      });
    </c:forEach>
    //todo:eliminar
    console.log("unitats: ${unitats}");
    <c:forEach items="${unitats}" var="unitat">
      console.log("unitat2: ${unitat.unitatID} - ${unitat.codi} ${unitat.cooficial}");
    </c:forEach>

  $(document).ready(function () {
    document.getElementById("FilterDiv").style.display = "inherit";
    document.getElementById("FilterButton").style.display = "none";


    replaceOamrFilter();
    replaceUnitatsFilter(unitatsOptions);
    addActiusSelectFilter();
  });
</script>
