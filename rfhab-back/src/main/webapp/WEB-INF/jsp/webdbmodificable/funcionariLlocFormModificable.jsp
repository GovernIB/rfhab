<script>
  document.addEventListener("DOMContentLoaded", function (event) {
    if ("true" != "${isView}") {
      //afegeix input numero CAI
      const taula = document.getElementById("funcionariLloc_tableid");
      const tbody = taula.querySelector("tbody");
      const nouNumeroCaiTr = tbody.insertRow(-1);
      nouNumeroCaiTr.id = "funcionariLloc_numerocai_rowid";

      const nouTd1 = document.createElement("td");
      nouTd1.id = "funcionariLloc_numerocai_columnlabelid";
      nouTd1.innerHTML =
        '<label style="font-weight:bold; text-align:right;"><fmt:message key="historic.numeroCai"/></label>';
      nouNumeroCaiTr.appendChild(nouTd1);

      const nouTd2 = document.createElement("td");
      nouTd2.id = "funcionariLloc_numerocai_columnvalueid";
      nouTd2.innerHTML =
        '<input type="text" name="numerocai" id="numerocai" class="form-control w-100"></input>';
      nouNumeroCaiTr.appendChild(nouTd2);

      //afegeix input observacions
      const nouObservacionsTr = tbody.insertRow(-1);
      nouObservacionsTr.id = "funcionariLloc_observacions_rowid";

      const nouTdObservacions1 = document.createElement("td");
      nouTdObservacions1.id = "funcionariLloc_observacions_columnlabelid";
      nouTdObservacions1.innerHTML =
        '<label style="font-weight:bold; text-align:right;"><fmt:message key="historic.observacions"/></label>';
      nouObservacionsTr.appendChild(nouTdObservacions1);

      const nouTdObservacions2 = document.createElement("td");
      nouTdObservacions2.id = "funcionariLloc_observacions_columnvalueid";
      nouTdObservacions2.innerHTML =
        '<input type="text" name="observacions" id="observacions" class="form-control w-100"></input>';
      nouObservacionsTr.appendChild(nouTdObservacions2);
    }
  });
</script>
