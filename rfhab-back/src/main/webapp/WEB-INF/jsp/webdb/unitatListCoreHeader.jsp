<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="UnitatFields" className="es.caib.rfhab.model.fields.UnitatFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${rfh:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UnitatFields.UNITATID)}">
        <th>${rfh:getSortIcons(__theFilterForm,UnitatFields.UNITATID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UnitatFields.CODI)}">
        <th>${rfh:getSortIcons(__theFilterForm,UnitatFields.CODI)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UnitatFields.VERSIO)}">
        <th>${rfh:getSortIcons(__theFilterForm,UnitatFields.VERSIO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UnitatFields.DENOMINACIO)}">
        <th>${rfh:getSortIcons(__theFilterForm,UnitatFields.DENOMINACIO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UnitatFields.COOFICIAL)}">
        <th>${rfh:getSortIcons(__theFilterForm,UnitatFields.COOFICIAL)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UnitatFields.ARREL)}">
        <th>${rfh:getSortIcons(__theFilterForm,UnitatFields.ARREL)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UnitatFields.ARRELVERSIO)}">
        <th>${rfh:getSortIcons(__theFilterForm,UnitatFields.ARRELVERSIO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UnitatFields.SUPERIOR)}">
        <th>${rfh:getSortIcons(__theFilterForm,UnitatFields.SUPERIOR)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UnitatFields.SUPERIORVERSIO)}">
        <th>${rfh:getSortIcons(__theFilterForm,UnitatFields.SUPERIORVERSIO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,UnitatFields.ESTAT)}">
        <th>${rfh:getSortIcons(__theFilterForm,UnitatFields.ESTAT)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${rfh:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

