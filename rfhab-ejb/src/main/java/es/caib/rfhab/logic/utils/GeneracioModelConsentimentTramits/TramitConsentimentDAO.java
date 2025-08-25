
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

    // Constructor buit
    public TramitConsentimentDAO() {
    }

    // Constructor amb totes les propietats
    public TramitConsentimentDAO(String interessatNom, String interessatLlinatge1, String interessatLlinatge2,
            String interessatNif, String representantNom, String representantLlinatge1, String representantLlinatge2,
            String representantNif, String funcionariNom, String funcionariLlinatge1, String funcionariLlinatge2,
            String funcionariCodi, String procedimentNom, String procedimentCodi, String tramitNom, String tramitCodi, Date dataTramit) {
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
}
