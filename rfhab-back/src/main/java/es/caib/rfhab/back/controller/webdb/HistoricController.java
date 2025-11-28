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
import es.caib.rfhab.back.form.webdb.HistoricForm;

import es.caib.rfhab.back.validator.webdb.HistoricWebValidator;

import es.caib.rfhab.persistence.HistoricJPA;
import es.caib.rfhab.model.entity.Historic;
import es.caib.rfhab.model.fields.*;
import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.fundaciobit.genapp.common.web.tiles.Tile;
import org.fundaciobit.genapp.common.web.tiles.TileAttribute;
import org.fundaciobit.genapp.common.web.tiles.TileType;
import es.caib.rfhab.back.utils.Tab;

/**
 * Controller per gestionar un Historic
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@MenuOption(labelCode="historic.historic.plural", order=70, group=Tab.MENU_WEBDB)
@Controller
@RequestMapping(value = "/webdb/historic")
@SessionAttributes(types = { HistoricForm.class, HistoricFilterForm.class })
@Tile(name="historicFormWebDB", contentJsp="/WEB-INF/jsp/webdb/historicForm.jsp", extendsTile=Tab.MENU_WEBDB,
      type=TileType.WEBDB_FORM , attributes={ @TileAttribute(name="titol", value="historic.historic")})
@Tile(name="historicListWebDB", contentJsp="/WEB-INF/jsp/webdb/historicList.jsp", extendsTile=Tab.MENU_WEBDB,
       type=TileType.WEBDB_LIST, attributes={ @TileAttribute(name="titol", value="historic.historic") })
public class HistoricController
    extends es.caib.rfhab.back.controller.RFHabBaseController<Historic, java.lang.Long> implements HistoricFields {

  @EJB(mappedName = es.caib.rfhab.ejb.HistoricService.JNDI_NAME)
  protected es.caib.rfhab.ejb.HistoricService historicEjb;

  @Autowired
  private HistoricWebValidator historicWebValidator;

  @Autowired
  protected HistoricRefList historicRefList;

  // References 
  @Autowired
  protected FuncionariRefList funcionariRefList;

  // References 
  @Autowired
  protected UsuariRefList usuariRefList;

  /**
   * Llistat de totes Historic
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    HistoricFilterForm ff;
    ff = (HistoricFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar Historic de forma paginada
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
    llistat(mav, request, getHistoricFilterForm(pagina, mav, request));
    return mav;
  }

  public HistoricFilterForm getHistoricFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    HistoricFilterForm historicFilterForm;
    historicFilterForm = (HistoricFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(historicFilterForm == null) {
      historicFilterForm = new HistoricFilterForm();
      historicFilterForm.setContexte(getContextWeb());
      historicFilterForm.setEntityNameCode(getEntityNameCode());
      historicFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      historicFilterForm.setNou(true);
    } else {
      historicFilterForm.setNou(false);
    }
    historicFilterForm.setPage(pagina == null ? 1 : pagina);
    return historicFilterForm;
  }

  /**
   * Segona i següent peticions per llistar Historic de forma paginada
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
      @ModelAttribute HistoricFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getHistoricFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de Historic de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<Historic> llistat(ModelAndView mav, HttpServletRequest request,
     HistoricFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<Historic> historic = processarLlistat(historicEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("historicItems", historic);

    mav.addObject("historicFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, historic, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, historic);

    return historic;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(HistoricFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<Historic> list, List<GroupByItem> groupItems) throws I18NException {
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
    HistoricFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<Historic> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_HISTORIC_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(FUNCIONARIID, filterForm.getMapOfFuncionariForFuncionariID());
    __mapping.put(USUARIID, filterForm.getMapOfUsuariForUsuariID());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou Historic
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearHistoricGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    HistoricForm historicForm = getHistoricForm(null, false, request, mav);
    mav.addObject("historicForm" ,historicForm);
    fillReferencesForForm(historicForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public HistoricForm getHistoricForm(HistoricJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    HistoricForm historicForm;
    if(_jpa == null) {
      historicForm = new HistoricForm(new HistoricJPA(), true);
    } else {
      historicForm = new HistoricForm(_jpa, false);
      historicForm.setView(__isView);
    }
    historicForm.setContexte(getContextWeb());
    historicForm.setEntityNameCode(getEntityNameCode());
    historicForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return historicForm;
  }

  public void fillReferencesForForm(HistoricForm historicForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (historicForm.getListOfFuncionariForFuncionariID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForFuncionariID(request, mav, historicForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      historicForm.setListOfFuncionariForFuncionariID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (historicForm.getListOfUsuariForUsuariID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForUsuariID(request, mav, historicForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      historicForm.setListOfUsuariForUsuariID(_listSKV);
    }
    
  }

  /**
   * Guardar un nou Historic
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearHistoricPost(@ModelAttribute HistoricForm historicForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    HistoricJPA historic = historicForm.getHistoric();

    try {
      preValidate(request, historicForm, result);
      getWebValidator().validate(historicForm, result);
      postValidate(request,historicForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        historic = create(request, historic);
        createMessageSuccess(request, "success.creation", historic.getHistoricID());
        historicForm.setHistoric(historic);
        return getRedirectWhenCreated(request, historicForm);
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

  @RequestMapping(value = "/view/{historicID}", method = RequestMethod.GET)
  public ModelAndView veureHistoricGet(@PathVariable("historicID") java.lang.Long historicID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewHistoricGet(historicID,
        request, response, true);
  }


  protected ModelAndView editAndViewHistoricGet(@PathVariable("historicID") java.lang.Long historicID,
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
    HistoricJPA historic = findByPrimaryKey(request, historicID);

    if (historic == null) {
      createMessageWarning(request, "error.notfound", historicID);
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      HistoricForm historicForm = getHistoricForm(historic, __isView, request, mav);
      historicForm.setView(__isView);
      if(__isView) {
        historicForm.setAllFieldsReadOnly(ALL_HISTORIC_FIELDS);
        historicForm.setSaveButtonVisible(false);
        historicForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(historicForm, request, mav);
      mav.addObject("historicForm", historicForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un Historic existent
   */
  @RequestMapping(value = "/{historicID}/edit", method = RequestMethod.GET)
  public ModelAndView editarHistoricGet(@PathVariable("historicID") java.lang.Long historicID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewHistoricGet(historicID,
        request, response, false);
  }



  /**
   * Editar un Historic existent
   */
  @RequestMapping(value = "/{historicID}/edit", method = RequestMethod.POST)
  public String editarHistoricPost(@ModelAttribute HistoricForm historicForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    HistoricJPA historic = historicForm.getHistoric();

    try {
      preValidate(request, historicForm, result);
      getWebValidator().validate(historicForm, result);
      postValidate(request, historicForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        historic = update(request, historic);
        createMessageSuccess(request, "success.modification", historic.getHistoricID());
        status.setComplete();
        return getRedirectWhenModified(request, historicForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          historic.getHistoricID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, historicForm, __e);
    }

  }


  /**
   * Eliminar un Historic existent
   */
  @RequestMapping(value = "/{historicID}/delete")
  public String eliminarHistoric(@PathVariable("historicID") java.lang.Long historicID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      Historic historic = this.findByPrimaryKey(request, historicID);
      if (historic == null) {
        String __msg = createMessageError(request, "error.notfound", historicID);
        return getRedirectWhenDelete(request, historicID, new Exception(__msg));
      } else {
        delete(request, historic);
        createMessageSuccess(request, "success.deleted", historicID);
        return getRedirectWhenDelete(request, historicID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", historicID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, historicID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute HistoricFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarHistoric(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __historicID, Throwable e) {
    java.lang.Long historicID = (java.lang.Long)__historicID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (historicID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(historicID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "historic.historic";
  }

  public String getEntityNameCodePlural() {
    return "historic.historic.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("historic.historicID");
  }

  @InitBinder("historicFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("historicForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "historic.historicID");
  }

  public HistoricWebValidator getWebValidator() {
    return historicWebValidator;
  }


  public void setWebValidator(HistoricWebValidator __val) {
    if (__val != null) {
      this.historicWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de Historic
   */
  @RequestMapping(value = "/{historicID}/cancel")
  public String cancelHistoric(@PathVariable("historicID") java.lang.Long historicID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, historicID);
  }

  /**
   * Entra aqui al pitjar el boto cancel en el la creació de Historic
   */
  @RequestMapping(value = "/cancel")
  public String cancelHistoric(HttpServletRequest request,HttpServletResponse response) {
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
       ModelAndView mav, HistoricForm historicForm, Where where)  throws I18NException {
    if (historicForm.isHiddenField(FUNCIONARIID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (historicForm.isReadOnlyField(FUNCIONARIID)) {
      _where = FuncionariFields.FUNCIONARIID.equal(historicForm.getHistoric().getFuncionariID());
    }
    return getReferenceListForFuncionariID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForFuncionariID(HttpServletRequest request,
       ModelAndView mav, HistoricFilterForm historicFilterForm,
       List<Historic> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (historicFilterForm.isHiddenField(FUNCIONARIID)
       && !historicFilterForm.isGroupByField(FUNCIONARIID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(FUNCIONARIID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (Historic _item : list) {
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


  public List<StringKeyValue> getReferenceListForUsuariID(HttpServletRequest request,
       ModelAndView mav, HistoricForm historicForm, Where where)  throws I18NException {
    if (historicForm.isHiddenField(USUARIID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (historicForm.isReadOnlyField(USUARIID)) {
      _where = UsuariFields.USUARIID.equal(historicForm.getHistoric().getUsuariID());
    }
    return getReferenceListForUsuariID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForUsuariID(HttpServletRequest request,
       ModelAndView mav, HistoricFilterForm historicFilterForm,
       List<Historic> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (historicFilterForm.isHiddenField(USUARIID)
       && !historicFilterForm.isGroupByField(USUARIID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(USUARIID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (Historic _item : list) {
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

  public void preValidate(HttpServletRequest request,HistoricForm historicForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,HistoricForm historicForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, HistoricFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, HistoricFilterForm filterForm,  List<Historic> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, HistoricForm historicForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, HistoricForm historicForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long historicID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long historicID) {
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
    return "historicFormWebDB";
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
        return "historicListWebDB";
    }

  public String getSessionAttributeFilterForm() {
    return "Historic_FilterForm_" + this.getClass().getName();
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public HistoricJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long historicID) throws I18NException {
    return (HistoricJPA) historicEjb.findByPrimaryKey(historicID);
  }


  public HistoricJPA create(HttpServletRequest request, HistoricJPA historic)
    throws I18NException, I18NValidationException {
    return (HistoricJPA) historicEjb.create(historic);
  }


  public HistoricJPA update(HttpServletRequest request, HistoricJPA historic)
    throws I18NException, I18NValidationException {
    return (HistoricJPA) historicEjb.update(historic);
  }


  public void delete(HttpServletRequest request, Historic historic) throws I18NException {
    historicEjb.delete(historic);
  }

} // Final de Classe

