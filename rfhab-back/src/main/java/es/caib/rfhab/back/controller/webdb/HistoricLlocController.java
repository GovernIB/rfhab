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
import es.caib.rfhab.back.form.webdb.HistoricLlocForm;

import es.caib.rfhab.back.validator.webdb.HistoricLlocWebValidator;

import es.caib.rfhab.persistence.HistoricLlocJPA;
import es.caib.rfhab.model.entity.HistoricLloc;
import es.caib.rfhab.model.fields.*;
import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.fundaciobit.genapp.common.web.tiles.Tile;
import org.fundaciobit.genapp.common.web.tiles.TileAttribute;
import org.fundaciobit.genapp.common.web.tiles.TileType;
import es.caib.rfhab.back.utils.Tab;

/**
 * Controller per gestionar un HistoricLloc
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@MenuOption(labelCode="historicLloc.historicLloc.plural", order=80, group=Tab.MENU_WEBDB)
@Controller
@RequestMapping(value = "/webdb/historicLloc")
@SessionAttributes(types = { HistoricLlocForm.class, HistoricLlocFilterForm.class })
@Tile(name="historicLlocFormWebDB", contentJsp="/WEB-INF/jsp/webdb/historicLlocForm.jsp", extendsTile=Tab.MENU_WEBDB,
      type=TileType.WEBDB_FORM , attributes={ @TileAttribute(name="titol", value="historicLloc.historicLloc")})
@Tile(name="historicLlocListWebDB", contentJsp="/WEB-INF/jsp/webdb/historicLlocList.jsp", extendsTile=Tab.MENU_WEBDB,
       type=TileType.WEBDB_LIST, attributes={ @TileAttribute(name="titol", value="historicLloc.historicLloc") })
public class HistoricLlocController
    extends es.caib.rfhab.back.controller.RFHabBaseController<HistoricLloc, java.lang.Long> implements HistoricLlocFields {

  @EJB(mappedName = es.caib.rfhab.ejb.HistoricLlocService.JNDI_NAME)
  protected es.caib.rfhab.ejb.HistoricLlocService historicLlocEjb;

  @Autowired
  private HistoricLlocWebValidator historicLlocWebValidator;

  @Autowired
  protected HistoricLlocRefList historicLlocRefList;

  // References 
  @Autowired
  protected LlocRefList llocRefList;

  // References 
  @Autowired
  protected UsuariRefList usuariRefList;

  /**
   * Llistat de totes HistoricLloc
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    HistoricLlocFilterForm ff;
    ff = (HistoricLlocFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar HistoricLloc de forma paginada
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
    llistat(mav, request, getHistoricLlocFilterForm(pagina, mav, request));
    return mav;
  }

  public HistoricLlocFilterForm getHistoricLlocFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    HistoricLlocFilterForm historicLlocFilterForm;
    historicLlocFilterForm = (HistoricLlocFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(historicLlocFilterForm == null) {
      historicLlocFilterForm = new HistoricLlocFilterForm();
      historicLlocFilterForm.setContexte(getContextWeb());
      historicLlocFilterForm.setEntityNameCode(getEntityNameCode());
      historicLlocFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      historicLlocFilterForm.setNou(true);
    } else {
      historicLlocFilterForm.setNou(false);
    }
    historicLlocFilterForm.setPage(pagina == null ? 1 : pagina);
    return historicLlocFilterForm;
  }

  /**
   * Segona i següent peticions per llistar HistoricLloc de forma paginada
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
      @ModelAttribute HistoricLlocFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getHistoricLlocFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de HistoricLloc de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<HistoricLloc> llistat(ModelAndView mav, HttpServletRequest request,
     HistoricLlocFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<HistoricLloc> historicLloc = processarLlistat(historicLlocEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("historicLlocItems", historicLloc);

    mav.addObject("historicLlocFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, historicLloc, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, historicLloc);

    return historicLloc;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(HistoricLlocFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<HistoricLloc> list, List<GroupByItem> groupItems) throws I18NException {
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

    // Field usuariID
    {
      _listSKV = getReferenceListForUsuariID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfUsuariForUsuariID(_tmp);
      if (filterForm.getGroupByFields().contains(USUARIID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, USUARIID, false);
      };
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    HistoricLlocFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<HistoricLloc> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_HISTORICLLOC_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(LLOCID, filterForm.getMapOfLlocForLlocID());
    __mapping.put(USUARIID, filterForm.getMapOfUsuariForUsuariID());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou HistoricLloc
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearHistoricLlocGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    HistoricLlocForm historicLlocForm = getHistoricLlocForm(null, false, request, mav);
    mav.addObject("historicLlocForm" ,historicLlocForm);
    fillReferencesForForm(historicLlocForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public HistoricLlocForm getHistoricLlocForm(HistoricLlocJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    HistoricLlocForm historicLlocForm;
    if(_jpa == null) {
      historicLlocForm = new HistoricLlocForm(new HistoricLlocJPA(), true);
    } else {
      historicLlocForm = new HistoricLlocForm(_jpa, false);
      historicLlocForm.setView(__isView);
    }
    historicLlocForm.setContexte(getContextWeb());
    historicLlocForm.setEntityNameCode(getEntityNameCode());
    historicLlocForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return historicLlocForm;
  }

  public void fillReferencesForForm(HistoricLlocForm historicLlocForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (historicLlocForm.getListOfLlocForLlocID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForLlocID(request, mav, historicLlocForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      historicLlocForm.setListOfLlocForLlocID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (historicLlocForm.getListOfUsuariForUsuariID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForUsuariID(request, mav, historicLlocForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      historicLlocForm.setListOfUsuariForUsuariID(_listSKV);
    }
    
  }

  /**
   * Guardar un nou HistoricLloc
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearHistoricLlocPost(@ModelAttribute HistoricLlocForm historicLlocForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    HistoricLlocJPA historicLloc = historicLlocForm.getHistoricLloc();

    try {
      preValidate(request, historicLlocForm, result);
      getWebValidator().validate(historicLlocForm, result);
      postValidate(request,historicLlocForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        historicLloc = create(request, historicLloc);
        createMessageSuccess(request, "success.creation", historicLloc.getHistoricllocID());
        historicLlocForm.setHistoricLloc(historicLloc);
        return getRedirectWhenCreated(request, historicLlocForm);
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

  @RequestMapping(value = "/view/{historicllocID}", method = RequestMethod.GET)
  public ModelAndView veureHistoricLlocGet(@PathVariable("historicllocID") java.lang.Long historicllocID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewHistoricLlocGet(historicllocID,
        request, response, true);
  }


  protected ModelAndView editAndViewHistoricLlocGet(@PathVariable("historicllocID") java.lang.Long historicllocID,
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
    HistoricLlocJPA historicLloc = findByPrimaryKey(request, historicllocID);

    if (historicLloc == null) {
      createMessageWarning(request, "error.notfound", historicllocID);
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      HistoricLlocForm historicLlocForm = getHistoricLlocForm(historicLloc, __isView, request, mav);
      historicLlocForm.setView(__isView);
      if(__isView) {
        historicLlocForm.setAllFieldsReadOnly(ALL_HISTORICLLOC_FIELDS);
        historicLlocForm.setSaveButtonVisible(false);
        historicLlocForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(historicLlocForm, request, mav);
      mav.addObject("historicLlocForm", historicLlocForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un HistoricLloc existent
   */
  @RequestMapping(value = "/{historicllocID}/edit", method = RequestMethod.GET)
  public ModelAndView editarHistoricLlocGet(@PathVariable("historicllocID") java.lang.Long historicllocID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewHistoricLlocGet(historicllocID,
        request, response, false);
  }



  /**
   * Editar un HistoricLloc existent
   */
  @RequestMapping(value = "/{historicllocID}/edit", method = RequestMethod.POST)
  public String editarHistoricLlocPost(@ModelAttribute HistoricLlocForm historicLlocForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    HistoricLlocJPA historicLloc = historicLlocForm.getHistoricLloc();

    try {
      preValidate(request, historicLlocForm, result);
      getWebValidator().validate(historicLlocForm, result);
      postValidate(request, historicLlocForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        historicLloc = update(request, historicLloc);
        createMessageSuccess(request, "success.modification", historicLloc.getHistoricllocID());
        status.setComplete();
        return getRedirectWhenModified(request, historicLlocForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          historicLloc.getHistoricllocID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, historicLlocForm, __e);
    }

  }


  /**
   * Eliminar un HistoricLloc existent
   */
  @RequestMapping(value = "/{historicllocID}/delete")
  public String eliminarHistoricLloc(@PathVariable("historicllocID") java.lang.Long historicllocID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      HistoricLloc historicLloc = this.findByPrimaryKey(request, historicllocID);
      if (historicLloc == null) {
        String __msg = createMessageError(request, "error.notfound", historicllocID);
        return getRedirectWhenDelete(request, historicllocID, new Exception(__msg));
      } else {
        delete(request, historicLloc);
        createMessageSuccess(request, "success.deleted", historicllocID);
        return getRedirectWhenDelete(request, historicllocID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", historicllocID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, historicllocID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute HistoricLlocFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarHistoricLloc(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __historicllocID, Throwable e) {
    java.lang.Long historicllocID = (java.lang.Long)__historicllocID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (historicllocID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(historicllocID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "historicLloc.historicLloc";
  }

  public String getEntityNameCodePlural() {
    return "historicLloc.historicLloc.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("historicLloc.historicllocID");
  }

  @InitBinder("historicLlocFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("historicLlocForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "historicLloc.historicllocID");
  }

  public HistoricLlocWebValidator getWebValidator() {
    return historicLlocWebValidator;
  }


  public void setWebValidator(HistoricLlocWebValidator __val) {
    if (__val != null) {
      this.historicLlocWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de HistoricLloc
   */
  @RequestMapping(value = "/{historicllocID}/cancel")
  public String cancelHistoricLloc(@PathVariable("historicllocID") java.lang.Long historicllocID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, historicllocID);
  }

  /**
   * Entra aqui al pitjar el boto cancel en el la creació de HistoricLloc
   */
  @RequestMapping(value = "/cancel")
  public String cancelHistoricLloc(HttpServletRequest request,HttpServletResponse response) {
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
       ModelAndView mav, HistoricLlocForm historicLlocForm, Where where)  throws I18NException {
    if (historicLlocForm.isHiddenField(LLOCID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (historicLlocForm.isReadOnlyField(LLOCID)) {
      _where = LlocFields.LLOCID.equal(historicLlocForm.getHistoricLloc().getLlocID());
    }
    return getReferenceListForLlocID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForLlocID(HttpServletRequest request,
       ModelAndView mav, HistoricLlocFilterForm historicLlocFilterForm,
       List<HistoricLloc> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (historicLlocFilterForm.isHiddenField(LLOCID)
       && !historicLlocFilterForm.isGroupByField(LLOCID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(LLOCID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (HistoricLloc _item : list) {
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


  public List<StringKeyValue> getReferenceListForUsuariID(HttpServletRequest request,
       ModelAndView mav, HistoricLlocForm historicLlocForm, Where where)  throws I18NException {
    if (historicLlocForm.isHiddenField(USUARIID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (historicLlocForm.isReadOnlyField(USUARIID)) {
      _where = UsuariFields.USUARIID.equal(historicLlocForm.getHistoricLloc().getUsuariID());
    }
    return getReferenceListForUsuariID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForUsuariID(HttpServletRequest request,
       ModelAndView mav, HistoricLlocFilterForm historicLlocFilterForm,
       List<HistoricLloc> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (historicLlocFilterForm.isHiddenField(USUARIID)
       && !historicLlocFilterForm.isGroupByField(USUARIID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(USUARIID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (HistoricLloc _item : list) {
        if(_item.getUsuariID() == null) { continue; };
        _pkList.add(_item.getUsuariID());
        }
        _w = UsuariFields.USUARIID.in(_pkList);
      }
    return getReferenceListForUsuariID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForUsuariID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return usuariRefList.getReferenceList(UsuariFields.USUARIID, where );
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

  public void preValidate(HttpServletRequest request,HistoricLlocForm historicLlocForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,HistoricLlocForm historicLlocForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, HistoricLlocFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, HistoricLlocFilterForm filterForm,  List<HistoricLloc> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, HistoricLlocForm historicLlocForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, HistoricLlocForm historicLlocForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long historicllocID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long historicllocID) {
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
    return "historicLlocFormWebDB";
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
        return "historicLlocListWebDB";
    }

  public String getSessionAttributeFilterForm() {
    return "HistoricLloc_FilterForm_" + this.getClass().getName();
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public HistoricLlocJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long historicllocID) throws I18NException {
    return (HistoricLlocJPA) historicLlocEjb.findByPrimaryKey(historicllocID);
  }


  public HistoricLlocJPA create(HttpServletRequest request, HistoricLlocJPA historicLloc)
    throws I18NException, I18NValidationException {
    return (HistoricLlocJPA) historicLlocEjb.create(historicLloc);
  }


  public HistoricLlocJPA update(HttpServletRequest request, HistoricLlocJPA historicLloc)
    throws I18NException, I18NValidationException {
    return (HistoricLlocJPA) historicLlocEjb.update(historicLloc);
  }


  public void delete(HttpServletRequest request, HistoricLloc historicLloc) throws I18NException {
    historicLlocEjb.delete(historicLloc);
  }

} // Final de Classe

