<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="ScanWebFields" className="es.caib.rfhab.model.fields.ScanWebFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${rfh:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ScanWebFields.DIGITALID)}">
        <th>${rfh:getSortIcons(__theFilterForm,ScanWebFields.DIGITALID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ScanWebFields.TRANSACTIONID)}">
        <th>${rfh:getSortIcons(__theFilterForm,ScanWebFields.TRANSACTIONID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ScanWebFields.TRANSACTIONWEBID)}">
        <th>${rfh:getSortIcons(__theFilterForm,ScanWebFields.TRANSACTIONWEBID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ScanWebFields.STATUS)}">
        <th>${rfh:getSortIcons(__theFilterForm,ScanWebFields.STATUS)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ScanWebFields.FITXERID)}">
        <th>${rfh:getSortIcons(__theFilterForm,ScanWebFields.FITXERID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ScanWebFields.FILEINFO)}">
        <th>${rfh:getSortIcons(__theFilterForm,ScanWebFields.FILEINFO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ScanWebFields.SIGNEDFILEINFO)}">
        <th>${rfh:getSortIcons(__theFilterForm,ScanWebFields.SIGNEDFILEINFO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ScanWebFields.METADADES)}">
        <th>${rfh:getSortIcons(__theFilterForm,ScanWebFields.METADADES)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ScanWebFields.MISSATGE)}">
        <th>${rfh:getSortIcons(__theFilterForm,ScanWebFields.MISSATGE)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ScanWebFields.USUARIID)}">
        <th>${rfh:getSortIcons(__theFilterForm,ScanWebFields.USUARIID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ScanWebFields.DATACREACIO)}">
        <th>${rfh:getSortIcons(__theFilterForm,ScanWebFields.DATACREACIO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,ScanWebFields.ENTITATID)}">
        <th>${rfh:getSortIcons(__theFilterForm,ScanWebFields.ENTITATID)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${rfh:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

