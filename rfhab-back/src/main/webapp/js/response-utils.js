function downloadPdf(blob, returnBlob = false) {
  if (blob instanceof Blob) {
    const link = document.createElement("a");
    const file = window.URL.createObjectURL(blob);
    if (returnBlob) {
      return file;
    } else {
      link.href = file;
      link.download = "document.pdf";
      document.body.appendChild(link);
      link.click();
      document.body.removeChild(link);
    }
  } else {
    console.error("Provided blob is not a valid Blob object.");
  }
}
