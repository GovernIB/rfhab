package es.caib.rfhab.back.controller.webdb;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.utils.Utils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.GroupByItem;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.web.validation.ValidationWebUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.Arrays;

import es.caib.rfhab.back.form.webdb.*;
import es.caib.rfhab.back.form.webdb.HabilitacioForm;

import es.caib.rfhab.back.validator.webdb.HabilitacioWebValidator;

import es.caib.rfhab.persistence.HabilitacioJPA;
import es.caib.rfhab.model.entity.Habilitacio;
import es.caib.rfhab.model.fields.*;
import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.fundaciobit.genapp.common.web.tiles.Tile;
import org.fundaciobit.genapp.common.web.tiles.TileAttribute;
import org.fundaciobit.genapp.common.web.tiles.TileType;
import es.caib.rfhab.back.utils.Tab;

/**
 * Controller per gestionar un Habilitacio
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@MenuOption(labelCode="habilitacio.habilitacio.plural", order=60, group=Tab.MENU_WEBDB)
@Controller
@RequestMapping(value = "/webdb/habilitacio")
@SessionAttributes(types = { HabilitacioForm.class, HabilitacioFilterForm.class })
@Tile(name="habilitacioFormWebDB", extendsTile=Tab.MENU_WEBDB,
    // Els següents atributs no són necessaris si heredes aquesta classe
    contentJsp="/WEB-INF/jsp/webdb/habilitacioForm.jsp", type=TileType.WEBDB_FORM,
    attributes={ @TileAttribute(name="titol", value="habilitacio.habilitacio")})
@Tile(name="habilitacioListWebDB", extendsTile=Tab.MENU_WEBDB,
    // Els següents atributs no són necessaris si heredes aquesta classe 
    contentJsp="/WEB-INF/jsp/webdb/habilitacioList.jsp", type=TileType.WEBDB_LIST,
    attributes={ @TileAttribute(name="titol", value="habilitacio.habilitacio")})
public class HabilitacioController
    extends es.caib.rfhab.back.controller.RFHabBaseController<Habilitacio, java.lang.Long> implements HabilitacioFields {

  @EJB(mappedName = es.caib.rfhab.ejb.IdiomaService.JNDI_NAME)
  protected es.caib.rfhab.ejb.IdiomaService idiomaEjb;

  @EJB(mappedName = es.caib.rfhab.ejb.HabilitacioService.JNDI_NAME)
  protected es.caib.rfhab.ejb.HabilitacioService habilitacioEjb;

  @Autowired
  private HabilitacioWebValidator habilitacioWebValidator;

  @Autowired
  protected HabilitacioRefList habilitacioRefList;

  // References 
  @Autowired
  protected TraduccioRefList traduccioRefList;

  /**
   * Llistat de totes Habilitacio
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    HabilitacioFilterForm ff;
    ff = (HabilitacioFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar Habilitacio de forma paginada
   */
  @RequestMapping(value = "/list/{pagina}", method = RequestMethod.GET)
  public ModelAndView llistatPaginat(HttpServletRequest request,
    HttpServletResponse response, @PathVariable Integer pagina)
      throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileList());
    llistat(mav, request, getHabilitacioFilterForm(pagina, mav, request));
    return mav;
  }

  public HabilitacioFilterForm getHabilitacioFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    HabilitacioFilterForm habilitacioFilterForm;
    habilitacioFilterForm = (HabilitacioFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(habilitacioFilterForm == null) {
      habilitacioFilterForm = new HabilitacioFilterForm();
      habilitacioFilterForm.setContexte(getContextWeb());
      habilitacioFilterForm.setEntityNameCode(getEntityNameCode());
      habilitacioFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      habilitacioFilterForm.setNou(true);
    } else {
      habilitacioFilterForm.setNou(false);
    }
    habilitacioFilterForm.setPage(pagina == null ? 1 : pagina);
    return habilitacioFilterForm;
  }

  /**
   * Segona i següent peticions per llistar Habilitacio de forma paginada
   * 
   * @param request
   * @param pagina
   * @param filterForm
   * @return
   * @throws I18NException
   */
  @RequestMapping(value = "/list/{pagina}", method = RequestMethod.POST)
  public ModelAndView llistatPaginat(HttpServletRequest request,
      HttpServletResponse response,@PathVariable Integer pagina,
      @ModelAttribute HabilitacioFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getHabilitacioFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de Habilitacio de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<Habilitacio> llistat(ModelAndView mav, HttpServletRequest request,
     HabilitacioFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<Habilitacio> habilitacio = processarLlistat(habilitacioEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("habilitacioItems", habilitacio);

    mav.addObject("habilitacioFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, habilitacio, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, habilitacio);

    return habilitacio;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(HabilitacioFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<Habilitacio> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field nomID
    {
      _listSKV = getReferenceListForNomID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfTraduccioForNomID(_tmp);
      if (filterForm.getGroupByFields().contains(NOMID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, NOMID, false);
      };
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    HabilitacioFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<Habilitacio> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_HABILITACIO_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(NOMID, filterForm.getMapOfTraduccioForNomID());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou Habilitacio
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearHabilitacioGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    HabilitacioForm habilitacioForm = getHabilitacioForm(null, false, request, mav);
    
    if (habilitacioForm.getHabilitacio().getNom() == null){
      es.caib.rfhab.persistence.TraduccioJPA trad = new es.caib.rfhab.persistence.TraduccioJPA();
      for (es.caib.rfhab.model.entity.Idioma idioma : habilitacioForm.getIdiomesTraduccio()) {
        trad.addTraduccio(idioma.getIdiomaID(), new es.caib.rfhab.persistence.TraduccioMapJPA());
      }
      habilitacioForm.getHabilitacio().setNom(trad);
    }

    mav.addObject("habilitacioForm" ,habilitacioForm);
    fillReferencesForForm(habilitacioForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public HabilitacioForm getHabilitacioForm(HabilitacioJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    HabilitacioForm habilitacioForm;
    if(_jpa == null) {
      habilitacioForm = new HabilitacioForm(new HabilitacioJPA(), true);
    } else {
      habilitacioForm = new HabilitacioForm(_jpa, false);
      habilitacioForm.setView(__isView);
    }
    habilitacioForm.setContexte(getContextWeb());
    habilitacioForm.setEntityNameCode(getEntityNameCode());
    habilitacioForm.setEntityNameCodePlural(getEntityNameCodePlural());
    habilitacioForm.setIdiomesTraduccio(getIdiomesSuportats());
    return habilitacioForm;
  }

  public void fillReferencesForForm(HabilitacioForm habilitacioForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    
  }


  public List<es.caib.rfhab.model.entity.Idioma> getIdiomesSuportats() throws I18NException {
    List<es.caib.rfhab.model.entity.Idioma> idiomes = idiomaEjb.select(es.caib.rfhab.model.fields.IdiomaFields.SUPORTAT.equal(true));
    return idiomes;
  }


  /**
   * Guardar un nou Habilitacio
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearHabilitacioPost(@ModelAttribute HabilitacioForm habilitacioForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    HabilitacioJPA habilitacio = habilitacioForm.getHabilitacio();

    try {
      preValidate(request, habilitacioForm, result);
      getWebValidator().validate(habilitacioForm, result);
      postValidate(request,habilitacioForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        habilitacio = create(request, habilitacio);
        createMessageSuccess(request, "success.creation", habilitacio.getHabilitacioID());
        habilitacioForm.setHabilitacio(habilitacio);
        return getRedirectWhenCreated(request, habilitacioForm);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.creation", null, __e);
      log.error(msg, __e);
      return getTileForm();
    }
  }

  @RequestMapping(value = "/view/{habilitacioID}", method = RequestMethod.GET)
  public ModelAndView veureHabilitacioGet(@PathVariable("habilitacioID") java.lang.Long habilitacioID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewHabilitacioGet(habilitacioID,
        request, response, true);
  }


  protected ModelAndView editAndViewHabilitacioGet(@PathVariable("habilitacioID") java.lang.Long habilitacioID,
      HttpServletRequest request,
      HttpServletResponse response, boolean __isView) throws I18NException {
    if((!__isView) && !isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    } else {
      if(__isView && !isActiveFormView()) {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return null;
      }
    }
    HabilitacioJPA habilitacio = findByPrimaryKey(request, habilitacioID);

    if (habilitacio == null) {
      createMessageWarning(request, "error.notfound", habilitacioID);
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      HabilitacioForm habilitacioForm = getHabilitacioForm(habilitacio, __isView, request, mav);
      habilitacioForm.setView(__isView);
      if(__isView) {
        habilitacioForm.setAllFieldsReadOnly(ALL_HABILITACIO_FIELDS);
        habilitacioForm.setSaveButtonVisible(false);
        habilitacioForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(habilitacioForm, request, mav);
      mav.addObject("habilitacioForm", habilitacioForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un Habilitacio existent
   */
  @RequestMapping(value = "/{habilitacioID}/edit", method = RequestMethod.GET)
  public ModelAndView editarHabilitacioGet(@PathVariable("habilitacioID") java.lang.Long habilitacioID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewHabilitacioGet(habilitacioID,
        request, response, false);
  }



  /**
   * Editar un Habilitacio existent
   */
  @RequestMapping(value = "/{habilitacioID}/edit", method = RequestMethod.POST)
  public String editarHabilitacioPost(@ModelAttribute HabilitacioForm habilitacioForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    HabilitacioJPA habilitacio = habilitacioForm.getHabilitacio();

    try {
      preValidate(request, habilitacioForm, result);
      getWebValidator().validate(habilitacioForm, result);
      postValidate(request, habilitacioForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        habilitacio = update(request, habilitacio);
        createMessageSuccess(request, "success.modification", habilitacio.getHabilitacioID());
        status.setComplete();
        return getRedirectWhenModified(request, habilitacioForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          habilitacio.getHabilitacioID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, habilitacioForm, __e);
    }

  }


  /**
   * Eliminar un Habilitacio existent
   */
  @RequestMapping(value = "/{habilitacioID}/delete")
  public String eliminarHabilitacio(@PathVariable("habilitacioID") java.lang.Long habilitacioID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      Habilitacio habilitacio = this.findByPrimaryKey(request, habilitacioID);
      if (habilitacio == null) {
        String __msg = createMessageError(request, "error.notfound", habilitacioID);
        return getRedirectWhenDelete(request, habilitacioID, new Exception(__msg));
      } else {
        delete(request, habilitacio);
        createMessageSuccess(request, "success.deleted", habilitacioID);
        return getRedirectWhenDelete(request, habilitacioID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", habilitacioID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, habilitacioID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute HabilitacioFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarHabilitacio(stringToPK(seleccionats[i]), request, response);
    }
  }
  if (redirect == null) {
    redirect = getRedirectWhenDelete(request, null,null);
  }

  return redirect;
}



public java.lang.Long stringToPK(String value) {
  return java.lang.Long.parseLong(value, 10);
}

  @Override
  public String[] getArgumentsMissatge(Object __habilitacioID, Throwable e) {
    java.lang.Long habilitacioID = (java.lang.Long)__habilitacioID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (habilitacioID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(habilitacioID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "habilitacio.habilitacio";
  }

  public String getEntityNameCodePlural() {
    return "habilitacio.habilitacio.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("habilitacio.habilitacioID");
  }

  @InitBinder("habilitacioFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("habilitacioForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "habilitacio.habilitacioID");
  }

  public HabilitacioWebValidator getWebValidator() {
    return habilitacioWebValidator;
  }


  public void setWebValidator(HabilitacioWebValidator __val) {
    if (__val != null) {
      this.habilitacioWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de Habilitacio
   */
  @RequestMapping(value = "/{habilitacioID}/cancel")
  public String cancelHabilitacio(@PathVariable("habilitacioID") java.lang.Long habilitacioID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, habilitacioID);
  }

  /**
   * Entra aqui al pitjar el boto cancel en el la creació de Habilitacio
   */
  @RequestMapping(value = "/cancel")
  public String cancelHabilitacio(HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, null);
  }

  @Override
  public String getTableModelName() {
    return _TABLE_MODEL;
  }

  // Mètodes a sobreescriure 

  public boolean isActiveList() {
    return true;
  }


  public boolean isActiveFormNew() {
    return true;
  }


  public boolean isActiveFormEdit() {
    return true;
  }


  public boolean isActiveDelete() {
    return true;
  }


  public boolean isActiveFormView() {
    return isActiveFormEdit();
  }

  public List<StringKeyValue> getReferenceListForNomID(HttpServletRequest request,
       ModelAndView mav, HabilitacioFilterForm habilitacioFilterForm,
       List<Habilitacio> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (habilitacioFilterForm.isHiddenField(NOMID)
       && !habilitacioFilterForm.isGroupByField(NOMID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(NOMID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (Habilitacio _item : list) {
        _pkList.add(_item.getNomID());
        }
        _w = TraduccioFields.TRADUCCIOID.in(_pkList);
      }
    return getReferenceListForNomID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForNomID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return traduccioRefList.getReferenceList(TraduccioFields.TRADUCCIOID, where );
  }


    @Override
    /** Ha de ser igual que el RequestMapping de la Classe */
    public String getContextWeb() {
        RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
        final String[] values = rm.value();
        if (values.length == 1) {
            return values[0];
        } else {
            final HttpServletRequest request;
            request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

            final String servletPath = request.getServletPath();

            for (String webcontext : values) {
                if (servletPath.startsWith(webcontext)) {
                    return webcontext;
                }
            }

            log.warn(" No puc trobar el contextweb associat a la cridada.");
            log.warn(" ==== RequestMapping::value=" + Arrays.toString(values));
            log.warn(" ++++ getContextWeb::Scheme: " + request.getScheme());
            log.warn(" ++++ getContextWeb::PathInfo: " + request.getPathInfo());
            log.warn(" ++++ getContextWeb::PathTrans: " + request.getPathTranslated());
            log.warn(" ++++ getContextWeb::ContextPath: " + request.getContextPath());
            log.warn(" ++++ getContextWeb::ServletPath: " + request.getServletPath());
            log.warn(" ++++ getContextWeb::getRequestURI: " + request.getRequestURI());
            log.warn(" ++++ getContextWeb::getRequestURL: " + request.getRequestURL().toString());
            log.warn(" ++++ getContextWeb::getQueryString: " + request.getQueryString());

            return values[0];
        }  }

  public void preValidate(HttpServletRequest request,HabilitacioForm habilitacioForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,HabilitacioForm habilitacioForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, HabilitacioFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, HabilitacioFilterForm filterForm,  List<Habilitacio> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, HabilitacioForm habilitacioForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, HabilitacioForm habilitacioForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long habilitacioID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long habilitacioID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
        try {
            Set<Tile> rm;
            rm=AnnotationUtils.getDeclaredRepeatableAnnotations(this.getClass(), Tile.class);
            if (rm != null && !rm.isEmpty()) {
                String trobada = null;
                for (Tile tile : rm) {
                    if (tile.type() == TileType.WEBDB_FORM) {
                        trobada = tile.name();
                    }
                }
                if (trobada != null) {
                    return trobada;
                }
            }
        } catch (Exception e) {
            log.error("Error en el getTileForm: " + e.getMessage(), e);
        }
    return "habilitacioFormWebDB";
  }

    public String getTileList() {
        try {
            Set<Tile> rm;
            rm=AnnotationUtils.getDeclaredRepeatableAnnotations(this.getClass(), Tile.class);
            if (rm != null && !rm.isEmpty()) {
                String trobada = null;
                for (Tile tile : rm) {
                    if (tile.type() == TileType.WEBDB_LIST) {
                        trobada = tile.name();
                    }
                }
                if (trobada != null) {
                    return trobada;
                }
            }
        } catch (Exception e) {
            log.error("Error en el getTileList: " + e.getMessage(), e);
        }
        return "habilitacioListWebDB";
    }

  public String getSessionAttributeFilterForm() {
    return "Habilitacio_FilterForm_" + this.getClass().getName();
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public HabilitacioJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long habilitacioID) throws I18NException {
    return (HabilitacioJPA) habilitacioEjb.findByPrimaryKey(habilitacioID);
  }


  public HabilitacioJPA create(HttpServletRequest request, HabilitacioJPA habilitacio)
    throws I18NException, I18NValidationException {
    return (HabilitacioJPA) habilitacioEjb.create(habilitacio);
  }


  public HabilitacioJPA update(HttpServletRequest request, HabilitacioJPA habilitacio)
    throws I18NException, I18NValidationException {
    return (HabilitacioJPA) habilitacioEjb.update(habilitacio);
  }


  public void delete(HttpServletRequest request, Habilitacio habilitacio) throws I18NException {
    habilitacioEjb.delete(habilitacio);
  }

} // Final de Classe

