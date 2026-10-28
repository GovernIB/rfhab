<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="LlocFields" className="es.caib.rfhab.model.fields.LlocFields"/>

  <%-- HIDDEN PARAMS: FILTER BY --%> 
  <form:hidden path="visibleFilterBy"/>

  <%-- FILTRAR PER - INICI --%>
  
  <c:set var="displayFilterDiv" value="${__theFilterForm.visibleFilterBy?'':'display:none;'}" />  
  
  <div id="FilterDiv" class="wellgroupfilter formbox" style="${displayFilterDiv} margin-bottom:3px; margin-left: 1px; padding:3px;">

      <div class="page-header">
        <fmt:message key="genapp.form.filterby"/>
        
        <div class="float-right">

           <a class="float-right" style="margin-left:10px" href="#" title="<fmt:message key="genapp.form.hidefilter"/>" onclick="document.getElementById('FilterDiv').style.display='none'; document.getElementById('FilterButton').style.display='inline'; return false;"> <i aria-hidden="true" class="far fa-window-close"></i><span class="sr-only"><fmt:message key="genapp.form.hidefilter"/></span></a>
           <input style="margin-left: 3px" class="btn btn-sm btn-warning float-right" type="button" onclick="clear_form_elements(this.form)" value="<fmt:message key="genapp.form.clean"/>"/>
           <input style="margin-left: 3px" class="btn btn-sm btn-warning float-right" type="reset" value="<fmt:message key="genapp.form.reset"/>"/>
           <input style="margin-left: 3px" class="btn btn-sm btn-primary float-right" type="submit" value="<fmt:message key="genapp.form.search"/>"/>

        </div>
      </div>
      <div class="form-inline">
      
      <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
      <c:if test="${ __entry.key < 0 && not empty __entry.value.searchBy }">
      <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
        <label for="${__entry.value.codeName}" style="display: inline;">
        <span class="add-on"><fmt:message key="${__entry.value.codeName}" />:</span>
        </label>
        <fmt:message key="genapp.form.searchby" var="cercaperAF" >
          <fmt:param>
            <fmt:message key="${__entry.value.codeName}" />
          </fmt:param>
        </fmt:message>
        <c:choose>
          <c:when test="${gen:isFieldSearchInRange(__entry.value.searchBy)}">
            <span class="add-on"><fmt:message key="genapp.from" /></span>
            <input aria-label="${__entry.value.codeName}"  id="${__entry.value.searchBy.fullName}" name="${__entry.value.searchBy.fullName}" class="input-small input-medium" type="text" value="${__entry.value.searchByValue}"/>
            <span class="add-on"><fmt:message key="genapp.to" /></span>
            <input id="${__entry.value.searchBy.fullName}Fins" name="${__entry.value.searchBy.fullName}Fins" class="input-small input-medium search-query" type="text" value="${__entry.value.searchByValueFins}"/>
          </c:when>
          <c:otherwise>
            <input aria-label="${__entry.value.codeName}" id="${__entry.value.searchBy.fullName}" name="${__entry.value.searchBy.fullName}" class="search-query input-medium" placeholder="${cercaperAF}" type="text" value="${__entry.value.searchByValue}"/>
          </c:otherwise>
        </c:choose>
      </div>
      </c:if>
      </c:forEach>


        <c:if test="${gen:contains(__theFilterForm.filterByFields ,LlocFields.LLOCID)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label id="lloc_llocID" style="display: inline;">
              <span class="add-on"><fmt:message key="lloc.llocID" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="llocIDDesde"  aria-labelledby="lloc_llocID" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="llocIDFins"  aria-labelledby="lloc_llocID" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,LlocFields.CODILLOCPROPI)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="codiLlocPropi" style="display: inline;">
              <fmt:message key="lloc.codiLlocPropi" var="codiLlocPropi" />
              <fmt:message key="genapp.form.searchby" var="cercapercodiLlocPropi" >                
                 <fmt:param value="${codiLlocPropi}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${codiLlocPropi}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercapercodiLlocPropi}" path="codiLlocPropi" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,LlocFields.CODILLOC)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="codiLloc" style="display: inline;">
              <fmt:message key="lloc.codiLloc" var="codiLloc" />
              <fmt:message key="genapp.form.searchby" var="cercapercodiLloc" >                
                 <fmt:param value="${codiLloc}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${codiLloc}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercapercodiLloc}" path="codiLloc" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,LlocFields.EXPANSIO)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="expansio" style="display: inline;">
              <fmt:message key="lloc.expansio" var="expansio" />
              <fmt:message key="genapp.form.searchby" var="cercaperexpansio" >                
                 <fmt:param value="${expansio}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${expansio}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperexpansio}" path="expansio" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,LlocFields.NOM)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="nom" style="display: inline;">
              <fmt:message key="lloc.nom" var="nom" />
              <fmt:message key="genapp.form.searchby" var="cercapernom" >                
                 <fmt:param value="${nom}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${nom}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercapernom}" path="nom" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,LlocFields.ENTITATID)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label id="lloc_entitatID" style="display: inline;">
              <span class="add-on"><fmt:message key="lloc.entitatID" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="entitatIDDesde"  aria-labelledby="lloc_entitatID" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="entitatIDFins"  aria-labelledby="lloc_entitatID" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,LlocFields.UNITATID)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label id="lloc_unitatID" style="display: inline;">
              <span class="add-on"><fmt:message key="lloc.unitatID" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="unitatIDDesde"  aria-labelledby="lloc_unitatID" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="unitatIDFins"  aria-labelledby="lloc_unitatID" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,LlocFields.DATAALTA)}">
<%-- FILTRE DATE-TIME --%>
            <div class="input-group" style="padding-right:24px;padding-bottom:4px;align-items:center;">
              <label id="lloc_dataalta_label" style="display: inline;">
              <span class="add-on"><fmt:message key="lloc.dataalta" />:</span>
              </label>
              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="dataaltaDesdeDiv" data-target-input="nearest">
                      <form:input  cssClass="form-control datetimepicker-input"  data-target="#dataaltaDesdeDiv" path="dataaltaDesde"  aria-labelledby="lloc_dataalta_label"   />
                    <c:if test="${!false}" >
                    <div class="input-group-append"  data-target="#dataaltaDesdeDiv"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script>
            $(function () {
                $('#dataaltaDesdeDiv').datetimepicker({
                    format: '${gen:getJSDateTimePattern()}',
                    locale: '${lang}',
                    icons: {
                       time: 'far fa-clock'
                    }
                });
            });
        </script>
              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="dataaltaFinsDiv" data-target-input="nearest">
                      <form:input  cssClass="form-control datetimepicker-input"  data-target="#dataaltaFinsDiv" path="dataaltaFins"  aria-labelledby="lloc_dataalta_label"   />
                    <c:if test="${!false}" >
                    <div class="input-group-append"  data-target="#dataaltaFinsDiv"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script>
            $(function () {
                $('#dataaltaFinsDiv').datetimepicker({
                    format: '${gen:getJSDateTimePattern()}',
                    locale: '${lang}',
                    icons: {
                       time: 'far fa-clock'
                    }
                });
            });
        </script>
            </div>

    
        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,LlocFields.DATACREACIO)}">
<%-- FILTRE DATE-TIME --%>
            <div class="input-group" style="padding-right:24px;padding-bottom:4px;align-items:center;">
              <label id="lloc_dataCreacio_label" style="display: inline;">
              <span class="add-on"><fmt:message key="lloc.dataCreacio" />:</span>
              </label>
              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="dataCreacioDesdeDiv" data-target-input="nearest">
                      <form:input  cssClass="form-control datetimepicker-input"  data-target="#dataCreacioDesdeDiv" path="dataCreacioDesde"  aria-labelledby="lloc_dataCreacio_label"   />
                    <c:if test="${!false}" >
                    <div class="input-group-append"  data-target="#dataCreacioDesdeDiv"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script>
            $(function () {
                $('#dataCreacioDesdeDiv').datetimepicker({
                    format: '${gen:getJSDateTimePattern()}',
                    locale: '${lang}',
                    icons: {
                       time: 'far fa-clock'
                    }
                });
            });
        </script>
              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="dataCreacioFinsDiv" data-target-input="nearest">
                      <form:input  cssClass="form-control datetimepicker-input"  data-target="#dataCreacioFinsDiv" path="dataCreacioFins"  aria-labelledby="lloc_dataCreacio_label"   />
                    <c:if test="${!false}" >
                    <div class="input-group-append"  data-target="#dataCreacioFinsDiv"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script>
            $(function () {
                $('#dataCreacioFinsDiv').datetimepicker({
                    format: '${gen:getJSDateTimePattern()}',
                    locale: '${lang}',
                    icons: {
                       time: 'far fa-clock'
                    }
                });
            });
        </script>
            </div>

    
        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,LlocFields.DATABAIXA)}">
<%-- FILTRE DATE-TIME --%>
            <div class="input-group" style="padding-right:24px;padding-bottom:4px;align-items:center;">
              <label id="lloc_dataBaixa_label" style="display: inline;">
              <span class="add-on"><fmt:message key="lloc.dataBaixa" />:</span>
              </label>
              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="dataBaixaDesdeDiv" data-target-input="nearest">
                      <form:input  cssClass="form-control datetimepicker-input"  data-target="#dataBaixaDesdeDiv" path="dataBaixaDesde"  aria-labelledby="lloc_dataBaixa_label"   />
                    <c:if test="${!false}" >
                    <div class="input-group-append"  data-target="#dataBaixaDesdeDiv"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script>
            $(function () {
                $('#dataBaixaDesdeDiv').datetimepicker({
                    format: '${gen:getJSDateTimePattern()}',
                    locale: '${lang}',
                    icons: {
                       time: 'far fa-clock'
                    }
                });
            });
        </script>
              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="dataBaixaFinsDiv" data-target-input="nearest">
                      <form:input  cssClass="form-control datetimepicker-input"  data-target="#dataBaixaFinsDiv" path="dataBaixaFins"  aria-labelledby="lloc_dataBaixa_label"   />
                    <c:if test="${!false}" >
                    <div class="input-group-append"  data-target="#dataBaixaFinsDiv"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script>
            $(function () {
                $('#dataBaixaFinsDiv').datetimepicker({
                    format: '${gen:getJSDateTimePattern()}',
                    locale: '${lang}',
                    icons: {
                       time: 'far fa-clock'
                    }
                });
            });
        </script>
            </div>

    
        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,LlocFields.PERSONALOAMR)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
              <%-- FILTRE NUMERO SELECT MULTIPLE --%>
              <div class="input-group-prepend" style="padding-top: 5px;padding-right: 24px;">
              <label for="lloc_personalOamr_select" style="display: inline;">
                 <span class="add-on"><fmt:message key="lloc.personalOamr" />:</span>
              </label>
              </div>

              <div class="input-group-prepend" style="min-width:200px">
              <form:select id="lloc_personalOamr_select" path="personalOamrSelect"  cssClass="search-query input-medium form-control select2 select2-hidden-accessible" multiple="true" style="width:100%;" tabindex="-1" aria-hidden="true">
                    <c:forEach var="_entry" items="${__theFilterForm.mapOfValuesForPersonalOamr}">
                      <option value="${_entry.key}" ${fn:contains(__theFilterForm.personalOamrSelect, _entry.key)?'selected':''} >${_entry.value}</option>
                    </c:forEach>
              </form:select>
              </div>
              <script>
                $(document).ready(function() {
                    var $select = $('#lloc_personalOamr_select');
                    $select.select2({
                        closeOnSelect: false
                    });
                    $select.next('.select2-container')
                           .find('[role="combobox"]')
                           .attr('aria-label', "lloc_personalOamr_select");
                    $select.next('.select2-container')
                           .find('.select2-search__field')
                           .attr('aria-label', "lloc_personalOamr_select");

                    $('.select2-selection__rendered').css('padding-bottom','5px');
                });
              </script>
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,LlocFields.OBSERVACIONS)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="observacions" style="display: inline;">
              <fmt:message key="lloc.observacions" var="observacions" />
              <fmt:message key="genapp.form.searchby" var="cercaperobservacions" >                
                 <fmt:param value="${observacions}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${observacions}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperobservacions}" path="observacions" />
            </div>


        </c:if>

      <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
      <c:if test="${ __entry.key >= 0 && not empty __entry.value.searchBy }">
      <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
        <label for="${__entry.value.codeName}" style="display: inline;">
        <span class="add-on"><fmt:message key="${__entry.value.codeName}" />:</span>
        </label>
        <fmt:message key="genapp.form.searchby" var="cercaperAF" >
          <fmt:param>
            <fmt:message key="${__entry.value.codeName}" />
          </fmt:param>
        </fmt:message>
        <c:choose>
          <c:when test="${gen:isFieldSearchInRange(__entry.value.searchBy)}">
            <span class="add-on"><fmt:message key="genapp.from" /></span>
            <input aria-label="${__entry.value.codeName}"  id="${__entry.value.searchBy.fullName}" name="${__entry.value.searchBy.fullName}" class="input-small input-medium" type="text" value="${__entry.value.searchByValue}"/>
            <span class="add-on"><fmt:message key="genapp.to" /></span>
            <input id="${__entry.value.searchBy.fullName}Fins" name="${__entry.value.searchBy.fullName}Fins" class="input-small input-medium search-query" type="text" value="${__entry.value.searchByValueFins}"/>
          </c:when>
          <c:otherwise>
            <input aria-label="${__entry.value.codeName}" id="${__entry.value.searchBy.fullName}" name="${__entry.value.searchBy.fullName}" class="search-query input-medium" placeholder="${cercaperAF}" type="text" value="${__entry.value.searchByValue}"/>
          </c:otherwise>
        </c:choose>
      </div>
      </c:if>
      </c:forEach>
      </div>
    </div>



    <%-- FILTRAR PER - FINAL --%>
  
