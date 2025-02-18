<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="FuncionariRolFields" className="es.caib.rfhab.model.fields.FuncionariRolFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${rfh:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FuncionariRolFields.FUNCIONARIROLID)}">
        <th>${rfh:getSortIcons(__theFilterForm,FuncionariRolFields.FUNCIONARIROLID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FuncionariRolFields.FUNCIONARIID)}">
        <th>${rfh:getSortIcons(__theFilterForm,FuncionariRolFields.FUNCIONARIID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FuncionariRolFields.ROLID)}">
        <th>${rfh:getSortIcons(__theFilterForm,FuncionariRolFields.ROLID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FuncionariRolFields.DATACREACIO)}">
        <th>${rfh:getSortIcons(__theFilterForm,FuncionariRolFields.DATACREACIO)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${rfh:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

