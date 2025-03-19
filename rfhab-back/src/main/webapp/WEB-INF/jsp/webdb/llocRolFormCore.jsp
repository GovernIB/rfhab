<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="LlocRolFields" className="es.caib.rfhab.model.fields.LlocRolFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,LlocRolFields.DATACREACIO)}">
        <tr id="llocRol_dataCreacio_rowid">
          <td id="llocRol_dataCreacio_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[LlocRolFields.DATACREACIO])?'llocRol.dataCreacio':__theForm.labels[LlocRolFields.DATACREACIO]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[LlocRolFields.DATACREACIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[LlocRolFields.DATACREACIO]}" ></i>
              </c:if>
            </td>
          <td id="llocRol_dataCreacio_columnvalueid">
    <form:errors path="llocRol.dataCreacio" cssClass="errorField alert alert-danger" />
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="llocRol_dataCreacio" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,LlocRolFields.DATACREACIO)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#llocRol_dataCreacio" path="llocRol.dataCreacio" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,LlocRolFields.DATACREACIO)}" >
                    <div class="input-group-append"  data-target="#llocRol_dataCreacio"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#llocRol_dataCreacio').datetimepicker({
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,LlocRolFields.LLOCID)}">
        <tr id="llocRol_llocID_rowid">
          <td id="llocRol_llocID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[LlocRolFields.LLOCID])?'llocRol.llocID':__theForm.labels[LlocRolFields.LLOCID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[LlocRolFields.LLOCID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[LlocRolFields.LLOCID]}" ></i>
              </c:if>
            </td>
          <td id="llocRol_llocID_columnvalueid">
          <form:errors path="llocRol.llocID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,LlocRolFields.LLOCID)}" >
          <form:hidden path="llocRol.llocID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.llocRol.llocID,__theForm.listOfLlocForLlocID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,LlocRolFields.LLOCID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="llocRol_llocID"  onchange="if(typeof onChangeLlocID == 'function') {  onChangeLlocID(this); };"  cssClass="form-control col-md-9-optional" path="llocRol.llocID">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,LlocRolFields.ROLID)}">
        <tr id="llocRol_rolID_rowid">
          <td id="llocRol_rolID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[LlocRolFields.ROLID])?'llocRol.rolID':__theForm.labels[LlocRolFields.ROLID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[LlocRolFields.ROLID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[LlocRolFields.ROLID]}" ></i>
              </c:if>
            </td>
          <td id="llocRol_rolID_columnvalueid">
          <form:errors path="llocRol.rolID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,LlocRolFields.ROLID)}" >
          <form:hidden path="llocRol.rolID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.llocRol.rolID,__theForm.listOfRolForRolID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,LlocRolFields.ROLID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="llocRol_rolID"  onchange="if(typeof onChangeRolID == 'function') {  onChangeRolID(this); };"  cssClass="form-control col-md-9-optional" path="llocRol.rolID">
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
        
