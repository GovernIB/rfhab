function highlightRowIfTdHasContent(rowsQuerySelector, i) {
  // Selecciona tots els rowsQuerySelector
  var rows = document.querySelectorAll(rowsQuerySelector);
  rows.forEach(function (row) {
    var tds = row.getElementsByTagName("td");
    if (tds.length > i && tds[i].textContent.trim() !== "") {
      // Si el contingut de la cel·la no és buit, aplica l'estil
      row.style.backdropFilter = "brightness(0.85)"; // gris
      // tds[i].style.color = "#fff"; // text blanc per contrast//
      tds[i].style.color = "dimgray";
    }
  });
}

function getColumnIndexByHeaderText(tableQuerySelector, headerText) {
  // Troba la taula amb tableQuerySelector
  var table = document.querySelector(tableQuerySelector);
  if (!table) return -1;

  // Troba tots els <th> dins del primer <tr> del <thead>
  var ths = table.querySelectorAll("thead tr th");
  for (var i = 0; i < ths.length; i++) {
    var span = ths[i].querySelector("span");
    if (span && span.textContent.trim() === headerText) {
      return i;
    }
  }
  return -1; // No trobat
}

function hideEntireColumn(tableQuerySelector, thContentSearchBy){
  var table = document.getElementById(tableQuerySelector);
    if (!table) return;

    var columnIndex = getColumnIndexByHeaderText(tableQuerySelector, thContentSearchBy);
    if (columnIndex === -1) return;

    // Amaga el <th>
    var ths = table.querySelectorAll("thead tr th");
    if (ths[columnIndex]) {
      ths[columnIndex].style.display = "none";
    }

    // Amaga totes les <td> de la columna
    var rows = table.querySelectorAll("tbody tr");
    rows.forEach(function(row) {
      var tds = row.getElementsByTagName("td");
      if (tds[columnIndex]) {
        tds[columnIndex].style.display = "none";
      }
    });
}