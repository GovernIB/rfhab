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
import es.caib.rfhab.back.form.webdb.ActivitatForm;

import es.caib.rfhab.back.validator.webdb.ActivitatWebValidator;

import es.caib.rfhab.persistence.ActivitatJPA;
import es.caib.rfhab.model.entity.Activitat;
import es.caib.rfhab.model.fields.*;
import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.fundaciobit.genapp.common.web.tiles.Tile;
import org.fundaciobit.genapp.common.web.tiles.TileAttribute;
import org.fundaciobit.genapp.common.web.tiles.TileType;
import es.caib.rfhab.back.utils.Tab;

/**
 * Controller per gestionar un Activitat
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@MenuOption(labelCode="activitat.activitat.plural", order=0, group=Tab.MENU_WEBDB)
@Controller
@RequestMapping(value = "/webdb/activitat")
@SessionAttributes(types = { ActivitatForm.class, ActivitatFilterForm.class })
@Tile(name="activitatFormWebDB", extendsTile=Tab.MENU_WEBDB,
    // Els següents atributs no són necessaris si heredes aquesta classe
    contentJsp="/WEB-INF/jsp/webdb/activitatForm.jsp", type=TileType.WEBDB_FORM,
    attributes={ @TileAttribute(name="titol", value="activitat.activitat")})
@Tile(name="activitatListWebDB", extendsTile=Tab.MENU_WEBDB,
    // Els següents atributs no són necessaris si heredes aquesta classe 
    contentJsp="/WEB-INF/jsp/webdb/activitatList.jsp", type=TileType.WEBDB_LIST,
    attributes={ @TileAttribute(name="titol", value="activitat.activitat")})
public class ActivitatController
    extends es.caib.rfhab.back.controller.RFHabBaseController<Activitat, java.lang.Long> implements ActivitatFields {

  @EJB(mappedName = es.caib.rfhab.ejb.ActivitatService.JNDI_NAME)
  protected es.caib.rfhab.ejb.ActivitatService activitatEjb;

  @Autowired
  private ActivitatWebValidator activitatWebValidator;

  @Autowired
  protected ActivitatRefList activitatRefList;

  // References 
  @Autowired
  protected FuncionariRefList funcionariRefList;

  /**
   * Llistat de totes Activitat
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    ActivitatFilterForm ff;
    ff = (ActivitatFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar Activitat de forma paginada
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
    llistat(mav, request, getActivitatFilterForm(pagina, mav, request));
    return mav;
  }

  public ActivitatFilterForm getActivitatFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    ActivitatFilterForm activitatFilterForm;
    activitatFilterForm = (ActivitatFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(activitatFilterForm == null) {
      activitatFilterForm = new ActivitatFilterForm();
      activitatFilterForm.setContexte(getContextWeb());
      activitatFilterForm.setEntityNameCode(getEntityNameCode());
      activitatFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      activitatFilterForm.setNou(true);
    } else {
      activitatFilterForm.setNou(false);
    }
    activitatFilterForm.setPage(pagina == null ? 1 : pagina);
    return activitatFilterForm;
  }

  /**
   * Segona i següent peticions per llistar Activitat de forma paginada
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
      @ModelAttribute ActivitatFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getActivitatFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de Activitat de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<Activitat> llistat(ModelAndView mav, HttpServletRequest request,
     ActivitatFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<Activitat> activitat = processarLlistat(activitatEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("activitatItems", activitat);

    mav.addObject("activitatFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, activitat, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, activitat);

    return activitat;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(ActivitatFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<Activitat> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field funcionariID
    {
      _listSKV = getReferenceListForFuncionariID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfFuncionariForFuncionariID(_tmp);
      if (filterForm.getGroupByFields().contains(FUNCIONARIID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, FUNCIONARIID, false);
      };
    }

    // Field tipus
    {
      _listSKV = getReferenceListForTipus(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfValuesForTipus(_tmp);
      if (filterForm.getGroupByFields().contains(TIPUS)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, TIPUS, false);
      };
    }

    // Field estat
    {
      _listSKV = getReferenceListForEstat(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfValuesForEstat(_tmp);
      if (filterForm.getGroupByFields().contains(ESTAT)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, ESTAT, false);
      };
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    ActivitatFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<Activitat> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_ACTIVITAT_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(FUNCIONARIID, filterForm.getMapOfFuncionariForFuncionariID());
    __mapping.put(TIPUS, filterForm.getMapOfValuesForTipus());
    __mapping.put(ESTAT, filterForm.getMapOfValuesForEstat());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou Activitat
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearActivitatGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    ActivitatForm activitatForm = getActivitatForm(null, false, request, mav);
    mav.addObject("activitatForm" ,activitatForm);
    fillReferencesForForm(activitatForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public ActivitatForm getActivitatForm(ActivitatJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    ActivitatForm activitatForm;
    if(_jpa == null) {
      activitatForm = new ActivitatForm(new ActivitatJPA(), true);
    } else {
      activitatForm = new ActivitatForm(_jpa, false);
      activitatForm.setView(__isView);
    }
    activitatForm.setContexte(getContextWeb());
    activitatForm.setEntityNameCode(getEntityNameCode());
    activitatForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return activitatForm;
  }

  public void fillReferencesForForm(ActivitatForm activitatForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (activitatForm.getListOfFuncionariForFuncionariID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForFuncionariID(request, mav, activitatForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      activitatForm.setListOfFuncionariForFuncionariID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (activitatForm.getListOfValuesForTipus() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForTipus(request, mav, activitatForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      activitatForm.setListOfValuesForTipus(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (activitatForm.getListOfValuesForEstat() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForEstat(request, mav, activitatForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      activitatForm.setListOfValuesForEstat(_listSKV);
    }
    
  }

  /**
   * Guardar un nou Activitat
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearActivitatPost(@ModelAttribute ActivitatForm activitatForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ActivitatJPA activitat = activitatForm.getActivitat();

    try {
      preValidate(request, activitatForm, result);
      getWebValidator().validate(activitatForm, result);
      postValidate(request,activitatForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        activitat = create(request, activitat);
        createMessageSuccess(request, "success.creation", activitat.getActivitatID());
        activitatForm.setActivitat(activitat);
        return getRedirectWhenCreated(request, activitatForm);
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

  @RequestMapping(value = "/view/{activitatID}", method = RequestMethod.GET)
  public ModelAndView veureActivitatGet(@PathVariable("activitatID") java.lang.Long activitatID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewActivitatGet(activitatID,
        request, response, true);
  }


  protected ModelAndView editAndViewActivitatGet(@PathVariable("activitatID") java.lang.Long activitatID,
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
    ActivitatJPA activitat = findByPrimaryKey(request, activitatID);

    if (activitat == null) {
      createMessageWarning(request, "error.notfound", activitatID);
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      ActivitatForm activitatForm = getActivitatForm(activitat, __isView, request, mav);
      activitatForm.setView(__isView);
      if(__isView) {
        activitatForm.setAllFieldsReadOnly(ALL_ACTIVITAT_FIELDS);
        activitatForm.setSaveButtonVisible(false);
        activitatForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(activitatForm, request, mav);
      mav.addObject("activitatForm", activitatForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un Activitat existent
   */
  @RequestMapping(value = "/{activitatID}/edit", method = RequestMethod.GET)
  public ModelAndView editarActivitatGet(@PathVariable("activitatID") java.lang.Long activitatID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewActivitatGet(activitatID,
        request, response, false);
  }



  /**
   * Editar un Activitat existent
   */
  @RequestMapping(value = "/{activitatID}/edit", method = RequestMethod.POST)
  public String editarActivitatPost(@ModelAttribute ActivitatForm activitatForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ActivitatJPA activitat = activitatForm.getActivitat();

    try {
      preValidate(request, activitatForm, result);
      getWebValidator().validate(activitatForm, result);
      postValidate(request, activitatForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        activitat = update(request, activitat);
        createMessageSuccess(request, "success.modification", activitat.getActivitatID());
        status.setComplete();
        return getRedirectWhenModified(request, activitatForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          activitat.getActivitatID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, activitatForm, __e);
    }

  }


  /**
   * Eliminar un Activitat existent
   */
  @RequestMapping(value = "/{activitatID}/delete")
  public String eliminarActivitat(@PathVariable("activitatID") java.lang.Long activitatID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      Activitat activitat = this.findByPrimaryKey(request, activitatID);
      if (activitat == null) {
        String __msg = createMessageError(request, "error.notfound", activitatID);
        return getRedirectWhenDelete(request, activitatID, new Exception(__msg));
      } else {
        delete(request, activitat);
        createMessageSuccess(request, "success.deleted", activitatID);
        return getRedirectWhenDelete(request, activitatID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", activitatID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, activitatID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute ActivitatFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarActivitat(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __activitatID, Throwable e) {
    java.lang.Long activitatID = (java.lang.Long)__activitatID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (activitatID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(activitatID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "activitat.activitat";
  }

  public String getEntityNameCodePlural() {
    return "activitat.activitat.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("activitat.activitatID");
  }

  @InitBinder("activitatFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("activitatForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "activitat.activitatID");
  }

  public ActivitatWebValidator getWebValidator() {
    return activitatWebValidator;
  }


  public void setWebValidator(ActivitatWebValidator __val) {
    if (__val != null) {
      this.activitatWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de Activitat
   */
  @RequestMapping(value = "/{activitatID}/cancel")
  public String cancelActivitat(@PathVariable("activitatID") java.lang.Long activitatID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, activitatID);
  }

  /**
   * Entra aqui al pitjar el boto cancel en el la creació de Activitat
   */
  @RequestMapping(value = "/cancel")
  public String cancelActivitat(HttpServletRequest request,HttpServletResponse response) {
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


  public List<StringKeyValue> getReferenceListForFuncionariID(HttpServletRequest request,
       ModelAndView mav, ActivitatForm activitatForm, Where where)  throws I18NException {
    if (activitatForm.isHiddenField(FUNCIONARIID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (activitatForm.isReadOnlyField(FUNCIONARIID)) {
      _where = FuncionariFields.FUNCIONARIID.equal(activitatForm.getActivitat().getFuncionariID());
    }
    return getReferenceListForFuncionariID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForFuncionariID(HttpServletRequest request,
       ModelAndView mav, ActivitatFilterForm activitatFilterForm,
       List<Activitat> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (activitatFilterForm.isHiddenField(FUNCIONARIID)
       && !activitatFilterForm.isGroupByField(FUNCIONARIID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(FUNCIONARIID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (Activitat _item : list) {
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


  public List<StringKeyValue> getReferenceListForTipus(HttpServletRequest request,
       ModelAndView mav, ActivitatForm activitatForm, Where where)  throws I18NException {
    if (activitatForm.isHiddenField(TIPUS)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForTipus(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForTipus(HttpServletRequest request,
       ModelAndView mav, ActivitatFilterForm activitatFilterForm,
       List<Activitat> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (activitatFilterForm.isHiddenField(TIPUS)
       && !activitatFilterForm.isGroupByField(TIPUS)
       && !activitatFilterForm.isFilterByField(TIPUS)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    return getReferenceListForTipus(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForTipus(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    __tmp.add(new StringKeyValue("1" , "1"));
    __tmp.add(new StringKeyValue("2" , "2"));
    __tmp.add(new StringKeyValue("3" , "3"));
    return __tmp;
  }


  public List<StringKeyValue> getReferenceListForEstat(HttpServletRequest request,
       ModelAndView mav, ActivitatForm activitatForm, Where where)  throws I18NException {
    if (activitatForm.isHiddenField(ESTAT)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForEstat(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForEstat(HttpServletRequest request,
       ModelAndView mav, ActivitatFilterForm activitatFilterForm,
       List<Activitat> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (activitatFilterForm.isHiddenField(ESTAT)
       && !activitatFilterForm.isGroupByField(ESTAT)
       && !activitatFilterForm.isFilterByField(ESTAT)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    return getReferenceListForEstat(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForEstat(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    __tmp.add(new StringKeyValue("0" , "0"));
    __tmp.add(new StringKeyValue("1" , "1"));
    __tmp.add(new StringKeyValue("2" , "2"));
    __tmp.add(new StringKeyValue("3" , "3"));
    return __tmp;
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

  public void preValidate(HttpServletRequest request,ActivitatForm activitatForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,ActivitatForm activitatForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, ActivitatFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, ActivitatFilterForm filterForm,  List<Activitat> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, ActivitatForm activitatForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, ActivitatForm activitatForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long activitatID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long activitatID) {
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
    return "activitatFormWebDB";
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
        return "activitatListWebDB";
    }

  public String getSessionAttributeFilterForm() {
    return "Activitat_FilterForm_" + this.getClass().getName();
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public ActivitatJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long activitatID) throws I18NException {
    return (ActivitatJPA) activitatEjb.findByPrimaryKey(activitatID);
  }


  public ActivitatJPA create(HttpServletRequest request, ActivitatJPA activitat)
    throws I18NException, I18NValidationException {
    return (ActivitatJPA) activitatEjb.create(activitat);
  }


  public ActivitatJPA update(HttpServletRequest request, ActivitatJPA activitat)
    throws I18NException, I18NValidationException {
    return (ActivitatJPA) activitatEjb.update(activitat);
  }


  public void delete(HttpServletRequest request, Activitat activitat) throws I18NException {
    activitatEjb.delete(activitat);
  }

} // Final de Classe

