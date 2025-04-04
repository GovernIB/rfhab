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

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

import es.caib.rfhab.back.form.webdb.*;
import es.caib.rfhab.back.form.webdb.AutoritzacioForm;

import es.caib.rfhab.back.validator.webdb.AutoritzacioWebValidator;

import es.caib.rfhab.persistence.AutoritzacioJPA;
import es.caib.rfhab.model.entity.Autoritzacio;
import es.caib.rfhab.model.fields.*;
import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.fundaciobit.genapp.common.web.tiles.Tile;
import org.fundaciobit.genapp.common.web.tiles.TileAttribute;
import org.fundaciobit.genapp.common.web.tiles.TileType;
import es.caib.rfhab.back.utils.Tab;

/**
 * Controller per gestionar un Autoritzacio
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@MenuOption(labelCode="autoritzacio.autoritzacio.plural", order=10, group=Tab.MENU_WEBDB)
@Controller
@RequestMapping(value = "/webdb/autoritzacio")
@SessionAttributes(types = { AutoritzacioForm.class, AutoritzacioFilterForm.class })
@Tile(name="autoritzacioFormWebDB", contentJsp="/WEB-INF/jsp/webdb/autoritzacioForm.jsp", extendsTile=Tab.MENU_WEBDB,
      type=TileType.WEBDB_FORM , attributes={ @TileAttribute(name="titol", value="autoritzacio.autoritzacio")})
@Tile(name="autoritzacioListWebDB", contentJsp="/WEB-INF/jsp/webdb/autoritzacioList.jsp", extendsTile=Tab.MENU_WEBDB,
       type=TileType.WEBDB_LIST, attributes={ @TileAttribute(name="titol", value="autoritzacio.autoritzacio") })
public class AutoritzacioController
    extends es.caib.rfhab.back.controller.RFHabBaseController<Autoritzacio, java.lang.Long> implements AutoritzacioFields {

  @EJB(mappedName = es.caib.rfhab.ejb.AutoritzacioService.JNDI_NAME)
  protected es.caib.rfhab.ejb.AutoritzacioService autoritzacioEjb;

  @Autowired
  private AutoritzacioWebValidator autoritzacioWebValidator;

  @Autowired
  protected AutoritzacioRefList autoritzacioRefList;

  // References 
  @Autowired
  protected LlocRefList llocRefList;

  // References 
  @Autowired
  protected FuncionariRefList funcionariRefList;

  /**
   * Llistat de totes Autoritzacio
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    AutoritzacioFilterForm ff;
    ff = (AutoritzacioFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar Autoritzacio de forma paginada
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
    llistat(mav, request, getAutoritzacioFilterForm(pagina, mav, request));
    return mav;
  }

  public AutoritzacioFilterForm getAutoritzacioFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    AutoritzacioFilterForm autoritzacioFilterForm;
    autoritzacioFilterForm = (AutoritzacioFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(autoritzacioFilterForm == null) {
      autoritzacioFilterForm = new AutoritzacioFilterForm();
      autoritzacioFilterForm.setContexte(getContextWeb());
      autoritzacioFilterForm.setEntityNameCode(getEntityNameCode());
      autoritzacioFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      autoritzacioFilterForm.setNou(true);
    } else {
      autoritzacioFilterForm.setNou(false);
    }
    autoritzacioFilterForm.setPage(pagina == null ? 1 : pagina);
    return autoritzacioFilterForm;
  }

  /**
   * Segona i següent peticions per llistar Autoritzacio de forma paginada
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
      @ModelAttribute AutoritzacioFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getAutoritzacioFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de Autoritzacio de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<Autoritzacio> llistat(ModelAndView mav, HttpServletRequest request,
     AutoritzacioFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<Autoritzacio> autoritzacio = processarLlistat(autoritzacioEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("autoritzacioItems", autoritzacio);

    mav.addObject("autoritzacioFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, autoritzacio, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, autoritzacio);

    return autoritzacio;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(AutoritzacioFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<Autoritzacio> list, List<GroupByItem> groupItems) throws I18NException {
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

    // Field funcionariID
    {
      _listSKV = getReferenceListForFuncionariID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfFuncionariForFuncionariID(_tmp);
      if (filterForm.getGroupByFields().contains(FUNCIONARIID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, FUNCIONARIID, false);
      };
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    AutoritzacioFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<Autoritzacio> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_AUTORITZACIO_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(LLOCID, filterForm.getMapOfLlocForLlocID());
    __mapping.put(FUNCIONARIID, filterForm.getMapOfFuncionariForFuncionariID());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou Autoritzacio
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearAutoritzacioGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    AutoritzacioForm autoritzacioForm = getAutoritzacioForm(null, false, request, mav);
    mav.addObject("autoritzacioForm" ,autoritzacioForm);
    fillReferencesForForm(autoritzacioForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public AutoritzacioForm getAutoritzacioForm(AutoritzacioJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    AutoritzacioForm autoritzacioForm;
    if(_jpa == null) {
      autoritzacioForm = new AutoritzacioForm(new AutoritzacioJPA(), true);
    } else {
      autoritzacioForm = new AutoritzacioForm(_jpa, false);
      autoritzacioForm.setView(__isView);
    }
    autoritzacioForm.setContexte(getContextWeb());
    autoritzacioForm.setEntityNameCode(getEntityNameCode());
    autoritzacioForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return autoritzacioForm;
  }

  public void fillReferencesForForm(AutoritzacioForm autoritzacioForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (autoritzacioForm.getListOfLlocForLlocID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForLlocID(request, mav, autoritzacioForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      autoritzacioForm.setListOfLlocForLlocID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (autoritzacioForm.getListOfFuncionariForFuncionariID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForFuncionariID(request, mav, autoritzacioForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      autoritzacioForm.setListOfFuncionariForFuncionariID(_listSKV);
    }
    
  }

  /**
   * Guardar un nou Autoritzacio
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearAutoritzacioPost(@ModelAttribute AutoritzacioForm autoritzacioForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    AutoritzacioJPA autoritzacio = autoritzacioForm.getAutoritzacio();

    try {
      preValidate(request, autoritzacioForm, result);
      getWebValidator().validate(autoritzacioForm, result);
      postValidate(request,autoritzacioForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        autoritzacio = create(request, autoritzacio);
        createMessageSuccess(request, "success.creation", autoritzacio.getAutoritzacioID());
        autoritzacioForm.setAutoritzacio(autoritzacio);
        return getRedirectWhenCreated(request, autoritzacioForm);
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

  @RequestMapping(value = "/view/{autoritzacioID}", method = RequestMethod.GET)
  public ModelAndView veureAutoritzacioGet(@PathVariable("autoritzacioID") java.lang.Long autoritzacioID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewAutoritzacioGet(autoritzacioID,
        request, response, true);
  }


  protected ModelAndView editAndViewAutoritzacioGet(@PathVariable("autoritzacioID") java.lang.Long autoritzacioID,
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
    AutoritzacioJPA autoritzacio = findByPrimaryKey(request, autoritzacioID);

    if (autoritzacio == null) {
      createMessageWarning(request, "error.notfound", autoritzacioID);
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      AutoritzacioForm autoritzacioForm = getAutoritzacioForm(autoritzacio, __isView, request, mav);
      autoritzacioForm.setView(__isView);
      if(__isView) {
        autoritzacioForm.setAllFieldsReadOnly(ALL_AUTORITZACIO_FIELDS);
        autoritzacioForm.setSaveButtonVisible(false);
        autoritzacioForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(autoritzacioForm, request, mav);
      mav.addObject("autoritzacioForm", autoritzacioForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un Autoritzacio existent
   */
  @RequestMapping(value = "/{autoritzacioID}/edit", method = RequestMethod.GET)
  public ModelAndView editarAutoritzacioGet(@PathVariable("autoritzacioID") java.lang.Long autoritzacioID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewAutoritzacioGet(autoritzacioID,
        request, response, false);
  }



  /**
   * Editar un Autoritzacio existent
   */
  @RequestMapping(value = "/{autoritzacioID}/edit", method = RequestMethod.POST)
  public String editarAutoritzacioPost(@ModelAttribute AutoritzacioForm autoritzacioForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    AutoritzacioJPA autoritzacio = autoritzacioForm.getAutoritzacio();

    try {
      preValidate(request, autoritzacioForm, result);
      getWebValidator().validate(autoritzacioForm, result);
      postValidate(request, autoritzacioForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        autoritzacio = update(request, autoritzacio);
        createMessageSuccess(request, "success.modification", autoritzacio.getAutoritzacioID());
        status.setComplete();
        return getRedirectWhenModified(request, autoritzacioForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          autoritzacio.getAutoritzacioID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, autoritzacioForm, __e);
    }

  }


  /**
   * Eliminar un Autoritzacio existent
   */
  @RequestMapping(value = "/{autoritzacioID}/delete")
  public String eliminarAutoritzacio(@PathVariable("autoritzacioID") java.lang.Long autoritzacioID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      Autoritzacio autoritzacio = this.findByPrimaryKey(request, autoritzacioID);
      if (autoritzacio == null) {
        String __msg = createMessageError(request, "error.notfound", autoritzacioID);
        return getRedirectWhenDelete(request, autoritzacioID, new Exception(__msg));
      } else {
        delete(request, autoritzacio);
        createMessageSuccess(request, "success.deleted", autoritzacioID);
        return getRedirectWhenDelete(request, autoritzacioID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", autoritzacioID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, autoritzacioID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute AutoritzacioFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarAutoritzacio(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __autoritzacioID, Throwable e) {
    java.lang.Long autoritzacioID = (java.lang.Long)__autoritzacioID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (autoritzacioID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(autoritzacioID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "autoritzacio.autoritzacio";
  }

  public String getEntityNameCodePlural() {
    return "autoritzacio.autoritzacio.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("autoritzacio.autoritzacioID");
  }

  @InitBinder("autoritzacioFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("autoritzacioForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "autoritzacio.autoritzacioID");
  }

  public AutoritzacioWebValidator getWebValidator() {
    return autoritzacioWebValidator;
  }


  public void setWebValidator(AutoritzacioWebValidator __val) {
    if (__val != null) {
      this.autoritzacioWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de Autoritzacio
   */
  @RequestMapping(value = "/{autoritzacioID}/cancel")
  public String cancelAutoritzacio(@PathVariable("autoritzacioID") java.lang.Long autoritzacioID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, autoritzacioID);
  }

  /**
   * Entra aqui al pitjar el boto cancel en el la creació de Autoritzacio
   */
  @RequestMapping(value = "/cancel")
  public String cancelAutoritzacio(HttpServletRequest request,HttpServletResponse response) {
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
       ModelAndView mav, AutoritzacioForm autoritzacioForm, Where where)  throws I18NException {
    if (autoritzacioForm.isHiddenField(LLOCID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (autoritzacioForm.isReadOnlyField(LLOCID)) {
      _where = LlocFields.LLOCID.equal(autoritzacioForm.getAutoritzacio().getLlocID());
    }
    return getReferenceListForLlocID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForLlocID(HttpServletRequest request,
       ModelAndView mav, AutoritzacioFilterForm autoritzacioFilterForm,
       List<Autoritzacio> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (autoritzacioFilterForm.isHiddenField(LLOCID)
       && !autoritzacioFilterForm.isGroupByField(LLOCID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(LLOCID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (Autoritzacio _item : list) {
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


  public List<StringKeyValue> getReferenceListForFuncionariID(HttpServletRequest request,
       ModelAndView mav, AutoritzacioForm autoritzacioForm, Where where)  throws I18NException {
    if (autoritzacioForm.isHiddenField(FUNCIONARIID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (autoritzacioForm.isReadOnlyField(FUNCIONARIID)) {
      _where = FuncionariFields.FUNCIONARIID.equal(autoritzacioForm.getAutoritzacio().getFuncionariID());
    }
    return getReferenceListForFuncionariID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForFuncionariID(HttpServletRequest request,
       ModelAndView mav, AutoritzacioFilterForm autoritzacioFilterForm,
       List<Autoritzacio> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (autoritzacioFilterForm.isHiddenField(FUNCIONARIID)
       && !autoritzacioFilterForm.isGroupByField(FUNCIONARIID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(FUNCIONARIID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (Autoritzacio _item : list) {
        if(_item.getFuncionariID() == null) { continue; };
        _pkList.add(_item.getFuncionariID());
        }
        _w = FuncionariFields.FUNCIONARIID.in(_pkList);
      }
    return getReferenceListForFuncionariID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForFuncionariID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return funcionariRefList.getReferenceList(FuncionariFields.FUNCIONARIID, where );
  }


  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public void preValidate(HttpServletRequest request,AutoritzacioForm autoritzacioForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,AutoritzacioForm autoritzacioForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, AutoritzacioFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, AutoritzacioFilterForm filterForm,  List<Autoritzacio> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, AutoritzacioForm autoritzacioForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, AutoritzacioForm autoritzacioForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long autoritzacioID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long autoritzacioID) {
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
    return "autoritzacioFormWebDB";
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
        return "autoritzacioListWebDB";
    }

  public String getSessionAttributeFilterForm() {
    return "Autoritzacio_FilterForm_" + this.getClass().getName();
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public AutoritzacioJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long autoritzacioID) throws I18NException {
    return (AutoritzacioJPA) autoritzacioEjb.findByPrimaryKey(autoritzacioID);
  }


  public AutoritzacioJPA create(HttpServletRequest request, AutoritzacioJPA autoritzacio)
    throws I18NException, I18NValidationException {
    return (AutoritzacioJPA) autoritzacioEjb.create(autoritzacio);
  }


  public AutoritzacioJPA update(HttpServletRequest request, AutoritzacioJPA autoritzacio)
    throws I18NException, I18NValidationException {
    return (AutoritzacioJPA) autoritzacioEjb.update(autoritzacio);
  }


  public void delete(HttpServletRequest request, Autoritzacio autoritzacio) throws I18NException {
    autoritzacioEjb.delete(autoritzacio);
  }

} // Final de Classe

