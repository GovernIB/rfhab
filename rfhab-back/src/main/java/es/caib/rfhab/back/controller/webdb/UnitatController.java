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

import es.caib.rfhab.back.form.webdb.*;
import es.caib.rfhab.back.form.webdb.UnitatForm;

import es.caib.rfhab.back.validator.webdb.UnitatWebValidator;

import es.caib.rfhab.persistence.UnitatJPA;
import es.caib.rfhab.model.entity.Unitat;
import es.caib.rfhab.model.fields.*;
import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;

/**
 * Controller per gestionar un Unitat
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@MenuOption(labelCode="unitat.unitat.plural", order=160, group="WEBDB")
@Controller
@RequestMapping(value = "/webdb/unitat")
@SessionAttributes(types = { UnitatForm.class, UnitatFilterForm.class })
public class UnitatController
    extends es.caib.rfhab.back.controller.RFHabBaseController<Unitat, java.lang.Long> implements UnitatFields {

  @EJB(mappedName = es.caib.rfhab.ejb.UnitatService.JNDI_NAME)
  protected es.caib.rfhab.ejb.UnitatService unitatEjb;

  @Autowired
  private UnitatWebValidator unitatWebValidator;

  @Autowired
  protected UnitatRefList unitatRefList;

  /**
   * Llistat de totes Unitat
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    UnitatFilterForm ff;
    ff = (UnitatFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar Unitat de forma paginada
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
    llistat(mav, request, getUnitatFilterForm(pagina, mav, request));
    return mav;
  }

  public UnitatFilterForm getUnitatFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    UnitatFilterForm unitatFilterForm;
    unitatFilterForm = (UnitatFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(unitatFilterForm == null) {
      unitatFilterForm = new UnitatFilterForm();
      unitatFilterForm.setContexte(getContextWeb());
      unitatFilterForm.setEntityNameCode(getEntityNameCode());
      unitatFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      unitatFilterForm.setNou(true);
    } else {
      unitatFilterForm.setNou(false);
    }
    unitatFilterForm.setPage(pagina == null ? 1 : pagina);
    return unitatFilterForm;
  }

  /**
   * Segona i següent peticions per llistar Unitat de forma paginada
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
      @ModelAttribute UnitatFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getUnitatFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de Unitat de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<Unitat> llistat(ModelAndView mav, HttpServletRequest request,
     UnitatFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<Unitat> unitat = processarLlistat(unitatEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("unitatItems", unitat);

    mav.addObject("unitatFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, unitat, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, unitat);

    return unitat;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(UnitatFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<Unitat> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

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
    UnitatFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<Unitat> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_UNITAT_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(ESTAT, filterForm.getMapOfValuesForEstat());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou Unitat
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearUnitatGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    UnitatForm unitatForm = getUnitatForm(null, false, request, mav);
    mav.addObject("unitatForm" ,unitatForm);
    fillReferencesForForm(unitatForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public UnitatForm getUnitatForm(UnitatJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    UnitatForm unitatForm;
    if(_jpa == null) {
      unitatForm = new UnitatForm(new UnitatJPA(), true);
    } else {
      unitatForm = new UnitatForm(_jpa, false);
      unitatForm.setView(__isView);
    }
    unitatForm.setContexte(getContextWeb());
    unitatForm.setEntityNameCode(getEntityNameCode());
    unitatForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return unitatForm;
  }

  public void fillReferencesForForm(UnitatForm unitatForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (unitatForm.getListOfValuesForEstat() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForEstat(request, mav, unitatForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      unitatForm.setListOfValuesForEstat(_listSKV);
    }
    
  }

  /**
   * Guardar un nou Unitat
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearUnitatPost(@ModelAttribute UnitatForm unitatForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    UnitatJPA unitat = unitatForm.getUnitat();

    try {
      preValidate(request, unitatForm, result);
      getWebValidator().validate(unitatForm, result);
      postValidate(request,unitatForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        unitat = create(request, unitat);
        createMessageSuccess(request, "success.creation", unitat.getUnitatID());
        unitatForm.setUnitat(unitat);
        return getRedirectWhenCreated(request, unitatForm);
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

  @RequestMapping(value = "/view/{unitatID}", method = RequestMethod.GET)
  public ModelAndView veureUnitatGet(@PathVariable("unitatID") java.lang.Long unitatID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewUnitatGet(unitatID,
        request, response, true);
  }


  protected ModelAndView editAndViewUnitatGet(@PathVariable("unitatID") java.lang.Long unitatID,
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
    UnitatJPA unitat = findByPrimaryKey(request, unitatID);

    if (unitat == null) {
      createMessageWarning(request, "error.notfound", unitatID);
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      UnitatForm unitatForm = getUnitatForm(unitat, __isView, request, mav);
      unitatForm.setView(__isView);
      if(__isView) {
        unitatForm.setAllFieldsReadOnly(ALL_UNITAT_FIELDS);
        unitatForm.setSaveButtonVisible(false);
        unitatForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(unitatForm, request, mav);
      mav.addObject("unitatForm", unitatForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un Unitat existent
   */
  @RequestMapping(value = "/{unitatID}/edit", method = RequestMethod.GET)
  public ModelAndView editarUnitatGet(@PathVariable("unitatID") java.lang.Long unitatID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewUnitatGet(unitatID,
        request, response, false);
  }



  /**
   * Editar un Unitat existent
   */
  @RequestMapping(value = "/{unitatID}/edit", method = RequestMethod.POST)
  public String editarUnitatPost(@ModelAttribute UnitatForm unitatForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    UnitatJPA unitat = unitatForm.getUnitat();

    try {
      preValidate(request, unitatForm, result);
      getWebValidator().validate(unitatForm, result);
      postValidate(request, unitatForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        unitat = update(request, unitat);
        createMessageSuccess(request, "success.modification", unitat.getUnitatID());
        status.setComplete();
        return getRedirectWhenModified(request, unitatForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          unitat.getUnitatID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, unitatForm, __e);
    }

  }


  /**
   * Eliminar un Unitat existent
   */
  @RequestMapping(value = "/{unitatID}/delete")
  public String eliminarUnitat(@PathVariable("unitatID") java.lang.Long unitatID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      Unitat unitat = this.findByPrimaryKey(request, unitatID);
      if (unitat == null) {
        String __msg = createMessageError(request, "error.notfound", unitatID);
        return getRedirectWhenDelete(request, unitatID, new Exception(__msg));
      } else {
        delete(request, unitat);
        createMessageSuccess(request, "success.deleted", unitatID);
        return getRedirectWhenDelete(request, unitatID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", unitatID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, unitatID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute UnitatFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarUnitat(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __unitatID, Throwable e) {
    java.lang.Long unitatID = (java.lang.Long)__unitatID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (unitatID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(unitatID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "unitat.unitat";
  }

  public String getEntityNameCodePlural() {
    return "unitat.unitat.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("unitat.unitatID");
  }

  @InitBinder("unitatFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("unitatForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "unitat.unitatID");
  }

  public UnitatWebValidator getWebValidator() {
    return unitatWebValidator;
  }


  public void setWebValidator(UnitatWebValidator __val) {
    if (__val != null) {
      this.unitatWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de Unitat
   */
  @RequestMapping(value = "/{unitatID}/cancel")
  public String cancelUnitat(@PathVariable("unitatID") java.lang.Long unitatID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, unitatID);
  }

  /**
   * Entra aqui al pitjar el boto cancel en el la creació de Unitat
   */
  @RequestMapping(value = "/cancel")
  public String cancelUnitat(HttpServletRequest request,HttpServletResponse response) {
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


  public List<StringKeyValue> getReferenceListForEstat(HttpServletRequest request,
       ModelAndView mav, UnitatForm unitatForm, Where where)  throws I18NException {
    if (unitatForm.isHiddenField(ESTAT)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForEstat(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForEstat(HttpServletRequest request,
       ModelAndView mav, UnitatFilterForm unitatFilterForm,
       List<Unitat> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (unitatFilterForm.isHiddenField(ESTAT)
       && !unitatFilterForm.isGroupByField(ESTAT)
       && !unitatFilterForm.isFilterByField(ESTAT)) {
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
    return __tmp;
  }


  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public void preValidate(HttpServletRequest request,UnitatForm unitatForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,UnitatForm unitatForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, UnitatFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, UnitatFilterForm filterForm,  List<Unitat> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, UnitatForm unitatForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, UnitatForm unitatForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long unitatID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long unitatID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "unitatFormWebDB";
  }

  public String getTileList() {
    return "unitatListWebDB";
  }

  public String getSessionAttributeFilterForm() {
    return "Unitat_FilterForm_" + this.getClass().getName();
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public UnitatJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long unitatID) throws I18NException {
    return (UnitatJPA) unitatEjb.findByPrimaryKey(unitatID);
  }


  public UnitatJPA create(HttpServletRequest request, UnitatJPA unitat)
    throws I18NException, I18NValidationException {
    return (UnitatJPA) unitatEjb.create(unitat);
  }


  public UnitatJPA update(HttpServletRequest request, UnitatJPA unitat)
    throws I18NException, I18NValidationException {
    return (UnitatJPA) unitatEjb.update(unitat);
  }


  public void delete(HttpServletRequest request, Unitat unitat) throws I18NException {
    unitatEjb.delete(unitat);
  }

} // Final de Classe

