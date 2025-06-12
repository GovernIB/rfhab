function downloadPdf(blob) {
  if (blob instanceof Blob) {
    const link = document.createElement("a");
    link.href = window.URL.createObjectURL(blob);
    link.download = "document.pdf";
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
  } else {
    console.error("Provided blob is not a valid Blob object.");
  }
}
