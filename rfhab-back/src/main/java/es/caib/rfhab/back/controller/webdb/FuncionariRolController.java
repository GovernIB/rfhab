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
import org.springframework.web.servlet.view.RedirectView;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import es.caib.rfhab.back.form.webdb.*;
import es.caib.rfhab.back.form.webdb.FuncionariRolForm;

import es.caib.rfhab.back.validator.webdb.FuncionariRolWebValidator;

import es.caib.rfhab.persistence.FuncionariRolJPA;
import es.caib.rfhab.model.entity.FuncionariRol;
import es.caib.rfhab.model.fields.*;

/**
 * Controller per gestionar un FuncionariRol
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/funcionariRol")
@SessionAttributes(types = { FuncionariRolForm.class, FuncionariRolFilterForm.class })
public class FuncionariRolController
    extends es.caib.rfhab.back.controller.RFHabBaseController<FuncionariRol, java.lang.Long> implements FuncionariRolFields {

  @EJB(mappedName = es.caib.rfhab.ejb.FuncionariRolService.JNDI_NAME)
  protected es.caib.rfhab.ejb.FuncionariRolService funcionariRolEjb;

  @Autowired
  private FuncionariRolWebValidator funcionariRolWebValidator;

  @Autowired
  protected FuncionariRolRefList funcionariRolRefList;

  // References 
  @Autowired
  protected FuncionariRefList funcionariRefList;

  // References 
  @Autowired
  protected RolRefList rolRefList;

  /**
   * Llistat de totes FuncionariRol
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    FuncionariRolFilterForm ff;
    ff = (FuncionariRolFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar FuncionariRol de forma paginada
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
    llistat(mav, request, getFuncionariRolFilterForm(pagina, mav, request));
    return mav;
  }

  public FuncionariRolFilterForm getFuncionariRolFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    FuncionariRolFilterForm funcionariRolFilterForm;
    funcionariRolFilterForm = (FuncionariRolFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(funcionariRolFilterForm == null) {
      funcionariRolFilterForm = new FuncionariRolFilterForm();
      funcionariRolFilterForm.setContexte(getContextWeb());
      funcionariRolFilterForm.setEntityNameCode(getEntityNameCode());
      funcionariRolFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      funcionariRolFilterForm.setNou(true);
    } else {
      funcionariRolFilterForm.setNou(false);
    }
    funcionariRolFilterForm.setPage(pagina == null ? 1 : pagina);
    return funcionariRolFilterForm;
  }

  /**
   * Segona i següent peticions per llistar FuncionariRol de forma paginada
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
      @ModelAttribute FuncionariRolFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getFuncionariRolFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de FuncionariRol de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<FuncionariRol> llistat(ModelAndView mav, HttpServletRequest request,
     FuncionariRolFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<FuncionariRol> funcionariRol = processarLlistat(funcionariRolEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("funcionariRolItems", funcionariRol);

    mav.addObject("funcionariRolFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, funcionariRol, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, funcionariRol);

    return funcionariRol;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(FuncionariRolFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<FuncionariRol> list, List<GroupByItem> groupItems) throws I18NException {
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
    FuncionariRolFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<FuncionariRol> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_FUNCIONARIROL_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(FUNCIONARIID, filterForm.getMapOfFuncionariForFuncionariID());
    __mapping.put(ROLID, filterForm.getMapOfRolForRolID());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou FuncionariRol
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearFuncionariRolGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    FuncionariRolForm funcionariRolForm = getFuncionariRolForm(null, false, request, mav);
    mav.addObject("funcionariRolForm" ,funcionariRolForm);
    fillReferencesForForm(funcionariRolForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public FuncionariRolForm getFuncionariRolForm(FuncionariRolJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    FuncionariRolForm funcionariRolForm;
    if(_jpa == null) {
      funcionariRolForm = new FuncionariRolForm(new FuncionariRolJPA(), true);
    } else {
      funcionariRolForm = new FuncionariRolForm(_jpa, false);
      funcionariRolForm.setView(__isView);
    }
    funcionariRolForm.setContexte(getContextWeb());
    funcionariRolForm.setEntityNameCode(getEntityNameCode());
    funcionariRolForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return funcionariRolForm;
  }

  public void fillReferencesForForm(FuncionariRolForm funcionariRolForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (funcionariRolForm.getListOfFuncionariForFuncionariID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForFuncionariID(request, mav, funcionariRolForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      funcionariRolForm.setListOfFuncionariForFuncionariID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (funcionariRolForm.getListOfRolForRolID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForRolID(request, mav, funcionariRolForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      funcionariRolForm.setListOfRolForRolID(_listSKV);
    }
    
  }

  /**
   * Guardar un nou FuncionariRol
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearFuncionariRolPost(@ModelAttribute FuncionariRolForm funcionariRolForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    FuncionariRolJPA funcionariRol = funcionariRolForm.getFuncionariRol();

    try {
      preValidate(request, funcionariRolForm, result);
      getWebValidator().validate(funcionariRolForm, result);
      postValidate(request,funcionariRolForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        funcionariRol = create(request, funcionariRol);
        createMessageSuccess(request, "success.creation", funcionariRol.getFuncionariRolID());
        funcionariRolForm.setFuncionariRol(funcionariRol);
        return getRedirectWhenCreated(request, funcionariRolForm);
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

  @RequestMapping(value = "/view/{funcionariRolID}", method = RequestMethod.GET)
  public ModelAndView veureFuncionariRolGet(@PathVariable("funcionariRolID") java.lang.Long funcionariRolID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewFuncionariRolGet(funcionariRolID,
        request, response, true);
  }


  protected ModelAndView editAndViewFuncionariRolGet(@PathVariable("funcionariRolID") java.lang.Long funcionariRolID,
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
    FuncionariRolJPA funcionariRol = findByPrimaryKey(request, funcionariRolID);

    if (funcionariRol == null) {
      createMessageWarning(request, "error.notfound", funcionariRolID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, funcionariRolID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      FuncionariRolForm funcionariRolForm = getFuncionariRolForm(funcionariRol, __isView, request, mav);
      funcionariRolForm.setView(__isView);
      if(__isView) {
        funcionariRolForm.setAllFieldsReadOnly(ALL_FUNCIONARIROL_FIELDS);
        funcionariRolForm.setSaveButtonVisible(false);
        funcionariRolForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(funcionariRolForm, request, mav);
      mav.addObject("funcionariRolForm", funcionariRolForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un FuncionariRol existent
   */
  @RequestMapping(value = "/{funcionariRolID}/edit", method = RequestMethod.GET)
  public ModelAndView editarFuncionariRolGet(@PathVariable("funcionariRolID") java.lang.Long funcionariRolID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewFuncionariRolGet(funcionariRolID,
        request, response, false);
  }



  /**
   * Editar un FuncionariRol existent
   */
  @RequestMapping(value = "/{funcionariRolID}/edit", method = RequestMethod.POST)
  public String editarFuncionariRolPost(@ModelAttribute FuncionariRolForm funcionariRolForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    FuncionariRolJPA funcionariRol = funcionariRolForm.getFuncionariRol();

    try {
      preValidate(request, funcionariRolForm, result);
      getWebValidator().validate(funcionariRolForm, result);
      postValidate(request, funcionariRolForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        funcionariRol = update(request, funcionariRol);
        createMessageSuccess(request, "success.modification", funcionariRol.getFuncionariRolID());
        status.setComplete();
        return getRedirectWhenModified(request, funcionariRolForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          funcionariRol.getFuncionariRolID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, funcionariRolForm, __e);
    }

  }


  /**
   * Eliminar un FuncionariRol existent
   */
  @RequestMapping(value = "/{funcionariRolID}/delete")
  public String eliminarFuncionariRol(@PathVariable("funcionariRolID") java.lang.Long funcionariRolID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      FuncionariRol funcionariRol = this.findByPrimaryKey(request, funcionariRolID);
      if (funcionariRol == null) {
        String __msg = createMessageError(request, "error.notfound", funcionariRolID);
        return getRedirectWhenDelete(request, funcionariRolID, new Exception(__msg));
      } else {
        delete(request, funcionariRol);
        createMessageSuccess(request, "success.deleted", funcionariRolID);
        return getRedirectWhenDelete(request, funcionariRolID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", funcionariRolID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, funcionariRolID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute FuncionariRolFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarFuncionariRol(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __funcionariRolID, Throwable e) {
    java.lang.Long funcionariRolID = (java.lang.Long)__funcionariRolID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (funcionariRolID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(funcionariRolID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "funcionariRol.funcionariRol";
  }

  public String getEntityNameCodePlural() {
    return "funcionariRol.funcionariRol.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("funcionariRol.funcionariRolID");
  }

  @InitBinder("funcionariRolFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("funcionariRolForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "funcionariRol.funcionariRolID");
  }

  public FuncionariRolWebValidator getWebValidator() {
    return funcionariRolWebValidator;
  }


  public void setWebValidator(FuncionariRolWebValidator __val) {
    if (__val != null) {
      this.funcionariRolWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de FuncionariRol
   */
  @RequestMapping(value = "/{funcionariRolID}/cancel")
  public String cancelFuncionariRol(@PathVariable("funcionariRolID") java.lang.Long funcionariRolID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, funcionariRolID);
  }

  /**
   * Entra aqui al pitjar el boto cancel en el la creació de FuncionariRol
   */
  @RequestMapping(value = "/cancel")
  public String cancelFuncionariRol(HttpServletRequest request,HttpServletResponse response) {
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
       ModelAndView mav, FuncionariRolForm funcionariRolForm, Where where)  throws I18NException {
    if (funcionariRolForm.isHiddenField(FUNCIONARIID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (funcionariRolForm.isReadOnlyField(FUNCIONARIID)) {
      _where = FuncionariFields.FUNCIONARIID.equal(funcionariRolForm.getFuncionariRol().getFuncionariID());
    }
    return getReferenceListForFuncionariID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForFuncionariID(HttpServletRequest request,
       ModelAndView mav, FuncionariRolFilterForm funcionariRolFilterForm,
       List<FuncionariRol> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (funcionariRolFilterForm.isHiddenField(FUNCIONARIID)
       && !funcionariRolFilterForm.isGroupByField(FUNCIONARIID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(FUNCIONARIID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (FuncionariRol _item : list) {
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


  public List<StringKeyValue> getReferenceListForRolID(HttpServletRequest request,
       ModelAndView mav, FuncionariRolForm funcionariRolForm, Where where)  throws I18NException {
    if (funcionariRolForm.isHiddenField(ROLID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (funcionariRolForm.isReadOnlyField(ROLID)) {
      _where = RolFields.ROLID.equal(funcionariRolForm.getFuncionariRol().getRolID());
    }
    return getReferenceListForRolID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForRolID(HttpServletRequest request,
       ModelAndView mav, FuncionariRolFilterForm funcionariRolFilterForm,
       List<FuncionariRol> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (funcionariRolFilterForm.isHiddenField(ROLID)
       && !funcionariRolFilterForm.isGroupByField(ROLID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(ROLID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (FuncionariRol _item : list) {
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

  public void preValidate(HttpServletRequest request,FuncionariRolForm funcionariRolForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,FuncionariRolForm funcionariRolForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, FuncionariRolFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, FuncionariRolFilterForm filterForm,  List<FuncionariRol> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, FuncionariRolForm funcionariRolForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, FuncionariRolForm funcionariRolForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long funcionariRolID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long funcionariRolID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "funcionariRolFormWebDB";
  }

  public String getTileList() {
    return "funcionariRolListWebDB";
  }

  public String getSessionAttributeFilterForm() {
    return "FuncionariRol_FilterForm_" + this.getClass().getName();
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public FuncionariRolJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long funcionariRolID) throws I18NException {
    return (FuncionariRolJPA) funcionariRolEjb.findByPrimaryKey(funcionariRolID);
  }


  public FuncionariRolJPA create(HttpServletRequest request, FuncionariRolJPA funcionariRol)
    throws I18NException, I18NValidationException {
    return (FuncionariRolJPA) funcionariRolEjb.create(funcionariRol);
  }


  public FuncionariRolJPA update(HttpServletRequest request, FuncionariRolJPA funcionariRol)
    throws I18NException, I18NValidationException {
    return (FuncionariRolJPA) funcionariRolEjb.update(funcionariRol);
  }


  public void delete(HttpServletRequest request, FuncionariRol funcionariRol) throws I18NException {
    funcionariRolEjb.delete(funcionariRol);
  }

} // Final de Classe

