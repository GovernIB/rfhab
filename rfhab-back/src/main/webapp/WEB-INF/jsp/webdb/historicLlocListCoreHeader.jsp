<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="HistoricLlocFields" className="es.caib.rfhab.model.fields.HistoricLlocFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${rfh:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,HistoricLlocFields.HISTORICLLOCID)}">
        <th>${rfh:getSortIcons(__theFilterForm,HistoricLlocFields.HISTORICLLOCID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,HistoricLlocFields.LLOCID)}">
        <th>${rfh:getSortIcons(__theFilterForm,HistoricLlocFields.LLOCID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,HistoricLlocFields.NUMEROCAI)}">
        <th>${rfh:getSortIcons(__theFilterForm,HistoricLlocFields.NUMEROCAI)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,HistoricLlocFields.OBSERVACIONS)}">
        <th>${rfh:getSortIcons(__theFilterForm,HistoricLlocFields.OBSERVACIONS)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,HistoricLlocFields.DATACREACIO)}">
        <th>${rfh:getSortIcons(__theFilterForm,HistoricLlocFields.DATACREACIO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,HistoricLlocFields.USUARIID)}">
        <th>${rfh:getSortIcons(__theFilterForm,HistoricLlocFields.USUARIID)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${rfh:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

