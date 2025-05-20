<%@page import="es.caib.rfhab.commons.utils.FiltresCookies"%>

<style type="text/css">
  .search-query {
    width: auto !important;
  }
</style>

<script type="text/javascript">
  $(document).ready(function () {
    document.getElementById("FilterDiv").style.display = "inherit";
    document.getElementById("FilterButton").style.display = "none";

    replaceOamrFilter();
    addActiusSelectFilter("${FiltresCookies.FILTRE_FUNCIONARIS_DATA_BAIXA_ACTIUS_COOKIE_NAME}");
  });
</script>
