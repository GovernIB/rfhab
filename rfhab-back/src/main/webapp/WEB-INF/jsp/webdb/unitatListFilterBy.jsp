<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="UnitatFields" className="es.caib.rfhab.model.fields.UnitatFields"/>

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


        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UnitatFields.UNITATID)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label id="unitat_unitatID" style="display: inline;">
              <span class="add-on"><fmt:message key="unitat.unitatID" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="unitatIDDesde"  aria-labelledby="unitat_unitatID" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="unitatIDFins"  aria-labelledby="unitat_unitatID" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UnitatFields.CODI)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="codi" style="display: inline;">
              <fmt:message key="unitat.codi" var="codi" />
              <fmt:message key="genapp.form.searchby" var="cercapercodi" >                
                 <fmt:param value="${codi}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${codi}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercapercodi}" path="codi" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UnitatFields.VERSIO)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label id="unitat_versio" style="display: inline;">
              <span class="add-on"><fmt:message key="unitat.versio" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="versioDesde"  aria-labelledby="unitat_versio" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="versioFins"  aria-labelledby="unitat_versio" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UnitatFields.DENOMINACIO)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="denominacio" style="display: inline;">
              <fmt:message key="unitat.denominacio" var="denominacio" />
              <fmt:message key="genapp.form.searchby" var="cercaperdenominacio" >                
                 <fmt:param value="${denominacio}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${denominacio}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperdenominacio}" path="denominacio" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UnitatFields.COOFICIAL)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="cooficial" style="display: inline;">
              <fmt:message key="unitat.cooficial" var="cooficial" />
              <fmt:message key="genapp.form.searchby" var="cercapercooficial" >                
                 <fmt:param value="${cooficial}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${cooficial}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercapercooficial}" path="cooficial" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UnitatFields.ARREL)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="arrel" style="display: inline;">
              <fmt:message key="unitat.arrel" var="arrel" />
              <fmt:message key="genapp.form.searchby" var="cercaperarrel" >                
                 <fmt:param value="${arrel}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${arrel}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperarrel}" path="arrel" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UnitatFields.ARRELVERSIO)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label id="unitat_arrelVersio" style="display: inline;">
              <span class="add-on"><fmt:message key="unitat.arrelVersio" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="arrelVersioDesde"  aria-labelledby="unitat_arrelVersio" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="arrelVersioFins"  aria-labelledby="unitat_arrelVersio" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UnitatFields.SUPERIOR)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="superior" style="display: inline;">
              <fmt:message key="unitat.superior" var="superior" />
              <fmt:message key="genapp.form.searchby" var="cercapersuperior" >                
                 <fmt:param value="${superior}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${superior}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercapersuperior}" path="superior" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UnitatFields.SUPERIORVERSIO)}">
            <div class="input-group" style="padding-right: 24px;padding-bottom: 4px;">
            <%-- FILTRE NUMERO DESDE-FINS --%>
              <label id="unitat_superiorVersio" style="display: inline;">
              <span class="add-on"><fmt:message key="unitat.superiorVersio" />:</span>
              </label>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="superiorVersioDesde"  aria-labelledby="unitat_superiorVersio" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="superiorVersioFins"  aria-labelledby="unitat_superiorVersio" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,UnitatFields.ESTAT)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 24px;padding-bottom: 4px;">
              <label for="estat" style="display: inline;">
              <fmt:message key="unitat.estat" var="estat" />
              <fmt:message key="genapp.form.searchby" var="cercaperestat" >                
                 <fmt:param value="${estat}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${estat}" />:</span>
              </label>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperestat}" path="estat" />
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
  
