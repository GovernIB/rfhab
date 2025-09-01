package es.caib.rfhab.logic.utils.GeneracioModelConsentimentTramits;
import java.util.Date;

public class TramitConsentimentDAO {
    private String interessatNom;
    private String interessatLlinatge1;
    private String interessatLlinatge2;
    private String interessatNif;
    private String representantNom;
    private String representantLlinatge1;
    private String representantLlinatge2;
    private String representantNif;

    private String funcionariNom;
    private String funcionariLlinatge1;
    private String funcionariLlinatge2;
    private String funcionariCodi;
    
    private String procedimentNom;
    private String procedimentCodi;
    private String tramitNom;
    private String tramitCodi;
    private Date dataTramit;

    private String interessatAdreca;
    private String interessatAdrecaNumero;
    private String interessatAdrecaEscala;
    private String interessatAdrecaPis;
    private String interessatAdrecaPorta;
    private String interessatAdrecaMunicipi;
    private String interessatAdrecaCodiPostal;
    private String interessatTelefon;
    private String interessatCorreu;
    private String representantAdreca;
    private String representantAdrecaNumero;
    private String representantAdrecaEscala;
    private String representantAdrecaPis;
    private String representantAdrecaPorta;
    private String representantAdrecaMunicipi;
    private String representantAdrecaCodiPostal;
    private String representantTelefon;
    private String representantCorreu;
    private String representantMitjaAcreditacio;
    private String representantRea;
    private String representantAltres;

    // Constructor buit
    public TramitConsentimentDAO() {
    }

    // Constructor amb totes les propietats
    public TramitConsentimentDAO(String interessatNom, String interessatLlinatge1, String interessatLlinatge2,
            String interessatNif, String representantNom, String representantLlinatge1, String representantLlinatge2,
            String representantNif, String funcionariNom, String funcionariLlinatge1, String funcionariLlinatge2,
            String funcionariCodi, String procedimentNom, String procedimentCodi, String tramitNom, String tramitCodi, Date dataTramit,

            String interessatAdreca, String interessatAdrecaNumero, String interessatAdrecaEscala, String interessatAdrecaPis, String interessatAdrecaPorta,
            String interessatAdrecaMunicipi, String interessatAdrecaCodiPostal, String interessatTelefon, String interessatCorreu,
            String representantAdreca, String representantAdrecaNumero, String representantAdrecaEscala, String representantAdrecaPis, String representantAdrecaPorta,
            String representantAdrecaMunicipi, String representantAdrecaCodiPostal, String representantTelefon, String representantCorreu,
            String representantMitjaAcreditacio, String representantRea, String representantAltres
    ) {
        this.interessatNom = interessatNom;
        this.interessatLlinatge1 = interessatLlinatge1;
        this.interessatLlinatge2 = interessatLlinatge2;
        this.interessatNif = interessatNif;
        this.representantNom = representantNom;
        this.representantLlinatge1 = representantLlinatge1;
        this.representantLlinatge2 = representantLlinatge2;
        this.representantNif = representantNif;
        this.funcionariNom = funcionariNom;
        this.funcionariLlinatge1 = funcionariLlinatge1;
        this.funcionariLlinatge2 = funcionariLlinatge2;
        this.funcionariCodi = funcionariCodi;
        this.procedimentNom = procedimentNom;
        this.procedimentCodi = procedimentCodi;
        this.tramitNom = tramitNom;
        this.tramitCodi = tramitCodi;
        this.dataTramit = dataTramit;

        this.interessatAdreca = interessatAdreca;
        this.interessatAdrecaNumero = interessatAdrecaNumero;
        this.interessatAdrecaEscala = interessatAdrecaEscala;
        this.interessatAdrecaPis = interessatAdrecaPis;
        this.interessatAdrecaPorta = interessatAdrecaPorta;
        this.interessatAdrecaMunicipi = interessatAdrecaMunicipi;
        this.interessatAdrecaCodiPostal = interessatAdrecaCodiPostal;
        this.interessatTelefon = interessatTelefon;
        this.interessatCorreu = interessatCorreu;
        this.representantAdreca = representantAdreca;
        this.representantAdrecaNumero = representantAdrecaNumero;
        this.representantAdrecaEscala = representantAdrecaEscala;
        this.representantAdrecaPis = representantAdrecaPis;
        this.representantAdrecaPorta = representantAdrecaPorta;
        this.representantAdrecaMunicipi = representantAdrecaMunicipi;
        this.representantAdrecaCodiPostal = representantAdrecaCodiPostal;
        this.representantTelefon = representantTelefon;
        this.representantCorreu = representantCorreu;
        this.representantMitjaAcreditacio = representantMitjaAcreditacio;
        this.representantRea = representantRea;
        this.representantAltres = representantAltres;
    }

    public String getInteressatNom() {
        return interessatNom;
    }

    public void setInteressatNom(String interessatNom) {
        this.interessatNom = interessatNom;
    }

    public String getInteressatLlinatge1() {
        return interessatLlinatge1;
    }

    public void setInteressatLlinatge1(String interessatLlinatge1) {
        this.interessatLlinatge1 = interessatLlinatge1;
    }

    public String getInteressatLlinatge2() {
        return interessatLlinatge2;
    }

    public void setInteressatLlinatge2(String interessatLlinatge2) {
        this.interessatLlinatge2 = interessatLlinatge2;
    }

    public String getInteressatNif() {
        return interessatNif;
    }

    public void setInteressatNif(String interessatNif) {
        this.interessatNif = interessatNif;
    }

    public String getRepresentantNom() {
        return representantNom;
    }

    public void setRepresentantNom(String representantNom) {
        this.representantNom = representantNom;
    }

    public String getRepresentantLlinatge1() {
        return representantLlinatge1;
    }

    public void setRepresentantLlinatge1(String representantLlinatge1) {
        this.representantLlinatge1 = representantLlinatge1;
    }

    public String getRepresentantLlinatge2() {
        return representantLlinatge2;
    }

    public void setRepresentantLlinatge2(String representantLlinatge2) {
        this.representantLlinatge2 = representantLlinatge2;
    }

    public String getRepresentantNif() {
        return representantNif;
    }

    public void setRepresentantNif(String representantNif) {
        this.representantNif = representantNif;
    }

    public String getFuncionariNom() {
        return funcionariNom;
    }

    public void setFuncionariNom(String funcionariNom) {
        this.funcionariNom = funcionariNom;
    }

    public String getFuncionariLlinatge1() {
        return funcionariLlinatge1;
    }

    public void setFuncionariLlinatge1(String funcionariLlinatge1) {
        this.funcionariLlinatge1 = funcionariLlinatge1;
    }

    public String getFuncionariLlinatge2() {
        return funcionariLlinatge2;
    }

    public void setFuncionariLlinatge2(String funcionariLlinatge2) {
        this.funcionariLlinatge2 = funcionariLlinatge2;
    }

    public String getFuncionariCodi() {
        return funcionariCodi;
    }

    public void setFuncionariCodi(String funcionariCodi) {
        this.funcionariCodi = funcionariCodi;
    }

    public String getProcedimentNom() {
        return procedimentNom;
    }

    public void setProcedimentNom(String procedimentNom) {
        this.procedimentNom = procedimentNom;
    }

    public String getProcedimentCodi() {
        return procedimentCodi;
    }

    public void setProcedimentCodi(String procedimentCodi) {
        this.procedimentCodi = procedimentCodi;
    }

    public String getTramitNom() {
        return tramitNom;
    }

    public void setTramitNom(String tramitNom) {
        this.tramitNom = tramitNom;
    }

    public String getTramitCodi() {
        return tramitCodi;
    }

    public void setTramitCodi(String tramitCodi) {
        this.tramitCodi = tramitCodi;
    }

    public Date getDataTramit() {
        return dataTramit;
    }

    public void setDataTramit(Date dataTramit) {
        this.dataTramit = dataTramit;
    }

    // Getters i setters de les noves propietats
    public String getInteressatAdreca() { return interessatAdreca; }
    public void setInteressatAdreca(String interessatAdreca) { this.interessatAdreca = interessatAdreca; }

    public String getInteressatAdrecaNumero() { return interessatAdrecaNumero; }
    public void setInteressatAdrecaNumero(String interessatAdrecaNumero) { this.interessatAdrecaNumero = interessatAdrecaNumero; }

    public String getInteressatAdrecaEscala() { return interessatAdrecaEscala; }
    public void setInteressatAdrecaEscala(String interessatAdrecaEscala) { this.interessatAdrecaEscala = interessatAdrecaEscala; }

    public String getInteressatAdrecaPis() { return interessatAdrecaPis; }
    public void setInteressatAdrecaPis(String interessatAdrecaPis) { this.interessatAdrecaPis = interessatAdrecaPis; }

    public String getInteressatAdrecaPorta() { return interessatAdrecaPorta; }
    public void setInteressatAdrecaPorta(String interessatAdrecaPorta) { this.interessatAdrecaPorta = interessatAdrecaPorta; }

    public String getInteressatAdrecaMunicipi() { return interessatAdrecaMunicipi; }
    public void setInteressatAdrecaMunicipi(String interessatAdrecaMunicipi) { this.interessatAdrecaMunicipi = interessatAdrecaMunicipi; }

    public String getInteressatAdrecaCodiPostal() { return interessatAdrecaCodiPostal; }
    public void setInteressatAdrecaCodiPostal(String interessatAdrecaCodiPostal) { this.interessatAdrecaCodiPostal = interessatAdrecaCodiPostal; }

    public String getInteressatTelefon() { return interessatTelefon; }
    public void setInteressatTelefon(String interessatTelefon) { this.interessatTelefon = interessatTelefon; }

    public String getInteressatCorreu() { return interessatCorreu; }
    public void setInteressatCorreu(String interessatCorreu) { this.interessatCorreu = interessatCorreu; }

    public String getRepresentantAdreca() { return representantAdreca; }
    public void setRepresentantAdreca(String representantAdreca) { this.representantAdreca = representantAdreca; }

    public String getRepresentantAdrecaNumero() { return representantAdrecaNumero; }
    public void setRepresentantAdrecaNumero(String representantAdrecaNumero) { this.representantAdrecaNumero = representantAdrecaNumero; }

    public String getRepresentantAdrecaEscala() { return representantAdrecaEscala; }
    public void setRepresentantAdrecaEscala(String representantAdrecaEscala) { this.representantAdrecaEscala = representantAdrecaEscala; }

    public String getRepresentantAdrecaPis() { return representantAdrecaPis; }
    public void setRepresentantAdrecaPis(String representantAdrecaPis) { this.representantAdrecaPis = representantAdrecaPis; }

    public String getRepresentantAdrecaPorta() { return representantAdrecaPorta; }
    public void setRepresentantAdrecaPorta(String representantAdrecaPorta) { this.representantAdrecaPorta = representantAdrecaPorta; }

    public String getRepresentantAdrecaMunicipi() { return representantAdrecaMunicipi; }
    public void setRepresentantAdrecaMunicipi(String representantAdrecaMunicipi) { this.representantAdrecaMunicipi = representantAdrecaMunicipi; }

    public String getRepresentantAdrecaCodiPostal() { return representantAdrecaCodiPostal; }
    public void setRepresentantAdrecaCodiPostal(String representantAdrecaCodiPostal) { this.representantAdrecaCodiPostal = representantAdrecaCodiPostal; }

    public String getRepresentantTelefon() { return representantTelefon; }
    public void setRepresentantTelefon(String representantTelefon) { this.representantTelefon = representantTelefon; }

    public String getRepresentantCorreu() { return representantCorreu; }
    public void setRepresentantCorreu(String representantCorreu) { this.representantCorreu = representantCorreu; }

    public String getRepresentantMitjaAcreditacio() { return representantMitjaAcreditacio; }
    public void setRepresentantMitjaAcreditacio(String representantMitjaAcreditacio) { this.representantMitjaAcreditacio = representantMitjaAcreditacio; }

    public String getRepresentantRea() { return representantRea; }
    public void setRepresentantRea(String representantRea) { this.representantRea = representantRea; }

    public String getRepresentantAltres() { return representantAltres; }
    public void setRepresentantAltres(String representantAltres) { this.representantAltres = representantAltres; }
}
