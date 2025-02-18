<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PluginFields" className="es.caib.rfhab.model.fields.PluginFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${rfh:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginFields.PLUGINID)}">
        <th>${rfh:getSortIcons(__theFilterForm,PluginFields.PLUGINID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginFields.NOM)}">
        <th>${rfh:getSortIcons(__theFilterForm,PluginFields.NOM)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginFields.DESCRIPCIO)}">
        <th>${rfh:getSortIcons(__theFilterForm,PluginFields.DESCRIPCIO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginFields.CLASSE)}">
        <th>${rfh:getSortIcons(__theFilterForm,PluginFields.CLASSE)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginFields.ENTITATID)}">
        <th>${rfh:getSortIcons(__theFilterForm,PluginFields.ENTITATID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginFields.PROPERTIES)}">
        <th>${rfh:getSortIcons(__theFilterForm,PluginFields.PROPERTIES)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginFields.ACTIU)}">
        <th>${rfh:getSortIcons(__theFilterForm,PluginFields.ACTIU)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginFields.DATACREACIO)}">
        <th>${rfh:getSortIcons(__theFilterForm,PluginFields.DATACREACIO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PluginFields.TIPUS)}">
        <th>${rfh:getSortIcons(__theFilterForm,PluginFields.TIPUS)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${rfh:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

