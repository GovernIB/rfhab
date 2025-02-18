<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="FuncionariFields" className="es.caib.rfhab.model.fields.FuncionariFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${rfh:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FuncionariFields.FUNCIONARIID)}">
        <th>${rfh:getSortIcons(__theFilterForm,FuncionariFields.FUNCIONARIID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FuncionariFields.NUMERO)}">
        <th>${rfh:getSortIcons(__theFilterForm,FuncionariFields.NUMERO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FuncionariFields.NOM)}">
        <th>${rfh:getSortIcons(__theFilterForm,FuncionariFields.NOM)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FuncionariFields.LLINATGE1)}">
        <th>${rfh:getSortIcons(__theFilterForm,FuncionariFields.LLINATGE1)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FuncionariFields.LLINATGE2)}">
        <th>${rfh:getSortIcons(__theFilterForm,FuncionariFields.LLINATGE2)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FuncionariFields.TIPUSIDENTIFICADOR)}">
        <th>${rfh:getSortIcons(__theFilterForm,FuncionariFields.TIPUSIDENTIFICADOR)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FuncionariFields.IDENTIFICADOR)}">
        <th>${rfh:getSortIcons(__theFilterForm,FuncionariFields.IDENTIFICADOR)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FuncionariFields.USUARI)}">
        <th>${rfh:getSortIcons(__theFilterForm,FuncionariFields.USUARI)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FuncionariFields.CORREU)}">
        <th>${rfh:getSortIcons(__theFilterForm,FuncionariFields.CORREU)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FuncionariFields.DATACREACIO)}">
        <th>${rfh:getSortIcons(__theFilterForm,FuncionariFields.DATACREACIO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FuncionariFields.OBSERVACIONS)}">
        <th>${rfh:getSortIcons(__theFilterForm,FuncionariFields.OBSERVACIONS)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FuncionariFields.DATABAIXA)}">
        <th>${rfh:getSortIcons(__theFilterForm,FuncionariFields.DATABAIXA)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FuncionariFields.ENTITATID)}">
        <th>${rfh:getSortIcons(__theFilterForm,FuncionariFields.ENTITATID)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${rfh:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

