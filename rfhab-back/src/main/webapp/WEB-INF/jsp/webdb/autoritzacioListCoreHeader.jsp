<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="AutoritzacioFields" className="es.caib.rfhab.model.fields.AutoritzacioFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${rfh:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AutoritzacioFields.AUTORITZACIOID)}">
        <th>${rfh:getSortIcons(__theFilterForm,AutoritzacioFields.AUTORITZACIOID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AutoritzacioFields.LLOCID)}">
        <th>${rfh:getSortIcons(__theFilterForm,AutoritzacioFields.LLOCID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AutoritzacioFields.CODISIA)}">
        <th>${rfh:getSortIcons(__theFilterForm,AutoritzacioFields.CODISIA)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AutoritzacioFields.PROCEDIMENT)}">
        <th>${rfh:getSortIcons(__theFilterForm,AutoritzacioFields.PROCEDIMENT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AutoritzacioFields.CAI)}">
        <th>${rfh:getSortIcons(__theFilterForm,AutoritzacioFields.CAI)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AutoritzacioFields.DATAINICI)}">
        <th>${rfh:getSortIcons(__theFilterForm,AutoritzacioFields.DATAINICI)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AutoritzacioFields.DATAFI)}">
        <th>${rfh:getSortIcons(__theFilterForm,AutoritzacioFields.DATAFI)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AutoritzacioFields.DATACREACIO)}">
        <th>${rfh:getSortIcons(__theFilterForm,AutoritzacioFields.DATACREACIO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AutoritzacioFields.OBSERVACIONS)}">
        <th>${rfh:getSortIcons(__theFilterForm,AutoritzacioFields.OBSERVACIONS)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AutoritzacioFields.USUARIID)}">
        <th>${rfh:getSortIcons(__theFilterForm,AutoritzacioFields.USUARIID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AutoritzacioFields.FUNCIONARIID)}">
        <th>${rfh:getSortIcons(__theFilterForm,AutoritzacioFields.FUNCIONARIID)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${rfh:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

