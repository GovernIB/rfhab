<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="HistoricFields" className="es.caib.rfhab.model.fields.HistoricFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,HistoricFields.FUNCIONARIID)}">
        <tr id="historic_funcionariID_rowid">
          <td id="historic_funcionariID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[HistoricFields.FUNCIONARIID])?'historic.funcionariID':__theForm.labels[HistoricFields.FUNCIONARIID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[HistoricFields.FUNCIONARIID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[HistoricFields.FUNCIONARIID]}" ></i>
              </c:if>
            </td>
          <td id="historic_funcionariID_columnvalueid">
          <form:errors path="historic.funcionariID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,HistoricFields.FUNCIONARIID)}" >
          <form:hidden path="historic.funcionariID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.historic.funcionariID,__theForm.listOfFuncionariForFuncionariID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,HistoricFields.FUNCIONARIID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="historic_funcionariID"  onchange="if(typeof onChangeFuncionariID == 'function') {  onChangeFuncionariID(this); };"  cssClass="form-control col-md-9-optional" path="historic.funcionariID">
            <c:forEach items="${__theForm.listOfFuncionariForFuncionariID}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,HistoricFields.NUMEROCAI)}">
        <tr id="historic_numeroCai_rowid">
          <td id="historic_numeroCai_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[HistoricFields.NUMEROCAI])?'historic.numeroCai':__theForm.labels[HistoricFields.NUMEROCAI]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[HistoricFields.NUMEROCAI]}">
              <i class="fas fa-info-circle" title="${__theForm.help[HistoricFields.NUMEROCAI]}" ></i>
              </c:if>
            </td>
          <td id="historic_numeroCai_columnvalueid">
            <form:errors path="historic.numeroCai" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,HistoricFields.NUMEROCAI)? 'true' : 'false'}" cssClass="w-75 form-control  ${gen:contains(__theForm.readOnlyFields ,HistoricFields.NUMEROCAI)? ' uneditable-input' : ''}"  style="" maxlength="50" path="historic.numeroCai"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,HistoricFields.OBSERVACIONS)}">
        <tr id="historic_observacions_rowid">
          <td id="historic_observacions_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[HistoricFields.OBSERVACIONS])?'historic.observacions':__theForm.labels[HistoricFields.OBSERVACIONS]}" />
             </label>
              <c:if test="${not empty __theForm.help[HistoricFields.OBSERVACIONS]}">
              <i class="fas fa-info-circle" title="${__theForm.help[HistoricFields.OBSERVACIONS]}" ></i>
              </c:if>
            </td>
          <td id="historic_observacions_columnvalueid">
              <form:errors path="historic.observacions" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,HistoricFields.OBSERVACIONS)? 'true' : 'false'}" path="historic.observacions"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_observacions" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_observacions" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('historic.observacions'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('historic.observacions'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('historic.observacions'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_observacions').on('click', function(){
					var valor = ($('#dropdownMenuContainer_observacions').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_observacions').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,HistoricFields.DATACREACIO)}">
        <tr id="historic_dataCreacio_rowid">
          <td id="historic_dataCreacio_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[HistoricFields.DATACREACIO])?'historic.dataCreacio':__theForm.labels[HistoricFields.DATACREACIO]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[HistoricFields.DATACREACIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[HistoricFields.DATACREACIO]}" ></i>
              </c:if>
            </td>
          <td id="historic_dataCreacio_columnvalueid">
    <form:errors path="historic.dataCreacio" cssClass="errorField alert alert-danger" />
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="historic_dataCreacio" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,HistoricFields.DATACREACIO)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#historic_dataCreacio" path="historic.dataCreacio" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,HistoricFields.DATACREACIO)}" >
                    <div class="input-group-append"  data-target="#historic_dataCreacio"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#historic_dataCreacio').datetimepicker({
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,HistoricFields.USUARIID)}">
        <tr id="historic_usuariID_rowid">
          <td id="historic_usuariID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[HistoricFields.USUARIID])?'historic.usuariID':__theForm.labels[HistoricFields.USUARIID]}" />
             </label>
              <c:if test="${not empty __theForm.help[HistoricFields.USUARIID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[HistoricFields.USUARIID]}" ></i>
              </c:if>
            </td>
          <td id="historic_usuariID_columnvalueid">
          <form:errors path="historic.usuariID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,HistoricFields.USUARIID)}" >
          <form:hidden path="historic.usuariID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.historic.usuariID,__theForm.listOfUsuariForUsuariID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,HistoricFields.USUARIID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="historic_usuariID"  onchange="if(typeof onChangeUsuariID == 'function') {  onChangeUsuariID(this); };"  cssClass="form-control col-md-9-optional" path="historic.usuariID">
            <c:forEach items="${__theForm.listOfUsuariForUsuariID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.historic.usuariID }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.historic.usuariID }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
