<%@ page import="es.caib.rfhab.commons.utils.Constants" %> <% String
filtre_actius_vperdefecte =
(String)session.getAttribute(Constants.ATTR_FILTRE_ACTIUS_VALOR_PER_DEFECTE); %>

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
    const actiusSelectFilter = addActiusSelectFilter();
    const filtreActiusValorPerDefecte = '<%=filtre_actius_vperdefecte%>';
    console.log("filtreActiusValorPerDefecte: " + filtreActiusValorPerDefecte);
    if(filtreActiusValorPerDefecte && filtreActiusValorPerDefecte != "null"){
      actiusSelectFilter.value = filtreActiusValorPerDefecte;
    }

    const darreraModificacio = '<fmt:message key="darreramodificacio"/>';
    // #105 Si es volen ordenar per darrera modificació, s'ha de mostrar aquesta columna, perquè si es seleccionés la ordenació a una altra columna, no es podria tornar a darrera modificació.
    // hideEntireColumn("table.table-genapp-list", darreraModificacio);
  });
</script>
