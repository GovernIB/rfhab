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
    hideEntireColumn("table.table-genapp-list", dataBaixaHeader);

    const codiLlocHeader = '<fmt:message key="lloc.codiLloc"/>';
    hideEntireColumn("table.table-genapp-list", codiLlocHeader);

    const unitatOrganicaHeader = '<fmt:message key="lloc.unitatID"/>';
    hideEntireColumn("table.table-genapp-list", unitatOrganicaHeader);

    const personalOamrHeader = '<fmt:message key="lloc.personalOamr"/>';
    hideEntireColumn("table.table-genapp-list", personalOamrHeader);

    const inputNomFuncionariPlaceholder = "<fmt:message key="genapp.form.searchby"><fmt:param><fmt:message key="funcionari.nom" /></fmt:param></fmt:message>";
    const inputNomFuncionariLabel = '<fmt:message key="funcionari.nom"/>' + ": ";

    const oldInputFuncionarisNomName = "nom";
    const inputFuncionarisNomName = "funcionarisNom";

    let inputNomFuncionaris = document.getElementById(oldInputFuncionarisNomName);
    if (inputNomFuncionaris) {
      replaceFilterForTextInputFilterWithOtherName(
        inputNomFuncionaris,
        inputFuncionarisNomName,
        inputFuncionarisNomName,
        inputNomFuncionariLabel,
        inputNomFuncionariPlaceholder,
        false
      );
      return;
    }
  });
</script>
