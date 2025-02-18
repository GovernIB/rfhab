<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="EntitatFields" className="es.caib.rfhab.model.fields.EntitatFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.NOM)}">
        <tr id="entitat_nom_rowid">
          <td id="entitat_nom_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.NOM])?'entitat.nom':__theForm.labels[EntitatFields.NOM]}" />
             </label>
              <c:if test="${not empty __theForm.help[EntitatFields.NOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.NOM]}" ></i>
              </c:if>
            </td>
          <td id="entitat_nom_columnvalueid">
            <form:errors path="entitat.nom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.NOM)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,EntitatFields.NOM)? ' uneditable-input' : ''}"  style="" maxlength="255" path="entitat.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.ACTIU)}">
        <tr id="entitat_actiu_rowid">
          <td id="entitat_actiu_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.ACTIU])?'entitat.actiu':__theForm.labels[EntitatFields.ACTIU]}" />
             </label>
              <c:if test="${not empty __theForm.help[EntitatFields.ACTIU]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.ACTIU]}" ></i>
              </c:if>
            </td>
          <td id="entitat_actiu_columnvalueid">
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EntitatFields.ACTIU)}" >
              <form:errors path="entitat.actiu" cssClass="errorField alert alert-danger" />
              <form:checkbox cssClass="" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,EntitatFields.ACTIU)? 'false' : 'true'}" path="entitat.actiu" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EntitatFields.ACTIU)}" >
                <fmt:message key="genapp.checkbox.${__theForm.entitat.actiu}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.UNITATID)}">
        <tr id="entitat_unitatID_rowid">
          <td id="entitat_unitatID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.UNITATID])?'entitat.unitatID':__theForm.labels[EntitatFields.UNITATID]}" />
             </label>
              <c:if test="${not empty __theForm.help[EntitatFields.UNITATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.UNITATID]}" ></i>
              </c:if>
            </td>
          <td id="entitat_unitatID_columnvalueid">
          <form:errors path="entitat.unitatID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EntitatFields.UNITATID)}" >
          <form:hidden path="entitat.unitatID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.entitat.unitatID,__theForm.listOfUnitatForUnitatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EntitatFields.UNITATID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="entitat_unitatID"  onchange="if(typeof onChangeUnitatID == 'function') {  onChangeUnitatID(this); };"  cssClass="form-control col-md-9-optional" path="entitat.unitatID">
            <c:forEach items="${__theForm.listOfUnitatForUnitatID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.entitat.unitatID }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.entitat.unitatID }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.DATABAIXA)}">
        <tr id="entitat_dataBaixa_rowid">
          <td id="entitat_dataBaixa_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.DATABAIXA])?'entitat.dataBaixa':__theForm.labels[EntitatFields.DATABAIXA]}" />
             </label>
              <c:if test="${not empty __theForm.help[EntitatFields.DATABAIXA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.DATABAIXA]}" ></i>
              </c:if>
            </td>
          <td id="entitat_dataBaixa_columnvalueid">
    <form:errors path="entitat.dataBaixa" cssClass="errorField alert alert-danger" />
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="entitat_dataBaixa" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.DATABAIXA)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#entitat_dataBaixa" path="entitat.dataBaixa" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,EntitatFields.DATABAIXA)}" >
                    <div class="input-group-append"  data-target="#entitat_dataBaixa"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#entitat_dataBaixa').datetimepicker({
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
        
