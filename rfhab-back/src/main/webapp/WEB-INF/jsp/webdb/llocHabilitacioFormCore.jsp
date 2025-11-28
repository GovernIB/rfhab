<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="LlocHabilitacioFields" className="es.caib.rfhab.model.fields.LlocHabilitacioFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,LlocHabilitacioFields.DATACREACIO)}">
        <tr id="llocHabilitacio_dataCreacio_rowid">
          <td id="llocHabilitacio_dataCreacio_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[LlocHabilitacioFields.DATACREACIO])?'llocHabilitacio.dataCreacio':__theForm.labels[LlocHabilitacioFields.DATACREACIO]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[LlocHabilitacioFields.DATACREACIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[LlocHabilitacioFields.DATACREACIO]}" ></i>
              </c:if>
            </td>
          <td id="llocHabilitacio_dataCreacio_columnvalueid">
    <form:errors path="llocHabilitacio.dataCreacio" cssClass="errorField alert alert-danger" />
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="llocHabilitacio_dataCreacio" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,LlocHabilitacioFields.DATACREACIO)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#llocHabilitacio_dataCreacio" path="llocHabilitacio.dataCreacio" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,LlocHabilitacioFields.DATACREACIO)}" >
                    <div class="input-group-append"  data-target="#llocHabilitacio_dataCreacio"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#llocHabilitacio_dataCreacio').datetimepicker({
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,LlocHabilitacioFields.LLOCID)}">
        <tr id="llocHabilitacio_llocID_rowid">
          <td id="llocHabilitacio_llocID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[LlocHabilitacioFields.LLOCID])?'llocHabilitacio.llocID':__theForm.labels[LlocHabilitacioFields.LLOCID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[LlocHabilitacioFields.LLOCID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[LlocHabilitacioFields.LLOCID]}" ></i>
              </c:if>
            </td>
          <td id="llocHabilitacio_llocID_columnvalueid">
          <form:errors path="llocHabilitacio.llocID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,LlocHabilitacioFields.LLOCID)}" >
          <form:hidden path="llocHabilitacio.llocID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.llocHabilitacio.llocID,__theForm.listOfLlocForLlocID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,LlocHabilitacioFields.LLOCID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="llocHabilitacio_llocID"  onchange="if(typeof onChangeLlocID == 'function') {  onChangeLlocID(this); };"  cssClass="form-control col-md-9-optional" path="llocHabilitacio.llocID">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,LlocHabilitacioFields.HABILITACIOID)}">
        <tr id="llocHabilitacio_habilitacioId_rowid">
          <td id="llocHabilitacio_habilitacioId_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[LlocHabilitacioFields.HABILITACIOID])?'llocHabilitacio.habilitacioId':__theForm.labels[LlocHabilitacioFields.HABILITACIOID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[LlocHabilitacioFields.HABILITACIOID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[LlocHabilitacioFields.HABILITACIOID]}" ></i>
              </c:if>
            </td>
          <td id="llocHabilitacio_habilitacioId_columnvalueid">
          <form:errors path="llocHabilitacio.habilitacioId" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,LlocHabilitacioFields.HABILITACIOID)}" >
          <form:hidden path="llocHabilitacio.habilitacioId"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.llocHabilitacio.habilitacioId,__theForm.listOfHabilitacioForHabilitacioId)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,LlocHabilitacioFields.HABILITACIOID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="llocHabilitacio_habilitacioId"  onchange="if(typeof onChangeHabilitacioId == 'function') {  onChangeHabilitacioId(this); };"  cssClass="form-control col-md-9-optional" path="llocHabilitacio.habilitacioId">
            <c:forEach items="${__theForm.listOfHabilitacioForHabilitacioId}" var="tmp">
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
        
