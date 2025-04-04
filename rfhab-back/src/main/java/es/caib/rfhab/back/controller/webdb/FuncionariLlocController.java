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
import es.caib.rfhab.back.form.webdb.FuncionariLlocForm;

import es.caib.rfhab.back.validator.webdb.FuncionariLlocWebValidator;

import es.caib.rfhab.persistence.FuncionariLlocJPA;
import es.caib.rfhab.model.entity.FuncionariLloc;
import es.caib.rfhab.model.fields.*;
import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.fundaciobit.genapp.common.web.tiles.Tile;
import org.fundaciobit.genapp.common.web.tiles.TileAttribute;
import org.fundaciobit.genapp.common.web.tiles.TileType;
import es.caib.rfhab.back.utils.Tab;

/**
 * Controller per gestionar un FuncionariLloc
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@MenuOption(labelCode="funcionariLloc.funcionariLloc.plural", order=50, group=Tab.MENU_WEBDB)
@Controller
@RequestMapping(value = "/webdb/funcionariLloc")
@SessionAttributes(types = { FuncionariLlocForm.class, FuncionariLlocFilterForm.class })
@Tile(name="funcionariLlocFormWebDB", contentJsp="/WEB-INF/jsp/webdb/funcionariLlocForm.jsp", extendsTile=Tab.MENU_WEBDB,
      type=TileType.WEBDB_FORM , attributes={ @TileAttribute(name="titol", value="funcionariLloc.funcionariLloc")})
@Tile(name="funcionariLlocListWebDB", contentJsp="/WEB-INF/jsp/webdb/funcionariLlocList.jsp", extendsTile=Tab.MENU_WEBDB,
       type=TileType.WEBDB_LIST, attributes={ @TileAttribute(name="titol", value="funcionariLloc.funcionariLloc") })
public class FuncionariLlocController
    extends es.caib.rfhab.back.controller.RFHabBaseController<FuncionariLloc, java.lang.Long> implements FuncionariLlocFields {

  @EJB(mappedName = es.caib.rfhab.ejb.FuncionariLlocService.JNDI_NAME)
  protected es.caib.rfhab.ejb.FuncionariLlocService funcionariLlocEjb;

  @Autowired
  private FuncionariLlocWebValidator funcionariLlocWebValidator;

  @Autowired
  protected FuncionariLlocRefList funcionariLlocRefList;

  // References 
  @Autowired
  protected LlocRefList llocRefList;

  // References 
  @Autowired
  protected FuncionariRefList funcionariRefList;

  /**
   * Llistat de totes FuncionariLloc
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    FuncionariLlocFilterForm ff;
    ff = (FuncionariLlocFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar FuncionariLloc de forma paginada
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
    llistat(mav, request, getFuncionariLlocFilterForm(pagina, mav, request));
    return mav;
  }

  public FuncionariLlocFilterForm getFuncionariLlocFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    FuncionariLlocFilterForm funcionariLlocFilterForm;
    funcionariLlocFilterForm = (FuncionariLlocFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(funcionariLlocFilterForm == null) {
      funcionariLlocFilterForm = new FuncionariLlocFilterForm();
      funcionariLlocFilterForm.setContexte(getContextWeb());
      funcionariLlocFilterForm.setEntityNameCode(getEntityNameCode());
      funcionariLlocFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      funcionariLlocFilterForm.setNou(true);
    } else {
      funcionariLlocFilterForm.setNou(false);
    }
    funcionariLlocFilterForm.setPage(pagina == null ? 1 : pagina);
    return funcionariLlocFilterForm;
  }

  /**
   * Segona i següent peticions per llistar FuncionariLloc de forma paginada
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
      @ModelAttribute FuncionariLlocFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getFuncionariLlocFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de FuncionariLloc de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<FuncionariLloc> llistat(ModelAndView mav, HttpServletRequest request,
     FuncionariLlocFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<FuncionariLloc> funcionariLloc = processarLlistat(funcionariLlocEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("funcionariLlocItems", funcionariLloc);

    mav.addObject("funcionariLlocFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, funcionariLloc, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, funcionariLloc);

    return funcionariLloc;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(FuncionariLlocFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<FuncionariLloc> list, List<GroupByItem> groupItems) throws I18NException {
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
    FuncionariLlocFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<FuncionariLloc> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_FUNCIONARILLOC_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(LLOCID, filterForm.getMapOfLlocForLlocID());
    __mapping.put(FUNCIONARIID, filterForm.getMapOfFuncionariForFuncionariID());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou FuncionariLloc
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearFuncionariLlocGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    FuncionariLlocForm funcionariLlocForm = getFuncionariLlocForm(null, false, request, mav);
    mav.addObject("funcionariLlocForm" ,funcionariLlocForm);
    fillReferencesForForm(funcionariLlocForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public FuncionariLlocForm getFuncionariLlocForm(FuncionariLlocJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    FuncionariLlocForm funcionariLlocForm;
    if(_jpa == null) {
      funcionariLlocForm = new FuncionariLlocForm(new FuncionariLlocJPA(), true);
    } else {
      funcionariLlocForm = new FuncionariLlocForm(_jpa, false);
      funcionariLlocForm.setView(__isView);
    }
    funcionariLlocForm.setContexte(getContextWeb());
    funcionariLlocForm.setEntityNameCode(getEntityNameCode());
    funcionariLlocForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return funcionariLlocForm;
  }

  public void fillReferencesForForm(FuncionariLlocForm funcionariLlocForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (funcionariLlocForm.getListOfLlocForLlocID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForLlocID(request, mav, funcionariLlocForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      funcionariLlocForm.setListOfLlocForLlocID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (funcionariLlocForm.getListOfFuncionariForFuncionariID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForFuncionariID(request, mav, funcionariLlocForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      funcionariLlocForm.setListOfFuncionariForFuncionariID(_listSKV);
    }
    
  }

  /**
   * Guardar un nou FuncionariLloc
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearFuncionariLlocPost(@ModelAttribute FuncionariLlocForm funcionariLlocForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    FuncionariLlocJPA funcionariLloc = funcionariLlocForm.getFuncionariLloc();

    try {
      preValidate(request, funcionariLlocForm, result);
      getWebValidator().validate(funcionariLlocForm, result);
      postValidate(request,funcionariLlocForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        funcionariLloc = create(request, funcionariLloc);
        createMessageSuccess(request, "success.creation", funcionariLloc.getFuncionarillocID());
        funcionariLlocForm.setFuncionariLloc(funcionariLloc);
        return getRedirectWhenCreated(request, funcionariLlocForm);
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

  @RequestMapping(value = "/view/{funcionarillocID}", method = RequestMethod.GET)
  public ModelAndView veureFuncionariLlocGet(@PathVariable("funcionarillocID") java.lang.Long funcionarillocID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewFuncionariLlocGet(funcionarillocID,
        request, response, true);
  }


  protected ModelAndView editAndViewFuncionariLlocGet(@PathVariable("funcionarillocID") java.lang.Long funcionarillocID,
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
    FuncionariLlocJPA funcionariLloc = findByPrimaryKey(request, funcionarillocID);

    if (funcionariLloc == null) {
      createMessageWarning(request, "error.notfound", funcionarillocID);
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      FuncionariLlocForm funcionariLlocForm = getFuncionariLlocForm(funcionariLloc, __isView, request, mav);
      funcionariLlocForm.setView(__isView);
      if(__isView) {
        funcionariLlocForm.setAllFieldsReadOnly(ALL_FUNCIONARILLOC_FIELDS);
        funcionariLlocForm.setSaveButtonVisible(false);
        funcionariLlocForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(funcionariLlocForm, request, mav);
      mav.addObject("funcionariLlocForm", funcionariLlocForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un FuncionariLloc existent
   */
  @RequestMapping(value = "/{funcionarillocID}/edit", method = RequestMethod.GET)
  public ModelAndView editarFuncionariLlocGet(@PathVariable("funcionarillocID") java.lang.Long funcionarillocID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewFuncionariLlocGet(funcionarillocID,
        request, response, false);
  }



  /**
   * Editar un FuncionariLloc existent
   */
  @RequestMapping(value = "/{funcionarillocID}/edit", method = RequestMethod.POST)
  public String editarFuncionariLlocPost(@ModelAttribute FuncionariLlocForm funcionariLlocForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    FuncionariLlocJPA funcionariLloc = funcionariLlocForm.getFuncionariLloc();

    try {
      preValidate(request, funcionariLlocForm, result);
      getWebValidator().validate(funcionariLlocForm, result);
      postValidate(request, funcionariLlocForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        funcionariLloc = update(request, funcionariLloc);
        createMessageSuccess(request, "success.modification", funcionariLloc.getFuncionarillocID());
        status.setComplete();
        return getRedirectWhenModified(request, funcionariLlocForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          funcionariLloc.getFuncionarillocID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, funcionariLlocForm, __e);
    }

  }


  /**
   * Eliminar un FuncionariLloc existent
   */
  @RequestMapping(value = "/{funcionarillocID}/delete")
  public String eliminarFuncionariLloc(@PathVariable("funcionarillocID") java.lang.Long funcionarillocID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      FuncionariLloc funcionariLloc = this.findByPrimaryKey(request, funcionarillocID);
      if (funcionariLloc == null) {
        String __msg = createMessageError(request, "error.notfound", funcionarillocID);
        return getRedirectWhenDelete(request, funcionarillocID, new Exception(__msg));
      } else {
        delete(request, funcionariLloc);
        createMessageSuccess(request, "success.deleted", funcionarillocID);
        return getRedirectWhenDelete(request, funcionarillocID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", funcionarillocID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, funcionarillocID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute FuncionariLlocFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarFuncionariLloc(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __funcionarillocID, Throwable e) {
    java.lang.Long funcionarillocID = (java.lang.Long)__funcionarillocID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (funcionarillocID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(funcionarillocID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "funcionariLloc.funcionariLloc";
  }

  public String getEntityNameCodePlural() {
    return "funcionariLloc.funcionariLloc.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("funcionariLloc.funcionarillocID");
  }

  @InitBinder("funcionariLlocFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("funcionariLlocForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "funcionariLloc.funcionarillocID");
  }

  public FuncionariLlocWebValidator getWebValidator() {
    return funcionariLlocWebValidator;
  }


  public void setWebValidator(FuncionariLlocWebValidator __val) {
    if (__val != null) {
      this.funcionariLlocWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de FuncionariLloc
   */
  @RequestMapping(value = "/{funcionarillocID}/cancel")
  public String cancelFuncionariLloc(@PathVariable("funcionarillocID") java.lang.Long funcionarillocID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, funcionarillocID);
  }

  /**
   * Entra aqui al pitjar el boto cancel en el la creació de FuncionariLloc
   */
  @RequestMapping(value = "/cancel")
  public String cancelFuncionariLloc(HttpServletRequest request,HttpServletResponse response) {
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
       ModelAndView mav, FuncionariLlocForm funcionariLlocForm, Where where)  throws I18NException {
    if (funcionariLlocForm.isHiddenField(LLOCID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (funcionariLlocForm.isReadOnlyField(LLOCID)) {
      _where = LlocFields.LLOCID.equal(funcionariLlocForm.getFuncionariLloc().getLlocID());
    }
    return getReferenceListForLlocID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForLlocID(HttpServletRequest request,
       ModelAndView mav, FuncionariLlocFilterForm funcionariLlocFilterForm,
       List<FuncionariLloc> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (funcionariLlocFilterForm.isHiddenField(LLOCID)
       && !funcionariLlocFilterForm.isGroupByField(LLOCID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(LLOCID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (FuncionariLloc _item : list) {
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
       ModelAndView mav, FuncionariLlocForm funcionariLlocForm, Where where)  throws I18NException {
    if (funcionariLlocForm.isHiddenField(FUNCIONARIID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (funcionariLlocForm.isReadOnlyField(FUNCIONARIID)) {
      _where = FuncionariFields.FUNCIONARIID.equal(funcionariLlocForm.getFuncionariLloc().getFuncionariID());
    }
    return getReferenceListForFuncionariID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForFuncionariID(HttpServletRequest request,
       ModelAndView mav, FuncionariLlocFilterForm funcionariLlocFilterForm,
       List<FuncionariLloc> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (funcionariLlocFilterForm.isHiddenField(FUNCIONARIID)
       && !funcionariLlocFilterForm.isGroupByField(FUNCIONARIID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(FUNCIONARIID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (FuncionariLloc _item : list) {
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

  public void preValidate(HttpServletRequest request,FuncionariLlocForm funcionariLlocForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,FuncionariLlocForm funcionariLlocForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, FuncionariLlocFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, FuncionariLlocFilterForm filterForm,  List<FuncionariLloc> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, FuncionariLlocForm funcionariLlocForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, FuncionariLlocForm funcionariLlocForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long funcionarillocID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long funcionarillocID) {
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
    return "funcionariLlocFormWebDB";
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
        return "funcionariLlocListWebDB";
    }

  public String getSessionAttributeFilterForm() {
    return "FuncionariLloc_FilterForm_" + this.getClass().getName();
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public FuncionariLlocJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long funcionarillocID) throws I18NException {
    return (FuncionariLlocJPA) funcionariLlocEjb.findByPrimaryKey(funcionarillocID);
  }


  public FuncionariLlocJPA create(HttpServletRequest request, FuncionariLlocJPA funcionariLloc)
    throws I18NException, I18NValidationException {
    return (FuncionariLlocJPA) funcionariLlocEjb.create(funcionariLloc);
  }


  public FuncionariLlocJPA update(HttpServletRequest request, FuncionariLlocJPA funcionariLloc)
    throws I18NException, I18NValidationException {
    return (FuncionariLlocJPA) funcionariLlocEjb.update(funcionariLloc);
  }


  public void delete(HttpServletRequest request, FuncionariLloc funcionariLloc) throws I18NException {
    funcionariLlocEjb.delete(funcionariLloc);
  }

} // Final de Classe

