<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="FuncionariLlocFields" className="es.caib.rfhab.model.fields.FuncionariLlocFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${rfh:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FuncionariLlocFields.FUNCIONARILLOCID)}">
        <th>${rfh:getSortIcons(__theFilterForm,FuncionariLlocFields.FUNCIONARILLOCID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FuncionariLlocFields.LLOCID)}">
        <th>${rfh:getSortIcons(__theFilterForm,FuncionariLlocFields.LLOCID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FuncionariLlocFields.FUNCIONARIID)}">
        <th>${rfh:getSortIcons(__theFilterForm,FuncionariLlocFields.FUNCIONARIID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FuncionariLlocFields.DATAINICI)}">
        <th>${rfh:getSortIcons(__theFilterForm,FuncionariLlocFields.DATAINICI)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FuncionariLlocFields.DATAFI)}">
        <th>${rfh:getSortIcons(__theFilterForm,FuncionariLlocFields.DATAFI)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FuncionariLlocFields.DATACREACIO)}">
        <th>${rfh:getSortIcons(__theFilterForm,FuncionariLlocFields.DATACREACIO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FuncionariLlocFields.USUARIID)}">
        <th>${rfh:getSortIcons(__theFilterForm,FuncionariLlocFields.USUARIID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FuncionariLlocFields.NUMEROCAI)}">
        <th>${rfh:getSortIcons(__theFilterForm,FuncionariLlocFields.NUMEROCAI)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${rfh:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

