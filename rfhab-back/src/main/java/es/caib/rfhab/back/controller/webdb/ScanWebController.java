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
import es.caib.rfhab.back.form.webdb.ScanWebForm;

import es.caib.rfhab.back.validator.webdb.ScanWebWebValidator;

import es.caib.rfhab.model.entity.Fitxer;
import es.caib.rfhab.persistence.FitxerJPA;
import org.fundaciobit.genapp.common.web.controller.FilesFormManager;
import es.caib.rfhab.persistence.ScanWebJPA;
import es.caib.rfhab.model.entity.ScanWeb;
import es.caib.rfhab.model.fields.*;
import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;

/**
 * Controller per gestionar un ScanWeb
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@MenuOption(labelCode="scanWeb.scanWeb.plural", order=130, group="WEBDB")
@Controller
@RequestMapping(value = "/webdb/scanWeb")
@SessionAttributes(types = { ScanWebForm.class, ScanWebFilterForm.class })
public class ScanWebController
    extends es.caib.rfhab.back.controller.RFHabFilesBaseController<ScanWeb, java.lang.Long, ScanWebForm> implements ScanWebFields {

  @EJB(mappedName = es.caib.rfhab.ejb.ScanWebService.JNDI_NAME)
  protected es.caib.rfhab.ejb.ScanWebService scanWebEjb;

  @Autowired
  private ScanWebWebValidator scanWebWebValidator;

  @Autowired
  protected ScanWebRefList scanWebRefList;

  // References 
  @Autowired
  protected UsuariRefList usuariRefList;

  /**
   * Llistat de totes ScanWeb
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    ScanWebFilterForm ff;
    ff = (ScanWebFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar ScanWeb de forma paginada
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
    llistat(mav, request, getScanWebFilterForm(pagina, mav, request));
    return mav;
  }

  public ScanWebFilterForm getScanWebFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    ScanWebFilterForm scanWebFilterForm;
    scanWebFilterForm = (ScanWebFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(scanWebFilterForm == null) {
      scanWebFilterForm = new ScanWebFilterForm();
      scanWebFilterForm.setContexte(getContextWeb());
      scanWebFilterForm.setEntityNameCode(getEntityNameCode());
      scanWebFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      scanWebFilterForm.setNou(true);
    } else {
      scanWebFilterForm.setNou(false);
    }
    scanWebFilterForm.setPage(pagina == null ? 1 : pagina);
    return scanWebFilterForm;
  }

  /**
   * Segona i següent peticions per llistar ScanWeb de forma paginada
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
      @ModelAttribute ScanWebFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getScanWebFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de ScanWeb de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<ScanWeb> llistat(ModelAndView mav, HttpServletRequest request,
     ScanWebFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<ScanWeb> scanWeb = processarLlistat(scanWebEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("scanWebItems", scanWeb);

    mav.addObject("scanWebFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, scanWeb, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, scanWeb);

    return scanWeb;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(ScanWebFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<ScanWeb> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

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
    ScanWebFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<ScanWeb> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_SCANWEB_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(USUARIID, filterForm.getMapOfUsuariForUsuariID());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou ScanWeb
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearScanWebGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    ScanWebForm scanWebForm = getScanWebForm(null, false, request, mav);
    mav.addObject("scanWebForm" ,scanWebForm);
    fillReferencesForForm(scanWebForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public ScanWebForm getScanWebForm(ScanWebJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    ScanWebForm scanWebForm;
    if(_jpa == null) {
      scanWebForm = new ScanWebForm(new ScanWebJPA(), true);
    } else {
      scanWebForm = new ScanWebForm(_jpa, false);
      scanWebForm.setView(__isView);
    }
    scanWebForm.setContexte(getContextWeb());
    scanWebForm.setEntityNameCode(getEntityNameCode());
    scanWebForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return scanWebForm;
  }

  public void fillReferencesForForm(ScanWebForm scanWebForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (scanWebForm.getListOfUsuariForUsuariID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForUsuariID(request, mav, scanWebForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      scanWebForm.setListOfUsuariForUsuariID(_listSKV);
    }
    
  }

  /**
   * Guardar un nou ScanWeb
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearScanWebPost(@ModelAttribute ScanWebForm scanWebForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ScanWebJPA scanWeb = scanWebForm.getScanWeb();

    FilesFormManager<Fitxer> afm = getFilesFormManager(); // FILE

    try {
      this.setFilesFormToEntity(afm, scanWeb, scanWebForm); // FILE
      preValidate(request, scanWebForm, result);
      getWebValidator().validate(scanWebForm, result);
      postValidate(request,scanWebForm, result);

      if (result.hasErrors()) {
        afm.processErrorFilesWithoutThrowException(); // FILE
        result.reject("error.form");
        return getTileForm();
      } else {
        scanWeb = create(request, scanWeb);
        afm.postPersistFiles(); // FILE
        createMessageSuccess(request, "success.creation", scanWeb.getDigitalID());
        scanWebForm.setScanWeb(scanWeb);
        return getRedirectWhenCreated(request, scanWebForm);
      }
    } catch (Throwable __e) {
      afm.processErrorFilesWithoutThrowException(); // FILE
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.creation", null, __e);
      log.error(msg, __e);
      return getTileForm();
    }
  }

  @RequestMapping(value = "/view/{digitalID}", method = RequestMethod.GET)
  public ModelAndView veureScanWebGet(@PathVariable("digitalID") java.lang.Long digitalID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewScanWebGet(digitalID,
        request, response, true);
  }


  protected ModelAndView editAndViewScanWebGet(@PathVariable("digitalID") java.lang.Long digitalID,
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
    ScanWebJPA scanWeb = findByPrimaryKey(request, digitalID);

    if (scanWeb == null) {
      createMessageWarning(request, "error.notfound", digitalID);
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      ScanWebForm scanWebForm = getScanWebForm(scanWeb, __isView, request, mav);
      scanWebForm.setView(__isView);
      if(__isView) {
        scanWebForm.setAllFieldsReadOnly(ALL_SCANWEB_FIELDS);
        scanWebForm.setSaveButtonVisible(false);
        scanWebForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(scanWebForm, request, mav);
      mav.addObject("scanWebForm", scanWebForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un ScanWeb existent
   */
  @RequestMapping(value = "/{digitalID}/edit", method = RequestMethod.GET)
  public ModelAndView editarScanWebGet(@PathVariable("digitalID") java.lang.Long digitalID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewScanWebGet(digitalID,
        request, response, false);
  }



  /**
   * Editar un ScanWeb existent
   */
  @RequestMapping(value = "/{digitalID}/edit", method = RequestMethod.POST)
  public String editarScanWebPost(@ModelAttribute ScanWebForm scanWebForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ScanWebJPA scanWeb = scanWebForm.getScanWeb();

    FilesFormManager<Fitxer> afm = getFilesFormManager(); // FILE
    try {
      this.setFilesFormToEntity(afm, scanWeb, scanWebForm); // FILE
      preValidate(request, scanWebForm, result);
      getWebValidator().validate(scanWebForm, result);
      postValidate(request, scanWebForm, result);

      if (result.hasErrors()) {
        afm.processErrorFilesWithoutThrowException(); // FILE
        result.reject("error.form");
        return getTileForm();
      } else {
        scanWeb = update(request, scanWeb);
        afm.postPersistFiles(); // FILE
        createMessageSuccess(request, "success.modification", scanWeb.getDigitalID());
        status.setComplete();
        return getRedirectWhenModified(request, scanWebForm, null);
      }
    } catch (Throwable __e) {
      afm.processErrorFilesWithoutThrowException(); // FILE
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          scanWeb.getDigitalID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, scanWebForm, __e);
    }

  }


  /**
   * Eliminar un ScanWeb existent
   */
  @RequestMapping(value = "/{digitalID}/delete")
  public String eliminarScanWeb(@PathVariable("digitalID") java.lang.Long digitalID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      ScanWeb scanWeb = this.findByPrimaryKey(request, digitalID);
      if (scanWeb == null) {
        String __msg = createMessageError(request, "error.notfound", digitalID);
        return getRedirectWhenDelete(request, digitalID, new Exception(__msg));
      } else {
        delete(request, scanWeb);
        createMessageSuccess(request, "success.deleted", digitalID);
        return getRedirectWhenDelete(request, digitalID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", digitalID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, digitalID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute ScanWebFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarScanWeb(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __digitalID, Throwable e) {
    java.lang.Long digitalID = (java.lang.Long)__digitalID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (digitalID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(digitalID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "scanWeb.scanWeb";
  }

  public String getEntityNameCodePlural() {
    return "scanWeb.scanWeb.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("scanWeb.digitalID");
  }

  @InitBinder("scanWebFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("scanWebForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "scanWeb.digitalID");
  }

  public ScanWebWebValidator getWebValidator() {
    return scanWebWebValidator;
  }


  public void setWebValidator(ScanWebWebValidator __val) {
    if (__val != null) {
      this.scanWebWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de ScanWeb
   */
  @RequestMapping(value = "/{digitalID}/cancel")
  public String cancelScanWeb(@PathVariable("digitalID") java.lang.Long digitalID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, digitalID);
  }

  /**
   * Entra aqui al pitjar el boto cancel en el la creació de ScanWeb
   */
  @RequestMapping(value = "/cancel")
  public String cancelScanWeb(HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, null);
  }

  @Override
  public String getTableModelName() {
    return _TABLE_MODEL;
  }

  // FILE
  @Override
  public void setFilesFormToEntity(FilesFormManager<Fitxer> afm, ScanWeb scanWeb,
      ScanWebForm form) throws I18NException {

    FitxerJPA f;
    f = (FitxerJPA)afm.preProcessFile(form.getFitxerID(), form.isFitxerIDDelete(),
        form.isNou()? null : scanWeb.getFitxer());
    ((ScanWebJPA)scanWeb).setFitxer(f);
    if (f != null) { 
      scanWeb.setFitxerID(f.getFitxerID());
    } else {
      scanWeb.setFitxerID(0);
    }

  }

  // FILE
  @Override
  public void deleteFiles(ScanWeb scanWeb) {
    deleteFile(scanWeb.getFitxerID());
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


  public List<StringKeyValue> getReferenceListForUsuariID(HttpServletRequest request,
       ModelAndView mav, ScanWebForm scanWebForm, Where where)  throws I18NException {
    if (scanWebForm.isHiddenField(USUARIID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (scanWebForm.isReadOnlyField(USUARIID)) {
      _where = UsuariFields.USUARIID.equal(scanWebForm.getScanWeb().getUsuariID());
    }
    return getReferenceListForUsuariID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForUsuariID(HttpServletRequest request,
       ModelAndView mav, ScanWebFilterForm scanWebFilterForm,
       List<ScanWeb> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (scanWebFilterForm.isHiddenField(USUARIID)
       && !scanWebFilterForm.isGroupByField(USUARIID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(USUARIID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (ScanWeb _item : list) {
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
    return rm.value()[0];
  }

  public void preValidate(HttpServletRequest request,ScanWebForm scanWebForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,ScanWebForm scanWebForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, ScanWebFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, ScanWebFilterForm filterForm,  List<ScanWeb> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, ScanWebForm scanWebForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, ScanWebForm scanWebForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long digitalID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long digitalID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "scanWebFormWebDB";
  }

  public String getTileList() {
    return "scanWebListWebDB";
  }

  public String getSessionAttributeFilterForm() {
    return "ScanWeb_FilterForm_" + this.getClass().getName();
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public ScanWebJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long digitalID) throws I18NException {
    return (ScanWebJPA) scanWebEjb.findByPrimaryKey(digitalID);
  }


  public ScanWebJPA create(HttpServletRequest request, ScanWebJPA scanWeb)
    throws I18NException, I18NValidationException {
    return (ScanWebJPA) scanWebEjb.create(scanWeb);
  }


  public ScanWebJPA update(HttpServletRequest request, ScanWebJPA scanWeb)
    throws I18NException, I18NValidationException {
    return (ScanWebJPA) scanWebEjb.update(scanWeb);
  }


  public void delete(HttpServletRequest request, ScanWeb scanWeb) throws I18NException {
    scanWebEjb.delete(scanWeb);
  }

} // Final de Classe

