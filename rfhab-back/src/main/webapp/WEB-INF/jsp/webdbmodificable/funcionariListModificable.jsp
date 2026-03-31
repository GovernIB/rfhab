<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<%@ page import="es.caib.rfhab.commons.utils.Constants" %>
<un:useConstants
  var="FuncionariFields"
  className="es.caib.rfhab.model.fields.FuncionariFields"
/>
<% String
filtre_foamr_vperdefecte =
(String)session.getAttribute(Constants.ATTR_FILTRE_FOAMR_VALOR_PER_DEFECTE); %>
<% String
filtre_factius_vperdefecte =
(String)session.getAttribute(Constants.ATTR_FILTRE_FACTIUS_VALOR_PER_DEFECTE); %>
<% String
filtre_fassignats_vperdefecte =
(String)session.getAttribute(Constants.ATTR_FILTRE_FASSIGNATS_VALOR_PER_DEFECTE); %>

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

    const oamrSelectFilter = addOamrSelectFilter();
    const filtreOamrValorPerDefecte = '<%=filtre_foamr_vperdefecte%>';
    console.log("filtreOamrValorPerDefecte: " + filtreOamrValorPerDefecte);
    if(filtreOamrValorPerDefecte && filtreOamrValorPerDefecte != "null" && oamrSelectFilter){
      oamrSelectFilter.value = filtreOamrValorPerDefecte;
    }

    const actiusSelectFilter = addActiusSelectFilter(null, '<fmt:message key="tots"/>', '<fmt:message key="lloc.filtres.habilitats"/>');
    const filtreActiusValorPerDefecte = '<%=filtre_factius_vperdefecte%>';
    console.log("filtreActiusValorPerDefecte: " + filtreActiusValorPerDefecte);
    if(filtreActiusValorPerDefecte && filtreActiusValorPerDefecte != "null" && actiusSelectFilter){
      actiusSelectFilter.value = filtreActiusValorPerDefecte;
    }

    const assignatsSelectFilter = addAssignatsLlocSelectFilter();
    const filtreAssignatsValorPerDefecte = '<%=filtre_fassignats_vperdefecte%>';
    console.log("filtreUnitatsoValorPerDefecte: " + filtreAssignatsValorPerDefecte);
    if(filtreAssignatsValorPerDefecte && filtreAssignatsValorPerDefecte != "null" && assignatsSelectFilter){
      assignatsSelectFilter.value = filtreAssignatsValorPerDefecte;
    }


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
