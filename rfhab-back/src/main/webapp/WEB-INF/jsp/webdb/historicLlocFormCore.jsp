<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="HistoricLlocFields" className="es.caib.rfhab.model.fields.HistoricLlocFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,HistoricLlocFields.LLOCID)}">
        <tr id="historicLloc_llocID_rowid">
          <td id="historicLloc_llocID_columnlabelid">
            <label for="historicLloc.llocID">
              <fmt:message key="${(empty __theForm.labels[HistoricLlocFields.LLOCID])?'historicLloc.llocID':__theForm.labels[HistoricLlocFields.LLOCID]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[HistoricLlocFields.LLOCID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[HistoricLlocFields.LLOCID]}" ></i>
              </c:if>
            </td>
          <td id="historicLloc_llocID_columnvalueid">
          <form:errors path="historicLloc.llocID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,HistoricLlocFields.LLOCID)}" >
          <form:hidden path="historicLloc.llocID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.historicLloc.llocID,__theForm.listOfLlocForLlocID)}" aria-label="historicLloc.llocID"   required aria-required="true"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,HistoricLlocFields.LLOCID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select path="historicLloc.llocID" cssClass="form-control col-md-9-optional"  required="required"  aria-required="true"  onchange="if(typeof onChangeLlocID == 'function') {  onChangeLlocID(this); };">


            <c:forEach items="${__theForm.listOfLlocForLlocID}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,HistoricLlocFields.NUMEROCAI)}">
        <tr id="historicLloc_numeroCai_rowid">
          <td id="historicLloc_numeroCai_columnlabelid">
            <label for="historicLloc.numeroCai">
              <fmt:message key="${(empty __theForm.labels[HistoricLlocFields.NUMEROCAI])?'historicLloc.numeroCai':__theForm.labels[HistoricLlocFields.NUMEROCAI]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[HistoricLlocFields.NUMEROCAI]}">
              <i class="fas fa-info-circle" title="${__theForm.help[HistoricLlocFields.NUMEROCAI]}" ></i>
              </c:if>
            </td>
          <td id="historicLloc_numeroCai_columnvalueid">
            <form:errors path="historicLloc.numeroCai" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,HistoricLlocFields.NUMEROCAI)? 'readonly' : ''}" cssClass="w-75 form-control  ${gen:contains(__theForm.readOnlyFields ,HistoricLlocFields.NUMEROCAI)? ' uneditable-input' : ''}"  style="" maxlength="50" path="historicLloc.numeroCai"  required="required"  aria-required="true"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,HistoricLlocFields.OBSERVACIONS)}">
        <tr id="historicLloc_observacions_rowid">
          <td id="historicLloc_observacions_columnlabelid">
            <label for="historicLloc.observacions">
              <fmt:message key="${(empty __theForm.labels[HistoricLlocFields.OBSERVACIONS])?'historicLloc.observacions':__theForm.labels[HistoricLlocFields.OBSERVACIONS]}" />
             </label>
              <c:if test="${not empty __theForm.help[HistoricLlocFields.OBSERVACIONS]}">
              <i class="fas fa-info-circle" title="${__theForm.help[HistoricLlocFields.OBSERVACIONS]}" ></i>
              </c:if>
            </td>
          <td id="historicLloc_observacions_columnvalueid">
              <form:errors path="historicLloc.observacions" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,HistoricLlocFields.OBSERVACIONS)? 'readonly' : ''}" path="historicLloc.observacions"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_observacions" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;" aria-label="Opcions de format"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_observacions" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('historicLloc.observacions'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('historicLloc.observacions'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('historicLloc.observacions'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script>
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,HistoricLlocFields.DATACREACIO)}">
        <tr id="historicLloc_dataCreacio_rowid">
          <td id="historicLloc_dataCreacio_columnlabelid">
            <label for="historicLloc.dataCreacio">
              <fmt:message key="${(empty __theForm.labels[HistoricLlocFields.DATACREACIO])?'historicLloc.dataCreacio':__theForm.labels[HistoricLlocFields.DATACREACIO]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[HistoricLlocFields.DATACREACIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[HistoricLlocFields.DATACREACIO]}" ></i>
              </c:if>
            </td>
          <td id="historicLloc_dataCreacio_columnvalueid">
    <form:errors path="historicLloc.dataCreacio" cssClass="errorField alert alert-danger" />
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="historicLloc_dataCreacioDiv" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,HistoricLlocFields.DATACREACIO)? 'readonly' : ''}" cssClass="form-control datetimepicker-input"  data-target="#historicLloc_dataCreacioDiv" path="historicLloc.dataCreacio"   required="required"  aria-required="true"  />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,HistoricLlocFields.DATACREACIO)}" >
                    <div class="input-group-append"  data-target="#historicLloc_dataCreacioDiv"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script>
            $(function () {
                $('#historicLloc_dataCreacioDiv').datetimepicker({
                    format: '${gen:getJSDateTimePattern()}',
                    locale: '${lang}',
                    icons: {
                       time: 'far fa-clock'
                    }
                });
            });
        </script>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,HistoricLlocFields.USUARIID)}">
        <tr id="historicLloc_usuariID_rowid">
          <td id="historicLloc_usuariID_columnlabelid">
            <label for="historicLloc.usuariID">
              <fmt:message key="${(empty __theForm.labels[HistoricLlocFields.USUARIID])?'historicLloc.usuariID':__theForm.labels[HistoricLlocFields.USUARIID]}" />
             </label>
              <c:if test="${not empty __theForm.help[HistoricLlocFields.USUARIID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[HistoricLlocFields.USUARIID]}" ></i>
              </c:if>
            </td>
          <td id="historicLloc_usuariID_columnvalueid">
          <form:errors path="historicLloc.usuariID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,HistoricLlocFields.USUARIID)}" >
          <form:hidden path="historicLloc.usuariID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.historicLloc.usuariID,__theForm.listOfUsuariForUsuariID)}" aria-label="historicLloc.usuariID"   />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,HistoricLlocFields.USUARIID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select path="historicLloc.usuariID" cssClass="form-control col-md-9-optional"  onchange="if(typeof onChangeUsuariID == 'function') {  onChangeUsuariID(this); };">


            <c:forEach items="${__theForm.listOfUsuariForUsuariID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>

                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.historicLloc.usuariID }">
                  <option value="" label=" " selected></option>

              </c:if>
              <c:if test="${not empty __theForm.historicLloc.usuariID }">

                  <option value="" label=" "></option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
