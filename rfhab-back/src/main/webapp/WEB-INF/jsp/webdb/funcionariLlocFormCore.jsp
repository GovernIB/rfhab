<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="FuncionariLlocFields" className="es.caib.rfhab.model.fields.FuncionariLlocFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,FuncionariLlocFields.LLOCID)}">
        <tr id="funcionariLloc_llocID_rowid">
          <td id="funcionariLloc_llocID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[FuncionariLlocFields.LLOCID])?'funcionariLloc.llocID':__theForm.labels[FuncionariLlocFields.LLOCID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[FuncionariLlocFields.LLOCID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[FuncionariLlocFields.LLOCID]}" ></i>
              </c:if>
            </td>
          <td id="funcionariLloc_llocID_columnvalueid">
          <form:errors path="funcionariLloc.llocID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,FuncionariLlocFields.LLOCID)}" >
          <form:hidden path="funcionariLloc.llocID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.funcionariLloc.llocID,__theForm.listOfLlocForLlocID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,FuncionariLlocFields.LLOCID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="funcionariLloc_llocID"  onchange="if(typeof onChangeLlocID == 'function') {  onChangeLlocID(this); };"  cssClass="form-control col-md-9-optional" path="funcionariLloc.llocID">
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
            <label>
              <fmt:message key="${(empty __theForm.labels[FuncionariLlocFields.FUNCIONARIID])?'funcionariLloc.funcionariID':__theForm.labels[FuncionariLlocFields.FUNCIONARIID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[FuncionariLlocFields.FUNCIONARIID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[FuncionariLlocFields.FUNCIONARIID]}" ></i>
              </c:if>
            </td>
          <td id="funcionariLloc_funcionariID_columnvalueid">
          <form:errors path="funcionariLloc.funcionariID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,FuncionariLlocFields.FUNCIONARIID)}" >
          <form:hidden path="funcionariLloc.funcionariID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.funcionariLloc.funcionariID,__theForm.listOfFuncionariForFuncionariID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,FuncionariLlocFields.FUNCIONARIID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="funcionariLloc_funcionariID"  onchange="if(typeof onChangeFuncionariID == 'function') {  onChangeFuncionariID(this); };"  cssClass="form-control col-md-9-optional" path="funcionariLloc.funcionariID">
            <c:forEach items="${__theForm.listOfFuncionariForFuncionariID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
          <script>
              $(document).ready(function() {
                  $('#funcionariLloc_funcionariID').select2();
              });
          </script>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FuncionariLlocFields.DATAINICI)}">
        <tr id="funcionariLloc_dataInici_rowid">
          <td id="funcionariLloc_dataInici_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[FuncionariLlocFields.DATAINICI])?'funcionariLloc.dataInici':__theForm.labels[FuncionariLlocFields.DATAINICI]}" />
             </label>
              <c:if test="${not empty __theForm.help[FuncionariLlocFields.DATAINICI]}">
              <i class="fas fa-info-circle" title="${__theForm.help[FuncionariLlocFields.DATAINICI]}" ></i>
              </c:if>
            </td>
          <td id="funcionariLloc_dataInici_columnvalueid">
    <form:errors path="funcionariLloc.dataInici" cssClass="errorField alert alert-danger" />
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="funcionariLloc_dataInici" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,FuncionariLlocFields.DATAINICI)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#funcionariLloc_dataInici" path="funcionariLloc.dataInici" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,FuncionariLlocFields.DATAINICI)}" >
                    <div class="input-group-append"  data-target="#funcionariLloc_dataInici"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#funcionariLloc_dataInici').datetimepicker({
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FuncionariLlocFields.DATAFI)}">
        <tr id="funcionariLloc_dataFi_rowid">
          <td id="funcionariLloc_dataFi_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[FuncionariLlocFields.DATAFI])?'funcionariLloc.dataFi':__theForm.labels[FuncionariLlocFields.DATAFI]}" />
             </label>
              <c:if test="${not empty __theForm.help[FuncionariLlocFields.DATAFI]}">
              <i class="fas fa-info-circle" title="${__theForm.help[FuncionariLlocFields.DATAFI]}" ></i>
              </c:if>
            </td>
          <td id="funcionariLloc_dataFi_columnvalueid">
    <form:errors path="funcionariLloc.dataFi" cssClass="errorField alert alert-danger" />
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="funcionariLloc_dataFi" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,FuncionariLlocFields.DATAFI)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#funcionariLloc_dataFi" path="funcionariLloc.dataFi" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,FuncionariLlocFields.DATAFI)}" >
                    <div class="input-group-append"  data-target="#funcionariLloc_dataFi"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#funcionariLloc_dataFi').datetimepicker({
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FuncionariLlocFields.DATACREACIO)}">
        <tr id="funcionariLloc_dataCreacio_rowid">
          <td id="funcionariLloc_dataCreacio_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[FuncionariLlocFields.DATACREACIO])?'funcionariLloc.dataCreacio':__theForm.labels[FuncionariLlocFields.DATACREACIO]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[FuncionariLlocFields.DATACREACIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[FuncionariLlocFields.DATACREACIO]}" ></i>
              </c:if>
            </td>
          <td id="funcionariLloc_dataCreacio_columnvalueid">
    <form:errors path="funcionariLloc.dataCreacio" cssClass="errorField alert alert-danger" />
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="funcionariLloc_dataCreacio" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,FuncionariLlocFields.DATACREACIO)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#funcionariLloc_dataCreacio" path="funcionariLloc.dataCreacio" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,FuncionariLlocFields.DATACREACIO)}" >
                    <div class="input-group-append"  data-target="#funcionariLloc_dataCreacio"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#funcionariLloc_dataCreacio').datetimepicker({
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FuncionariLlocFields.USUARIID)}">
        <tr id="funcionariLloc_usuariID_rowid">
          <td id="funcionariLloc_usuariID_columnlabelid">
            <label>
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
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.funcionariLloc.usuariID,__theForm.listOfUsuariForUsuariID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,FuncionariLlocFields.USUARIID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="funcionariLloc_usuariID"  onchange="if(typeof onChangeUsuariID == 'function') {  onChangeUsuariID(this); };"  cssClass="form-control col-md-9-optional" path="funcionariLloc.usuariID">
            <c:forEach items="${__theForm.listOfUsuariForUsuariID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.funcionariLloc.usuariID }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.funcionariLloc.usuariID }">
                  <form:option value="" ></form:option>
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
            <label>
              <fmt:message key="${(empty __theForm.labels[FuncionariLlocFields.NUMEROCAI])?'funcionariLloc.numeroCai':__theForm.labels[FuncionariLlocFields.NUMEROCAI]}" />
             </label>
              <c:if test="${not empty __theForm.help[FuncionariLlocFields.NUMEROCAI]}">
              <i class="fas fa-info-circle" title="${__theForm.help[FuncionariLlocFields.NUMEROCAI]}" ></i>
              </c:if>
            </td>
          <td id="funcionariLloc_numeroCai_columnvalueid">
            <form:errors path="funcionariLloc.numeroCai" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,FuncionariLlocFields.NUMEROCAI)? 'true' : 'false'}" cssClass="w-75 form-control  ${gen:contains(__theForm.readOnlyFields ,FuncionariLlocFields.NUMEROCAI)? ' uneditable-input' : ''}"  style="" maxlength="50" path="funcionariLloc.numeroCai"   />

           </td>
        </tr>
        </c:if>
        
