<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="ScanWebFields" className="es.caib.rfhab.model.fields.ScanWebFields"/>

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


        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ScanWebFields.DIGITALID)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label id="scanWeb_digitalID" style="display: inline;">
              <span class="add-on"><fmt:message key="scanWeb.digitalID" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="digitalIDDesde"  aria-labelledby="scanWeb_digitalID" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="digitalIDFins"  aria-labelledby="scanWeb_digitalID" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ScanWebFields.TRANSACTIONID)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label id="scanWeb_transactionID" style="display: inline;">
              <span class="add-on"><fmt:message key="scanWeb.transactionID" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="transactionIDDesde"  aria-labelledby="scanWeb_transactionID" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="transactionIDFins"  aria-labelledby="scanWeb_transactionID" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ScanWebFields.TRANSACTIONWEBID)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="transactionWebID" style="display: inline;">
              <fmt:message key="scanWeb.transactionWebID" var="transactionWebID" />
              <fmt:message key="genapp.form.searchby" var="cercapertransactionWebID" >                
                 <fmt:param value="${transactionWebID}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${transactionWebID}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercapertransactionWebID}" path="transactionWebID" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ScanWebFields.STATUS)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label id="scanWeb_status" style="display: inline;">
              <span class="add-on"><fmt:message key="scanWeb.status" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="statusDesde"  aria-labelledby="scanWeb_status" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="statusFins"  aria-labelledby="scanWeb_status" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ScanWebFields.FILEINFO)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="fileInfo" style="display: inline;">
              <fmt:message key="scanWeb.fileInfo" var="fileInfo" />
              <fmt:message key="genapp.form.searchby" var="cercaperfileInfo" >                
                 <fmt:param value="${fileInfo}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${fileInfo}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperfileInfo}" path="fileInfo" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ScanWebFields.SIGNEDFILEINFO)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="signedFileInfo" style="display: inline;">
              <fmt:message key="scanWeb.signedFileInfo" var="signedFileInfo" />
              <fmt:message key="genapp.form.searchby" var="cercapersignedFileInfo" >                
                 <fmt:param value="${signedFileInfo}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${signedFileInfo}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercapersignedFileInfo}" path="signedFileInfo" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ScanWebFields.METADADES)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="metadades" style="display: inline;">
              <fmt:message key="scanWeb.metadades" var="metadades" />
              <fmt:message key="genapp.form.searchby" var="cercapermetadades" >                
                 <fmt:param value="${metadades}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${metadades}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercapermetadades}" path="metadades" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ScanWebFields.MISSATGE)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="missatge" style="display: inline;">
              <fmt:message key="scanWeb.missatge" var="missatge" />
              <fmt:message key="genapp.form.searchby" var="cercapermissatge" >                
                 <fmt:param value="${missatge}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${missatge}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercapermissatge}" path="missatge" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ScanWebFields.USUARIID)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label id="scanWeb_usuariID" style="display: inline;">
              <span class="add-on"><fmt:message key="scanWeb.usuariID" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="usuariIDDesde"  aria-labelledby="scanWeb_usuariID" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="usuariIDFins"  aria-labelledby="scanWeb_usuariID" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ScanWebFields.DATACREACIO)}">
<%-- FILTRE DATE-TIME --%>
            <div class="input-group" style="padding-right:24px;padding-bottom:4px;align-items:center;">
              <label id="scanWeb_dataCreacio_label" style="display: inline;">
              <span class="add-on"><fmt:message key="scanWeb.dataCreacio" />:</span>
              </label>
              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="dataCreacioDesdeDiv" data-target-input="nearest">
                      <form:input  cssClass="form-control datetimepicker-input"  data-target="#dataCreacioDesdeDiv" path="dataCreacioDesde"  aria-labelledby="scanWeb_dataCreacio_label"   />
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
                      <form:input  cssClass="form-control datetimepicker-input"  data-target="#dataCreacioFinsDiv" path="dataCreacioFins"  aria-labelledby="scanWeb_dataCreacio_label"   />
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
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,ScanWebFields.ENTITATID)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label id="scanWeb_entitatID" style="display: inline;">
              <span class="add-on"><fmt:message key="scanWeb.entitatID" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="entitatIDDesde"  aria-labelledby="scanWeb_entitatID" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="entitatIDFins"  aria-labelledby="scanWeb_entitatID" />

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
  
