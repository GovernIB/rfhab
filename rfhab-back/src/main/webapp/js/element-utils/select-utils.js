function displayOptionsFromSelect(element) {
  let txt = "All options: ";
  for (let i = 0; i < element.length; i++) {
    txt =
      txt + "\n" + element.options[i].text + " - " + element.options[i].value;
  }
  return txt;
}
