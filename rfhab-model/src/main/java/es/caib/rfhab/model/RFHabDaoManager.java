package es.caib.rfhab.model;

public class RFHabDaoManager {
  
  private static IRFHabDaoManagers instance = null;
  
  public static void setDaoManagers(IRFHabDaoManagers managers) {
    instance = managers;
  }
  
  public static IRFHabDaoManagers getDaoManagers() throws Exception {
    if(instance == null) {
      throw new Exception("Ha de inicialitzar el sistema de Managers cridant "
          + " al mètode RFHabDaoManager.setDaoManagers(...)");
    }
    return instance;
  }
  
}
