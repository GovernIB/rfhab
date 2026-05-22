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

           <a class="float-right" style="margin-left:10px" href="#"> <i title="<fmt:message key="genapp.form.hidefilter"/>" onclick="document.getElementById('FilterDiv').style.display='none'; document.getElementById('FilterButton').style.display='inline';" class="far fa-window-close"></i></a>
           <input style="margin-left: 3px" class="btn btn-sm btn-warning float-right" type="button" onclick="clear_form_elements(this.form)" value="<fmt:message key="genapp.form.clean"/>"/>
           <input style="margin-left: 3px" class="btn btn-sm btn-warning float-right" type="reset" value="<fmt:message key="genapp.form.reset"/>"/>
           <input style="margin-left: 3px" class="btn btn-sm btn-primary float-right" type="submit" value="<fmt:message key="genapp.form.search"/>"/>

        </div>
      </div>
      <div class="form-inline">
      
      <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
      <c:if test="${ __entry.key < 0 && not empty __entry.value.searchBy }">
      <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
        <span class="add-on"><fmt:message key="${__entry.value.codeName}" />:</span>
        <fmt:message key="genapp.form.searchby" var="cercaperAF" >
          <fmt:param>
            <fmt:message key="${__entry.value.codeName}" />
          </fmt:param>
        </fmt:message>
        <c:choose>
          <c:when test="${gen:isFieldSearchInRange(__entry.value.searchBy)}">
            <span class="add-on"><fmt:message key="genapp.from" /></span>
            <input id="${__entry.value.searchBy.fullName}" name="${__entry.value.searchBy.fullName}" class="input-small input-medium" type="text" value="${__entry.value.searchByValue}"/>
            <span class="add-on"><fmt:message key="genapp.to" /></span>
            <input id="${__entry.value.searchBy.fullName}Fins" name="${__entry.value.searchBy.fullName}Fins" class="input-small input-medium search-query" type="text" value="${__entry.value.searchByValueFins}"/>
          </c:when>
          <c:otherwise>
            <input id="${__entry.value.searchBy.fullName}" name="${__entry.value.searchBy.fullName}" class="search-query input-medium" placeholder="${cercaperAF}" type="text" value="${__entry.value.searchByValue}"/>
          </c:otherwise>
        </c:choose>
      </div>
      </c:if>
      </c:forEach>


        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ActivitatFields.ACTIVITATID)}">
            <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <span class="add-on"><fmt:message key="activitat.activitatID" />:</span>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="activitatIDDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="activitatIDFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ActivitatFields.FUNCIONARIID)}">
            <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <span class="add-on"><fmt:message key="activitat.funcionariID" />:</span>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="funcionariIDDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="funcionariIDFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ActivitatFields.TIPUS)}">
            <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
              <%-- FILTRE NUMERO SELECT MULTIPLE --%>
              <div class="input-group-prepend" style="padding-top: 5px;padding-right: 5px;">
                 <span class="add-on"><fmt:message key="activitat.tipus" />:</span>
              </div>

              <div class="input-group-prepend" style="min-width:200px">
                <form:select id="activitat_tipus_select" path="tipusSelect" cssClass="search-query input-medium form-control select2 select2-hidden-accessible" multiple="true" style="width:100%;" tabindex="-1" aria-hidden="true">
                    <c:forEach var="_entry" items="${__theFilterForm.mapOfValuesForTipus}">
                      <option value="${_entry.key}" ${fn:contains(__theFilterForm.tipusSelect, _entry.key)?'selected':''} >${_entry.value}</option>
                    </c:forEach>
                </form:select>
              </div>

              <script type="text/javascript">
                $(document).ready(function() {
                    $('#activitat_tipus_select').select2({
                        closeOnSelect: false
                    });
                    $('.select2-selection__rendered').css('padding-bottom','5px');
                });
              </script>
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ActivitatFields.REGISTRE)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="activitat.registre" var="registre" />
              <fmt:message key="genapp.form.searchby" var="cercaperregistre" >                
                 <fmt:param value="${registre}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${registre}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperregistre}" path="registre" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ActivitatFields.TRAMIT)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="activitat.tramit" var="tramit" />
              <fmt:message key="genapp.form.searchby" var="cercapertramit" >                
                 <fmt:param value="${tramit}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${tramit}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapertramit}" path="tramit" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ActivitatFields.CODISIA)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="activitat.codiSia" var="codiSia" />
              <fmt:message key="genapp.form.searchby" var="cercapercodiSia" >                
                 <fmt:param value="${codiSia}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${codiSia}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapercodiSia}" path="codiSia" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ActivitatFields.AUTORITZACIOID)}">
            <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <span class="add-on"><fmt:message key="activitat.autoritzacioID" />:</span>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="autoritzacioIDDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="autoritzacioIDFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ActivitatFields.DATACREACIO)}">
<%-- FILTRE DATE-TIME --%>
            <div class="input-group" style="padding-right:4px;padding-bottom:4px;align-items:center;">
              <span class="add-on"><fmt:message key="activitat.dataCreacio" />:</span>
              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="dataCreacioDesde" data-target-input="nearest">
                      <form:input  cssClass="form-control datetimepicker-input"  data-target="#dataCreacioDesde" path="dataCreacioDesde" />
                    <c:if test="${!false}" >
                    <div class="input-group-append"  data-target="#dataCreacioDesde"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#dataCreacioDesde').datetimepicker({
                    format: '${gen:getJSDateTimePattern()}',
                    locale: '${lang}',
                    icons: {
                       time: 'far fa-clock'
                    }
                });
            });
        </script>              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="dataCreacioFins" data-target-input="nearest">
                      <form:input  cssClass="form-control datetimepicker-input"  data-target="#dataCreacioFins" path="dataCreacioFins" />
                    <c:if test="${!false}" >
                    <div class="input-group-append"  data-target="#dataCreacioFins"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#dataCreacioFins').datetimepicker({
                    format: '${gen:getJSDateTimePattern()}',
                    locale: '${lang}',
                    icons: {
                       time: 'far fa-clock'
                    }
                });
            });
        </script>            </div>

    
        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ActivitatFields.INTERESSATNOM)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="activitat.interessatNom" var="interessatNom" />
              <fmt:message key="genapp.form.searchby" var="cercaperinteressatNom" >                
                 <fmt:param value="${interessatNom}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${interessatNom}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperinteressatNom}" path="interessatNom" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ActivitatFields.INTERESSATLLINATGE1)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="activitat.interessatLlinatge1" var="interessatLlinatge1" />
              <fmt:message key="genapp.form.searchby" var="cercaperinteressatLlinatge1" >                
                 <fmt:param value="${interessatLlinatge1}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${interessatLlinatge1}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperinteressatLlinatge1}" path="interessatLlinatge1" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ActivitatFields.INTERESSATLLINATGE2)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="activitat.interessatLlinatge2" var="interessatLlinatge2" />
              <fmt:message key="genapp.form.searchby" var="cercaperinteressatLlinatge2" >                
                 <fmt:param value="${interessatLlinatge2}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${interessatLlinatge2}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperinteressatLlinatge2}" path="interessatLlinatge2" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ActivitatFields.INTERESSATTIPUS)}">
            <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <span class="add-on"><fmt:message key="activitat.interessatTipus" />:</span>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="interessatTipusDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="interessatTipusFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ActivitatFields.INTERESSATIDENTIFICACIO)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="activitat.interessatIdentificacio" var="interessatIdentificacio" />
              <fmt:message key="genapp.form.searchby" var="cercaperinteressatIdentificacio" >                
                 <fmt:param value="${interessatIdentificacio}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${interessatIdentificacio}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperinteressatIdentificacio}" path="interessatIdentificacio" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ActivitatFields.REPRESENTANTNOM)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="activitat.representantNom" var="representantNom" />
              <fmt:message key="genapp.form.searchby" var="cercaperrepresentantNom" >                
                 <fmt:param value="${representantNom}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${representantNom}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperrepresentantNom}" path="representantNom" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ActivitatFields.REPRESENTANTLLINATGE1)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="activitat.representantLlinatge1" var="representantLlinatge1" />
              <fmt:message key="genapp.form.searchby" var="cercaperrepresentantLlinatge1" >                
                 <fmt:param value="${representantLlinatge1}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${representantLlinatge1}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperrepresentantLlinatge1}" path="representantLlinatge1" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ActivitatFields.REPRESENTANTLLINATGE2)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="activitat.representantLlinatge2" var="representantLlinatge2" />
              <fmt:message key="genapp.form.searchby" var="cercaperrepresentantLlinatge2" >                
                 <fmt:param value="${representantLlinatge2}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${representantLlinatge2}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperrepresentantLlinatge2}" path="representantLlinatge2" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ActivitatFields.REPRESENTANTTIPUS)}">
            <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <span class="add-on"><fmt:message key="activitat.representantTipus" />:</span>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="representantTipusDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="representantTipusFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ActivitatFields.REPRESENTANTIDENTIFICACIO)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="activitat.representantIdentificacio" var="representantIdentificacio" />
              <fmt:message key="genapp.form.searchby" var="cercaperrepresentantIdentificacio" >                
                 <fmt:param value="${representantIdentificacio}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${representantIdentificacio}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperrepresentantIdentificacio}" path="representantIdentificacio" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ActivitatFields.TRAMITVERSIO)}">
            <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <span class="add-on"><fmt:message key="activitat.tramitVersio" />:</span>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="tramitVersioDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="tramitVersioFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ActivitatFields.ARXIUDOCUMENTID)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="activitat.arxiuDocumentID" var="arxiuDocumentID" />
              <fmt:message key="genapp.form.searchby" var="cercaperarxiuDocumentID" >                
                 <fmt:param value="${arxiuDocumentID}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${arxiuDocumentID}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperarxiuDocumentID}" path="arxiuDocumentID" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ActivitatFields.ARXIUEXPEDIENTID)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="activitat.arxiuExpedientID" var="arxiuExpedientID" />
              <fmt:message key="genapp.form.searchby" var="cercaperarxiuExpedientID" >                
                 <fmt:param value="${arxiuExpedientID}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${arxiuExpedientID}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperarxiuExpedientID}" path="arxiuExpedientID" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ActivitatFields.ESTAT)}">
            <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
              <%-- FILTRE NUMERO SELECT MULTIPLE --%>
              <div class="input-group-prepend" style="padding-top: 5px;padding-right: 5px;">
                 <span class="add-on"><fmt:message key="activitat.estat" />:</span>
              </div>

              <div class="input-group-prepend" style="min-width:200px">
                <form:select id="activitat_estat_select" path="estatSelect" cssClass="search-query input-medium form-control select2 select2-hidden-accessible" multiple="true" style="width:100%;" tabindex="-1" aria-hidden="true">
                    <c:forEach var="_entry" items="${__theFilterForm.mapOfValuesForEstat}">
                      <option value="${_entry.key}" ${fn:contains(__theFilterForm.estatSelect, _entry.key)?'selected':''} >${_entry.value}</option>
                    </c:forEach>
                </form:select>
              </div>

              <script type="text/javascript">
                $(document).ready(function() {
                    $('#activitat_estat_select').select2({
                        closeOnSelect: false
                    });
                    $('.select2-selection__rendered').css('padding-bottom','5px');
                });
              </script>
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ActivitatFields.URL)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="activitat.url" var="url" />
              <fmt:message key="genapp.form.searchby" var="cercaperurl" >                
                 <fmt:param value="${url}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${url}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperurl}" path="url" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ActivitatFields.DATAACTIVITAT)}">
<%-- FILTRE DATE-TIME --%>
            <div class="input-group" style="padding-right:4px;padding-bottom:4px;align-items:center;">
              <span class="add-on"><fmt:message key="activitat.dataActivitat" />:</span>
              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="dataActivitatDesde" data-target-input="nearest">
                      <form:input  cssClass="form-control datetimepicker-input"  data-target="#dataActivitatDesde" path="dataActivitatDesde" />
                    <c:if test="${!false}" >
                    <div class="input-group-append"  data-target="#dataActivitatDesde"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#dataActivitatDesde').datetimepicker({
                    format: '${gen:getJSDateTimePattern()}',
                    locale: '${lang}',
                    icons: {
                       time: 'far fa-clock'
                    }
                });
            });
        </script>              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="dataActivitatFins" data-target-input="nearest">
                      <form:input  cssClass="form-control datetimepicker-input"  data-target="#dataActivitatFins" path="dataActivitatFins" />
                    <c:if test="${!false}" >
                    <div class="input-group-append"  data-target="#dataActivitatFins"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#dataActivitatFins').datetimepicker({
                    format: '${gen:getJSDateTimePattern()}',
                    locale: '${lang}',
                    icons: {
                       time: 'far fa-clock'
                    }
                });
            });
        </script>            </div>

    
        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ActivitatFields.IDACTUACIOTRAMIT)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="activitat.idActuacioTramit" var="idActuacioTramit" />
              <fmt:message key="genapp.form.searchby" var="cercaperidActuacioTramit" >                
                 <fmt:param value="${idActuacioTramit}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${idActuacioTramit}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperidActuacioTramit}" path="idActuacioTramit" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ActivitatFields.PROCEDIMENT)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="activitat.procediment" var="procediment" />
              <fmt:message key="genapp.form.searchby" var="cercaperprocediment" >                
                 <fmt:param value="${procediment}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${procediment}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperprocediment}" path="procediment" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ActivitatFields.ARXIUREINTENTS)}">
            <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <span class="add-on"><fmt:message key="activitat.arxiuReintents" />:</span>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="arxiuReintentsDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="arxiuReintentsFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ActivitatFields.ARXIUESTAT)}">
            <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <span class="add-on"><fmt:message key="activitat.arxiuEstat" />:</span>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="arxiuEstatDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="arxiuEstatFins" />

            </div>


        </c:if>

      <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
      <c:if test="${ __entry.key >= 0 && not empty __entry.value.searchBy }">
      <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
        <span class="add-on"><fmt:message key="${__entry.value.codeName}" />:</span>
        <fmt:message key="genapp.form.searchby" var="cercaperAF" >
          <fmt:param>
            <fmt:message key="${__entry.value.codeName}" />
          </fmt:param>
        </fmt:message>
        <c:choose>
          <c:when test="${gen:isFieldSearchInRange(__entry.value.searchBy)}">
            <span class="add-on"><fmt:message key="genapp.from" /></span>
            <input id="${__entry.value.searchBy.fullName}" name="${__entry.value.searchBy.fullName}" class="input-small input-medium" type="text" value="${__entry.value.searchByValue}"/>
            <span class="add-on"><fmt:message key="genapp.to" /></span>
            <input id="${__entry.value.searchBy.fullName}Fins" name="${__entry.value.searchBy.fullName}Fins" class="input-small input-medium search-query" type="text" value="${__entry.value.searchByValueFins}"/>
          </c:when>
          <c:otherwise>
            <input id="${__entry.value.searchBy.fullName}" name="${__entry.value.searchBy.fullName}" class="search-query input-medium" placeholder="${cercaperAF}" type="text" value="${__entry.value.searchByValue}"/>
          </c:otherwise>
        </c:choose>
      </div>
      </c:if>
      </c:forEach>
      </div>
    </div>



    <%-- FILTRAR PER - FINAL --%>
  
