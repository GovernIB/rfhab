package es.caib.rfhab.logic.utils.GeneracioModelConsentimentTramits;

public class TramitConsentimentDAO {
    private String interessatNom;
    private String interessatLlinatge1;
    private String interessatLlinatge2;
    private String interessatNif;
    private String representantNom;
    private String representantLlinatge1;
    private String representantLlinatge2;
    private String representantNif;

    // Constructor buit
    public TramitConsentimentDAO() {
    }

    // Constructor amb totes les propietats
    public TramitConsentimentDAO(String interessatNom, String interessatLlinatge1, String interessatLlinatge2,
            String interessatNif, String representantNom, String representantLlinatge1, String representantLlinatge2,
            String representantNif) {
        this.interessatNom = interessatNom;
        this.interessatLlinatge1 = interessatLlinatge1;
        this.interessatLlinatge2 = interessatLlinatge2;
        this.interessatNif = interessatNif;
        this.representantNom = representantNom;
        this.representantLlinatge1 = representantLlinatge1;
        this.representantLlinatge2 = representantLlinatge2;
        this.representantNif = representantNif;
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
}
