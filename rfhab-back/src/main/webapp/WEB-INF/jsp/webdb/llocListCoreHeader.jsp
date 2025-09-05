<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="LlocFields" className="es.caib.rfhab.model.fields.LlocFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${rfh:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,LlocFields.LLOCID)}">
        <th>${rfh:getSortIcons(__theFilterForm,LlocFields.LLOCID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,LlocFields.CODILLOC)}">
        <th>${rfh:getSortIcons(__theFilterForm,LlocFields.CODILLOC)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,LlocFields.CODILLOCPROPI)}">
        <th>${rfh:getSortIcons(__theFilterForm,LlocFields.CODILLOCPROPI)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,LlocFields.EXPANSIO)}">
        <th>${rfh:getSortIcons(__theFilterForm,LlocFields.EXPANSIO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,LlocFields.NOM)}">
        <th>${rfh:getSortIcons(__theFilterForm,LlocFields.NOM)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,LlocFields.ENTITATID)}">
        <th>${rfh:getSortIcons(__theFilterForm,LlocFields.ENTITATID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,LlocFields.UNITATID)}">
        <th>${rfh:getSortIcons(__theFilterForm,LlocFields.UNITATID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,LlocFields.PERSONALOAMR)}">
        <th>${rfh:getSortIcons(__theFilterForm,LlocFields.PERSONALOAMR)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,LlocFields.DATAALTA)}">
        <th>${rfh:getSortIcons(__theFilterForm,LlocFields.DATAALTA)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,LlocFields.DATACREACIO)}">
        <th>${rfh:getSortIcons(__theFilterForm,LlocFields.DATACREACIO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,LlocFields.DATABAIXA)}">
        <th>${rfh:getSortIcons(__theFilterForm,LlocFields.DATABAIXA)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,LlocFields.OBSERVACIONS)}">
        <th>${rfh:getSortIcons(__theFilterForm,LlocFields.OBSERVACIONS)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${rfh:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

