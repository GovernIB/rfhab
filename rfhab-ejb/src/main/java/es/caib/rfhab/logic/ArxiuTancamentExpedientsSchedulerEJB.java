package es.caib.rfhab.logic;

import java.util.Calendar;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.jboss.logging.Logger;

import es.caib.rfhab.commons.utils.Configuracio;

@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)//Per un Singleton, el valor per defecte ja és CONTAINER. Per tant, aquesta línia és redundant funcionalment; serveix sobretot per deixar-ho explícit a nivell de lectura.
public class ArxiuTancamentExpedientsSchedulerEJB {

    protected final Logger log = Logger.getLogger(this.getClass());

    @EJB(mappedName = ActivitatLogicaService.JNDI_NAME)
    protected ActivitatLogicaService activitatLogicaEjb;

    @Lock(LockType.WRITE)
    @Schedule(hour = "*", minute = "0", second = "0", persistent = false)
    public void executarTancamentExpedientsPendentsArxiu() {
        if (!Configuracio.isArxiuTancamentExpedientsSchedulerActiu()) {
            return;
        }

        final int horaInici = Configuracio.getArxiuTancamentExpedientsSchedulerHora();
        final int intervalHores = Configuracio.getArxiuTancamentExpedientsSchedulerNhores();
        final int horaActual = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);

        int modul = (horaActual - horaInici) % intervalHores;
        if (modul < 0) {
            modul += intervalHores;
        }

        if (modul != 0) {
            return;
        }

        try {
            log.info("ArxiuTancamentExpedientsSchedulerEJB::Iniciant procés de tancament d'expedients pendents");
            activitatLogicaEjb.processarTancamentExpedientsPendentsArxiu();
            log.info("ArxiuTancamentExpedientsSchedulerEJB::Procés de tancament d'expedients finalitzat");
        } catch (Exception e) {
            log.error("ArxiuTancamentExpedientsSchedulerEJB::Error executant el procés nocturn de tancament d'expedients", e);
        }
    }
}
