<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="LlocFields" className="es.caib.rfhab.model.fields.LlocFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,LlocFields.CODILLOCPROPI)}">
        <tr id="lloc_codiLlocPropi_rowid">
          <td id="lloc_codiLlocPropi_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[LlocFields.CODILLOCPROPI])?'lloc.codiLlocPropi':__theForm.labels[LlocFields.CODILLOCPROPI]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[LlocFields.CODILLOCPROPI]}">
              <i class="fas fa-info-circle" title="${__theForm.help[LlocFields.CODILLOCPROPI]}" ></i>
              </c:if>
            </td>
          <td id="lloc_codiLlocPropi_columnvalueid">
            <form:errors path="lloc.codiLlocPropi" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,LlocFields.CODILLOCPROPI)? 'true' : 'false'}" cssClass="w-75 form-control  ${gen:contains(__theForm.readOnlyFields ,LlocFields.CODILLOCPROPI)? ' uneditable-input' : ''}"  style="" maxlength="50" path="lloc.codiLlocPropi"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,LlocFields.CODILLOC)}">
        <tr id="lloc_codiLloc_rowid">
          <td id="lloc_codiLloc_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[LlocFields.CODILLOC])?'lloc.codiLloc':__theForm.labels[LlocFields.CODILLOC]}" />
             </label>
              <c:if test="${not empty __theForm.help[LlocFields.CODILLOC]}">
              <i class="fas fa-info-circle" title="${__theForm.help[LlocFields.CODILLOC]}" ></i>
              </c:if>
            </td>
          <td id="lloc_codiLloc_columnvalueid">
            <form:errors path="lloc.codiLloc" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,LlocFields.CODILLOC)? 'true' : 'false'}" cssClass="w-75 form-control  ${gen:contains(__theForm.readOnlyFields ,LlocFields.CODILLOC)? ' uneditable-input' : ''}"  style="" maxlength="50" path="lloc.codiLloc"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,LlocFields.EXPANSIO)}">
        <tr id="lloc_expansio_rowid">
          <td id="lloc_expansio_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[LlocFields.EXPANSIO])?'lloc.expansio':__theForm.labels[LlocFields.EXPANSIO]}" />
             </label>
              <c:if test="${not empty __theForm.help[LlocFields.EXPANSIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[LlocFields.EXPANSIO]}" ></i>
              </c:if>
            </td>
          <td id="lloc_expansio_columnvalueid">
            <form:errors path="lloc.expansio" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,LlocFields.EXPANSIO)? 'true' : 'false'}" cssClass="w-75 form-control  ${gen:contains(__theForm.readOnlyFields ,LlocFields.EXPANSIO)? ' uneditable-input' : ''}"  style="" maxlength="50" path="lloc.expansio"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,LlocFields.NOM)}">
        <tr id="lloc_nom_rowid">
          <td id="lloc_nom_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[LlocFields.NOM])?'lloc.nom':__theForm.labels[LlocFields.NOM]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[LlocFields.NOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[LlocFields.NOM]}" ></i>
              </c:if>
            </td>
          <td id="lloc_nom_columnvalueid">
            <form:errors path="lloc.nom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,LlocFields.NOM)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,LlocFields.NOM)? ' uneditable-input' : ''}"  style="" maxlength="255" path="lloc.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,LlocFields.ENTITATID)}">
        <tr id="lloc_entitatID_rowid">
          <td id="lloc_entitatID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[LlocFields.ENTITATID])?'lloc.entitatID':__theForm.labels[LlocFields.ENTITATID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[LlocFields.ENTITATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[LlocFields.ENTITATID]}" ></i>
              </c:if>
            </td>
          <td id="lloc_entitatID_columnvalueid">
          <form:errors path="lloc.entitatID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,LlocFields.ENTITATID)}" >
          <form:hidden path="lloc.entitatID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.lloc.entitatID,__theForm.listOfEntitatForEntitatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,LlocFields.ENTITATID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="lloc_entitatID"  onchange="if(typeof onChangeEntitatID == 'function') {  onChangeEntitatID(this); };"  cssClass="form-control col-md-9-optional" path="lloc.entitatID">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,LlocFields.UNITATID)}">
        <tr id="lloc_unitatID_rowid">
          <td id="lloc_unitatID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[LlocFields.UNITATID])?'lloc.unitatID':__theForm.labels[LlocFields.UNITATID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[LlocFields.UNITATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[LlocFields.UNITATID]}" ></i>
              </c:if>
            </td>
          <td id="lloc_unitatID_columnvalueid">
          <form:errors path="lloc.unitatID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,LlocFields.UNITATID)}" >
          <form:hidden path="lloc.unitatID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.lloc.unitatID,__theForm.listOfUnitatForUnitatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,LlocFields.UNITATID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="lloc_unitatID"  onchange="if(typeof onChangeUnitatID == 'function') {  onChangeUnitatID(this); };"  cssClass="form-control col-md-9-optional" path="lloc.unitatID">
            <c:forEach items="${__theForm.listOfUnitatForUnitatID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
          <script>
              $(document).ready(function() {
                  $('#lloc_unitatID').select2();
              });
          </script>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,LlocFields.DATAALTA)}">
        <tr id="lloc_dataalta_rowid">
          <td id="lloc_dataalta_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[LlocFields.DATAALTA])?'lloc.dataalta':__theForm.labels[LlocFields.DATAALTA]}" />
             </label>
              <c:if test="${not empty __theForm.help[LlocFields.DATAALTA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[LlocFields.DATAALTA]}" ></i>
              </c:if>
            </td>
          <td id="lloc_dataalta_columnvalueid">
    <form:errors path="lloc.dataalta" cssClass="errorField alert alert-danger" />
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="lloc_dataalta" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,LlocFields.DATAALTA)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#lloc_dataalta" path="lloc.dataalta" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,LlocFields.DATAALTA)}" >
                    <div class="input-group-append"  data-target="#lloc_dataalta"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#lloc_dataalta').datetimepicker({
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,LlocFields.DATACREACIO)}">
        <tr id="lloc_dataCreacio_rowid">
          <td id="lloc_dataCreacio_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[LlocFields.DATACREACIO])?'lloc.dataCreacio':__theForm.labels[LlocFields.DATACREACIO]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[LlocFields.DATACREACIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[LlocFields.DATACREACIO]}" ></i>
              </c:if>
            </td>
          <td id="lloc_dataCreacio_columnvalueid">
    <form:errors path="lloc.dataCreacio" cssClass="errorField alert alert-danger" />
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="lloc_dataCreacio" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,LlocFields.DATACREACIO)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#lloc_dataCreacio" path="lloc.dataCreacio" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,LlocFields.DATACREACIO)}" >
                    <div class="input-group-append"  data-target="#lloc_dataCreacio"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#lloc_dataCreacio').datetimepicker({
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,LlocFields.DATABAIXA)}">
        <tr id="lloc_dataBaixa_rowid">
          <td id="lloc_dataBaixa_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[LlocFields.DATABAIXA])?'lloc.dataBaixa':__theForm.labels[LlocFields.DATABAIXA]}" />
             </label>
              <c:if test="${not empty __theForm.help[LlocFields.DATABAIXA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[LlocFields.DATABAIXA]}" ></i>
              </c:if>
            </td>
          <td id="lloc_dataBaixa_columnvalueid">
    <form:errors path="lloc.dataBaixa" cssClass="errorField alert alert-danger" />
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="lloc_dataBaixa" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,LlocFields.DATABAIXA)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#lloc_dataBaixa" path="lloc.dataBaixa" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,LlocFields.DATABAIXA)}" >
                    <div class="input-group-append"  data-target="#lloc_dataBaixa"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#lloc_dataBaixa').datetimepicker({
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,LlocFields.PERSONALOAMR)}">
        <tr id="lloc_personalOamr_rowid">
          <td id="lloc_personalOamr_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[LlocFields.PERSONALOAMR])?'lloc.personalOamr':__theForm.labels[LlocFields.PERSONALOAMR]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[LlocFields.PERSONALOAMR]}">
              <i class="fas fa-info-circle" title="${__theForm.help[LlocFields.PERSONALOAMR]}" ></i>
              </c:if>
            </td>
          <td id="lloc_personalOamr_columnvalueid">
          <form:errors path="lloc.personalOamr" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,LlocFields.PERSONALOAMR)}" >
          <form:hidden path="lloc.personalOamr"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.lloc.personalOamr,__theForm.listOfValuesForPersonalOamr)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,LlocFields.PERSONALOAMR)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="lloc_personalOamr"  onchange="if(typeof onChangePersonalOamr == 'function') {  onChangePersonalOamr(this); };"  cssClass="form-control col-md-9-optional" path="lloc.personalOamr">
            <c:forEach items="${__theForm.listOfValuesForPersonalOamr}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,LlocFields.OBSERVACIONS)}">
        <tr id="lloc_observacions_rowid">
          <td id="lloc_observacions_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[LlocFields.OBSERVACIONS])?'lloc.observacions':__theForm.labels[LlocFields.OBSERVACIONS]}" />
             </label>
              <c:if test="${not empty __theForm.help[LlocFields.OBSERVACIONS]}">
              <i class="fas fa-info-circle" title="${__theForm.help[LlocFields.OBSERVACIONS]}" ></i>
              </c:if>
            </td>
          <td id="lloc_observacions_columnvalueid">
              <form:errors path="lloc.observacions" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,LlocFields.OBSERVACIONS)? 'true' : 'false'}" path="lloc.observacions"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_observacions" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_observacions" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('lloc.observacions'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('lloc.observacions'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('lloc.observacions'); ta.wrap='hard';">Hard Wrap</a>
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
        
