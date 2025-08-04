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
import es.caib.rfhab.back.form.webdb.LlocForm;

import es.caib.rfhab.back.validator.webdb.LlocWebValidator;

import es.caib.rfhab.persistence.LlocJPA;
import es.caib.rfhab.model.entity.Lloc;
import es.caib.rfhab.model.fields.*;
import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.fundaciobit.genapp.common.web.tiles.Tile;
import org.fundaciobit.genapp.common.web.tiles.TileAttribute;
import org.fundaciobit.genapp.common.web.tiles.TileType;
import es.caib.rfhab.back.utils.Tab;

/**
 * Controller per gestionar un Lloc
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@MenuOption(labelCode="lloc.lloc.plural", order=90, group=Tab.MENU_WEBDB)
@Controller
@RequestMapping(value = "/webdb/lloc")
@SessionAttributes(types = { LlocForm.class, LlocFilterForm.class })
@Tile(name="llocFormWebDB", contentJsp="/WEB-INF/jsp/webdb/llocForm.jsp", extendsTile=Tab.MENU_WEBDB,
      type=TileType.WEBDB_FORM , attributes={ @TileAttribute(name="titol", value="lloc.lloc")})
@Tile(name="llocListWebDB", contentJsp="/WEB-INF/jsp/webdb/llocList.jsp", extendsTile=Tab.MENU_WEBDB,
       type=TileType.WEBDB_LIST, attributes={ @TileAttribute(name="titol", value="lloc.lloc") })
public class LlocController
    extends es.caib.rfhab.back.controller.RFHabBaseController<Lloc, java.lang.Long> implements LlocFields {

  @EJB(mappedName = es.caib.rfhab.ejb.LlocService.JNDI_NAME)
  protected es.caib.rfhab.ejb.LlocService llocEjb;

  @Autowired
  private LlocWebValidator llocWebValidator;

  @Autowired
  protected LlocRefList llocRefList;

  // References 
  @Autowired
  protected EntitatRefList entitatRefList;

  // References 
  @Autowired
  protected UnitatRefList unitatRefList;

  /**
   * Llistat de totes Lloc
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    LlocFilterForm ff;
    ff = (LlocFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar Lloc de forma paginada
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
    llistat(mav, request, getLlocFilterForm(pagina, mav, request));
    return mav;
  }

  public LlocFilterForm getLlocFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    LlocFilterForm llocFilterForm;
    llocFilterForm = (LlocFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(llocFilterForm == null) {
      llocFilterForm = new LlocFilterForm();
      llocFilterForm.setContexte(getContextWeb());
      llocFilterForm.setEntityNameCode(getEntityNameCode());
      llocFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      llocFilterForm.setNou(true);
    } else {
      llocFilterForm.setNou(false);
    }
    llocFilterForm.setPage(pagina == null ? 1 : pagina);
    return llocFilterForm;
  }

  /**
   * Segona i següent peticions per llistar Lloc de forma paginada
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
      @ModelAttribute LlocFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getLlocFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de Lloc de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<Lloc> llistat(ModelAndView mav, HttpServletRequest request,
     LlocFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<Lloc> lloc = processarLlistat(llocEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("llocItems", lloc);

    mav.addObject("llocFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, lloc, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, lloc);

    return lloc;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(LlocFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<Lloc> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field entitatID
    {
      _listSKV = getReferenceListForEntitatID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfEntitatForEntitatID(_tmp);
      if (filterForm.getGroupByFields().contains(ENTITATID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, ENTITATID, false);
      };
    }

    // Field unitatID
    {
      _listSKV = getReferenceListForUnitatID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfUnitatForUnitatID(_tmp);
      if (filterForm.getGroupByFields().contains(UNITATID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, UNITATID, false);
      };
    }

    // Field personalOamr
    {
      _listSKV = getReferenceListForPersonalOamr(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfValuesForPersonalOamr(_tmp);
      if (filterForm.getGroupByFields().contains(PERSONALOAMR)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, PERSONALOAMR, false);
      };
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    LlocFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<Lloc> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_LLOC_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(ENTITATID, filterForm.getMapOfEntitatForEntitatID());
    __mapping.put(UNITATID, filterForm.getMapOfUnitatForUnitatID());
    __mapping.put(PERSONALOAMR, filterForm.getMapOfValuesForPersonalOamr());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou Lloc
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearLlocGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    LlocForm llocForm = getLlocForm(null, false, request, mav);
    mav.addObject("llocForm" ,llocForm);
    fillReferencesForForm(llocForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public LlocForm getLlocForm(LlocJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    LlocForm llocForm;
    if(_jpa == null) {
      llocForm = new LlocForm(new LlocJPA(), true);
    } else {
      llocForm = new LlocForm(_jpa, false);
      llocForm.setView(__isView);
    }
    llocForm.setContexte(getContextWeb());
    llocForm.setEntityNameCode(getEntityNameCode());
    llocForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return llocForm;
  }

  public void fillReferencesForForm(LlocForm llocForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (llocForm.getListOfEntitatForEntitatID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForEntitatID(request, mav, llocForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      llocForm.setListOfEntitatForEntitatID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (llocForm.getListOfUnitatForUnitatID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForUnitatID(request, mav, llocForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      llocForm.setListOfUnitatForUnitatID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (llocForm.getListOfValuesForPersonalOamr() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForPersonalOamr(request, mav, llocForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      llocForm.setListOfValuesForPersonalOamr(_listSKV);
    }
    
  }

  /**
   * Guardar un nou Lloc
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearLlocPost(@ModelAttribute LlocForm llocForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    LlocJPA lloc = llocForm.getLloc();

    try {
      preValidate(request, llocForm, result);
      getWebValidator().validate(llocForm, result);
      postValidate(request,llocForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        lloc = create(request, lloc);
        createMessageSuccess(request, "success.creation", lloc.getLlocID());
        llocForm.setLloc(lloc);
        return getRedirectWhenCreated(request, llocForm);
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

  @RequestMapping(value = "/view/{llocID}", method = RequestMethod.GET)
  public ModelAndView veureLlocGet(@PathVariable("llocID") java.lang.Long llocID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewLlocGet(llocID,
        request, response, true);
  }


  protected ModelAndView editAndViewLlocGet(@PathVariable("llocID") java.lang.Long llocID,
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
    LlocJPA lloc = findByPrimaryKey(request, llocID);

    if (lloc == null) {
      createMessageWarning(request, "error.notfound", llocID);
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      LlocForm llocForm = getLlocForm(lloc, __isView, request, mav);
      llocForm.setView(__isView);
      if(__isView) {
        llocForm.setAllFieldsReadOnly(ALL_LLOC_FIELDS);
        llocForm.setSaveButtonVisible(false);
        llocForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(llocForm, request, mav);
      mav.addObject("llocForm", llocForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un Lloc existent
   */
  @RequestMapping(value = "/{llocID}/edit", method = RequestMethod.GET)
  public ModelAndView editarLlocGet(@PathVariable("llocID") java.lang.Long llocID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewLlocGet(llocID,
        request, response, false);
  }



  /**
   * Editar un Lloc existent
   */
  @RequestMapping(value = "/{llocID}/edit", method = RequestMethod.POST)
  public String editarLlocPost(@ModelAttribute LlocForm llocForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    LlocJPA lloc = llocForm.getLloc();

    try {
      preValidate(request, llocForm, result);
      getWebValidator().validate(llocForm, result);
      postValidate(request, llocForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        lloc = update(request, lloc);
        createMessageSuccess(request, "success.modification", lloc.getLlocID());
        status.setComplete();
        return getRedirectWhenModified(request, llocForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          lloc.getLlocID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, llocForm, __e);
    }

  }


  /**
   * Eliminar un Lloc existent
   */
  @RequestMapping(value = "/{llocID}/delete")
  public String eliminarLloc(@PathVariable("llocID") java.lang.Long llocID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      Lloc lloc = this.findByPrimaryKey(request, llocID);
      if (lloc == null) {
        String __msg = createMessageError(request, "error.notfound", llocID);
        return getRedirectWhenDelete(request, llocID, new Exception(__msg));
      } else {
        delete(request, lloc);
        createMessageSuccess(request, "success.deleted", llocID);
        return getRedirectWhenDelete(request, llocID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", llocID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, llocID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute LlocFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarLloc(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __llocID, Throwable e) {
    java.lang.Long llocID = (java.lang.Long)__llocID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (llocID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(llocID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "lloc.lloc";
  }

  public String getEntityNameCodePlural() {
    return "lloc.lloc.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("lloc.llocID");
  }

  @InitBinder("llocFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("llocForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "lloc.llocID");
  }

  public LlocWebValidator getWebValidator() {
    return llocWebValidator;
  }


  public void setWebValidator(LlocWebValidator __val) {
    if (__val != null) {
      this.llocWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de Lloc
   */
  @RequestMapping(value = "/{llocID}/cancel")
  public String cancelLloc(@PathVariable("llocID") java.lang.Long llocID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, llocID);
  }

  /**
   * Entra aqui al pitjar el boto cancel en el la creació de Lloc
   */
  @RequestMapping(value = "/cancel")
  public String cancelLloc(HttpServletRequest request,HttpServletResponse response) {
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


  public List<StringKeyValue> getReferenceListForEntitatID(HttpServletRequest request,
       ModelAndView mav, LlocForm llocForm, Where where)  throws I18NException {
    if (llocForm.isHiddenField(ENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (llocForm.isReadOnlyField(ENTITATID)) {
      _where = EntitatFields.ENTITATID.equal(llocForm.getLloc().getEntitatID());
    }
    return getReferenceListForEntitatID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForEntitatID(HttpServletRequest request,
       ModelAndView mav, LlocFilterForm llocFilterForm,
       List<Lloc> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (llocFilterForm.isHiddenField(ENTITATID)
       && !llocFilterForm.isGroupByField(ENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(ENTITATID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (Lloc _item : list) {
        _pkList.add(_item.getEntitatID());
        }
        _w = EntitatFields.ENTITATID.in(_pkList);
      }
    return getReferenceListForEntitatID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForEntitatID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return entitatRefList.getReferenceList(EntitatFields.ENTITATID, where );
  }


  public List<StringKeyValue> getReferenceListForUnitatID(HttpServletRequest request,
       ModelAndView mav, LlocForm llocForm, Where where)  throws I18NException {
    if (llocForm.isHiddenField(UNITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (llocForm.isReadOnlyField(UNITATID)) {
      _where = UnitatFields.UNITATID.equal(llocForm.getLloc().getUnitatID());
    }
    return getReferenceListForUnitatID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForUnitatID(HttpServletRequest request,
       ModelAndView mav, LlocFilterForm llocFilterForm,
       List<Lloc> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (llocFilterForm.isHiddenField(UNITATID)
       && !llocFilterForm.isGroupByField(UNITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(UNITATID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (Lloc _item : list) {
        _pkList.add(_item.getUnitatID());
        }
        _w = UnitatFields.UNITATID.in(_pkList);
      }
    return getReferenceListForUnitatID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForUnitatID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return unitatRefList.getReferenceList(UnitatFields.UNITATID, where );
  }


  public List<StringKeyValue> getReferenceListForPersonalOamr(HttpServletRequest request,
       ModelAndView mav, LlocForm llocForm, Where where)  throws I18NException {
    if (llocForm.isHiddenField(PERSONALOAMR)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForPersonalOamr(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForPersonalOamr(HttpServletRequest request,
       ModelAndView mav, LlocFilterForm llocFilterForm,
       List<Lloc> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (llocFilterForm.isHiddenField(PERSONALOAMR)
       && !llocFilterForm.isGroupByField(PERSONALOAMR)
       && !llocFilterForm.isFilterByField(PERSONALOAMR)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    return getReferenceListForPersonalOamr(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForPersonalOamr(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    __tmp.add(new StringKeyValue("0" , "0"));
    __tmp.add(new StringKeyValue("1" , "1"));
    return __tmp;
  }


  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public void preValidate(HttpServletRequest request,LlocForm llocForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,LlocForm llocForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, LlocFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, LlocFilterForm filterForm,  List<Lloc> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, LlocForm llocForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, LlocForm llocForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long llocID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long llocID) {
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
    return "llocFormWebDB";
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
        return "llocListWebDB";
    }

  public String getSessionAttributeFilterForm() {
    return "Lloc_FilterForm_" + this.getClass().getName();
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public LlocJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long llocID) throws I18NException {
    return (LlocJPA) llocEjb.findByPrimaryKey(llocID);
  }


  public LlocJPA create(HttpServletRequest request, LlocJPA lloc)
    throws I18NException, I18NValidationException {
    return (LlocJPA) llocEjb.create(lloc);
  }


  public LlocJPA update(HttpServletRequest request, LlocJPA lloc)
    throws I18NException, I18NValidationException {
    return (LlocJPA) llocEjb.update(lloc);
  }


  public void delete(HttpServletRequest request, Lloc lloc) throws I18NException {
    llocEjb.delete(lloc);
  }

} // Final de Classe

