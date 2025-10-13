package es.caib.rfhab.pluginsib.rolsac.model.ibitrolsac;

/**
 * Comentario de una ficha.
 *
 * @author areus
 */
public class ComentarioFicha extends Comentario {

    private Ficha ficha;

    public Ficha getFicha() {
        return ficha;
    }

    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }
}
