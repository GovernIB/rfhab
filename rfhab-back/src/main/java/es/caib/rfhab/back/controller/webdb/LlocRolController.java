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
import es.caib.rfhab.back.form.webdb.LlocRolForm;

import es.caib.rfhab.back.validator.webdb.LlocRolWebValidator;

import es.caib.rfhab.persistence.LlocRolJPA;
import es.caib.rfhab.model.entity.LlocRol;
import es.caib.rfhab.model.fields.*;
import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.fundaciobit.genapp.common.web.tiles.Tile;
import org.fundaciobit.genapp.common.web.tiles.TileAttribute;
import org.fundaciobit.genapp.common.web.tiles.TileType;
import es.caib.rfhab.back.utils.Tab;

/**
 * Controller per gestionar un LlocRol
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@MenuOption(labelCode="llocRol.llocRol.plural", order=100, group=Tab.MENU_WEBDB)
@Controller
@RequestMapping(value = "/webdb/llocRol")
@SessionAttributes(types = { LlocRolForm.class, LlocRolFilterForm.class })
@Tile(name="llocRolFormWebDB", contentJsp="/WEB-INF/jsp/webdb/llocRolForm.jsp", extendsTile=Tab.MENU_WEBDB,
      type=TileType.WEBDB_FORM , attributes={ @TileAttribute(name="titol", value="llocRol.llocRol")})
@Tile(name="llocRolListWebDB", contentJsp="/WEB-INF/jsp/webdb/llocRolList.jsp", extendsTile=Tab.MENU_WEBDB,
       type=TileType.WEBDB_LIST, attributes={ @TileAttribute(name="titol", value="llocRol.llocRol") })
public class LlocRolController
    extends es.caib.rfhab.back.controller.RFHabBaseController<LlocRol, java.lang.Long> implements LlocRolFields {

  @EJB(mappedName = es.caib.rfhab.ejb.LlocRolService.JNDI_NAME)
  protected es.caib.rfhab.ejb.LlocRolService llocRolEjb;

  @Autowired
  private LlocRolWebValidator llocRolWebValidator;

  @Autowired
  protected LlocRolRefList llocRolRefList;

  // References 
  @Autowired
  protected LlocRefList llocRefList;

  // References 
  @Autowired
  protected RolRefList rolRefList;

  /**
   * Llistat de totes LlocRol
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    LlocRolFilterForm ff;
    ff = (LlocRolFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar LlocRol de forma paginada
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
    llistat(mav, request, getLlocRolFilterForm(pagina, mav, request));
    return mav;
  }

  public LlocRolFilterForm getLlocRolFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    LlocRolFilterForm llocRolFilterForm;
    llocRolFilterForm = (LlocRolFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(llocRolFilterForm == null) {
      llocRolFilterForm = new LlocRolFilterForm();
      llocRolFilterForm.setContexte(getContextWeb());
      llocRolFilterForm.setEntityNameCode(getEntityNameCode());
      llocRolFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      llocRolFilterForm.setNou(true);
    } else {
      llocRolFilterForm.setNou(false);
    }
    llocRolFilterForm.setPage(pagina == null ? 1 : pagina);
    return llocRolFilterForm;
  }

  /**
   * Segona i següent peticions per llistar LlocRol de forma paginada
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
      @ModelAttribute LlocRolFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getLlocRolFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de LlocRol de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<LlocRol> llistat(ModelAndView mav, HttpServletRequest request,
     LlocRolFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<LlocRol> llocRol = processarLlistat(llocRolEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("llocRolItems", llocRol);

    mav.addObject("llocRolFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, llocRol, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, llocRol);

    return llocRol;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(LlocRolFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<LlocRol> list, List<GroupByItem> groupItems) throws I18NException {
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

    // Field rolID
    {
      _listSKV = getReferenceListForRolID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfRolForRolID(_tmp);
      if (filterForm.getGroupByFields().contains(ROLID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, ROLID, false);
      };
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    LlocRolFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<LlocRol> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_LLOCROL_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(LLOCID, filterForm.getMapOfLlocForLlocID());
    __mapping.put(ROLID, filterForm.getMapOfRolForRolID());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou LlocRol
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearLlocRolGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    LlocRolForm llocRolForm = getLlocRolForm(null, false, request, mav);
    mav.addObject("llocRolForm" ,llocRolForm);
    fillReferencesForForm(llocRolForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public LlocRolForm getLlocRolForm(LlocRolJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    LlocRolForm llocRolForm;
    if(_jpa == null) {
      llocRolForm = new LlocRolForm(new LlocRolJPA(), true);
    } else {
      llocRolForm = new LlocRolForm(_jpa, false);
      llocRolForm.setView(__isView);
    }
    llocRolForm.setContexte(getContextWeb());
    llocRolForm.setEntityNameCode(getEntityNameCode());
    llocRolForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return llocRolForm;
  }

  public void fillReferencesForForm(LlocRolForm llocRolForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (llocRolForm.getListOfLlocForLlocID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForLlocID(request, mav, llocRolForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      llocRolForm.setListOfLlocForLlocID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (llocRolForm.getListOfRolForRolID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForRolID(request, mav, llocRolForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      llocRolForm.setListOfRolForRolID(_listSKV);
    }
    
  }

  /**
   * Guardar un nou LlocRol
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearLlocRolPost(@ModelAttribute LlocRolForm llocRolForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    LlocRolJPA llocRol = llocRolForm.getLlocRol();

    try {
      preValidate(request, llocRolForm, result);
      getWebValidator().validate(llocRolForm, result);
      postValidate(request,llocRolForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        llocRol = create(request, llocRol);
        createMessageSuccess(request, "success.creation", llocRol.getLlocRolID());
        llocRolForm.setLlocRol(llocRol);
        return getRedirectWhenCreated(request, llocRolForm);
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

  @RequestMapping(value = "/view/{llocRolID}", method = RequestMethod.GET)
  public ModelAndView veureLlocRolGet(@PathVariable("llocRolID") java.lang.Long llocRolID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewLlocRolGet(llocRolID,
        request, response, true);
  }


  protected ModelAndView editAndViewLlocRolGet(@PathVariable("llocRolID") java.lang.Long llocRolID,
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
    LlocRolJPA llocRol = findByPrimaryKey(request, llocRolID);

    if (llocRol == null) {
      createMessageWarning(request, "error.notfound", llocRolID);
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      LlocRolForm llocRolForm = getLlocRolForm(llocRol, __isView, request, mav);
      llocRolForm.setView(__isView);
      if(__isView) {
        llocRolForm.setAllFieldsReadOnly(ALL_LLOCROL_FIELDS);
        llocRolForm.setSaveButtonVisible(false);
        llocRolForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(llocRolForm, request, mav);
      mav.addObject("llocRolForm", llocRolForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un LlocRol existent
   */
  @RequestMapping(value = "/{llocRolID}/edit", method = RequestMethod.GET)
  public ModelAndView editarLlocRolGet(@PathVariable("llocRolID") java.lang.Long llocRolID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewLlocRolGet(llocRolID,
        request, response, false);
  }



  /**
   * Editar un LlocRol existent
   */
  @RequestMapping(value = "/{llocRolID}/edit", method = RequestMethod.POST)
  public String editarLlocRolPost(@ModelAttribute LlocRolForm llocRolForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    LlocRolJPA llocRol = llocRolForm.getLlocRol();

    try {
      preValidate(request, llocRolForm, result);
      getWebValidator().validate(llocRolForm, result);
      postValidate(request, llocRolForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        llocRol = update(request, llocRol);
        createMessageSuccess(request, "success.modification", llocRol.getLlocRolID());
        status.setComplete();
        return getRedirectWhenModified(request, llocRolForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          llocRol.getLlocRolID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, llocRolForm, __e);
    }

  }


  /**
   * Eliminar un LlocRol existent
   */
  @RequestMapping(value = "/{llocRolID}/delete")
  public String eliminarLlocRol(@PathVariable("llocRolID") java.lang.Long llocRolID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      LlocRol llocRol = this.findByPrimaryKey(request, llocRolID);
      if (llocRol == null) {
        String __msg = createMessageError(request, "error.notfound", llocRolID);
        return getRedirectWhenDelete(request, llocRolID, new Exception(__msg));
      } else {
        delete(request, llocRol);
        createMessageSuccess(request, "success.deleted", llocRolID);
        return getRedirectWhenDelete(request, llocRolID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", llocRolID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, llocRolID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute LlocRolFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarLlocRol(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __llocRolID, Throwable e) {
    java.lang.Long llocRolID = (java.lang.Long)__llocRolID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (llocRolID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(llocRolID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "llocRol.llocRol";
  }

  public String getEntityNameCodePlural() {
    return "llocRol.llocRol.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("llocRol.llocRolID");
  }

  @InitBinder("llocRolFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("llocRolForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "llocRol.llocRolID");
  }

  public LlocRolWebValidator getWebValidator() {
    return llocRolWebValidator;
  }


  public void setWebValidator(LlocRolWebValidator __val) {
    if (__val != null) {
      this.llocRolWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de LlocRol
   */
  @RequestMapping(value = "/{llocRolID}/cancel")
  public String cancelLlocRol(@PathVariable("llocRolID") java.lang.Long llocRolID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, llocRolID);
  }

  /**
   * Entra aqui al pitjar el boto cancel en el la creació de LlocRol
   */
  @RequestMapping(value = "/cancel")
  public String cancelLlocRol(HttpServletRequest request,HttpServletResponse response) {
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
       ModelAndView mav, LlocRolForm llocRolForm, Where where)  throws I18NException {
    if (llocRolForm.isHiddenField(LLOCID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (llocRolForm.isReadOnlyField(LLOCID)) {
      _where = LlocFields.LLOCID.equal(llocRolForm.getLlocRol().getLlocID());
    }
    return getReferenceListForLlocID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForLlocID(HttpServletRequest request,
       ModelAndView mav, LlocRolFilterForm llocRolFilterForm,
       List<LlocRol> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (llocRolFilterForm.isHiddenField(LLOCID)
       && !llocRolFilterForm.isGroupByField(LLOCID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(LLOCID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (LlocRol _item : list) {
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


  public List<StringKeyValue> getReferenceListForRolID(HttpServletRequest request,
       ModelAndView mav, LlocRolForm llocRolForm, Where where)  throws I18NException {
    if (llocRolForm.isHiddenField(ROLID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (llocRolForm.isReadOnlyField(ROLID)) {
      _where = RolFields.ROLID.equal(llocRolForm.getLlocRol().getRolID());
    }
    return getReferenceListForRolID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForRolID(HttpServletRequest request,
       ModelAndView mav, LlocRolFilterForm llocRolFilterForm,
       List<LlocRol> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (llocRolFilterForm.isHiddenField(ROLID)
       && !llocRolFilterForm.isGroupByField(ROLID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(ROLID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (LlocRol _item : list) {
        _pkList.add(_item.getRolID());
        }
        _w = RolFields.ROLID.in(_pkList);
      }
    return getReferenceListForRolID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForRolID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return rolRefList.getReferenceList(RolFields.ROLID, where );
  }


  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public void preValidate(HttpServletRequest request,LlocRolForm llocRolForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,LlocRolForm llocRolForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, LlocRolFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, LlocRolFilterForm filterForm,  List<LlocRol> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, LlocRolForm llocRolForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, LlocRolForm llocRolForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long llocRolID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long llocRolID) {
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
    return "llocRolFormWebDB";
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
        return "llocRolListWebDB";
    }

  public String getSessionAttributeFilterForm() {
    return "LlocRol_FilterForm_" + this.getClass().getName();
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public LlocRolJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long llocRolID) throws I18NException {
    return (LlocRolJPA) llocRolEjb.findByPrimaryKey(llocRolID);
  }


  public LlocRolJPA create(HttpServletRequest request, LlocRolJPA llocRol)
    throws I18NException, I18NValidationException {
    return (LlocRolJPA) llocRolEjb.create(llocRol);
  }


  public LlocRolJPA update(HttpServletRequest request, LlocRolJPA llocRol)
    throws I18NException, I18NValidationException {
    return (LlocRolJPA) llocRolEjb.update(llocRol);
  }


  public void delete(HttpServletRequest request, LlocRol llocRol) throws I18NException {
    llocRolEjb.delete(llocRol);
  }

} // Final de Classe

