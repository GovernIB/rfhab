<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="UsuariFields" className="es.caib.rfhab.model.fields.UsuariFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariFields.NOM)}">
        <tr id="usuari_nom_rowid">
          <td id="usuari_nom_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariFields.NOM])?'usuari.nom':__theForm.labels[UsuariFields.NOM]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[UsuariFields.NOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariFields.NOM]}" ></i>
              </c:if>
            </td>
          <td id="usuari_nom_columnvalueid">
            <form:errors path="usuari.nom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariFields.NOM)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,UsuariFields.NOM)? ' uneditable-input' : ''}"  style="" maxlength="255" path="usuari.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariFields.LLINATGE1)}">
        <tr id="usuari_llinatge1_rowid">
          <td id="usuari_llinatge1_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariFields.LLINATGE1])?'usuari.llinatge1':__theForm.labels[UsuariFields.LLINATGE1]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[UsuariFields.LLINATGE1]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariFields.LLINATGE1]}" ></i>
              </c:if>
            </td>
          <td id="usuari_llinatge1_columnvalueid">
            <form:errors path="usuari.llinatge1" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariFields.LLINATGE1)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,UsuariFields.LLINATGE1)? ' uneditable-input' : ''}"  style="" maxlength="255" path="usuari.llinatge1"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariFields.LLINATGE2)}">
        <tr id="usuari_llinatge2_rowid">
          <td id="usuari_llinatge2_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariFields.LLINATGE2])?'usuari.llinatge2':__theForm.labels[UsuariFields.LLINATGE2]}" />
             </label>
              <c:if test="${not empty __theForm.help[UsuariFields.LLINATGE2]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariFields.LLINATGE2]}" ></i>
              </c:if>
            </td>
          <td id="usuari_llinatge2_columnvalueid">
            <form:errors path="usuari.llinatge2" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariFields.LLINATGE2)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,UsuariFields.LLINATGE2)? ' uneditable-input' : ''}"  style="" maxlength="255" path="usuari.llinatge2"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariFields.NIF)}">
        <tr id="usuari_nif_rowid">
          <td id="usuari_nif_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariFields.NIF])?'usuari.nif':__theForm.labels[UsuariFields.NIF]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[UsuariFields.NIF]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariFields.NIF]}" ></i>
              </c:if>
            </td>
          <td id="usuari_nif_columnvalueid">
            <form:errors path="usuari.nif" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariFields.NIF)? 'true' : 'false'}" cssClass="w-75 form-control  ${gen:contains(__theForm.readOnlyFields ,UsuariFields.NIF)? ' uneditable-input' : ''}"  style="" maxlength="50" path="usuari.nif"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariFields.USERNAME)}">
        <tr id="usuari_username_rowid">
          <td id="usuari_username_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariFields.USERNAME])?'usuari.username':__theForm.labels[UsuariFields.USERNAME]}" />
             </label>
              <c:if test="${not empty __theForm.help[UsuariFields.USERNAME]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariFields.USERNAME]}" ></i>
              </c:if>
            </td>
          <td id="usuari_username_columnvalueid">
            <form:errors path="usuari.username" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariFields.USERNAME)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,UsuariFields.USERNAME)? ' uneditable-input' : ''}"  style="" maxlength="255" path="usuari.username"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariFields.CORREU)}">
        <tr id="usuari_correu_rowid">
          <td id="usuari_correu_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariFields.CORREU])?'usuari.correu':__theForm.labels[UsuariFields.CORREU]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[UsuariFields.CORREU]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariFields.CORREU]}" ></i>
              </c:if>
            </td>
          <td id="usuari_correu_columnvalueid">
            <form:errors path="usuari.correu" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariFields.CORREU)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,UsuariFields.CORREU)? ' uneditable-input' : ''}"  style="" maxlength="255" path="usuari.correu"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariFields.IDIOMAID)}">
        <tr id="usuari_idiomaID_rowid">
          <td id="usuari_idiomaID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariFields.IDIOMAID])?'usuari.idiomaID':__theForm.labels[UsuariFields.IDIOMAID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[UsuariFields.IDIOMAID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariFields.IDIOMAID]}" ></i>
              </c:if>
            </td>
          <td id="usuari_idiomaID_columnvalueid">
          <form:errors path="usuari.idiomaID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariFields.IDIOMAID)}" >
          <form:hidden path="usuari.idiomaID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.usuari.idiomaID,__theForm.listOfIdiomaForIdiomaID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariFields.IDIOMAID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="usuari_idiomaID"  onchange="if(typeof onChangeIdiomaID == 'function') {  onChangeIdiomaID(this); };"  cssClass="form-control col-md-9-optional" path="usuari.idiomaID">
            <c:forEach items="${__theForm.listOfIdiomaForIdiomaID}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariFields.ACTIU)}">
        <tr id="usuari_actiu_rowid">
          <td id="usuari_actiu_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariFields.ACTIU])?'usuari.actiu':__theForm.labels[UsuariFields.ACTIU]}" />
             </label>
              <c:if test="${not empty __theForm.help[UsuariFields.ACTIU]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariFields.ACTIU]}" ></i>
              </c:if>
            </td>
          <td id="usuari_actiu_columnvalueid">
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariFields.ACTIU)}" >
              <form:errors path="usuari.actiu" cssClass="errorField alert alert-danger" />
              <form:checkbox cssClass="" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,UsuariFields.ACTIU)? 'false' : 'true'}" path="usuari.actiu" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariFields.ACTIU)}" >
                <fmt:message key="genapp.checkbox.${__theForm.usuari.actiu}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariFields.DATACREACIO)}">
        <tr id="usuari_dataCreacio_rowid">
          <td id="usuari_dataCreacio_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariFields.DATACREACIO])?'usuari.dataCreacio':__theForm.labels[UsuariFields.DATACREACIO]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[UsuariFields.DATACREACIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariFields.DATACREACIO]}" ></i>
              </c:if>
            </td>
          <td id="usuari_dataCreacio_columnvalueid">
    <form:errors path="usuari.dataCreacio" cssClass="errorField alert alert-danger" />
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="usuari_dataCreacio" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariFields.DATACREACIO)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#usuari_dataCreacio" path="usuari.dataCreacio" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariFields.DATACREACIO)}" >
                    <div class="input-group-append"  data-target="#usuari_dataCreacio"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#usuari_dataCreacio').datetimepicker({
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariFields.DARRERAENTITAT)}">
        <tr id="usuari_darreraEntitat_rowid">
          <td id="usuari_darreraEntitat_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariFields.DARRERAENTITAT])?'usuari.darreraEntitat':__theForm.labels[UsuariFields.DARRERAENTITAT]}" />
             </label>
              <c:if test="${not empty __theForm.help[UsuariFields.DARRERAENTITAT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariFields.DARRERAENTITAT]}" ></i>
              </c:if>
            </td>
          <td id="usuari_darreraEntitat_columnvalueid">
          <form:errors path="usuari.darreraEntitat" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariFields.DARRERAENTITAT)}" >
          <form:hidden path="usuari.darreraEntitat"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.usuari.darreraEntitat,__theForm.listOfEntitatForDarreraEntitat)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariFields.DARRERAENTITAT)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="usuari_darreraEntitat"  onchange="if(typeof onChangeDarreraEntitat == 'function') {  onChangeDarreraEntitat(this); };"  cssClass="form-control col-md-9-optional" path="usuari.darreraEntitat">
            <c:forEach items="${__theForm.listOfEntitatForDarreraEntitat}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.usuari.darreraEntitat }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.usuari.darreraEntitat }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariFields.DATABAIXA)}">
        <tr id="usuari_dataBaixa_rowid">
          <td id="usuari_dataBaixa_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariFields.DATABAIXA])?'usuari.dataBaixa':__theForm.labels[UsuariFields.DATABAIXA]}" />
             </label>
              <c:if test="${not empty __theForm.help[UsuariFields.DATABAIXA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariFields.DATABAIXA]}" ></i>
              </c:if>
            </td>
          <td id="usuari_dataBaixa_columnvalueid">
    <form:errors path="usuari.dataBaixa" cssClass="errorField alert alert-danger" />
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="usuari_dataBaixa" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariFields.DATABAIXA)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#usuari_dataBaixa" path="usuari.dataBaixa" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariFields.DATABAIXA)}" >
                    <div class="input-group-append"  data-target="#usuari_dataBaixa"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#usuari_dataBaixa').datetimepicker({
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
        
