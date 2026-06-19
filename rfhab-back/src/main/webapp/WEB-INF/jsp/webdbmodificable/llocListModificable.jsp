<%@ page import="es.caib.rfhab.commons.utils.Constants" %>
<% String
filtre_oamr_vperdefecte =
(String)session.getAttribute(Constants.ATTR_FILTRE_OAMR_VALOR_PER_DEFECTE); %>
<% String
filtre_actius_vperdefecte =
(String)session.getAttribute(Constants.ATTR_FILTRE_ACTIUS_VALOR_PER_DEFECTE); %>
<% String
filtre_unitatsuperior_vperdefecte =
(String)session.getAttribute(Constants.ATTR_FILTRE_UNITATSUPERIOR_VALOR_PER_DEFECTE); %>
<% String
filtre_unitatso_vperdefecte =
(String)session.getAttribute(Constants.ATTR_FILTRE_UNITATSO_VALOR_PER_DEFECTE); %>

<style type="text/css">
  .search-query {
    width: auto !important;
  }
</style>

<script type="text/javascript">
    <c:set var="nomAttrUnitats" value="<%= Constants.NOM_ATTR_FILTRE_UNITATS %>"/>
    <c:set var="nomAttrUnitatSuperior" value="<%= Constants.NOM_ATTR_FILTRE_UNITATS_SUPERIORS %>"/>
    const unitatsOptions = [];
    <c:forEach items="${requestScope[nomAttrUnitats]}" var="unitat">
      console.log("unitat: ${unitat.key} - ${unitat.value}");
      unitatsOptions.push({
        value: "${unitat.key}",
        text: "${unitat.value}",
      });
    </c:forEach>
    const unitatsSuperiorOptions = [];
    <c:forEach items="${requestScope[nomAttrUnitatSuperior]}" var="unitat">
      console.log("unitat: ${unitat.key} - ${unitat.value}");
      unitatsSuperiorOptions.push({
        value: "${unitat.key}",
        text: "${unitat.value}",
      });
    </c:forEach>

  $(document).ready(function () {
    document.getElementById("FilterDiv").style.display = "inherit";
    document.getElementById("FilterButton").style.display = "none";


    const oamrSelectFilter = replaceOamrFilter();
    const filtreOamrValorPerDefecte = '<%=filtre_oamr_vperdefecte%>';
    console.log("filtreOamrValorPerDefecte: " + filtreOamrValorPerDefecte);
    if(filtreOamrValorPerDefecte && filtreOamrValorPerDefecte != "null" && oamrSelectFilter){
      oamrSelectFilter.value = filtreOamrValorPerDefecte;
    }

    const actiusSelectFilter = addActiusSelectFilter(null, '<fmt:message key="tots"/>', '<fmt:message key="lloc.filtres.habilitats"/>');
    const filtreActiusValorPerDefecte = '<%=filtre_actius_vperdefecte%>';
    console.log("filtreActiusValorPerDefecte: " + filtreActiusValorPerDefecte);
    if(filtreActiusValorPerDefecte && filtreActiusValorPerDefecte != "null" && actiusSelectFilter){
      actiusSelectFilter.value = filtreActiusValorPerDefecte;
    }

    const unitatSuperiorSelectFilter = addUnitatsSuperiorSelectFilter(null, unitatsSuperiorOptions, '<fmt:message key="lloc.filtres.unitatSuperior"/>');
    const filtreUnitatSuperiorValorPerDefecte = '<%=filtre_unitatsuperior_vperdefecte%>';
    console.log("filtreUnitatSuperiorValorPerDefecte: " + filtreUnitatSuperiorValorPerDefecte);
    if(filtreUnitatSuperiorValorPerDefecte && filtreUnitatSuperiorValorPerDefecte != "null" && unitatSuperiorSelectFilter){
      unitatSuperiorSelectFilter.value = filtreUnitatSuperiorValorPerDefecte;
    }

    const unitatsoSelectFilter = replaceUnitatsFilter(unitatsOptions);
    const filtreUnitatsoValorPerDefecte = '<%=filtre_unitatso_vperdefecte%>';
    console.log("filtreUnitatsoValorPerDefecte: " + filtreUnitatsoValorPerDefecte);
    if(filtreUnitatsoValorPerDefecte && filtreUnitatsoValorPerDefecte != "null" && unitatsoSelectFilter){
      unitatsoSelectFilter.value = filtreUnitatsoValorPerDefecte;
    }

    const darreraModificacio = '<fmt:message key="darreramodificacio"/>';
    const codiLf = '<fmt:message key="lloc.codiLlocPropi"/>';
    const codiFp = '<fmt:message key="lloc.codiLloc"/>';
    const dataAlta = "<fmt:message key='lloc.dataalta'/>";
    const unitatSuperior = '<fmt:message key="unitat.superior"/>';
    const unitatOrganica = '<fmt:message key="unitat.unitat"/>';
    // #105 Si es volen ordenar per darrera modificació, s'ha de mostrar aquesta columna, perquè si es seleccionés la ordenació a una altra columna, no es podria tornar a darrera modificació.
    // hideEntireColumn("table.table-genapp-list", darreraModificacio);
    moveColumnByHeaderText("table.table-genapp-list", codiFp, codiLf, "after");
    moveColumnByHeaderText("table.table-genapp-list", unitatSuperior, unitatOrganica, "after");

    function getDateOnlyText(text) {
      const normalizedText = text ? text.replace(/\u00a0/g, " ").trim() : "";
      if (!normalizedText) {
        return "";
      }

      const dateTimeMatch = normalizedText.match(/^(\d{1,2}\/\d{1,2}\/\d{2,4})\s+\d{1,2}:\d{2}(:\d{2})?$/);
      if (dateTimeMatch) {
        return dateTimeMatch[1];
      }

      return normalizedText.split(/\s+/)[0];
    }

    function formatDataAltaColumn() {
      const tableSelector = "table.table-genapp-list";
      const dataAltaColumnIndex = getColumnIndexByHeaderText(tableSelector, dataAlta);
      const rows = document.querySelectorAll(tableSelector + " tbody tr");

      rows.forEach(function (row) {
        const tds = row.getElementsByTagName("td");
        if (!tds.length) {
          return;
        }

        if (dataAltaColumnIndex !== -1 && tds.length > dataAltaColumnIndex) {
          const dataAltaCell = tds[dataAltaColumnIndex];
          const currentText = dataAltaCell.textContent ? dataAltaCell.textContent.replace(/\u00a0/g, " ").trim() : "";
          const dateOnlyText = getDateOnlyText(dataAltaCell.textContent);
          if (dateOnlyText && currentText !== dateOnlyText) {
            dataAltaCell.textContent = dateOnlyText;
          }
          return;
        }

        // Fallback: if header lookup fails, normalize any datetime cell value in this row.
        for (let i = 0; i < tds.length; i++) {
          const cellText = tds[i].textContent ? tds[i].textContent.replace(/\u00a0/g, " ").trim() : "";
          if (/^\d{1,2}\/\d{1,2}\/\d{2,4}\s+\d{1,2}:\d{2}(:\d{2})?$/.test(cellText)) {
            const dateOnlyText = getDateOnlyText(cellText);
            if (dateOnlyText && cellText !== dateOnlyText) {
              tds[i].textContent = dateOnlyText;
            }
          }
        }
      });
    }

    formatDataAltaColumn();
    setTimeout(formatDataAltaColumn, 0);

    const tableBody = document.querySelector("table.table-genapp-list tbody");
    if (tableBody && window.MutationObserver) {
      const observer = new MutationObserver(function () {
        formatDataAltaColumn();
      });
      observer.observe(tableBody, { childList: true, subtree: true });
    }
  });
</script>
