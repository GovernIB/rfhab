package es.caib.rfhab.logic;

import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;

import es.caib.rfhab.model.RFHabDaoManager;
import es.caib.rfhab.persistence.RFHabJPADaoManagers;

public class EntitatManagerUtil {
    /**
     * 
     * @return
     */
    public static EntityManager initDB() {
        Properties prop = new Properties();

        prop.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        prop.put("javax.persistence.jdbc.driver", "org.postgresql.Driver");
        prop.put("javax.persistence.jdbc.url", "jdbc:postgresql://localhost:5432/rfhab");
        prop.put("javax.persistence.jdbc.user", "rfhab");
        prop.put("javax.persistence.jdbc.password", "rfhab");

        prop.put("hibernate.connection.driver_class", "org.postgresql.Driver");
        prop.put("hibernate.connection.url", "jdbc:postgresql://localhost:5432/rfhab");
        prop.put("hibernate.connection.username", "rfhab");
        prop.put("hibernate.connection.password", "rfhab");

        prop.put("hibernate.show_sql", "true");

        EntityManagerFactory emf;

        // Veure persistence.xml
        emf = Persistence.createEntityManagerFactory("rfhabPULocal", prop);

        EntityManager em = emf.createEntityManager();

        em.setFlushMode(FlushModeType.AUTO);

        RFHabDaoManager.setDaoManagers(new RFHabJPADaoManagers(em));

        return em;
    }

}
