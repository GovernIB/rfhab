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
  const table = document.querySelector(tableQuerySelector);
  if (!table) return -1;

  // Troba tots els <th> dins del primer <tr> del <thead>
  const ths = table.querySelectorAll("thead tr th");
  for (let i = 0; i < ths.length; i++) {
    // const span = ths[i].querySelector("span");
    // if (span && span.textContent.trim() === headerText) {
    //   return i;
    // }
    const text = ths[i].textContent;
    if (text && text.trim() === headerText) {
      return i;
    }
  }
  return -1; // No trobat
}

function hideEntireColumn(tableQuerySelector, thContentSearchBy) {
  const table = document.querySelector(tableQuerySelector);
  if (!table) return;

  const columnIndex = getColumnIndexByHeaderText(
    tableQuerySelector,
    thContentSearchBy
  );
  if (columnIndex === -1) return;
  
  // Amaga el <th>
  const ths = table.querySelectorAll("thead tr th");
  if (ths[columnIndex]) {
    ths[columnIndex].style.display = "none";
  }

  // Amaga totes les <td> de la columna
  const rows = table.querySelectorAll("tbody tr");
  rows.forEach(function (row) {
    const tds = row.getElementsByTagName("td");
    if (tds[columnIndex]) {
      tds[columnIndex].style.display = "none";
    }
  });
}

function moveColumnByHeaderText(
  tableQuerySelector,
  titolColumnaAmoure,
  titolColumnaAmoureDevora,
  position
) {
  const table = document.querySelector(tableQuerySelector);
  if (!table) return;

  const indexToMove = getColumnIndexByHeaderText(
    tableQuerySelector,
    titolColumnaAmoure
  );
  const indexReference = getColumnIndexByHeaderText(
    tableQuerySelector,
    titolColumnaAmoureDevora
  );

  if (indexToMove === -1 || indexReference === -1 || indexToMove === indexReference) {
    return;
  }

  const normalizedPosition = String(position || "before").toLowerCase();
  const moveAfter = normalizedPosition === "after" || normalizedPosition === "darrera";

  // Mou la cel·la de cada fila (th/td) mantenint la mateixa estructura de columna.
  const rows = table.querySelectorAll("tr");
  rows.forEach(function (row) {
    const cells = row.children;
    const sourceCell = cells[indexToMove];
    const referenceCell = cells[indexReference];
    if (!sourceCell || !referenceCell) return;

    if (moveAfter) {
      row.insertBefore(sourceCell, referenceCell.nextSibling);
      return;
    }

    row.insertBefore(sourceCell, referenceCell);
  });
}

/*
 * Convert data array to CSV string
 * @param arr {Array} - the actual data
 * @param columnCount {Number} - the amount to split the data into columns
 * @param initial {String} - initial string to append to CSV string
 * return {String} - ready CSV string
 */
function prepCSVRow(arr, columnCount, initial) {
  var row = ""; // this will hold data
  var delimeter = ";"; // data slice separator, in excel it's `;`, in usual CSv it's `,`
  var newLine = "\r\n"; // newline separator for CSV row

  /*
   * Convert [1,2,3,4] into [[1,2], [3,4]] while count is 2
   * @param _arr {Array} - the actual array to split
   * @param _count {Number} - the amount to split
   * return {Array} - splitted array
   */
  function splitArray(_arr, _count) {
    var splitted = [];
    var result = [];
    _arr.forEach(function (item, idx) {
      if ((idx + 1) % _count === 0) {
        splitted.push(item);
        result.push(splitted);
        splitted = [];
      } else {
        splitted.push(item);
      }
    });
    return result;
  }
  var plainArr = splitArray(arr, columnCount);
  // don't know how to explain this
  // you just have to like follow the code
  // and you understand, it's pretty simple
  // it converts `['a', 'b', 'c']` to `a,b,c` string
  plainArr.forEach(function (arrItem) {
    arrItem.forEach(function (item, idx) {
      row += item + (idx + 1 === arrItem.length ? "" : delimeter);
    });
    row += newLine;
  });
  return initial + row;
}

function downloadDataFromTable(dataTableSelector, filename) {
  var titles = [];
  var data = [];

  /*
   * Get the table headers, this will be CSV headers
   * The count of headers will be CSV string separator
   */
  $(dataTableSelector + " th").each(function () {
    titles.push($(this).text());
  });

  /*
   * Get the actual data, this will contain all the data, in 1 array
   */
  $(dataTableSelector + " td").each(function () {
    data.push($(this).text());
  });

  /*
   * Convert our data to CSV string
   */
  var CSVString = prepCSVRow(titles, titles.length, "");
  CSVString = prepCSVRow(data, titles.length, CSVString);

  /*
   * Make CSV downloadable
   */
  var downloadLink = document.createElement("a");
  var blob = new Blob(["\ufeff", CSVString]);
  var url = URL.createObjectURL(blob);
  downloadLink.href = url;
  downloadLink.download = filename;

  /*
   * Actually download CSV
   */
  document.body.appendChild(downloadLink);
  downloadLink.click();
  document.body.removeChild(downloadLink);
}
