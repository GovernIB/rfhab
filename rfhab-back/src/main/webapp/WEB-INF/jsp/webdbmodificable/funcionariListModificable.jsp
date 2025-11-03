<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants
  var="FuncionariFields"
  className="es.caib.rfhab.model.fields.FuncionariFields"
/>

<style type="text/css">
  .search-query {
    width: auto !important;
  }
</style>

<span id="data-baixa-header-traduit-id" style="display: none"
  ><fmt:message key="${FuncionariFields.DATABAIXA.codeLabel}" />
</span>

<script type="text/javascript">
  $(document).ready(function () {
    document.getElementById("FilterDiv").style.display = "inherit";
    document.getElementById("FilterButton").style.display = "none";

    addOamrSelectFilter();
    addActiusSelectFilter();
    addAssignatsLlocSelectFilter();

    const dataBaixaHeader = document
      .getElementById("data-baixa-header-traduit-id")
      .textContent.trim();
    const columnaDataBaixa = getColumnIndexByHeaderText(
      "table.table-genapp-list",
      dataBaixaHeader
    );
    highlightRowIfTdHasContent("tr[id^='funcionari_rowid']", columnaDataBaixa);
  });
</script>
