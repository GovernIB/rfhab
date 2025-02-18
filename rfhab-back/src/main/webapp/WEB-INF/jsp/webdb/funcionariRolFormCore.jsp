<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="FuncionariRolFields" className="es.caib.rfhab.model.fields.FuncionariRolFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,FuncionariRolFields.FUNCIONARIID)}">
        <tr id="funcionariRol_funcionariID_rowid">
          <td id="funcionariRol_funcionariID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[FuncionariRolFields.FUNCIONARIID])?'funcionariRol.funcionariID':__theForm.labels[FuncionariRolFields.FUNCIONARIID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[FuncionariRolFields.FUNCIONARIID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[FuncionariRolFields.FUNCIONARIID]}" ></i>
              </c:if>
            </td>
          <td id="funcionariRol_funcionariID_columnvalueid">
          <form:errors path="funcionariRol.funcionariID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,FuncionariRolFields.FUNCIONARIID)}" >
          <form:hidden path="funcionariRol.funcionariID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.funcionariRol.funcionariID,__theForm.listOfFuncionariForFuncionariID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,FuncionariRolFields.FUNCIONARIID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="funcionariRol_funcionariID"  onchange="if(typeof onChangeFuncionariID == 'function') {  onChangeFuncionariID(this); };"  cssClass="form-control col-md-9-optional" path="funcionariRol.funcionariID">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FuncionariRolFields.ROLID)}">
        <tr id="funcionariRol_rolID_rowid">
          <td id="funcionariRol_rolID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[FuncionariRolFields.ROLID])?'funcionariRol.rolID':__theForm.labels[FuncionariRolFields.ROLID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[FuncionariRolFields.ROLID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[FuncionariRolFields.ROLID]}" ></i>
              </c:if>
            </td>
          <td id="funcionariRol_rolID_columnvalueid">
          <form:errors path="funcionariRol.rolID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,FuncionariRolFields.ROLID)}" >
          <form:hidden path="funcionariRol.rolID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.funcionariRol.rolID,__theForm.listOfRolForRolID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,FuncionariRolFields.ROLID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="funcionariRol_rolID"  onchange="if(typeof onChangeRolID == 'function') {  onChangeRolID(this); };"  cssClass="form-control col-md-9-optional" path="funcionariRol.rolID">
            <c:forEach items="${__theForm.listOfRolForRolID}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FuncionariRolFields.DATACREACIO)}">
        <tr id="funcionariRol_dataCreacio_rowid">
          <td id="funcionariRol_dataCreacio_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[FuncionariRolFields.DATACREACIO])?'funcionariRol.dataCreacio':__theForm.labels[FuncionariRolFields.DATACREACIO]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[FuncionariRolFields.DATACREACIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[FuncionariRolFields.DATACREACIO]}" ></i>
              </c:if>
            </td>
          <td id="funcionariRol_dataCreacio_columnvalueid">
    <form:errors path="funcionariRol.dataCreacio" cssClass="errorField alert alert-danger" />
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="funcionariRol_dataCreacio" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,FuncionariRolFields.DATACREACIO)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#funcionariRol_dataCreacio" path="funcionariRol.dataCreacio" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,FuncionariRolFields.DATACREACIO)}" >
                    <div class="input-group-append"  data-target="#funcionariRol_dataCreacio"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#funcionariRol_dataCreacio').datetimepicker({
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
        
