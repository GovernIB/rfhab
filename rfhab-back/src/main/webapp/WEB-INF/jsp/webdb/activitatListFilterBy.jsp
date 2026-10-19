<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="ActivitatFields" className="es.caib.rfhab.model.fields.ActivitatFields"/>

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


        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ActivitatFields.ACTIVITATID)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label for="activitat_activitatID" style="display: inline;">
              <span class="add-on"><fmt:message key="activitat.activitatID" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="activitatIDDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="activitatIDFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ActivitatFields.FUNCIONARIID)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label for="activitat_funcionariID" style="display: inline;">
              <span class="add-on"><fmt:message key="activitat.funcionariID" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="funcionariIDDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="funcionariIDFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ActivitatFields.TIPUS)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
              <%-- FILTRE NUMERO SELECT MULTIPLE --%>
              <div class="input-group-prepend" style="padding-top: 5px;padding-right: 24px;">
              <label for="activitat_tipus_select" style="display: inline;">
                 <span class="add-on"><fmt:message key="activitat.tipus" />:</span>
              </label>
              </div>

              <div class="input-group-prepend" style="min-width:200px">
                <form:select aria-label="activitat_tipus_select"   id="activitat_tipus_select" path="tipusSelect" cssClass="search-query input-medium form-control select2 select2-hidden-accessible" multiple="true" style="width:100%;" tabindex="-1" aria-hidden="true">
                    <c:forEach var="_entry" items="${__theFilterForm.mapOfValuesForTipus}">
                      <option value="${_entry.key}" ${fn:contains(__theFilterForm.tipusSelect, _entry.key)?'selected':''} >${_entry.value}</option>
                    </c:forEach>
                </form:select>
              </div>

              <script>
                $(document).ready(function() {
                    var $select = $('#activitat_tipus_select');
                    var ariaLabel = $select.attr('aria-label');

                    $select.select2({
                        closeOnSelect: false
                    });

                    if (ariaLabel) {
                        $select.next('.select2-container')
                               .find('[role="combobox"]')
                               .attr('aria-label', ariaLabel);
                        $select.next('.select2-container')
                               .find('.select2-search__field')
                               .attr('aria-label', ariaLabel);
                    }

                    $('.select2-selection__rendered').css('padding-bottom','5px');
                });
              </script>
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ActivitatFields.REGISTRE)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="registre" style="display: inline;">
              <fmt:message key="activitat.registre" var="registre" />
              <fmt:message key="genapp.form.searchby" var="cercaperregistre" >                
                 <fmt:param value="${registre}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${registre}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperregistre}" path="registre" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ActivitatFields.TRAMIT)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="tramit" style="display: inline;">
              <fmt:message key="activitat.tramit" var="tramit" />
              <fmt:message key="genapp.form.searchby" var="cercapertramit" >                
                 <fmt:param value="${tramit}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${tramit}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercapertramit}" path="tramit" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ActivitatFields.CODISIA)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="codiSia" style="display: inline;">
              <fmt:message key="activitat.codiSia" var="codiSia" />
              <fmt:message key="genapp.form.searchby" var="cercapercodiSia" >                
                 <fmt:param value="${codiSia}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${codiSia}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercapercodiSia}" path="codiSia" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ActivitatFields.AUTORITZACIOID)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label for="activitat_autoritzacioID" style="display: inline;">
              <span class="add-on"><fmt:message key="activitat.autoritzacioID" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="autoritzacioIDDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="autoritzacioIDFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ActivitatFields.DATACREACIO)}">
<%-- FILTRE DATE-TIME --%>
            <div class="input-group" style="padding-right:24px;padding-bottom:4px;align-items:center;">
              <label id="activitat_dataCreacio_label" style="display: inline;">
              <span class="add-on"><fmt:message key="activitat.dataCreacio" />:</span>
              </label>
              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="dataCreacioDesdeDiv" data-target-input="nearest">
                      <form:input  cssClass="form-control datetimepicker-input"  data-target="#dataCreacioDesde" path="dataCreacioDesde" aria-labelledby="activitat_dataCreacio_label"  />
                    <c:if test="${!false}" >
                    <div class="input-group-append"  data-target="#dataCreacioDesde"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script>
            $(function () {
                $('#dataCreacioDesde').datetimepicker({
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
                      <form:input  cssClass="form-control datetimepicker-input"  data-target="#dataCreacioFins" path="dataCreacioFins" aria-labelledby="activitat_dataCreacio_label"  />
                    <c:if test="${!false}" >
                    <div class="input-group-append"  data-target="#dataCreacioFins"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script>
            $(function () {
                $('#dataCreacioFins').datetimepicker({
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
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ActivitatFields.INTERESSATNOM)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="interessatNom" style="display: inline;">
              <fmt:message key="activitat.interessatNom" var="interessatNom" />
              <fmt:message key="genapp.form.searchby" var="cercaperinteressatNom" >                
                 <fmt:param value="${interessatNom}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${interessatNom}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperinteressatNom}" path="interessatNom" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ActivitatFields.INTERESSATLLINATGE1)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="interessatLlinatge1" style="display: inline;">
              <fmt:message key="activitat.interessatLlinatge1" var="interessatLlinatge1" />
              <fmt:message key="genapp.form.searchby" var="cercaperinteressatLlinatge1" >                
                 <fmt:param value="${interessatLlinatge1}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${interessatLlinatge1}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperinteressatLlinatge1}" path="interessatLlinatge1" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ActivitatFields.INTERESSATLLINATGE2)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="interessatLlinatge2" style="display: inline;">
              <fmt:message key="activitat.interessatLlinatge2" var="interessatLlinatge2" />
              <fmt:message key="genapp.form.searchby" var="cercaperinteressatLlinatge2" >                
                 <fmt:param value="${interessatLlinatge2}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${interessatLlinatge2}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperinteressatLlinatge2}" path="interessatLlinatge2" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ActivitatFields.INTERESSATTIPUS)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label for="activitat_interessatTipus" style="display: inline;">
              <span class="add-on"><fmt:message key="activitat.interessatTipus" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="interessatTipusDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="interessatTipusFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ActivitatFields.INTERESSATIDENTIFICACIO)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="interessatIdentificacio" style="display: inline;">
              <fmt:message key="activitat.interessatIdentificacio" var="interessatIdentificacio" />
              <fmt:message key="genapp.form.searchby" var="cercaperinteressatIdentificacio" >                
                 <fmt:param value="${interessatIdentificacio}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${interessatIdentificacio}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperinteressatIdentificacio}" path="interessatIdentificacio" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ActivitatFields.REPRESENTANTNOM)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="representantNom" style="display: inline;">
              <fmt:message key="activitat.representantNom" var="representantNom" />
              <fmt:message key="genapp.form.searchby" var="cercaperrepresentantNom" >                
                 <fmt:param value="${representantNom}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${representantNom}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperrepresentantNom}" path="representantNom" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ActivitatFields.REPRESENTANTLLINATGE1)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="representantLlinatge1" style="display: inline;">
              <fmt:message key="activitat.representantLlinatge1" var="representantLlinatge1" />
              <fmt:message key="genapp.form.searchby" var="cercaperrepresentantLlinatge1" >                
                 <fmt:param value="${representantLlinatge1}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${representantLlinatge1}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperrepresentantLlinatge1}" path="representantLlinatge1" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ActivitatFields.REPRESENTANTLLINATGE2)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="representantLlinatge2" style="display: inline;">
              <fmt:message key="activitat.representantLlinatge2" var="representantLlinatge2" />
              <fmt:message key="genapp.form.searchby" var="cercaperrepresentantLlinatge2" >                
                 <fmt:param value="${representantLlinatge2}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${representantLlinatge2}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperrepresentantLlinatge2}" path="representantLlinatge2" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ActivitatFields.REPRESENTANTTIPUS)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label for="activitat_representantTipus" style="display: inline;">
              <span class="add-on"><fmt:message key="activitat.representantTipus" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="representantTipusDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="representantTipusFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ActivitatFields.REPRESENTANTIDENTIFICACIO)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="representantIdentificacio" style="display: inline;">
              <fmt:message key="activitat.representantIdentificacio" var="representantIdentificacio" />
              <fmt:message key="genapp.form.searchby" var="cercaperrepresentantIdentificacio" >                
                 <fmt:param value="${representantIdentificacio}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${representantIdentificacio}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperrepresentantIdentificacio}" path="representantIdentificacio" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ActivitatFields.TRAMITVERSIO)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label for="activitat_tramitVersio" style="display: inline;">
              <span class="add-on"><fmt:message key="activitat.tramitVersio" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="tramitVersioDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="tramitVersioFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ActivitatFields.ARXIUDOCUMENTID)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="arxiuDocumentID" style="display: inline;">
              <fmt:message key="activitat.arxiuDocumentID" var="arxiuDocumentID" />
              <fmt:message key="genapp.form.searchby" var="cercaperarxiuDocumentID" >                
                 <fmt:param value="${arxiuDocumentID}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${arxiuDocumentID}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperarxiuDocumentID}" path="arxiuDocumentID" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ActivitatFields.ARXIUEXPEDIENTID)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="arxiuExpedientID" style="display: inline;">
              <fmt:message key="activitat.arxiuExpedientID" var="arxiuExpedientID" />
              <fmt:message key="genapp.form.searchby" var="cercaperarxiuExpedientID" >                
                 <fmt:param value="${arxiuExpedientID}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${arxiuExpedientID}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperarxiuExpedientID}" path="arxiuExpedientID" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ActivitatFields.ESTAT)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
              <%-- FILTRE NUMERO SELECT MULTIPLE --%>
              <div class="input-group-prepend" style="padding-top: 5px;padding-right: 24px;">
              <label for="activitat_estat_select" style="display: inline;">
                 <span class="add-on"><fmt:message key="activitat.estat" />:</span>
              </label>
              </div>

              <div class="input-group-prepend" style="min-width:200px">
                <form:select aria-label="activitat_estat_select"   id="activitat_estat_select" path="estatSelect" cssClass="search-query input-medium form-control select2 select2-hidden-accessible" multiple="true" style="width:100%;" tabindex="-1" aria-hidden="true">
                    <c:forEach var="_entry" items="${__theFilterForm.mapOfValuesForEstat}">
                      <option value="${_entry.key}" ${fn:contains(__theFilterForm.estatSelect, _entry.key)?'selected':''} >${_entry.value}</option>
                    </c:forEach>
                </form:select>
              </div>

              <script>
                $(document).ready(function() {
                    var $select = $('#activitat_estat_select');
                    var ariaLabel = $select.attr('aria-label');

                    $select.select2({
                        closeOnSelect: false
                    });

                    if (ariaLabel) {
                        $select.next('.select2-container')
                               .find('[role="combobox"]')
                               .attr('aria-label', ariaLabel);
                        $select.next('.select2-container')
                               .find('.select2-search__field')
                               .attr('aria-label', ariaLabel);
                    }

                    $('.select2-selection__rendered').css('padding-bottom','5px');
                });
              </script>
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ActivitatFields.URL)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="url" style="display: inline;">
              <fmt:message key="activitat.url" var="url" />
              <fmt:message key="genapp.form.searchby" var="cercaperurl" >                
                 <fmt:param value="${url}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${url}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperurl}" path="url" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ActivitatFields.DATAACTIVITAT)}">
<%-- FILTRE DATE-TIME --%>
            <div class="input-group" style="padding-right:24px;padding-bottom:4px;align-items:center;">
              <label id="activitat_dataActivitat_label" style="display: inline;">
              <span class="add-on"><fmt:message key="activitat.dataActivitat" />:</span>
              </label>
              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="dataActivitatDesdeDiv" data-target-input="nearest">
                      <form:input  cssClass="form-control datetimepicker-input"  data-target="#dataActivitatDesde" path="dataActivitatDesde" aria-labelledby="activitat_dataActivitat_label"  />
                    <c:if test="${!false}" >
                    <div class="input-group-append"  data-target="#dataActivitatDesde"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script>
            $(function () {
                $('#dataActivitatDesde').datetimepicker({
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
                <div class="input-group date" id="dataActivitatFinsDiv" data-target-input="nearest">
                      <form:input  cssClass="form-control datetimepicker-input"  data-target="#dataActivitatFins" path="dataActivitatFins" aria-labelledby="activitat_dataActivitat_label"  />
                    <c:if test="${!false}" >
                    <div class="input-group-append"  data-target="#dataActivitatFins"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script>
            $(function () {
                $('#dataActivitatFins').datetimepicker({
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
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ActivitatFields.IDACTUACIOTRAMIT)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="idActuacioTramit" style="display: inline;">
              <fmt:message key="activitat.idActuacioTramit" var="idActuacioTramit" />
              <fmt:message key="genapp.form.searchby" var="cercaperidActuacioTramit" >                
                 <fmt:param value="${idActuacioTramit}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${idActuacioTramit}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperidActuacioTramit}" path="idActuacioTramit" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ActivitatFields.PROCEDIMENT)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="procediment" style="display: inline;">
              <fmt:message key="activitat.procediment" var="procediment" />
              <fmt:message key="genapp.form.searchby" var="cercaperprocediment" >                
                 <fmt:param value="${procediment}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${procediment}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperprocediment}" path="procediment" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ActivitatFields.ARXIUREINTENTS)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label for="activitat_arxiuReintents" style="display: inline;">
              <span class="add-on"><fmt:message key="activitat.arxiuReintents" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="arxiuReintentsDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="arxiuReintentsFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ActivitatFields.ARXIUESTAT)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label for="activitat_arxiuEstat" style="display: inline;">
              <span class="add-on"><fmt:message key="activitat.arxiuEstat" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="arxiuEstatDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="arxiuEstatFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ActivitatFields.ARXIUDARRERINTENT)}">
<%-- FILTRE DATE-TIME --%>
            <div class="input-group" style="padding-right:24px;padding-bottom:4px;align-items:center;">
              <label id="activitat_arxiuDarrerIntent_label" style="display: inline;">
              <span class="add-on"><fmt:message key="activitat.arxiuDarrerIntent" />:</span>
              </label>
              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="arxiuDarrerIntentDesdeDiv" data-target-input="nearest">
                      <form:input  cssClass="form-control datetimepicker-input"  data-target="#arxiuDarrerIntentDesde" path="arxiuDarrerIntentDesde" aria-labelledby="activitat_arxiuDarrerIntent_label"  />
                    <c:if test="${!false}" >
                    <div class="input-group-append"  data-target="#arxiuDarrerIntentDesde"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script>
            $(function () {
                $('#arxiuDarrerIntentDesde').datetimepicker({
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
                <div class="input-group date" id="arxiuDarrerIntentFinsDiv" data-target-input="nearest">
                      <form:input  cssClass="form-control datetimepicker-input"  data-target="#arxiuDarrerIntentFins" path="arxiuDarrerIntentFins" aria-labelledby="activitat_arxiuDarrerIntent_label"  />
                    <c:if test="${!false}" >
                    <div class="input-group-append"  data-target="#arxiuDarrerIntentFins"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script>
            $(function () {
                $('#arxiuDarrerIntentFins').datetimepicker({
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
  
