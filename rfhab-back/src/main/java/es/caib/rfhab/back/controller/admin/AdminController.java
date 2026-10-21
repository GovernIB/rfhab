package es.caib.rfhab.back.controller.admin;

import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.web.menuoptions.MenuItem;
import org.fundaciobit.genapp.common.web.menuoptions.MenuOptionManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import es.caib.rfhab.back.utils.Tab;
import es.caib.rfhab.commons.utils.Configuracio;
import org.fundaciobit.genapp.common.web.tiles.Tile;
import org.fundaciobit.genapp.common.web.tiles.TileType;

/**
 * 
 * @author anadal
 *
 */
@Controller
@Tile(name = "webpages", extendsTile = Tab.MENU_ADMIN, type = TileType.ANOTHER,
    contentJsp = "/WEB-INF/jsp/admin/webpages.jsp")
@RequestMapping(value = "/admin/")
public class AdminController {

  protected final Logger log = Logger.getLogger(getClass());

  @RequestMapping(value = "/home")
  public ModelAndView option1(HttpSession session,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    
    return new ModelAndView("homeAdmin");
    
  }  

  @RequestMapping(value = "/webpages")
  public ModelAndView webPages(HttpServletRequest request) {
    String subtitle = "Per descarregar les URL a les pàgines web en format pla, feis clic aquí:"
        + " <b><a target=\"_blank\" href=\"" + request.getContextPath()
        + "/admin/webpagesplain?includenew=true\">[AMB NEW]</a></b>"
        + " &nbsp;&nbsp;<b><a target=\"_blank\" href=\"" + request.getContextPath()
        + "/admin/webpagesplain?includenew=false\">[SENSE NEW]</a></b>";
    ModelAndView mav = new ModelAndView("webpages");
    mav.addObject("subtitle", subtitle);
    return mav;
  }

  @RequestMapping(value = "/webpagesplain")
  public void webPagesPlain(HttpSession session, HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    boolean includeNew = "true".equalsIgnoreCase(request.getParameter("includenew"));
    String[] tabs = { Tab.MENU_PUBLIC_AND_COMMON, Tab.MENU_USER, Tab.MENU_ADMIN };

    response.setContentType("text/plain");
    PrintWriter writer = response.getWriter();

    for (String tab : tabs) {
      Map<Class<?>, List<MenuItem>> fullInfo = MenuOptionManager.getMenuItemsByClass(tab);
      fullInfo.forEach((controllerClass, items) -> {
        for (MenuItem item : items) {
          if (item != null) {
            writer.println(Configuracio.getBackUrl() + item.getUrl());
          }
        }

        if (includeNew) {
          writeNewFormUrl(controllerClass, writer);
        }
      });
    }

    writer.println(Configuracio.getBackUrl() + "/public/index.html");
    writer.println(Configuracio.getBackUrl() + "/common/principal.html");
    writer.flush();
    writer.close();
  }

  private void writeNewFormUrl(Class<?> controllerClass, PrintWriter writer) {
    try {
      Method activeFormMethod = controllerClass.getMethod("isActiveFormNew");
      Method contextWebMethod = controllerClass.getMethod("getContextWeb");
      Object controller = controllerClass.getDeclaredConstructor().newInstance();
      if (Boolean.TRUE.equals(activeFormMethod.invoke(controller))) {
        writer.println(Configuracio.getBackUrl() + contextWebMethod.invoke(controller) + "/new");
      }
    } catch (NoSuchMethodException e) {
      // Not every menu controller exposes a new-form URL.
    } catch (ReflectiveOperationException e) {
      log.warn("Could not determine the new-form URL for " + controllerClass.getName(), e);
    }
  }
  
}
