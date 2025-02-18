<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="HistoricFields" className="es.caib.rfhab.model.fields.HistoricFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${rfh:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,HistoricFields.HISTORICID)}">
        <th>${rfh:getSortIcons(__theFilterForm,HistoricFields.HISTORICID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,HistoricFields.FUNCIONARIID)}">
        <th>${rfh:getSortIcons(__theFilterForm,HistoricFields.FUNCIONARIID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,HistoricFields.NUMEROCAI)}">
        <th>${rfh:getSortIcons(__theFilterForm,HistoricFields.NUMEROCAI)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,HistoricFields.OBSERVACIONS)}">
        <th>${rfh:getSortIcons(__theFilterForm,HistoricFields.OBSERVACIONS)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,HistoricFields.DATACREACIO)}">
        <th>${rfh:getSortIcons(__theFilterForm,HistoricFields.DATACREACIO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,HistoricFields.USUARIID)}">
        <th>${rfh:getSortIcons(__theFilterForm,HistoricFields.USUARIID)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${rfh:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

