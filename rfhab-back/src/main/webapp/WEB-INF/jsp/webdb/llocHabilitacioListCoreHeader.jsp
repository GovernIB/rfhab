<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="LlocHabilitacioFields" className="es.caib.rfhab.model.fields.LlocHabilitacioFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${rfh:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,LlocHabilitacioFields.LLOCHABILITACIOID)}">
        <th>${rfh:getSortIcons(__theFilterForm,LlocHabilitacioFields.LLOCHABILITACIOID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,LlocHabilitacioFields.DATACREACIO)}">
        <th>${rfh:getSortIcons(__theFilterForm,LlocHabilitacioFields.DATACREACIO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,LlocHabilitacioFields.LLOCID)}">
        <th>${rfh:getSortIcons(__theFilterForm,LlocHabilitacioFields.LLOCID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,LlocHabilitacioFields.HABILITACIOID)}">
        <th>${rfh:getSortIcons(__theFilterForm,LlocHabilitacioFields.HABILITACIOID)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${rfh:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

