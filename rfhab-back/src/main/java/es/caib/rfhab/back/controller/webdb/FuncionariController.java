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
import es.caib.rfhab.back.form.webdb.FuncionariForm;

import es.caib.rfhab.back.validator.webdb.FuncionariWebValidator;

import es.caib.rfhab.persistence.FuncionariJPA;
import es.caib.rfhab.model.entity.Funcionari;
import es.caib.rfhab.model.fields.*;
import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.fundaciobit.genapp.common.web.tiles.Tile;
import org.fundaciobit.genapp.common.web.tiles.TileAttribute;
import org.fundaciobit.genapp.common.web.tiles.TileType;
import es.caib.rfhab.back.utils.Tab;

/**
 * Controller per gestionar un Funcionari
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@MenuOption(labelCode="funcionari.funcionari.plural", order=40, group=Tab.MENU_WEBDB)
@Controller
@RequestMapping(value = "/webdb/funcionari")
@SessionAttributes(types = { FuncionariForm.class, FuncionariFilterForm.class })
@Tile(name="funcionariFormWebDB", extendsTile=Tab.MENU_WEBDB,
    // Els següents atributs no són necessaris si heredes aquesta classe
    contentJsp="/WEB-INF/jsp/webdb/funcionariForm.jsp", type=TileType.WEBDB_FORM,
    attributes={ @TileAttribute(name="titol", value="funcionari.funcionari")})
@Tile(name="funcionariListWebDB", extendsTile=Tab.MENU_WEBDB,
    // Els següents atributs no són necessaris si heredes aquesta classe 
    contentJsp="/WEB-INF/jsp/webdb/funcionariList.jsp", type=TileType.WEBDB_LIST,
    attributes={ @TileAttribute(name="titol", value="funcionari.funcionari")})
public class FuncionariController
    extends es.caib.rfhab.back.controller.RFHabBaseController<Funcionari, java.lang.Long> implements FuncionariFields {

  @EJB(mappedName = es.caib.rfhab.ejb.FuncionariService.JNDI_NAME)
  protected es.caib.rfhab.ejb.FuncionariService funcionariEjb;

  @Autowired
  private FuncionariWebValidator funcionariWebValidator;

  @Autowired
  protected FuncionariRefList funcionariRefList;

  /**
   * Llistat de totes Funcionari
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    FuncionariFilterForm ff;
    ff = (FuncionariFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar Funcionari de forma paginada
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
    llistat(mav, request, getFuncionariFilterForm(pagina, mav, request));
    return mav;
  }

  public FuncionariFilterForm getFuncionariFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    FuncionariFilterForm funcionariFilterForm;
    funcionariFilterForm = (FuncionariFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(funcionariFilterForm == null) {
      funcionariFilterForm = new FuncionariFilterForm();
      funcionariFilterForm.setContexte(getContextWeb());
      funcionariFilterForm.setEntityNameCode(getEntityNameCode());
      funcionariFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      funcionariFilterForm.setNou(true);
    } else {
      funcionariFilterForm.setNou(false);
    }
    funcionariFilterForm.setPage(pagina == null ? 1 : pagina);
    return funcionariFilterForm;
  }

  /**
   * Segona i següent peticions per llistar Funcionari de forma paginada
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
      @ModelAttribute FuncionariFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getFuncionariFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de Funcionari de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<Funcionari> llistat(ModelAndView mav, HttpServletRequest request,
     FuncionariFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<Funcionari> funcionari = processarLlistat(funcionariEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("funcionariItems", funcionari);

    mav.addObject("funcionariFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, funcionari, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, funcionari);

    return funcionari;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(FuncionariFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<Funcionari> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field tipusIdentificador
    {
      _listSKV = getReferenceListForTipusIdentificador(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfValuesForTipusIdentificador(_tmp);
      if (filterForm.getGroupByFields().contains(TIPUSIDENTIFICADOR)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, TIPUSIDENTIFICADOR, false);
      };
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    FuncionariFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<Funcionari> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_FUNCIONARI_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(TIPUSIDENTIFICADOR, filterForm.getMapOfValuesForTipusIdentificador());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou Funcionari
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearFuncionariGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    FuncionariForm funcionariForm = getFuncionariForm(null, false, request, mav);
    mav.addObject("funcionariForm" ,funcionariForm);
    fillReferencesForForm(funcionariForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public FuncionariForm getFuncionariForm(FuncionariJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    FuncionariForm funcionariForm;
    if(_jpa == null) {
      funcionariForm = new FuncionariForm(new FuncionariJPA(), true);
    } else {
      funcionariForm = new FuncionariForm(_jpa, false);
      funcionariForm.setView(__isView);
    }
    funcionariForm.setContexte(getContextWeb());
    funcionariForm.setEntityNameCode(getEntityNameCode());
    funcionariForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return funcionariForm;
  }

  public void fillReferencesForForm(FuncionariForm funcionariForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (funcionariForm.getListOfValuesForTipusIdentificador() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForTipusIdentificador(request, mav, funcionariForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      funcionariForm.setListOfValuesForTipusIdentificador(_listSKV);
    }
    
  }

  /**
   * Guardar un nou Funcionari
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearFuncionariPost(@ModelAttribute FuncionariForm funcionariForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    FuncionariJPA funcionari = funcionariForm.getFuncionari();

    try {
      preValidate(request, funcionariForm, result);
      getWebValidator().validate(funcionariForm, result);
      postValidate(request,funcionariForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        funcionari = create(request, funcionari);
        createMessageSuccess(request, "success.creation", funcionari.getFuncionariID());
        funcionariForm.setFuncionari(funcionari);
        return getRedirectWhenCreated(request, funcionariForm);
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

  @RequestMapping(value = "/view/{funcionariID}", method = RequestMethod.GET)
  public ModelAndView veureFuncionariGet(@PathVariable("funcionariID") java.lang.Long funcionariID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewFuncionariGet(funcionariID,
        request, response, true);
  }


  protected ModelAndView editAndViewFuncionariGet(@PathVariable("funcionariID") java.lang.Long funcionariID,
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
    FuncionariJPA funcionari = findByPrimaryKey(request, funcionariID);

    if (funcionari == null) {
      createMessageWarning(request, "error.notfound", funcionariID);
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      FuncionariForm funcionariForm = getFuncionariForm(funcionari, __isView, request, mav);
      funcionariForm.setView(__isView);
      if(__isView) {
        funcionariForm.setAllFieldsReadOnly(ALL_FUNCIONARI_FIELDS);
        funcionariForm.setSaveButtonVisible(false);
        funcionariForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(funcionariForm, request, mav);
      mav.addObject("funcionariForm", funcionariForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un Funcionari existent
   */
  @RequestMapping(value = "/{funcionariID}/edit", method = RequestMethod.GET)
  public ModelAndView editarFuncionariGet(@PathVariable("funcionariID") java.lang.Long funcionariID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewFuncionariGet(funcionariID,
        request, response, false);
  }



  /**
   * Editar un Funcionari existent
   */
  @RequestMapping(value = "/{funcionariID}/edit", method = RequestMethod.POST)
  public String editarFuncionariPost(@ModelAttribute FuncionariForm funcionariForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    FuncionariJPA funcionari = funcionariForm.getFuncionari();

    try {
      preValidate(request, funcionariForm, result);
      getWebValidator().validate(funcionariForm, result);
      postValidate(request, funcionariForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        funcionari = update(request, funcionari);
        createMessageSuccess(request, "success.modification", funcionari.getFuncionariID());
        status.setComplete();
        return getRedirectWhenModified(request, funcionariForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          funcionari.getFuncionariID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, funcionariForm, __e);
    }

  }


  /**
   * Eliminar un Funcionari existent
   */
  @RequestMapping(value = "/{funcionariID}/delete")
  public String eliminarFuncionari(@PathVariable("funcionariID") java.lang.Long funcionariID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      Funcionari funcionari = this.findByPrimaryKey(request, funcionariID);
      if (funcionari == null) {
        String __msg = createMessageError(request, "error.notfound", funcionariID);
        return getRedirectWhenDelete(request, funcionariID, new Exception(__msg));
      } else {
        delete(request, funcionari);
        createMessageSuccess(request, "success.deleted", funcionariID);
        return getRedirectWhenDelete(request, funcionariID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", funcionariID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, funcionariID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute FuncionariFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarFuncionari(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __funcionariID, Throwable e) {
    java.lang.Long funcionariID = (java.lang.Long)__funcionariID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (funcionariID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(funcionariID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "funcionari.funcionari";
  }

  public String getEntityNameCodePlural() {
    return "funcionari.funcionari.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("funcionari.funcionariID");
  }

  @InitBinder("funcionariFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("funcionariForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "funcionari.funcionariID");
  }

  public FuncionariWebValidator getWebValidator() {
    return funcionariWebValidator;
  }


  public void setWebValidator(FuncionariWebValidator __val) {
    if (__val != null) {
      this.funcionariWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de Funcionari
   */
  @RequestMapping(value = "/{funcionariID}/cancel")
  public String cancelFuncionari(@PathVariable("funcionariID") java.lang.Long funcionariID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, funcionariID);
  }

  /**
   * Entra aqui al pitjar el boto cancel en el la creació de Funcionari
   */
  @RequestMapping(value = "/cancel")
  public String cancelFuncionari(HttpServletRequest request,HttpServletResponse response) {
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


  public List<StringKeyValue> getReferenceListForTipusIdentificador(HttpServletRequest request,
       ModelAndView mav, FuncionariForm funcionariForm, Where where)  throws I18NException {
    if (funcionariForm.isHiddenField(TIPUSIDENTIFICADOR)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForTipusIdentificador(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForTipusIdentificador(HttpServletRequest request,
       ModelAndView mav, FuncionariFilterForm funcionariFilterForm,
       List<Funcionari> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (funcionariFilterForm.isHiddenField(TIPUSIDENTIFICADOR)
       && !funcionariFilterForm.isGroupByField(TIPUSIDENTIFICADOR)
       && !funcionariFilterForm.isFilterByField(TIPUSIDENTIFICADOR)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    return getReferenceListForTipusIdentificador(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForTipusIdentificador(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    __tmp.add(new StringKeyValue("0" , "0"));
    __tmp.add(new StringKeyValue("1" , "1"));
    __tmp.add(new StringKeyValue("2" , "2"));
    __tmp.add(new StringKeyValue("4" , "4"));
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

  public void preValidate(HttpServletRequest request,FuncionariForm funcionariForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,FuncionariForm funcionariForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, FuncionariFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, FuncionariFilterForm filterForm,  List<Funcionari> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, FuncionariForm funcionariForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, FuncionariForm funcionariForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long funcionariID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long funcionariID) {
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
    return "funcionariFormWebDB";
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
        return "funcionariListWebDB";
    }

  public String getSessionAttributeFilterForm() {
    return "Funcionari_FilterForm_" + this.getClass().getName();
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public FuncionariJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long funcionariID) throws I18NException {
    return (FuncionariJPA) funcionariEjb.findByPrimaryKey(funcionariID);
  }


  public FuncionariJPA create(HttpServletRequest request, FuncionariJPA funcionari)
    throws I18NException, I18NValidationException {
    return (FuncionariJPA) funcionariEjb.create(funcionari);
  }


  public FuncionariJPA update(HttpServletRequest request, FuncionariJPA funcionari)
    throws I18NException, I18NValidationException {
    return (FuncionariJPA) funcionariEjb.update(funcionari);
  }


  public void delete(HttpServletRequest request, Funcionari funcionari) throws I18NException {
    funcionariEjb.delete(funcionari);
  }

} // Final de Classe

