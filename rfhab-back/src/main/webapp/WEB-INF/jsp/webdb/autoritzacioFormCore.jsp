<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="AutoritzacioFields" className="es.caib.rfhab.model.fields.AutoritzacioFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,AutoritzacioFields.LLOCID)}">
        <tr id="autoritzacio_llocID_rowid">
          <td id="autoritzacio_llocID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[AutoritzacioFields.LLOCID])?'autoritzacio.llocID':__theForm.labels[AutoritzacioFields.LLOCID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[AutoritzacioFields.LLOCID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AutoritzacioFields.LLOCID]}" ></i>
              </c:if>
            </td>
          <td id="autoritzacio_llocID_columnvalueid">
          <form:errors path="autoritzacio.llocID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,AutoritzacioFields.LLOCID)}" >
          <form:hidden path="autoritzacio.llocID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.autoritzacio.llocID,__theForm.listOfLlocForLlocID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,AutoritzacioFields.LLOCID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="autoritzacio_llocID"  onchange="if(typeof onChangeLlocID == 'function') {  onChangeLlocID(this); };"  cssClass="form-control col-md-9-optional" path="autoritzacio.llocID">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AutoritzacioFields.CODISIA)}">
        <tr id="autoritzacio_codiSia_rowid">
          <td id="autoritzacio_codiSia_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[AutoritzacioFields.CODISIA])?'autoritzacio.codiSia':__theForm.labels[AutoritzacioFields.CODISIA]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[AutoritzacioFields.CODISIA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AutoritzacioFields.CODISIA]}" ></i>
              </c:if>
            </td>
          <td id="autoritzacio_codiSia_columnvalueid">
            <form:errors path="autoritzacio.codiSia" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,AutoritzacioFields.CODISIA)? 'true' : 'false'}" cssClass="w-75 form-control  ${gen:contains(__theForm.readOnlyFields ,AutoritzacioFields.CODISIA)? ' uneditable-input' : ''}"  style="" maxlength="50" path="autoritzacio.codiSia"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AutoritzacioFields.PROCEDIMENT)}">
        <tr id="autoritzacio_procediment_rowid">
          <td id="autoritzacio_procediment_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[AutoritzacioFields.PROCEDIMENT])?'autoritzacio.procediment':__theForm.labels[AutoritzacioFields.PROCEDIMENT]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[AutoritzacioFields.PROCEDIMENT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AutoritzacioFields.PROCEDIMENT]}" ></i>
              </c:if>
            </td>
          <td id="autoritzacio_procediment_columnvalueid">
            <form:errors path="autoritzacio.procediment" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,AutoritzacioFields.PROCEDIMENT)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,AutoritzacioFields.PROCEDIMENT)? ' uneditable-input' : ''}"  style="" maxlength="255" path="autoritzacio.procediment"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AutoritzacioFields.CAI)}">
        <tr id="autoritzacio_cai_rowid">
          <td id="autoritzacio_cai_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[AutoritzacioFields.CAI])?'autoritzacio.cai':__theForm.labels[AutoritzacioFields.CAI]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[AutoritzacioFields.CAI]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AutoritzacioFields.CAI]}" ></i>
              </c:if>
            </td>
          <td id="autoritzacio_cai_columnvalueid">
            <form:errors path="autoritzacio.cai" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,AutoritzacioFields.CAI)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,AutoritzacioFields.CAI)? ' uneditable-input' : ''}"  style="" maxlength="255" path="autoritzacio.cai"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AutoritzacioFields.DATAINICI)}">
        <tr id="autoritzacio_dataInici_rowid">
          <td id="autoritzacio_dataInici_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[AutoritzacioFields.DATAINICI])?'autoritzacio.dataInici':__theForm.labels[AutoritzacioFields.DATAINICI]}" />
             </label>
              <c:if test="${not empty __theForm.help[AutoritzacioFields.DATAINICI]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AutoritzacioFields.DATAINICI]}" ></i>
              </c:if>
            </td>
          <td id="autoritzacio_dataInici_columnvalueid">
    <form:errors path="autoritzacio.dataInici" cssClass="errorField alert alert-danger" />
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="autoritzacio_dataInici" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,AutoritzacioFields.DATAINICI)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#autoritzacio_dataInici" path="autoritzacio.dataInici" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,AutoritzacioFields.DATAINICI)}" >
                    <div class="input-group-append"  data-target="#autoritzacio_dataInici"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#autoritzacio_dataInici').datetimepicker({
                    format: '${gen:getJSDatePattern()}',
                    locale: '${lang}',
                    icons: {
                       time: 'far fa-clock'
                    }
                });
            });
        </script>           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AutoritzacioFields.DATAFI)}">
        <tr id="autoritzacio_dataFi_rowid">
          <td id="autoritzacio_dataFi_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[AutoritzacioFields.DATAFI])?'autoritzacio.dataFi':__theForm.labels[AutoritzacioFields.DATAFI]}" />
             </label>
              <c:if test="${not empty __theForm.help[AutoritzacioFields.DATAFI]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AutoritzacioFields.DATAFI]}" ></i>
              </c:if>
            </td>
          <td id="autoritzacio_dataFi_columnvalueid">
    <form:errors path="autoritzacio.dataFi" cssClass="errorField alert alert-danger" />
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="autoritzacio_dataFi" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,AutoritzacioFields.DATAFI)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#autoritzacio_dataFi" path="autoritzacio.dataFi" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,AutoritzacioFields.DATAFI)}" >
                    <div class="input-group-append"  data-target="#autoritzacio_dataFi"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#autoritzacio_dataFi').datetimepicker({
                    format: '${gen:getJSDatePattern()}',
                    locale: '${lang}',
                    icons: {
                       time: 'far fa-clock'
                    }
                });
            });
        </script>           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AutoritzacioFields.DATACREACIO)}">
        <tr id="autoritzacio_dataCreacio_rowid">
          <td id="autoritzacio_dataCreacio_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[AutoritzacioFields.DATACREACIO])?'autoritzacio.dataCreacio':__theForm.labels[AutoritzacioFields.DATACREACIO]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[AutoritzacioFields.DATACREACIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AutoritzacioFields.DATACREACIO]}" ></i>
              </c:if>
            </td>
          <td id="autoritzacio_dataCreacio_columnvalueid">
    <form:errors path="autoritzacio.dataCreacio" cssClass="errorField alert alert-danger" />
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="autoritzacio_dataCreacio" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,AutoritzacioFields.DATACREACIO)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#autoritzacio_dataCreacio" path="autoritzacio.dataCreacio" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,AutoritzacioFields.DATACREACIO)}" >
                    <div class="input-group-append"  data-target="#autoritzacio_dataCreacio"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#autoritzacio_dataCreacio').datetimepicker({
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AutoritzacioFields.OBSERVACIONS)}">
        <tr id="autoritzacio_observacions_rowid">
          <td id="autoritzacio_observacions_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[AutoritzacioFields.OBSERVACIONS])?'autoritzacio.observacions':__theForm.labels[AutoritzacioFields.OBSERVACIONS]}" />
             </label>
              <c:if test="${not empty __theForm.help[AutoritzacioFields.OBSERVACIONS]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AutoritzacioFields.OBSERVACIONS]}" ></i>
              </c:if>
            </td>
          <td id="autoritzacio_observacions_columnvalueid">
              <form:errors path="autoritzacio.observacions" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,AutoritzacioFields.OBSERVACIONS)? 'true' : 'false'}" path="autoritzacio.observacions"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_observacions" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_observacions" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('autoritzacio.observacions'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('autoritzacio.observacions'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('autoritzacio.observacions'); ta.wrap='hard';">Hard Wrap</a>
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AutoritzacioFields.USUARIID)}">
        <tr id="autoritzacio_usuariID_rowid">
          <td id="autoritzacio_usuariID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[AutoritzacioFields.USUARIID])?'autoritzacio.usuariID':__theForm.labels[AutoritzacioFields.USUARIID]}" />
             </label>
              <c:if test="${not empty __theForm.help[AutoritzacioFields.USUARIID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AutoritzacioFields.USUARIID]}" ></i>
              </c:if>
            </td>
          <td id="autoritzacio_usuariID_columnvalueid">
            <form:errors path="autoritzacio.usuariID" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,AutoritzacioFields.USUARIID)? 'true' : 'false'}" cssClass="w-25 form-control  ${gen:contains(__theForm.readOnlyFields ,AutoritzacioFields.USUARIID)? ' uneditable-input' : ''}"  style=""  path="autoritzacio.usuariID"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AutoritzacioFields.FUNCIONARIID)}">
        <tr id="autoritzacio_funcionariID_rowid">
          <td id="autoritzacio_funcionariID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[AutoritzacioFields.FUNCIONARIID])?'autoritzacio.funcionariID':__theForm.labels[AutoritzacioFields.FUNCIONARIID]}" />
             </label>
              <c:if test="${not empty __theForm.help[AutoritzacioFields.FUNCIONARIID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AutoritzacioFields.FUNCIONARIID]}" ></i>
              </c:if>
            </td>
          <td id="autoritzacio_funcionariID_columnvalueid">
          <form:errors path="autoritzacio.funcionariID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,AutoritzacioFields.FUNCIONARIID)}" >
          <form:hidden path="autoritzacio.funcionariID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.autoritzacio.funcionariID,__theForm.listOfFuncionariForFuncionariID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,AutoritzacioFields.FUNCIONARIID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="autoritzacio_funcionariID"  onchange="if(typeof onChangeFuncionariID == 'function') {  onChangeFuncionariID(this); };"  cssClass="form-control col-md-9-optional" path="autoritzacio.funcionariID">
            <c:forEach items="${__theForm.listOfFuncionariForFuncionariID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.autoritzacio.funcionariID }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.autoritzacio.funcionariID }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
