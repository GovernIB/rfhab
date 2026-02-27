package es.caib.rfhab.api.interna.secure.carregadades;

public class FitxersCarrega {

    
    
    @io.swagger.v3.oas.annotations.media.Schema(
            description = "Excel.",
            required = false,
            type = "string",
            format = "byte")
    protected byte[] excel;
    @io.swagger.v3.oas.annotations.media.Schema(
            description = "Excel.",
            required = false,
            type = "string",
            format = "byte")
    protected byte[] properties;


    public byte[] getExcel() {
        return excel;
    }

    public void setExcel(byte[] excel) {
        this.excel = excel;
    }

    public byte[] getProperties() {
        return properties;
    }

    public void setProperties(byte[] properties) {
        this.properties = properties;
    }

}
