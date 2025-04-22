<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="FuncionariFields" className="es.caib.rfhab.model.fields.FuncionariFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,FuncionariFields.NUMERO)}">
        <tr id="funcionari_numero_rowid">
          <td id="funcionari_numero_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[FuncionariFields.NUMERO])?'funcionari.numero':__theForm.labels[FuncionariFields.NUMERO]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[FuncionariFields.NUMERO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[FuncionariFields.NUMERO]}" ></i>
              </c:if>
            </td>
          <td id="funcionari_numero_columnvalueid">
            <form:errors path="funcionari.numero" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,FuncionariFields.NUMERO)? 'true' : 'false'}" cssClass="w-50 form-control  ${gen:contains(__theForm.readOnlyFields ,FuncionariFields.NUMERO)? ' uneditable-input' : ''}"  style="" maxlength="10" path="funcionari.numero"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FuncionariFields.NOM)}">
        <tr id="funcionari_nom_rowid">
          <td id="funcionari_nom_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[FuncionariFields.NOM])?'funcionari.nom':__theForm.labels[FuncionariFields.NOM]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[FuncionariFields.NOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[FuncionariFields.NOM]}" ></i>
              </c:if>
            </td>
          <td id="funcionari_nom_columnvalueid">
            <form:errors path="funcionari.nom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,FuncionariFields.NOM)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,FuncionariFields.NOM)? ' uneditable-input' : ''}"  style="" maxlength="255" path="funcionari.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FuncionariFields.LLINATGE1)}">
        <tr id="funcionari_llinatge1_rowid">
          <td id="funcionari_llinatge1_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[FuncionariFields.LLINATGE1])?'funcionari.llinatge1':__theForm.labels[FuncionariFields.LLINATGE1]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[FuncionariFields.LLINATGE1]}">
              <i class="fas fa-info-circle" title="${__theForm.help[FuncionariFields.LLINATGE1]}" ></i>
              </c:if>
            </td>
          <td id="funcionari_llinatge1_columnvalueid">
            <form:errors path="funcionari.llinatge1" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,FuncionariFields.LLINATGE1)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,FuncionariFields.LLINATGE1)? ' uneditable-input' : ''}"  style="" maxlength="255" path="funcionari.llinatge1"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FuncionariFields.LLINATGE2)}">
        <tr id="funcionari_llinatge2_rowid">
          <td id="funcionari_llinatge2_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[FuncionariFields.LLINATGE2])?'funcionari.llinatge2':__theForm.labels[FuncionariFields.LLINATGE2]}" />
             </label>
              <c:if test="${not empty __theForm.help[FuncionariFields.LLINATGE2]}">
              <i class="fas fa-info-circle" title="${__theForm.help[FuncionariFields.LLINATGE2]}" ></i>
              </c:if>
            </td>
          <td id="funcionari_llinatge2_columnvalueid">
            <form:errors path="funcionari.llinatge2" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,FuncionariFields.LLINATGE2)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,FuncionariFields.LLINATGE2)? ' uneditable-input' : ''}"  style="" maxlength="255" path="funcionari.llinatge2"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FuncionariFields.TIPUSIDENTIFICADOR)}">
        <tr id="funcionari_tipusIdentificador_rowid">
          <td id="funcionari_tipusIdentificador_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[FuncionariFields.TIPUSIDENTIFICADOR])?'funcionari.tipusIdentificador':__theForm.labels[FuncionariFields.TIPUSIDENTIFICADOR]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[FuncionariFields.TIPUSIDENTIFICADOR]}">
              <i class="fas fa-info-circle" title="${__theForm.help[FuncionariFields.TIPUSIDENTIFICADOR]}" ></i>
              </c:if>
            </td>
          <td id="funcionari_tipusIdentificador_columnvalueid">
          <form:errors path="funcionari.tipusIdentificador" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,FuncionariFields.TIPUSIDENTIFICADOR)}" >
          <form:hidden path="funcionari.tipusIdentificador"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.funcionari.tipusIdentificador,__theForm.listOfValuesForTipusIdentificador)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,FuncionariFields.TIPUSIDENTIFICADOR)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="funcionari_tipusIdentificador"  onchange="if(typeof onChangeTipusIdentificador == 'function') {  onChangeTipusIdentificador(this); };"  cssClass="form-control col-md-9-optional" path="funcionari.tipusIdentificador">
            <c:forEach items="${__theForm.listOfValuesForTipusIdentificador}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FuncionariFields.IDENTIFICADOR)}">
        <tr id="funcionari_identificador_rowid">
          <td id="funcionari_identificador_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[FuncionariFields.IDENTIFICADOR])?'funcionari.identificador':__theForm.labels[FuncionariFields.IDENTIFICADOR]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[FuncionariFields.IDENTIFICADOR]}">
              <i class="fas fa-info-circle" title="${__theForm.help[FuncionariFields.IDENTIFICADOR]}" ></i>
              </c:if>
            </td>
          <td id="funcionari_identificador_columnvalueid">
            <form:errors path="funcionari.identificador" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,FuncionariFields.IDENTIFICADOR)? 'true' : 'false'}" cssClass="w-75 form-control  ${gen:contains(__theForm.readOnlyFields ,FuncionariFields.IDENTIFICADOR)? ' uneditable-input' : ''}"  style="" maxlength="50" path="funcionari.identificador"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FuncionariFields.USUARI)}">
        <tr id="funcionari_usuari_rowid">
          <td id="funcionari_usuari_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[FuncionariFields.USUARI])?'funcionari.usuari':__theForm.labels[FuncionariFields.USUARI]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[FuncionariFields.USUARI]}">
              <i class="fas fa-info-circle" title="${__theForm.help[FuncionariFields.USUARI]}" ></i>
              </c:if>
            </td>
          <td id="funcionari_usuari_columnvalueid">
            <form:errors path="funcionari.usuari" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,FuncionariFields.USUARI)? 'true' : 'false'}" cssClass="w-75 form-control  ${gen:contains(__theForm.readOnlyFields ,FuncionariFields.USUARI)? ' uneditable-input' : ''}"  style="" maxlength="50" path="funcionari.usuari"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FuncionariFields.CORREU)}">
        <tr id="funcionari_correu_rowid">
          <td id="funcionari_correu_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[FuncionariFields.CORREU])?'funcionari.correu':__theForm.labels[FuncionariFields.CORREU]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[FuncionariFields.CORREU]}">
              <i class="fas fa-info-circle" title="${__theForm.help[FuncionariFields.CORREU]}" ></i>
              </c:if>
            </td>
          <td id="funcionari_correu_columnvalueid">
            <form:errors path="funcionari.correu" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,FuncionariFields.CORREU)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,FuncionariFields.CORREU)? ' uneditable-input' : ''}"  style="" maxlength="255" path="funcionari.correu"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FuncionariFields.DATACREACIO)}">
        <tr id="funcionari_dataCreacio_rowid">
          <td id="funcionari_dataCreacio_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[FuncionariFields.DATACREACIO])?'funcionari.dataCreacio':__theForm.labels[FuncionariFields.DATACREACIO]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[FuncionariFields.DATACREACIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[FuncionariFields.DATACREACIO]}" ></i>
              </c:if>
            </td>
          <td id="funcionari_dataCreacio_columnvalueid">
    <form:errors path="funcionari.dataCreacio" cssClass="errorField alert alert-danger" />
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="funcionari_dataCreacio" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,FuncionariFields.DATACREACIO)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#funcionari_dataCreacio" path="funcionari.dataCreacio" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,FuncionariFields.DATACREACIO)}" >
                    <div class="input-group-append"  data-target="#funcionari_dataCreacio"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#funcionari_dataCreacio').datetimepicker({
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FuncionariFields.OBSERVACIONS)}">
        <tr id="funcionari_observacions_rowid">
          <td id="funcionari_observacions_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[FuncionariFields.OBSERVACIONS])?'funcionari.observacions':__theForm.labels[FuncionariFields.OBSERVACIONS]}" />
             </label>
              <c:if test="${not empty __theForm.help[FuncionariFields.OBSERVACIONS]}">
              <i class="fas fa-info-circle" title="${__theForm.help[FuncionariFields.OBSERVACIONS]}" ></i>
              </c:if>
            </td>
          <td id="funcionari_observacions_columnvalueid">
              <form:errors path="funcionari.observacions" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,FuncionariFields.OBSERVACIONS)? 'true' : 'false'}" path="funcionari.observacions"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_observacions" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_observacions" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('funcionari.observacions'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('funcionari.observacions'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('funcionari.observacions'); ta.wrap='hard';">Hard Wrap</a>
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FuncionariFields.DATABAIXA)}">
        <tr id="funcionari_dataBaixa_rowid">
          <td id="funcionari_dataBaixa_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[FuncionariFields.DATABAIXA])?'funcionari.dataBaixa':__theForm.labels[FuncionariFields.DATABAIXA]}" />
             </label>
              <c:if test="${not empty __theForm.help[FuncionariFields.DATABAIXA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[FuncionariFields.DATABAIXA]}" ></i>
              </c:if>
            </td>
          <td id="funcionari_dataBaixa_columnvalueid">
    <form:errors path="funcionari.dataBaixa" cssClass="errorField alert alert-danger" />
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="funcionari_dataBaixa" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,FuncionariFields.DATABAIXA)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#funcionari_dataBaixa" path="funcionari.dataBaixa" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,FuncionariFields.DATABAIXA)}" >
                    <div class="input-group-append"  data-target="#funcionari_dataBaixa"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#funcionari_dataBaixa').datetimepicker({
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FuncionariFields.ENTITATID)}">
        <tr id="funcionari_entitatID_rowid">
          <td id="funcionari_entitatID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[FuncionariFields.ENTITATID])?'funcionari.entitatID':__theForm.labels[FuncionariFields.ENTITATID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[FuncionariFields.ENTITATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[FuncionariFields.ENTITATID]}" ></i>
              </c:if>
            </td>
          <td id="funcionari_entitatID_columnvalueid">
            <form:errors path="funcionari.entitatID" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,FuncionariFields.ENTITATID)? 'true' : 'false'}" cssClass="w-25 form-control  ${gen:contains(__theForm.readOnlyFields ,FuncionariFields.ENTITATID)? ' uneditable-input' : ''}"  style=""  path="funcionari.entitatID"   />

           </td>
        </tr>
        </c:if>
        
