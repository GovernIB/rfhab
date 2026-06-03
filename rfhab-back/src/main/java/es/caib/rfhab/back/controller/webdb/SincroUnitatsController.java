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
import es.caib.rfhab.back.form.webdb.SincroUnitatsForm;

import es.caib.rfhab.back.validator.webdb.SincroUnitatsWebValidator;

import es.caib.rfhab.persistence.SincroUnitatsJPA;
import es.caib.rfhab.model.entity.SincroUnitats;
import es.caib.rfhab.model.fields.*;
import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.fundaciobit.genapp.common.web.tiles.Tile;
import org.fundaciobit.genapp.common.web.tiles.TileAttribute;
import org.fundaciobit.genapp.common.web.tiles.TileType;
import es.caib.rfhab.back.utils.Tab;

/**
 * Controller per gestionar un SincroUnitats
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@MenuOption(labelCode="sincroUnitats.sincroUnitats.plural", order=140, group=Tab.MENU_WEBDB)
@Controller
@RequestMapping(value = "/webdb/sincroUnitats")
@SessionAttributes(types = { SincroUnitatsForm.class, SincroUnitatsFilterForm.class })
@Tile(name="sincroUnitatsFormWebDB", extendsTile=Tab.MENU_WEBDB,
    // Els següents atributs no són necessaris si heredes aquesta classe
    contentJsp="/WEB-INF/jsp/webdb/sincroUnitatsForm.jsp", type=TileType.WEBDB_FORM,
    attributes={ @TileAttribute(name="titol", value="sincroUnitats.sincroUnitats")})
@Tile(name="sincroUnitatsListWebDB", extendsTile=Tab.MENU_WEBDB,
    // Els següents atributs no són necessaris si heredes aquesta classe 
    contentJsp="/WEB-INF/jsp/webdb/sincroUnitatsList.jsp", type=TileType.WEBDB_LIST,
    attributes={ @TileAttribute(name="titol", value="sincroUnitats.sincroUnitats")})
public class SincroUnitatsController
    extends es.caib.rfhab.back.controller.RFHabBaseController<SincroUnitats, java.lang.Long> implements SincroUnitatsFields {

  @EJB(mappedName = es.caib.rfhab.ejb.SincroUnitatsService.JNDI_NAME)
  protected es.caib.rfhab.ejb.SincroUnitatsService sincroUnitatsEjb;

  @Autowired
  private SincroUnitatsWebValidator sincroUnitatsWebValidator;

  @Autowired
  protected SincroUnitatsRefList sincroUnitatsRefList;

  // References 
  @Autowired
  protected UsuariRefList usuariRefList;

  /**
   * Llistat de totes SincroUnitats
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    SincroUnitatsFilterForm ff;
    ff = (SincroUnitatsFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar SincroUnitats de forma paginada
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
    llistat(mav, request, getSincroUnitatsFilterForm(pagina, mav, request));
    return mav;
  }

  public SincroUnitatsFilterForm getSincroUnitatsFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    SincroUnitatsFilterForm sincroUnitatsFilterForm;
    sincroUnitatsFilterForm = (SincroUnitatsFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(sincroUnitatsFilterForm == null) {
      sincroUnitatsFilterForm = new SincroUnitatsFilterForm();
      sincroUnitatsFilterForm.setContexte(getContextWeb());
      sincroUnitatsFilterForm.setEntityNameCode(getEntityNameCode());
      sincroUnitatsFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      sincroUnitatsFilterForm.setNou(true);
    } else {
      sincroUnitatsFilterForm.setNou(false);
    }
    sincroUnitatsFilterForm.setPage(pagina == null ? 1 : pagina);
    return sincroUnitatsFilterForm;
  }

  /**
   * Segona i següent peticions per llistar SincroUnitats de forma paginada
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
      @ModelAttribute SincroUnitatsFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getSincroUnitatsFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de SincroUnitats de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<SincroUnitats> llistat(ModelAndView mav, HttpServletRequest request,
     SincroUnitatsFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<SincroUnitats> sincroUnitats = processarLlistat(sincroUnitatsEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("sincroUnitatsItems", sincroUnitats);

    mav.addObject("sincroUnitatsFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, sincroUnitats, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, sincroUnitats);

    return sincroUnitats;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(SincroUnitatsFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<SincroUnitats> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field usuariId
    {
      _listSKV = getReferenceListForUsuariId(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfUsuariForUsuariId(_tmp);
      if (filterForm.getGroupByFields().contains(USUARIID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, USUARIID, false);
      };
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    SincroUnitatsFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<SincroUnitats> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_SINCROUNITATS_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(USUARIID, filterForm.getMapOfUsuariForUsuariId());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou SincroUnitats
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearSincroUnitatsGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    SincroUnitatsForm sincroUnitatsForm = getSincroUnitatsForm(null, false, request, mav);
    mav.addObject("sincroUnitatsForm" ,sincroUnitatsForm);
    fillReferencesForForm(sincroUnitatsForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public SincroUnitatsForm getSincroUnitatsForm(SincroUnitatsJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    SincroUnitatsForm sincroUnitatsForm;
    if(_jpa == null) {
      sincroUnitatsForm = new SincroUnitatsForm(new SincroUnitatsJPA(), true);
    } else {
      sincroUnitatsForm = new SincroUnitatsForm(_jpa, false);
      sincroUnitatsForm.setView(__isView);
    }
    sincroUnitatsForm.setContexte(getContextWeb());
    sincroUnitatsForm.setEntityNameCode(getEntityNameCode());
    sincroUnitatsForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return sincroUnitatsForm;
  }

  public void fillReferencesForForm(SincroUnitatsForm sincroUnitatsForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (sincroUnitatsForm.getListOfUsuariForUsuariId() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForUsuariId(request, mav, sincroUnitatsForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      sincroUnitatsForm.setListOfUsuariForUsuariId(_listSKV);
    }
    
  }

  /**
   * Guardar un nou SincroUnitats
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearSincroUnitatsPost(@ModelAttribute SincroUnitatsForm sincroUnitatsForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    SincroUnitatsJPA sincroUnitats = sincroUnitatsForm.getSincroUnitats();

    try {
      preValidate(request, sincroUnitatsForm, result);
      getWebValidator().validate(sincroUnitatsForm, result);
      postValidate(request,sincroUnitatsForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        sincroUnitats = create(request, sincroUnitats);
        createMessageSuccess(request, "success.creation", sincroUnitats.getSincrounitatsId());
        sincroUnitatsForm.setSincroUnitats(sincroUnitats);
        return getRedirectWhenCreated(request, sincroUnitatsForm);
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

  @RequestMapping(value = "/view/{sincrounitatsId}", method = RequestMethod.GET)
  public ModelAndView veureSincroUnitatsGet(@PathVariable("sincrounitatsId") java.lang.Long sincrounitatsId,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewSincroUnitatsGet(sincrounitatsId,
        request, response, true);
  }


  protected ModelAndView editAndViewSincroUnitatsGet(@PathVariable("sincrounitatsId") java.lang.Long sincrounitatsId,
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
    SincroUnitatsJPA sincroUnitats = findByPrimaryKey(request, sincrounitatsId);

    if (sincroUnitats == null) {
      createMessageWarning(request, "error.notfound", sincrounitatsId);
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      SincroUnitatsForm sincroUnitatsForm = getSincroUnitatsForm(sincroUnitats, __isView, request, mav);
      sincroUnitatsForm.setView(__isView);
      if(__isView) {
        sincroUnitatsForm.setAllFieldsReadOnly(ALL_SINCROUNITATS_FIELDS);
        sincroUnitatsForm.setSaveButtonVisible(false);
        sincroUnitatsForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(sincroUnitatsForm, request, mav);
      mav.addObject("sincroUnitatsForm", sincroUnitatsForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un SincroUnitats existent
   */
  @RequestMapping(value = "/{sincrounitatsId}/edit", method = RequestMethod.GET)
  public ModelAndView editarSincroUnitatsGet(@PathVariable("sincrounitatsId") java.lang.Long sincrounitatsId,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewSincroUnitatsGet(sincrounitatsId,
        request, response, false);
  }



  /**
   * Editar un SincroUnitats existent
   */
  @RequestMapping(value = "/{sincrounitatsId}/edit", method = RequestMethod.POST)
  public String editarSincroUnitatsPost(@ModelAttribute SincroUnitatsForm sincroUnitatsForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    SincroUnitatsJPA sincroUnitats = sincroUnitatsForm.getSincroUnitats();

    try {
      preValidate(request, sincroUnitatsForm, result);
      getWebValidator().validate(sincroUnitatsForm, result);
      postValidate(request, sincroUnitatsForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        sincroUnitats = update(request, sincroUnitats);
        createMessageSuccess(request, "success.modification", sincroUnitats.getSincrounitatsId());
        status.setComplete();
        return getRedirectWhenModified(request, sincroUnitatsForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          sincroUnitats.getSincrounitatsId(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, sincroUnitatsForm, __e);
    }

  }


  /**
   * Eliminar un SincroUnitats existent
   */
  @RequestMapping(value = "/{sincrounitatsId}/delete")
  public String eliminarSincroUnitats(@PathVariable("sincrounitatsId") java.lang.Long sincrounitatsId,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      SincroUnitats sincroUnitats = this.findByPrimaryKey(request, sincrounitatsId);
      if (sincroUnitats == null) {
        String __msg = createMessageError(request, "error.notfound", sincrounitatsId);
        return getRedirectWhenDelete(request, sincrounitatsId, new Exception(__msg));
      } else {
        delete(request, sincroUnitats);
        createMessageSuccess(request, "success.deleted", sincrounitatsId);
        return getRedirectWhenDelete(request, sincrounitatsId,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", sincrounitatsId, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, sincrounitatsId, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute SincroUnitatsFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarSincroUnitats(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __sincrounitatsId, Throwable e) {
    java.lang.Long sincrounitatsId = (java.lang.Long)__sincrounitatsId;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (sincrounitatsId == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(sincrounitatsId),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "sincroUnitats.sincroUnitats";
  }

  public String getEntityNameCodePlural() {
    return "sincroUnitats.sincroUnitats.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("sincroUnitats.sincrounitatsId");
  }

  @InitBinder("sincroUnitatsFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("sincroUnitatsForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "sincroUnitats.sincrounitatsId");
  }

  public SincroUnitatsWebValidator getWebValidator() {
    return sincroUnitatsWebValidator;
  }


  public void setWebValidator(SincroUnitatsWebValidator __val) {
    if (__val != null) {
      this.sincroUnitatsWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de SincroUnitats
   */
  @RequestMapping(value = "/{sincrounitatsId}/cancel")
  public String cancelSincroUnitats(@PathVariable("sincrounitatsId") java.lang.Long sincrounitatsId,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, sincrounitatsId);
  }

  /**
   * Entra aqui al pitjar el boto cancel en el la creació de SincroUnitats
   */
  @RequestMapping(value = "/cancel")
  public String cancelSincroUnitats(HttpServletRequest request,HttpServletResponse response) {
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


  public List<StringKeyValue> getReferenceListForUsuariId(HttpServletRequest request,
       ModelAndView mav, SincroUnitatsForm sincroUnitatsForm, Where where)  throws I18NException {
    if (sincroUnitatsForm.isHiddenField(USUARIID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (sincroUnitatsForm.isReadOnlyField(USUARIID)) {
      _where = UsuariFields.USUARIID.equal(sincroUnitatsForm.getSincroUnitats().getUsuariId());
    }
    return getReferenceListForUsuariId(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForUsuariId(HttpServletRequest request,
       ModelAndView mav, SincroUnitatsFilterForm sincroUnitatsFilterForm,
       List<SincroUnitats> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (sincroUnitatsFilterForm.isHiddenField(USUARIID)
       && !sincroUnitatsFilterForm.isGroupByField(USUARIID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(USUARIID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (SincroUnitats _item : list) {
        if(_item.getUsuariId() == null) { continue; };
        _pkList.add(_item.getUsuariId());
        }
        _w = UsuariFields.USUARIID.in(_pkList);
      }
    return getReferenceListForUsuariId(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForUsuariId(HttpServletRequest request,
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

  public void preValidate(HttpServletRequest request,SincroUnitatsForm sincroUnitatsForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,SincroUnitatsForm sincroUnitatsForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, SincroUnitatsFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, SincroUnitatsFilterForm filterForm,  List<SincroUnitats> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, SincroUnitatsForm sincroUnitatsForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, SincroUnitatsForm sincroUnitatsForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long sincrounitatsId, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long sincrounitatsId) {
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
    return "sincroUnitatsFormWebDB";
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
        return "sincroUnitatsListWebDB";
    }

  public String getSessionAttributeFilterForm() {
    return "SincroUnitats_FilterForm_" + this.getClass().getName();
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public SincroUnitatsJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long sincrounitatsId) throws I18NException {
    return (SincroUnitatsJPA) sincroUnitatsEjb.findByPrimaryKey(sincrounitatsId);
  }


  public SincroUnitatsJPA create(HttpServletRequest request, SincroUnitatsJPA sincroUnitats)
    throws I18NException, I18NValidationException {
    return (SincroUnitatsJPA) sincroUnitatsEjb.create(sincroUnitats);
  }


  public SincroUnitatsJPA update(HttpServletRequest request, SincroUnitatsJPA sincroUnitats)
    throws I18NException, I18NValidationException {
    return (SincroUnitatsJPA) sincroUnitatsEjb.update(sincroUnitats);
  }


  public void delete(HttpServletRequest request, SincroUnitats sincroUnitats) throws I18NException {
    sincroUnitatsEjb.delete(sincroUnitats);
  }

} // Final de Classe

