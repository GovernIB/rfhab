<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="SincroUnitatsFields" className="es.caib.rfhab.model.fields.SincroUnitatsFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,SincroUnitatsFields.DATACREACIO)}">
        <tr id="sincroUnitats_dataCreacio_rowid">
          <td id="sincroUnitats_dataCreacio_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SincroUnitatsFields.DATACREACIO])?'sincroUnitats.dataCreacio':__theForm.labels[SincroUnitatsFields.DATACREACIO]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[SincroUnitatsFields.DATACREACIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SincroUnitatsFields.DATACREACIO]}" ></i>
              </c:if>
            </td>
          <td id="sincroUnitats_dataCreacio_columnvalueid">
    <form:errors path="sincroUnitats.dataCreacio" cssClass="errorField alert alert-danger" />
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="sincroUnitats_dataCreacio" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,SincroUnitatsFields.DATACREACIO)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#sincroUnitats_dataCreacio" path="sincroUnitats.dataCreacio" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,SincroUnitatsFields.DATACREACIO)}" >
                    <div class="input-group-append"  data-target="#sincroUnitats_dataCreacio"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#sincroUnitats_dataCreacio').datetimepicker({
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SincroUnitatsFields.DATADARRERASINCRO)}">
        <tr id="sincroUnitats_dataDarreraSincro_rowid">
          <td id="sincroUnitats_dataDarreraSincro_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SincroUnitatsFields.DATADARRERASINCRO])?'sincroUnitats.dataDarreraSincro':__theForm.labels[SincroUnitatsFields.DATADARRERASINCRO]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[SincroUnitatsFields.DATADARRERASINCRO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SincroUnitatsFields.DATADARRERASINCRO]}" ></i>
              </c:if>
            </td>
          <td id="sincroUnitats_dataDarreraSincro_columnvalueid">
    <form:errors path="sincroUnitats.dataDarreraSincro" cssClass="errorField alert alert-danger" />
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="sincroUnitats_dataDarreraSincro" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,SincroUnitatsFields.DATADARRERASINCRO)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#sincroUnitats_dataDarreraSincro" path="sincroUnitats.dataDarreraSincro" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,SincroUnitatsFields.DATADARRERASINCRO)}" >
                    <div class="input-group-append"  data-target="#sincroUnitats_dataDarreraSincro"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#sincroUnitats_dataDarreraSincro').datetimepicker({
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SincroUnitatsFields.DATAPRIMERASINCRO)}">
        <tr id="sincroUnitats_dataPrimeraSincro_rowid">
          <td id="sincroUnitats_dataPrimeraSincro_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SincroUnitatsFields.DATAPRIMERASINCRO])?'sincroUnitats.dataPrimeraSincro':__theForm.labels[SincroUnitatsFields.DATAPRIMERASINCRO]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[SincroUnitatsFields.DATAPRIMERASINCRO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SincroUnitatsFields.DATAPRIMERASINCRO]}" ></i>
              </c:if>
            </td>
          <td id="sincroUnitats_dataPrimeraSincro_columnvalueid">
    <form:errors path="sincroUnitats.dataPrimeraSincro" cssClass="errorField alert alert-danger" />
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="sincroUnitats_dataPrimeraSincro" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,SincroUnitatsFields.DATAPRIMERASINCRO)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#sincroUnitats_dataPrimeraSincro" path="sincroUnitats.dataPrimeraSincro" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,SincroUnitatsFields.DATAPRIMERASINCRO)}" >
                    <div class="input-group-append"  data-target="#sincroUnitats_dataPrimeraSincro"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#sincroUnitats_dataPrimeraSincro').datetimepicker({
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SincroUnitatsFields.CODIENTITAT)}">
        <tr id="sincroUnitats_codiEntitat_rowid">
          <td id="sincroUnitats_codiEntitat_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SincroUnitatsFields.CODIENTITAT])?'sincroUnitats.codiEntitat':__theForm.labels[SincroUnitatsFields.CODIENTITAT]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[SincroUnitatsFields.CODIENTITAT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SincroUnitatsFields.CODIENTITAT]}" ></i>
              </c:if>
            </td>
          <td id="sincroUnitats_codiEntitat_columnvalueid">
            <form:errors path="sincroUnitats.codiEntitat" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,SincroUnitatsFields.CODIENTITAT)? 'true' : 'false'}" cssClass="w-75 form-control  ${gen:contains(__theForm.readOnlyFields ,SincroUnitatsFields.CODIENTITAT)? ' uneditable-input' : ''}"  style="" maxlength="50" path="sincroUnitats.codiEntitat"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SincroUnitatsFields.OBSERVACIONS)}">
        <tr id="sincroUnitats_observacions_rowid">
          <td id="sincroUnitats_observacions_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SincroUnitatsFields.OBSERVACIONS])?'sincroUnitats.observacions':__theForm.labels[SincroUnitatsFields.OBSERVACIONS]}" />
             </label>
              <c:if test="${not empty __theForm.help[SincroUnitatsFields.OBSERVACIONS]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SincroUnitatsFields.OBSERVACIONS]}" ></i>
              </c:if>
            </td>
          <td id="sincroUnitats_observacions_columnvalueid">
              <form:errors path="sincroUnitats.observacions" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,SincroUnitatsFields.OBSERVACIONS)? 'true' : 'false'}" path="sincroUnitats.observacions"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_observacions" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_observacions" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('sincroUnitats.observacions'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('sincroUnitats.observacions'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('sincroUnitats.observacions'); ta.wrap='hard';">Hard Wrap</a>
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SincroUnitatsFields.USUARIID)}">
        <tr id="sincroUnitats_usuariId_rowid">
          <td id="sincroUnitats_usuariId_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[SincroUnitatsFields.USUARIID])?'sincroUnitats.usuariId':__theForm.labels[SincroUnitatsFields.USUARIID]}" />
             </label>
              <c:if test="${not empty __theForm.help[SincroUnitatsFields.USUARIID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SincroUnitatsFields.USUARIID]}" ></i>
              </c:if>
            </td>
          <td id="sincroUnitats_usuariId_columnvalueid">
          <form:errors path="sincroUnitats.usuariId" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,SincroUnitatsFields.USUARIID)}" >
          <form:hidden path="sincroUnitats.usuariId"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.sincroUnitats.usuariId,__theForm.listOfUsuariForUsuariId)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,SincroUnitatsFields.USUARIID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="sincroUnitats_usuariId"  onchange="if(typeof onChangeUsuariId == 'function') {  onChangeUsuariId(this); };"  cssClass="form-control col-md-9-optional" path="sincroUnitats.usuariId">
            <c:forEach items="${__theForm.listOfUsuariForUsuariId}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.sincroUnitats.usuariId }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.sincroUnitats.usuariId }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
