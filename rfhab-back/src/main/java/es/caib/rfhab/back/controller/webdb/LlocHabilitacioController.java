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
import es.caib.rfhab.back.form.webdb.LlocHabilitacioForm;

import es.caib.rfhab.back.validator.webdb.LlocHabilitacioWebValidator;

import es.caib.rfhab.persistence.LlocHabilitacioJPA;
import es.caib.rfhab.model.entity.LlocHabilitacio;
import es.caib.rfhab.model.fields.*;
import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.fundaciobit.genapp.common.web.tiles.Tile;
import org.fundaciobit.genapp.common.web.tiles.TileAttribute;
import org.fundaciobit.genapp.common.web.tiles.TileType;
import es.caib.rfhab.back.utils.Tab;

/**
 * Controller per gestionar un LlocHabilitacio
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@MenuOption(labelCode="llocHabilitacio.llocHabilitacio.plural", order=110, group=Tab.MENU_WEBDB)
@Controller
@RequestMapping(value = "/webdb/llocHabilitacio")
@SessionAttributes(types = { LlocHabilitacioForm.class, LlocHabilitacioFilterForm.class })
@Tile(name="llocHabilitacioFormWebDB", contentJsp="/WEB-INF/jsp/webdb/llocHabilitacioForm.jsp", extendsTile=Tab.MENU_WEBDB,
      type=TileType.WEBDB_FORM , attributes={ @TileAttribute(name="titol", value="llocHabilitacio.llocHabilitacio")})
@Tile(name="llocHabilitacioListWebDB", contentJsp="/WEB-INF/jsp/webdb/llocHabilitacioList.jsp", extendsTile=Tab.MENU_WEBDB,
       type=TileType.WEBDB_LIST, attributes={ @TileAttribute(name="titol", value="llocHabilitacio.llocHabilitacio") })
public class LlocHabilitacioController
    extends es.caib.rfhab.back.controller.RFHabBaseController<LlocHabilitacio, java.lang.Long> implements LlocHabilitacioFields {

  @EJB(mappedName = es.caib.rfhab.ejb.LlocHabilitacioService.JNDI_NAME)
  protected es.caib.rfhab.ejb.LlocHabilitacioService llocHabilitacioEjb;

  @Autowired
  private LlocHabilitacioWebValidator llocHabilitacioWebValidator;

  @Autowired
  protected LlocHabilitacioRefList llocHabilitacioRefList;

  // References 
  @Autowired
  protected LlocRefList llocRefList;

  // References 
  @Autowired
  protected HabilitacioRefList habilitacioRefList;

  /**
   * Llistat de totes LlocHabilitacio
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    LlocHabilitacioFilterForm ff;
    ff = (LlocHabilitacioFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar LlocHabilitacio de forma paginada
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
    llistat(mav, request, getLlocHabilitacioFilterForm(pagina, mav, request));
    return mav;
  }

  public LlocHabilitacioFilterForm getLlocHabilitacioFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    LlocHabilitacioFilterForm llocHabilitacioFilterForm;
    llocHabilitacioFilterForm = (LlocHabilitacioFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(llocHabilitacioFilterForm == null) {
      llocHabilitacioFilterForm = new LlocHabilitacioFilterForm();
      llocHabilitacioFilterForm.setContexte(getContextWeb());
      llocHabilitacioFilterForm.setEntityNameCode(getEntityNameCode());
      llocHabilitacioFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      llocHabilitacioFilterForm.setNou(true);
    } else {
      llocHabilitacioFilterForm.setNou(false);
    }
    llocHabilitacioFilterForm.setPage(pagina == null ? 1 : pagina);
    return llocHabilitacioFilterForm;
  }

  /**
   * Segona i següent peticions per llistar LlocHabilitacio de forma paginada
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
      @ModelAttribute LlocHabilitacioFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getLlocHabilitacioFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de LlocHabilitacio de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<LlocHabilitacio> llistat(ModelAndView mav, HttpServletRequest request,
     LlocHabilitacioFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<LlocHabilitacio> llocHabilitacio = processarLlistat(llocHabilitacioEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("llocHabilitacioItems", llocHabilitacio);

    mav.addObject("llocHabilitacioFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, llocHabilitacio, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, llocHabilitacio);

    return llocHabilitacio;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(LlocHabilitacioFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<LlocHabilitacio> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field llocID
    {
      _listSKV = getReferenceListForLlocID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfLlocForLlocID(_tmp);
      if (filterForm.getGroupByFields().contains(LLOCID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, LLOCID, false);
      };
    }

    // Field habilitacioId
    {
      _listSKV = getReferenceListForHabilitacioId(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfHabilitacioForHabilitacioId(_tmp);
      if (filterForm.getGroupByFields().contains(HABILITACIOID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, HABILITACIOID, false);
      };
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    LlocHabilitacioFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<LlocHabilitacio> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_LLOCHABILITACIO_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(LLOCID, filterForm.getMapOfLlocForLlocID());
    __mapping.put(HABILITACIOID, filterForm.getMapOfHabilitacioForHabilitacioId());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou LlocHabilitacio
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearLlocHabilitacioGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    LlocHabilitacioForm llocHabilitacioForm = getLlocHabilitacioForm(null, false, request, mav);
    mav.addObject("llocHabilitacioForm" ,llocHabilitacioForm);
    fillReferencesForForm(llocHabilitacioForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public LlocHabilitacioForm getLlocHabilitacioForm(LlocHabilitacioJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    LlocHabilitacioForm llocHabilitacioForm;
    if(_jpa == null) {
      llocHabilitacioForm = new LlocHabilitacioForm(new LlocHabilitacioJPA(), true);
    } else {
      llocHabilitacioForm = new LlocHabilitacioForm(_jpa, false);
      llocHabilitacioForm.setView(__isView);
    }
    llocHabilitacioForm.setContexte(getContextWeb());
    llocHabilitacioForm.setEntityNameCode(getEntityNameCode());
    llocHabilitacioForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return llocHabilitacioForm;
  }

  public void fillReferencesForForm(LlocHabilitacioForm llocHabilitacioForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (llocHabilitacioForm.getListOfLlocForLlocID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForLlocID(request, mav, llocHabilitacioForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      llocHabilitacioForm.setListOfLlocForLlocID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (llocHabilitacioForm.getListOfHabilitacioForHabilitacioId() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForHabilitacioId(request, mav, llocHabilitacioForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      llocHabilitacioForm.setListOfHabilitacioForHabilitacioId(_listSKV);
    }
    
  }

  /**
   * Guardar un nou LlocHabilitacio
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearLlocHabilitacioPost(@ModelAttribute LlocHabilitacioForm llocHabilitacioForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    LlocHabilitacioJPA llocHabilitacio = llocHabilitacioForm.getLlocHabilitacio();

    try {
      preValidate(request, llocHabilitacioForm, result);
      getWebValidator().validate(llocHabilitacioForm, result);
      postValidate(request,llocHabilitacioForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        llocHabilitacio = create(request, llocHabilitacio);
        createMessageSuccess(request, "success.creation", llocHabilitacio.getLlocHabilitacioID());
        llocHabilitacioForm.setLlocHabilitacio(llocHabilitacio);
        return getRedirectWhenCreated(request, llocHabilitacioForm);
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

  @RequestMapping(value = "/view/{llocHabilitacioID}", method = RequestMethod.GET)
  public ModelAndView veureLlocHabilitacioGet(@PathVariable("llocHabilitacioID") java.lang.Long llocHabilitacioID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewLlocHabilitacioGet(llocHabilitacioID,
        request, response, true);
  }


  protected ModelAndView editAndViewLlocHabilitacioGet(@PathVariable("llocHabilitacioID") java.lang.Long llocHabilitacioID,
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
    LlocHabilitacioJPA llocHabilitacio = findByPrimaryKey(request, llocHabilitacioID);

    if (llocHabilitacio == null) {
      createMessageWarning(request, "error.notfound", llocHabilitacioID);
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      LlocHabilitacioForm llocHabilitacioForm = getLlocHabilitacioForm(llocHabilitacio, __isView, request, mav);
      llocHabilitacioForm.setView(__isView);
      if(__isView) {
        llocHabilitacioForm.setAllFieldsReadOnly(ALL_LLOCHABILITACIO_FIELDS);
        llocHabilitacioForm.setSaveButtonVisible(false);
        llocHabilitacioForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(llocHabilitacioForm, request, mav);
      mav.addObject("llocHabilitacioForm", llocHabilitacioForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un LlocHabilitacio existent
   */
  @RequestMapping(value = "/{llocHabilitacioID}/edit", method = RequestMethod.GET)
  public ModelAndView editarLlocHabilitacioGet(@PathVariable("llocHabilitacioID") java.lang.Long llocHabilitacioID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewLlocHabilitacioGet(llocHabilitacioID,
        request, response, false);
  }



  /**
   * Editar un LlocHabilitacio existent
   */
  @RequestMapping(value = "/{llocHabilitacioID}/edit", method = RequestMethod.POST)
  public String editarLlocHabilitacioPost(@ModelAttribute LlocHabilitacioForm llocHabilitacioForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    LlocHabilitacioJPA llocHabilitacio = llocHabilitacioForm.getLlocHabilitacio();

    try {
      preValidate(request, llocHabilitacioForm, result);
      getWebValidator().validate(llocHabilitacioForm, result);
      postValidate(request, llocHabilitacioForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        llocHabilitacio = update(request, llocHabilitacio);
        createMessageSuccess(request, "success.modification", llocHabilitacio.getLlocHabilitacioID());
        status.setComplete();
        return getRedirectWhenModified(request, llocHabilitacioForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          llocHabilitacio.getLlocHabilitacioID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, llocHabilitacioForm, __e);
    }

  }


  /**
   * Eliminar un LlocHabilitacio existent
   */
  @RequestMapping(value = "/{llocHabilitacioID}/delete")
  public String eliminarLlocHabilitacio(@PathVariable("llocHabilitacioID") java.lang.Long llocHabilitacioID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      LlocHabilitacio llocHabilitacio = this.findByPrimaryKey(request, llocHabilitacioID);
      if (llocHabilitacio == null) {
        String __msg = createMessageError(request, "error.notfound", llocHabilitacioID);
        return getRedirectWhenDelete(request, llocHabilitacioID, new Exception(__msg));
      } else {
        delete(request, llocHabilitacio);
        createMessageSuccess(request, "success.deleted", llocHabilitacioID);
        return getRedirectWhenDelete(request, llocHabilitacioID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", llocHabilitacioID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, llocHabilitacioID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute LlocHabilitacioFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarLlocHabilitacio(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __llocHabilitacioID, Throwable e) {
    java.lang.Long llocHabilitacioID = (java.lang.Long)__llocHabilitacioID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (llocHabilitacioID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(llocHabilitacioID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "llocHabilitacio.llocHabilitacio";
  }

  public String getEntityNameCodePlural() {
    return "llocHabilitacio.llocHabilitacio.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("llocHabilitacio.llocHabilitacioID");
  }

  @InitBinder("llocHabilitacioFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("llocHabilitacioForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "llocHabilitacio.llocHabilitacioID");
  }

  public LlocHabilitacioWebValidator getWebValidator() {
    return llocHabilitacioWebValidator;
  }


  public void setWebValidator(LlocHabilitacioWebValidator __val) {
    if (__val != null) {
      this.llocHabilitacioWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de LlocHabilitacio
   */
  @RequestMapping(value = "/{llocHabilitacioID}/cancel")
  public String cancelLlocHabilitacio(@PathVariable("llocHabilitacioID") java.lang.Long llocHabilitacioID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, llocHabilitacioID);
  }

  /**
   * Entra aqui al pitjar el boto cancel en el la creació de LlocHabilitacio
   */
  @RequestMapping(value = "/cancel")
  public String cancelLlocHabilitacio(HttpServletRequest request,HttpServletResponse response) {
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


  public List<StringKeyValue> getReferenceListForLlocID(HttpServletRequest request,
       ModelAndView mav, LlocHabilitacioForm llocHabilitacioForm, Where where)  throws I18NException {
    if (llocHabilitacioForm.isHiddenField(LLOCID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (llocHabilitacioForm.isReadOnlyField(LLOCID)) {
      _where = LlocFields.LLOCID.equal(llocHabilitacioForm.getLlocHabilitacio().getLlocID());
    }
    return getReferenceListForLlocID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForLlocID(HttpServletRequest request,
       ModelAndView mav, LlocHabilitacioFilterForm llocHabilitacioFilterForm,
       List<LlocHabilitacio> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (llocHabilitacioFilterForm.isHiddenField(LLOCID)
       && !llocHabilitacioFilterForm.isGroupByField(LLOCID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(LLOCID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (LlocHabilitacio _item : list) {
        _pkList.add(_item.getLlocID());
        }
        _w = LlocFields.LLOCID.in(_pkList);
      }
    return getReferenceListForLlocID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForLlocID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return llocRefList.getReferenceList(LlocFields.LLOCID, where );
  }


  public List<StringKeyValue> getReferenceListForHabilitacioId(HttpServletRequest request,
       ModelAndView mav, LlocHabilitacioForm llocHabilitacioForm, Where where)  throws I18NException {
    if (llocHabilitacioForm.isHiddenField(HABILITACIOID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (llocHabilitacioForm.isReadOnlyField(HABILITACIOID)) {
      _where = HabilitacioFields.HABILITACIOID.equal(llocHabilitacioForm.getLlocHabilitacio().getHabilitacioId());
    }
    return getReferenceListForHabilitacioId(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForHabilitacioId(HttpServletRequest request,
       ModelAndView mav, LlocHabilitacioFilterForm llocHabilitacioFilterForm,
       List<LlocHabilitacio> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (llocHabilitacioFilterForm.isHiddenField(HABILITACIOID)
       && !llocHabilitacioFilterForm.isGroupByField(HABILITACIOID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(HABILITACIOID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (LlocHabilitacio _item : list) {
        _pkList.add(_item.getHabilitacioId());
        }
        _w = HabilitacioFields.HABILITACIOID.in(_pkList);
      }
    return getReferenceListForHabilitacioId(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForHabilitacioId(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return habilitacioRefList.getReferenceList(HabilitacioFields.HABILITACIOID, where );
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

  public void preValidate(HttpServletRequest request,LlocHabilitacioForm llocHabilitacioForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,LlocHabilitacioForm llocHabilitacioForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, LlocHabilitacioFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, LlocHabilitacioFilterForm filterForm,  List<LlocHabilitacio> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, LlocHabilitacioForm llocHabilitacioForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, LlocHabilitacioForm llocHabilitacioForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long llocHabilitacioID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long llocHabilitacioID) {
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
    return "llocHabilitacioFormWebDB";
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
        return "llocHabilitacioListWebDB";
    }

  public String getSessionAttributeFilterForm() {
    return "LlocHabilitacio_FilterForm_" + this.getClass().getName();
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public LlocHabilitacioJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long llocHabilitacioID) throws I18NException {
    return (LlocHabilitacioJPA) llocHabilitacioEjb.findByPrimaryKey(llocHabilitacioID);
  }


  public LlocHabilitacioJPA create(HttpServletRequest request, LlocHabilitacioJPA llocHabilitacio)
    throws I18NException, I18NValidationException {
    return (LlocHabilitacioJPA) llocHabilitacioEjb.create(llocHabilitacio);
  }


  public LlocHabilitacioJPA update(HttpServletRequest request, LlocHabilitacioJPA llocHabilitacio)
    throws I18NException, I18NValidationException {
    return (LlocHabilitacioJPA) llocHabilitacioEjb.update(llocHabilitacio);
  }


  public void delete(HttpServletRequest request, LlocHabilitacio llocHabilitacio) throws I18NException {
    llocHabilitacioEjb.delete(llocHabilitacio);
  }

} // Final de Classe

