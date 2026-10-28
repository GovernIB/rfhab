<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="FuncionariLlocFields" className="es.caib.rfhab.model.fields.FuncionariLlocFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,FuncionariLlocFields.LLOCID)}">
        <tr id="funcionariLloc_llocID_rowid">
          <td id="funcionariLloc_llocID_columnlabelid">
            <label for="funcionariLloc.llocID">
              <fmt:message key="${(empty __theForm.labels[FuncionariLlocFields.LLOCID])?'funcionariLloc.llocID':__theForm.labels[FuncionariLlocFields.LLOCID]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[FuncionariLlocFields.LLOCID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[FuncionariLlocFields.LLOCID]}" ></i>
              </c:if>
            </td>
          <td id="funcionariLloc_llocID_columnvalueid">
          <form:errors path="funcionariLloc.llocID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,FuncionariLlocFields.LLOCID)}" >
          <form:hidden path="funcionariLloc.llocID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.funcionariLloc.llocID,__theForm.listOfLlocForLlocID)}" aria-label="funcionariLloc.llocID"   required aria-required="true"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,FuncionariLlocFields.LLOCID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select path="funcionariLloc.llocID" cssClass="form-control col-md-9-optional"  required="required"  aria-required="true"  onchange="if(typeof onChangeLlocID == 'function') {  onChangeLlocID(this); };">


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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FuncionariLlocFields.FUNCIONARIID)}">
        <tr id="funcionariLloc_funcionariID_rowid">
          <td id="funcionariLloc_funcionariID_columnlabelid">
            <label for="funcionariLloc.funcionariID">
              <fmt:message key="${(empty __theForm.labels[FuncionariLlocFields.FUNCIONARIID])?'funcionariLloc.funcionariID':__theForm.labels[FuncionariLlocFields.FUNCIONARIID]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[FuncionariLlocFields.FUNCIONARIID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[FuncionariLlocFields.FUNCIONARIID]}" ></i>
              </c:if>
            </td>
          <td id="funcionariLloc_funcionariID_columnvalueid">
          <form:errors path="funcionariLloc.funcionariID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,FuncionariLlocFields.FUNCIONARIID)}" >
          <form:hidden path="funcionariLloc.funcionariID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.funcionariLloc.funcionariID,__theForm.listOfFuncionariForFuncionariID)}" aria-label="funcionariLloc.funcionariID"   required aria-required="true"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,FuncionariLlocFields.FUNCIONARIID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select path="funcionariLloc.funcionariID" cssClass="form-control col-md-9-optional"  required="required"  aria-required="true"  onchange="if(typeof onChangeFuncionariID == 'function') {  onChangeFuncionariID(this); };">


            <c:forEach items="${__theForm.listOfFuncionariForFuncionariID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>

                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
          </form:select>
          <script>
              $(document).ready(function() {
                  $('#funcionariLloc\\.funcionariID').select2();
              });
          </script>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FuncionariLlocFields.DATAINICI)}">
        <tr id="funcionariLloc_dataInici_rowid">
          <td id="funcionariLloc_dataInici_columnlabelid">
            <label for="funcionariLloc.dataInici">
              <fmt:message key="${(empty __theForm.labels[FuncionariLlocFields.DATAINICI])?'funcionariLloc.dataInici':__theForm.labels[FuncionariLlocFields.DATAINICI]}" />
             </label>
              <c:if test="${not empty __theForm.help[FuncionariLlocFields.DATAINICI]}">
              <i class="fas fa-info-circle" title="${__theForm.help[FuncionariLlocFields.DATAINICI]}" ></i>
              </c:if>
            </td>
          <td id="funcionariLloc_dataInici_columnvalueid">
    <form:errors path="funcionariLloc.dataInici" cssClass="errorField alert alert-danger" />
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="funcionariLloc_dataIniciDiv" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,FuncionariLlocFields.DATAINICI)? 'readonly' : ''}" cssClass="form-control datetimepicker-input"  data-target="#funcionariLloc_dataIniciDiv" path="funcionariLloc.dataInici"   />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,FuncionariLlocFields.DATAINICI)}" >
                    <div class="input-group-append"  data-target="#funcionariLloc_dataIniciDiv"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script>
            $(function () {
                $('#funcionariLloc_dataIniciDiv').datetimepicker({
                    format: '${gen:getJSDatePattern()}',
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FuncionariLlocFields.DATAFI)}">
        <tr id="funcionariLloc_dataFi_rowid">
          <td id="funcionariLloc_dataFi_columnlabelid">
            <label for="funcionariLloc.dataFi">
              <fmt:message key="${(empty __theForm.labels[FuncionariLlocFields.DATAFI])?'funcionariLloc.dataFi':__theForm.labels[FuncionariLlocFields.DATAFI]}" />
             </label>
              <c:if test="${not empty __theForm.help[FuncionariLlocFields.DATAFI]}">
              <i class="fas fa-info-circle" title="${__theForm.help[FuncionariLlocFields.DATAFI]}" ></i>
              </c:if>
            </td>
          <td id="funcionariLloc_dataFi_columnvalueid">
    <form:errors path="funcionariLloc.dataFi" cssClass="errorField alert alert-danger" />
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="funcionariLloc_dataFiDiv" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,FuncionariLlocFields.DATAFI)? 'readonly' : ''}" cssClass="form-control datetimepicker-input"  data-target="#funcionariLloc_dataFiDiv" path="funcionariLloc.dataFi"   />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,FuncionariLlocFields.DATAFI)}" >
                    <div class="input-group-append"  data-target="#funcionariLloc_dataFiDiv"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script>
            $(function () {
                $('#funcionariLloc_dataFiDiv').datetimepicker({
                    format: '${gen:getJSDatePattern()}',
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FuncionariLlocFields.DATACREACIO)}">
        <tr id="funcionariLloc_dataCreacio_rowid">
          <td id="funcionariLloc_dataCreacio_columnlabelid">
            <label for="funcionariLloc.dataCreacio">
              <fmt:message key="${(empty __theForm.labels[FuncionariLlocFields.DATACREACIO])?'funcionariLloc.dataCreacio':__theForm.labels[FuncionariLlocFields.DATACREACIO]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[FuncionariLlocFields.DATACREACIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[FuncionariLlocFields.DATACREACIO]}" ></i>
              </c:if>
            </td>
          <td id="funcionariLloc_dataCreacio_columnvalueid">
    <form:errors path="funcionariLloc.dataCreacio" cssClass="errorField alert alert-danger" />
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="funcionariLloc_dataCreacioDiv" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,FuncionariLlocFields.DATACREACIO)? 'readonly' : ''}" cssClass="form-control datetimepicker-input"  data-target="#funcionariLloc_dataCreacioDiv" path="funcionariLloc.dataCreacio"   required="required"  aria-required="true"  />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,FuncionariLlocFields.DATACREACIO)}" >
                    <div class="input-group-append"  data-target="#funcionariLloc_dataCreacioDiv"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script>
            $(function () {
                $('#funcionariLloc_dataCreacioDiv').datetimepicker({
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FuncionariLlocFields.USUARIID)}">
        <tr id="funcionariLloc_usuariID_rowid">
          <td id="funcionariLloc_usuariID_columnlabelid">
            <label for="funcionariLloc.usuariID">
              <fmt:message key="${(empty __theForm.labels[FuncionariLlocFields.USUARIID])?'funcionariLloc.usuariID':__theForm.labels[FuncionariLlocFields.USUARIID]}" />
             </label>
              <c:if test="${not empty __theForm.help[FuncionariLlocFields.USUARIID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[FuncionariLlocFields.USUARIID]}" ></i>
              </c:if>
            </td>
          <td id="funcionariLloc_usuariID_columnvalueid">
          <form:errors path="funcionariLloc.usuariID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,FuncionariLlocFields.USUARIID)}" >
          <form:hidden path="funcionariLloc.usuariID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.funcionariLloc.usuariID,__theForm.listOfUsuariForUsuariID)}" aria-label="funcionariLloc.usuariID"   />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,FuncionariLlocFields.USUARIID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select path="funcionariLloc.usuariID" cssClass="form-control col-md-9-optional"  onchange="if(typeof onChangeUsuariID == 'function') {  onChangeUsuariID(this); };">


            <c:forEach items="${__theForm.listOfUsuariForUsuariID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>

                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.funcionariLloc.usuariID }">
                  <option value="" selected></option>

              </c:if>
              <c:if test="${not empty __theForm.funcionariLloc.usuariID }">

                  <option value="" ></option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FuncionariLlocFields.NUMEROCAI)}">
        <tr id="funcionariLloc_numeroCai_rowid">
          <td id="funcionariLloc_numeroCai_columnlabelid">
            <label for="funcionariLloc.numeroCai">
              <fmt:message key="${(empty __theForm.labels[FuncionariLlocFields.NUMEROCAI])?'funcionariLloc.numeroCai':__theForm.labels[FuncionariLlocFields.NUMEROCAI]}" />
             </label>
              <c:if test="${not empty __theForm.help[FuncionariLlocFields.NUMEROCAI]}">
              <i class="fas fa-info-circle" title="${__theForm.help[FuncionariLlocFields.NUMEROCAI]}" ></i>
              </c:if>
            </td>
          <td id="funcionariLloc_numeroCai_columnvalueid">
            <form:errors path="funcionariLloc.numeroCai" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,FuncionariLlocFields.NUMEROCAI)? 'readonly' : ''}" cssClass="w-75 form-control  ${gen:contains(__theForm.readOnlyFields ,FuncionariLlocFields.NUMEROCAI)? ' uneditable-input' : ''}"  style="" maxlength="50" path="funcionariLloc.numeroCai"   />

           </td>
        </tr>
        </c:if>
        
