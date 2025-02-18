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
import es.caib.rfhab.back.form.webdb.RolForm;

import es.caib.rfhab.back.validator.webdb.RolWebValidator;

import es.caib.rfhab.persistence.RolJPA;
import es.caib.rfhab.model.entity.Rol;
import es.caib.rfhab.model.fields.*;

/**
 * Controller per gestionar un Rol
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/rol")
@SessionAttributes(types = { RolForm.class, RolFilterForm.class })
public class RolController
    extends es.caib.rfhab.back.controller.RFHabBaseController<Rol, java.lang.Long> implements RolFields {

  @EJB(mappedName = es.caib.rfhab.ejb.IdiomaService.JNDI_NAME)
  protected es.caib.rfhab.ejb.IdiomaService idiomaEjb;

  @EJB(mappedName = es.caib.rfhab.ejb.RolService.JNDI_NAME)
  protected es.caib.rfhab.ejb.RolService rolEjb;

  @Autowired
  private RolWebValidator rolWebValidator;

  @Autowired
  protected RolRefList rolRefList;

  // References 
  @Autowired
  protected TraduccioRefList traduccioRefList;

  /**
   * Llistat de totes Rol
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    RolFilterForm ff;
    ff = (RolFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar Rol de forma paginada
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
    llistat(mav, request, getRolFilterForm(pagina, mav, request));
    return mav;
  }

  public RolFilterForm getRolFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    RolFilterForm rolFilterForm;
    rolFilterForm = (RolFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(rolFilterForm == null) {
      rolFilterForm = new RolFilterForm();
      rolFilterForm.setContexte(getContextWeb());
      rolFilterForm.setEntityNameCode(getEntityNameCode());
      rolFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      rolFilterForm.setNou(true);
    } else {
      rolFilterForm.setNou(false);
    }
    rolFilterForm.setPage(pagina == null ? 1 : pagina);
    return rolFilterForm;
  }

  /**
   * Segona i següent peticions per llistar Rol de forma paginada
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
      @ModelAttribute RolFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getRolFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de Rol de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<Rol> llistat(ModelAndView mav, HttpServletRequest request,
     RolFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<Rol> rol = processarLlistat(rolEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("rolItems", rol);

    mav.addObject("rolFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, rol, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, rol);

    return rol;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(RolFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<Rol> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field nomID
    {
      _listSKV = getReferenceListForNomID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfTraduccioForNomID(_tmp);
      if (filterForm.getGroupByFields().contains(NOMID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, NOMID, false);
      };
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    RolFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<Rol> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_ROL_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(NOMID, filterForm.getMapOfTraduccioForNomID());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou Rol
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearRolGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    RolForm rolForm = getRolForm(null, false, request, mav);
    
    if (rolForm.getRol().getNom() == null){
      es.caib.rfhab.persistence.TraduccioJPA trad = new es.caib.rfhab.persistence.TraduccioJPA();
      for (es.caib.rfhab.model.entity.Idioma idioma : rolForm.getIdiomesTraduccio()) {
        trad.addTraduccio(idioma.getIdiomaID(), new es.caib.rfhab.persistence.TraduccioMapJPA());
      }
      rolForm.getRol().setNom(trad);
    }

    mav.addObject("rolForm" ,rolForm);
    fillReferencesForForm(rolForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public RolForm getRolForm(RolJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    RolForm rolForm;
    if(_jpa == null) {
      rolForm = new RolForm(new RolJPA(), true);
    } else {
      rolForm = new RolForm(_jpa, false);
      rolForm.setView(__isView);
    }
    rolForm.setContexte(getContextWeb());
    rolForm.setEntityNameCode(getEntityNameCode());
    rolForm.setEntityNameCodePlural(getEntityNameCodePlural());
    rolForm.setIdiomesTraduccio(getIdiomesSuportats());
    return rolForm;
  }

  public void fillReferencesForForm(RolForm rolForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    
  }


  public List<es.caib.rfhab.model.entity.Idioma> getIdiomesSuportats() throws I18NException {
    List<es.caib.rfhab.model.entity.Idioma> idiomes = idiomaEjb.select(es.caib.rfhab.model.fields.IdiomaFields.SUPORTAT.equal(true));
    return idiomes;
  }


  /**
   * Guardar un nou Rol
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearRolPost(@ModelAttribute RolForm rolForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    RolJPA rol = rolForm.getRol();

    try {
      preValidate(request, rolForm, result);
      getWebValidator().validate(rolForm, result);
      postValidate(request,rolForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        rol = create(request, rol);
        createMessageSuccess(request, "success.creation", rol.getRolID());
        rolForm.setRol(rol);
        return getRedirectWhenCreated(request, rolForm);
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

  @RequestMapping(value = "/view/{rolID}", method = RequestMethod.GET)
  public ModelAndView veureRolGet(@PathVariable("rolID") java.lang.Long rolID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewRolGet(rolID,
        request, response, true);
  }


  protected ModelAndView editAndViewRolGet(@PathVariable("rolID") java.lang.Long rolID,
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
    RolJPA rol = findByPrimaryKey(request, rolID);

    if (rol == null) {
      createMessageWarning(request, "error.notfound", rolID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, rolID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      RolForm rolForm = getRolForm(rol, __isView, request, mav);
      rolForm.setView(__isView);
      if(__isView) {
        rolForm.setAllFieldsReadOnly(ALL_ROL_FIELDS);
        rolForm.setSaveButtonVisible(false);
        rolForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(rolForm, request, mav);
      mav.addObject("rolForm", rolForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un Rol existent
   */
  @RequestMapping(value = "/{rolID}/edit", method = RequestMethod.GET)
  public ModelAndView editarRolGet(@PathVariable("rolID") java.lang.Long rolID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewRolGet(rolID,
        request, response, false);
  }



  /**
   * Editar un Rol existent
   */
  @RequestMapping(value = "/{rolID}/edit", method = RequestMethod.POST)
  public String editarRolPost(@ModelAttribute RolForm rolForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    RolJPA rol = rolForm.getRol();

    try {
      preValidate(request, rolForm, result);
      getWebValidator().validate(rolForm, result);
      postValidate(request, rolForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        rol = update(request, rol);
        createMessageSuccess(request, "success.modification", rol.getRolID());
        status.setComplete();
        return getRedirectWhenModified(request, rolForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          rol.getRolID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, rolForm, __e);
    }

  }


  /**
   * Eliminar un Rol existent
   */
  @RequestMapping(value = "/{rolID}/delete")
  public String eliminarRol(@PathVariable("rolID") java.lang.Long rolID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      Rol rol = this.findByPrimaryKey(request, rolID);
      if (rol == null) {
        String __msg = createMessageError(request, "error.notfound", rolID);
        return getRedirectWhenDelete(request, rolID, new Exception(__msg));
      } else {
        delete(request, rol);
        createMessageSuccess(request, "success.deleted", rolID);
        return getRedirectWhenDelete(request, rolID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", rolID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, rolID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute RolFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarRol(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __rolID, Throwable e) {
    java.lang.Long rolID = (java.lang.Long)__rolID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (rolID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(rolID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "rol.rol";
  }

  public String getEntityNameCodePlural() {
    return "rol.rol.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("rol.rolID");
  }

  @InitBinder("rolFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("rolForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "rol.rolID");
  }

  public RolWebValidator getWebValidator() {
    return rolWebValidator;
  }


  public void setWebValidator(RolWebValidator __val) {
    if (__val != null) {
      this.rolWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de Rol
   */
  @RequestMapping(value = "/{rolID}/cancel")
  public String cancelRol(@PathVariable("rolID") java.lang.Long rolID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, rolID);
  }

  /**
   * Entra aqui al pitjar el boto cancel en el la creació de Rol
   */
  @RequestMapping(value = "/cancel")
  public String cancelRol(HttpServletRequest request,HttpServletResponse response) {
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

  public List<StringKeyValue> getReferenceListForNomID(HttpServletRequest request,
       ModelAndView mav, RolFilterForm rolFilterForm,
       List<Rol> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (rolFilterForm.isHiddenField(NOMID)
       && !rolFilterForm.isGroupByField(NOMID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(NOMID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (Rol _item : list) {
        _pkList.add(_item.getNomID());
        }
        _w = TraduccioFields.TRADUCCIOID.in(_pkList);
      }
    return getReferenceListForNomID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForNomID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return traduccioRefList.getReferenceList(TraduccioFields.TRADUCCIOID, where );
  }


  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public void preValidate(HttpServletRequest request,RolForm rolForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,RolForm rolForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, RolFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, RolFilterForm filterForm,  List<Rol> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, RolForm rolForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, RolForm rolForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long rolID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long rolID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "rolFormWebDB";
  }

  public String getTileList() {
    return "rolListWebDB";
  }

  public String getSessionAttributeFilterForm() {
    return "Rol_FilterForm_" + this.getClass().getName();
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public RolJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long rolID) throws I18NException {
    return (RolJPA) rolEjb.findByPrimaryKey(rolID);
  }


  public RolJPA create(HttpServletRequest request, RolJPA rol)
    throws I18NException, I18NValidationException {
    return (RolJPA) rolEjb.create(rol);
  }


  public RolJPA update(HttpServletRequest request, RolJPA rol)
    throws I18NException, I18NValidationException {
    return (RolJPA) rolEjb.update(rol);
  }


  public void delete(HttpServletRequest request, Rol rol) throws I18NException {
    rolEjb.delete(rol);
  }

} // Final de Classe

