<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="UnitatFields" className="es.caib.rfhab.model.fields.UnitatFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,UnitatFields.CODI)}">
        <tr id="unitat_codi_rowid">
          <td id="unitat_codi_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[UnitatFields.CODI])?'unitat.codi':__theForm.labels[UnitatFields.CODI]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[UnitatFields.CODI]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UnitatFields.CODI]}" ></i>
              </c:if>
            </td>
          <td id="unitat_codi_columnvalueid">
            <form:errors path="unitat.codi" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UnitatFields.CODI)? 'true' : 'false'}" cssClass="w-75 form-control  ${gen:contains(__theForm.readOnlyFields ,UnitatFields.CODI)? ' uneditable-input' : ''}"  style="" maxlength="50" path="unitat.codi"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UnitatFields.VERSIO)}">
        <tr id="unitat_versio_rowid">
          <td id="unitat_versio_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[UnitatFields.VERSIO])?'unitat.versio':__theForm.labels[UnitatFields.VERSIO]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[UnitatFields.VERSIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UnitatFields.VERSIO]}" ></i>
              </c:if>
            </td>
          <td id="unitat_versio_columnvalueid">
            <form:errors path="unitat.versio" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UnitatFields.VERSIO)? 'true' : 'false'}" cssClass="w-25 form-control  ${gen:contains(__theForm.readOnlyFields ,UnitatFields.VERSIO)? ' uneditable-input' : ''}"  style=""  path="unitat.versio"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UnitatFields.DENOMINACIO)}">
        <tr id="unitat_denominacio_rowid">
          <td id="unitat_denominacio_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[UnitatFields.DENOMINACIO])?'unitat.denominacio':__theForm.labels[UnitatFields.DENOMINACIO]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[UnitatFields.DENOMINACIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UnitatFields.DENOMINACIO]}" ></i>
              </c:if>
            </td>
          <td id="unitat_denominacio_columnvalueid">
              <form:errors path="unitat.denominacio" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,UnitatFields.DENOMINACIO)? 'true' : 'false'}" path="unitat.denominacio"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_denominacio" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_denominacio" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('unitat.denominacio'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('unitat.denominacio'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('unitat.denominacio'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_denominacio').on('click', function(){
					var valor = ($('#dropdownMenuContainer_denominacio').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_denominacio').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UnitatFields.COOFICIAL)}">
        <tr id="unitat_cooficial_rowid">
          <td id="unitat_cooficial_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[UnitatFields.COOFICIAL])?'unitat.cooficial':__theForm.labels[UnitatFields.COOFICIAL]}" />
             </label>
              <c:if test="${not empty __theForm.help[UnitatFields.COOFICIAL]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UnitatFields.COOFICIAL]}" ></i>
              </c:if>
            </td>
          <td id="unitat_cooficial_columnvalueid">
              <form:errors path="unitat.cooficial" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,UnitatFields.COOFICIAL)? 'true' : 'false'}" path="unitat.cooficial"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_cooficial" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_cooficial" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('unitat.cooficial'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('unitat.cooficial'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('unitat.cooficial'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_cooficial').on('click', function(){
					var valor = ($('#dropdownMenuContainer_cooficial').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_cooficial').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UnitatFields.ARREL)}">
        <tr id="unitat_arrel_rowid">
          <td id="unitat_arrel_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[UnitatFields.ARREL])?'unitat.arrel':__theForm.labels[UnitatFields.ARREL]}" />
             </label>
              <c:if test="${not empty __theForm.help[UnitatFields.ARREL]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UnitatFields.ARREL]}" ></i>
              </c:if>
            </td>
          <td id="unitat_arrel_columnvalueid">
            <form:errors path="unitat.arrel" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UnitatFields.ARREL)? 'true' : 'false'}" cssClass="w-75 form-control  ${gen:contains(__theForm.readOnlyFields ,UnitatFields.ARREL)? ' uneditable-input' : ''}"  style="" maxlength="50" path="unitat.arrel"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UnitatFields.ARRELVERSIO)}">
        <tr id="unitat_arrelVersio_rowid">
          <td id="unitat_arrelVersio_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[UnitatFields.ARRELVERSIO])?'unitat.arrelVersio':__theForm.labels[UnitatFields.ARRELVERSIO]}" />
             </label>
              <c:if test="${not empty __theForm.help[UnitatFields.ARRELVERSIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UnitatFields.ARRELVERSIO]}" ></i>
              </c:if>
            </td>
          <td id="unitat_arrelVersio_columnvalueid">
            <form:errors path="unitat.arrelVersio" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UnitatFields.ARRELVERSIO)? 'true' : 'false'}" cssClass="w-25 form-control  ${gen:contains(__theForm.readOnlyFields ,UnitatFields.ARRELVERSIO)? ' uneditable-input' : ''}"  style=""  path="unitat.arrelVersio"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UnitatFields.SUPERIOR)}">
        <tr id="unitat_superior_rowid">
          <td id="unitat_superior_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[UnitatFields.SUPERIOR])?'unitat.superior':__theForm.labels[UnitatFields.SUPERIOR]}" />
             </label>
              <c:if test="${not empty __theForm.help[UnitatFields.SUPERIOR]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UnitatFields.SUPERIOR]}" ></i>
              </c:if>
            </td>
          <td id="unitat_superior_columnvalueid">
            <form:errors path="unitat.superior" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UnitatFields.SUPERIOR)? 'true' : 'false'}" cssClass="w-75 form-control  ${gen:contains(__theForm.readOnlyFields ,UnitatFields.SUPERIOR)? ' uneditable-input' : ''}"  style="" maxlength="50" path="unitat.superior"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UnitatFields.SUPERIORVERSIO)}">
        <tr id="unitat_superiorVersio_rowid">
          <td id="unitat_superiorVersio_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[UnitatFields.SUPERIORVERSIO])?'unitat.superiorVersio':__theForm.labels[UnitatFields.SUPERIORVERSIO]}" />
             </label>
              <c:if test="${not empty __theForm.help[UnitatFields.SUPERIORVERSIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UnitatFields.SUPERIORVERSIO]}" ></i>
              </c:if>
            </td>
          <td id="unitat_superiorVersio_columnvalueid">
            <form:errors path="unitat.superiorVersio" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UnitatFields.SUPERIORVERSIO)? 'true' : 'false'}" cssClass="w-25 form-control  ${gen:contains(__theForm.readOnlyFields ,UnitatFields.SUPERIORVERSIO)? ' uneditable-input' : ''}"  style=""  path="unitat.superiorVersio"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UnitatFields.ESTAT)}">
        <tr id="unitat_estat_rowid">
          <td id="unitat_estat_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[UnitatFields.ESTAT])?'unitat.estat':__theForm.labels[UnitatFields.ESTAT]}" />
             </label>
              <c:if test="${not empty __theForm.help[UnitatFields.ESTAT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UnitatFields.ESTAT]}" ></i>
              </c:if>
            </td>
          <td id="unitat_estat_columnvalueid">
          <form:errors path="unitat.estat" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UnitatFields.ESTAT)}" >
          <form:hidden path="unitat.estat"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.unitat.estat,__theForm.listOfValuesForEstat)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UnitatFields.ESTAT)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="unitat_estat"  onchange="if(typeof onChangeEstat == 'function') {  onChangeEstat(this); };"  cssClass="form-control col-md-9-optional" path="unitat.estat">
            <c:forEach items="${__theForm.listOfValuesForEstat}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.unitat.estat }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.unitat.estat }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
