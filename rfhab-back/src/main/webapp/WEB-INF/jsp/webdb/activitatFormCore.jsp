<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="ActivitatFields" className="es.caib.rfhab.model.fields.ActivitatFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,ActivitatFields.FUNCIONARIID)}">
        <tr id="activitat_funcionariID_rowid">
          <td id="activitat_funcionariID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ActivitatFields.FUNCIONARIID])?'activitat.funcionariID':__theForm.labels[ActivitatFields.FUNCIONARIID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[ActivitatFields.FUNCIONARIID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ActivitatFields.FUNCIONARIID]}" ></i>
              </c:if>
            </td>
          <td id="activitat_funcionariID_columnvalueid">
          <form:errors path="activitat.funcionariID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,ActivitatFields.FUNCIONARIID)}" >
          <form:hidden path="activitat.funcionariID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.activitat.funcionariID,__theForm.listOfFuncionariForFuncionariID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,ActivitatFields.FUNCIONARIID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="activitat_funcionariID"  onchange="if(typeof onChangeFuncionariID == 'function') {  onChangeFuncionariID(this); };"  cssClass="form-control col-md-9-optional" path="activitat.funcionariID">
            <c:forEach items="${__theForm.listOfFuncionariForFuncionariID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
          <script>
              $(document).ready(function() {
                  $('#activitat_funcionariID').select2();
              });
          </script>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ActivitatFields.TIPUS)}">
        <tr id="activitat_tipus_rowid">
          <td id="activitat_tipus_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ActivitatFields.TIPUS])?'activitat.tipus':__theForm.labels[ActivitatFields.TIPUS]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[ActivitatFields.TIPUS]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ActivitatFields.TIPUS]}" ></i>
              </c:if>
            </td>
          <td id="activitat_tipus_columnvalueid">
          <form:errors path="activitat.tipus" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,ActivitatFields.TIPUS)}" >
          <form:hidden path="activitat.tipus"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.activitat.tipus,__theForm.listOfValuesForTipus)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,ActivitatFields.TIPUS)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="activitat_tipus"  onchange="if(typeof onChangeTipus == 'function') {  onChangeTipus(this); };"  cssClass="form-control col-md-9-optional" path="activitat.tipus">
            <c:forEach items="${__theForm.listOfValuesForTipus}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ActivitatFields.REGISTRE)}">
        <tr id="activitat_registre_rowid">
          <td id="activitat_registre_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ActivitatFields.REGISTRE])?'activitat.registre':__theForm.labels[ActivitatFields.REGISTRE]}" />
             </label>
              <c:if test="${not empty __theForm.help[ActivitatFields.REGISTRE]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ActivitatFields.REGISTRE]}" ></i>
              </c:if>
            </td>
          <td id="activitat_registre_columnvalueid">
            <form:errors path="activitat.registre" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,ActivitatFields.REGISTRE)? 'true' : 'false'}" cssClass="w-75 form-control  ${gen:contains(__theForm.readOnlyFields ,ActivitatFields.REGISTRE)? ' uneditable-input' : ''}"  style="" maxlength="50" path="activitat.registre"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ActivitatFields.TRAMIT)}">
        <tr id="activitat_tramit_rowid">
          <td id="activitat_tramit_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ActivitatFields.TRAMIT])?'activitat.tramit':__theForm.labels[ActivitatFields.TRAMIT]}" />
             </label>
              <c:if test="${not empty __theForm.help[ActivitatFields.TRAMIT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ActivitatFields.TRAMIT]}" ></i>
              </c:if>
            </td>
          <td id="activitat_tramit_columnvalueid">
            <form:errors path="activitat.tramit" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,ActivitatFields.TRAMIT)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,ActivitatFields.TRAMIT)? ' uneditable-input' : ''}"  style="" maxlength="150" path="activitat.tramit"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ActivitatFields.CODISIA)}">
        <tr id="activitat_codiSia_rowid">
          <td id="activitat_codiSia_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ActivitatFields.CODISIA])?'activitat.codiSia':__theForm.labels[ActivitatFields.CODISIA]}" />
             </label>
              <c:if test="${not empty __theForm.help[ActivitatFields.CODISIA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ActivitatFields.CODISIA]}" ></i>
              </c:if>
            </td>
          <td id="activitat_codiSia_columnvalueid">
            <form:errors path="activitat.codiSia" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,ActivitatFields.CODISIA)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,ActivitatFields.CODISIA)? ' uneditable-input' : ''}"  style="" maxlength="150" path="activitat.codiSia"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ActivitatFields.AUTORITZACIOID)}">
        <tr id="activitat_autoritzacioID_rowid">
          <td id="activitat_autoritzacioID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ActivitatFields.AUTORITZACIOID])?'activitat.autoritzacioID':__theForm.labels[ActivitatFields.AUTORITZACIOID]}" />
             </label>
              <c:if test="${not empty __theForm.help[ActivitatFields.AUTORITZACIOID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ActivitatFields.AUTORITZACIOID]}" ></i>
              </c:if>
            </td>
          <td id="activitat_autoritzacioID_columnvalueid">
            <form:errors path="activitat.autoritzacioID" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,ActivitatFields.AUTORITZACIOID)? 'true' : 'false'}" cssClass="w-25 form-control  ${gen:contains(__theForm.readOnlyFields ,ActivitatFields.AUTORITZACIOID)? ' uneditable-input' : ''}"  style=""  path="activitat.autoritzacioID"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ActivitatFields.DATACREACIO)}">
        <tr id="activitat_dataCreacio_rowid">
          <td id="activitat_dataCreacio_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ActivitatFields.DATACREACIO])?'activitat.dataCreacio':__theForm.labels[ActivitatFields.DATACREACIO]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[ActivitatFields.DATACREACIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ActivitatFields.DATACREACIO]}" ></i>
              </c:if>
            </td>
          <td id="activitat_dataCreacio_columnvalueid">
    <form:errors path="activitat.dataCreacio" cssClass="errorField alert alert-danger" />
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="activitat_dataCreacio" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,ActivitatFields.DATACREACIO)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#activitat_dataCreacio" path="activitat.dataCreacio" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,ActivitatFields.DATACREACIO)}" >
                    <div class="input-group-append"  data-target="#activitat_dataCreacio"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#activitat_dataCreacio').datetimepicker({
                    format: '${gen:getJSDateTimePattern()}',
                    locale: '${lang}',
                    icons: {
                       time: 'far fa-clock'
                    }
                });
            });
        </script>           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ActivitatFields.INTERESSATNOM)}">
        <tr id="activitat_interessatNom_rowid">
          <td id="activitat_interessatNom_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ActivitatFields.INTERESSATNOM])?'activitat.interessatNom':__theForm.labels[ActivitatFields.INTERESSATNOM]}" />
             </label>
              <c:if test="${not empty __theForm.help[ActivitatFields.INTERESSATNOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ActivitatFields.INTERESSATNOM]}" ></i>
              </c:if>
            </td>
          <td id="activitat_interessatNom_columnvalueid">
              <form:errors path="activitat.interessatNom" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,ActivitatFields.INTERESSATNOM)? 'true' : 'false'}" path="activitat.interessatNom"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_interessatNom" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_interessatNom" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('activitat.interessatNom'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('activitat.interessatNom'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('activitat.interessatNom'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_interessatNom').on('click', function(){
					var valor = ($('#dropdownMenuContainer_interessatNom').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_interessatNom').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ActivitatFields.INTERESSATLLINATGE1)}">
        <tr id="activitat_interessatLlinatge1_rowid">
          <td id="activitat_interessatLlinatge1_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ActivitatFields.INTERESSATLLINATGE1])?'activitat.interessatLlinatge1':__theForm.labels[ActivitatFields.INTERESSATLLINATGE1]}" />
             </label>
              <c:if test="${not empty __theForm.help[ActivitatFields.INTERESSATLLINATGE1]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ActivitatFields.INTERESSATLLINATGE1]}" ></i>
              </c:if>
            </td>
          <td id="activitat_interessatLlinatge1_columnvalueid">
              <form:errors path="activitat.interessatLlinatge1" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,ActivitatFields.INTERESSATLLINATGE1)? 'true' : 'false'}" path="activitat.interessatLlinatge1"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_interessatLlinatge1" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_interessatLlinatge1" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('activitat.interessatLlinatge1'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('activitat.interessatLlinatge1'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('activitat.interessatLlinatge1'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_interessatLlinatge1').on('click', function(){
					var valor = ($('#dropdownMenuContainer_interessatLlinatge1').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_interessatLlinatge1').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ActivitatFields.INTERESSATLLINATGE2)}">
        <tr id="activitat_interessatLlinatge2_rowid">
          <td id="activitat_interessatLlinatge2_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ActivitatFields.INTERESSATLLINATGE2])?'activitat.interessatLlinatge2':__theForm.labels[ActivitatFields.INTERESSATLLINATGE2]}" />
             </label>
              <c:if test="${not empty __theForm.help[ActivitatFields.INTERESSATLLINATGE2]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ActivitatFields.INTERESSATLLINATGE2]}" ></i>
              </c:if>
            </td>
          <td id="activitat_interessatLlinatge2_columnvalueid">
              <form:errors path="activitat.interessatLlinatge2" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,ActivitatFields.INTERESSATLLINATGE2)? 'true' : 'false'}" path="activitat.interessatLlinatge2"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_interessatLlinatge2" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_interessatLlinatge2" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('activitat.interessatLlinatge2'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('activitat.interessatLlinatge2'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('activitat.interessatLlinatge2'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_interessatLlinatge2').on('click', function(){
					var valor = ($('#dropdownMenuContainer_interessatLlinatge2').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_interessatLlinatge2').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ActivitatFields.INTERESSATTIPUS)}">
        <tr id="activitat_interessatTipus_rowid">
          <td id="activitat_interessatTipus_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ActivitatFields.INTERESSATTIPUS])?'activitat.interessatTipus':__theForm.labels[ActivitatFields.INTERESSATTIPUS]}" />
             </label>
              <c:if test="${not empty __theForm.help[ActivitatFields.INTERESSATTIPUS]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ActivitatFields.INTERESSATTIPUS]}" ></i>
              </c:if>
            </td>
          <td id="activitat_interessatTipus_columnvalueid">
            <form:errors path="activitat.interessatTipus" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,ActivitatFields.INTERESSATTIPUS)? 'true' : 'false'}" cssClass="w-25 form-control  ${gen:contains(__theForm.readOnlyFields ,ActivitatFields.INTERESSATTIPUS)? ' uneditable-input' : ''}"  style=""  path="activitat.interessatTipus"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ActivitatFields.INTERESSATIDENTIFICACIO)}">
        <tr id="activitat_interessatIdentificacio_rowid">
          <td id="activitat_interessatIdentificacio_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ActivitatFields.INTERESSATIDENTIFICACIO])?'activitat.interessatIdentificacio':__theForm.labels[ActivitatFields.INTERESSATIDENTIFICACIO]}" />
             </label>
              <c:if test="${not empty __theForm.help[ActivitatFields.INTERESSATIDENTIFICACIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ActivitatFields.INTERESSATIDENTIFICACIO]}" ></i>
              </c:if>
            </td>
          <td id="activitat_interessatIdentificacio_columnvalueid">
            <form:errors path="activitat.interessatIdentificacio" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,ActivitatFields.INTERESSATIDENTIFICACIO)? 'true' : 'false'}" cssClass="w-75 form-control  ${gen:contains(__theForm.readOnlyFields ,ActivitatFields.INTERESSATIDENTIFICACIO)? ' uneditable-input' : ''}"  style="" maxlength="50" path="activitat.interessatIdentificacio"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ActivitatFields.REPRESENTANTNOM)}">
        <tr id="activitat_representantNom_rowid">
          <td id="activitat_representantNom_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ActivitatFields.REPRESENTANTNOM])?'activitat.representantNom':__theForm.labels[ActivitatFields.REPRESENTANTNOM]}" />
             </label>
              <c:if test="${not empty __theForm.help[ActivitatFields.REPRESENTANTNOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ActivitatFields.REPRESENTANTNOM]}" ></i>
              </c:if>
            </td>
          <td id="activitat_representantNom_columnvalueid">
              <form:errors path="activitat.representantNom" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,ActivitatFields.REPRESENTANTNOM)? 'true' : 'false'}" path="activitat.representantNom"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_representantNom" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_representantNom" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('activitat.representantNom'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('activitat.representantNom'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('activitat.representantNom'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_representantNom').on('click', function(){
					var valor = ($('#dropdownMenuContainer_representantNom').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_representantNom').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ActivitatFields.REPRESENTANTLLINATGE1)}">
        <tr id="activitat_representantLlinatge1_rowid">
          <td id="activitat_representantLlinatge1_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ActivitatFields.REPRESENTANTLLINATGE1])?'activitat.representantLlinatge1':__theForm.labels[ActivitatFields.REPRESENTANTLLINATGE1]}" />
             </label>
              <c:if test="${not empty __theForm.help[ActivitatFields.REPRESENTANTLLINATGE1]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ActivitatFields.REPRESENTANTLLINATGE1]}" ></i>
              </c:if>
            </td>
          <td id="activitat_representantLlinatge1_columnvalueid">
              <form:errors path="activitat.representantLlinatge1" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,ActivitatFields.REPRESENTANTLLINATGE1)? 'true' : 'false'}" path="activitat.representantLlinatge1"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_representantLlinatge1" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_representantLlinatge1" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('activitat.representantLlinatge1'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('activitat.representantLlinatge1'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('activitat.representantLlinatge1'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_representantLlinatge1').on('click', function(){
					var valor = ($('#dropdownMenuContainer_representantLlinatge1').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_representantLlinatge1').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ActivitatFields.REPRESENTANTLLINATGE2)}">
        <tr id="activitat_representantLlinatge2_rowid">
          <td id="activitat_representantLlinatge2_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ActivitatFields.REPRESENTANTLLINATGE2])?'activitat.representantLlinatge2':__theForm.labels[ActivitatFields.REPRESENTANTLLINATGE2]}" />
             </label>
              <c:if test="${not empty __theForm.help[ActivitatFields.REPRESENTANTLLINATGE2]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ActivitatFields.REPRESENTANTLLINATGE2]}" ></i>
              </c:if>
            </td>
          <td id="activitat_representantLlinatge2_columnvalueid">
              <form:errors path="activitat.representantLlinatge2" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,ActivitatFields.REPRESENTANTLLINATGE2)? 'true' : 'false'}" path="activitat.representantLlinatge2"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_representantLlinatge2" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_representantLlinatge2" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('activitat.representantLlinatge2'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('activitat.representantLlinatge2'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('activitat.representantLlinatge2'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_representantLlinatge2').on('click', function(){
					var valor = ($('#dropdownMenuContainer_representantLlinatge2').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_representantLlinatge2').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ActivitatFields.REPRESENTANTTIPUS)}">
        <tr id="activitat_representantTipus_rowid">
          <td id="activitat_representantTipus_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ActivitatFields.REPRESENTANTTIPUS])?'activitat.representantTipus':__theForm.labels[ActivitatFields.REPRESENTANTTIPUS]}" />
             </label>
              <c:if test="${not empty __theForm.help[ActivitatFields.REPRESENTANTTIPUS]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ActivitatFields.REPRESENTANTTIPUS]}" ></i>
              </c:if>
            </td>
          <td id="activitat_representantTipus_columnvalueid">
            <form:errors path="activitat.representantTipus" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,ActivitatFields.REPRESENTANTTIPUS)? 'true' : 'false'}" cssClass="w-25 form-control  ${gen:contains(__theForm.readOnlyFields ,ActivitatFields.REPRESENTANTTIPUS)? ' uneditable-input' : ''}"  style=""  path="activitat.representantTipus"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ActivitatFields.REPRESENTANTIDENTIFICACIO)}">
        <tr id="activitat_representantIdentificacio_rowid">
          <td id="activitat_representantIdentificacio_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ActivitatFields.REPRESENTANTIDENTIFICACIO])?'activitat.representantIdentificacio':__theForm.labels[ActivitatFields.REPRESENTANTIDENTIFICACIO]}" />
             </label>
              <c:if test="${not empty __theForm.help[ActivitatFields.REPRESENTANTIDENTIFICACIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ActivitatFields.REPRESENTANTIDENTIFICACIO]}" ></i>
              </c:if>
            </td>
          <td id="activitat_representantIdentificacio_columnvalueid">
            <form:errors path="activitat.representantIdentificacio" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,ActivitatFields.REPRESENTANTIDENTIFICACIO)? 'true' : 'false'}" cssClass="w-75 form-control  ${gen:contains(__theForm.readOnlyFields ,ActivitatFields.REPRESENTANTIDENTIFICACIO)? ' uneditable-input' : ''}"  style="" maxlength="50" path="activitat.representantIdentificacio"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ActivitatFields.TRAMITVERSIO)}">
        <tr id="activitat_tramitVersio_rowid">
          <td id="activitat_tramitVersio_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ActivitatFields.TRAMITVERSIO])?'activitat.tramitVersio':__theForm.labels[ActivitatFields.TRAMITVERSIO]}" />
             </label>
              <c:if test="${not empty __theForm.help[ActivitatFields.TRAMITVERSIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ActivitatFields.TRAMITVERSIO]}" ></i>
              </c:if>
            </td>
          <td id="activitat_tramitVersio_columnvalueid">
            <form:errors path="activitat.tramitVersio" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,ActivitatFields.TRAMITVERSIO)? 'true' : 'false'}" cssClass="w-25 form-control  ${gen:contains(__theForm.readOnlyFields ,ActivitatFields.TRAMITVERSIO)? ' uneditable-input' : ''}"  style=""  path="activitat.tramitVersio"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ActivitatFields.ARXIUDOCUMENTID)}">
        <tr id="activitat_arxiuDocumentID_rowid">
          <td id="activitat_arxiuDocumentID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ActivitatFields.ARXIUDOCUMENTID])?'activitat.arxiuDocumentID':__theForm.labels[ActivitatFields.ARXIUDOCUMENTID]}" />
             </label>
              <c:if test="${not empty __theForm.help[ActivitatFields.ARXIUDOCUMENTID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ActivitatFields.ARXIUDOCUMENTID]}" ></i>
              </c:if>
            </td>
          <td id="activitat_arxiuDocumentID_columnvalueid">
              <form:errors path="activitat.arxiuDocumentID" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,ActivitatFields.ARXIUDOCUMENTID)? 'true' : 'false'}" path="activitat.arxiuDocumentID"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_arxiuDocumentID" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_arxiuDocumentID" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('activitat.arxiuDocumentID'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('activitat.arxiuDocumentID'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('activitat.arxiuDocumentID'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_arxiuDocumentID').on('click', function(){
					var valor = ($('#dropdownMenuContainer_arxiuDocumentID').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_arxiuDocumentID').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ActivitatFields.ARXIUEXPEDIENTID)}">
        <tr id="activitat_arxiuExpedientID_rowid">
          <td id="activitat_arxiuExpedientID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ActivitatFields.ARXIUEXPEDIENTID])?'activitat.arxiuExpedientID':__theForm.labels[ActivitatFields.ARXIUEXPEDIENTID]}" />
             </label>
              <c:if test="${not empty __theForm.help[ActivitatFields.ARXIUEXPEDIENTID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ActivitatFields.ARXIUEXPEDIENTID]}" ></i>
              </c:if>
            </td>
          <td id="activitat_arxiuExpedientID_columnvalueid">
              <form:errors path="activitat.arxiuExpedientID" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,ActivitatFields.ARXIUEXPEDIENTID)? 'true' : 'false'}" path="activitat.arxiuExpedientID"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_arxiuExpedientID" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_arxiuExpedientID" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('activitat.arxiuExpedientID'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('activitat.arxiuExpedientID'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('activitat.arxiuExpedientID'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_arxiuExpedientID').on('click', function(){
					var valor = ($('#dropdownMenuContainer_arxiuExpedientID').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_arxiuExpedientID').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ActivitatFields.ESTAT)}">
        <tr id="activitat_estat_rowid">
          <td id="activitat_estat_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ActivitatFields.ESTAT])?'activitat.estat':__theForm.labels[ActivitatFields.ESTAT]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[ActivitatFields.ESTAT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ActivitatFields.ESTAT]}" ></i>
              </c:if>
            </td>
          <td id="activitat_estat_columnvalueid">
          <form:errors path="activitat.estat" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,ActivitatFields.ESTAT)}" >
          <form:hidden path="activitat.estat"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.activitat.estat,__theForm.listOfValuesForEstat)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,ActivitatFields.ESTAT)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="activitat_estat"  onchange="if(typeof onChangeEstat == 'function') {  onChangeEstat(this); };"  cssClass="form-control col-md-9-optional" path="activitat.estat">
            <c:forEach items="${__theForm.listOfValuesForEstat}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ActivitatFields.URL)}">
        <tr id="activitat_url_rowid">
          <td id="activitat_url_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ActivitatFields.URL])?'activitat.url':__theForm.labels[ActivitatFields.URL]}" />
             </label>
              <c:if test="${not empty __theForm.help[ActivitatFields.URL]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ActivitatFields.URL]}" ></i>
              </c:if>
            </td>
          <td id="activitat_url_columnvalueid">
              <form:errors path="activitat.url" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,ActivitatFields.URL)? 'true' : 'false'}" path="activitat.url"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_url" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_url" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('activitat.url'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('activitat.url'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('activitat.url'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_url').on('click', function(){
					var valor = ($('#dropdownMenuContainer_url').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_url').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ActivitatFields.DATAACTIVITAT)}">
        <tr id="activitat_dataActivitat_rowid">
          <td id="activitat_dataActivitat_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ActivitatFields.DATAACTIVITAT])?'activitat.dataActivitat':__theForm.labels[ActivitatFields.DATAACTIVITAT]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[ActivitatFields.DATAACTIVITAT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ActivitatFields.DATAACTIVITAT]}" ></i>
              </c:if>
            </td>
          <td id="activitat_dataActivitat_columnvalueid">
    <form:errors path="activitat.dataActivitat" cssClass="errorField alert alert-danger" />
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="activitat_dataActivitat" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,ActivitatFields.DATAACTIVITAT)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#activitat_dataActivitat" path="activitat.dataActivitat" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,ActivitatFields.DATAACTIVITAT)}" >
                    <div class="input-group-append"  data-target="#activitat_dataActivitat"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#activitat_dataActivitat').datetimepicker({
                    format: '${gen:getJSDateTimePattern()}',
                    locale: '${lang}',
                    icons: {
                       time: 'far fa-clock'
                    }
                });
            });
        </script>           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ActivitatFields.IDACTUACIOTRAMIT)}">
        <tr id="activitat_idActuacioTramit_rowid">
          <td id="activitat_idActuacioTramit_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ActivitatFields.IDACTUACIOTRAMIT])?'activitat.idActuacioTramit':__theForm.labels[ActivitatFields.IDACTUACIOTRAMIT]}" />
             </label>
              <c:if test="${not empty __theForm.help[ActivitatFields.IDACTUACIOTRAMIT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ActivitatFields.IDACTUACIOTRAMIT]}" ></i>
              </c:if>
            </td>
          <td id="activitat_idActuacioTramit_columnvalueid">
            <form:errors path="activitat.idActuacioTramit" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,ActivitatFields.IDACTUACIOTRAMIT)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,ActivitatFields.IDACTUACIOTRAMIT)? ' uneditable-input' : ''}"  style="" maxlength="255" path="activitat.idActuacioTramit"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ActivitatFields.PROCEDIMENT)}">
        <tr id="activitat_procediment_rowid">
          <td id="activitat_procediment_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ActivitatFields.PROCEDIMENT])?'activitat.procediment':__theForm.labels[ActivitatFields.PROCEDIMENT]}" />
             </label>
              <c:if test="${not empty __theForm.help[ActivitatFields.PROCEDIMENT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ActivitatFields.PROCEDIMENT]}" ></i>
              </c:if>
            </td>
          <td id="activitat_procediment_columnvalueid">
            <form:errors path="activitat.procediment" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,ActivitatFields.PROCEDIMENT)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,ActivitatFields.PROCEDIMENT)? ' uneditable-input' : ''}"  style="" maxlength="150" path="activitat.procediment"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ActivitatFields.ARXIUREINTENTS)}">
        <tr id="activitat_arxiuReintents_rowid">
          <td id="activitat_arxiuReintents_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ActivitatFields.ARXIUREINTENTS])?'activitat.arxiuReintents':__theForm.labels[ActivitatFields.ARXIUREINTENTS]}" />
             </label>
              <c:if test="${not empty __theForm.help[ActivitatFields.ARXIUREINTENTS]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ActivitatFields.ARXIUREINTENTS]}" ></i>
              </c:if>
            </td>
          <td id="activitat_arxiuReintents_columnvalueid">
            <form:errors path="activitat.arxiuReintents" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,ActivitatFields.ARXIUREINTENTS)? 'true' : 'false'}" cssClass="w-25 form-control  ${gen:contains(__theForm.readOnlyFields ,ActivitatFields.ARXIUREINTENTS)? ' uneditable-input' : ''}"  style=""  path="activitat.arxiuReintents"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ActivitatFields.ARXIUESTAT)}">
        <tr id="activitat_arxiuEstat_rowid">
          <td id="activitat_arxiuEstat_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ActivitatFields.ARXIUESTAT])?'activitat.arxiuEstat':__theForm.labels[ActivitatFields.ARXIUESTAT]}" />
             </label>
              <c:if test="${not empty __theForm.help[ActivitatFields.ARXIUESTAT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ActivitatFields.ARXIUESTAT]}" ></i>
              </c:if>
            </td>
          <td id="activitat_arxiuEstat_columnvalueid">
            <form:errors path="activitat.arxiuEstat" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,ActivitatFields.ARXIUESTAT)? 'true' : 'false'}" cssClass="w-25 form-control  ${gen:contains(__theForm.readOnlyFields ,ActivitatFields.ARXIUESTAT)? ' uneditable-input' : ''}"  style=""  path="activitat.arxiuEstat"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ActivitatFields.ARXIUDARRERINTENT)}">
        <tr id="activitat_arxiuDarrerIntent_rowid">
          <td id="activitat_arxiuDarrerIntent_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[ActivitatFields.ARXIUDARRERINTENT])?'activitat.arxiuDarrerIntent':__theForm.labels[ActivitatFields.ARXIUDARRERINTENT]}" />
             </label>
              <c:if test="${not empty __theForm.help[ActivitatFields.ARXIUDARRERINTENT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ActivitatFields.ARXIUDARRERINTENT]}" ></i>
              </c:if>
            </td>
          <td id="activitat_arxiuDarrerIntent_columnvalueid">
    <form:errors path="activitat.arxiuDarrerIntent" cssClass="errorField alert alert-danger" />
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="activitat_arxiuDarrerIntent" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,ActivitatFields.ARXIUDARRERINTENT)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#activitat_arxiuDarrerIntent" path="activitat.arxiuDarrerIntent" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,ActivitatFields.ARXIUDARRERINTENT)}" >
                    <div class="input-group-append"  data-target="#activitat_arxiuDarrerIntent"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#activitat_arxiuDarrerIntent').datetimepicker({
                    format: '${gen:getJSDateTimePattern()}',
                    locale: '${lang}',
                    icons: {
                       time: 'far fa-clock'
                    }
                });
            });
        </script>           </td>
        </tr>
        </c:if>
        
