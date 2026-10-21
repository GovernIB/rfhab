<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="UsuariEntitatFields" className="es.caib.rfhab.model.fields.UsuariEntitatFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariEntitatFields.ENTITATID)}">
        <tr id="usuariEntitat_entitatID_rowid">
          <td id="usuariEntitat_entitatID_columnlabelid">
            <label for="usuariEntitat.entitatID">
              <fmt:message key="${(empty __theForm.labels[UsuariEntitatFields.ENTITATID])?'usuariEntitat.entitatID':__theForm.labels[UsuariEntitatFields.ENTITATID]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[UsuariEntitatFields.ENTITATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariEntitatFields.ENTITATID]}" ></i>
              </c:if>
            </td>
          <td id="usuariEntitat_entitatID_columnvalueid">
          <form:errors path="usuariEntitat.entitatID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.ENTITATID)}" >
          <form:hidden path="usuariEntitat.entitatID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.usuariEntitat.entitatID,__theForm.listOfEntitatForEntitatID)}" aria-label="usuariEntitat.entitatID"   required aria-required="true"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.ENTITATID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select path="usuariEntitat.entitatID" cssClass="form-control col-md-9-optional"  required="required"  aria-required="true"  onchange="if(typeof onChangeEntitatID == 'function') {  onChangeEntitatID(this); };">


            <c:forEach items="${__theForm.listOfEntitatForEntitatID}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariEntitatFields.USUARIID)}">
        <tr id="usuariEntitat_usuariID_rowid">
          <td id="usuariEntitat_usuariID_columnlabelid">
            <label for="usuariEntitat.usuariID">
              <fmt:message key="${(empty __theForm.labels[UsuariEntitatFields.USUARIID])?'usuariEntitat.usuariID':__theForm.labels[UsuariEntitatFields.USUARIID]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[UsuariEntitatFields.USUARIID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariEntitatFields.USUARIID]}" ></i>
              </c:if>
            </td>
          <td id="usuariEntitat_usuariID_columnvalueid">
          <form:errors path="usuariEntitat.usuariID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.USUARIID)}" >
          <form:hidden path="usuariEntitat.usuariID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.usuariEntitat.usuariID,__theForm.listOfUsuariForUsuariID)}" aria-label="usuariEntitat.usuariID"   required aria-required="true"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.USUARIID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select path="usuariEntitat.usuariID" cssClass="form-control col-md-9-optional"  required="required"  aria-required="true"  onchange="if(typeof onChangeUsuariID == 'function') {  onChangeUsuariID(this); };">


            <c:forEach items="${__theForm.listOfUsuariForUsuariID}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariEntitatFields.ACTIU)}">
        <tr id="usuariEntitat_actiu_rowid">
          <td id="usuariEntitat_actiu_columnlabelid">
            <label for="usuariEntitat.actiu">
              <fmt:message key="${(empty __theForm.labels[UsuariEntitatFields.ACTIU])?'usuariEntitat.actiu':__theForm.labels[UsuariEntitatFields.ACTIU]}" />
             </label>
              <c:if test="${not empty __theForm.help[UsuariEntitatFields.ACTIU]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariEntitatFields.ACTIU]}" ></i>
              </c:if>
            </td>
          <td id="usuariEntitat_actiu_columnvalueid">
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.ACTIU)}" >
              <form:errors  path="usuariEntitat.actiu" cssClass="errorField alert alert-danger" />
<fieldset>
<legend class="sr-only">Evitar errors d'Accessibilitat</legend>
              <form:checkbox cssClass="" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.ACTIU)? 'false' : 'true'}" id="usuariEntitat.actiu" path="usuariEntitat.actiu"   />
</fieldset>
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.ACTIU)}" >
                <fmt:message key="genapp.checkbox.${__theForm.usuariEntitat.actiu}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
