function displayOptionsFromSelect(element) {
  let txt = "All options: ";
  for (let i = 0; i < element.length; i++) {
    txt =
      txt + "\n" + element.options[i].text + " - " + element.options[i].value;
  }
  return txt;
}

function getSelectedOptions(sel) {
  var opts = [],
    opt;
  var len = sel.options.length;
  for (var i = 0; i < len; i++) {
    opt = sel.options[i];

    if (opt.selected) {
      opts.push(opt);
    }
  }

  return opts;
}
