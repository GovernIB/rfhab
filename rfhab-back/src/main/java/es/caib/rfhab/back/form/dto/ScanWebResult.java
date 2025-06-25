package es.caib.rfhab.back.form.dto;

public class ScanWebResult {
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    private String urlFitxer;

    public String getUrlFitxer() {
        return urlFitxer;
    }

    public void setUrlFitxer(String urlFitxer) {
        this.urlFitxer = urlFitxer;
    }

    private String tipusFirma;

    public String getTipusFirma() {
        return tipusFirma;
    }

    public void setTipusFirma(String tipusFirma) {
        this.tipusFirma = tipusFirma;
    }

    private String perfilFirma;

    public String getPerfilFirma() {
        return perfilFirma;
    }

    public void setPerfilFirma(String perfilFirma) {
        this.perfilFirma = perfilFirma;
    }

    public ScanWebResult() {
        this.error = null;
        this.urlFitxer = null;
        this.tipusFirma = null;
        this.perfilFirma = null;
    }

    public ScanWebResult(String error, String urlFitxer, String tipusFirma, String perfilFirma) {
        this.error = error;
        this.urlFitxer = urlFitxer;
        this.tipusFirma = tipusFirma;
        this.perfilFirma = perfilFirma;
    }
}
