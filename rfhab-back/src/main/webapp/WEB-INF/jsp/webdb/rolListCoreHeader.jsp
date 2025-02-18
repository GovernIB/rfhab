<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="RolFields" className="es.caib.rfhab.model.fields.RolFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${rfh:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,RolFields.ROLID)}">
        <th>${rfh:getSortIcons(__theFilterForm,RolFields.ROLID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,RolFields.NOMID)}">
        <th>${rfh:getSortIcons(__theFilterForm,RolFields.NOMID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,RolFields.CODI)}">
        <th>${rfh:getSortIcons(__theFilterForm,RolFields.CODI)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,RolFields.DATACREACIO)}">
        <th>${rfh:getSortIcons(__theFilterForm,RolFields.DATACREACIO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,RolFields.ENTITATID)}">
        <th>${rfh:getSortIcons(__theFilterForm,RolFields.ENTITATID)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${rfh:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

